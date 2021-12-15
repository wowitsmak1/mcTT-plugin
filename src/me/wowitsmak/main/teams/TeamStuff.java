package me.wowitsmak.main.teams;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;

public class TeamStuff {
    private HashMap<String,HashMap<Player,Integer>> teams = new HashMap<>();
    public TeamStuff(){
        
    }
    public void addTeamPlayers(){
        for(int i=1;i<26;i++){
            teams.put("team"+i, new HashMap<Player, Integer>());
        }
        teams.get("team1").put(Bukkit.getPlayer("TIKTOKGIRLSIENNA"), Main.getPointManager().getPoints(Bukkit.getPlayer("TIKTOKGIRLSIENNA")));
        teams.get("team1").put(Bukkit.getPlayer("IAmQuan"), Main.getPointManager().getPoints(Bukkit.getPlayer("IAmQuan")));
        teams.get("team2").put(Bukkit.getPlayer("ItsThePulse"), Main.getPointManager().getPoints(Bukkit.getPlayer("ItsThePulse")));
        teams.get("team2").put(Bukkit.getPlayer("NeilisHere"), Main.getPointManager().getPoints(Bukkit.getPlayer("NeilisHere")));
        teams.get("team3").put(Bukkit.getPlayer("Mind0fSneax"), Main.getPointManager().getPoints(Bukkit.getPlayer("Mind0fSneax")));
        teams.get("team3").put(Bukkit.getPlayer("ADMCrunch"), Main.getPointManager().getPoints(Bukkit.getPlayer("ADMCrunch")));
        teams.get("team4").put(Bukkit.getPlayer("Blu_xd"), Main.getPointManager().getPoints(Bukkit.getPlayer("Blu_xd")));
        teams.get("team4").put(Bukkit.getPlayer("heyitskinzyy"), Main.getPointManager().getPoints(Bukkit.getPlayer("heyitskinzyy")));
        teams.get("team5").put(Bukkit.getPlayer("Turamisu"), Main.getPointManager().getPoints(Bukkit.getPlayer("Turamisu")));
        teams.get("team6").put(Bukkit.getPlayer("iD13D"), Main.getPointManager().getPoints(Bukkit.getPlayer("iD13D")));
        teams.get("team6").put(Bukkit.getPlayer("okToasty"), Main.getPointManager().getPoints(Bukkit.getPlayer("okToasty")));
        teams.get("team7").put(Bukkit.getPlayer("Acewoman"), Main.getPointManager().getPoints(Bukkit.getPlayer("Acewoman")));
        teams.get("team7").put(Bukkit.getPlayer("xxmightymuffinxx"), Main.getPointManager().getPoints(Bukkit.getPlayer("xxmightymuffinxx")));
        teams.get("team8").put(Bukkit.getPlayer("xanderinabin"), Main.getPointManager().getPoints(Bukkit.getPlayer("xanderinabin")));
        teams.get("team8").put(Bukkit.getPlayer("TheTooken"), Main.getPointManager().getPoints(Bukkit.getPlayer("TheTooken")));
        teams.get("team9").put(Bukkit.getPlayer("Pupey"), Main.getPointManager().getPoints(Bukkit.getPlayer("Pupey")));
        teams.get("team9").put(Bukkit.getPlayer("tanman5123"), Main.getPointManager().getPoints(Bukkit.getPlayer("tanman5123")));
        teams.get("team10").put(Bukkit.getPlayer("TeamKalal"), Main.getPointManager().getPoints(Bukkit.getPlayer("TeamKalal")));
        teams.get("team10").put(Bukkit.getPlayer("Kanolinn"), Main.getPointManager().getPoints(Bukkit.getPlayer("Kanolinn")));
        teams.get("team11").put(Bukkit.getPlayer("Fenzir"), Main.getPointManager().getPoints(Bukkit.getPlayer("Fenzir")));
        teams.get("team11").put(Bukkit.getPlayer("Novacity"), Main.getPointManager().getPoints(Bukkit.getPlayer("Novacity")));
        teams.get("team25").put(Bukkit.getPlayer("wowitsmak"), Main.getPointManager().getPoints(Bukkit.getPlayer("wowitsmak")));
    }    
    public HashMap<Player, Integer> getPlayerTeam(Player player){
        Integer i = 1;
        while(true){
            if(i < 25){
            if(teams.get("team"+i).containsKey(player)){
                updateTeamPoints(teams.get("team"+i));
                return teams.get("team"+i);
            }
            else{
                i = i + 1;
            }
        }
        else{
            return null;
        }
        }
    }
    public HashMap<Player, Integer> getTeam(String str){
        updateTeamPoints(teams.get(str));
        return teams.get(str);
    }
    public HashMap<Player, Integer> getTeam(HashMap<Player, Integer> hashMap){
        updateTeamPoints(hashMap);
        return hashMap;
    }
    public void updateTeamPoints(HashMap<Player, Integer> team){
        for(Player player : team.keySet()){
			team.replace(player, Main.getPointManager().getPoints(player));
        }
    }
}


