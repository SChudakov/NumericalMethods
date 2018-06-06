package com.sschudakov.equationandsystem.solving.newton;

import com.sschudakov.equationandsystem.function.NewtonFunction;
import com.sschudakov.equationandsystem.method.NewtonMethod;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class NewtonMethodTest {

    private NewtonMethod newtonMethod;

    @Before
    public void init() {
        this.newtonMethod = new NewtonMethod(new NewtonFunction() {
            @Override
            public double calculateDerivative(double point) {
                return 4 * Math.pow(point, 3) + 2;
            }

            @Override
            public double calculateValue(double point) {
                return Math.pow(point, 4) + 2 * point - 1;
            }
        });

        this.newtonMethod.setxZero(0.4);
        this.newtonMethod.setSolutionApproximation(0.5);
    }
    @Ignore
    @Test
    public void solve() {
        double precision = 0.0000000000001;
        this.newtonMethod.solve(precision);
        System.out.println("solution: " + this.newtonMethod.getSolution());
        System.out.println("number of iterations: " + this.newtonMethod.getNumOfIterations());
        System.out.println("expected number of iterations: " + this.newtonMethod.getExpectedNumOdIterations());
    }

    @Ignore
    @Test(timeout = 1_000)
    public void solveSeveralPrecisions() {
        double precision = 0.0001;
        for (int i = 0; i < 10; i++) {
            this.newtonMethod.solve(precision);
            System.out.println();
            System.out.println("precision: " + precision);
            System.out.println("solution: " + this.newtonMethod.getSolution());
            System.out.println("FUNCTION value: " + this.newtonMethod.getFunctionValue());
            System.out.println();
            precision /= 10;
        }

    }
}