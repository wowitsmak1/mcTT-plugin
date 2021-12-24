package me.wowitsmak.main.loot_tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.wowitsmak.main.Main;


public class HungerGamesLootTable {

	public void setupLoot(Inventory inv) {
		Integer times = ThreadLocalRandom.current().nextInt(2, 5);
		inv.clear();
		for(int i = 0; i < times; i++) {
			spinItem(inv);
		}

	}
	private void spinItem(Inventory inv) {
		FileConfiguration config = Main.getInstance().getConfig();
		List<String> commonItems = config.getStringList("CommonItems");
		List<String> rareItems = config.getStringList("RareItems");
		List<String> legendaryItems = config.getStringList("LegendaryItems");
		Random rand = new Random();
		int rarity = rand.nextInt(20);
		if(rarity <= 15) {
			ItemStack commonItem = new ItemStack(Material.getMaterial(commonItems.get(rand.nextInt(commonItems.size())).toUpperCase())); 
			inv.setItem(getEmptyItemSlot(inv), commonItem);
		}
		else if (rarity <= 19 && rarity > 15) {
			Material mat = Material.getMaterial(rareItems.get(rand.nextInt(rareItems.size())).toUpperCase());
			if(mat.isEdible() && !mat.equals(Material.GOLDEN_APPLE) && !mat.equals(Material.ENCHANTED_GOLDEN_APPLE)){
				ItemStack rareItem = new ItemStack(mat, 16); 
				inv.setItem(getEmptyItemSlot(inv), rareItem);
			}
			else {
				ItemStack rareItem = new ItemStack(mat); 
				final String typeNameString = rareItem.getType().name();
				if(rand.nextInt(10) == 1){
					if (typeNameString.endsWith("_HELMET")
					|| typeNameString.endsWith("_CHESTPLATE")
					|| typeNameString.endsWith("_LEGGINGS")
					|| typeNameString.endsWith("_BOOTS")) {
				rareItem.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, ThreadLocalRandom.current().nextInt(1, 2));
				}
				else if(typeNameString.endsWith("_SWORD")){
					rareItem.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, ThreadLocalRandom.current().nextInt(1, 2));
				}
				}
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
				if(rand.nextInt(5) == 1){
		        	legendaryItem.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, ThreadLocalRandom.current().nextInt(1, 4));
							}
		            }
			inv.setItem(getEmptyItemSlot(inv), legendaryItem);
		}
	}
	private int getEmptyItemSlot(Inventory inventory){
		Random random = new Random();
		List<Integer> list = new ArrayList<>(26);
		for (int i = 0; i < 26; i++) {
		    ItemStack item = inventory.getItem(i);
		    if (item == null || item.getType() == Material.AIR) {
		        list.add(i);
		    }
		}
		return list.isEmpty() ? -1 : list.get(random.nextInt(list.size()));
	}
}
