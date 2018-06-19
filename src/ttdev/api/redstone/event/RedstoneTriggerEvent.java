package ttdev.api.redstone.event;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.material.RedstoneWire;
import ttdev.api.redstone.TriggerType;

import java.util.List;

public class RedstoneTriggerEvent extends Event
{

    private static HandlerList handlerList = new HandlerList();

    private Block callingBlock;
    private List<RedstoneWire> redstoneWires;
    private TriggerType triggerType;

    public RedstoneTriggerEvent(Block callingBlock, List<RedstoneWire> redstoneWires, TriggerType triggerType)
    {
        this.callingBlock = callingBlock;
        this.redstoneWires = redstoneWires;
        this.triggerType = triggerType;
    }

    public Block getCallingBlock()
    {
        return callingBlock;
    }

    public List<RedstoneWire> getRedstoneWires()
    {
        return redstoneWires;
    }

    public TriggerType getTriggerType()
    {
        return triggerType;
    }

    public HandlerList getHandlers()
    {
        return handlerList;
    }

    public static HandlerList getHandlerList()
    {
        return handlerList;
    }
}
