package me.wowitsmak.main.survivalgames.events;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.loot.Lootable;

import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.score.PlayerPoints;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import me.wowitsmak.main.survivalgames.managers.GameManager;

public class Events implements Listener {

	private GameManager gameManager;
	public Events(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	@EventHandler
	private void onBlockBreak(BlockBreakEvent event) {
		if(!gameManager.getBlockManager().canBreak(event.getBlock())) {
			event.setCancelled(true);
		}
	}
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        PlayerPoints.addPlayer(e.getPlayer());
        ScoreboardOwner.createScoreboard(e.getPlayer());
        ScoreboardOwner.updateScoreboard();     
        
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
    	ScoreboardOwner.updateScoreboard();
    }
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
    	if(event.getWorld() != Bukkit.getWorld("world"))
            for(BlockState block : event.getChunk().getTileEntities()){
                if(block instanceof Chest){
                 Chest chest = (Chest) block;
                 if(chest instanceof Lootable) {
                	 Lootable lootable = (Lootable) chest;
                	 chest.getInventory().clear();
                	 lootable.setLootTable(new HungerGamesLootTable());
                 }
                }
            }
        }
    }
