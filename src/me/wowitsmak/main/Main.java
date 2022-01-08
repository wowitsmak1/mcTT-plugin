package me.wowitsmak.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.score.Leaderboard;
import me.wowitsmak.main.score.PlayerPoints;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import me.wowitsmak.main.commands.AddToTeam;
import me.wowitsmak.main.commands.HideOtherPlayers;
import me.wowitsmak.main.commands.RemoveFromTeam;
import me.wowitsmak.main.commands.ShowOtherPlayers;
import me.wowitsmak.main.commands.StartHungerGamesCommand;
import me.wowitsmak.main.commands.StopHungerGamesCommand;
import me.wowitsmak.main.events.Events;
import me.wowitsmak.main.managers.FindTheButton;
import me.wowitsmak.main.managers.FindTheButtonStart;
import me.wowitsmak.main.managers.GameManager;
import me.wowitsmak.main.managers.HungerGamesStart;
import me.wowitsmak.main.managers.PlayerManager;
import me.wowitsmak.main.teams.TeamStuff;
import me.wowitsmak.main.worlds.ReadyWorld;


public class Main extends JavaPlugin {
    private static Integer game = 1;
	private static FindTheButtonStart fButtonStart;
	private static FindTheButton fbutton;
	private static GameManager gameManager;
	private static PlayerManager playerManager;
	private static HungerGamesStart hgstart;
	private static ReadyWorld hgworldpreparer;
	private static PlayerPoints playerPoints;
	private static TeamStuff teamStuff;
	private static ScoreboardOwner scmanager;
	private static Main instance;
	private static Leaderboard leaderboard;
	public Main() {
		Main.scmanager = new ScoreboardOwner();
		Main.teamStuff = new TeamStuff();
		Main.playerManager = new PlayerManager();
		Main.gameManager = new GameManager();
		Main.playerPoints = new PlayerPoints();
		Main.hgstart = new HungerGamesStart();
		Main.hgworldpreparer = new ReadyWorld();
		Main.leaderboard = new Leaderboard();
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
		ShowOtherPlayers showplayers = new ShowOtherPlayers();
		HideOtherPlayers hideplayers = new HideOtherPlayers();
    	Events blockbreak = new Events(new HungerGamesLootTable());
    	getServer().getPluginManager().registerEvents(blockbreak, this);
    	this.getCommand("survivalgames-start").setExecutor(hstartcommand);
		this.getCommand("survivalgames-stop").setExecutor(hstopcommand);
		this.getCommand("team-add").setExecutor(teamadd);
		this.getCommand("team-remove").setExecutor(remteam);
		this.getCommand("player-show").setExecutor(showplayers);
		this.getCommand("player-hide").setExecutor(hideplayers);
    }
    
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	instance = null;
    }
    public static Main getInstance(){
	    return instance;
	}
    public static ScoreboardOwner getScoreboardManager() {return scmanager; }
	public static TeamStuff getTeamManager(){ return teamStuff; }
    public static PlayerPoints getPointManager() { return playerPoints; }
    public static GameManager getGameManager() { return gameManager; }
    public static PlayerManager getPlayerManager() { return playerManager; }
	public static Integer getRound(){ return game; }
	public static void setRound(Integer num){ game = num; }
	public static Integer getTime(){ return hgstart.time; }
	public static void setTime(Integer num){ hgstart.time = num; }
	public static Integer getCooldown(){ return hgstart.cooldown; }
	public static void setCooldown(Integer num) {hgstart.cooldown = num; }
	public static ReadyWorld getHungerGamesWorld() { return hgworldpreparer; }	
	public static Leaderboard getLeaderboard() { return leaderboard; }
	public static FindTheButtonStart getFindTheButtonStart() { return fButtonStart; }
	public static FindTheButton getFindTheButton() { return fbutton; }
}