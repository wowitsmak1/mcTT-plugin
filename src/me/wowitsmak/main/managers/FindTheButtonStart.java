package me.wowitsmak.main.managers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;

public class FindTheButtonStart {
    private Integer time = Main.getTime();
    @SuppressWarnings("deprecation")
    public void Start() {
        Main.setRound(2);
        Main.getGameManager().setGameState(GameState.PREPARING);
        for(Player player : Main.getPlayerManager().getParticipantsSet()){
            if(!Main.getPlayerManager().playing.contains(player)){
                Main.getPlayerManager().playing.add(player);
            }
        }
        if(Bukkit.getWorld("button") == null) {
            new WorldCreator("button").createWorld();
        }
        for(Player player : Main.getPlayerManager().getParticipantsSet()) {
			player.getInventory().clear();
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 100000, true, false));
			player.setWalkSpeed(0F);
			player.setSaturation(20);
			player.setHealth(20);
			player.setGameMode(GameMode.SURVIVAL);
			Main.getPlayerManager().teleportPlayers(Bukkit.getWorld("button"));
		}
        for (Player toHide : Main.getPlayerManager().getParticipantsSet()) {
            for (Player player : Main.getPlayerManager().getParticipantsSet()) {
                if (player != toHide && Main.getTeamManager().getPlayerTeam(player) != Main.getTeamManager().getPlayerTeam(toHide)) {
                      player.hidePlayer(toHide);
                }
            }
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@Override
		    public void run() {
                if(time == 0) {
					Main.getGameManager().setGameState(GameState.ACTIVE);
					Bukkit.getScheduler().cancelTasks(Main.getInstance());
					for(Player player : Main.getPlayerManager().getParticipantsSet()) {
			    		player.sendMessage(ChatColor.GREEN + "Starting...");
			    		player.setWalkSpeed(0.2F);
			    		for (PotionEffect effect : player.getActivePotionEffects())
			    	        player.removePotionEffect(effect.getType());
			    		player.sendMessage(ChatColor.GOLD + "Go!");
			    	}
					time = 60; 
				}
                else {
					Main.getScoreboardManager();
					ScoreboardOwner.updateScoreboard();
					for(Player player : Main.getPlayerManager().getParticipantsSet()) {
			    		player.sendMessage(ChatColor.AQUA + "Only " + time + " seconds left");
			    	} 
					time = time - 1;
				}
		    }
		    
		}, 0L, 20);
    }
}
