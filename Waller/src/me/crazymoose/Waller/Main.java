package me.crazymoose.Waller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
	
	Map<String, Long> cooldowns = new HashMap<String, Long>();
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("waller")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You cant do this");
				return true;
			}
			Player player = (Player) sender;
			player.getInventory().addItem(getSword());
			player.sendMessage(ChatColor.BLUE + "You have been gifted the waller");
			return true;
		}
		return false;
	}
	
	public ItemStack getSword() {
		
		ItemStack item = new ItemStack(Material.STRING);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GOLD + "Waller String");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "(Right Click) Spawn walls");
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
		
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STRING)) {
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() &&
					event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Waller String"))
				event.setCancelled(true);
			
				//Right CLick
				if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					Player player = event.getPlayer();
					
					if (cooldowns.containsKey(player.getName())) {
						if (cooldowns.get(player.getName()) > System.currentTimeMillis()) {
							long timeleft = (cooldowns.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.GOLD + "Ability will be ready in " + timeleft + " second(s)");
							return;
						}
					}
					
					cooldowns.put(player.getName(), System.currentTimeMillis() + (5 * 1000));
					
					
					Location block = event.getClickedBlock().getLocation();
					Location org = event.getClickedBlock().getLocation();
					
					if (player.getFacing() == BlockFace.NORTH || player.getFacing() == BlockFace.SOUTH) {
						// X direction
						for (int i = 0 ; i < 3 ; i++) {
							block.add(0,1,0);
							block.setX(org.getX());
							if (block.getBlock().getType() == Material.AIR)
								block.getBlock().setType(Material.COBWEB);
							for (int j = 0 ; j < 2 ; j++) {
								block.add(1,0,0);
								if (block.getBlock().getType() == Material.AIR)
									block.getBlock().setType(Material.COBWEB);
							}
							block.setX(org.getX());
							for (int j = 0 ; j < 2 ; j++) {
								block.add(-1,0,0);
								if (block.getBlock().getType() == Material.AIR)
									block.getBlock().setType(Material.COBWEB);
							}
						}
					}
					if (player.getFacing() == BlockFace.EAST || player.getFacing() == BlockFace.WEST) {
						// Z direction
						for (int i = 0 ; i < 3 ; i++) {
							block.add(0,1,0);
							block.setZ(org.getZ());
							if (block.getBlock().getType() == Material.AIR)
								block.getBlock().setType(Material.COBWEB);
							for (int j = 0 ; j < 2 ; j++) {
								block.add(0,0,1);
								if (block.getBlock().getType() == Material.AIR)
									block.getBlock().setType(Material.COBWEB);
							}
							block.setZ(org.getZ());
							for (int j = 0 ; j < 2 ; j++) {
								block.add(0,0,-1);
								if (block.getBlock().getType() == Material.AIR)
									block.getBlock().setType(Material.COBWEB);
							}
						}
					}
					
					return;
				}
				// left click
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					Location block = event.getClickedBlock().getLocation();
					block.add(0, 1, 0);
					TNTPrimed tnt = block.getWorld().spawn(block, TNTPrimed.class);
					tnt.setFuseTicks(20);
				}
		}
	}

}
