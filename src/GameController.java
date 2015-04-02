import java.util.*;
/*
 * GameController:
 * - 
 */
/*
 * ADD:
 * 	- add a return human / comp1 / comp2 methods that search the playerList first 
 *   for the right player because it will change around not always 0-1-2,
 *   check for name or type of the player
 *	- need a method to rotate the starting players index in the array list
 *
 * FIX:
 * 	- change game tiles to production tiles
 */	 

public class GameController {

	// data: Bank, tiles, cards?
	// views: 
	private ArrayList<Player> playersList = new ArrayList<>();
	//private static GameTiles gameTiles;
	private GameTiles gameTiles = new GameTiles(); // change to production tiles
	private Bank bank = new Bank();
	private Player human = new Player();
	private Player computer1 = new Player();
	private Player computer2 = new Player();
	
	// constructor
	public GameController() {
		
		initPlayersList();
	}

	// initialize the games players
	private void initPlayersList() {
		// set names
		human.setName("");
		computer1.setName("Comp1");
		computer2.setName("Comp2");
		// set types
		human.setType("human");
		computer1.setType("computer");
		computer2.setType("computer");
		// start every player off with 4 of each cube
		human.getCubes().setBlue(4);
		human.getCubes().setGreen(4);
		human.getCubes().setBrown(4);
		human.getCubes().setYellow(4);
		
		computer1.getCubes().setBlue(4);
		computer1.getCubes().setGreen(4);
		computer1.getCubes().setBrown(4);
		computer1.getCubes().setYellow(4);
		
		computer2.getCubes().setBlue(4);
		computer2.getCubes().setGreen(4);
		computer2.getCubes().setBrown(4);
		computer2.getCubes().setYellow(4);
		
		// remove the cubes handed out from the bank
		bank.removeCubes("blue", 12);
		bank.removeCubes("green", 12);
		bank.removeCubes("brown", 12);
		bank.removeCubes("yellow", 12);
		
		// add players to player list arrayList
		playersList.add(human);
		playersList.add(computer1);
		playersList.add(computer2);
	}
	
	// 
	public void updateStartingPlayer() {
		
	}
	
	
//	public void setPlayersList(ArrayList<Player> newPlayerList) { // shouldnt need 
//		playersList = new ArrayList<Player>(newPlayerList);
//	}
	
	// returns the array list of players in the game
	public ArrayList<Player> getPlayersList() {
		return playersList;
	}
	
	// returns the index of the human player in the array
	public int getHumanIndex() {
		return playersList.indexOf(human);
	}
	
//	public static Tile removeGameTile() {
//		return gameTiles.remove(0);
//	}
	
	//public static void addTile(Tile tile) {
	public void addTile(Tile tile) {
		gameTiles.add(tile);
	}
	
	//public static GameTiles getGameTiles() {
	public GameTiles getGameTiles() {
		return gameTiles;
	}

	// return the games bank object
	public Bank getBank() {
		return bank;
	}

//	public void setBank(Bank bank) {		// shouldnt need
//		this.bank = bank;
//	}
		
}
