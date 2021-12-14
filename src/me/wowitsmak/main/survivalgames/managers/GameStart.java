package me.wowitsmak.main.survivalgames.managers;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import net.md_5.bungee.api.ChatColor;

public class GameStart {
	PlayerManager pm = Main.getPlayerManager();
	Integer time = 5;
	HungerGamesLootTable hg;
	public GameStart() {
		this.pm = Main.getPlayerManager();
		this.hg = new HungerGamesLootTable();
	}
	public void start() {
	for(Chunk chunk : Bukkit.getWorld("cove").getLoadedChunks()) {
		for(BlockState block : chunk.getTileEntities()){
            if(block instanceof Chest){
             Chest chest = (Chest) block;
             hg.setupLoot(chest.getInventory());
            }
		
		}
		for(Entity current : Bukkit.getWorld("cove").getEntities()){//loop through the list
            if (current instanceof Item){//make sure we aren't deleting mobs/players
            current.remove();//remove it
            }
		}
		}
		Bukkit.getScheduler().cancelTasks(Main.getInstance());
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(!pm.playing.contains(player)) {
				pm.playing.add(player);
			}
			if(pm.spectators.contains(player)) {
				pm.spectators.remove(player);
			}
			player.getInventory().clear();
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 100000, true, false));
			player.setWalkSpeed(0F);
			player.setSaturation(20);
			player.setHealth(20);
			pm.teleportPlayers();
		}
		ScoreboardOwner.updateScoreboard();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@Override
		    public void run() {
				if(time == 0) {
					Main.getGameManager().setGameState(GameState.ACTIVE);
					for(Player player : pm.playing) {
			    		player.sendMessage(ChatColor.GREEN + "Starting...");
			    		player.setWalkSpeed(0.2F);
			    		for (PotionEffect effect : player.getActivePotionEffects())
			    	        player.removePotionEffect(effect.getType());
			    		player.sendMessage(ChatColor.GREEN + "Your current game state is " + ChatColor.GOLD + Main.getGameManager().getGameState());
			    		
			    		
			    	}
					time = 5; 
					Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
		    			@Override
		    		    public void run() {
		    				for(Chunk chunk : Bukkit.getWorld("cove").getLoadedChunks()) {
		    					for(BlockState block : chunk.getTileEntities()){
		    			            if(block instanceof Chest){
		    			             Chest chest = (Chest) block;
		    			             hg.setupLoot(chest.getInventory());
		    			            }
		    					}
		    					
		    				}
		    				Bukkit.broadcastMessage(ChatColor.GREEN + "Chests refilled.");
		    		    }
		    		    
		    		}, 0L, 2000);
					Bukkit.getScheduler().cancelTasks(Main.getInstance());
					
				}
				else {
					for(Player player : pm.playing) {
			    		player.sendMessage(ChatColor.AQUA + "Only " + time + " seconds left");
			    	} 
					time = time - 1;
				}
		    	   
		    }
		    
		}, 0L, 20);
	
	}
}
