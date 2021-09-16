package me.crazymoose.PrivateVault;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin implements Listener {

   public static Map<String, ItemStack[]> menus = new HashMap<String, ItemStack[]>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig(); //<create config.yml>

        if (this.getConfig().contains("data"))
            this.restoreInvs();
            this.getConfig().set("data", null);
            this.saveConfig();
    }

    @Override
    public void onDisable() {
        if (!menus.isEmpty()) {
            this.saveInvs();
        }
    }

    public void saveInvs() {
        for (Map.Entry<String, ItemStack[]> entry : menus.entrySet()) {
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void restoreInvs() {
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key ->{
            ItemStack[] content = ((List<ItemStack>) this.getConfig().get("data." + key)).toArray(new ItemStack[0]);
            menus.put(key, content);
        });
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("pv")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("No!");
                return true;
            }
            Player player = (Player) sender;
            if (menus.containsKey(player.getUniqueId().toString())) {
                // have info in the hashmap already
                Inventory inv = Bukkit.createInventory(player, 54, player.getName() + "'s Private Vault");
                inv.setContents(menus.get(player.getUniqueId().toString()));
                player.openInventory(inv);
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains(event.getPlayer().getName() + "'s Private Vault"))
            menus.put(event.getPlayer().getUniqueId().toString(), event.getInventory().getContents());
    }

}



















