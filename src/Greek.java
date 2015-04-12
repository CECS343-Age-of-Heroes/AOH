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
		getRandomCards().add(new BuildCard("Build 3"));
		getRandomCards().add(new BuildCard("Build 2"));
		getRandomCards().add(new BuildCard("Build 2"));
		getRandomCards().add(new BuildCard("Build 3","Hera"));
		getRandomCards().add(new ExploreCard("Explore 2"));
		getRandomCards().add(new ExploreCard("Explore 2"));
		getRandomCards().add(new ExploreCard("Explore 0"));
		getRandomCards().add(new ExploreCard("Explore 0"));
		getRandomCards().add(new ExploreCard("Explore 2","Artemis"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather 1","Poseidon"));
		getRandomCards().add(new GatherCard("Gather 1","Hades"));
		getRandomCards().add(new GatherCard("Gather 0","Dionysus"));
		getRandomCards().add(new NextAgeCard("Next Age 345"));
		getRandomCards().add(new NextAgeCard("Next Age 345"));
		getRandomCards().add(new NextAgeCard("Next Age 345","Valcano"));
		getRandomCards().add(new NextAgeCard("Next Age 345","Zeus"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 0","Hermes"));
	}
}
