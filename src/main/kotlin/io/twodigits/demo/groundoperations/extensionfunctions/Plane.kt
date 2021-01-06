package io.twodigits.demo.groundoperations.extensionfunctions

import io.twodigits.demo.groundoperations.thirdparty.Cargo
import io.twodigits.demo.groundoperations.thirdparty.Plane
import io.twodigits.demo.groundoperations.thirdparty.Wheel

fun Plane.getFrontWheel() = this.gear.wheels.first { it.installationPosition == Wheel.InstallationPosition.FRONT }

fun Plane.steerLeft() {
    this.getFrontWheel().rotationAngle = -30.0
    this.engines.forEach { it.thrust = 10.0 }
    println("[${callsign.toUpperCase()}] Set rotationAngle to ${getFrontWheel().rotationAngle}, thrust: ${engines.map { it.thrust }}")
}

fun Plane.steerRight() {
    this.getFrontWheel().rotationAngle = 30.0
    this.engines.forEach { it.thrust = 10.0 }
    println("[${callsign.toUpperCase()}] Set rotationAngle to ${getFrontWheel().rotationAngle}, thrust: ${engines.map { it.thrust }}")
}

fun Plane.steerStraight() {
    this.getFrontWheel().rotationAngle = 0.0
    this.engines.forEach { it.thrust = 20.0 }
    println("[${callsign.toUpperCase()}] Set rotationAngle to ${getFrontWheel().rotationAngle}, thrust: ${engines.map { it.thrust }}")
}

sealed class Steering {
    object LEFT : Steering()
    object RIGHT : Steering()
    object STRAIGHT : Steering()
}

fun Plane.steer(direction: Steering) {
    val (steering, thrust)  = when (direction) {
        Steering.LEFT -> -30.0 to 10.0
        Steering.RIGHT -> 30.0 to 10.0
        Steering.STRAIGHT -> 0.0 to 20.0
    }

    this.getFrontWheel().rotationAngle = steering
    this.engines.forEach { it.thrust = thrust }
    println("[${callsign.toUpperCase()}] Set rotationAngle to ${getFrontWheel().rotationAngle}, thrust: ${engines.map { it.thrust }}")
}

operator fun Plane.plus(cargo: Cargo): Plane {
    this.cargo.add(cargo)
    return this
}