package me.Krzysztofz01.BetterList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEventHandler implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String IpAddress = player.getAddress().getAddress().getHostAddress();
		Location loc = player.getLocation();
		String parsedLocation = "[" + Math.floor(loc.getX()) + ", " + Math.floor(loc.getY()) + ", " + Math.floor(loc.getZ()) + "]";
		
		if(player.hasPlayedBefore()) {
			if(player.isOp()) {
				Notify(ChatColor.translateAlternateColorCodes('&', "&3&o-> &6&l" + player.getName() + "&r&3&o - IP: " + IpAddress + " " + parsedLocation));
			} else {
				Notify(ChatColor.translateAlternateColorCodes('&', "&3&o-> &e" + player.getName() + "&r&3&o - IP: " + IpAddress + " " + parsedLocation));
			}
		} else {
			String country = Geolocation.GetLocation(IpAddress);
			if(player.isOp()) {
				Notify(ChatColor.translateAlternateColorCodes('&', "&3&o-> &6&l" + player.getName() + "&r&3&o - IP: " + IpAddress + " [" + country + "] " + parsedLocation));
			} else {
				Notify(ChatColor.translateAlternateColorCodes('&', "&3&o-> &e" + player.getName() + "&r&3&o - IP: " + IpAddress + " [" + country + "] " + parsedLocation));
			}
		}
	}
	
	private void Notify(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
		
		var players = Bukkit.getOnlinePlayers();
		players.forEach((player) -> {
			if(player.isOp()) {
				player.sendMessage(message);
			}
		});
	}
}
