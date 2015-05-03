/*
 * BuildCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class BuildCard extends ActionCard {

	private int numOfBuildings = 0; 	// 1,2,3,4
	
	//
	public BuildCard(int buildings) {
		setName("Build");
		numOfBuildings = buildings;
	}
	
	
	// return the number of buildings this card is aloud to make
	public int getNumOfBuildings() {
		return numOfBuildings;
	}
	
	//
	public String getValue() {
		return String.valueOf(numOfBuildings);
	}
	
	//
	@Override
	public String toString() {
		return getName() + " " + numOfBuildings;
	}

}
