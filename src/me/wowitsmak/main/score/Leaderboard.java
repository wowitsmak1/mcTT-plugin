package me.wowitsmak.main.score;

import java.util.HashMap;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;

public class Leaderboard {
    private HashMap<HashMap<Player,Integer>,Integer> teamleaderboard = new HashMap<>();
    private HashMap<Player,Integer> playerleaderboard = new HashMap<>();
    public Leaderboard(){
        
    }
    public HashMap<HashMap<Player,Integer>,Integer> getTeamLeaderboard() {
        return teamleaderboard;
    }
    public HashMap<Player,Integer> getPlayerLeaderBoard(){
        return playerleaderboard;
    }
    public Integer getPlayerPosition(Player player){
        return playerleaderboard.get(player);
    }
    public Integer getTeamPosition(HashMap<Player, Integer> team){
        return teamleaderboard.get(team);
    }

    public void updateLeaderboard(){
        for(HashMap<Player, Integer> team : Main.getTeamManager().teams.values()){
            Integer place = Main.getTeamManager().teams.size();
            for(int i=1;i<Main.getTeamManager().teams.size();i++){
                if(Main.getPointManager().getTeamPoints(team) > Main.getPointManager().getTeamPoints(Main.getTeamManager().getTeam("team"+i)) && team != Main.getTeamManager().getTeam("team"+i)){
                    place = place - 1;
                }
                else if(Main.getPointManager().getTeamPoints(team) == Main.getPointManager().getTeamPoints(Main.getTeamManager().getTeam("team"+i)) && team != Main.getTeamManager().getTeam("team"+i)){
                    place = place - 1;
                }
            }
            teamleaderboard.put(team, place);
        }
        for(Player player : Main.getPlayerManager().getParticipantsSet()){
            Integer place = Main.getPlayerManager().getParticipantsSet().size() ;
            for(Player opponent : Main.getPlayerManager().getParticipantsSet()){
                if(Main.getPointManager().getPoints(player) > Main.getPointManager().getPoints(opponent) && player != opponent){
                    place = place - 1;
                }
                else if(Main.getPointManager().getPoints(player) == Main.getPointManager().getPoints(opponent) && player != opponent){
                    place = place - 1;
                }
                else {

                }
            }
            playerleaderboard.put(player, place);
        }
    }
}
