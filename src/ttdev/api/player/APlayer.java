package ttdev.api.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class APlayer implements IPlayer {

    private Player player;

    private APlayer(Player player) {
        this.player = player;
    }

    /**
     * Create an instance of this wrapper using the provided player
     * reference.
     * @param player
     * @return
     */
    public static IPlayer getInstance(Player player) {
        return new APlayer(player);
    }

    /**
     * Create an instance of this wrapper using the provided UUID.
     * @param uuid
     * @return
     */
    public static IPlayer getInstance(UUID uuid) {
        return new APlayer(Bukkit.getPlayer(uuid));
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
