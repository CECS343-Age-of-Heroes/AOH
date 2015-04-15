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
		initPermanentActionCards();
		initRandomActionCards();
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
	
	// initialize permanent action cards
	private void initPermanentActionCards() {
		getPermanentCards().add(new NextAgeCard(456));
		getPermanentCards().add(new BuildCard(1));
		getPermanentCards().add(new ExploreCard(4));
		getPermanentCards().add(new GatherCard("type"));
		getPermanentCards().add(new TradeCard(2));
	}
	
	// initialize random action cards
	private void initRandomActionCards() {
		// next age
		for (int i = 0; i < 3; i++) {
			getRandomCards().add(new NextAgeCard(345));
		}
		// build
		getRandomCards().add(new BuildCard(2));
		getRandomCards().add(new BuildCard(2));
		for (int i = 0; i < 3; i++) {
			getRandomCards().add(new BuildCard(3));
		}
		getRandomCards().add(new BuildCard(4));
		// explore
		
		getRandomCards().add(new ExploreCard(3));
		getRandomCards().add(new ExploreCard(3));
		getRandomCards().add(new ExploreCard(4));
		getRandomCards().add(new ExploreCard(5));
		getRandomCards().add(new ExploreCard(5));
		
		// gather
		for (int i = 0; i < 5; i++) {
			getRandomCards().add(new GatherCard("All"));
		}
		// trade
		for (int i = 0; i < 4; i++) {
			getRandomCards().add(new TradeCard(1));
		}
		
	}
	
}
