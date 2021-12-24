package me.wowitsmak.main.worlds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import me.wowitsmak.main.loot_tables.HungerGamesLootTable;

public class ReadyWorld {
    public ArrayList<Location> locations = new ArrayList<Location>();
    HungerGamesLootTable hg;
	public ReadyWorld() {
		this.hg = new HungerGamesLootTable();
	}
    public void start(String str){
    if(Bukkit.getWorld("survivalmap1") == null) {
    	new WorldCreator("survivalmap1").createWorld();
    }
	else if(Bukkit.getWorld("survivalmap2") == null) {
    	new WorldCreator("survivalmap2").createWorld();
    }
	else if(Bukkit.getWorld("survivalmap3") == null) {
    	new WorldCreator("survivalmap3").createWorld();
    }
	else if(Bukkit.getWorld("survivalmap4") == null) {
    	new WorldCreator("survivalmap4").createWorld();
    }
    else if(Bukkit.getWorld(str) == null){
        new WorldCreator(str).createWorld();
    }
    World world = Bukkit.getWorld(str);
    
    if(str == "survivalmap2"){
        locations.clear();
        locations.add(new Location(world, 5.5, 67, 46.5,-152, 0));
		locations.add(new Location(world, 16.5, 67, 49.5, 180, 0));
        locations.add(new Location(world, 19.5, 67, 48.5, 175, 0));
        locations.add(new Location(world, 22.5, 67, 48.5, -917, 0));
        locations.add(new Location(world, 25.5, 67, 47.50, -928, 0));
        locations.add(new Location(world, 30.5, 67, 45.5, 142, 0));
        locations.add(new Location(world, 32.5, 67, 42.5, 134, 0));
        locations.add(new Location(world, 34.5, 67, 40.5, 125, 0));
        locations.add(new Location(world, 39.5, 67, 41.5, 124, 0));
        locations.add(new Location(world, 36.5, 67, 37.5, 121, 0));
        locations.add(new Location(world, 39.5, 67, 35.5, 115, 0));
        locations.add(new Location(world, 38.5, 67, 32.5, 105, 0));
        locations.add(new Location(world, 39.5, 67, 29.5, -260, 0));
        locations.add(new Location(world, 39.5, 67, 26.5, -269, 0));
        locations.add(new Location(world, 40.5, 67, 23.5, -276, 0));
        locations.add(new Location(world, 38.5, 67, 20.5, -287, 0));
        locations.add(new Location(world, 38.5, 67, 17.5, -293, 0));
        locations.add(new Location(world, 36.5, 67, 15.5, -297, 0));
        locations.add(new Location(world, 34.5, 67, 12.5, -308, 0));
        locations.add(new Location(world, 32.5, 67, 10.5, -315, 0));
        locations.add(new Location(world, 30.5, 67, 8.5, -320, 0));
        locations.add(new Location(world, 27.5, 67, 6.5, -331, 0));
        locations.add(new Location(world, 24.5, 67, 6.5, -335, 0));
        locations.add(new Location(world, 22.5, 67, 4.5, -342, 0));
        locations.add(new Location(world, 19.5, 67, 4.5, 4, 0));
        locations.add(new Location(world, 16.5, 67, 3.5, 0, 0));
        locations.add(new Location(world, 13.5, 67, 1.5, -9, 0));
        locations.add(new Location(world, 10.5, 67, 4.5, -17, 0));
        locations.add(new Location(world, 8.5, 67, 6.5, -27, 0));
        locations.add(new Location(world, 5.5, 67, 6.5, -30, 0));
        locations.add(new Location(world, 2.5, 67, 8.5, -36, 0));
        locations.add(new Location(world, 0.5, 67, 10.5, -44, 0));
        locations.add(new Location(world, -1.5, 67, 12.5, -53, 0));
        locations.add(new Location(world, -3.5, 67, 15.5, -61, 0));
        locations.add(new Location(world, -4.5, 67, 18.5, -67, 0));
        locations.add(new Location(world, -5.5, 67, 20.5, -70, 0));
        locations.add(new Location(world, -6.5, 67, 23.5, -80, 0));
        locations.add(new Location(world, -6.5, 67, 26.5, -90, 0));
        locations.add(new Location(world, -6.5, 67, 29.5, -100, 0));
        locations.add(new Location(world, -5.5, 67, 32.5, -107, 0));
        locations.add(new Location(world, -3.5, 67, 34.5, -113, 0));
        locations.add(new Location(world, -3.5, 67, 37.5, -122, 0));
        locations.add(new Location(world, -1.5, 67, 39.5, 233, 0));
        locations.add(new Location(world, 0.5, 67, 42.5, 225, 0));
        locations.add(new Location(world, 2.5, 67, 44.5, 223, 0));
        locations.add(new Location(world, 8.5, 67, 46.5, 203, 0));
        locations.add(new Location(world, 10.5, 67, 48.5, 194, 0));
        locations.add(new Location(world, 13.5, 67, 49.5, 186, 0));
    }
    else if(str == "survivalmap3"){
        locations.clear();
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -4.5, 72, 24.5, 525, 0));
        locations.add(new Location(world, -0.5, 72, 22.5, 506, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
        locations.add(new Location(world, -9.5, 72, 25.5, 540, 0));
    }
        for(Chunk chunk : world.getLoadedChunks()) {
            for(BlockState block : chunk.getTileEntities()){
                if(block instanceof Chest){
                 Chest chest = (Chest) block;
                 hg.setupLoot(chest.getInventory());
                }
            }
        }
    for(Entity current : world.getEntities()){
        if (current instanceof Item){
            current.remove();
            }
        }
    
    } 
    
}

