package me.wowitsmak.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.survivalgames.commands.AddPoint;
import me.wowitsmak.main.survivalgames.commands.StartCommand;
import me.wowitsmak.main.survivalgames.commands.SubtractPoint;
import me.wowitsmak.main.survivalgames.events.Events;
import me.wowitsmak.main.survivalgames.managers.GameManager;


public class Main extends JavaPlugin {
    
	public FileConfiguration config = this.getConfig();
	private static Main instance;
	public static Main getInstance(){
	    return instance;
	}
	public Main plugin;
	// todo: points system

    @Override
    public void onEnable() {
		
		config.options().copyDefaults(true);
		saveConfig();
		this.saveDefaultConfig();
    	GameManager gameManager = new GameManager(this);
    	StartCommand startcommand = new StartCommand(gameManager);
    	AddPoint addPoint = new AddPoint();
    	SubtractPoint removePoint = new SubtractPoint();
    	Events blockbreak = new Events(new HungerGamesLootTable(instance), instance);
    	getServer().getPluginManager().registerEvents(blockbreak, this);
    	this.getCommand("survivalgames-start").setExecutor(startcommand);
    	this.getCommand("addpoint").setExecutor(addPoint);
    	this.getCommand("removepoint").setExecutor(removePoint);
    }
    public FileConfiguration getconfig() {
    	return getConfig();
    }
    
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
    
    
}