package me.wowitsmak.main.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wowitsmak.main.score.PlayerPoints;
public class SubtractPoint implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		Player player = Bukkit.getPlayer(sender.getName());
		PlayerPoints.removePoints(player, 1);
		return false;
	}

	
}
