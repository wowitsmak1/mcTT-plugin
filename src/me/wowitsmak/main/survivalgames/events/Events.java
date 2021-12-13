package me.wowitsmak.main.survivalgames.events;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.wowitsmak.main.score.PlayerPoints;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import me.wowitsmak.main.survivalgames.managers.GameManager;

public class Events extends JavaPlugin implements Listener{

	@EventHandler
	private void onBlockBreak(BlockBreakEvent event) {
		//if(!gameManager.getBlockManager().canBreak(event.getBlock())) {
		//	event.setCancelled(true);
		//}
	}
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        PlayerPoints.addPlayer(e.getPlayer());
        ScoreboardOwner.createScoreboard(e.getPlayer());
        ScoreboardOwner.updateScoreboard();     
        
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
    	ScoreboardOwner.updateScoreboard();
    }
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
    	if(event.getWorld() == Bukkit.getWorld("cove"))
            for(BlockState block : event.getChunk().getTileEntities()){
                if(block instanceof Chest){
                 Chest chest = (Chest) block;
                 setupLoot(chest.getInventory());
                }
            }
        }
    FileConfiguration config = getConfig();
	List<String> commonItems = config.getStringList("CommonItems");
	List<String> rareItems = config.getStringList("RareItems");
	List<String> legendaryItems = config.getStringList("LegendaryItems");
	
	public void setupLoot(Inventory inv) {
		spinItem(inv);
		spinItem(inv);
		spinItem(inv);
		spinItem(inv);
		spinItem(inv);
	}
	private void spinItem(Inventory inv) {
		Random rand = new Random();
		int rarity = rand.nextInt(20);
		if(rarity <= 10) {
			ItemStack commonItem = new ItemStack(Material.getMaterial(commonItems.get(rand.nextInt(commonItems.size())).toUpperCase())); 
			inv.setItem(getEmptyItemSlot(inv), commonItem);
		}
		else if (rarity <= 17) {
			Material mat = Material.getMaterial(rareItems.get(rand.nextInt(rareItems.size())).toUpperCase());
			if(mat.equals(Material.COOKED_BEEF)){
				ItemStack rareItem = new ItemStack(mat, 16); 
				inv.setItem(getEmptyItemSlot(inv), rareItem);
			}
			else {
				ItemStack rareItem = new ItemStack(mat); 
				inv.setItem(getEmptyItemSlot(inv), rareItem);
			}

		}
		else {
			ItemStack legendaryItem = new ItemStack(Material.getMaterial(legendaryItems.get(rand.nextInt(legendaryItems.size())).toUpperCase())); 
			 final String typeNameString = legendaryItem.getType().name();
		        if (typeNameString.endsWith("_HELMET")
		                || typeNameString.endsWith("_CHESTPLATE")
		                || typeNameString.endsWith("_LEGGINGS")
		                || typeNameString.endsWith("_BOOTS")) {
		        	legendaryItem.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, rand.nextInt(4));
		            }
			inv.setItem(getEmptyItemSlot(inv), legendaryItem);
		}
	}
	private int getEmptyItemSlot(Inventory inventory){
		Random random = new Random();
		List<Integer> list = new ArrayList<>(36);
		for (int i = 0; i < 36; i++) {
		    ItemStack item = inventory.getItem(i);
		    if (item == null || item.getType() == Material.AIR) {
		        list.add(i);
		    }
		}
		return list.isEmpty() ? -1 : list.get(random.nextInt(list.size()));
	}
    }
