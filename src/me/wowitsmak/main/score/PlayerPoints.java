package me.wowitsmak.main.score;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;

public class PlayerPoints {

	private static Map<UUID, Integer> playerPoints = new HashMap<>();
	{
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(playerPoints.containsKey(player.getUniqueId())) {}
			else {
				playerPoints.putIfAbsent(player.getUniqueId(), 0);
			}
		}
	}
	
	public void addPoints(Player player, Integer num) {
		playerPoints.put(player.getUniqueId(), playerPoints.get(player.getUniqueId())+num);
		ScoreboardOwner.updateScoreboard();
	}
	public void removePoints(Player player, Integer num) {
		playerPoints.put(player.getUniqueId(), playerPoints.get(player.getUniqueId())-num);
		ScoreboardOwner.updateScoreboard();
	}
	public Integer getPoints(Player player) {
		return playerPoints.get(player.getUniqueId());
	}
	public void addPlayer(Player player) {
		if(playerPoints.containsKey(player.getUniqueId())) {}
		else {
			playerPoints.putIfAbsent(player.getUniqueId(), 0);
		}
	}
}

