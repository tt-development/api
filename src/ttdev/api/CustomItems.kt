package ttdev.api

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/*
Goals:
- Easily create custom items of a certain type/namepsace.
- Easily compare item stacks to determine if they are custom.
- Easily save custom items.
 */

class ItemNamespaceRegistry {

    companion object {

        val identifiers = mutableSetOf<Entry>()

        fun getEntry(name: String): Entry? = identifiers.find { it == Entry(name) }

        fun addName(name: String) = identifiers.add(Entry(name))

        fun hasName(name: String) = identifiers.contains(Entry(name))
    }

    data class Entry(val name: String)
}

class CustomItem(val entry: ItemNamespaceRegistry.Entry, name: String, private val itemStack: ItemStack) {

    constructor(entry: ItemNamespaceRegistry.Entry, name: String) : this(entry, name, ItemStack(Material.DIRT))

    init {
        setDisplayName(name)
        setLore(entry.name, 0)
    }

    fun setAmount(amount: Int) {
        itemStack.amount = amount
    }

    fun setDisplayName(name: String) {
        setMetaProperty { it.displayName = colorize(name) }
    }

    fun setLore(vararg lore: String) {
        setMetaProperty {
            val loreList = lore.map { colorize(it) }.toMutableList()
            loreList.add(0, entry.name)
            it.lore = loreList
        }
    }

    fun setLore(text: String, line: Int) {
        setMetaProperty {
            var lore = if (it.hasLore()) it.lore else mutableListOf()
            if (lore[line] == null) lore.add(line, text)
            else lore[line] = text
            it.lore = lore
        }
    }

    private fun setMetaProperty(function: (ItemMeta) -> Unit) {
        val meta = itemStack.itemMeta
        function(meta)
        itemStack.itemMeta = meta
    }

    fun makeItemStack() = ItemStack(itemStack)
}

fun colorize(sequence: String) = ChatColor.translateAlternateColorCodes('&', sequence)

fun ItemStack.isCustomItem() = ItemNamespaceRegistry.identifiers.any { itemMeta.displayName.startsWith(it.name) }

