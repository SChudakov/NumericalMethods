package com.sschudakov.interpolation.dto;

public class PlotStringParametersDTO {
    private String functionString;
    private String aEndpointString;
    private String bEndpointString;
    private String numOfPointsString;
    private String polynomialDegreeString;
    private String datasetPointsSpaceString;

    public String getFunctionString() {
        return functionString;
    }

    public void setFunctionString(String functionString) {
        this.functionString = functionString;
    }

    public String getaEndpointString() {
        return aEndpointString;
    }

    public void setaEndpointString(String aEndpointString) {
        this.aEndpointString = aEndpointString;
    }

    public String getbEndpointString() {
        return bEndpointString;
    }

    public void setbEndpointString(String bEndpointString) {
        this.bEndpointString = bEndpointString;
    }

    public String getNumOfPointsString() {
        return numOfPointsString;
    }

    public void setNumOfPointsString(String numOfPointsString) {
        this.numOfPointsString = numOfPointsString;
    }

    public String getPolynomialDegreeString() {
        return polynomialDegreeString;
    }

    public void setPolynomialDegreeString(String polynomialDegreeString) {
        this.polynomialDegreeString = polynomialDegreeString;
    }

    public String getDatasetPointsSpaceString() {
        return datasetPointsSpaceString;
    }

    public void setDatasetPointsSpaceString(String datasetPointsSpaceString) {
        this.datasetPointsSpaceString = datasetPointsSpaceString;
    }
}
