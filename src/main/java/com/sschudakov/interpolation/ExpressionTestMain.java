package com.sschudakov.interpolation;

import com.sschudakov.interpolation.evaluation.StringFunctionEvaluator;

import javax.script.ScriptException;

public class ExpressionTestMain {
    public static void main(String[] args) {
        StringFunctionEvaluator evaluator = StringFunctionEvaluator.forFunction("sin = Math.sin; exp = Math.exp; x^2 + sin(7*x) + 16*exp(-x^2)");
        try {
            System.out.println(evaluator.count(1.4));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
