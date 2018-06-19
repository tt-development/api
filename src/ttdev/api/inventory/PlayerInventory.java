package ttdev.api.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ttdev.api.color.Chat;
import ttdev.api.inventory.listener.InventoryEvent;
import ttdev.api.items.Item;

public class PlayerInventory {

	private String name;
	private int size;
	
	private Player lastClicker;
	
	private Inventory inventory;
	
	/**
	 * 
	 * @param name
	 * @param size
	 */
	public PlayerInventory(String name, int size) {
		this.inventory = Bukkit.createInventory(null, size, Chat.convertColors(name));
		
		InventoryEvent.addInventory(this);
	}
	
	/**
	 * 
	 * @param player
	 */
	public void setLastClicker(Player player) {
		this.lastClicker = player;
	}
	
	/**
	 * 
	 * @return
	 */
	public Player getLastClicker() {
		return lastClicker;
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
