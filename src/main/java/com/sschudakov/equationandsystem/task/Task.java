package com.sschudakov.equationandsystem.task;

import com.sschudakov.equationandsystem.function.DichotomyFunction;
import com.sschudakov.equationandsystem.function.NewtonFunction;

public class Task {

    public static final double dichotomyFirstIntervalBegin = 0;
    public static final double dichotomyFirstIntervalEnd = 1;

    public static final double dichotomySecondIntervalBegin = -2;
    public static final double dichotomySecondIntervalEnd = -1;

    public static final double newtonFirstSolutionXZero = 0.4;
    public static final double newtonFirstIntervalDerivativeMinimum = 17.0 / 8.0;
    public static final double newtonFirstIntervalSecondDerivativeMaximum = 3;
    public static final double newtonFirstSolutionApproximation = 0.5;

    public static final double newtonSecondSolutionXZero = -1.4;
    public static final double newtonSecondSolutionApproximation = -1.3;
    public static final double newtonSecondIntervalDerivativeMinimum = 12.975;
    public static final double newtonSecondIntervalSecondDerivativeMaximum = 23.52;


    public static final double precision = 0.0001;

    public static final DichotomyFunction dichotomyFunction = new DichotomyFunction() {
        @Override
        public double calculateValue(double point) {
            return (Math.pow(point, 4) + (2 * point) - 1);
        }
    };

    public static final NewtonFunction newtonFunction = new NewtonFunction() {
        @Override
        public double calculateDerivative(double point) {
            return 4 * Math.pow(point, 3) + 2;
        }

        @Override
        public double calculateValue(double point) {
            return Math.pow(point, 4) + 2 * point - 1;
        }
    };

    public static final double[][] MATRIX = new double[][]{
            {4.0, -1.0, 1.0, 0.0},
            {-1.0, 4.0, -1.0, 1.0},
            {1.0, -1.0, 4.0, -1.0},
            {0.0, 1.0, -1.0, 4.0}
    };
    public static final double[] RIGHT_PART = new double[]{1.0, 1.0, 1.0, 1.0};

    public static final double PRECISION = 0.0001;

    public static final double[] INITIAL_APPROXIMATION = new double[]{0, 0, 0, 0};
}
