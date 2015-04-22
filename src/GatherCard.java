/*
 * GatherCard:
 * 
 */
/*
 * Add:
 * 	-
 * Fix:
 * 	-
 */
@SuppressWarnings("serial")
public class GatherCard extends ActionCard {

	private String resourceType = "";		// 1/one or All
	
	
	// constructor
	public GatherCard(String resources) {
		setName("Gather");
		resourceType = resources;
	}
	
	
	// return the number of resources this card is aloud to take
	public String getResourceType() {
		return resourceType;
	}
	
	//
	//public boolean executeActionCard(Player player, String selection) {
	public boolean executeActionCard(Player player) {
		System.out.println("execute action card - Gather");
		
		// nned to pay first
		
		if (resourceType.equals("one")) { // or is it '1'
//			if (selection.equals("type")) {
//				
//			}
//			else if (selection.equals("color")) {
//				
//			}
		}
		else if (resourceType.equalsIgnoreCase("all")) {
			
		}
		
		return false;
	}
	
	
	@Override
	public String toString() {
		return getName() + " " + resourceType;
	}
}
