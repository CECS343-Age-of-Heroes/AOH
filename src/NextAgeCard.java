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
	private String godName = "";
	
	// constructor
	public NextAgeCard(int theCost) {
		setName("NextAge");
		cost = theCost;
	}
	public NextAgeCard(int theCost, String name) {
		setName("NextAge");
		cost = theCost;
		godName = name;
	}
	
	// return the cost to upgrade to new age
	public int getCost() {
		return cost;
	}
	
	// 456 permanent card
	// cost is same as player age
	
	// 345 random card
	// cost is playerAge - 1
	
	@Override
	public String toString() {
		return getName() + " " + cost + " " + godName;
	}
	
}
