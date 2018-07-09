package ttdev.api.user.inventory.fill;

import ttdev.api.user.inventory.AInventory;

/**
 *
 * @author Tre Logan
 */
public class UniFill implements Filler {

    private FillColor color;

    public UniFill(FillColor color) {
        this.color = color;
    }

    @Override
    public void fill(AInventory inventory) {

        FillPlacer fillPlacer = new FillPlacer(color);
        fillPlacer.placeIntermittently(0, 1, inventory);

    }

}
