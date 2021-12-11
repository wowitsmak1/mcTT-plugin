package me.wowitsmak.main;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import me.wowitsmak.main.survivalgames.commands.AddPoint;
import me.wowitsmak.main.survivalgames.commands.StartCommand;
import me.wowitsmak.main.survivalgames.commands.SubtractPoint;
import me.wowitsmak.main.survivalgames.events.Events;
import me.wowitsmak.main.survivalgames.managers.GameManager;


public class Main extends JavaPlugin {
    
	private static Main instance;
	public static Main getInstance(){
	    return instance;
	}
	public Main plugin;
	// todo: points system

    @Override
    public void onEnable() {
		FileConfiguration config = this.getConfig();
		config.addDefault("youAreAwesome", true);
		this.saveDefaultConfig();
		loadConfig();
    	GameManager gameManager = new GameManager(this);
    	StartCommand startcommand = new StartCommand(gameManager);
    	AddPoint addPoint = new AddPoint();
    	SubtractPoint removePoint = new SubtractPoint();
    	Events blockbreak = new Events(gameManager);
    	getServer().getPluginManager().registerEvents(blockbreak, this);
    	this.getCommand("survivalgames-start").setExecutor(startcommand);
    	this.getCommand("addpoint").setExecutor(addPoint);
    	this.getCommand("removepoint").setExecutor(removePoint);
    }
    
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
    
    
}

 