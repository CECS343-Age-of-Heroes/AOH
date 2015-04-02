import java.util.*;
/*
 * Culture:

 * - is the parent of each of the cultures in the game
 * - 
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	- change: setProductionList -> setProductionAreaList   (if needed anywhere)
 */

public class Culture {
	// board ( has: holding area, production area, city area), 
	
	private String name = "";		// culture name
	// is the list of names on the production area for the player board
	private ArrayList<String> productionAreaList = new ArrayList<>();
	// permanent action cards
	private ArrayList<ActionCard> permanentCards = new ArrayList<>();
	// random action cards
	private ArrayList<ActionCard> randomCards = new ArrayList<>();
	
	
	// constructor
	public Culture() {
		
	}

	// returns the culture name
	public String getName() {
		return name;
	}

	// sets the culture name
	public void setName(String name) {
		this.name = name;
	}
	
	// returns the production area list
	public ArrayList<String> getProductionAreaList() {
		return productionAreaList;
	}

	// sets the production area list
	public void setProductionList(ArrayList<String> productionAreaList) {
		this.productionAreaList = productionAreaList;
	}

	// get permanent cards arrayList
	public ArrayList<ActionCard> getPermanentCards() {
		return permanentCards;
	}

	// set permanent cards arrayList
	public void setPermanentCards(ArrayList<ActionCard> permanentCards) {
		this.permanentCards = permanentCards;
	}

	// get random cards arrayList
	public ArrayList<ActionCard> getRandomCards() {
		return randomCards;
	}
	
	// set random cards arrayList
	public void setRandomCards(ArrayList<ActionCard> randomCards) {
		this.randomCards = randomCards;
	}

	// override the toString for printing
	@Override
    public String toString() {
        return name;
    }
	
}
