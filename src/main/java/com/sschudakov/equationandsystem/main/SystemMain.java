package com.sschudakov.equationandsystem.main;

import com.sschudakov.equationandsystem.manager.JakobiMethodManager;
import com.sschudakov.equationandsystem.manager.SquareRootMethodManager;
import com.sschudakov.equationandsystem.task.Task;
import com.sschudakov.equationandsystem.util.Utils;

import java.util.Arrays;

public class SystemMain {

    public static void main(String[] args) {
        /*squareRootsMethod();*/
        jakobiMethod();
    }

    private static void squareRootsMethod() {
        System.out.println("\nsquare root method\n");

        SquareRootMethodManager squareRootMethodManager = new SquareRootMethodManager(Task.MATRIX, Task.RIGHT_PART);
        squareRootMethodManager.solve();


        System.out.println("S:");
        for (double[] doubles : squareRootMethodManager.getSquareRootMethod().getS()) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println("St:");
        for (double[] doubles : squareRootMethodManager.getSquareRootMethod().getSt()) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println("D:");
        for (double[] doubles : squareRootMethodManager.getSquareRootMethod().getD()) {
            System.out.println(Arrays.toString(doubles));
        }

        System.out.println("y:");
        System.out.println(Arrays.toString(squareRootMethodManager.getSquareRootMethod().getY()));

        System.out.println("x:");
        System.out.println(Arrays.toString(squareRootMethodManager.getSquareRootMethod().getX()));

        System.out.println("condition number:");
        System.out.println(squareRootMethodManager.getConditionNumber());

        System.out.println("determinant number:");
        System.out.println(squareRootMethodManager.getDeterminant());

        System.out.println("inverse matrix:");
        System.out.println(squareRootMethodManager.getInverseMatrix());

        System.out.println("matrix product:");
        System.out.println(squareRootMethodManager.getMatrixProduct());

        System.out.println("S^T*D*S");
        for (double[] doubles : Utils.matrixProduct(
                squareRootMethodManager.getSquareRootMethod().getSt(),
                squareRootMethodManager.getSquareRootMethod().getS()
        )) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    private static void jakobiMethod() {
        System.out.println("\nNewtons method\n");

        JakobiMethodManager jakobiMethodManager = new JakobiMethodManager(Task.MATRIX, Task.RIGHT_PART);
        jakobiMethodManager.solve(Task.PRECISION, Task.INITIAL_APPROXIMATION);


        System.out.println("A;");
        for (double[] doubles : jakobiMethodManager.getJakobiMethod().getA()) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();

        System.out.println("b");
        System.out.println(Arrays.toString(jakobiMethodManager.getJakobiMethod().getB()));
        System.out.println();

        System.out.println("normalized matrix:\n");
        for (double[] doubles : jakobiMethodManager.getJakobiMethod().getNormalizedA()) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();

        System.out.println("normalized b:\n");
        System.out.println(Arrays.toString(jakobiMethodManager.getJakobiMethod().getNormalizedB()));
        System.out.println();

        System.out.println("q:\n");
        System.out.println(jakobiMethodManager.getJakobiMethod().getQ());
        System.out.println();

        System.out.println("solution:\n");
        System.out.println(Arrays.toString(jakobiMethodManager.getJakobiMethod().getX()));
        System.out.println();
    }
}
