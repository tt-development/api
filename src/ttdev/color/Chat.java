package ttdev.color;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

	public static String convertColors(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static void sendMessage(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void printToConsole(String message) {
		System.out.println(message);
	}
	
}
