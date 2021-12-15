package me.wowitsmak.main.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import net.md_5.bungee.api.ChatColor;

public class AddToTeam implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.isOp()){
        String team = args[1].toString();
        Player player = Bukkit.getPlayerExact(args[0].toString());
        if(!Main.getTeamManager().getTeam(team).containsKey(player)) {
        	Main.getTeamManager().getTeam(team).put(player, Main.getPointManager().getPoints(player));
        	sender.sendMessage(ChatColor.GREEN + "Added "+ player.getName() + " to the team!");	
        	Main.getScoreboardManager();
        	ScoreboardOwner.createScoreboard(player);
			ScoreboardOwner.updateScoreboard();
        }
        else {
        	sender.sendMessage(ChatColor.RED + "The player is already on this team!");
        }
        
	}
        return false;
	
    }
}
