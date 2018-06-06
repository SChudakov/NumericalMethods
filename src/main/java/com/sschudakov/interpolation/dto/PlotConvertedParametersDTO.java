package com.sschudakov.interpolation.dto;

public class PlotConvertedParametersDTO {
    private String function;
    private Double aEndpoint;
    private Double bEndpoint;
    private Integer numOfPoints;
    private Integer polynomialDegree;
    private Double datasetPointsSpace;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Double getaEndpoint() {
        return aEndpoint;
    }

    public void setaEndpoint(Double aEndpoint) {
        this.aEndpoint = aEndpoint;
    }

    public Double getbEndpoint() {
        return bEndpoint;
    }

    public void setbEndpoint(Double bEndpoint) {
        this.bEndpoint = bEndpoint;
    }

    public Integer getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(Integer numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public Integer getPolynomialDegree() {
        return polynomialDegree;
    }

    public void setPolynomialDegree(Integer polynomialDegree) {
        this.polynomialDegree = polynomialDegree;
    }

    public Double getDatasetPointsSpace() {
        return datasetPointsSpace;
    }

    public void setDatasetPointsSpace(Double datasetPointsSpace) {
        this.datasetPointsSpace = datasetPointsSpace;
    }
}
