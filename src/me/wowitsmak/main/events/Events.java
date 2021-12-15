package me.wowitsmak.main.events;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import me.wowitsmak.main.managers.GameState;
import me.wowitsmak.main.managers.PlayerManager;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener{
	PlayerManager pm = Main.getPlayerManager();
	HungerGamesLootTable hg;
	public Events(HungerGamesLootTable hungerGamesLootTable) {
		this.hg = new HungerGamesLootTable();
	}
	@EventHandler
	private void onBlockBreak(BlockBreakEvent event) {
		if(pm.playing.contains(event.getPlayer())) {
			event.setCancelled(true);
		}
	}
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Main.getPointManager().addPlayer(e.getPlayer());
		Main.getTeamManager().addTeamPlayers();
        Main.getScoreboardManager();
		ScoreboardOwner.createScoreboard(e.getPlayer());
        Main.getScoreboardManager();
		ScoreboardOwner.updateScoreboard();     
        if(Bukkit.getWorld("survivalmap1") == null) {
        	new WorldCreator("survivalmap1").createWorld();
        }
		else if(Bukkit.getWorld("survivalmap2") == null) {
        	new WorldCreator("survivalmap2").createWorld();
        }
		else if(Bukkit.getWorld("survivalmap3") == null) {
        	new WorldCreator("survivalmap3").createWorld();
        }
		else if(Bukkit.getWorld("survivalmap4") == null) {
        	new WorldCreator("survivalmap4").createWorld();
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
    	Main.getScoreboardManager();
    	ScoreboardOwner.updateScoreboard();
    }
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
    	if(event.getWorld() == Bukkit.getWorld("cove"))
            for(BlockState block : event.getChunk().getTileEntities()){
                if(block instanceof Chest){
                 Chest chest = (Chest) block;
                 hg.setupLoot(chest.getInventory());
                }
            }
        }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    	if(pm.playing.contains(event.getPlayer()) && Main.getGameManager().getGameState() == GameState.STARTING) {
    		final Location from = event.getFrom();
            final Location to = event.getTo();
            Main.getScoreboardManager();
            ScoreboardOwner.updateScoreboard();
            if (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ() || from.getBlockY() != to.getBlockY()) {
                event.setTo(from);
            }
    	}
    }
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			if(event.getDamager() instanceof Player){
				Player killer = (Player) event.getDamager();
				Player damaged = (Player) event.getEntity();
				if(Main.getTeamManager().getPlayerTeam(damaged) == Main.getTeamManager().getPlayerTeam(killer)){
					event.setCancelled(true);
				}
			}
		}
	}
    @EventHandler
    public void onPlayerMurder(PlayerDeathEvent event) {
    		if(pm.playing.contains(event.getEntity()) && Main.getRound() == 1) {
    			pm.playing.remove(event.getEntity());
    			pm.spectators.add(event.getEntity());
				event.getEntity().setGameMode(GameMode.SPECTATOR);
    			Main.getScoreboardManager();
    			ScoreboardOwner.updateScoreboard();
    			if(pm.playing.contains(event.getEntity().getKiller())) {
    				Main.getPointManager().addPoints(event.getEntity().getKiller(), 500);
    				event.getEntity().getKiller().sendMessage("There are " + pm.playing.size() + " players left.");
    				if(pm.playing.size() == 1) {
        				Main.getGameManager().setGameState(GameState.ENDGAME);
        				event.getEntity().getKiller().sendMessage(ChatColor.GOLD + "You have won!");
        				Bukkit.broadcastMessage(ChatColor.GOLD + "" + event.getEntity().getKiller() + " has won!");
        				Main.getPointManager().addPoints(event.getEntity().getKiller(), 1000);
        				Bukkit.getScheduler().cancelTasks(Main.getInstance());
        				Bukkit.broadcastMessage(ChatColor.GREEN + event.getEntity().getKiller().getName() + " has " + Main.getPointManager().getPoints(event.getEntity().getKiller()) + " points");
        				for(Player player : pm.spectators) {
        					Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
        				}
    				}
    			}
    			else {
    				
    			
    			
    			
    			if(pm.playing.size() == 1) {
    				Main.getGameManager().setGameState(GameState.ENDGAME);
    				Bukkit.getScheduler().cancelTasks(Main.getInstance());
    				Bukkit.broadcastMessage(ChatColor.GREEN + "" + event.getEntity() + " has " + Main.getPointManager().getPoints(event.getEntity()) + " points");
    				Main.getPointManager().addPoints(event.getEntity(), 500);
    				for(Player player : pm.spectators) {
    					Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
    					}
    				}
    			}
    		}
    	}
    }