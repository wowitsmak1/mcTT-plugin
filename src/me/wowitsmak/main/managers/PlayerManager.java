package me.wowitsmak.main.managers;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import me.wowitsmak.main.Main;
import net.md_5.bungee.api.ChatColor;
public class PlayerManager {
	public Set<Player> moderators = new HashSet<>();
	public Set<Player> spectators = new HashSet<>();
	public Set<Player> playing = new HashSet<>();
	public Map<OfflinePlayer, Integer> offlineplayers = new HashMap<>();
	private Set<Player> participants = new HashSet<>();
	private Map<Player, Integer> buttonScoreMap = new HashMap<>();
	private Map<Player, Integer> buttonLeaderboard = new HashMap<>();
	//private static Map<Player, Integer> playerButtonTotal = new HashMap<>();
	public PlayerManager() {
		//for (OfflinePlayer player : Bukkit.getServer().getOperators()) {
    	//	moderators.add(player.getPlayer());
    	//}
		for(OfflinePlayer player : Bukkit.getWhitelistedPlayers()){
				participants.add(player.getPlayer());
		}
		for(Player player : participants){
			if(!buttonScoreMap.containsKey(player)){
				buttonScoreMap.put(player, 0);
			}
		}
	}
	public Set<Player> getParticipantsSet(){
		updateParticipantsList();
		return participants;
	}
	public Map<Player, Integer> getButtonScoreMap(){
		return buttonScoreMap;
	}
	public void resetButtonMap(){
		for(Player player : buttonScoreMap.keySet()){
			buttonScoreMap.remove(player);
		}
		for(Player player : participants){
			if(!buttonScoreMap.containsKey(player)){
				buttonScoreMap.put(player, 0);
			}
		}
	}
	public void updateButtonScoreMap() {
		for(Player player : participants){
			if(!buttonScoreMap.containsKey(player)){
				buttonScoreMap.put(player, 0);
			}
		}
	}
	public Map<Player, Integer> getButtonLeaderboard(){
		return buttonLeaderboard;
	}
	private void updateParticipantsList(){
		for(Player player : Bukkit.getOnlinePlayers()){
			if(!participants.contains(player)){
				participants.add(player);
			}
		}
	}
	public Collection<? extends Player> onlinePlayerList = Bukkit.getServer().getOnlinePlayers();
	public void teleportPlayer(Player player, String str){
		if(str == "button"){
			Main.getHungerGamesWorld().start("button");
			player.teleport(Main.getHungerGamesWorld().locations.get(Main.getPlayerManager().getButtonScoreMap().get(player)));
		}
		else if(str == "survivalmap2"){
			player.teleport(Main.getHungerGamesWorld().locations.get(participants.size() + 1));
		}
		else{
			player.sendMessage(ChatColor.RED + "error");
		}
	}
	public void teleportPlayers(World world)
	{
	Integer i = 0;
	for(Player player : Bukkit.getOnlinePlayers())
	if(Main.getPlayerManager().getParticipantsSet().contains(player))
	{
	if(Main.getHungerGamesWorld().locations.get(i).getWorld().getName() == "button"){
		player.teleport(Main.getHungerGamesWorld().locations.get(Main.getPlayerManager().getButtonScoreMap().get(player)));
	}
	else{
	player.teleport(Main.getHungerGamesWorld().locations.get(i));
	i = i + 1;
	}
	}
	}
}