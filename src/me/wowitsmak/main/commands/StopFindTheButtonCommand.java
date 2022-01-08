package me.wowitsmak.main.commands;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.managers.GameState;

public class StopFindTheButtonCommand implements CommandExecutor {
    
    @Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.isOp())
        Main.setRound(2);
		Main.getGameManager().setGameState(GameState.ENDGAME);
        Main.setTime(60);
        Main.getPlayerManager().resetButtonMap();
        for(Iterator<Player> iterator = Main.getPlayerManager().playing.iterator(); iterator.hasNext();) {
            Player player = iterator.next();
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
            Main.getPlayerManager().playing.remove(player);
        }
        for(Player player : Main.getPlayerManager().spectators) {
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
            
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!player.isOp()){
                Main.getPlayerManager().spectators.add(player);    
            }
            player.setSaturation(20);
			player.setHealth(20);
            player.setWalkSpeed(0.2F);
			for (PotionEffect effect : player.getActivePotionEffects())
			    player.removePotionEffect(effect.getType());
        }
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
        
		return false;
	}
}
