package me.crazymoose.ChangeTeam;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	public Inventory inv;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		createInv();
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("changeteam")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You cant do this");
				return true;
			}
			Player player = (Player) sender;
			//open gui
			player.openInventory(inv);
			return true;
			
		}
		return false;
	}
	
	
	@EventHandler()
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(inv))
        	return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        Color[] armorColors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.PURPLE, Color.AQUA, Color.BLACK};

        if (event.getSlot() == 8) {
            player.closeInventory();
        } else if (event.getSlot() < armorColors.length) {
            ItemStack[] armor = player.getEquipment().getArmorContents();
            armor = changeColor(armor, armorColors[event.getSlot()]);
            player.getEquipment().setArmorContents(armor);
            player.sendMessage("You have changed your team!");
        }
    }

    private ItemStack[] changeColor(ItemStack[] armorItems, Color color) {
        for (ItemStack item : armorItems) {
            if (item.getItemMeta() instanceof LeatherArmorMeta) {
                    LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                    meta.setColor(color);
                    item.setItemMeta(meta);
            }
        }

        return armorItems;
    }
	
	
	
	
	public void createInv() {
		
		inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Select Team");
		
		ItemStack item = new ItemStack(Material.BLUE_CONCRETE);
		ItemMeta meta = item.getItemMeta();
		
		//Blue
		meta.setDisplayName(ChatColor.DARK_BLUE + "Blue Team");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Click to join team");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		//Red
		item.setType(Material.RED_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_RED + "Red Team");
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		//lime
		item.setType(Material.LIME_CONCRETE);
		meta.setDisplayName(ChatColor.GREEN + "Lime Team");
		item.setItemMeta(meta);
		inv.setItem(2, item);

		//orange
		item.setType(Material.ORANGE_CONCRETE);
		meta.setDisplayName(ChatColor.GOLD + "Orange Team");
		item.setItemMeta(meta);
		inv.setItem(3, item);

		//purple
		item.setType(Material.PURPLE_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Purple Team");
		item.setItemMeta(meta);
		inv.setItem(4, item);

		//cyan
		item.setType(Material.CYAN_CONCRETE);
		meta.setDisplayName(ChatColor.BLUE + "Cyan Team");
		item.setItemMeta(meta);
		inv.setItem(5, item);

		
		item.setType(Material.BLACK_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_GRAY + "Black Team");
		item.setItemMeta(meta);
		inv.setItem(6, item);

		//close
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close Menu");
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(8, item);
	}

}
