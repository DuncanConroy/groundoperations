package io.twodigits.demo.groundoperations.taxi

data class TaxiNode(val taxiway: String, val turn: String? = null, var next: TaxiNode? = null)

operator fun TaxiNode.plus(node: TaxiNode): TaxiNode {
    this.next = node
    return node
}