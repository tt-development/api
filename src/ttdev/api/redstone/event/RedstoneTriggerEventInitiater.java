package ttdev.api.redstone.event;

import java.util.ArrayList;

public class RedstoneTriggerEventInitiater {

	private static ArrayList<RedstoneListener> listeners = new ArrayList<>();
	
	public static void registerEvent(RedstoneListener listener) {
		listeners.add(listener);
	}
	
	/*
	 * Call this to trigger an event.
	 */
	public static void redstoneTrigger(RedstoneTriggerEvent event) {
		for (RedstoneListener l : listeners) {
			l.InventoryClickEvent(event);
		}
	}
	
}
