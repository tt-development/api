package ttdev.api.user.inventory.fill;


import ttdev.api.user.inventory.AInventory;

/**
 *
 * @author Tre Logan
 */
public class TriFill implements Filler {

    private FillColor colorOne;
    private FillColor colorTwo;
    private FillColor colorThree;

    public TriFill(FillColor colorOne, FillColor colorTwo, FillColor colorThree) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
        this.colorThree = colorThree;
    }

    @Override
    public void fill(AInventory inventory) {

        FillPlacer fillPlacer = new FillPlacer(colorOne);
        fillPlacer.placeIntermittently(0, 3, inventory);

        fillPlacer.setFill(colorTwo);
        fillPlacer.placeIntermittently(1, 3, inventory);

        fillPlacer.setFill(colorThree);
        fillPlacer.placeIntermittently(2, 3, inventory);

    }

}
