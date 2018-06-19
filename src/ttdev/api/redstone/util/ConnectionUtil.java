package ttdev.api.redstone.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.material.Button;
import org.bukkit.material.RedstoneWire;

import java.util.ArrayList;
import java.util.List;

public class ConnectionUtil
{
    public static List<RedstoneWire> getConnectedRedstone(Block block)
    {
        Material type = block.getType();

        Block[] connectedBlocks = new Block[4];
        List<RedstoneWire> redstoneWires = new ArrayList<>();

        try
        {
            connectedBlocks[0] = block.getRelative(1, 0, 0);
            connectedBlocks[1] = block.getRelative(-1, 0, 0);
            connectedBlocks[2] = block.getRelative(0, 0, 1);
            connectedBlocks[3] = block.getRelative(0, 0, -1);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return redstoneWires;
        }

        for (Block b : connectedBlocks)
        {
            if (b.getType() == Material.REDSTONE_WIRE)
            {
                BlockState state = b.getState();
                RedstoneWire redstoneWire = (RedstoneWire) state;
                if (!redstoneWire.isPowered())
                {
                    continue;
                }
                redstoneWires.add((RedstoneWire) state);
            }
        }

        if (redstoneWires.size() == 0)
        {
            if (type == Material.WOOD_BUTTON || type == Material.STONE_BUTTON)
            {
                return getButtonConnectedRedstone(block);
            }
        }

        return redstoneWires;
    }

    private static List<RedstoneWire> getButtonConnectedRedstone(Block block)
    {
        Location location = block.getLocation();
        Button button = (Button) block.getState().getData();

        BlockFace face = button.getAttachedFace();
        Location attachedBlockLocation = location.add(face.getModX(), face.getModY(), face.getModZ());
        Block attachedBlock = attachedBlockLocation.getBlock();

        return getConnectedRedstone(attachedBlock);
    }

}
