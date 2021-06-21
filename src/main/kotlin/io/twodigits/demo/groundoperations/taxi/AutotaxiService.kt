package io.twodigits.demo.groundoperations.taxi

import io.twodigits.demo.groundoperations.thirdparty.Plane
import org.springframework.stereotype.Service

@Service
class AutotaxiService {

    val planes = mutableMapOf<Plane, TaxiNode?>()

    fun addPlane(plane: Plane, route: TaxiNode?) {
        planes.putIfAbsent(plane, route)
    }

    fun runTaxiLoop() {
        planes.map { it.toPair() }.forEach {
            taxiPlane(it)
        }
    }

    fun taxiPlane(planeRoute: Pair<Plane, TaxiNode?>) {
        val (plane, route) = planeRoute

        if (route?.next === null) return

//        when (route.turn) {
//            "left" -> TODO()
//            "right" -> TODO()
//            else -> TODO()
//        }

        val taxiTime = (Math.random() * 5000).toLong()
        Thread.sleep(taxiTime)
        println("${plane.callsign.toUpperCase()}, Taxiing on ${route.taxiway}, for ${taxiTime}ms")

        taxiPlane(Pair(plane, route.next))

        if(route.next === null) println("${plane.callsign.toUpperCase()}, ready for take-off at $route")
    }
}