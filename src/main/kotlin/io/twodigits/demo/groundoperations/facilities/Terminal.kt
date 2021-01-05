package io.twodigits.demo.groundoperations.facilities

import io.twodigits.demo.groundoperations.thirdparty.Plane
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
data class Terminal(
    val planes: MutableMap<Int, Plane> = mutableMapOf(
        Pair(7, Plane("npt09h")),  // Neptune zero niner hotel
        Pair(26, Plane("dLh404")), // Lufthansa four zero four
        Pair(29, Plane("Klm308")), // K L M three zero eight
        Pair(28, Plane("sas122")), // Scandinavian one two two
        Pair(19, Plane("wzz543")), // Wizzair five four three
        Pair(21, Plane("auA2Ty")), // Austrian two tango yankee
    )
) {
    @PostConstruct
    fun initComplete() {
        println(
            """Planes at the Terminal
            #| Stand | Callsign    |
            #|---------------------|
            #${planesToTable(planes)}
            #|---------------------|
        """.trimMargin("#")
        )
    }

    fun getPlane(callsign: String) = planes.values.first { it.callsign.equals(callsign, ignoreCase = true) }

    private fun printLine(stand: Int, plane: Plane) = "| ${stand.toString().padEnd(6)}| ${plane.callsign.padEnd(12)}|"

    private fun planesToTable(planes: Map<Int, Plane>): String {
        return planes
            .map { (a, b) -> printLine(a, b) }
            .joinToString("\n")
    }
}