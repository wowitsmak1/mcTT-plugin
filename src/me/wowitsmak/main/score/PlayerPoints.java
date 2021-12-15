package me.wowitsmak.main.score;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;

public class PlayerPoints {

	public PlayerPoints() {
		for(OfflinePlayer player : Bukkit.getWhitelistedPlayers()) {
			addPlayer(player.getPlayer());
		}
	}
	private static Map<Player, Integer> playerPoints = new HashMap<>();
	
	public void addPoints(Player player, Integer num) {
		playerPoints.put(player, playerPoints.get(player)+num);
		ScoreboardOwner.updateScoreboard();
	}
	public void removePoints(Player player, Integer num) {
		playerPoints.put(player, playerPoints.get(player)-num);
		ScoreboardOwner.updateScoreboard();
	}
	public Integer getPoints(Player player) {
		return playerPoints.get(player);
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
		if(playerPoints.containsKey(player)) {}
		else {
			playerPoints.putIfAbsent(player, 0);
		}
	}
}

