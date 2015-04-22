import java.util.*;
/*
 * GameBuildingTiles:
 * - represents the pool of game building tiles available to every player 
 * 	as an ArrayList
 */
/*
 * Add:
 * 	- 
 * 
 * FIX:
 * 	-
 */
@SuppressWarnings("serial")
public class GameBuildingTiles extends ArrayList<BuildingTile> {

	//private ArrayList<String> buildingsList = new ArrayList<>();
	private ArrayList<BuildingTile> buildingsList = new ArrayList<>();
	
	// constructor
	public GameBuildingTiles() {
		createSetOfBuildingTiles();
		initBuildingsList();
	}
	
	// add building tiles to game building tiles array list
	private void createSetOfBuildingTiles() {
		
		// house x40
		for (int i = 0; i < 40; i++) {
			add(new BuildingTile("House", new Cubes(0, 2, 2, 0, 0)));
		}
		// great temple x2
		for (int i = 0; i < 2; i++) {
			add(new BuildingTile("Great Temple", new Cubes(4, 4, 4, 4, 0)));
		}
		// the wonder x1
		add(new BuildingTile("The Wonder", new Cubes(10, 10, 10, 10, 0)));
		// others x4
		for (int i = 0; i < 4; i++) {
			add(new BuildingTile("Storehouse", new Cubes(2, 2, 2, 2, 0)));
			add(new BuildingTile("Gold Mint", new Cubes(0, 3, 2, 0, 0)));
			add(new BuildingTile("Wood Workshop", new Cubes(0, 2, 0, 3, 0)));
			add(new BuildingTile("Granary", new Cubes(0, 0, 2, 3, 0)));
			add(new BuildingTile("Quarry", new Cubes(0, 3, 0, 1, 0)));
			add(new BuildingTile("Monument", new Cubes(0, 3, 2, 0, 0)));
			add(new BuildingTile("Market", new Cubes(2, 0, 0, 3, 0)));
		}
	}
	
	//
	public BuildingTile removeBuildingWithName(String name) {
		//this.contains(o);
		BuildingTile bt = new BuildingTile("Nothing Found", new Cubes());
		for (BuildingTile tile : this) {
			if (tile.getName().equals(name)) {
				System.out.println("assigning building");
				bt = tile;
				//this.remove(tile);
			}
		}
		System.out.println("Building removed =  " + bt.getName());
		return bt;
	}
	
	//
	private void initBuildingsList() {
		buildingsList.add(new BuildingTile("House", new Cubes(0, 2, 2, 0, 0)));
		buildingsList.add(new BuildingTile("Great Temple", new Cubes(4, 4, 4, 4, 0)));
		buildingsList.add(new BuildingTile("The Wonder", new Cubes(10, 10, 10, 10, 0)));
		buildingsList.add(new BuildingTile("Storehouse", new Cubes(2, 2, 2, 2, 0)));
		buildingsList.add(new BuildingTile("Gold Mint", new Cubes(0, 3, 2, 0, 0)));
		buildingsList.add(new BuildingTile("Wood Workshop", new Cubes(0, 2, 0, 3, 0)));
		buildingsList.add(new BuildingTile("Granary", new Cubes(0, 0, 2, 3, 0)));
		buildingsList.add(new BuildingTile("Quarry", new Cubes(0, 3, 0, 1, 0)));
		buildingsList.add(new BuildingTile("Monument", new Cubes(0, 3, 2, 0, 0)));
		buildingsList.add(new BuildingTile("Market", new Cubes(2, 0, 0, 3, 0)));
	}
	
	//
	//public ArrayList<String> getBuildingsList() {
	public ArrayList<BuildingTile> getBuildingsList() {
		return buildingsList;
	}
}



