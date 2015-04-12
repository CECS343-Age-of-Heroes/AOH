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
		getRandomCards().add(new BuildCard("Build 4","Njord"));
		getRandomCards().add(new ExploreCard("Explore 2"));
		getRandomCards().add(new ExploreCard("Explore 2"));
		getRandomCards().add(new ExploreCard("Explore 0"));
		getRandomCards().add(new ExploreCard("Explore 0"));
		getRandomCards().add(new ExploreCard("Explore 0","Baldr"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather All"));
		getRandomCards().add(new GatherCard("Gather 1","Thor"));
		getRandomCards().add(new GatherCard("Gather 1","Freyia"));
		getRandomCards().add(new GatherCard("Gather 1","Skadi"));
		getRandomCards().add(new NextAgeCard("Next Age 345"));
		getRandomCards().add(new NextAgeCard("Next Age 345"));
		getRandomCards().add(new NextAgeCard("Next Age 345"));
		getRandomCards().add(new NextAgeCard("Next Age 345","Odin"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 1"));
		getRandomCards().add(new TradeCard("Trade 0","Forseti"));
		getRandomCards().add(new TradeCard("Trade 0","Loki"));
	}
}
