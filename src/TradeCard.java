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

	public TradeCard(String aType) {
		setName(aType);
		setType(aType);
	}
	public TradeCard(String a, String b){
		setName(a+" "+b);
		setType(b);
	}

}
