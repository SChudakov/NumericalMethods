package com.sschudakov.eigenvalues.solver;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;

public class DotProductMethodSolver {

    private MathOperationsExecutor operationsExecutor;
    private Integer numOfIterations;

    private List<RealVector> vectors;
    private List<RealVector> normalizedVectors;
    private List<Double> approximations;

    //getters
    public MathOperationsExecutor getOperationsExecutor() {
        return operationsExecutor;
    }

    public Integer getNumOfIterations() {
        return numOfIterations;
    }

    public List<RealVector> getVectors() {
        return vectors;
    }

    public List<RealVector> getNormalizedVectors() {
        return normalizedVectors;
    }

    public List<Double> getApproximations() {
        return approximations;
    }

    public DotProductMethodSolver() {
        this.operationsExecutor = new MathOperationsExecutor();
        this.vectors = new ArrayList<>();
        this.normalizedVectors = new ArrayList<>();
        this.approximations = new ArrayList<>();
    }


    public Double solve(double[][] matrix, Double precision) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        ensureIsSymmetric(realMatrix);

        RealVector initialApproximation = InitialApproximationFormer.UNIT_VECTOR
                .formApproximation(realMatrix.getRowDimension());
        this.vectors.add(initialApproximation.copy());

        RealVector firstVector = normalize(initialApproximation);
        this.normalizedVectors.add(firstVector);

        RealVector secondVector = produceVector(realMatrix, firstVector);
        this.vectors.add(secondVector.copy());

        Double firstApproximation = calculateApproximation(firstVector, secondVector);
        this.approximations.add(firstApproximation);

        firstVector = normalize(secondVector);
        this.normalizedVectors.add(firstVector);
        secondVector = produceVector(realMatrix, secondVector);
        this.vectors.add(secondVector.copy());

        Double secondApproximation = calculateApproximation(firstVector, secondVector);
        this.approximations.add(secondApproximation);

        Integer numOfIterations = 2;
        while (Math.abs(operationsExecutor.subtract(firstApproximation, secondApproximation)) > precision) {

            firstVector = normalize(secondVector);
            this.normalizedVectors.add(firstVector);
            secondVector = produceVector(realMatrix, secondVector);
            this.vectors.add(secondVector.copy());

            firstApproximation = secondApproximation;
            secondApproximation = calculateApproximation(firstVector, secondVector);
            this.approximations.add(secondApproximation);

            numOfIterations++;
        }
        this.numOfIterations = numOfIterations;
        return secondApproximation;
    }

    private RealVector produceVector(RealMatrix realMatrix, RealVector realVector) {
        this.operationsExecutor.increaseNumOFMultiplications(
                realMatrix.getRowDimension() *
                        realMatrix.getColumnDimension() *
                        realVector.getDimension()
        );
        this.operationsExecutor.increaseNumOFAdditions(
                realVector.getDimension() *
                        (realMatrix.getColumnDimension() - 1)
        );

        return realMatrix.operate(realVector);
    }

    private RealVector normalize(RealVector vector) {
        this.operationsExecutor.increaseNumOFDivisions(
                vector.getDimension()
        );
        return vector.mapDivideToSelf(
                Math.sqrt(vector.dotProduct(vector))
        );
    }

    private Double calculateApproximation(RealVector firstVector, RealVector secondVector) {
        this.operationsExecutor.increaseNumOFMultiplications(
                firstVector.getDimension()
        );
        this.operationsExecutor.increaseNumOFAdditions(
                firstVector.getDimension() - 1
        );
        return firstVector.dotProduct(secondVector);
    }

    private void ensureIsSymmetric(RealMatrix realMatrix) {
        if (!MatrixUtils.isSymmetric(realMatrix, 0.0D)) {
            throw new IllegalArgumentException("matrix should be symmetric");
        }
    }
}
