package com.sschudakov.equationandsystem.manager;

import com.sschudakov.equationandsystem.method.JakobiMethod;
import com.sschudakov.equationandsystem.util.Utils;

import java.util.Arrays;

public class JakobiMethodManager {

    private JakobiMethod jakobiMethod;

    public JakobiMethod getJakobiMethod() {
        return jakobiMethod;
    }

    public JakobiMethodManager(double[][] matrix, double[] rightPart) {
        this.jakobiMethod = new JakobiMethod(matrix, rightPart);
    }

    public void solve(String precision, String initialSolution) {
        solve(
                Double.valueOf(precision),
                Utils.convertToDoubleArray(initialSolution)
        );
    }

    public void solve(double precision, double[] initialSolution) {
        this.jakobiMethod.solve(precision, initialSolution);
    }

    public String getIterativeSolution() {
        return Arrays.toString(this.jakobiMethod.getX());
    }
}
