/*
 * Nores:
 * - 
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	-
 */

public class Norse extends Culture {
	
	// constructor
	public Norse() {
		setName("Norse");			// set culture name
		initProductionAreaList();	// create production area list
	}

	// initialized this cultures production area list of available spots
	private void initProductionAreaList() {
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("mountain");
		getProductionAreaList().add("mountain");
		getProductionAreaList().add("mountain");
		
		getProductionAreaList().add("fertile");
		getProductionAreaList().add("forest");
		getProductionAreaList().add("hill");
		getProductionAreaList().add("mountain");
		
		getProductionAreaList().add("hill");
		getProductionAreaList().add("swamp");
		getProductionAreaList().add("forest");
		getProductionAreaList().add("hill");
		
		getProductionAreaList().add("desert");
		getProductionAreaList().add("forest");
		getProductionAreaList().add("forest");
		getProductionAreaList().add("fertile");
	}
	
}
