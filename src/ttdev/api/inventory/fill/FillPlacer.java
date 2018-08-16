package ttdev.api.inventory.fill;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ttdev.api.inventory.AInventory;
import ttdev.api.inventory.Item;

public class FillPlacer {

    private ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE);

    public FillPlacer(FillColor fillColor) {
        itemStack.setDurability(fillColor.getShort());
    }

    public void setFill(FillColor fillColor) {
        itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, fillColor.getShort());
    }

    public void placeIntermittently(int start, int spacing, AInventory inventory) {
        int size = inventory.getRows() * 9;
        double times = ((double) (size - start) / spacing);
        times = Math.ceil(times);

        int position = start;
        for(int i = 0; i < times; i++) {
            inventory.setItem(new Item(itemStack), position);
            position += spacing;
        }
    }

}
