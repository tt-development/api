package ttdev.api.user.items;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

public class Item {

	private ItemStack itemStack;
	private ItemMeta itemMeta;
	
	private int ID;
	
	/**
	 * Create an instance of Item with an Item.
	 * @param item
	 */
	public Item(Item item) {
		this.itemStack = item.getItemStack().clone();
		this.itemMeta = item.getItemMeta().clone();
		this.ID = item.getID();
	}
	
	/**
	 * Create an instance of Item with an ItemStack.
	 * @param ItemStack
	 */
	public Item(ItemStack itemStack) {
		this.itemStack = itemStack;
		this.itemMeta = this.itemStack.getItemMeta();
	}
	
	/**
	 * Create an instance of Item with an ItemStack
	 * @param material
	 */
	public Item(Material material) {
		this.itemStack = new ItemStack(material);
		this.itemMeta = this.itemStack.getItemMeta();
	}
	
	public Item(String file, String path) {
		
	}
	
	/**
	 * Returns ItemStack.
	 * @return
	 */
	public ItemStack getItemStack() {
		this.itemStack.setItemMeta(itemMeta);
		return this.itemStack;
	}
	
	/**
	 * Returns ItemMeta.
	 * @return
	 */
	public ItemMeta getItemMeta() {
		return this.itemMeta;
	}
	
	/**
	 * Returns Material.
	 * @return
	 */
	public Material getMaterial() {
		return this.itemStack.getType();
	}
	
	/**
	 * Adds an enchantment to the Item.
	 * @param enchantment
	 * @param level
	 */
	public void addEnchant(Enchantment enchantment, int level) {
		this.itemMeta.addEnchant(enchantment, level, false);
	}
	
	/**
	 * Removes an enchantment from the Item.
	 * @param enchantment
	 */
	public void removeEnchant(Enchantment enchantment) {
		this.itemMeta.removeEnchant(enchantment);
	}
	
	/**
	 * Adds an unsafe enchantment to the Item.
	 * @param enchantment
	 * @param level
	 */
	public void addUnsafeEnchant(Enchantment enchantment, int level) {
		this.itemStack.addUnsafeEnchantment(enchantment, level);
	}
	
	/**
	 * Returns level of enchantment.
	 * @param enchantment
	 * @return
	 */
	public int getEnchantmentLevel(Enchantment enchantment) {
		return this.itemStack.getEnchantmentLevel(enchantment);
	}
	
	/**
	 * Returns all the enchantments.
	 * @return
	 */
	public Map<Enchantment, Integer> getEnchantments() {
		return this.itemStack.getEnchantments();
	}
	
	/**
	 * Returns durability.
	 * @return
	 */
	public int getDurability() {
		return this.itemStack.getDurability();
	}
	
	/**
	 * Set the durability.
	 * @param durability
	 */
	public void setDurability(int durability) {
		Short d = (short) durability;
		this.itemStack.setDurability(d);
	}
	
	/**
	 * Set the display name.
	 * @param Set the displayname of an item.
	 */
	public void setName(String name) {
		this.itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
	}
	
	/**
	 * Add a lore. 
	 * @param lore
	 */
	public void addLore(String lore) {
		List<String> lores;
		if (hasLore()) {
			lores = this.itemMeta.getLore();
		} else {
			lores = new ArrayList<>();
		}
		lores.add(ChatColor.translateAlternateColorCodes('&', lore));
		this.itemMeta.setLore(lores);
	}
	
	/**
	 * Set the lore.
	 * @param lores
	 */
	public void setLore(List<String> lores) {
		this.itemMeta.setLore(lores);
	}
	
	/**
	 * Returns name.
	 * @return
	 */
	public String getName() {
		return this.itemMeta.getDisplayName();
	}
	
	/**
	 * Returns list of lore.
	 * @return
	 */
	public List<String> getLore() {
		return this.itemMeta.getLore();
	}
	
	/**
	 * Returns true if Item has lore.
	 * @return
	 */
	public boolean hasLore() {
		return this.itemMeta.hasLore();
	}
	
	/**
	 * Adds an ItemFlag.
	 * @param itemFlag
	 */
	public void addItemFlag(ItemFlag itemFlag) {
		this.itemMeta.addItemFlags(itemFlag);
	}
	
	/**
	 * Remove an ItemFlag.
	 * @param itemFlag
	 */
	public void removeItemFlag(ItemFlag itemFlag) {
		this.itemMeta.removeItemFlags(itemFlag);
	}
	
	/**
	 * Returns list of ItemFlag.
	 * @return
	 */
	public Set<ItemFlag> getItemFlags() {
		return this.itemMeta.getItemFlags();
	}
	
	/**
	 * Returns amount of items.
	 * @return
	 */
	public int getAmount() {
		return this.itemStack.getAmount();
	}
	
	public void setAmount(int amount) {
		this.itemStack.setAmount(amount);
	}
	
	/**
	 * Returns MaxStackSize.
	 * @return
	 */
	public int getMaxStackSize() {
		return this.itemStack.getMaxStackSize();
	}
	
	/**
	 * Set a custom Item ID.
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	 * Returns the item ID.
	 * @return
	 */
	public int getID() {
		return this.ID;
	}
	
}
