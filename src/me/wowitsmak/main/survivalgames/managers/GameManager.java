package me.wowitsmak.main.survivalgames.managers;
import me.wowitsmak.main.Main;

public class GameManager {
	private final BlockManager blockManager;
	
	private final PlayerManager playerManager;
	
	private GameState gameState;
	
	public GameManager(Main instance) {
		this.blockManager = new BlockManager(this);
		this.playerManager = new PlayerManager(this);
	}
	
	public void setGameState(GameState gameState){
		if(this.gameState == GameState.STARTING && gameState.equals(GameState.ACTIVE)) return;
		this.gameState = gameState;
		switch(gameState) {
			case STARTING:
				playerManager.giveItems();
			case ACTIVE:
			case ENDGAME:
			case INGAME:
			default:
		}
	}
	public BlockManager getBlockManager() {return blockManager; }
	
	public PlayerManager getPlayerManager() {return playerManager; }
}
