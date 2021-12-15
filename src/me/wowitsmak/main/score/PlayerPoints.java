package me.wowitsmak.main.score;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;
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
	public Integer getTeamPoints(String str){
		Integer teampoints = 0; 
		for(Player player : Main.getTeamManager().getTeam(str).keySet()){
			teampoints = teampoints + Main.getTeamManager().getTeam(str).get(player);
		}
		return teampoints;
	}
	public Integer getTeamPoints(HashMap<Player, Integer> hashMap){
		Integer teampoints = 0; 
		for(Player player : Main.getTeamManager().getTeam(hashMap).keySet()){
			teampoints = teampoints + Main.getTeamManager().getTeam(hashMap).get(player);
		}
		return teampoints;
	}
	public void addPlayer(Player player) {
		if(playerPoints.containsKey(player.getUniqueId())) {}
		else {
			playerPoints.putIfAbsent(player.getUniqueId(), 0);
		}
	}
}

