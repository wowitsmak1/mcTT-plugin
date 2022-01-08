package me.wowitsmak.main.managers;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import net.md_5.bungee.api.ChatColor;

public class HungerGamesStart {
	PlayerManager pm = Main.getPlayerManager();
	public Integer time = 60;
	public Integer cooldown = 100;
	HungerGamesLootTable hg;
	public HungerGamesStart() {
		this.pm = Main.getPlayerManager();
		this.hg = new HungerGamesLootTable();
	}
	public void start() {
		Main.getGameManager().setGameState(GameState.PREPARING);
		Main.setRound(1);
		Bukkit.getScheduler().cancelTasks(Main.getInstance());
		for(Player player : Main.getPlayerManager().getParticipantsSet()) {
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
			player.setGameMode(GameMode.SURVIVAL);
			Main.getHungerGamesWorld().start("survivalmap2");
			pm.teleportPlayers(Bukkit.getWorld("survivalmap2"));
		}
		Main.getScoreboardManager();
		ScoreboardOwner.updateScoreboard();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@Override
		    public void run() {
				if(time == 0) {
					cooldown = 100;
					Main.getGameManager().setGameState(GameState.ACTIVE);
					Bukkit.getScheduler().cancelTasks(Main.getInstance());
					for(Player player : pm.playing) {
			    		player.setWalkSpeed(0.2F);
			    		for (PotionEffect effect : player.getActivePotionEffects())
			    	        player.removePotionEffect(effect.getType());
							player.sendTitle(ChatColor.GREEN + "The game has started!", ChatColor.YELLOW + "Let's go champ!", 1, 20, 1);
			    		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
							@Override
							public void run() {
								   if(cooldown == 0){
									   Bukkit.getServer().broadcastMessage(ChatColor.RED + "Cooldown is now off.");
									   cooldown = cooldown - 1;
								   }
								   else if (cooldown == -1){}
								   else{
									   cooldown = cooldown - 1;
								   }
							}
							
						}, 0L, 20);
			    		
			    	}
					time = 60; 
					
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
		    		    
		    		}, 2000L, 2000);
					
					
				}
				else {
					Main.getScoreboardManager();
					ScoreboardOwner.updateScoreboard();
					for(Player player : pm.playing) {
			    		player.sendTitle(ChatColor.GOLD + "Only " + time.toString() + " left!", ChatColor.YELLOW + "Be ready!", 1, 20, 1);
			    	} 
					time = time - 1;
				}
		    	   
		    }
		    
		}, 0L, 20);
	
	}
}
