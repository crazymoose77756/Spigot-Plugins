package me.crazymoose.Launch;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getCommand("Launch").setExecutor(new Fly());
	}
	
	public void onDisable() {
		
	}
	

}
