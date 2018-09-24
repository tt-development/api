package ttdev.api.bukkit.bungeecord;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import ttdev.api.API;

public class Bungeecord implements PluginMessageListener {
	
	/**
	 * https://www.spigotmc.org/wiki/bukkit-bungee-plugin-messaging-channel/
	 */

	static {
		new Bungeecord();
	}
	
	private Bungeecord() {
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(API.getInstance(), "BungeeCord", this);
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		//ByteArrayDataInput in = ByteStreams.newDataInput(message);
		
		//TODO Create custom Listener.
	}
	
	/**
	 * Send a Player to a specific server using Bungeecord.
	 * @param player
	 * @param server
	 */
	public static void send(Player player, String server) {
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);

		player.sendPluginMessage(API.getInstance(), "BungeeCord", out.toByteArray());
	}
	
	/**
	 * Send a Player to a specific server using Bungeecord.
	 * @param player
	 * @param server
	 */
	public static void send(String player, String server) {
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ConnectOther");
		out.writeUTF(player);
		out.writeUTF(server);
		
	}

	
}
