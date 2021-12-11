package me.wowitsmak.main.survivalgames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
		return false;
	}

	
}
