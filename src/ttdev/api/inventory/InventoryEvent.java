package ttdev.api.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ttdev.api.API;

public class InventoryEvent implements Listener {

	static {
		new InventoryEvent();
	}
	
	public InventoryEvent() {
		Bukkit.getPluginManager().registerEvents(this, API.getInstance());
	}
	
	private static ArrayList<PlayerInventory> inventories = new ArrayList<>();
	
	public static void addInventory(PlayerInventory playerInventory) {
		inventories.add(playerInventory);
	}
	
	public static ArrayList<PlayerInventory> getInventories() {
		return inventories;
	}
	
	@EventHandler
	public static void InventoryClick(InventoryClickEvent e) {
		for (int i=0; i < inventories.size(); i++) {
			if (inventories.get(i).getInventory().equals(e.getClickedInventory())) {
				if (e.getWhoClicked() instanceof Player) {
					Player player = (Player) e.getWhoClicked();
					inventories.get(i).setLastClicker(player);
				}
			}
		}
	}
	
}
