import java.util.*;
/*
 * GameProductionTiles:
 * - represents the pool of game tiles available to every player as an ArrayList
 */
/*
 * Add:
 * 	- 
 * 
 * FIX:
 * 	-
 */
@SuppressWarnings("serial")
public class GameProductionTiles extends ArrayList<ProductionTile> {
	
	// constructor
	public GameProductionTiles() {
		createSetOfGameProductionTiles();
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
	public void putUnusedProductionTilesBack(ArrayList<ProductionTile> unusedTiles) {
		
		for (int i = 0; i < unusedTiles.size(); i++) {
			add(unusedTiles.remove(i));
		}
		shuffleTiles();
	}
	
	// creates and adds all the possible game tiles
	private void createSetOfGameProductionTiles() {
		// fertile x21
		for (int i = 1; i <= 21; i++) {
			if (i <= 12) {
				add(new ProductionTile("fertile", "green", 2));
			}
			else if (i > 12 && i <= 15) {
				add(new ProductionTile("fertile", "brown", 1));
			}
			else if (i > 15 && i <= 18) {
				add(new ProductionTile("fertile", "blue", 1));
			}
			else if (i > 18 && i <= 21) {
				add(new ProductionTile("fertile", "yellow", 1));
			}
		}
		
		// forest x 15
		for (int i = 1; i <= 15; i++) {
			if (i <= 9) {
				add(new ProductionTile("forest", "brown", 2));
			}
			else if (i > 9 && i <= 11) {
				add(new ProductionTile("forest", "green", 1));
			}
			else if (i > 11 && i <= 13) {
				add(new ProductionTile("forest", "yellow", 1));
			}
			else if (i > 13 && i <= 15) {
				add(new ProductionTile("forest", "blue", 1));
			}
		}
		
		// hill x16
		for (int i = 1; i <= 16; i++) {
			if (i <= 4) {
				add(new ProductionTile("hill", "yellow", 2));
			}
			else if (i > 4 && i <= 8) {
				add(new ProductionTile("hill", "green", 1));
			}
			else if (i > 8 && i <= 12) {
				add(new ProductionTile("hill", "brown", 1));
			}
			else if (i > 12 && i <= 16) {
				add(new ProductionTile("hill", "blue", 1));
			}
		}
		
		// mountain x12
		for (int i = 1; i <= 12; i++) {
			if (i <= 6) {
				add(new ProductionTile("mountain", "yellow", 2));
			}
			else if (i > 6 && i <= 9) {
				add(new ProductionTile("mountain", "brown", 1));
			}
			else if (i > 9 && i <= 12) {
				add(new ProductionTile("mountain", "blue", 1));
			}
		}
		
		// desert x14
		for (int i = 1; i <= 14; i++) {
			if (i <= 7) {
				add(new ProductionTile("desert", "blue", 2));
			}
			else if (i > 7 && i <= 14) {
				add(new ProductionTile("desert", "yellow", 1));
			}
		}
		
		// swamp x12
		for (int i = 1; i <= 12; i++) {
			if (i <= 4) {
				add(new ProductionTile("swamp", "brown", 1));
			}
			else if (i > 4 && i <= 8) {
				add(new ProductionTile("swamp", "green", 1));
			}
			else if (i > 8 && i <= 12) {
				add(new ProductionTile("swamp", "blue", 1));
			}
		}
	}
}
