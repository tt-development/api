package ttdev.api.gui.fill;

import ttdev.api.gui.Menu;

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
    public void fill(Menu menu) {

        FillPlacer fillPlacer = new FillPlacer(color);
        fillPlacer.placeIntermittently(0, 1, menu);

    }

}
