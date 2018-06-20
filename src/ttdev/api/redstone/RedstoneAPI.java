package ttdev.api.redstone;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.material.RedstoneWire;

import java.util.List;

public abstract class RedstoneAPI {

    /**
     * Returns true if this block is connected to a powered redstone source.
     * @param block
     * @return
     */
    public static boolean isConnectedBlock(Block block) {
        return RedstoneFinder.isConnectedBlock(block);
    }

    /**
     * Gets the redstone wire connected to this block or none if there
     * isn't any redstone wire connected.
     * @param block
     * @return
     */
    public static List<RedstoneWire> getConnectedWire(Block block) {
        return RedstoneFinder.getConnectedWire(block);
    }

    /**
     * Gets the redstone sources connected to this block or none if there
     * aren't any connected sources.
     * @param block
     * @return
     */
    public static List<Block> getConnectedRedstone(Block block) {
        return RedstoneFinder.getConnectedRedstone(block);
    }

    /**
     * Gets the possible locations where a redstone source can connect to this block.
     * @param block
     * @return
     */
    public static List<Location> getConnectLocations(Block block) {
        return RedstoneFinder.getConnectLocations(block);
    }

}
