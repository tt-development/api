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
	 * Used to initialize an inventory.
	 * @param name
	 * @param size
	 */
	public AInventory(String name, int rows) {
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.INVENTORY_CREATE, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		} 
		
		this.canceled = false;
		
		this.inventory = Bukkit.createInventory(null, (rows * 9), ChatColor.translateAlternateColorCodes('&', name));
		
		InventoryEvent.addInventory(this);
	}
	
	/**
	 * Delete the inventory.
	 */
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
	 * Set the last clicker. Not recommended to use.
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
	 * Returns a Player who was the last one to click on the inventory.
	 * @return
	 */
	public Player getLastClicker() {
		return lastClicker;
	}
	
	/**
	 * Returns the Inventory.
	 * @return
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * Returns the inventory name.
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the inventory rows.
	 * @return
	 */
	public int getRows() {
		return (this.size / 9);
	}
	
	/**
	 * Adds an item to the inventory.
	 * @param item
	 */
	public void addItem(Item item) {
		/* Cancel the action */
		InventoryUpdate iu = new InventoryUpdate(InventoryUpdateType.SET_ITEM, this);
		InventoryUpdateEventInitiater.InventoryUpdate(iu);
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
		this.inventory.addItem(item.getItemStack());
	}
	
	/**
	 * Adds an item to the selected slot of the anventory.
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
	 * Removes an item from a slot.
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
	
	/**
	 * Removes all items from the inventory.
	 */
	public void clear() {
		/* Cancel the action */
		if (this.canceled) {
			this.canceled = false;
			return;
		}
		
		this.inventory.clear();
	}
	
	/**
	 * Checks if the inventory contains an item.
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
	 * Opens the inventory for a Player.
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
	 * Returns all the items that are in the inventory.
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
