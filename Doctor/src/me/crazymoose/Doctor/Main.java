package me.crazymoose.Doctor;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getCommand("Doctor").setExecutor(new Heal());
	}
	
	@Override
	public void onDisable() {
		
	}

}
