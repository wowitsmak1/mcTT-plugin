package me.wowitsmak.main.managers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;

public class FindTheButtonStart {
    private Integer time = Main.getTime();
    @SuppressWarnings("deprecation")
    public void Start() {
    	Main.getPlayerManager().updateButtonScoreMap();
        Main.setRound(2);
        Main.getGameManager().setGameState(GameState.PREPARING);
        for(Player player : Main.getPlayerManager().getParticipantsSet()){
            if(!Main.getPlayerManager().playing.contains(player)){
                Main.getPlayerManager().playing.add(player);
            }
        }
        Main.getHungerGamesWorld().start("button");
        for(Player player : Bukkit.getOnlinePlayers()) {
    			if(!Main.getPlayerManager().playing.contains(player) && Main.getPlayerManager().getParticipantsSet().contains(player)) {
    				Main.getPlayerManager().playing.add(player);
    			}
    			if(Main.getPlayerManager().spectators.contains(player)) {
    				Main.getPlayerManager().spectators.remove(player);
    			}
			player.getInventory().clear();
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 100000, true, false));
			player.setWalkSpeed(0F);
			player.setSaturation(20);
			player.setHealth(20);
			player.setGameMode(GameMode.SURVIVAL);
			Main.getPlayerManager().teleportPlayers(Bukkit.getWorld("button"));
		}
        for (Player toHide : Bukkit.getOnlinePlayers()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
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
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(Main.getPlayerManager().getParticipantsSet().contains(player))
			    		player.setWalkSpeed(0.2F);
			    		for (PotionEffect effect : player.getActivePotionEffects())
			    	        player.removePotionEffect(effect.getType());
							player.sendTitle(ChatColor.GREEN + "The game has started!", ChatColor.YELLOW + "Let's go champ!", 1, 20, 1);
			    	}
					time = 60; 
				}
                else {
					Main.getScoreboardManager();
					ScoreboardOwner.updateScoreboard();
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(Main.getPlayerManager().getParticipantsSet().contains(player))
			    		player.sendTitle(ChatColor.GOLD + "Only " + time.toString() + " left!", ChatColor.YELLOW + "Be ready!", 1, 20, 1);
			    	} 
					time = time - 1;
				}
		    }
		    
		}, 0L, 20);
    }
}
