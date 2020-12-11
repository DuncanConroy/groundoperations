package io.twodigits.demo.groundprocedures.taxi

data class TaxiRoute(val taxiway: String, val turn: String? = null, val next: TaxiRoute? = null)
