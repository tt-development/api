package ttdev.api.inventory.event;

import java.util.ArrayList;

public class InventoryClickEventInitiater {
	
	private static ArrayList<InventoryListener> listeners = new ArrayList<>();
	
	public static void registerEvent(InventoryListener listener) {
		listeners.add(listener);
	}
	
	public static void InventoryClick(InventoryClick event) {
		for (InventoryListener l : listeners) {
			l.InventoryClickEvent(event);
		}
	}
	
}
