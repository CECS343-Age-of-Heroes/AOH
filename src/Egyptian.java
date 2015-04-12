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
		initPermanentActionCards();
		initRandomActionCards();
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
	private void initPermanentActionCards(){
		getPermanentCards().add(new NextAgeCard("NextAge 456"));
		getPermanentCards().add(new BuildCard("Build 1"));
		getPermanentCards().add(new ExploreCard("Explore 1"));
		getPermanentCards().add(new GatherCard("Gather 1"));
		getPermanentCards().add(new TradeCard("Trade 2"));
	}
	private void initRandomActionCards() {
		getRandomCards().add(new BuildCard("Build 4"));
		getRandomCards().add(new BuildCard("Build 3"));
		getRandomCards().add(new BuildCard("Build 3"));
		getRandomCards().add(new BuildCard("Build 2"));
		getRandomCards().add(new BuildCard("Build 2"));
		getRandomCards().add(new BuildCard("Build 3","Nephthys"));
		getRandomCards().add(new BuildCard("Build 3","Horus"));
		getRandomCards().add(new ExploreCard("Explore 2"));
		getRandomCards().add(new ExploreCard("Explore 2"));
		getRandomCards().add(new ExploreCard("Explore 0"));
		getRandomCards().add(new ExploreCard("Explore 0"));
		getRandomCards().add(new ExploreCard("Explore 2","Ptah"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather 0","Ra"));
		getRandomCards().add(new NextAgeCard("Next Age 345"));
		getRandomCards().add(new NextAgeCard("Next Age 345"));
		getRandomCards().add(new NextAgeCard("Next Age 345","Set"));
		getRandomCards().add(new NextAgeCard("Next Age 345","Hathor"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
	}

}
