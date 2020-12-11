package io.twodigits.demo.groundprocedures.extensionfunctions

import io.twodigits.demo.groundprocedures.thirdparty.Cargo
import io.twodigits.demo.groundprocedures.thirdparty.Plane
import io.twodigits.demo.groundprocedures.thirdparty.Wheel

fun Plane.getFrontWheel() = this.gear.wheels.first { it.installationPosition == Wheel.InstallationPosition.FRONT }

fun Plane.steerLeft() {
    this.getFrontWheel()?.rotationAngle = -90.0
    this.engines.forEach { it.thrust = 10.0 }
    println("[${this.callsign}] Set rotationAngle to ${this.getFrontWheel()?.rotationAngle}, thrust: ${this.engines.map { it.thrust }}")
}

fun Plane.steerRight() {
    this.getFrontWheel()?.rotationAngle = 90.0
    this.engines.forEach { it.thrust = 10.0 }
    println("[${this.callsign}] Set rotationAngle to ${this.getFrontWheel()?.rotationAngle}, thrust: ${this.engines.map { it.thrust }}")
}

fun Plane.steerStraight() {
    this.getFrontWheel()?.rotationAngle = 0.0
    this.engines.forEach { it.thrust = 20.0 }
    println("[${this.callsign}] Set rotationAngle to ${this.getFrontWheel()?.rotationAngle}, thrust: ${this.engines.map { it.thrust }}")
}

operator fun Plane.plus(cargo: Cargo): Plane {
    this.cargo.add(cargo)
    return this
}