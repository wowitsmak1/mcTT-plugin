package me.wowitsmak.main.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.wowitsmak.main.score.PlayerPoints;

public class ScoreboardOwner {

	@SuppressWarnings("deprecation")
    public static void createScoreboard(Player player){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("Stats", "dummy");
        objective.setDisplayName("Stats");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = objective.getScore("Points:");
        score.setScore(PlayerPoints.getPoints(player));
        player.setScoreboard(board);
    }
    
    public static void updateScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            Score score = online.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("Points:");
            score.setScore(PlayerPoints.getPoints(online));
        }
    }
}
