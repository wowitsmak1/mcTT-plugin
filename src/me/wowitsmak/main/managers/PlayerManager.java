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
public class PlayerManager {
	public Set<Player> moderators = new HashSet<>();
	public Set<Player> spectators = new HashSet<>();
	public Set<Player> playing = new HashSet<>();
	public Map<OfflinePlayer, Integer> offlineplayers = new HashMap<>();
	public Set<Player> participants = new HashSet<>();
	//private static Map<Player, Integer> playerButtonTotal = new HashMap<>();
	public PlayerManager() {
		for (OfflinePlayer player : Bukkit.getServer().getOperators()) {
    		moderators.add(player.getPlayer());
    	}
		for(OfflinePlayer player : Bukkit.getWhitelistedPlayers()){
			if(!player.isOp()){
				participants.add(player.getPlayer());
			}
		}
	}
	public Set<Player> getParticipantsSet(){
		updateParticipantsList();
		return participants;
	}
	private void updateParticipantsList(){
		for(Player player : Bukkit.getOnlinePlayers()){
			if(player.isWhitelisted() && !player.isOp() && !participants.contains(player)){
				participants.add(player);
			}
		}
	}
	public Collection<? extends Player> onlinePlayerList = Bukkit.getServer().getOnlinePlayers();
	public void teleportPlayers(World world)
	{
	Integer i = 0;
	for(Player player : playing)
	{
	player.teleport(Main.getHungerGamesWorld().locations.get(i));
	i = i + 1;
	}
	}
}