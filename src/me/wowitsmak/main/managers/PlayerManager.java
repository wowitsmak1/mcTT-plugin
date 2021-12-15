package me.wowitsmak.main.managers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {
	public ArrayList<Location> locations = new ArrayList<Location>();
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
	public void giveItems() {
		for (Player player : onlinePlayerList) {
			giveKit(player);
		}
	}
	public void giveKit(Player player) {
		player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
	}
	public void teleportPlayers(World world)
	{
	if(world.getName() == "cove"){
		
	}
	else if (world.getName() == "cavern"){

	}
	
	Integer i = 0;
	 for(Player player : playing)
	 {
	 player.teleport(locations.get(i));
	 i = i + 1;
	 }
	}
}