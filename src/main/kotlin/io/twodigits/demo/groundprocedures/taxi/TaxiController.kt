package io.twodigits.demo.groundprocedures.taxi

import io.twodigits.demo.groundprocedures.facilities.Terminal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/taxi")
class TaxiController(
    val terminal: Terminal,
    val autotaxiService: AutotaxiService
) {

    @GetMapping("/{callsign}/{runway}")
    fun getTaxiInstructions(@PathVariable callsign: String, @PathVariable runway: Int) = "E Y /$runway"

    @GetMapping("/{callsign}/{runway}", params = ["autotaxi=true"])
    fun autoTaxi(@PathVariable callsign: String, @PathVariable runway: Int): String {
        val route = getStandardRoute(runway)
        val plane = terminal.getPlane(callsign)
        autotaxiService.addPlane(Pair(plane, route))

        return "OK. Route: $route"
    }

    @GetMapping("/execute")
    fun execute(): String {
        autotaxiService.runTaxiLoop()
        return "Execute."
    }

    private fun getStandardRoute(runway: Int) = TaxiRoute(
        "E",
        next = TaxiRoute(
            "Y",
            "right",
            TaxiRoute("/$runway", "left")
        )
    )
}