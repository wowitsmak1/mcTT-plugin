package me.wowitsmak.main.survivalgames.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.wowitsmak.main.Main;

public class FindTheButton {
    public void Start() {
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!Main.getPlayerManager().playing.contains(player)){
                Main.getPlayerManager().playing.add(player);
            }
        }
    }

}
