package ttdev.api.redstone;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.RedstoneWire;

import java.util.ArrayList;
import java.util.List;

abstract class RedstoneFinder {

    public static boolean isConnectedBlock(Block block) {
        return !getConnectedRedstone(block).isEmpty();
    }

    public static List<RedstoneWire> getConnectedWire(Block block) {

        List<RedstoneWire> connectedWire = new ArrayList<>();
        List<Block> connectedBlocks = getConnectedRedstone(block);

        for (Block connectedBlock : connectedBlocks) {
            Material blockType = connectedBlock.getType();

            if (blockType == Material.REDSTONE_WIRE) {
                BlockState blockState = connectedBlock.getState();
                RedstoneWire redstoneWire = (RedstoneWire) blockState;
                connectedWire.add(redstoneWire);
            }
        }

        return connectedWire;
    }

    public static List<Block> getConnectedRedstone(Block block) {

        List<Block> connectedRedstone = new ArrayList<>();
        List<Location> connectedLocations = getConnectLocations(block);

        for (Location location : connectedLocations) {

            Block connectedBlock = location.getBlock();
            Material blockType = connectedBlock.getType();

            if (blockType == Material.REDSTONE_WIRE || blockType == Material.REDSTONE_BLOCK || blockType == Material.LEGACY_REDSTONE_TORCH_ON) {
                connectedRedstone.add(connectedBlock);
            }

        }

        return connectedRedstone;
    }

    public static List<Location> getConnectLocations(Block block) {
        List<Location> connectedLocations = new ArrayList<>();
        connectedLocations.add(block.getRelative(1, 0, 0).getLocation());
        connectedLocations.add(block.getRelative(-1, 0, 0).getLocation());
        connectedLocations.add(block.getRelative(0, 0, 1).getLocation());
        connectedLocations.add(block.getRelative(0, 0, -0).getLocation());
        return connectedLocations;
    }

}
