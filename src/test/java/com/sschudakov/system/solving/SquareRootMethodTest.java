package com.sschudakov.system.solving;

import com.sschudakov.equationandsystem.method.SquareRootMethod;
import com.sschudakov.equationandsystem.util.Utils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SquareRootMethodTest {

    SquareRootMethod squareRootMethod;

    @Before
    public void inti() {
        this.squareRootMethod = new SquareRootMethod(new double[][]{
                {4.0, -1.0, 1.0, 0.0},
                {-1.0, 4.0, -1.0, 1.0},
                {1.0, -1.0, 4.0, -1.0},
                {0.0, 1.0, -1.0, 4.0}
        },
                new double[]{1.0, 1.0, 1.0, 1.0});
        this.squareRootMethod.solve();
    }

    @Test
    public void printA() {
        System.out.println("A:");
        for (double[] doubles : this.squareRootMethod.getA()) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    public void printB() {
        System.out.println("b:");
        System.out.println(Arrays.toString(this.squareRootMethod.getB()));
    }

    @Test
    public void printS() {
        System.out.println("S:");
        for (double[] doubles : this.squareRootMethod.getS()) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    public void printSt() {
        System.out.println("St:");
        for (double[] doubles : this.squareRootMethod.getSt()) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    public void printD() {
        System.out.println("D:");
        for (double[] doubles : this.squareRootMethod.getD()) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    public void checkDecomposition() {
        System.out.println("S * St");
        for (double[] doubles : Utils.matrixProduct(
                this.squareRootMethod.getSt(),
                this.squareRootMethod.getS()
        )) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    public void printY() {
        System.out.println("y:");
        System.out.println(Arrays.toString(this.squareRootMethod.getY()));
    }

    @Test
    public void printX() {
        System.out.println("x:");
        System.out.println(Arrays.toString(this.squareRootMethod.getX()));
    }

    @Test
    public void checkY() {
        System.out.println("St * y");
        System.out.println(Arrays.toString(Utils.multiply(
                this.squareRootMethod.getSt(),
                this.squareRootMethod.getY()
        )));
    }

    @Test
    public void checkX() {
        System.out.println("St * y");
        System.out.println(Arrays.toString(Utils.multiply(
                this.squareRootMethod.getS(),
                this.squareRootMethod.getX()
        )));
    }

}