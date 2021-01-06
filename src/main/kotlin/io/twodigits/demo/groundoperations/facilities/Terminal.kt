package io.twodigits.demo.groundoperations.facilities

import io.twodigits.demo.groundoperations.thirdparty.Plane
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
data class Terminal(
    val planes: MutableMap<Int, Plane> = mutableMapOf(
        7 to Plane("npt09h"),  // Neptune zero niner hotel
        26 to Plane("dLh404"), // Lufthansa four zero four
        29 to Plane("Klm308"), // K L M three zero eight
        28 to Plane("sas122"), // Scandinavian one two two
        19 to Plane("wzz543"), // Wizzair five four three
        21 to Plane("auA2Ty"), // Austrian two tango yankee
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