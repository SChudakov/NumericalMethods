package com.sschudakov.interpolation.generator

import com.sschudakov.interpolation.task.Task
import spock.lang.Shared
import spock.lang.Specification

class EquallySpacedPointsITGeneratorSpec extends Specification {

    @Shared
    private EquallySpacedPointsITGenerator equallySpacedPointsITGenerator = new EquallySpacedPointsITGenerator()

    def "test generate"() {
        when:
        def table = equallySpacedPointsITGenerator.generate(
                Task.FUNCTION,
                Task.A_END_POINT,
                Task.B_END_POINT,
                Task.NUM_OF_POINTS
        )
        println "table;\n ${table.toString()}"
        then:
        notThrown(Exception)

    }
}
