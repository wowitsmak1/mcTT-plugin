package me.wowitsmak.main.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;

public class HideOtherPlayers implements CommandExecutor {

    @SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender.isOp()){
            for (Player toHide : Bukkit.getServer().getOnlinePlayers()) {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (player != toHide && Main.getTeamManager().getPlayerTeam(player) != Main.getTeamManager().getPlayerTeam(toHide)) {
                          player.hidePlayer(toHide);
                    }
                }
            }
        }
		return false;
	}

	
}
