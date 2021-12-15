package me.wowitsmak.main.managers;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockManager {
	
	public BlockManager(GameManager gameManager) {
		
		allowedToBreak.add(Material.OAK_LOG);
		allowedToBreak.add(Material.GRASS);
		allowedToBreak.add(Material.TALL_GRASS);
		allowedToBreak.add(Material.OAK_LOG);
	}
	private Set<Material> allowedToBreak = new HashSet<>();
	
	public boolean canBreak(Block block) { return allowedToBreak.contains(block.getType()); }
}
