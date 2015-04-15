/*
 * BuildingTile:
 * - represents a building tile in the game
 * -  
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	- 
 */

public class BuildingTile {

	private String name;
	
	// constructor
	public BuildingTile(String aName) {
		name = aName;
	}

	//
	public String getName() {
		return name;
	}
	
	//
	@Override
	public String toString() {
		return name;
	}
	
}
