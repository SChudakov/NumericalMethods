package com.sschudakov.interpolation.generator;

import com.sschudakov.interpolation.table.InterpolationTable;

import java.util.List;

public class InterpolatingPolynomialGenerator {

    private static final int FIRST_POSITION = 0;

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";
    private static final String X = "x";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";

    public String generate(InterpolationTable table) {
        StringBuilder polynomial = new StringBuilder();

        polynomial.append(generatePolynomialSummand(
                table.getPoints(),
                FIRST_POSITION,
                table.getValue(FIRST_POSITION)
        ));

        for (int i = FIRST_POSITION + 1; i < table.size(); i++) {
            polynomial
                    .append(PLUS)
                    .append(generatePolynomialSummand(
                            table.getPoints(),
                            i,
                            table.getValue(i)
                    ));
        }

        return polynomial.toString();
    }

    private String generatePolynomialSummand(List<Double> points, int pointPosition, Double functionValue) {
        StringBuilder summand = new StringBuilder();
        summand
                .append(functionValue);

        if (points.size() > 1) {
            summand.append(MULTIPLICATION)
                    .append(generateFactorsSequence(X, MINUS, points, pointPosition))
                    .append(DIVISION)
                    .append(LEFT_PARENTHESIS)
                    .append(generateFactorsSequence(points.get(pointPosition), MINUS, points, pointPosition))
                    .append(RIGHT_PARENTHESIS);
        }

        return summand.toString();
    }

    private String generateFactorsSequence(Object firstValue, String operation, List<Double> points, int pointPosition) {
        StringBuilder numerator = new StringBuilder();

        boolean firstFactorAdded = false;

        if (pointPosition != FIRST_POSITION) {
            numerator.append(
                    generateFactor(
                            firstValue,
                            operation,
                            points.get(FIRST_POSITION)
                    )
            );
            firstFactorAdded = true;
        }

        for (int i = FIRST_POSITION + 1; i < points.size(); i++) {
            if (i != pointPosition) {
                if (firstFactorAdded) {
                    numerator.append(MULTIPLICATION);
                } else {
                    firstFactorAdded = true;
                }
                numerator.append(
                        generateFactor(
                                firstValue,
                                MINUS,
                                points.get(i)
                        )
                );
            }
        }
        return numerator.toString();
    }

    private String generateFactor(Object firstValue, String operation, Object secondValue) {

        StringBuilder result = new StringBuilder();

        result.append(LEFT_PARENTHESIS);
        result.append(firstValue);
        if (secondValue instanceof Double && operation.equals(MINUS)) {
            Double leftOperand = (Double) secondValue;
            if (leftOperand < 0) {
                result.append(PLUS);
                result.append(-leftOperand);
            } else {
                result.append(operation);
                result.append(leftOperand);
            }
        }
        result.append(RIGHT_PARENTHESIS);

        return result.toString();
    }
}
