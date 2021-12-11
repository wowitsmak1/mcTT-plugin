package me.wowitsmak.main;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;



public class DiamondStuff implements Listener, CommandExecutor {
	private Set<UUID> diamond = new HashSet<UUID>();
	private Main plugin;
	public DiamondStuff(Main plugin) {
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender.isOp()) {
			if(Bukkit.getPlayer(args[0]) instanceof Player) {
				Player player = Bukkit.getPlayer(args[0]);
				UUID uuid = player.getUniqueId();
				if(!diamond.contains(uuid)) {
						diamond.add(uuid);
						sender.sendMessage("Added "+ player.getName());
						Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
						    @Override
						    public void run() {
						    	if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir() == false && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.DIAMOND_BLOCK) {
						    		player.getLocation().getBlock().setType(Material.DIAMOND_BLOCK);
						    	};
						    }
						}, 0L, 20);
						}
						else if(diamond.contains(uuid)){
							Bukkit.getScheduler().cancelTasks(Main.getInstance());
							diamond.remove(uuid);
							sender.sendMessage("Removed "+ player.getName());
							player.getInventory().clear();
						}
			}}
		return false;
		}
	@EventHandler
	private void onBlockHit(PlayerInteractEvent event) {
		if(diamond.contains(event.getPlayer().getUniqueId()) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_BLOCK) && !event.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)) {
			event.getClickedBlock().setType(Material.DIAMOND_BLOCK);
		}
	}
}
	  
