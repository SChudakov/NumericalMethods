package com.sschudakov.eigenvalues.solver;

public class MathOperationsExecutor {

    private Integer numOfAdditions;
    private Integer numOfSubtractions;
    private Integer numOfMultiplications;
    private Integer numOfDivisions;

    public MathOperationsExecutor() {
        this.numOfAdditions = 0;
        this.numOfSubtractions = 0;
        this.numOfMultiplications = 0;
        this.numOfDivisions = 0;
    }

    public Integer getNumOfAdditions() {
        return numOfAdditions;
    }

    public Integer getNumOfSubtractions() {
        return numOfSubtractions;
    }

    public Integer getNumOfMultiplications() {
        return numOfMultiplications;
    }

    public Integer getNumOfDivisions() {
        return numOfDivisions;
    }

    public void increaseNumOFAdditions(Integer value) {
        numOfAdditions += value;
    }

    public void increaseNumOFSubtractions(Integer value) {
        numOfSubtractions += value;
    }

    public void increaseNumOFMultiplications(Integer value) {
        numOfMultiplications += value;
    }

    public void increaseNumOFDivisions(Integer value) {
        numOfDivisions += value;
    }

    public Double add(Double x, Double y) {
        numOfAdditions++;
        return x + y;
    }

    public Double subtract(Double x, Double y) {
        numOfSubtractions++;
        return x - y;
    }

    public Double multiply(Double x, Double y) {
        numOfMultiplications++;
        return x * y;
    }

    public Double divide(Double x, Double y) {
        numOfDivisions++;
        return x / y;
    }
}
