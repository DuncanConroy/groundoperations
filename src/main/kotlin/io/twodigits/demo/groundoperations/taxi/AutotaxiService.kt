package io.twodigits.demo.groundoperations.taxi

import io.twodigits.demo.groundoperations.extensionfunctions.Steering
import io.twodigits.demo.groundoperations.extensionfunctions.steer
import io.twodigits.demo.groundoperations.thirdparty.Plane
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service

@Service
class AutotaxiService {

    val planes = mutableMapOf<Plane, TaxiNode?>()

    fun addPlane(plane: Plane, route: TaxiNode?) {
        planes.putIfAbsent(plane, route)
    }

    fun runTaxiLoop() {
        planes.map { it.toPair() }.forEach {
            GlobalScope.launch {
                taxiPlane(it)
            }
        }
    }

    fun taxiPlane(planeRoute: Pair<Plane, TaxiNode?>) {
        val (plane, route) = planeRoute

        route?.next?.let {

            when (route.turn) {
                "left" -> plane.steer(Steering.LEFT)
                "right" -> plane.steer(Steering.RIGHT)
                else -> plane.steer(Steering.STRAIGHT)
            }

            val taxiTime = (Math.random() * 5000).toLong()
            Thread.sleep(taxiTime)
            println("${plane.callsign.toUpperCase()}($taxiTime), Taxiing on $route")

            taxiPlane(plane to route.next)
        } ?: run {
            println("${plane.callsign.toUpperCase()}, ready for take-off at $route")
        }
    }
}