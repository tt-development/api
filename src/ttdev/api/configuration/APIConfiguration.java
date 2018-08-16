package ttdev.api.configuration;

public class APIConfiguration {

	private static Status inventoryClick = Status.NOT_SET;
	private static Status inventoryUpdate = Status.NOT_SET;
	private static Status packetListener = Status.NOT_SET;
	
	public static Status getInventoryClickStatus() {
		return inventoryClick;
	}
	
	public static Status getInventoryUpdateStatus() {
		return inventoryUpdate;
	}
	
	public static Status getPacketListenerStatus() {
		return packetListener;
	}
	
	public static void setInventoryClickStatus(Status status) {
		inventoryClick = status;
	}
	
	public static void setInventoryUpdateStatus(Status status) {
		inventoryUpdate = status;
	}
	
	public static void setPacketListenerStatus(Status status) {
		packetListener = status;
	}
	
}
