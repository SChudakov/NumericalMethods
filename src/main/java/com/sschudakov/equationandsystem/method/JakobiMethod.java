package com.sschudakov.equationandsystem.method;

import com.sschudakov.equationandsystem.formula.JakobiMethodFormulas;
import com.sschudakov.equationandsystem.util.Utils;

public class JakobiMethod {


    private double[][] A;
    private double[] b;
    private double[][] normalizedA;
    private double[] normalizedB;
    private double[] x;

    private double q;

    //getters and setters
    public double[][] getA() {
        return A;
    }

    public void setA(double[][] a) {
        A = a;
    }

    public double[] getB() {
        return b;
    }

    public void setB(double[] b) {
        this.b = b;
    }

    public double[][] getNormalizedA() {
        return normalizedA;
    }

    public void setNormalizedA(double[][] normalizedA) {
        this.normalizedA = normalizedA;
    }

    public double[] getNormalizedB() {
        return normalizedB;
    }

    public void setNormalizedB(double[] normalizedB) {
        this.normalizedB = normalizedB;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }


    public JakobiMethod(double[][] matrix, double[] rightPart) {
        this.A = matrix;
        this.b = rightPart;
        checkSimetricCondition(matrix);
        checkDiagonalCondition(matrix);
    }

    private void checkSimetricCondition(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    throw new IllegalArgumentException("matrix is not symmetric");
                }
            }
        }
    }

    private void checkDiagonalCondition(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (j != i) {
                    sum += Math.abs(matrix[i][j]);
                }
            }
            if (Math.abs(matrix[i][i]) < sum) {
                throw new IllegalArgumentException("task cannot be solved because converging condition is not met");
            }
        }
    }


    public void solve(double precision, double[] initialPrecision) {
        calculateQ();
        normalizeSystem();
        double[] xOne = calculateNextPrecision(initialPrecision);
        double[] currentIteration = xOne;

        int numOfIterations = 1;
        while (JakobiMethodFormulas.jacobiMethodOversight(this.q, initialPrecision, xOne, numOfIterations) >= precision ||
                Math.pow(q, numOfIterations) / (1 - q) > precision) {
            currentIteration = calculateNextPrecision(currentIteration);
            numOfIterations++;
        }
        this.x = currentIteration;
    }

    private double[] calculateNextPrecision(double[] currentPrecision) {
        double[] result = new double[currentPrecision.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < this.normalizedA[i].length; j++) {
                if (j != i) {
                    result[i] -= this.normalizedA[i][j] * currentPrecision[i];
                }
            }
            result[i] += this.normalizedB[i];
        }
        return result;
    }

    private void calculateQ() {
        double result = 0;
        double q;
        for (int i = 0; i < this.A.length; i++) {
            double sum = 0;
            for (int j = 0; j < this.A[i].length; j++) {
                if (j != i) {
                    sum += Math.abs(this.A[i][j]);
                }
            }
            q = sum / Math.abs(this.A[i][i]);
            if (q > result) {
                result = q;
            }
        }
        this.q = result;
    }

    private void normalizeSystem() {
        this.normalizedA = Utils.copyMatrix(this.A);
        this.normalizedB = Utils.copyVector(this.b);
        double diagonalCoefficient;
        for (int i = 0; i < this.A.length; i++) {
            diagonalCoefficient = this.A[i][i];
            this.normalizedB[i] /= diagonalCoefficient;
            for (int j = 0; j < this.A[i].length; j++) {
                this.normalizedA[i][j] /= diagonalCoefficient;
            }
        }
    }
}
