package com.sschudakov.equationandsystem.manager;

import com.sschudakov.equationandsystem.function.NewtonFunction;
import com.sschudakov.equationandsystem.method.NewtonMethod;

public class NewtonMethodManager {
    private NewtonMethod newtonMethod;

    public NewtonMethodManager(NewtonFunction newtonFunction) {
        this.newtonMethod = new NewtonMethod(newtonFunction);
    }

    public double getSolution() {
        return this.newtonMethod.getSolution();
    }

    public double getFunctionValue() {
        return this.newtonMethod.getFunctionValue();
    }

    public int getNumOfIterations() {
        return this.newtonMethod.getNumOfIterations();
    }

    public int getExpectedNumOfIterations() {
        return this.newtonMethod.getExpectedNumOdIterations();
    }

    private static double convertString(String number) {
        return Double.valueOf(number);
    }

    public void setParameters(String xZero, String approximateSolution, String derivativeMinimum, String sodm) {
        double xZeroConverted = Double.valueOf(xZero);
        double approximateSolutionConverted = Double.valueOf(approximateSolution);
        this.newtonMethod.setxZero(xZeroConverted);
        this.newtonMethod.setSolutionApproximation(approximateSolutionConverted);
        double convertedDerivativeMinimum = Double.valueOf(derivativeMinimum);
        double convertedSodm = Double.valueOf(sodm);
        checkConvertedDerivativeValues(convertedDerivativeMinimum, convertedSodm);
        this.newtonMethod.setDerivativeMinimum(convertedDerivativeMinimum);
        this.newtonMethod.setSecondOrderDerivativeMaximum(convertedSodm);
    }

    private void checkConvertedDerivativeValues(double derivativeMinimum, double sodm) {
        if (derivativeMinimum < 0) {
            throw new IllegalArgumentException("derivative minimum should be > 0");
        }
        if (sodm < 0) {
            throw new IllegalArgumentException("second derivative maximum should be should be < 0");
        }
    }

    public void solve(String precision) {
        this.newtonMethod.solve(
                convertString(precision)
        );
    }

}
