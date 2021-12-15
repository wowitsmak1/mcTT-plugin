package me.wowitsmak.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.score.PlayerPoints;
import me.wowitsmak.main.survivalgames.commands.AddToTeam;
import me.wowitsmak.main.survivalgames.commands.RemoveFromTeam;
import me.wowitsmak.main.survivalgames.commands.StartHungerGamesCommand;
import me.wowitsmak.main.survivalgames.commands.StopHungerGamesCommand;
import me.wowitsmak.main.survivalgames.events.Events;
import me.wowitsmak.main.survivalgames.managers.GameManager;
import me.wowitsmak.main.survivalgames.managers.HungerGamesStart;
import me.wowitsmak.main.survivalgames.managers.PlayerManager;
import me.wowitsmak.main.teams.TeamStuff;


public class Main extends JavaPlugin {
    private static Integer game = 1;
	private static GameManager gameManager;
	private static PlayerManager playerManager;
	private static HungerGamesStart hgstart;
	private static PlayerPoints playerPoints;
	private static Main instance;
	private static TeamStuff teamStuff;
	public Main() {
		Main.teamStuff = new TeamStuff();
		Main.playerManager = new PlayerManager();
		Main.gameManager = new GameManager();
		Main.playerPoints = new PlayerPoints();
		Main.hgstart = new HungerGamesStart();
	}
    @Override
    public void onEnable() {
    	instance = this;
    	final FileConfiguration config = this.getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		this.saveDefaultConfig();
		AddToTeam teamadd = new AddToTeam();
		RemoveFromTeam remteam = new RemoveFromTeam();
    	StartHungerGamesCommand hstartcommand = new StartHungerGamesCommand();
		StopHungerGamesCommand hstopcommand = new StopHungerGamesCommand();
    	Events blockbreak = new Events(new HungerGamesLootTable());
    	getServer().getPluginManager().registerEvents(blockbreak, this);
    	this.getCommand("survivalgames-start").setExecutor(hstartcommand);
		this.getCommand("survivalgames-stop").setExecutor(hstopcommand);
		this.getCommand("team-add").setExecutor(teamadd);
		this.getCommand("team-remove").setExecutor(remteam);
    }
    
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	instance = null;
    }
    public static Main getInstance(){
	    return instance;
	}
	public static TeamStuff getTeamManager(){ return teamStuff; }
    public static PlayerPoints getPointManager() { return playerPoints; }
    public static GameManager getGameManager() { return gameManager; }
    public static PlayerManager getPlayerManager() { return playerManager; }
	public static Integer getRound(){ return game; }
	public static void setRound(Integer num){ game = num; }
	public static Integer getTime(){ return hgstart.time; }
	public static void setTime(Integer num){ hgstart.time = num; }
	
}