package ttdev.api.inventory.events.inventoryupdate;

import java.util.ArrayList;

public class InventoryUpdateEventInitiater {

	private static ArrayList<InventoryUpdateListener> listeners = new ArrayList<>();
	
	public static void registerEvent(InventoryUpdateListener listener) {
		listeners.add(listener);
	}
	
	public static void InventoryUpdate(InventoryUpdate event) {
		for (InventoryUpdateListener l : listeners) {
			l.InventoryUpdateEvent(event);
		}
	}
	
}
