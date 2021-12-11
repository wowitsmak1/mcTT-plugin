package me.wowitsmak.main.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wowitsmak.main.survivalgames.managers.GameManager;
import me.wowitsmak.main.survivalgames.managers.GameState;

public class StartCommand implements CommandExecutor {

	private GameManager gameManager;
	
	public StartCommand(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		gameManager.setGameState(GameState.STARTING);
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.teleport(new Location(Bukkit.getWorld("cove"), Bukkit.getWorld("cove").getSpawnLocation().getX(), Bukkit.getWorld("cove").getSpawnLocation().getY(), Bukkit.getWorld("cove").getSpawnLocation().getZ()));
			
		}
		return false;
	}

	
}
