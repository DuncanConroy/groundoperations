package io.twodigits.demo.groundprocedures.taxi

import io.twodigits.demo.groundprocedures.extensionfunctions.steerLeft
import io.twodigits.demo.groundprocedures.extensionfunctions.steerRight
import io.twodigits.demo.groundprocedures.extensionfunctions.steerStraight
import io.twodigits.demo.groundprocedures.facilities.Terminal
import io.twodigits.demo.groundprocedures.thirdparty.Plane
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class AutotaxiService(val terminal: Terminal) {

    val planes = mutableMapOf<Plane, TaxiRoute?>()

    @Scheduled(cron = "0 30/* * * * *")
    fun scheduled() {
        runTaxiLoop()
    }

    fun runTaxiLoop() {
        planes.map { it.toPair() }.forEach {
            GlobalScope.launch {
                taxiPlane(it)
            }
        }
    }

    fun addPlane(planeRoute: Pair<Plane, TaxiRoute?>) {
        planes.putIfAbsent(planeRoute.first, planeRoute.second)
    }

    fun taxiPlane(planeRoute: Pair<Plane, TaxiRoute?>) {
        val plane = planeRoute.first
        val route = planeRoute.second

        if (route?.next === null) return

        when (route.turn) {
            "left" -> plane.steerLeft()
            "right" -> plane.steerRight()
            else -> plane.steerStraight()
        }
        Thread.sleep(1500)

        taxiPlane(Pair(plane, route.next))
    }
}