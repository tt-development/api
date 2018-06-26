package ttdev.api.user.inventory.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ttdev.api.API;
import ttdev.api.user.inventory.AInventory;
import ttdev.api.user.inventory.InventoryCommand;
import ttdev.api.user.items.Item;
import ttdev.api.user.inventory.events.inventoryclick.InventoryClick;
import ttdev.api.user.inventory.events.inventoryclick.InventoryClickEventInitiater;

public class InventoryEvent implements Listener {

	static {
		new InventoryEvent();
	}
	
	public InventoryEvent() {
		Bukkit.getPluginManager().registerEvents(this, API.getInstance());
	}
	
	private static ArrayList<AInventory> inventories = new ArrayList<>();
	
	public static void addInventory(AInventory inventory) {
		inventories.add(inventory);
	}
	
	public static void removeInventory(AInventory inventory) {
		inventories.remove(inventory);
	}
	
	public static ArrayList<AInventory> getInventories() {
		return inventories;
	}
	
	public static void runCommand(InventoryCommand command, String inventoryName, Item item, int slot) {
		for (AInventory inv : inventories) {
			if (inv.getName().equals(inventoryName)) {
				switch (command) {
				case DELETE:
					inv.delete();
					return;
				case CLEAR:
					inv.clear();
					return;
				case SET_ITEM:
					inv.setItem(item, slot);
				case REMOVE_ITEM:
					inv.removeItem(slot);
				}
			}
		}
	}
	
	public static void runCommand(InventoryCommand command, int rows, Item item, int slot) {
		for (AInventory inv : inventories) {
			if (inv.getRows() == rows) {
				switch (command) {
				case DELETE:
					inv.delete();
					return;
				case CLEAR:
					inv.clear();
					return;
				case SET_ITEM:
					inv.setItem(item, slot);
				case REMOVE_ITEM:
					inv.removeItem(slot);
				}
			}
		}
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
