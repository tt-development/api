package ttdev.api.inventory.events.inventoryclick;

import ttdev.api.configuration.APIConfiguration;
import ttdev.api.configuration.Status;

import java.util.ArrayList;

public class InventoryClickEventInitiater {
	
	private static ArrayList<InventoryListener> listeners = new ArrayList<>();
	
	public static void registerEvent(InventoryListener listener) {
		listeners.add(listener);
	}
	
	public static void InventoryClick(InventoryClick event) {
		if (APIConfiguration.getInventoryClickStatus().equals(Status.DENIED)) {
			return;
		}
		for (InventoryListener l : listeners) {
			l.InventoryClickEvent(event);
		}
	}
	
}
