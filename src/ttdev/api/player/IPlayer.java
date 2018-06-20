package ttdev.api.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ttdev.api.data.IDataStore;
import ttdev.api.items.Item;

import java.util.List;
import java.util.UUID;

public interface IPlayer extends IDataStore {

    /**
     * Create an instance of this interface using the provided player
     * reference.
     * @param player
     * @return
     */
    static IPlayer createInstance(Player player) {
        return new APlayer(player);
    }

    /**
     * Create an instance of this interface using the provided UUID.
     * @param uuid
     * @return
     */
    static IPlayer createInstance(UUID uuid) {
        return new APlayer(Bukkit.getPlayer(uuid));
    }

    /**
     * Add an item to this players inventory if there's space available.
     * @param itemStack
     */
    void giveItem(ItemStack itemStack);

    /**
     * Add an item to this players inventory if there's space available.
     * @param
     */
    void giveItem(Item item);
    
    /**
     * Remove an item from this players inventory if it exists.
     * @param itemStack
     */
    void removeItem(ItemStack itemStack);

    /**
     * Remove an item from this players inventory if it exists.
     * @param item
     */
    void removeItem(Item item);
    
    /**
     * Will attempt to add a list of items to this players inventory.
     * If there isn't enough space in the inventory to fit all the items, then
     * only some of the item will get added.
     * @param itemStacks
     */
    void giveItems(List<ItemStack> itemStacks);

    /**
     * Will attempt to remove a list of items from this players inventory.
     * If an item in the list doesn't exist in the players inventory then it won't
     * be removed.
     * @param itemStacks
     */
    void removeItems(List<ItemStack> itemStacks);

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
     * Sends a colored formatted message to the player replacing all ampersands
     * with the corresponding color code.
     * @param message
     * @param args
     */
    void sendColoredMessage(String message, Object... args);

    /**
     * Sends a colored message to the player replacing all characters
     * matching <code>colorCode</code> with the corresponding color code.
     * @param colorCode
     * @param message
     */
    void sendColoredMessage(char colorCode, String message);

    /**
     * Sends a colored formatted message to the player replacing all characters
     * matching <code>colorCode</code> with the corresponding color code.
     * @param colorcode
     * @param message
     * @param args
     */
    void sendColoredMessage(char colorcode, String message, Object... args);

}
