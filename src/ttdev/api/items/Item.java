package ttdev.api.items;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * @param enchantment
	 * @param level
	 */
	public void addUnsafeEnchant(Enchantment enchantment, int level) {
		this.itemStack.addUnsafeEnchantment(enchantment, level);
	}
	
	/**
	 * 
	 * @param enchantment
	 * @return
	 */
	public int getEnchantmentLevel(Enchantment enchantment) {
		return this.itemStack.getEnchantmentLevel(enchantment);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<Enchantment, Integer> getEnchantments() {
		return this.itemStack.getEnchantments();
	}

	public int getAmount() {
		return itemStack.getAmount();
	}

	/**
	 * 
	 * @return
	 */
	public short getDurability() {
		return this.itemStack.getDurability();
	}

	/**
	 * 
	 * @param durability
	 */
	public void setDurability(int durability) {
		Short d = (short) durability;
		this.itemStack.setDurability(d);
	}
	
	/**
	 * 
	 * @param Set the displayname of an item.
	 */
	public void setName(String name) {
		this.itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
	}
	
	/**
	 * 
	 * @param lore
	 */
	public void addLore(String lore) {
		List<String> lores = this.itemMeta.getLore();
		lores.add(ChatColor.translateAlternateColorCodes('&', lore));
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
	
	/**
	 * 
	 * @return
	 */
	public boolean hasLore() {
		return this.itemMeta.hasLore();
	}
	
	/**
	 * 
	 * @return
	 */
	public Material getType() {
		return this.itemStack.getType();
	}
	
	/**
	 * 
	 * @param itemFlag
	 */
	public void addItemFlag(ItemFlag itemFlag) {
		this.itemMeta.addItemFlags(itemFlag);
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<ItemFlag> getItemFlags() {
		return this.itemMeta.getItemFlags();
	}

}
