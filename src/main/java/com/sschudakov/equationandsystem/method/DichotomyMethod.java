package com.sschudakov.equationandsystem.method;

import com.sschudakov.equationandsystem.function.DichotomyFunction;
import com.sschudakov.equationandsystem.formula.DichotomyMethodFormulas;
import org.jetbrains.annotations.NotNull;

public class DichotomyMethod {

    @NotNull
    private DichotomyFunction dichotomyFunction;

    private Double aEndpoint;
    private Double bEndpoint;

    private Double solution;
    private Double functionValue;
    private Integer numOfIterations;
    private Integer expectedNumberOfIterations;

    @NotNull
    public DichotomyFunction getDichotomyFunction() {
        return dichotomyFunction;
    }

    public Double getaEndpoint() {
        return aEndpoint;
    }

    public void setaEndpoint(Double aEndpoint) {
        this.aEndpoint = aEndpoint;
    }

    public Double getbEndpoint() {
        return bEndpoint;
    }

    public void setbEndpoint(Double bEndpoint) {
        this.bEndpoint = bEndpoint;
    }

    public Double getSolution() {
        return solution;
    }

    public Double getFunctionValue() {
        return functionValue;
    }

    public Integer getNumOfIterations() {
        return numOfIterations;
    }

    public Integer getExpectedNumberOfIterations() {
        return expectedNumberOfIterations;
    }

    public DichotomyMethod(DichotomyFunction dichotomyFunction) {
        this.dichotomyFunction = dichotomyFunction;
    }

    public void solve(double precision) {
        if (this.dichotomyFunction.calculateValue(aEndpoint) * this.dichotomyFunction.calculateValue(bEndpoint) >= 0) {
            throw new IllegalArgumentException("value of f(x)f(b) should be < 0");
        }
        double a = this.aEndpoint;
        double b = this.bEndpoint;
        double x = (a + b) / 2;
        System.out.println("a0: " + a);
        System.out.println("b0: " + b);
        System.out.println("x0: " + x);
        System.out.println("FUNCTION value: " + this.dichotomyFunction.calculateValue(x));
        System.out.println("f(x)f(b): " +
                this.dichotomyFunction.calculateValue(a) * this.dichotomyFunction.calculateValue(b));
        int numOfIterations = 0;

        while (DichotomyMethodFormulas.dichotomyOversight(this.aEndpoint, this.bEndpoint, numOfIterations) > precision) {
            a = sgn(this.dichotomyFunction.calculateValue(a)) == sgn(this.dichotomyFunction.calculateValue(x)) ? x : a;
            b = sgn(this.dichotomyFunction.calculateValue(b)) == sgn(this.dichotomyFunction.calculateValue(x)) ? x : b;
            x = (a + b) / 2;

            if (this.dichotomyFunction.calculateValue(aEndpoint) * this.dichotomyFunction.calculateValue(bEndpoint) >= 0) {
                throw new IllegalArgumentException("equationandsystem has more tha 1 solution on the input interval");
            }

            System.out.println();
            System.out.println("a" + (numOfIterations + 1) + ": " + a);
            System.out.println("b" + (numOfIterations + 1) + ": " + b);
            System.out.println("x" + (numOfIterations + 1) + ": " + x);
            System.out.println("FUNCTION value: " + this.dichotomyFunction.calculateValue(x));

            numOfIterations++;
        }

        this.solution = x;
        this.functionValue = this.dichotomyFunction.calculateValue(x);
        this.numOfIterations = numOfIterations;
        this.expectedNumberOfIterations = DichotomyMethodFormulas.dichotomyExpectedNumberOfIterations(this.aEndpoint, this.bEndpoint, precision);
    }

    private static int sgn(double x) {
        if (x > 0) {
            return 1;
        }
        if (x < 0) {
            return -1;
        }
        return 0;
    }
}
