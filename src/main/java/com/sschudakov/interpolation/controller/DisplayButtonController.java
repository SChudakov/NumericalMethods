package com.sschudakov.interpolation.controller;

import com.sschudakov.interpolation.dto.PlotConvertedParametersDTO;
import com.sschudakov.interpolation.dto.PlotStringParametersDTO;
import com.sschudakov.interpolation.evaluation.StringFunctionEvaluator;

import javax.script.ScriptException;

public class DisplayButtonController {


    public PlotConvertedParametersDTO verifyPlotParameters(PlotStringParametersDTO dto) {
        PlotConvertedParametersDTO result = new PlotConvertedParametersDTO();

        verifyFunction(dto.getFunctionString());

        Double aEndpoint = null;
        try {
            aEndpoint = Double.valueOf(dto.getaEndpointString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("a endpoint is not correct");
        }

        Double bEndpoint = null;
        try {
            bEndpoint = Double.valueOf(dto.getbEndpointString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("b endpoint is not correct");
        }
        verifyEndpoints(
                aEndpoint,
                bEndpoint
        );

        Integer numOfPoints = null;
        try {
            numOfPoints = Integer.valueOf(dto.getNumOfPointsString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("num of points is not correct");
        }
        verifyNumOfPoints(numOfPoints);

        Integer polynomialDegree = null;
        try {
            polynomialDegree = Integer.valueOf(dto.getPolynomialDegreeString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("polynomial degree is not correct");
        }
        verifyPolynomialDegree(polynomialDegree);

        Double pointSpace = null;
        try {
            pointSpace = Double.valueOf(dto.getDatasetPointsSpaceString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("points space is not correct");
        }
        verifyDatasetPointsSpace(pointSpace);

        result.setFunction(dto.getFunctionString());
        result.setaEndpoint(aEndpoint);
        result.setbEndpoint(bEndpoint);
        result.setNumOfPoints(numOfPoints);
        result.setPolynomialDegree(polynomialDegree);
        result.setDatasetPointsSpace(pointSpace);

        return result;
    }


    private void verifyFunction(String function) {
        StringFunctionEvaluator evaluator = StringFunctionEvaluator.forFunction(function);
        try {
            evaluator.count(1.0);
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Function is not correct");
        }
    }

    private void verifyEndpoints(Double aEndpoint, Double bEndpoint) {
        if (aEndpoint > bEndpoint) {
            throw new IllegalArgumentException("a endpoint should be less than or equal to b endpoint");
        }
    }


    private void verifyNumOfPoints(Integer numOfPoints) {
        if (numOfPoints < 1) {
            throw new IllegalArgumentException("Num of points should be greater than or equal to 1");
        }
    }

    private void verifyPolynomialDegree(Integer polynomialDegree) {
        if (polynomialDegree < 1) {
            throw new IllegalArgumentException("Polynomial degree should be greater than or equal to 1");
        }
    }

    private void verifyDatasetPointsSpace(Double pointSpace) {
        if (pointSpace <= 0) {
            throw new IllegalArgumentException("Points space should be  greater than 0");
        }
    }
}

