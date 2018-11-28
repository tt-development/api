package ttdev.api.bukkit;

import org.bukkit.plugin.Plugin;
import ttdev.api.API;
import ttdev.api.general.configuration.APIConfiguration;
import ttdev.api.general.configuration.Status;
import ttdev.api.user.inventory.events.inventoryclick.InventoryClickEventInitiater;
import ttdev.api.user.inventory.events.inventoryclick.InventoryListener;
import ttdev.api.user.inventory.events.inventoryupdate.InventoryUpdateEventInitiater;
import ttdev.api.user.inventory.events.inventoryupdate.InventoryUpdateListener;

public class Manager {

	/**
	 * Disable a plugin.
	 * @param pluginName
	 */
	public static void disablePlugin(String pluginName) {
		for (Plugin pl : API.getPluginManager().getPlugins()) {
			if (pl.getName().equalsIgnoreCase(pluginName)) {
				API.getPluginManager().disablePlugin(pl);
				return;
			}
		}
	}
	
	/**
	 * Enable a plugin.
	 * @param pluginName
	 */
	public static void enablePlugin(String pluginName) {
		for (Plugin pl : API.getPluginManager().getPlugins()) {
			if (pl.getName().equalsIgnoreCase(pluginName)) {
				API.getPluginManager().enablePlugin(pl);
				return;
			}
		}
	}
	
	/**
	 * Register a listener
	 * @param listener
	 */
	public static void registerEvent(InventoryListener listener) {
		InventoryClickEventInitiater.registerEvent(listener);
	}
	
	/**
	 * Register a listener
	 * @param listener
	 */
	public static void registerEvent(InventoryUpdateListener listener) {
		InventoryUpdateEventInitiater.registerEvent(listener);
	}
	
	/**
	 * Set InventoryClickStatus.
	 * @param status
	 */
	public static void setInventoryClickStatus(Status status) {
		APIConfiguration.setInventoryClickStatus(status);
	}
	
	/**
	 * set InventoryUpdateStatus
	 * @param status
	 */
	public static void setInventoryUpdateStatus(Status status) {
		APIConfiguration.setInventoryUpdateStatus(status);
	}
	
}
