package ttdev.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ttdev.api.inventory.event.InventoryClickEventInitiater;
import ttdev.api.inventory.event.InventoryListener;
import ttdev.api.redstone.event.RedstoneListener;
import ttdev.api.redstone.event.RedstoneTriggerEventInitiater;

public class API extends JavaPlugin {
 
	private static API singleton;
	
	public static API getInstance() {
		return singleton;
	}
	
	public void onEnable() {
		singleton = this;
	}
	
	/**
	 * 
	 * @param listener
	 */
	public static void registerEvent(InventoryListener listener) {
		InventoryClickEventInitiater.registerEvent(listener);
	}
	
	/**
	 * 
	 * @param listener
	 */
	public static void registerEvent(RedstoneListener listener) {
		RedstoneTriggerEventInitiater.registerEvent(listener);
	}
	
	/**
	 * 
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
	 * 
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
	
}
