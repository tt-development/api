package ttdev.api.redstone.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.RedstoneWire;
import ttdev.api.redstone.TriggerType;
import ttdev.api.redstone.event.RedstoneTriggerEvent;
import ttdev.api.redstone.util.ConnectionUtil;

import java.util.List;

public class InteractListener implements Listener
{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Action action = event.getAction();

        if (!action.equals(Action.RIGHT_CLICK_BLOCK))
        {
            return;
        }

        Block clickedBlock = event.getClickedBlock();
        Material blockType = clickedBlock.getType();

        if (blockType != Material.STONE_BUTTON && blockType != Material.WOOD_BUTTON)
        {
            return;
        }

        List<RedstoneWire> redstoneWires = ConnectionUtil.getConnectedRedstone(clickedBlock);

        if (redstoneWires.size() == 0)
        {
            return;
        }

        Bukkit.getServer().getPluginManager().callEvent(new RedstoneTriggerEvent(clickedBlock, redstoneWires, TriggerType.BUTTON));
    }

}
