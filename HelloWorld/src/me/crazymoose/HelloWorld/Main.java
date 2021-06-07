package me.crazymoose.HelloWorld;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		//startup, reloads, plugin reloads
	}
	
	@Override
	public void onDisable() {
		//shutdown, reloads, plugin reloads
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("hello")) {
			if(sender instanceof Player) {
				//player
				Player player = (Player) sender;
				if (player.hasPermission("hello.use")) {
					player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Welcome to the server!");
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&1H&2e&3l&4l&5o"));
					return true;
				}
				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "No Premission");
				return true;
			}
			else {
				// console
				sender.sendMessage(ChatColor.YELLOW + "Hey Console");
				return true;
			}
		}
		
		return false;
	}

}
