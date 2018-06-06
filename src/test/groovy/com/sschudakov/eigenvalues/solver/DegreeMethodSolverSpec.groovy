package com.sschudakov.eigenvalues.solver

import spock.lang.Shared
import spock.lang.Specification

class DegreeMethodSolverSpec extends Specification {

    @Shared
    private DegreeMethodSolver solver

    def setup() {
        this.solver = new DegreeMethodSolver()
    }

    def "test solve"() {
        given:
        double[][] matrix = [
                [1.0D, 1 / 2, 1 / 3],
                [1 / 2, 1 / 3, 1 / 4],
                [1 / 3, 1 / 4, 1 / 5]
        ]

        double precision = 0.0000001
        when:
        double eigenvalue = this.solver.solve(matrix, precision)
        then:
        notThrown(Exception)
        println "eigen value: ${eigenvalue}"
    }
}
