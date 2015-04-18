/*
 * TradeCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class TradeCard extends ActionCard {
	
	private int tradeCost = 0;
	private String godName = "";
	
	// constructor
	public TradeCard(int cost) {
		setName("Trade");
		tradeCost = cost;
	}
	public TradeCard(int cost, String name){
		setName("Trade");
		tradeCost = cost;
		godName = name;
	}

	
	// return the number of resources this card is aloud to take
	public int getTradeCost() {
		return tradeCost;
	}
	
	@Override
	public String toString() {
		return getName() + " " + tradeCost + " " + godName;
	}
}
