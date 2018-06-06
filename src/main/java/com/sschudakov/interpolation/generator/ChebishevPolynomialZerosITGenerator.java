package com.sschudakov.interpolation.generator;

import com.sschudakov.interpolation.table.InterpolationTable;
import com.sschudakov.interpolation.evaluation.StringFunctionEvaluator;

import javax.script.ScriptException;

public class ChebishevPolynomialZerosITGenerator {
    public InterpolationTable generate(String function, Double aEndPoint, Double bEndPoint, int polynomialDegree) {
        InterpolationTable result = new InterpolationTable();
        StringFunctionEvaluator evaluator = StringFunctionEvaluator.forFunction(function);
        for (int i = 0; i < polynomialDegree; i++) {
            Double point = convertToTablePoint(
                    chebishevPolynomialZero(polynomialDegree, i),
                    aEndPoint,
                    bEndPoint
            );
            try {
                result.addColumn(
                        point,
                        evaluator.count(point)
                );
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Double convertToTablePoint(Double chebishevPolynomialZero, Double aEndpoint, Double bEndpoint) {
        return (aEndpoint + bEndpoint) / 2 - ((bEndpoint - aEndpoint) / 2) * chebishevPolynomialZero;
    }

    private Double chebishevPolynomialZero(int polynomialDegree, int zeroIndex) {
        return Math.cos((2 * zeroIndex + 1) * Math.PI / (2 * (polynomialDegree + 1)));
    }
}
