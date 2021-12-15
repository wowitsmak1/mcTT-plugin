package me.wowitsmak.main.survivalgames.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.survivalgames.managers.GameState;

public class StartHungerGamesCommand implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.isOp())
		Main.getGameManager().setGameState(GameState.STARTING);
		Main.setRound(1);
		return false;
	}

	
}
