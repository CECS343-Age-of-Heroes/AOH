/*
 * NextAgeCard:
 * 	- represents the next age action card
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	- when to call find cost
 */
/* ancient, classical, heroic, mythic 
 * classical age = 4 of each, heroic age = 5 of each, mythic age = 6 of each
 */
@SuppressWarnings("serial")
public class NextAgeCard extends ActionCard {

	private Integer cost = 0;
	
	// constructor
	public NextAgeCard(String aType) {
		setName(aType);
		setType("random");
		//findCost();
	}
	public NextAgeCard(String a, String b){
		setName(a+" "+b);
		setType("god");
	}
	// might need to call this from player class or when playing the card
	// returns the cost of each resource type from a players current age
	private Integer findCost(Player p) {
		Integer ageCost = 0;
		
		if (p.getCulture().getPermanentCards().contains(this)) {
			switch (p.getAge()) {
			case 4: ageCost = 4;	// player in ancient
				break;
			case 5: ageCost = 5;	// player is in classical
				break;
			case 6: ageCost = 6;	// player is in heroic
				break;
			case 7: ageCost = -1;	// player is in mythic, highest already
				break;
			}
		}
		else if (p.getCulture().getRandomCards().contains(this)) {
			switch (p.getAge()) {
			case 4: ageCost = 3;	// player in ancient
				break;
			case 5: ageCost = 4;	// player is in classical
				break;
			case 6: ageCost = 5;	// player is in heroic
				break;
			case 7: ageCost = -1;	// player is in mythic, highest already
				break;
			}
		}
		
		return ageCost;
	}
	
	

}
