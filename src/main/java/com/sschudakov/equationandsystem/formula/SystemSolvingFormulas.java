package com.sschudakov.equationandsystem.formula;

import com.sschudakov.equationandsystem.method.SquareRootMethod;

public class SystemSolvingFormulas {
    public static double matrixNorm(double[][] matrix) {
        double result = 0;
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                sum += Math.abs(matrix[i][j]);
            }
            if (sum > result) {
                result = sum;
            }
        }
        return result;
        /*RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        return realMatrix.getNorm();*/
    }

    public static double calculateConditionNumber(double[][] matrix, double[][] inverseMatrix) {
        return matrixNorm(matrix) * matrixNorm(inverseMatrix);
    }

    public static double[][] calculateInverseMatrix(double[][] matrix) {

        double[][] result = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            SquareRootMethod squareRootMethod = new SquareRootMethod(matrix, getIEMatrixVector(i, matrix.length));
            squareRootMethod.solve();
            /*for (int j = 0; j < matrix.length; j++) {
                result[j][i] = squareRootMethod.getX()[j];
            }*/
            copyDataIntoResultMatrix(result, squareRootMethod.getX(), i);
        }

        return result;
        /*RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        return luDecomposition.getSolver().getInverse().getData();*/
    }

    private static void copyDataIntoResultMatrix(double[][] matrix, double[] vector, int vectorPosition) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][vectorPosition] = vector[i];
        }
    }

    private static double[] getIEMatrixVector(int i, int vectorRange) {
        double[] result = new double[vectorRange];
        result[i] = 1;
        return result;
    }
}
