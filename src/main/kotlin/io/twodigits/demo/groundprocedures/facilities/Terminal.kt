package io.twodigits.demo.groundprocedures.facilities

import io.twodigits.demo.groundprocedures.thirdparty.Plane
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
data class Terminal(
    val planes: MutableMap<Int, Plane> = mutableMapOf(
        Pair(26, Plane("DLH404")),
        Pair(29, Plane("KLM308")),
        Pair(28, Plane("NAX500"))
    )
) {
    fun getPlane(callsign: String) = planes.values.first { it.callsign == callsign }

    @PostConstruct
    fun initComplete() {
        println(
            """Planes at the Terminal
            #| Stand | Callsign    |
            #|---------------------|
            #${
                planes.map { (stand, plane) ->
                    "| $stand    | ${plane.callsign}      |"
                }.joinToString("\n")
            }
            #|---------------------|
        """.trimMargin("#")
        )
    }
}