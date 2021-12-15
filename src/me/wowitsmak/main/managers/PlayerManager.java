package me.wowitsmak.main.managers;
import java.util.Collection;
import java.util.HashSet;
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
	//private static Map<Player, Integer> playerButtonTotal = new HashMap<>();
	public PlayerManager() {
		for (OfflinePlayer player : Bukkit.getServer().getOperators()) {
    		moderators.add(player.getPlayer());
    	}
	}
	Collection<? extends Player> onlinePlayerList = Bukkit.getServer().getOnlinePlayers();
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