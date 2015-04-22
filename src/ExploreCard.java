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
	
	// constructor
	public ExploreCard(int tiles) {
		setName("Explore");
		numOfTiles = tiles;
	}
	
	// return the number of tiles this card is aloud to draw
	public int getNumOfTiles() {
		return numOfTiles;
	}
	
	//
	public boolean executeActionCard(Player player) { 
		System.out.println("execute action card - Explore");
		return false;
	}
	
	
	@Override
	public String toString() {
		return getName() + " " + numOfTiles;
	}
}
