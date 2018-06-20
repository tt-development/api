package ttdev.api.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ttdev.api.data.DataStore;

/**
 * An extensible class that provides shorthands for
 * commonly used player functions. Extend this class to add plugin
 * specific player functions.
 */
class APlayer extends DataStore implements IPlayer {

    private Player player;

    APlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the player reference associated with this wrapper.
     * @return
     */
    public Player player() {
        return player;
    }

    @Override
    public ItemStack setInventorySlot(int slot, ItemStack item) {
        ItemStack currentItem = getInventorySlot(slot);
        player.getInventory().setItem(slot, item);
        return currentItem;
    }

    @Override
    public ItemStack getInventorySlot(int slot) {
        return player.getInventory().getItem(slot);
    }

    @Override
    public void sendColoredMessage(String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public void sendColoredMessage(char colorCode, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes(colorCode, message));
    }

}
