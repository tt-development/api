package ttdev.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ttdev.api.configuration.APIConfiguration;
import ttdev.api.configuration.Status;
import ttdev.api.inventory.events.inventoryclick.InventoryClickEventInitiater;
import ttdev.api.inventory.events.inventoryclick.InventoryListener;
import ttdev.api.inventory.events.inventoryupdate.InventoryUpdateEventInitiater;
import ttdev.api.inventory.events.inventoryupdate.InventoryUpdateListener;
import ttdev.api.manager.Manager;
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
	private static PluginManager pluginManager;
	
	
	@Override
	public void onEnable() {
		singleton = this;
		
		pluginManager = Bukkit.getPluginManager();
	}
	
	@Override
	public void onDisable() {
		
		//Clear the perms.
		Manager.clearPermissions();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static API getInstance() {
		return singleton;
	}
	
	public static PluginManager getPluginManager() {
		return pluginManager;
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
