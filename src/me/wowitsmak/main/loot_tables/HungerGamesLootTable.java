package me.wowitsmak.main.loot_tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.plugin.Plugin;

import me.wowitsmak.main.Main;

public class HungerGamesLootTable implements LootTable {

	private Plugin plugin = Main.getPlugin(Main.class);
    private NamespacedKey key = new NamespacedKey(plugin, "hungergamesloottable");
    private static Collection<ItemStack> items = new ArrayList<ItemStack>();

    public Collection<ItemStack> populateLoot(Random random, LootContext context) {

    	random.nextInt(10);
        ItemStack rotten_flesh = new ItemStack(Material.ROTTEN_FLESH, 1);
        ItemStack leather = new ItemStack(Material.LEATHER, 1);

        items.add(rotten_flesh);
        items.add(leather);

        return items;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {
     inventory.setItem(1,new ItemStack(Material.ROTTEN_FLESH, 1));
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
