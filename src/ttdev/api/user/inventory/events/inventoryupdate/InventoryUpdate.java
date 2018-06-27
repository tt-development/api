package ttdev.api.user.inventory.events.inventoryupdate;

import ttdev.api.user.inventory.AInventory;

public class InventoryUpdate {

	InventoryUpdateType type;
	AInventory inventory;
	
	/**
	 * 
	 * @param type
	 * @param inventory
	 */
	public InventoryUpdate(InventoryUpdateType type, AInventory inventory) {
		this.type = type;
		this.inventory = inventory;
	}
	
	/**
	 * 
	 * @return
	 */
	public InventoryUpdateType getUpdateType() {
		return this.type;
	}
	
	/**
	 * 
	 * @return
	 */
	public AInventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * 
	 * @param canceled
	 */
	public void setCanceled(boolean canceled) {
		this.inventory.setCanceled(canceled);
	}
	
}
