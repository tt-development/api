package ttdev.api

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/*
Goals:
- Easily create custom items of a certain type/namepsace.
- Easily compare item stacks to determine if they are custom.
- Easily save and load custom items from file.
 */

/**
 * Used for registering identifiers for a collection
 * of custom items. Only one identifier can exist for
 * a collection making it impossible to create duplicate
 * identifiers.
 */
class ItemNamespaceRegistry {

    companion object {

        val identifiers = mutableSetOf<Entry>()

        fun getEntry(name: String): Entry {
            return identifiers.find { it == Entry(name) }!!
        }

        fun addName(name: String) = identifiers.add(Entry(name))

        fun hasName(name: String) = identifiers.contains(Entry(name))
    }

    data class Entry(val name: String)
}

class CustomItem(val entry: ItemNamespaceRegistry.Entry, name: String, private val itemStack: ItemStack) {

    constructor(entry: ItemNamespaceRegistry.Entry, name: String) : this(entry, name, ItemStack(Material.DIRT))

    constructor(entry: ItemNamespaceRegistry.Entry, itemStack: ItemStack): this(entry, itemStack.itemMeta.displayName, itemStack)

    init {
        setDisplayName(name)
        setLore(entry.name, 0)
    }

    fun setType(material: Material) {
        itemStack.type = material
    }

    fun setAmount(amount: Int) {
        itemStack.amount = amount
    }

    fun setDisplayName(name: String) = setMetaProperty { it.displayName = colorize(name) }

    fun setLore(vararg lore: String) {
        setMetaProperty { meta ->
            val loreList = lore.map { colorize(it) }.toMutableList()
            loreList.add(0, entry.name)
            meta.lore = loreList
        }
    }

    fun setLore(text: String, line: Int) = setMetaProperty {

        var lore = if (it.hasLore()) it.lore else mutableListOf()

        try {
            lore[line] = text
        } catch (e: IndexOutOfBoundsException) {
            lore.add(line, text)
        }

        it.lore = lore
    }

    class LoreContent(val text: String) {

        fun split(delimiter: String): List<String> {
            return text.split(delimiter)
        }
    }

    fun containsLore(text: String): LoreContent? {
        val lore = itemStack.itemMeta.lore.find { it.contains(text) }
        return if (lore is String) LoreContent(lore) else null
    }

    private fun setMetaProperty(function: (ItemMeta) -> Unit) {
        val meta = itemStack.itemMeta
        function(meta)
        itemStack.itemMeta = meta
    }

    fun makeItemStack() = ItemStack(itemStack)
}

fun colorize(sequence: String) = ChatColor.translateAlternateColorCodes('&', sequence)

fun ItemStack.isCustomItem() = ItemNamespaceRegistry.identifiers.any func@{
    return@func if (!hasItemMeta() || !itemMeta.hasLore()) return false
    else itemMeta.lore[0] == (it.name)
}

