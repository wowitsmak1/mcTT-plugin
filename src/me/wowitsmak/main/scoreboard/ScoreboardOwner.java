package me.wowitsmak.main.scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import me.wowitsmak.main.Main;
import me.wowitsmak.main.managers.GameState;
import net.md_5.bungee.api.ChatColor;

public class ScoreboardOwner {

	@SuppressWarnings("deprecation")
    public static void createScoreboard(Player player){
        Main.getLeaderboard().updateLeaderboard();
		ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("Stats", "dummy");
        objective.setDisplayName(ChatColor.BLUE + "Stats");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = objective.getScore(ChatColor.AQUA+ "Points: ");
        score.setScore(Main.getPointManager().getPoints(player));
        if(Main.getTeamManager().getPlayerTeam(player) != null){
            Score teampoints = objective.getScore(ChatColor.GREEN + "Team Points: ");
            teampoints.setScore(Main.getPointManager().getTeamPoints(Main.getTeamManager().getPlayerTeam(player)));
        }
        
        Score placement = objective.getScore(ChatColor.YELLOW + "Your position: ");
        placement.setScore(Main.getLeaderboard().getPlayerPosition(player));
        Score players = objective.getScore(ChatColor.GOLD +"Players Left: ");
        players.setScore(Main.getPlayerManager().playing.size());
        Score teamplacement = objective.getScore(ChatColor.RED + "Your team's position: ");
        teamplacement.setScore(Main.getLeaderboard().getTeamPosition(Main.getTeamManager().getPlayerTeam(player)));
        
        player.setScoreboard(board);
    }
    
    public static void updateScoreboard(){
        Main.getLeaderboard().updateLeaderboard();
        for(Player player : Bukkit.getOnlinePlayers()){
            Score score = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.AQUA+ "Points: ");
            score.setScore(Main.getPointManager().getPoints(player));
            if(Main.getTeamManager().getPlayerTeam(player) != null){
            Score teampoints = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.GREEN + "Team Points: ");
            teampoints.setScore(Main.getPointManager().getTeamPoints(Main.getTeamManager().getPlayerTeam(player)));
            Score placement = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.YELLOW + "Your position: ");
            placement.setScore(Main.getLeaderboard().getPlayerPosition(player));
            Score teamplacement = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.RED + "Your team's position: ");
            teamplacement.setScore(Main.getLeaderboard().getTeamPosition(Main.getTeamManager().getPlayerTeam(player)));
            Score players = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.GOLD +"Players Left: ");
            players.setScore(Main.getPlayerManager().playing.size());
            }
        }
        if(Main.getGameManager().getGameState() == GameState.ACTIVE) {
        	for(Player player : Bukkit.getOnlinePlayers()){
        		Score players = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.GOLD +"Players Left:");
        		players.setScore(Main.getPlayerManager().playing.size());
        		}
        	}
        }

    }
