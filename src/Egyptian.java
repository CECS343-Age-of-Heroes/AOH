/*
 * Egyptian:
 * 
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	-
 */

public class Egyptian extends Culture {

	// constructor
	public Egyptian() {
		setName("Egyptian");		// set culture name
		initProductionAreaList();	// create production area list
	}
	
	// initialized this cultures production area list of available spots
	private void initProductionAreaList() {
		getProductionAreaList().add("desert");
		getProductionAreaList().add("desert");
		getProductionAreaList().add("swamp");
		getProductionAreaList().add("swamp");
		
		getProductionAreaList().add("forest");
		getProductionAreaList().add("desert");
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("fertile");
		
		getProductionAreaList().add("desert");
		getProductionAreaList().add("desert");
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("fertile");
		
		getProductionAreaList().add("desert");
		getProductionAreaList().add("hill");
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("hill");
	}

}
