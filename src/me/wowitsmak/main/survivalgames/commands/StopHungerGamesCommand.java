package me.wowitsmak.main.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.survivalgames.managers.GameState;

public class StopHungerGamesCommand implements CommandExecutor {
    
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.isOp())
		Main.getGameManager().setGameState(GameState.ENDGAME);
		Main.setRound(1);
        for(Player player : Main.getPlayerManager().playing) {
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
            Main.getPlayerManager().playing.remove(player);
        }
        for(Player player : Main.getPlayerManager().spectators) {
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
            
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!player.isOp()){
                Main.getPlayerManager().spectators.add(player);
                player.setSaturation(20);
			    player.setHealth(20);
                for (PotionEffect effect : player.getActivePotionEffects())
			        player.removePotionEffect(effect.getType());
            }
        }
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
        Main.setTime(5);
		return false;
	}
}
