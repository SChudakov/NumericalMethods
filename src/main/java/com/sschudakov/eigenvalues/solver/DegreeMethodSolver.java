package com.sschudakov.eigenvalues.solver;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;

public class DegreeMethodSolver {

    private MathOperationsExecutor operationsExecutor;
    private Integer numOfIterations;

    private List<RealVector> vectors;
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

    public List<Double> getApproximations() {
        return approximations;
    }

    public DegreeMethodSolver() {
        this.operationsExecutor = new MathOperationsExecutor();
        this.vectors = new ArrayList<>();
        this.approximations = new ArrayList<>();
    }


    public Double solve(double[][] matrix, Double precision) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        ensureIsSymmetric(realMatrix);

        RealVector firstVector = InitialApproximationFormer.UNIT_VECTOR.formApproximation(realMatrix.getRowDimension());
        this.vectors.add(firstVector);
        RealVector secondVector = produceVector(realMatrix, firstVector);
        this.vectors.add(secondVector);

        Double firstApproximation = calculateApproximation(firstVector, secondVector);
        this.approximations.add(firstApproximation);

        firstVector = secondVector;
        secondVector = produceVector(realMatrix, secondVector);
        this.vectors.add(secondVector);

        Double secondApproximation = calculateApproximation(firstVector, secondVector);
        this.approximations.add(secondApproximation);

        Integer numOfIterations = 2;
        while (Math.abs(operationsExecutor.subtract(firstApproximation, secondApproximation)) > precision) {

            firstVector = secondVector;
            secondVector = produceVector(realMatrix, secondVector);
            this.vectors.add(secondVector);

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

    private Double calculateApproximation(RealVector firstVector, RealVector secondVector) {
        return this.operationsExecutor.divide(
                secondVector.getEntry(0),
                firstVector.getEntry(0)
        );
    }

    private void ensureIsSymmetric(RealMatrix realMatrix) {
        if (!MatrixUtils.isSymmetric(realMatrix, 0.0D)) {
            throw new IllegalArgumentException("matrix should be symmetric");
        }
    }
}
