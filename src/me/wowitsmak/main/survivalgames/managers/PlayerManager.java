package me.wowitsmak.main.survivalgames.managers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {

	public Set<UUID> moderators = new HashSet<UUID>();
	public PlayerManager(GameManager gameManager) {
		for (OfflinePlayer player : Bukkit.getServer().getOperators()) {
    		moderators.add(player.getUniqueId());
    	}
	}
	Collection<? extends Player> onlinePlayerList = Bukkit.getServer().getOnlinePlayers();
	public void giveItems() {
		for (Player player : onlinePlayerList) {
			if(!player.getGameMode().equals(GameMode.SURVIVAL)) return;
			player.sendMessage(player.getAddress().toString());
			giveKit(player);
		}
	}
	public void giveKit(Player player) {
		player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
	}
}