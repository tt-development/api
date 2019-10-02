package ttdev.api.bukkit.packet.listener;

import java.util.ArrayList;

import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import ttdev.api.bukkit.packet.PacketType;
import ttdev.api.bukkit.packet.event.PacketListener;
import ttdev.api.bukkit.packet.event.PacketListenerEvent;
import ttdev.api.general.configuration.APIConfiguration;
import ttdev.api.general.configuration.Status;

public class PacketListenerInitiater implements Listener {
	
	private static ArrayList<PacketListener> listeners = new ArrayList<>();
	
	/**
	 * Listener handler
	 */
	public static void registerEvent(PacketListener listener) {
		listeners.add(listener);
	}
	
	public static void triggerPacketEvent(Player player, Object packet, PacketType type) {
		if (APIConfiguration.getPacketListenerStatus().equals(Status.DENIED)) {
			return;
		}
		for (PacketListener l : listeners) {
			PacketListenerEvent event = new PacketListenerEvent(player, packet, type);
			l.PacketListenerEvent(event);
		}
	}
	
	/**
	 * Main Listener.
	 */
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		/**
		 * Block the PacketListener.
		 */
		if (APIConfiguration.getPacketListenerStatus().equals(Status.DENIED)) {
			return;
		}
		injectPlayer(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		/**
		 * Block the PacketListener.
		 */
		if (APIConfiguration.getPacketListenerStatus().equals(Status.DENIED)) {
			return;
		}
		removePlayer(e.getPlayer());
	}
	
	/**
	 * Removes the Player.
	 * @param player
	 */
	public static void removePlayer(Player player) {
		Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
		channel.eventLoop().submit(()-> { 
			channel.pipeline().remove(player.getName());
			return null;
		});
	}
	
	/**
	 * Injects the Player and starts listening for packets.
	 * @param player
	 */
	public static void injectPlayer(Player player) {
		ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
			
			@Override
			public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
			
				PacketListenerInitiater.triggerPacketEvent(player, packet, PacketType.READ);
				
				super.channelRead(channelHandlerContext, packet);
			}
			
			@Override
			public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
				
				PacketListenerInitiater.triggerPacketEvent(player, packet, PacketType.WRITE);
				
				super.write(channelHandlerContext, packet, channelPromise);
			}
			
		};
		
		ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
		
		pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
		
	}
	
	
	
}
