package com.sschudakov.equationandsystem.formula;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;

public class JakobiMethodFormulas {
    public static double jacobiMethodOversight(double q, double[] xZero, double[] xOne, int numOfIterations) {
        RealVector xZoroVector = MatrixUtils.createRealVector(xZero);
        RealVector xOneVector = MatrixUtils.createRealVector(xOne);
        return Math.pow(q, numOfIterations) / (1 - q) * xZoroVector.subtract(xOneVector).getNorm();
    }
}
