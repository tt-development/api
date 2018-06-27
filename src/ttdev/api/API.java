package ttdev.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ttdev.api.bukkit.Manager;

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
	
}
