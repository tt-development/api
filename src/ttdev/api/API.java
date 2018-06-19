package ttdev.api;

import org.bukkit.plugin.java.JavaPlugin;

import ttdev.api.inventory.event.InventoryClickEventInitiater;
import ttdev.api.inventory.event.InventoryListener;

public class API extends JavaPlugin {
 
	private static API singleton;
	
	public static API getInstance() {
		return singleton;
	}
	
	public void onEnable() {
		singleton = this;
		
	}
	
	public static void registerEvent(InventoryListener listener) {
		InventoryClickEventInitiater.registerEvent(listener);
	}
	
}
