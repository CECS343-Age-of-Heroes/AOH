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
	
	
	public BuildCard(int buildings) {
		setName("Build");
		numOfBuildings = buildings;
	}
	
	
	// return the number of buildings this card is aloud to make
	public int getNumOfBuildings() {
		return numOfBuildings;
	}
	
	//
	public boolean executeActionCard(Player player, BuildingTile building) { 
		System.out.println("execute action card - Build");
		
		if (player.hasEnoughResources(building.getCubeCost())) {
			
			
			player.removeCubes(building.getCubeCost());
			
			
		}
		
//		for (int i = 0; i < numOfBuildings; i++) {
//			
//			
//		}
		
		return false;
	}
	
	
	@Override
	public String toString() {
		return getName() + " " + numOfBuildings;
	}

}
