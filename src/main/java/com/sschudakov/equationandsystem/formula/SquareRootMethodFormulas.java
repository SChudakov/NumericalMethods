package com.sschudakov.equationandsystem.formula;

public class SquareRootMethodFormulas {
    public static double squareRootsDeterminant(double[][] St, double[][] D, double[][] S) {
        double result = 1;
        for (int i = 0; i < D.length; i++) {
            result *= St[i][i] * D[i][i] * S[i][i];
        }
        return result;
    }
}
