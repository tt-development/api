package ttdev.api.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ttdev.api.inventory.events.inventoryupdate.InventoryUpdate;
import ttdev.api.inventory.events.inventoryupdate.InventoryUpdateEventInitiater;
import ttdev.api.inventory.events.inventoryupdate.InventoryUpdateType;
import ttdev.api.inventory.listener.InventoryEvent;
import ttdev.api.items.Item;

public class AInventory {

	private String name;
	private int size;
	
	private Player lastClicker;
	
	private Inventory inventory;
	
	private boolean canceled;
	
	/**
	 * 
	 * @param name
	 * @param size
	 */
	public AInventory(String name, int size) {
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.INVENTORY_CREATE, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
		this.canceled = false;
		
		this.inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', name));
		
		InventoryEvent.addInventory(this);
	}
	
	public void delete() {
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.INVENTORY_DELETE, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		this.inventory.clear();
		this.inventory = null;
		InventoryEvent.removeInventory(this);
	}
	
	/**
	 * 
	 * @param player
	 */
	public void setLastClicker(Player player) {
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.UPDATE_LAST_CLICKER, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
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
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.SET_ITEM, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
		this.inventory.setItem(slot, item.getItemStack());
	}
	
	/**
	 * 
	 * @param slot
	 */
	public void removeItem(int slot) {
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.REMOVE_ITEM, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
		this.inventory.setItem(slot, null);
	}
	
	public void clear() {
		/* Cancel the action */
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
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
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.OPEN_INVENTORY, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
		player.openInventory(this.inventory);
	}

	/**
	 * 	
	 * @return
	 */
	public ItemStack[] getItems() {
		return this.inventory.getContents();
	}
	
	/**
	 * Cancels the next action.
	 * @param canceled
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
}
