package ttdev.api.inventory.events.inventoryupdate;

import ttdev.api.inventory.AInventory;

public class InventoryUpdate {

	InventoryUpdateType type;
	AInventory inventory;
	
	public InventoryUpdate(InventoryUpdateType type, AInventory inventory) {
		this.type = type;
		this.inventory = inventory;
	}
	
	public InventoryUpdateType getUpdateType() {
		return this.type;
	}
	
	public AInventory getInventory() {
		return this.inventory;
	}
	
	public void setCanceled(boolean canceled) {
		this.inventory.setCanceled(canceled);
	}
	
}
