package com.sschudakov.eigenvalues.solver;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;

public enum InitialApproximationFormer {

    UNIT_VECTOR() {
        @Override
        RealVector formApproximation(int dimension) {

            double[] data = new double[dimension];
            for (int i = 0; i < dimension; i++) {
                data[i] = 1.0;
            }
            return MatrixUtils.createRealVector(data);
        }
    };

    abstract RealVector formApproximation(int dimension);
}
