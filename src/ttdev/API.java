package ttdev;

import org.bukkit.plugin.java.JavaPlugin;

import ttdev.color.Chat;

public class API extends JavaPlugin {

	private static API singleton;
	
	public static API getInstance() {
		return singleton;
	}
	
	public void onEnable() {
		singleton = this;
		
	}
	
}
