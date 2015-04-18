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
	private String godName = "";
	
	// constructor
	public GatherCard(String resources) {
		setName("Gather");
		resourceType = resources;
	}
	public GatherCard(String resources, String name) {
		setName("Gather");
		resourceType = resources;
		godName = name;
	}
	
	// return the number of resources this card is aloud to take
	public String getResourceType() {
		return resourceType;
	}
	
	
	@Override
	public String toString() {
		return getName() + " " + resourceType + " " + godName;
	}
}
