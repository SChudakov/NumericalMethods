package com.sschudakov.equationandsystem.manager;

import com.sschudakov.equationandsystem.formula.SquareRootMethodFormulas;
import com.sschudakov.equationandsystem.formula.SystemSolvingFormulas;
import com.sschudakov.equationandsystem.method.SquareRootMethod;
import com.sschudakov.equationandsystem.util.Utils;

import java.util.Arrays;

public class SquareRootMethodManager {

    private SquareRootMethod squareRootMethod;

    private double[][] inverseMatrix;
    private double[][] matrixProduct;
    private double conditionNumber;
    private double determinant;

    public SquareRootMethod getSquareRootMethod() {
        return squareRootMethod;
    }


    public SquareRootMethodManager(double[][] matrix, double[] rightRart) {
        this.squareRootMethod = new SquareRootMethod(matrix, rightRart);
    }

    public void solve() {
        this.squareRootMethod.solve();
        this.inverseMatrix = SystemSolvingFormulas.calculateInverseMatrix(this.squareRootMethod.getA());
        this.matrixProduct = Utils.matrixProduct(this.squareRootMethod.getA(), this.inverseMatrix);
        this.conditionNumber = SystemSolvingFormulas.calculateConditionNumber(this.squareRootMethod.getA(), this.inverseMatrix);
        this.determinant = SquareRootMethodFormulas.squareRootsDeterminant(
                this.squareRootMethod.getSt(),
                this.squareRootMethod.getD(),
                this.squareRootMethod.getS()
        );
    }

    public String getStraightForwardSolution() {
        return Arrays.toString(this.squareRootMethod.getX());
    }

    public double getConditionNumber() {
        return this.conditionNumber;
    }

    public double getDeterminant() {
        return this.determinant;
    }

    public String getInverseMatrix() {
        return Utils.matrixToString(this.inverseMatrix);
    }

    public String getMatrixProduct() {
        return Utils.matrixToString(this.matrixProduct);
    }


}
