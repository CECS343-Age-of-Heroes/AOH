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
		getPermanentCards().add(new NextAgeCard("permanent1"));
		getPermanentCards().add(new BuildCard("permanent2"));
		getPermanentCards().add(new ExploreCard("permanent3"));
		getPermanentCards().add(new GatherCard("permanent4"));
		getPermanentCards().add(new TradeCard("permanent5"));
	}
	
	private void initRandomActionCards() {
		getRandomCards().add(new NextAgeCard("random0"));
		getRandomCards().add(new BuildCard("random1"));
		getRandomCards().add(new ExploreCard("random2"));
		getRandomCards().add(new GatherCard("random3"));
		getRandomCards().add(new TradeCard("random4"));
		getRandomCards().add(new NextAgeCard("random5")); 
		getRandomCards().add(new BuildCard("random6"));
		getRandomCards().add(new ExploreCard("random7"));
		getRandomCards().add(new GatherCard("random8"));
		getRandomCards().add(new TradeCard("random9"));
	}
	
}
