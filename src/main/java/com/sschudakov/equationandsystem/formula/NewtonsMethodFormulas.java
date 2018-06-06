package com.sschudakov.equationandsystem.formula;

public class NewtonsMethodFormulas {
    public static int newtonExpectedNumberOfIterations(double xZero, double solutionApproximation,
                                                       double q, double precision) {
        double numerator = Math.log((Math.log(Math.abs(xZero - solutionApproximation) / precision)) / Math.log(2));
        double denominator = Math.log(1 / q) / Math.log(2);
        double outerLogarithm = Math.log(numerator / denominator + 1) / Math.log(2);
        double result = outerLogarithm + 1;
        return (int) result;
    }

    public static double newtonOversight(double q, double xZero, double solutionApproximation, int numOfIterations) {
        return Math.pow(q, Math.pow(2, numOfIterations) - 1) * Math.abs(xZero - solutionApproximation);
    }

    public static double newtonMethodQ(double derivativeAbsMinimum, double secondOrderDerivativeAbsMaximum,
                                       double xZero, double solutionsApproximation) {
        return secondOrderDerivativeAbsMaximum * Math.abs(xZero - solutionsApproximation)
                / (2 * derivativeAbsMinimum);
    }
}
