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
		initRandomActionCards();
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
		getPermanentCards().add(new NextAgeCard(456));
		getPermanentCards().add(new BuildCard(1));
		getPermanentCards().add(new ExploreCard(4));
		getPermanentCards().add(new GatherCard("type"));
		getPermanentCards().add(new TradeCard(2));
	}
	
	// initalize random action cards
	private void initRandomActionCards() {
		// next age
		getRandomCards().add(new NextAgeCard(345));
		getRandomCards().add(new NextAgeCard(345));
		// build
		getRandomCards().add(new BuildCard(2));
		getRandomCards().add(new BuildCard(2));
		for (int i = 0; i < 3; i++) {
			getRandomCards().add(new BuildCard(3));
		}
		getRandomCards().add(new BuildCard(4));
		// explore
		for (int i = 0; i < 2; i++) {
			getRandomCards().add(new ExploreCard(5));
			getRandomCards().add(new ExploreCard(3));
		}
		// gather
		for (int i = 0; i < 5; i++) {
			getRandomCards().add(new GatherCard("All"));
		}
		// trade
		for (int i = 0; i < 5; i++) {
			getRandomCards().add(new TradeCard(1));
		}
		
	}
	
}
