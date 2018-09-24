package ttdev.api.user.inventory.fill

import ttdev.api.user.inventory.AInventory

data class Fill(val one: FillColor?, val two: FillColor?, val three: FillColor?, val four: FillColor?) {

    var start = 0
    var spacing = 1

    fun fill(inventory: AInventory) {
        val placer = FillPlacer(one ?: return)
        placer.placeIntermittently(start, spacing, inventory)

        placer.setFill(two ?: return)
        placer.placeIntermittently(start + 1, spacing, inventory)

        placer.setFill(three ?: return)
        placer.placeIntermittently(start + 2, spacing, inventory)

        placer.setFill(four ?: return)
        placer.placeIntermittently(start + 3, spacing, inventory)
    }

}