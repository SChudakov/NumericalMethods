package com.sschudakov.interpolation.evaluation;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class StringFunctionEvaluator {
    private static final String X_VARIABLE_NAME = "x";
    private static final String Y_VARIABLE_NAME = "y";

    private static VariableValue xVariableValue = new VariableValue(X_VARIABLE_NAME);
    private static VariableValue yVariableValue = new VariableValue(Y_VARIABLE_NAME);

    private static final String VAR = "var";
    private static final String SPACE = " ";
    private static final String EQUAL = "=";
    private static final String SEMI_COLUMN = ";";
    private static final String JAVA_SCRIPT = "JavaScript";

    private static ScriptEngine scriptEngine;

    static {
        ScriptEngineManager manager = new ScriptEngineManager();
        scriptEngine = manager.getEngineByName(JAVA_SCRIPT);
    }

    private String function;


    StringFunctionEvaluator(String function) {
        this.function = function;
    }

    public static StringFunctionEvaluator forFunction(String function) {
        return new StringFunctionEvaluator(function);
    }


    public Double count(Double xValue) throws ScriptException {
        xVariableValue.setValue(xValue);
        return count(xVariableValue);
    }

    public Double count(VariableValue... variableValues) throws ScriptException {
        StringBuilder expression = new StringBuilder();
        appendVariablesDeclaration(expression, variableValues);
        expression.append(this.function);
        String stringExpression = expression.toString();
        /*System.out.println(stringExpression);*/
        return (Double) scriptEngine.eval(stringExpression);
    }

    private void appendVariablesDeclaration(StringBuilder expression, VariableValue... variableValues) {
        for (VariableValue value : variableValues) {
            addVariableDeclaration(expression, value);
        }
    }

    private void addVariableDeclaration(StringBuilder expression, VariableValue variableValue) {
        expression
                .append(VAR).append(SPACE).append(variableValue.getVariable()).append(SPACE)
                .append(EQUAL).append(SPACE).append(variableValue.getValue()).append(SEMI_COLUMN).append(SPACE);
    }
}
