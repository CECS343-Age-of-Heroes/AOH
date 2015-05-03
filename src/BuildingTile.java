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
	
	public String getShortBuildingName() {
		String str = "";
		if (name.equals("House")) {
			str = "HSE";
		}
		else if (name.equals("Great Temple")) {
			str = "GT";
		}
		else if (name.equals("The Wonder")) {
			str = "WNDR";
		}
		else if (name.equals("Storehouse")) {
			str = "SH";
		}
		else if (name.equals("Gold Mint")) {
			str = "GM";
		}
		else if (name.equals("Wood Workshop")) {
			str = "WW";
		}
		else if (name.equals("Granary")) {
			str = "GY";
		}
		else if (name.equals("Quarry")) {
			str = "QY";
		}
		else if (name.equals("Monument")) {
			str = "MT";
		}
		else if (name.equals("Market")) {
			str = "MKT";
		}
		
		return str;
	}

	
}
