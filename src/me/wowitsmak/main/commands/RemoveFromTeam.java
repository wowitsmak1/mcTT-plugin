package me.wowitsmak.main.survivalgames.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import net.md_5.bungee.api.ChatColor;

public class RemoveFromTeam implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.isOp()){
        String team = args[1].toString();
        Player player = Bukkit.getPlayerExact(args[0].toString());
		Main.getTeamManager().getTeam(team).remove(player);
        sender.sendMessage(ChatColor.RED + "Removed "+ player.getName() + " from the team.");
        Main.getScoreboardManager();
        ScoreboardOwner.createScoreboard(player);
		ScoreboardOwner.updateScoreboard();	
		}
        return false;
	
    }
}
