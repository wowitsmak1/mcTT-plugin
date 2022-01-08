package me.wowitsmak.main.managers;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;
import net.md_5.bungee.api.ChatColor;

public class FindTheButton {
   public void nextLevel(Player player){
    Main.getPointManager().addPoints(player, 10);
    player.getInventory().clear();
	player.setSaturation(20);
	player.setHealth(20);
	player.setGameMode(GameMode.SURVIVAL);
	Integer level = Main.getPlayerManager().getButtonScoreMap().get(player);
	Main.getPlayerManager().getButtonScoreMap().remove(player);
	Main.getPlayerManager().getButtonScoreMap().put(player, level + 1);
	Main.getPlayerManager().teleportPlayer(player, "button");
    player.getPlayer().sendTitle(ChatColor.GOLD + "Next Level", ChatColor.GREEN + "Good Luck!", 1, 20, 1);
    Main.getPlayerManager().updateButtonScoreMap();
   }

}
