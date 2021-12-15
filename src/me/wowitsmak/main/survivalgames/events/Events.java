package me.wowitsmak.main.survivalgames.events;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import me.wowitsmak.main.survivalgames.managers.GameState;
import me.wowitsmak.main.survivalgames.managers.PlayerManager;
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
        ScoreboardOwner.createScoreboard(e.getPlayer());
        ScoreboardOwner.updateScoreboard();     
        if(Bukkit.getWorld("cove") == null) {
        	new WorldCreator("cove").createWorld();
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
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
            ScoreboardOwner.updateScoreboard();
            if (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ() || from.getBlockY() != to.getBlockY()) {
                event.setTo(from);
            }
    	}
    }
    @EventHandler
    public void onPlayerMurder(PlayerDeathEvent event) {
    		if(pm.playing.contains(event.getEntity()) && pm.playing.contains(event.getEntity().getKiller()) && Main.getRound() == 1) {
    			Main.getPointManager().addPoints(event.getEntity().getKiller(), 500);
    			pm.playing.remove(event.getEntity());
    			pm.spectators.add(event.getEntity());
    			ScoreboardOwner.updateScoreboard();
    			event.getEntity().getKiller().sendMessage("There are " + pm.playing.size() + " players left.");
    			if(pm.playing.size() == 1) {
    				Main.getGameManager().setGameState(GameState.ENDGAME);
    				event.getEntity().getKiller().sendMessage(ChatColor.GOLD + "You have won!");
    				Bukkit.getScheduler().cancelTasks(Main.getInstance());
    				Bukkit.broadcastMessage(ChatColor.GREEN + event.getEntity().getKiller().getName() + " has " + Main.getPointManager().getPoints(event.getEntity().getKiller()) + " points");
    				for(Player player : pm.spectators) {
    					Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
    				}
    			}
    		}
    	}
    }
