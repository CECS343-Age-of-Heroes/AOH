/*
 * ExploreCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class ExploreCard extends ActionCard {
	
	private int numOfTiles = 0;		// 3,4,5
	private String godName = "";
	
	// constructor
	public ExploreCard(int tiles) {
		setName("Explore");
		numOfTiles = tiles;
	}
	public ExploreCard(int tiles, String name) {
		setName("Explore");
		numOfTiles = tiles;
		godName = name;
	}
	
	// return the number of tiles this card is aloud to draw
	public int getNumOfTiles() {
		return numOfTiles;
	}
	
	@Override
	public String toString() {
		return getName() + " " + numOfTiles + " " + godName;
	}
}
