package ttdev.api.redstone;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

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

        if (blockType == Material.STONE_BUTTON || blockType == Material.WOOD_BUTTON)
        {

        }
    }

}
