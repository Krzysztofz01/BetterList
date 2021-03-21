package me.Krzysztofz01.BetterList;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ListUtility {
	
	//Generate the header for all commands with title and the count of players online
	private static List<String> Header() {
		List<String> headers = new ArrayList<String>();
		
		headers.add(ChatColor.translateAlternateColorCodes('&', "&3&o-> &bBetterList"));
		headers.add(ChatColor.translateAlternateColorCodes('&', "&3&o-> &b" + Bukkit.getOnlinePlayers().size() +" / " + Bukkit.getMaxPlayers() + " online"));
		
		return headers;
	}
	
	//Parse single player data
	private static String ParsePlayer(Player player) {
		String IpAddress = player.getAddress().getAddress().getHostAddress();
		String country = Geolocation.GetLocation(IpAddress);
		
		Location loc = player.getLocation();
		String parsedLocation = "[" + Math.floor(loc.getX()) + ", " + Math.floor(loc.getY()) + ", " + Math.floor(loc.getZ()) + "]";
		
		if(player.isOp()) {
			return ChatColor.translateAlternateColorCodes('&', "&3&o-> &6&l" + player.getName() + "&r&3&o - IP: " + IpAddress + " [" + country + "] " + parsedLocation);
		}
		return ChatColor.translateAlternateColorCodes('&', "&3&o-> &e" + player.getName() + "&r&3&o - IP: " + IpAddress + " [" + country + "] " + parsedLocation);
	}
	
	//Display all online players
	public static List<String> ListOnline() {
		List<String> outputContainer = new ArrayList<String>();
		List<Player> onlinePlayers = new ArrayList<Player>(Bukkit.getOnlinePlayers());
			
		outputContainer.addAll(Header());
		outputContainer.add(ChatColor.translateAlternateColorCodes('&', "&3&o-> &bOnline Players:"));
		outputContainer.add("");
		
		onlinePlayers.forEach((player) -> {
			outputContainer.add(ParsePlayer(player));
		});
		
		return outputContainer;
	}
	
	//Display all offline players
	public static List<String> ListOffline() {
		return null;
	}
	
	//Display all banned players
	public static List<String> ListBanned() {
		List<String> outputContainer = new ArrayList<String>();
		var bannedPlayers = Bukkit.getBannedPlayers();
		
		outputContainer.addAll(Header());
		outputContainer.add(ChatColor.translateAlternateColorCodes('&', "&3&o-> &bBanned Players:"));
		outputContainer.add("");
		
		bannedPlayers.forEach((player) -> {
			outputContainer.add(ParsePlayer(player.getPlayer()));
		});
		
		return outputContainer;
	}
	
}
