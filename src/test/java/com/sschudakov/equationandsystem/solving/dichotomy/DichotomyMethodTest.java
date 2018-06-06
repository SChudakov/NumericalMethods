package com.sschudakov.equationandsystem.solving.dichotomy;

import com.sschudakov.equationandsystem.function.DichotomyFunction;
import com.sschudakov.equationandsystem.method.DichotomyMethod;
import org.junit.Before;
import org.junit.Test;

public class DichotomyMethodTest {

    private DichotomyMethod dichotomyMethod;

    @Before
    public void inti() {
        this.dichotomyMethod = new DichotomyMethod(new DichotomyFunction() {
            @Override
            public double calculateValue(double point) {
                return Math.pow(point, 4) + 2 * point - 1;
            }
        });
        this.dichotomyMethod.setaEndpoint(0d);
        this.dichotomyMethod.setbEndpoint(1d);

    }

    @Test(timeout = 1_000)
    public void solve() {
        double precision = 0.00000_00001;
        this.dichotomyMethod.solve(precision);
        System.out.println("solution: " + this.dichotomyMethod.getSolution());
        System.out.println("number of iterations: " + this.dichotomyMethod.getNumOfIterations());
        System.out.println("expected number of iterations: " + this.dichotomyMethod.getExpectedNumberOfIterations());
    }

    @Test(timeout = 1_000)
    public void solveSeveralPrecisions() {
        double precision = 0.0001;
        for (int i = 0; i < 10; i++) {
            this.dichotomyMethod.solve(precision);
            System.out.println();
            System.out.println("precision: " + precision);
            System.out.println("solution: " + this.dichotomyMethod.getSolution());
            System.out.println("FUNCTION value: " + this.dichotomyMethod.getFunctionValue());
            System.out.println();
            precision /= 10;
        }

    }
}