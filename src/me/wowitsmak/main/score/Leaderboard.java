package me.wowitsmak.main.score;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;

public class Leaderboard {
    private HashMap<HashMap<Player,Integer>,Integer> leaderboard = new HashMap<>();
    public Leaderboard(){
        
    }
    public HashMap<HashMap<Player,Integer>,Integer> getLeaderboard() {
        return leaderboard;
    }
    public Integer getTeamPosition(HashMap<Player, Integer> team){
        return leaderboard.get(team);
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
            leaderboard.put(team, place);
        }
    }
}
