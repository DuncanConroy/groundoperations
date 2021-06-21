package io.twodigits.demo.groundoperations.taxi

//import io.twodigits.demo.groundoperations.extensionfunctions.plus
import io.twodigits.demo.groundoperations.facilities.Terminal
import io.twodigits.demo.groundoperations.thirdparty.Cargo
import io.twodigits.demo.groundoperations.thirdparty.Plane
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/taxi")
class TaxiController(
    val terminal: Terminal,
    val autotaxiService: AutotaxiService
) {

    @GetMapping("/{callsign}/{runway}")
    fun autoTaxi(@PathVariable callsign: String,
                 @PathVariable runway: Int): String {
        val plane = terminal.getPlane(callsign)
        addAutoTaxiRobot(plane)

        val route = getStandardRoute(runway)
        autotaxiService.addPlane(plane, route)

        return "${plane.callsign.toUpperCase()}, taxi: $route"
    }

    @GetMapping("/execute")
    fun execute(): String {
        autotaxiService.runTaxiLoop()
        return "Execute."
    }

    private fun addAutoTaxiRobot(plane: Plane) {
        val robot = Cargo()
       // TODO plane + robot
        println("TaxiRobot 🤖 is missing!")
        println("${plane.callsign.toUpperCase()} - Cargo: ${plane.cargo.size}")
    }

    private fun getStandardRoute(runway: Int) = TaxiNode(
        "W",
        next = TaxiNode(
            "E",
            "right",
            TaxiNode(
                "Y", "left",
                TaxiNode("/$runway", "left")
            )
        )
    )
}