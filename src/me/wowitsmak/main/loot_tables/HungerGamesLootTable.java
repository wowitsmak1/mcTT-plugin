package me.wowitsmak.main.loot_tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.wowitsmak.main.Main;


public class HungerGamesLootTable {

	private Main main;
	public HungerGamesLootTable(Main main) {
		this.main = new Main();
	}
	FileConfiguration config = main.getConfig();
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
