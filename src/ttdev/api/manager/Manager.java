package ttdev.api.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;

import ttdev.api.API;

public class Manager {
	
	private static ArrayList<Permission> permissions = new ArrayList<>();

	/**
	 * Register an permission.
	 * @param permission
	 */
	public static void registerPermission(String permission) {
		Permission perm = new Permission(permission);
		API.getPluginManager().addPermission(perm);
		permissions.add(perm);
	}
	
	/**
	 * Remove a permission.
	 * @param permission
	 */
	public static void removePermission(String permission) {
		for (int i=0; i < permissions.size(); i++) {
			if (permissions.get(i).getName().equals(permission)) {
				API.getPluginManager().removePermission(permissions.get(i));
				permissions.remove(i);
			}
		}
	}
	
	/**
	 * Returns all registered permissions.
	 * @return
	 */
	public static ArrayList<Permission> getPermissions() {
		return permissions;
	}
	
	/**
	 * Clear all permissions.
	 */
	public static void clearPermissions() {
		for (int i=0; i < permissions.size(); i++) {
			API.getPluginManager().removePermission(permissions.get(i));
		}
		permissions.clear();
	}
	
	/**
	 * Register an event.
	 * @param listener
	 */
	public static void registerEvent(Listener listener) {
		API.getPluginManager().registerEvents(listener, API.getInstance());
	}
	
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
	
}
