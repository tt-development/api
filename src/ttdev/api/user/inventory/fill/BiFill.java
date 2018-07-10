package ttdev.api.user.inventory.fill;

import ttdev.api.user.inventory.AInventory;

/**
 *
 * @author Tre Logan
 */
public class BiFill implements Filler {

    private FillColor colorOne;
    private FillColor colorTwo;

    public BiFill(FillColor colorOne, FillColor colorTwo) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
    }

    @Override
    public void fill(AInventory inventory) {

        FillPlacer fillPlacer = new FillPlacer(colorOne);
        fillPlacer.placeIntermittently(0, 2, inventory);

        fillPlacer.setFill(colorTwo);
        fillPlacer.placeIntermittently(1, 2, inventory);

    }

}
