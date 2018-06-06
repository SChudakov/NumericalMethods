package com.sschudakov.system.formula;

import com.sschudakov.equationandsystem.formula.SystemSolvingFormulas;
import com.sschudakov.equationandsystem.util.Utils;
import org.junit.Test;

import java.util.Arrays;

public class SystemDichotomyMethodFormulasTest {

    @Test
    public void matrixNorm() {
    }

    @Test
    public void calculateConditionNumber() {
    }

    @Test
    public void squareRootsDeterminant() {
    }

    @Test
    public void calculateInverseMatrix() {
        double[][] matrix = new double[][]{
                {7.0, 4.0}, {5.0, 3.0}
        };
        System.out.println("inverse matrix");
        for (double[] doubles : SystemSolvingFormulas.calculateInverseMatrix(matrix)) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    public void matrixProduct() {
        System.out.println("matrix product");
        double[][] matrix = new double[][]{
                {7.0, 4.0}, {5.0, 3.0}
        };
        double[][] inverseMatrix = SystemSolvingFormulas.calculateInverseMatrix(matrix);
        for (double[] doubles : Utils.matrixProduct(inverseMatrix, matrix)) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    public void copyMatrix() {
    }

    @Test
    public void copyVector() {
    }

    @Test
    public void multiply() {
    }
}