package ttdev.api.bukkit.packet.event;

import org.bukkit.entity.Player;

import ttdev.api.bukkit.packet.PacketType;

public class PacketListenerEvent {
	
	private Player player;
	private Object packet;
	private PacketType type;

	public PacketListenerEvent(Player player, Object packet, PacketType type) {
		this.player = player;
		this.packet = packet;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Object packet() {
		return this.packet;
	}
	
	public PacketType getType() {
		return this.type;
	}
	
}
