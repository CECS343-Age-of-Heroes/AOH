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
	private String godName = "";
	
	public BuildCard(int buildings) {
		setName("Build");
		numOfBuildings = buildings;
	}
	public BuildCard(int buildings,String name) {
		setName("Build");
		numOfBuildings = buildings;
		godName = name;
	}
	
	// return the number of buildings this card is aloud to make
	public int getNumOfBuildings() {
		return numOfBuildings;
	}
	
	@Override
	public String toString() {
		return getName() + " " + numOfBuildings + " " + godName;
	}

}
