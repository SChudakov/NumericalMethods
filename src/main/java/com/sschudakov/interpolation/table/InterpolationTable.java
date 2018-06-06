package com.sschudakov.interpolation.table;

import java.util.ArrayList;
import java.util.List;

public class InterpolationTable {
    private List<Double> points;
    private List<Double> functionValues;

    public List<Double> getPoints() {
        return points;
    }

    public List<Double> getFunctionValues() {
        return functionValues;
    }


    public InterpolationTable() {
        this.points = new ArrayList<>();
        this.functionValues = new ArrayList<>();
    }


    public void addColumn(Double point, Double functionValue) {
        this.points.add(point);
        this.functionValues.add(functionValue);
    }


    public int size() {
        return this.points.size();
    }

    public Double getPoint(int position) {
        return this.points.get(position);
    }

    public Double getValue(int position) {
        return this.functionValues.get(position);
    }


    @Override
    public String toString() {
        return new StringBuilder()
                .append("points: ")
                .append(this.points)
                .append("\n")
                .append("values")
                .append(this.functionValues)
                .toString();
    }
}
