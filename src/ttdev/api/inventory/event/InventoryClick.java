package ttdev.api.inventory.event;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import ttdev.api.inventory.PlayerInventory;
import ttdev.api.items.Item;

public class InventoryClick {

	private Player whoClicked;
	private PlayerInventory playerInventory;
	private Item clickedItem;
	
	private boolean isRightClick;
	private boolean isLeftClick;
	private boolean isShiftClick;
	
	private int Slot;
	private int HotbarSlot;
	
	private ClickType clickType;
	
	public InventoryClick(Player whoClicked, PlayerInventory playerInventory, Item clickedItem, boolean RightClick, boolean leftClick, boolean shiftClick, int Slot, ClickType clickType, int hotbarSlot) {
		this.whoClicked = whoClicked;
		this.playerInventory = playerInventory;
		this.clickedItem = clickedItem;
		this.isRightClick = RightClick;
		this.isLeftClick = leftClick;
		this.isShiftClick = shiftClick;
		this.Slot = Slot;
		this.clickType = clickType;
		this.HotbarSlot = hotbarSlot;
	}
	
	/**
	 * 
	 * @return
	 */
	public Player getWhoClick() {
		return this.whoClicked;
	}
	
	/**
	 * 
	 * @return
	 */
	public PlayerInventory getPlayerInventory() {
		return this.playerInventory;
	}
	
	/**
	 * 
	 * @return
	 */
	public Item getClickedItem() {
		return this.clickedItem;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRightClick() {
		return this.isRightClick;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isLeftClick() {
		return this.isLeftClick;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isShiftClick() {
		return this.isShiftClick;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSlot() {
		return this.Slot;
	}
	
	/**
	 * 
	 * @return
	 */
	public ClickType getClickType() {
		return this.clickType;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHotbarSlot() {
		return this.HotbarSlot;
	}
	
}
