package ttdev.api.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ttdev.api.data.DataStore;
import ttdev.api.items.Item;

import java.util.List;

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
    public void giveItem(ItemStack itemStack) {
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void removeItem(ItemStack itemStack) {
        player.getInventory().removeItem(itemStack);
    }

    @Override
    public void giveItems(List<ItemStack> itemStacks) {
        ItemStack[] itemArray = new ItemStack[itemStacks.size()];
        itemStacks.toArray(itemArray);
        player.getInventory().addItem(itemArray);
    }

    @Override
    public void removeItems(List<ItemStack> itemStacks) {
        ItemStack[] itemArray = new ItemStack[itemStacks.size()];
        itemStacks.toArray(itemArray);
        player.getInventory().removeItem(itemArray);
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
    public void sendColoredMessage(String message, Object... args) {
        String formattedMessage = String.format(message, args);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', formattedMessage));
    }

    @Override
    public void sendColoredMessage(char colorCode, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes(colorCode, message));
    }

    @Override
    public void sendColoredMessage(char colorcode, String message, Object... args) {
        String formattedMessage = String.format(message, args);
        player.sendMessage(ChatColor.translateAlternateColorCodes(colorcode, formattedMessage));
    }

	@Override
	public void giveItem(Item item) {
		player.getInventory().addItem(item.getItemStack());
		
	}

	@Override
	public void removeItem(Item item) {
		player.getInventory().remove(item.getItemStack());
	}
	
	public boolean hasScoreboard() {
		if (player.getScoreboard() == null) {
			return false;
		} else {
			return true;
		}
	}

}
