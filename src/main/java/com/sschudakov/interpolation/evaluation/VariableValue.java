package com.sschudakov.interpolation.evaluation;

public class VariableValue {
    private String variable;
    private Double value;

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public VariableValue() {
        this(null, null);
    }

    public VariableValue(String variable) {
        this(variable, null);
    }

    public VariableValue(Double value) {
        this(null, value);
    }

    public VariableValue(String variable, Double value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("variable: ").append(this.variable)
                .append("value: ").append(this.value)
                .toString();
    }
}
