import java.util.*;
/*
 * GameBuildingTiles:
 * - represents the pool of game building tiles available to every player 
 * 	as an ArrayList
 */
/*
 * Add:
 * 	- 
 * 
 * FIX:
 * 	-
 */
@SuppressWarnings("serial")
public class GameBuildingTiles extends ArrayList<BuildingTile> {

	// constructor
	public GameBuildingTiles() {
		createSetOfBuildingTiles();
	}
	
	// add building tiles to game building tiles array list
	private void createSetOfBuildingTiles() {
		// house x40
		for (int i = 0; i < 40; i++) {
			add(new BuildingTile("House"));
		}
		// great temple x2
		for (int i = 0; i < 2; i++) {
			add(new BuildingTile("Great Temple"));
		}
		// the wonder x1
		add(new BuildingTile("The Wonder"));
		// others x4
		for (int i = 0; i < 4; i++) {
			add(new BuildingTile("Storehouse"));
			add(new BuildingTile("Gold Mint"));
			add(new BuildingTile("Wood Workshop"));
			add(new BuildingTile("Granary"));
			add(new BuildingTile("Quarry"));
			add(new BuildingTile("Monument"));
			add(new BuildingTile("Market"));
		}
	}
	
	
}



