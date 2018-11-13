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

        println("Item namespace entries: ${ItemNamespaceRegistry.identifiers.size}")

        val doeEntry = ItemNamespaceRegistry.getEntry("John Doe") ?: kotlin.run {
            println("Entry is null.")
            return true
        }

        val anotherEntry = ItemNamespaceRegistry.getEntry("another") ?: kotlin.run {
            println("Another entry is null.")
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

        if (player.itemInHand.isCustomItem()) {
            println("Item in hand is custom.")
        }

        return true
    }
}