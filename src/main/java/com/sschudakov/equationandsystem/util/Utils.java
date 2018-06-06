package com.sschudakov.equationandsystem.util;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Utils {

    public static double[][] matrixProduct(double[][] a, double[][] b) {
        int aRows = a.length;
        int aColumns = a[0].length;
        int bRows = b.length;
        int bColumns = b[0].length;
        if (aColumns != bRows) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[][] result = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] copyMatrix(double[][] matrix) {
        /*double[][] result = new double[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                result[i][j] = a[i][j];
            }
        }
        return result;*/
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        return realMatrix.copy().getData();
    }

    public static double[] copyVector(double[] vector) {
        /*double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i];
        }
        return result;*/

        RealVector realVector = MatrixUtils.createRealVector(vector);
        return realVector.copy().toArray();
    }

    public static double[] multiply(double[][] matrix, double[] vector) {
        /*double[] result = new double[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] += matrix[i][j] * vector[i];
            }
        }
        return result;*/

        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        RealVector realVector = MatrixUtils.createRealVector(vector);
        return realMatrix.operate(realVector).toArray();
    }

    public static String matrixToString(double[][] matrix) {
        StringBuilder result = new StringBuilder("");
        for (double[] doubles : matrix) {
            result.append(arrayToString(doubles) + "\n");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    private static String arrayToString(double[] array) {
        StringBuilder result = new StringBuilder("");
        for (double v : array) {
            result.append("\t" + v + ",");
        }
        return result.deleteCharAt(0).deleteCharAt(result.length() - 1).toString();
    }

    public static double[] convertToDoubleArray(String vector) {
        String[] coordinates = vector.split(",");
        double[] result = new double[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            result[i] = Double.valueOf(coordinates[i]);
        }
        return result;
    }
}
