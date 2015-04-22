/*
 * NextAgeCard:
 * 	- represents the next age action card
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	- 
 */
/* ancient, classical, heroic, mythic 
 *
 */
@SuppressWarnings("serial")
public class NextAgeCard extends ActionCard {

	private int cost = 0;		// 345 or 456
	
	// constructor
	public NextAgeCard(int theCost) {
		setName("NextAge");
		cost = theCost;
	}
	
	// return the cost to upgrade to new age
	public int getCost() {
		return cost;
	}
	
	// 456 permanent card
	// cost is same as player age
	
	// 345 random card
	// cost is playerAge - 1
	
	public boolean executeActionCard(Player player) {
		System.out.println("execute action card - NextAge");
		boolean success = false;
		Cubes pCubes = player.getCubes();
		Cubes cubeCost = new Cubes();
		int theCost = 0;
		if (cost == 345) {
			theCost -= player.getAge() - 1;
		}
		else if (cost == 456) {
			theCost -= player.getAge();
		}
		cubeCost.setCubes(theCost, theCost, theCost, theCost);
		//
		if (player.hasEnoughResources(cubeCost)) {
//			pCubes.setBlue(pCubes.getBlue() - theCost);
//			pCubes.setGreen(pCubes.getGreen() - theCost);
//			pCubes.setBrown(pCubes.getBrown() - theCost);
//			pCubes.setYellow(pCubes.getYellow() - theCost);
			pCubes.addCubesToCubes(cubeCost);
			success = true;
			
			// need tp add those cubes to the bank after
			
		}
		else { 
			// display message not enough resources
			
		}
		return success;
	}
	
	
	@Override
	public String toString() {
		return getName() + " " + cost;
	}
	
}
