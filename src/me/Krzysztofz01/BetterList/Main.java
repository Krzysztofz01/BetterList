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
		
		getServer().getPluginManager().registerEvents(new JoinEventHandler(), this);
	}
	
	@Override
	public void onDisable() {
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("blist")) {
			if(sender.hasPermission("BetterList.blist.full")) {
				if(args.length == 0) {
					return this.Display(sender, ListUtility.ListOnline(), true);
				}
				
				if(args[0].equalsIgnoreCase("online")) {
					return this.Display(sender, ListUtility.ListOnline(), true);
				}
				
				if(args[0].equalsIgnoreCase("offline")) {
					return this.Display(sender, ListUtility.ListOffline(), false);
				}
				
				if(args[0].equalsIgnoreCase("banned")) {
					return this.Display(sender, ListUtility.ListBanned(), false);
				}
				return DisplayUsage(sender);
			}
			return DisplayPermissionError(sender);	
		}
		return false;
	}
	
	//This method checks the instance of the sender and displays the content if functionality is implemented
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
		return DisplayUsage(sender);
	}
	
	//This method shows the available commands if a player type the commands incorrect
	private boolean DisplayUsage(CommandSender sender) {
		String message = ChatColor.RED + "Usage: /blist <online/offline/banned>! Check /help for more informations!";
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			player.sendMessage(message);
		}
		
		if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = (ConsoleCommandSender)sender;
			console.sendMessage(message);
		}
		return false;
	}
	
	//This method displays an error message if the command source has no permission
	private boolean DisplayPermissionError(CommandSender sender) {
		String message = ChatColor.RED + "You do not have permission to use this command.";
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			player.sendMessage(message);
		}
		
		if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = (ConsoleCommandSender)sender;
			console.sendMessage(message);
		}
		return false;
	}
}
