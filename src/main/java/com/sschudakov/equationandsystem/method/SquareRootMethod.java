package com.sschudakov.equationandsystem.method;

import com.sschudakov.equationandsystem.util.Utils;

public class SquareRootMethod {

    private int systemRange;

    private double[][] A;
    private double[][] S;
    private double[][] St;
    private double[][] D;
    private double[] b;
    private double[] x;
    private double[] y;

    //getters and setters
    public double[][] getA() {
        return A;
    }

    public double[][] getS() {
        return S;
    }

    public double[][] getSt() {
        return St;
    }

    public double[][] getD() {
        return D;
    }

    public double[] getB() {
        return b;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public SquareRootMethod(double[][] matrix, double[] rightPart) {
        this.A = matrix;
        this.b = rightPart;
        this.systemRange = matrix.length;
    }


    public void solve() {
        calculateSAndD();
        calculateSt(this.S);
        solveY(this.St, this.D, this.b);
        solveX(this.S, this.y);
    }

    private void calculateSAndD() {

        this.S = new double[this.systemRange][this.systemRange];
        this.D = new double[this.systemRange][this.systemRange];

        for (int i = 0, k = 0; i < this.systemRange; i++, k++) {
            for (int j = k; j < this.systemRange; j++) {
                if (i == j) {
                    D[i][i] = this.A[i][i];
                    for (int p = 0; p < i; p++) {
                        D[i][i] -= S[p][i] * S[p][i] * D[p][p];
                    }

                    S[i][i] = this.A[i][i];
                    for (int p = 0; p < i; p++) {
                        S[i][i] -= S[p][i] * S[p][i] * D[p][p];
                    }
                    D[i][i] = sgn(D[i][i]);
                    S[i][i] = Math.pow(Math.abs(S[i][i]), 0.5);
                } else {
                    S[i][j] = this.A[i][j];
                    for (int p = 0; p < i; p++) {
                        S[i][j] -= S[p][i] * D[p][p] * S[p][j];
                    }
                    S[i][j] /= D[i][i] * S[i][i];
                }
            }
        }
    }

    private void calculateSt(double[][] S) {
        this.St = new double[this.systemRange][this.systemRange];
        for (int i = 0; i < this.systemRange; i++) {
            for (int j = 0; j < this.systemRange; j++) {
                this.St[i][j] = S[j][i];
            }
        }
    }

    private void solveY(double[][] St, double[][] D, double[] b) {

        /*System.out.println("St");
        for (double[] doubles : St) {
            System.out.println(Arrays.toString(doubles));
        }*/
        double[] solutionsVector = Utils.copyVector(b);
        double[][] equationsMatrix = Utils.matrixProduct(
                St,
                D
        );
        double[] y = new double[b.length];

        for (int i = 0; i < equationsMatrix.length; i++) {
            double diagonalCoefficient = equationsMatrix[i][i];
            for (int j = i; j >= 0; j--) {
                equationsMatrix[i][j] /= diagonalCoefficient;
            }
            solutionsVector[i] /= diagonalCoefficient;
        }

        /*System.out.println("normalized matrix");

        for (double[] matrix : equationsMatrix) {
            System.out.println(Arrays.toString(matrix));
        }*/
        for (int i = 0; i < y.length; i++) {
            y[i] = solutionsVector[i];
            for (int j = i - 1; j >= 0; j--) {
                y[i] -= equationsMatrix[i][j] * y[j];
            }
        }
        this.y = y;
    }

    private void solveX(double[][] S, double[] y) {
        /*System.out.println("S");
        for (double[] doubles : S) {
            System.out.println(Arrays.toString(doubles));
        }*/

        double[][] equationsMatrix = Utils.copyMatrix(S);
        double[] solutionsVector = Utils.copyVector(y);
        double[] x = new double[y.length];

        for (int i = 0; i < equationsMatrix.length; i++) {
            double diagonalCoefficient = S[i][i];
            for (int j = i; j < equationsMatrix[i].length; j++) {
                equationsMatrix[i][j] /= diagonalCoefficient;
            }
            solutionsVector[i] /= diagonalCoefficient;
        }
        /*System.out.println("normalized matrix");
        for (double[] matrix : equationsMatrix) {
            System.out.println(Arrays.toString(matrix));
        }*/
        for (int i = equationsMatrix.length - 1; i >= 0; i--) {
            x[i] = solutionsVector[i];
            for (int j = i + 1; j < equationsMatrix[i].length; j++) {
                x[i] -= equationsMatrix[i][j] * x[j];
            }
        }
        this.x = x;
    }

    private static int sgn(double num) {
        if (num > 0) {
            return 1;
        }
        if (num < 0) {
            return -1;
        }
        return 0;
    }
}
