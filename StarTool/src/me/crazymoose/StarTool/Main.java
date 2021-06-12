package me.crazymoose.StarTool;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin implements Listener {
	
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new StarEvents(this), this);
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("startool")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Bye console");
				return true;
			}
			Player player = (Player) sender;
			if (player.getInventory().firstEmpty() == -1) {
				Location loc = player.getLocation();
				World world = player.getWorld();
				
				world.dropItemNaturally(loc, getItem());
				player.sendMessage(ChatColor.GOLD + "Dropped a gift near you");
				return true;
			}
			player.getInventory().addItem(getItem());
			player.sendMessage(ChatColor.GOLD + "Trident from the minecraft Gods");
			return true;
			
		}
		return false;
	}
	
	
	public ItemStack getItem () {
		
		ItemStack trident = new ItemStack(Material.TRIDENT);
		ItemMeta meta = trident.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Star Tool");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Right Click) &a&oSpawn minions"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Left Click) &a&oShoot explosives"));
		meta.setLore(lore);
		
		meta.addEnchant(Enchantment.LOYALTY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		trident.setItemMeta(meta);
		
		return trident;
	}
	
}
