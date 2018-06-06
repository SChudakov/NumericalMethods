package com.sschudakov.equationandsystem.method;

import com.sschudakov.equationandsystem.formula.NewtonsMethodFormulas;
import com.sschudakov.equationandsystem.function.NewtonFunction;

public class NewtonMethod {

    private NewtonFunction newtonFunction;

    private Double solution;
    private Double functionValue;
    private Integer numOfIterations;
    private Integer expectedNumOdIterations;

    private Double q;

    private Double xZero;
    private Double solutionApproximation;

    private Double derivativeMinimum;
    private Double secondOrderDerivativeMaximum;

    public NewtonMethod(NewtonFunction newtonFunction) {
        this.newtonFunction = newtonFunction;
    }

    public NewtonFunction getNewtonFunction() {
        return newtonFunction;
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

    public Integer getExpectedNumOdIterations() {
        return expectedNumOdIterations;
    }

    public Double getQ() {
        return q;
    }

    public Double getxZero() {
        return xZero;
    }

    public void setxZero(Double xZero) {
        this.xZero = xZero;
    }

    public Double getSolutionApproximation() {
        return solutionApproximation;
    }

    public void setSolutionApproximation(Double solutionApproximation) {
        this.solutionApproximation = solutionApproximation;
    }

    public Double getDerivativeMinimum() {
        return derivativeMinimum;
    }

    public void setDerivativeMinimum(Double derivativeMinimum) {
        this.derivativeMinimum = derivativeMinimum;
    }

    public Double getSecondOrderDerivativeMaximum() {
        return secondOrderDerivativeMaximum;
    }

    public void setSecondOrderDerivativeMaximum(Double secondOrderDerivativeMaximum) {
        this.secondOrderDerivativeMaximum = secondOrderDerivativeMaximum;
    }

    public void solve(double precision) {
        if (this.newtonFunction.calculateValue(xZero)
                * this.newtonFunction.calculateValue(this.solutionApproximation) >= 0) {
            throw new IllegalArgumentException("value of f(x*)f(x0) should be < 0");
        }
        double x = this.xZero;
        this.q = NewtonsMethodFormulas.newtonMethodQ(
                this.derivativeMinimum,
                this.secondOrderDerivativeMaximum,
                this.xZero,
                this.solutionApproximation
        );
        if (q >= 1) {
            throw new IllegalArgumentException("values of first derivative minimum and second " +
                    "derivative maximum should be so that q < 1");
        }

        System.out.println("f(x*)f(x0): " + this.newtonFunction.calculateValue(xZero)
                * this.newtonFunction.calculateValue(this.solutionApproximation));
        System.out.println("x0: " + xZero);
        System.out.println("q: " + this.q);

        int numOfIterations = 0;

        while (NewtonsMethodFormulas.newtonOversight(
                q,
                this.xZero,
                this.solutionApproximation,
                numOfIterations
        ) > precision) {
            x = x - this.newtonFunction.calculateValue(x)
                    / this.newtonFunction.calculateDerivative(x);
            System.out.println("x" + (numOfIterations + 1) + ": " + x);
            numOfIterations++;

        }
        this.solution = x;
        this.functionValue = this.newtonFunction.calculateValue(x);
        this.numOfIterations = numOfIterations;
        this.expectedNumOdIterations = NewtonsMethodFormulas.newtonExpectedNumberOfIterations(
                this.xZero,
                this.solutionApproximation,
                this.q,
                precision
        );
    }
}
