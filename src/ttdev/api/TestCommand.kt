package ttdev.api

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {

        val player = sender as Player

        /* Try duplicating identifiers */
        ItemNamespaceRegistry.addName("John Doe")
        ItemNamespaceRegistry.addName("John Doe")
        ItemNamespaceRegistry.addName("another")

        val entryCount = ItemNamespaceRegistry.identifiers.size
        if (entryCount != 2) {
            println("entry count failed")
        }

        val doeEntry = ItemNamespaceRegistry.getEntry("John Doe") ?: kotlin.run {
            println("get entry 1 failed")
            return true
        }

        val anotherEntry = ItemNamespaceRegistry.getEntry("another") ?: kotlin.run {
            println("get entry 2 failed")
            return true
        }

        val customItem = CustomItem(doeEntry, "&aName")
        customItem.setAmount(8)
        customItem.setDisplayName("&cSome Name")
        customItem.setLore("&aFirst line", "&bsecond line", "&cthird line")

        val customItem1 = CustomItem(anotherEntry,"&6Name")
        customItem1.setAmount(2)
        customItem1.setLore("Second line", "Third line")

        player.inventory.addItem(customItem.makeItemStack())
        player.inventory.addItem(customItem1.makeItemStack())

        if (!player.itemInHand.isCustomItem()) {
            println("custom item failed")
        }

        return true
    }
}