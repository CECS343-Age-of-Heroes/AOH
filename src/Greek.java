/*
 * Greek:
 * - 
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	-
 */

public class Greek extends Culture {
	
	// constructor
	public Greek() {
		setName("Greek");			// set culture name
		initProductionAreaList();	// create production area list
		initPermanentActionCards();
	}
	
	// initialize this cultures production area list of available spots
	private void initProductionAreaList() {
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("forest");
		getProductionAreaList().add("swamp");
		
		getProductionAreaList().add("hill");
		getProductionAreaList().add("mountain");
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("forest");
		
		getProductionAreaList().add("hill");
		getProductionAreaList().add("hill");
		getProductionAreaList().add("hill");
		getProductionAreaList().add("hill");
		
		getProductionAreaList().add("desert");
		getProductionAreaList().add("hill");
		getProductionAreaList().add("hill");
		getProductionAreaList().add("hill");
	}

	// initialize permanent action cards
	private void initPermanentActionCards() {
		
		getPermanentCards().add(new GatherCard("god"));
		
		
	}
}
