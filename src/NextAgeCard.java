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
	
	// 345 random card	// cost is playerAge - 1
	// 456 permanent card	// cost is same as player age
	//
	public boolean canPlay(Player player) {
		boolean success = false;
		
		Cubes pCubes = player.getCubes();
	
		int theCost = 0;
		if (cost == 345) {
			theCost = player.getAge() - 1;
		}
		else if (cost == 456) {
			theCost = player.getAge();
		}
		
		Cubes cubeCost = new Cubes(theCost, theCost, theCost, theCost, 0);	
		
		//
		if (player.hasEnoughResources(cubeCost)) {
			pCubes.addCubesToCubes(cubeCost);
			success = true;			
		}

		return success;
	}
	
	//
	@Override
	public String toString() {
		return getName() + " " + cost;
	}
	
}
