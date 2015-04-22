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
	private Cubes cubeCost = new Cubes();
	
	// constructor
	public BuildingTile(String aName, Cubes cost) {
		name = aName;
		cubeCost = cost;
	}

	//
	public String getName() {
		return name;
	}
	
	//
	public Cubes getCubeCost() {
		return cubeCost;
	}
	
	//
	@Override
	public String toString() {
		return name;
	}
	
}
