package ttdev.api.player;

import org.bukkit.inventory.ItemStack;

public interface IPlayer {

    /**
     * Sets the item in <code>slot</code> to <code>item</code>
     * and returns the previous item in that slot if there was one.
     * @param slot
     * @param item
     * @return
     */
    ItemStack setInventorySlot(int slot, ItemStack item);

    /**
     * Gets the item in that slot.
     * @param slot
     * @return
     */
    ItemStack getInventorySlot(int slot);

    /**
     * Sends a colored message to the player replacing all ampersands
     * with the corresponding color code.
     * @param message
     */
    void sendColoredMessage(String message);

    /**
     * Sends a colored message to the player replacing all characters
     * matching <code>colorCode</code> with the corresponding color code.
     * @param colorCode
     * @param message
     */
    void sendColoredMessage(char colorCode, String message);

}
