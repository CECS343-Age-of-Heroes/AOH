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
	private ArrayList<ActionCard> egyptPermanentCards = new ArrayList<>();
	private ArrayList<ActionCard> greekPermanentCards = new ArrayList<>();
	private ArrayList<ActionCard> norsePermanentCards = new ArrayList<>();
	// random action cards
	private ArrayList<ActionCard> egyptRandomCards = new ArrayList<>();
	private ArrayList<ActionCard> greekRandomCards = new ArrayList<>();
	private ArrayList<ActionCard> norseRandomCards = new ArrayList<>();

	private Player player;
	
	
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
		if(getName().equals("Egyptian"))
			return egyptPermanentCards;
		else if(getName().equals("Greek"))
			return greekPermanentCards;
		else
			return norsePermanentCards;
	}

	// set permanent cards arrayList
	public void setPermanentCards(ArrayList<ActionCard> permanentCards) {
		if(getName().equals("Egyptian"))
			this.egyptPermanentCards = permanentCards;
		else if(getName().equals("Greek"))
			this.greekPermanentCards = permanentCards;
		else
			this.norsePermanentCards = permanentCards;
	}

	// get random cards arrayList
	public ArrayList<ActionCard> getRandomCards() {
		if(getName().equals("Egyptian"))	
			return egyptRandomCards;
		else if(getName().equals("Greek"))
			return greekRandomCards;
		else
			return norseRandomCards;
	}
	
	// set random cards arrayList
	public void setRandomCards(ArrayList<ActionCard> randomCards) {
		if(getName().equals("Egyptian"))
			this.egyptRandomCards = randomCards;
		else if(getName().equals("Greek"))
			this.greekRandomCards = randomCards;
		else
			this.norseRandomCards = randomCards;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	// override the toString for printing
	@Override
    public String toString() {
        return name;
    }
	
}
