package me.wowitsmak.main.managers;

import me.wowitsmak.main.Main;

public class GameManager {
	private final BlockManager blockManager;
	
	private GameState gameState;
	private HungerGamesStart hgameStart;
	
	public GameManager() {
		this.blockManager = new BlockManager(this);
		this.hgameStart = new HungerGamesStart();
	}
	
	public void setGameState(GameState gameState){
		if(this.gameState == GameState.ACTIVE && gameState.equals(GameState.STARTING)) return;
		this.gameState = gameState;
		switch(gameState) {
			case STARTING:
			if(Main.getRound() == 1){
				hgameStart.start();
			}
			case ACTIVE:
				
			case ENDGAME:
			case INGAME:
			default:
		}
	
	}
	public GameState getGameState() {
		return this.gameState;
	}
	public BlockManager getBlockManager() {return blockManager; }

}
