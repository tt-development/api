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

enum class FillColor(val id: Short) {

    WHITE(0),
    ORANGE(1),
    MAGENTA(2),
    LIGHT_BLUE(3),
    YELLOW(4),
    LIME(5),
    PINK(6),
    GRAY(7),
    LIGHT_GRAY(8),
    CYAN(9),
    PURPLE(10),
    BLUE(11),
    BROWN(12),
    GREEN(13),
    RED(14),
    BLACK(15);

    fun getFillColor(id: Short): FillColor? {
        return values().find { id == it.id }
    }

}