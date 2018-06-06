package com.sschudakov.interpolation.evaluation

import spock.lang.Specification

class StringFunctionEvaluatorSpec extends Specification {
    def "test count"() {
        given:
        String polynomial = new StringBuilder()
                .append("3.0*(x+1.0)*(x-0.0)*(x-1.0)*(x-2.0)/((-2.0+1.0)*(-2.0-0.0)*(-2.0-1.0)*(-2.0-2.0))")
                .append("+")
                .append("-1.0*(x+2.0)*(x-0.0)*(x-1.0)*(x-2.0)/((-1.0+2.0)*(-1.0-0.0)*(-1.0-1.0)*(-1.0-2.0))")
                .append("+")
                .append("-1.0*(x+2.0)*(x+1.0)*(x-1.0)*(x-2.0)/((0.0+2.0)*(0.0+1.0)*(0.0-1.0)*(0.0-2.0))")
                .append("+")
                .append("3.0*(x+2.0)*(x+1.0)*(x-0.0)*(x-2.0)/((1.0+2.0)*(1.0+1.0)*(1.0-0.0)*(1.0-2.0))")
                .append("+")
                .append("-13.0*(x+2.0)*(x+1.0)*(x-0.0)*(x-1.0)/((2.0+2.0)*(2.0+1.0)*(2.0-0.0)*(2.0-1.0))")

        StringFunctionEvaluator evaluator = new StringFunctionEvaluator(polynomial)
        //0.07031
        //0.15625
        //-0.703125
        //1.40625
        //0.5078125
        when:
        Double value = evaluator.count(new VariableValue("x", 0.5D))
        then:
        value == 1.4375
    }
}
