package ttdev.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ttdev.color.Chat;
import ttdev.items.Item;

public class PlayerInventory {

	private String name;
	private int size;
	
	private Inventory inventory;
	
	/**
	 * 
	 * @param name
	 * @param size
	 */
	public PlayerInventory(String name, int size) {
		this.inventory = Bukkit.createInventory(null, size, Chat.convertColors(name));
	}
	
	/**
	 * 
	 * @return
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * 
	 * @param item
	 * @param slot
	 */
	public void setItem(Item item, int slot) {
		this.inventory.setItem(slot, item.getItemStack());
	}
	
	/**
	 * 
	 * @param slot
	 */
	public void removeItem(int slot) {
		this.inventory.setItem(slot, null);
	}
	
	public void clear() {
		this.inventory.clear();
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean contains(Item item) {
		if (this.inventory.contains(item.getItemStack())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param player
	 */
	public void openInventory(Player player) {
		player.openInventory(this.inventory);
	}

	/**
	 * 	
	 * @return
	 */
	public ItemStack[] getItems() {
		return this.inventory.getContents();
	}
	
}
