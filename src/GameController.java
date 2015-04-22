import java.util.*;
/*
 * GameController:
 * - 
 */
/*
 * ADD:
 *	- 
 *
 * FIX:
 * 	- 
 */	 

public class GameController {

	// views ??
	private Bank bank = new Bank();
	private GameProductionTiles gameProductionTiles = new GameProductionTiles();
	private GameBuildingTiles gameBuildingTiles = new GameBuildingTiles();
	
	private ArrayList<Player> playersList = new ArrayList<>();
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
			
		// add players to player list arrayList
		playersList.add(human);
		playersList.add(computer1);
		playersList.add(computer2);
		
		setPlayersStartingCubes();
	}
	
	// setup players with starting cubes from game bank
	private void setPlayersStartingCubes() {
		// start every player off with 4 of each cube
		for (Player p : playersList) {
			p.getCubes().setBlue(4);
			p.getCubes().setGreen(4);
			p.getCubes().setBrown(4);
			p.getCubes().setYellow(4);
		}
		// remove the cubes handed out from the bank
		bank.removeCubes("blue", 12);
		bank.removeCubes("green", 12);
		bank.removeCubes("brown", 12);
		bank.removeCubes("yellow", 12);
	}
	
	// rotate starting player
	public void rotateStartingPlayer() {
		Player temp = playersList.get(0);
		playersList.set(0, playersList.get(1));
		playersList.set(1, playersList.get(2));
		playersList.set(2, temp);
	}
	
	public Player getNextPlayer(Player currentPlayer) {
		int index = playersList.indexOf(currentPlayer);
		return (index == 2 ? playersList.get(0) : playersList.get(++index));
	}
	
	
	// returns the array list of players in the game
	public ArrayList<Player> getPlayersList() {
		return playersList;
	}
	
	// returns the human player
	public Player getHuman() {
		return human;
	}
	
	// returns the human player
	public Player getComputer1() {
		return computer1;
	}
		
	// returns the human player
	public Player getComputer2() {
		return computer2;
	}
	
	// returns the index of the human player in the array
	public int getHumanIndex() {
		return playersList.indexOf(human);
	}
	
	// should be able to do this in the class using it (passed by reference)
	//public static void addTile(Tile tile) {
	public void addProductionTile(ProductionTile tile) {
		gameProductionTiles.add(tile);
	}
	
	//
	public GameProductionTiles getGameProductionTiles() {
		return gameProductionTiles;
	}
	
	//
	public GameBuildingTiles getGameBuildingTiles() {
		return gameBuildingTiles;
	}

	// return the games bank object
	public Bank getBank() {
		return bank;
	}

	
}
