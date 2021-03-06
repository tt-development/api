package ttdev.api.user.inventory.events.inventoryupdate;

import ttdev.api.general.configuration.APIConfiguration;
import ttdev.api.general.configuration.Status;

import java.util.ArrayList;

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
