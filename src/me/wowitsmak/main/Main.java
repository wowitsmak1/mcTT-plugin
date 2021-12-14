package me.wowitsmak.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.score.PlayerPoints;
import me.wowitsmak.main.survivalgames.commands.StartCommand;
import me.wowitsmak.main.survivalgames.events.Events;
import me.wowitsmak.main.survivalgames.managers.GameManager;
import me.wowitsmak.main.survivalgames.managers.PlayerManager;


public class Main extends JavaPlugin {
    
	private static GameManager gameManager;
	private static PlayerManager playerManager;
	private static PlayerPoints playerPoints;
	private static Main instance;
	public Main() {
		Main.playerManager = new PlayerManager();
		Main.gameManager = new GameManager();
		Main.playerPoints = new PlayerPoints();
	}
    @Override
    public void onEnable() {
    	instance = this;
    	final FileConfiguration config = this.getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		this.saveDefaultConfig();
    	StartCommand startcommand = new StartCommand();
    	Events blockbreak = new Events(new HungerGamesLootTable());
    	getServer().getPluginManager().registerEvents(blockbreak, this);
    	this.getCommand("survivalgames-start").setExecutor(startcommand);
    }
    
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	instance = null;
    }
    public static Main getInstance(){
	    return instance;
	}
    public static PlayerPoints getPointManager() { return playerPoints; }
    public static GameManager getGameManager() { return gameManager; }
    public static PlayerManager getPlayerManager() { return playerManager; }
}