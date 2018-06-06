package com.sschudakov.interpolation.task;

public class Task {
    /*"(x*x + Math.sin(7*x) + Math.exp(-x*x))/1.0"*/
    public static final String FUNCTION = "1.0/(9*x*x+1)";
    public static final Double A_END_POINT = -3.0;
    public static final Double B_END_POINT = 3.0;
    public static final Integer NUM_OF_POINTS = 8;
    public static final Integer CHEBISHEV_POLYNOMIAL_DEGREE = 10;
}
