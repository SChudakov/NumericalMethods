package com.sschudakov.eigenvalues.main;

import com.sschudakov.eigenvalues.solver.DotProductMethodSolver;
import org.apache.commons.math3.linear.RealVector;

import java.util.Arrays;

public class DotProductMethodMain {
    public static void main(String[] args) {
        double[][] matrix = new double[][]{
                {1.0, 1.0 / 2.0, 1.0 / 3.0},
                {1.0 / 2.0, 1.0 / 3.0, 1.0 / 4.0},
                {1.0 / 3.0, 1.0 / 4.0, 1.0 / 5.0}
        };

        double precision = 0.0000000001;
        long begin = System.nanoTime();
        DotProductMethodSolver solver = new DotProductMethodSolver();
        long end = System.nanoTime();


        System.out.println("MATRIX");
        for (double[] doubles : matrix) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println("PRECISION: " + precision);

        System.out.println();
        double eigenvalue = solver.solve(matrix, precision);
        System.out.println("EIGENVALUE: " + eigenvalue);
        System.out.println("NUM OF ITERATIONS: " + solver.getNumOfIterations());

        System.out.println();
        System.out.println("\t\t\tVECTORS");
        System.out.println();
        for (RealVector realVector : solver.getVectors()) {
            System.out.println(realVector);
        }

        System.out.println();
        System.out.println("\t\t\tNORMALIZED VECTORS");
        System.out.println();
        for (RealVector realVector : solver.getNormalizedVectors()) {
            System.out.println(realVector);
        }

        System.out.println();
        System.out.println("\t\t\tAPPROXIMATIONS");
        System.out.println();
        for (Double aDouble : solver.getApproximations()) {
            System.out.println(aDouble);
        }

        System.out.println();
        System.out.println("NUM OF ADDITIONS: " + solver.getOperationsExecutor().getNumOfAdditions());
        System.out.println("NUM OF SUBTRACTIONS: " + solver.getOperationsExecutor().getNumOfSubtractions());
        System.out.println("NUM OF MULTIPLICATION: " + solver.getOperationsExecutor().getNumOfMultiplications());
        System.out.println("NUM OF DIVISIONS: " + solver.getOperationsExecutor().getNumOfDivisions());
        System.out.println();
        System.out.println("EXECUTION TIME: " + ((end - begin) / 1000));
    }
}
