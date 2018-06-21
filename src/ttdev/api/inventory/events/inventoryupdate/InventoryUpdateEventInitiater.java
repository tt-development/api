package ttdev.api.inventory.events.inventoryupdate;

import java.util.ArrayList;

import ttdev.api.configuration.APIConfiguration;
import ttdev.api.configuration.Status;

public class InventoryUpdateEventInitiater {

	private static ArrayList<InventoryUpdateListener> listeners = new ArrayList<>();
	
	public static void registerEvent(InventoryUpdateListener listener) {
		listeners.add(listener);
	}
	
	public static void InventoryUpdate(InventoryUpdate event) {
		if (APIConfiguration.getInventoryUpdateStatus().equals(Status.DENIED)) {
			return;
		}
		for (InventoryUpdateListener l : listeners) {
			l.InventoryUpdateEvent(event);
		}
	}
	
}
