/*
 * ActionCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class ActionCard extends Card {
	
//	public String type = "";	// permanent, random, or god
	
	// constructor  // possibly not gunna be used
	public ActionCard() {
		
	}
	
//	// get type
//	public String getType() {
//		return type;
//	}
//	
//	// set type
//	public void setType(String aType) {
//		type = aType;
//	}
	
	// over ridden in derived classes
	public boolean canPlay(Player player) { return false; };
	
	//
	public String toString() {
		return "i";
	}

}
