package ttdev.api.items;


import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ttdev.api.color.Chat;

public class Item {

	private ItemStack itemStack;
	private ItemMeta itemMeta;
	
	/**
	 * 
	 * @param ItemStack
	 */
	public Item(ItemStack itemStack) {
		this.itemStack = itemStack;
		this.itemMeta = this.itemStack.getItemMeta();
	}
	
	/**
	 * 
	 * @param material
	 */
	public Item(Material material) {
		this.itemStack = new ItemStack(material);
	}
	
	/**
	 * 
	 * @return
	 */
	public ItemStack getItemStack() {
		this.itemStack.setItemMeta(itemMeta);
		return this.itemStack;
	}
	
	/**
	 * 
	 * @return
	 */
	public Material getMaterial() {
		return this.itemStack.getType();
	}
	
	/**
	 * 
	 * @param Enchantment that will be added to the item.
	 * @param Level of the enchantment.
	 */
	public void addEnchant(Enchantment enchantment, int level) {
		this.itemMeta.addEnchant(enchantment, level, false);
	}
	
	/**
	 * 
	 * @param Remove an enchantment from an item.
	 */
	public void removeEnchant(Enchantment enchantment) {
		this.itemMeta.removeEnchant(enchantment);
	}
	
	/**
	 * 
	 * @param Set the displayname of an item.
	 */
	public void setName(String name) {
		this.itemMeta.setDisplayName(Chat.convertColors(name));
	}
	
	/**
	 * 
	 * @param lore
	 */
	public void addLore(String lore) {
		List<String> lores = this.itemMeta.getLore();
		lores.add(Chat.convertColors(lore));
		this.itemMeta.setLore(lores);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.itemMeta.getDisplayName();
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<Enchantment, Integer> getEnchants() {
		return this.itemMeta.getEnchants();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getLore() {
		return this.itemMeta.getLore();
	}
	
}
