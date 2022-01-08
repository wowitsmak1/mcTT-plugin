package me.wowitsmak.main.events;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wowitsmak.main.Main;
import me.wowitsmak.main.loot_tables.HungerGamesLootTable;
import me.wowitsmak.main.scoreboard.ScoreboardOwner;
import me.wowitsmak.main.managers.GameState;
import me.wowitsmak.main.managers.PlayerManager;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener{
	PlayerManager pm = Main.getPlayerManager();
	HungerGamesLootTable hg;
	public Events(HungerGamesLootTable hungerGamesLootTable) {
		this.hg = new HungerGamesLootTable();
	}
	@EventHandler
	private void onBlockBreak(BlockBreakEvent event) {
		if(pm.playing.contains(event.getPlayer())) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	private void onBlockInteract(PlayerInteractEvent event){
		Main.getPlayerManager().updateButtonScoreMap();
		if(Main.getRound() == 2 && Main.getGameManager().getGameState().equals(GameState.ACTIVE)){
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().toString().endsWith("_BUTTON") && Main.getPlayerManager().getButtonScoreMap().containsKey(event.getPlayer())){
			if(Main.getHungerGamesWorld().locations.size() <= Main.getPlayerManager().getButtonScoreMap().get(event.getPlayer())){
				Main.getPlayerManager().getButtonLeaderboard().put(event.getPlayer(), Main.getPlayerManager().getButtonLeaderboard().size() + 1);
				Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + "has finished in " + ChatColor.GREEN + Main.getPlayerManager().getButtonLeaderboard().get(event.getPlayer()) + " place.");
				Integer placement = Main.getPlayerManager().getButtonLeaderboard().get(event.getPlayer());
				if(placement == 1){
					Main.getPointManager().addPoints(event.getPlayer(), 500);
					event.getPlayer().sendTitle(ChatColor.GOLD + "1st PLACE!!!", ChatColor.YELLOW + "+500 Points", 1, 60, 1);
				}
				else if(placement == 2){
					Main.getPointManager().addPoints(event.getPlayer(), 400);
					event.getPlayer().sendTitle(ChatColor.GOLD + "2nd PLACE!!!", ChatColor.YELLOW + "+400 Points", 1, 60, 1);
				}
				else if(placement == 3){
					Main.getPointManager().addPoints(event.getPlayer(), 250);
					event.getPlayer().sendTitle(ChatColor.GOLD + "3rd PLACE!!!", ChatColor.YELLOW + "+250 Points", 1, 60, 1);
				}
				else{
					Main.getPointManager().addPoints(event.getPlayer(), 150);
					event.getPlayer().sendTitle(ChatColor.GOLD + "" + Main.getPlayerManager().getButtonLeaderboard().get(event.getPlayer()) + "th PLACE!!!", ChatColor.YELLOW + "+150 Points", 1, 60, 1);
				}
				Main.getPlayerManager().playing.remove(event.getPlayer());
				Main.getPlayerManager().spectators.add(event.getPlayer());
				event.getPlayer().setGameMode(GameMode.SPECTATOR);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> event.getPlayer().spigot().respawn(), 2);
				if(Main.getPlayerManager().playing.size() == 0){
					Main.getGameManager().setGameState(GameState.ENDGAME);

				}
			}
			else{
			Main.getFindTheButton().nextLevel(event.getPlayer());
			}
		}
		else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().toString().contains("DOOR") && Main.getRound() == 2){
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					event.setCancelled(true);
				}
				
			}, 60L);
		}
		}
		
		}
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
		if(Main.getPlayerManager().offlineplayers.containsKey(e.getPlayer())){
			Main.getPointManager().addPlayer(e.getPlayer(), Main.getPlayerManager().offlineplayers.get(e.getPlayer()));
			Main.getPlayerManager().offlineplayers.remove(e.getPlayer());
		}
		else{
			Main.getPointManager().addPlayer(e.getPlayer());
		}
		Main.getPlayerManager().getParticipantsSet().add(e.getPlayer());
		Main.getTeamManager().addTeamPlayers();
        Main.getScoreboardManager();
		ScoreboardOwner.createScoreboard(e.getPlayer());
        Main.getScoreboardManager();
		ScoreboardOwner.updateScoreboard();     
		if(Main.getGameManager().getGameState() == GameState.ACTIVE){
			Main.getPlayerManager().spectators.add(e.getPlayer());
		}
		else if(Main.getGameManager().getGameState().equals(GameState.PREPARING) || Main.getGameManager().getGameState().equals(GameState.STARTING)){
			if(Main.getRound() == 1){
				if(!pm.playing.contains(e.getPlayer())) {
					pm.playing.add(e.getPlayer());
				}
				if(pm.spectators.contains(e.getPlayer())) {
					pm.spectators.remove(e.getPlayer());
				}
				e.getPlayer().getInventory().clear();
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 100000, true, false));
				e.getPlayer().setWalkSpeed(0F);
				e.getPlayer().setSaturation(20);
				e.getPlayer().setHealth(20);
				e.getPlayer().setGameMode(GameMode.SURVIVAL);
				pm.teleportPlayer(e.getPlayer(), "survivalmap2");
			}
			else if(Main.getRound() == 2){
				e.getPlayer().getInventory().clear();
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 100000, true, false));
				e.getPlayer().setWalkSpeed(0F);
				e.getPlayer().setSaturation(20);
				e.getPlayer().setHealth(20);
				e.getPlayer().setGameMode(GameMode.SURVIVAL);
				Main.getPlayerManager().teleportPlayer(e.getPlayer(), "button");
			}
		}
		
        
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
		if(Main.getPlayerManager().playing.contains(e.getPlayer())){
			Main.getPlayerManager().playing.remove(e.getPlayer());
			Main.getPlayerManager().offlineplayers.put(e.getPlayer(), Main.getPointManager().getPoints(e.getPlayer()));
			Main.getPointManager().removePlayer(e.getPlayer());
		}
		else if(Main.getPlayerManager().spectators.contains(e.getPlayer())){
			Main.getPlayerManager().spectators.remove(e.getPlayer());
			Main.getPlayerManager().offlineplayers.put(e.getPlayer(), Main.getPointManager().getPoints(e.getPlayer()));
			Main.getPointManager().removePlayer(e.getPlayer());
		}
    	Main.getScoreboardManager();
    	ScoreboardOwner.updateScoreboard();
    }
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
    	if(event.getWorld() == Bukkit.getWorld("cove"))
            for(BlockState block : event.getChunk().getTileEntities()){
                if(block instanceof Chest){
                 Chest chest = (Chest) block;
                 hg.setupLoot(chest.getInventory());
                }
            }
        }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    	if(pm.playing.contains(event.getPlayer()) && Main.getGameManager().getGameState() == GameState.STARTING || Main.getGameManager().getGameState() == GameState.PREPARING) {
    		final Location from = event.getFrom();
            final Location to = event.getTo();
            Main.getScoreboardManager();
            ScoreboardOwner.updateScoreboard();
            if (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ() || from.getBlockY() != to.getBlockY()) {
                event.setTo(from);
            }
    	}
    }
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event){
		if(Main.getGameManager().getGameState().equals(GameState.STARTING) && !event.getDamager().isOp() || Main.getCooldown() > 0)
		event.setCancelled(true);
		if(event.getEntity() instanceof Player){
			if(event.getDamager() instanceof Player){
				Player killer = (Player) event.getDamager();
				Player damaged = (Player) event.getEntity();
				if(Main.getTeamManager().getPlayerTeam(damaged) == Main.getTeamManager().getPlayerTeam(killer)){
					event.setCancelled(true);
				}
			}
		}
	}
    @EventHandler
    public void onPlayerMurder(PlayerDeathEvent event) {
    		if(pm.playing.contains(event.getEntity()) && Main.getRound() == 1) {
    			pm.playing.remove(event.getEntity());
    			pm.spectators.add(event.getEntity());
				event.getEntity().setGameMode(GameMode.SPECTATOR);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> event.getEntity().spigot().respawn(), 2);
				event.getEntity().teleport(event.getEntity().getKiller().getLocation());
				event.getEntity().sendTitle(ChatColor.RED + "You have died.", ChatColor.GREEN + "GGs though", 1, 40, 1);
    			Main.getScoreboardManager();
    			ScoreboardOwner.updateScoreboard();
    			if(pm.playing.contains(event.getEntity().getKiller())) {
    				Main.getPointManager().addPoints(event.getEntity().getKiller(), 500);
    				Bukkit.getServer().broadcastMessage("There are " + pm.playing.size() + " players left.");
    				if(pm.playing.size() == 1) {
        				Main.getGameManager().setGameState(GameState.ENDGAME);
        				event.getEntity().getKiller().sendTitle(ChatColor.GOLD + "You have won!", ChatColor.GREEN + "+500 Points", 1, 40, 1);
        				Bukkit.broadcastMessage(ChatColor.GOLD + "" + event.getEntity().getKiller().getName() + " has won!");
        				Main.getPointManager().addPoints(event.getEntity().getKiller(), 500);
						Main.getPointManager().addPoints(Main.getTeamManager().getTeammate(event.getEntity()), 500);
        				Bukkit.getScheduler().cancelTasks(Main.getInstance());
        				Bukkit.broadcastMessage(ChatColor.GREEN + event.getEntity().getKiller().getName() + " has " + Main.getPointManager().getPoints(event.getEntity().getKiller()) + " points");
        				for(Player player : pm.spectators) {
        					Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
        				}
    				}
					else if(pm.playing.size() == 2){
						Player player1 = pm.playing.iterator().next();
						pm.playing.iterator().remove();
						Player player2 = pm.playing.iterator().next();
						if(Main.getTeamManager().getPlayerTeam(player1) == Main.getTeamManager().getPlayerTeam(player2)){
							player1.sendTitle(ChatColor.GOLD + "You have won!", ChatColor.GREEN + "+500 Points", 1, 40, 1);
							player2.sendTitle(ChatColor.GOLD + "You have won!", ChatColor.GREEN + "+500 Points", 1, 40, 1);
        					Bukkit.broadcastMessage(ChatColor.GOLD + "Players " + player1.getName() + "and " + player2.getName() + " have won!");
        					Main.getPointManager().addPoints(player1, 500);
							Main.getPointManager().addPoints(player2, 500);
        					Bukkit.getScheduler().cancelTasks(Main.getInstance());
        					Bukkit.broadcastMessage(ChatColor.GREEN + event.getEntity().getKiller().getName() + " has " + Main.getPointManager().getPoints(event.getEntity().getKiller()) + " points");
							for(Player player : pm.spectators) {
								Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
							}
						}
						else{
							pm.playing.add(player1);
							pm.playing.add(player2);
						}
					}
    			}
    			else {
    			if(pm.playing.size() == 1) {
    				Main.getGameManager().setGameState(GameState.ENDGAME);
						event.getEntity().getKiller().sendTitle(ChatColor.GOLD + "You have won!", ChatColor.GREEN + "+500 Points", 1, 40, 1);
        				Bukkit.broadcastMessage(ChatColor.GOLD + "" + event.getEntity().getKiller().getName() + " has won!");
        				Main.getPointManager().addPoints(event.getEntity().getKiller(), 500);
						Main.getPointManager().addPoints(Main.getTeamManager().getTeammate(event.getEntity()), 500);
        				Bukkit.getScheduler().cancelTasks(Main.getInstance());
        				Bukkit.broadcastMessage(ChatColor.GREEN + event.getEntity().getKiller().getName() + " has " + Main.getPointManager().getPoints(event.getEntity().getKiller()) + " points");
        				for(Player player : pm.spectators) {
        					Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has " + Main.getPointManager().getPoints(player) + " points");
        				}
    				}
				else if(pm.playing.size() == 2){
					Player player1 = pm.playing.iterator().next();
						pm.playing.iterator().remove();
						Player player2 = pm.playing.iterator().next();
						if(Main.getTeamManager().getPlayerTeam(player1) == Main.getTeamManager().getPlayerTeam(player2)){
							player2.sendTitle(ChatColor.GOLD + "You have won!", ChatColor.GREEN + "+500 Points", 1, 40, 1);
							player1.sendTitle(ChatColor.GOLD + "You have won!", ChatColor.GREEN + "+500 Points", 1, 40, 1);
        					Bukkit.broadcastMessage(ChatColor.GOLD + "Players " + player1.getName() + "and " + player2.getName() + " have won!");
        					Main.getPointManager().addPoints(player1, 500);
							Main.getPointManager().addPoints(player2, 500);
        					Bukkit.getScheduler().cancelTasks(Main.getInstance());
        					Bukkit.broadcastMessage(ChatColor.GREEN + event.getEntity().getKiller().getName() + " has " + Main.getPointManager().getPoints(event.getEntity().getKiller()) + " points");
						}
						else{
							pm.playing.add(player1);
							pm.playing.add(player2);
						}
				}
    			
			}
    		
		}
    	
	}
    
}
