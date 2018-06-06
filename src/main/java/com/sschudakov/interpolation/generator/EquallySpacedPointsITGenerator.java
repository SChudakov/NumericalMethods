package com.sschudakov.interpolation.generator;

import com.sschudakov.interpolation.evaluation.StringFunctionEvaluator;
import com.sschudakov.interpolation.table.InterpolationTable;

import javax.script.ScriptException;

public class EquallySpacedPointsITGenerator {
    public InterpolationTable generate(String function, Double aEndpoint, Double bEndpoint, Integer numOfPoints) {
        InterpolationTable result = new InterpolationTable();
        StringFunctionEvaluator evaluator = StringFunctionEvaluator.forFunction(function);

        double pointsSpaces = calculatePointSpace(aEndpoint, bEndpoint, numOfPoints);

        for (double point = aEndpoint; point < bEndpoint; point += pointsSpaces) {
            try {
                result.addColumn(point, evaluator.count(point));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private double calculatePointSpace(Double aEndpoint, Double bEndpoint, Integer numOfPoints) {
        System.out.println(aEndpoint);
        System.out.println(bEndpoint);
        System.out.println(bEndpoint-aEndpoint);
        System.out.println((bEndpoint-aEndpoint)/numOfPoints);
        return (bEndpoint-aEndpoint)/numOfPoints;
    }

}
