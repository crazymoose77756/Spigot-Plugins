package me.crazymoose.Launch;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("lch")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("Console goes flying!");
				}
				Player player = (Player) sender;
				
				if(args.length == 0) {
					//launch
					player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zoooom!");
					player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
					
					return true;
				}
				//launch num
				if (isNum(args[0])) {
					player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zoooom!");
					player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
				}
				else {
					player.sendMessage(ChatColor.RED + "Do /launch *Number*");
				}
				
				return true;
			}
			return false;
		}
		
		public boolean isNum(String num) {
			try {
				Integer.parseInt(num); 
			} catch (Exception e) {
				return false;
			}
			return true;
			}

}
