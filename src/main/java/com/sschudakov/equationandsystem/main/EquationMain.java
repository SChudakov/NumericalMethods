package com.sschudakov.equationandsystem.main;

import com.sschudakov.equationandsystem.method.DichotomyMethod;
import com.sschudakov.equationandsystem.method.NewtonMethod;
import com.sschudakov.equationandsystem.task.Task;

public class EquationMain {

    public static void main(String[] args) {

        dichotomyFirstSolution();
        /*dichotomySecondSolution();
        newtonFirstSolution();
        newtonSecondSolution();*/
    }

    private static void dichotomyFirstSolution() {
        System.out.println("\nsolving equationandsystem with dichotomy method on the [0,1] (expected 0.4746)\n");

        DichotomyMethod dichotomyMethod = new DichotomyMethod(Task.dichotomyFunction);
        dichotomyMethod.setaEndpoint(Task.dichotomyFirstIntervalBegin);
        dichotomyMethod.setbEndpoint(Task.dichotomyFirstIntervalEnd);
        dichotomyMethod.solve(Task.precision);
    }

    private static void dichotomySecondSolution() {
        System.out.println("\nsolving equationandsystem with dichotomy method on the [-2,-1] (expected -1.3953)\n");

        DichotomyMethod dichotomyMethod = new DichotomyMethod(Task.dichotomyFunction);
        dichotomyMethod.setaEndpoint(Task.dichotomySecondIntervalBegin);
        dichotomyMethod.setbEndpoint(Task.dichotomySecondIntervalEnd);
        dichotomyMethod.solve(Task.precision);
    }

    private static void newtonFirstSolution() {
        System.out.println("\nsolving equationandsystem with Newtons method on the [0.4,0.5] (expected 0.4746)\n");

        NewtonMethod newtonMethod = new NewtonMethod(Task.newtonFunction);
        newtonMethod.setxZero(Task.newtonFirstSolutionXZero);
        newtonMethod.setSolutionApproximation(Task.newtonFirstSolutionApproximation);
        newtonMethod.setDerivativeMinimum(Task.newtonFirstIntervalDerivativeMinimum);
        newtonMethod.setSecondOrderDerivativeMaximum(Task.newtonFirstIntervalSecondDerivativeMaximum);
        newtonMethod.solve(Task.newtonFirstIntervalSecondDerivativeMaximum);
    }

    private static void newtonSecondSolution() {
        System.out.println("\nsolving equationandsystem with Newtons method on the [-1.4,-1.3] (expected 0.4746)\n");

        NewtonMethod newtonMethod = new NewtonMethod(Task.newtonFunction);
        newtonMethod.setxZero(Task.newtonSecondSolutionXZero);
        newtonMethod.setSolutionApproximation(Task.newtonSecondSolutionApproximation);
        newtonMethod.setDerivativeMinimum(Task.newtonSecondIntervalDerivativeMinimum);
        newtonMethod.setSecondOrderDerivativeMaximum(Task.newtonSecondIntervalSecondDerivativeMaximum);
        newtonMethod.solve(Task.precision);
    }
}
