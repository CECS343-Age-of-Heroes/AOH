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
	
	// constructor
	public TradeCard(int cost) {
		setName("Trade");
		tradeCost = cost;
	}

	
	// return the number of resources this card is aloud to take
	public int getTradeCost() {
		return tradeCost;
	}
	
	//
	public String getValue() {
		return String.valueOf(tradeCost);
	}
	
	//
	@Override
	public String toString() {
		return getName() + " " + tradeCost;
	}
}
