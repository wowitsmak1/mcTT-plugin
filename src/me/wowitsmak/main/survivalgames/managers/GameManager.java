package me.wowitsmak.main.survivalgames.managers;


public class GameManager {
	private final BlockManager blockManager;
	
	private GameState gameState;
	private GameStart gameStart;
	
	public GameManager() {
		this.blockManager = new BlockManager(this);
		this.gameStart = new GameStart();
	}
	
	public void setGameState(GameState gameState){
		if(this.gameState == GameState.ACTIVE && gameState.equals(GameState.STARTING)) return;
		this.gameState = gameState;
		switch(gameState) {
			case STARTING:
				gameStart.start();
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
