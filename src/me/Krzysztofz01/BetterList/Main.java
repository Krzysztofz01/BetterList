package me.Krzysztofz01.BetterList;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		ConsoleCommandSender console = getServer().getConsoleSender();
		console.sendMessage("BetterList V1.0 by: Krzysztofz01");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("blist")) {
			if(args.length == 0) {
				return this.Display(sender, ListUtility.ListOnline(), true);
			} else if(args.length > 0) {
				if(args[0] == "online") {
					return this.Display(sender, ListUtility.ListOnline(), true);
				} else if(args[0] == "offline") {
					return this.Display(sender, ListUtility.ListOffline(), false);									
				} else if(args[0] == "banned") {
					return this.Display(sender, ListUtility.ListBanned(), false);
				}
			}
		}
		return false;
	}
	
	private boolean Display(CommandSender sender, List<String> content, boolean implemented) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			if(implemented) {
				content.forEach((line) -> {
					player.sendMessage(line);
				});
			} else {
				player.sendMessage(ChatColor.RED + "Not implemented!");
			}
			
			return true;
		}
		
		if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = (ConsoleCommandSender)sender;
			
			if(implemented) {
				content.forEach((line) -> {
					console.sendMessage(line);
				});
			} else {
				console.sendMessage(ChatColor.RED + "Not implemented");
			}
			
			return true;
		}
		
		return false;
	}
}
