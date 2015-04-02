import java.util.*;
/*
 * GameTiles:
 * - represents the pool of game tiles available to every player as an ArrayList
 */
/*
 * Add:
 * 	- 
 * 
 * FIX:
 * 	- change to game production tiles
 */

@SuppressWarnings("serial")
public class GameTiles extends ArrayList<Tile> {
	
	// constructor
	public GameTiles() {
		createSetOfGameTiles();
		shuffleTiles();
	}
	
	// shuffle gameTiles array after creating and putting some back
	public void shuffleTiles() {
		Collections.shuffle(this);
	}
	
	// removes and returns a GameTile
	//public static Tile GetTile() {
	//	return remove(0);		
	//}
	
	// put unused tiles back in the array after player selection, and shuffles
	public void putUnusedTilesBack(ArrayList<Tile> unusedTiles) {
		
		for (int i = 0; i < unusedTiles.size(); i++) {
			add(unusedTiles.remove(i));
		}
		shuffleTiles();
	}
	
	// creates and adds all the possible game tiles
	private void createSetOfGameTiles() {
		// fertile x21
		for (int i = 1; i <= 21; i++) {
			if (i <= 12) {
				add(new Tile("fertile", "green", 2));
			}
			else if (i > 12 && i <= 15) {
				add(new Tile("fertile", "brown", 1));
			}
			else if (i > 15 && i <= 18) {
				add(new Tile("fertile", "blue", 1));
			}
			else if (i > 18 && i <= 21) {
				add(new Tile("fertile", "yellow", 1));
			}
		}
		
		// forest x 15
		for (int i = 1; i <= 15; i++) {
			if (i <= 9) {
				add(new Tile("forest", "brown", 2));
			}
			else if (i > 9 && i <= 11) {
				add(new Tile("forest", "green", 1));
			}
			else if (i > 11 && i <= 13) {
				add(new Tile("forest", "yellow", 1));
			}
			else if (i > 13 && i <= 15) {
				add(new Tile("forest", "blue", 1));
			}
		}
		
		// hill x16
		for (int i = 1; i <= 16; i++) {
			if (i <= 4) {
				add(new Tile("hill", "yellow", 2));
			}
			else if (i > 4 && i <= 8) {
				add(new Tile("hill", "green", 1));
			}
			else if (i > 8 && i <= 12) {
				add(new Tile("hill", "brown", 1));
			}
			else if (i > 12 && i <= 16) {
				add(new Tile("hill", "blue", 1));
			}
		}
		
		// mountain x12
		for (int i = 1; i <= 12; i++) {
			if (i <= 6) {
				add(new Tile("mountain", "yellow", 2));
			}
			else if (i > 6 && i <= 9) {
				add(new Tile("mountain", "brown", 1));
			}
			else if (i > 9 && i <= 12) {
				add(new Tile("mountain", "blue", 1));
			}
		}
		
		// desert x14
		for (int i = 1; i <= 14; i++) {
			if (i <= 7) {
				add(new Tile("desert", "blue", 2));
			}
			else if (i > 7 && i <= 14) {
				add(new Tile("desert", "yellow", 1));
			}
		}
		
		// swamp x12
		for (int i = 1; i <= 12; i++) {
			if (i <= 4) {
				add(new Tile("swamp", "brown", 1));
			}
			else if (i > 4 && i <= 8) {
				add(new Tile("swamp", "green", 1));
			}
			else if (i > 8 && i <= 12) {
				add(new Tile("swamp", "blue", 1));
			}
		}
	}
}
