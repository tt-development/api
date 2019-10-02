package ttdev.api.user.inventory;

import kotlin.Pair;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ttdev.api.user.inventory.events.inventoryupdate.InventoryUpdate;
import ttdev.api.user.inventory.events.inventoryupdate.InventoryUpdateEventInitiater;
import ttdev.api.user.inventory.events.inventoryupdate.InventoryUpdateType;
import ttdev.api.user.inventory.listener.InventoryEvent;
import ttdev.api.user.items.Item;

import java.util.ArrayList;


public class AInventory {

	private String name;
	private int size;
	
	private Player lastClicker;
	
	private Inventory inventory;
	
	private boolean canceled;
	private boolean cancelClick;
	
	private ArrayList<Pair<Item, Integer>> items = new ArrayList<>();
	
	/**
	 * Used to initialize an inventory.
	 * @param name
	 * @param rows
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
		this.cancelClick = false;
		this.name = name;
		
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
		
		this.items.add(new Pair<>(item, null));
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

		items.add(new Pair<>(item, slot));
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
		
		for (int i=0; i < this.items.size(); i++) {
			Pair<Item, Integer> tmp = this.items.get(i);
			if (tmp.getSecond() == slot) {
				this.items.remove(i);
				break;
			}
		}
		this.inventory.setItem(slot, null);
	}
	
	public Item getItem(int slot) {
		for (int i=0; i < this.items.size(); i++) {
			Pair<Item, Integer> tmp = this.items.get(i);
			if (tmp.getSecond() == slot) {
				return tmp.getFirst();
			}
		}
		return null;
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
		
		this.items.clear();
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
	public ItemStack[] getItemStacks() {
		return this.inventory.getContents();
	}
	
	/**
	 * Returns all the items that are in the inventory.
	 * @return
	 */
	public ArrayList<Pair<Item, Integer>> getItems() {
		return this.items;
	}
	
	/**
	 * Compare the inventory name to a string.
	 * @param name
	 * @return
	 */
	public boolean compareNameTo(String name) {
		if (this.getName().equals(ChatColor.translateAlternateColorCodes('&', name))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Cancels the next action.
	 * @param canceled
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	public void cancelClick() {
		this.cancelClick = true;
	}
	
	public boolean isCanceled() {
		return this.cancelClick;
	}
	
}
