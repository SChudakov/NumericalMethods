package com.sschudakov.interpolation.generator

import com.sschudakov.interpolation.table.InterpolationTable
import spock.lang.Shared
import spock.lang.Specification

class InterpolatingPolynomialGeneratorSpec extends Specification {

    @Shared
    private InterpolatingPolynomialGenerator generator = new InterpolatingPolynomialGenerator()

    def "test generate on table with one point"() {
        given:
        InterpolationTable table = new InterpolationTable()
        table.addColumn(2.0D, 3.0D)

        String expectedPolynomial = new StringBuilder()
                .append("3.0")
                .toString()

        when:
        String generatedPolynomial = this.generator.generate(table)

        then:
        generatedPolynomial == expectedPolynomial
    }

    def "test generate with generate point"() {
        given:
        InterpolationTable table = new InterpolationTable()
        table.addColumn(-2.0D, 3.0D)
        table.addColumn(-1.0D, -1.0D)
        table.addColumn(0.0D, -1.0D)
        table.addColumn(1.0D, 3.0D)
        table.addColumn(2.0D, -13.0D)

        String expectedPolynomial = new StringBuilder()
                .append("3.0*(x+1.0)*(x-0.0)*(x-1.0)*(x-2.0)/((-2.0+1.0)*(-2.0-0.0)*(-2.0-1.0)*(-2.0-2.0))")
                .append("+")
                .append("-1.0*(x+2.0)*(x-0.0)*(x-1.0)*(x-2.0)/((-1.0+2.0)*(-1.0-0.0)*(-1.0-1.0)*(-1.0-2.0))")
                .append("+")
                .append("-1.0*(x+2.0)*(x+1.0)*(x-1.0)*(x-2.0)/((0.0+2.0)*(0.0+1.0)*(0.0-1.0)*(0.0-2.0))")
                .append("+")
                .append("3.0*(x+2.0)*(x+1.0)*(x-0.0)*(x-2.0)/((1.0+2.0)*(1.0+1.0)*(1.0-0.0)*(1.0-2.0))")
                .append("+")
                .append("-13.0*(x+2.0)*(x+1.0)*(x-0.0)*(x-1.0)/((2.0+2.0)*(2.0+1.0)*(2.0-0.0)*(2.0-1.0))")

        when:
        String generatedPolynomial = this.generator.generate(table)
        println "EXPECTED POLYNOMIAL: ${expectedPolynomial}"
        println "GENERATED POLYNOMIAL: ${generatedPolynomial}"
        then:
        generatedPolynomial == expectedPolynomial
    }
}
