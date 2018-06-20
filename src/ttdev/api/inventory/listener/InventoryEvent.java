package ttdev.api.inventory.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ttdev.api.API;
import ttdev.api.inventory.AInventory;
import ttdev.api.inventory.event.InventoryClick;
import ttdev.api.inventory.event.InventoryClickEventInitiater;
import ttdev.api.items.Item;

import java.util.ArrayList;

public class InventoryEvent implements Listener {

	static {
		new InventoryEvent();
	}
	
	public InventoryEvent() {
		Bukkit.getPluginManager().registerEvents(this, API.getInstance());
	}
	
	private static ArrayList<AInventory> inventories = new ArrayList<>();
	
	public static void addInventory(AInventory playerInventory) {
		inventories.add(playerInventory);
	}
	
	public static ArrayList<AInventory> getInventories() {
		return inventories;
	}
	
	@EventHandler
	public static void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		AInventory inv = null;
		
		for (int i=0; i < inventories.size(); i++) {
			if (inventories.get(i).getInventory().equals(e.getInventory())) {
				inv = inventories.get(i);
			}
		}
		
		Item item = new Item(e.getCurrentItem());
		
		InventoryClick event = new InventoryClick(player, inv, item, e.isRightClick(), e.isLeftClick(), e.isShiftClick(), e.getSlot(), e.getClick(), e.getHotbarButton());
		InventoryClickEventInitiater.InventoryClick(event);
	}
	
	@EventHandler
	public static void InventoryClick(InventoryClickEvent e) {
		for (int i=0; i < inventories.size(); i++) {
			if (inventories.get(i).getInventory().equals(e.getInventory())) {
				if (e.getWhoClicked() instanceof Player) {
					Player player = (Player) e.getWhoClicked();
					inventories.get(i).setLastClicker(player);
				}
			}
		}
	}
	
}
