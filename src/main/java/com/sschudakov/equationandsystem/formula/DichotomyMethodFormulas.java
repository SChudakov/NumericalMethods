package com.sschudakov.equationandsystem.formula;

public class DichotomyMethodFormulas {

    public static int dichotomyExpectedNumberOfIterations(double a, double b, double precision) {
        return (int) (Math.log((b - a) / precision) / Math.log(2)) + 1;
    }

    public static double dichotomyOversight(double a, double b, int numOfIterations) {
        return (b - a) / Math.pow(2, numOfIterations + 1);
    }


}
