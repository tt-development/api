package ttdev.api.gui.fill;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ttdev.api.gui.Menu;
import ttdev.api.gui.MenuItem;

public class FillPlacer {

    private ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE);

    public FillPlacer(FillColor fillColor) {
        itemStack.setDurability(fillColor.getShort());
    }

    public void setFill(FillColor fillColor) {
        itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, fillColor.getShort());
    }

    public void placeIntermittently(int start, int spacing, Menu menu) {
        int size = menu.getSlots();
        double times = ((double) (size - start) / spacing);
        times = Math.ceil(times);

        int position = start;
        for(int i = 0; i < times; i++) {
            menu.addItem(position, new MenuItem("", itemStack));
            position += spacing;
        }
    }

}
