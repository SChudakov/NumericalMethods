package com.sschudakov.system.solving;

import com.sschudakov.equationandsystem.method.JakobiMethod;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class JakobiMethodTest {

    private JakobiMethod jakobiMethod;

    @Before
    public void init() {
        this.jakobiMethod = new JakobiMethod(new double[][]{
                {4.0, -1.0, 1.0, 0.0},
                {-1.0, 4.0, -1.0, 1.0},
                {1.0, -1.0, 4.0, -1.0},
                {0.0, 1.0, -1.0, 4.0}
        },
                new double[]{1.0, 1.0, 1.0, 1.0});
    }

    @Test
    public void solve() {
        double precision = 0.0001d;
        this.jakobiMethod.solve(precision, new double[]{0, 0, 0, 0});
        System.out.println("A;");
        for (double[] doubles : this.jakobiMethod.getA()) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();

        System.out.println("b");
        System.out.println(Arrays.toString(this.jakobiMethod.getB()));
        System.out.println();

        System.out.println("normalized matrix:\n");
        for (double[] doubles : this.jakobiMethod.getNormalizedA()) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();

        System.out.println("normalized b:\n");
        System.out.println(Arrays.toString(this.jakobiMethod.getNormalizedB()));
        System.out.println();

        System.out.println("q:\n");
        System.out.println(this.jakobiMethod.getQ());
        System.out.println();

        System.out.println("solution:\n");
        System.out.println(Arrays.toString(this.jakobiMethod.getX()));
        System.out.println();
    }
}