package ttdev.items;


import java.util.List;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

	private ItemStack itemStack;
	private ItemMeta itemMeta;
	
	public Item(ItemStack itemStack) {
		this.itemStack = itemStack;
		this.itemMeta = this.itemStack.getItemMeta();
	}
	
	public ItemStack getItemStack() {
		this.itemStack.setItemMeta(itemMeta);
		return this.itemStack;
	}
	
	
	public void addEnchant(Enchantment enchantment, int level) {
		this.itemMeta.addEnchant(enchantment, level, false);
	}
	
	public void removeEnchant(Enchantment enchantment) {
		this.itemMeta.removeEnchant(enchantment);
	}
	
	public void setName(String name) {
		this.itemMeta.setDisplayName(name);
	}
	
	public void setLore(List<String> lore) {
		this.itemMeta.setLore(lore);
	}
	
	public String getName() {
		return this.itemMeta.getDisplayName();
	}
	
	public Map<Enchantment, Integer> getEnchants() {
		return this.itemMeta.getEnchants();
	}
	
	public List<String> getLore() {
		return this.itemMeta.getLore();
	}
	
}
