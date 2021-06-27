package me.crazymoose.CustomRecipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.addRecipe(getRecipe());
		Bukkit.addRecipe(getPickaxeRecipe());
	}

	@Override
	public void onDisable() {

	}

	public ShapedRecipe getRecipe() {
		
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		
		NamespacedKey key = new NamespacedKey(this, "nether_star");
		
		ShapedRecipe recipe = new ShapedRecipe(key, item);
		 
		recipe.shape(" T ", "TET", " T ");
		
		recipe.setIngredient('T', Material.GHAST_TEAR);
		recipe.setIngredient('E', Material.EMERALD_BLOCK);
		
		return recipe;
	}
	
public ShapedRecipe getPickaxeRecipe() {
		
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "Emerald Pickaxe");
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		item.setItemMeta(meta);
		
		NamespacedKey key = new NamespacedKey(this, "emerald_pickaxe");
		
		ShapedRecipe recipe = new ShapedRecipe(key, item);
		 
		recipe.shape("EEE", " S ", " S ");
		
		recipe.setIngredient('S', Material.STICK);
		recipe.setIngredient('E', Material.EMERALD_BLOCK);
		
		return recipe;
	}

}
