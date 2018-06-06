package com.sschudakov.equationandsystem.manager;

import com.sschudakov.equationandsystem.function.DichotomyFunction;
import com.sschudakov.equationandsystem.method.DichotomyMethod;

public class DichotomyMethodManager {

    private DichotomyMethod dichotomyMethod;

    public DichotomyMethodManager(DichotomyFunction dichotomyFunction) {
        this.dichotomyMethod = new DichotomyMethod(dichotomyFunction);
    }

    public double getSolution() {
        return this.dichotomyMethod.getSolution();
    }

    public double getFunctionValue() {
        return this.dichotomyMethod.getFunctionValue();
    }

    public int getNumOfIterations() {
        return this.dichotomyMethod.getNumOfIterations();
    }

    public int getExpectedNumOfIterations() {
        return this.dichotomyMethod.getExpectedNumberOfIterations();
    }

    public void solve(String precision) {
        this.dichotomyMethod.solve(
                convertString(precision)
        );
    }

    public void setEndpoints(String a, String b) {
        double aConverted = convertString(a);
        double bConverted = convertString(b);
        verifyEndpoints(aConverted, bConverted);
        this.dichotomyMethod.setaEndpoint(aConverted);
        this.dichotomyMethod.setbEndpoint(bConverted);
    }

    private void verifyEndpoints(double a, double b) {
        if (a > b) {
            throw new IllegalArgumentException("a endpoint cannot be more that b endpoint");
        }
        if (a == b) {
            throw new IllegalArgumentException("a endpoint cannot be equal to b endpoint");
        }
        if (this.dichotomyMethod.getDichotomyFunction().calculateValue(a)
                * this.dichotomyMethod.getDichotomyFunction().calculateValue(b) > 0) {
            throw new IllegalArgumentException("FUNCTION values must have different signs at a and b andpoints");
        }
    }

    private static double convertString(String number) {
        return Double.valueOf(number);
    }
}
