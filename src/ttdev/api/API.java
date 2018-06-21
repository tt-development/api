package ttdev.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ttdev.api.configuration.APIConfiguration;
import ttdev.api.configuration.Status;
import ttdev.api.inventory.events.inventoryclick.InventoryClickEventInitiater;
import ttdev.api.inventory.events.inventoryclick.InventoryListener;
import ttdev.api.inventory.events.inventoryupdate.InventoryUpdateEventInitiater;
import ttdev.api.inventory.events.inventoryupdate.InventoryUpdateListener;
import ttdev.api.redstone.event.RedstoneListener;
import ttdev.api.redstone.event.RedstoneTriggerEventInitiater;

/**
 * Spigot API for minecraft 1.8 - 1.12. Developed by T&T development.
 * See README.md to get all features of this API.
 * 
 * @version 1.5
 * @category API
 * @see README.md
 * @since 13-6-2018
 * @author Tre & Thomas
 */

public class API extends JavaPlugin {
 
	private static API singleton;
	
	
	@Override
	public void onEnable() {
		singleton = this;
	}
	
	@Override
	public void onDisable() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static API getInstance() {
		return singleton;
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
	public static void registerEvent(RedstoneListener listener) {
		RedstoneTriggerEventInitiater.registerEvent(listener);
	}
	
	/**
	 * Register a listener
	 * @param listener
	 */
	public static void registerEvent(InventoryUpdateListener listener) {
		InventoryUpdateEventInitiater.registerEvent(listener);
	}
	
	/**
	 * Disable a plugin.
	 * @param pluginName
	 */
	public static void disablePlugin(String pluginName) {
		for (Plugin pl : Bukkit.getPluginManager().getPlugins()) {
			if (pl.getName().equalsIgnoreCase(pluginName)) {
				Bukkit.getPluginManager().disablePlugin(pl);
				return;
			}
		}
	}
	
	/**
	 * Enable a plugin.
	 * @param pluginName
	 */
	public static void enablePlugin(String pluginName) {
		for (Plugin pl : Bukkit.getPluginManager().getPlugins()) {
			if (pl.getName().equalsIgnoreCase(pluginName)) {
				Bukkit.getPluginManager().enablePlugin(pl);
				return;
			}
		}
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
