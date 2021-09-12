package me.crazymoose.Stats;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("mystats").setExecutor(new StatCommand());
        this.getCommand("mystats").setTabCompleter(new StatTab());
    }

    @Override
    public void onDisable() {

    }
}
