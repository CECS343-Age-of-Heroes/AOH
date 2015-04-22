import java.util.*;
/*
 * Player:
 * - 
 */
/*
 * ADD:
 * 	- 
 * FIX:
 * 	- change tiles to production tiles  array list
 */

public class Player {
	
	private String name = "";		// name
	private String type = "";		// human or computer
	private Culture culture;		// culture
	private Integer roll = 0;		// value of dice roll
	private Integer place = 0;		// game turn order (1 is starting player)
	private Integer age = 4;		// current age
	private Cubes cubes = new Cubes();			// cubes array 
	// holds tile at correct index in production area
	private ArrayList<ProductionTile> productionTiles = new ArrayList<>();	
	private ArrayList<BuildingTile> buildingTiles = new ArrayList<>();
	private ArrayList<ActionCard> actionCards = new ArrayList<>();
	private int maxTiles = 16;		// max tiles that fit on a board
	
	// constructor
	public Player() {
		initProductionTileList();
		//setRandomCards();
	}
	
	// sets up the players tile list with blank tiles to start
	public void initProductionTileList() {
		for (int i = 0; i < maxTiles; i++) {
			productionTiles.add(new ProductionTile());
		}
	}
	
	public ArrayList<ActionCard> getActionCards() {
		return actionCards;
	}
	
	public void setActionCards(ArrayList<ActionCard> ac) {
		this.actionCards = ac;
	}
	
	
	// get name
	public String getName() {
		return name;
	}

	// set name
	public void setName(String name) {
		this.name = name;
	}
	
	// returns culture
	public Culture getCulture() {
		return culture;
	}

	// sets culture based on radio button selected on StartScreenView
	public void setCulture(String str) {
		if (str == "Greek") {
			culture = new Greek();
		}
		else if (str == "Norse") {
			culture = new Norse();
		}
		else if (str == "Egyptian") {
			culture = new Egyptian();
		}
		culture.setPlayer(this);		// set the cultures player 
	}
	
	// return age
	public Integer getAge() {
		return age;
	}
	
	// set age
	public void setAge(Integer newAge) {
		this.age = newAge;
	}

	// print a string representation of player age
	public String ageToString() {
		String str = "";
		switch(age) { 		
		case 4:	str = "Archaic";   
				break;
		case 5: str = "Classical";
				break;
		case 6: str = "Heroic";
				break;	
		case 7: str = "Mythic";
				break;
		}
		return str;
	}
	
	// get place
	public Integer getPlace() {
		return place;
	}

	// set place 
	public void setPlace(Integer place) {
		this.place = place;
	}
	
	// return type of player (human or computer)
	public String getType() {
		return type;
	}

	// set type of player (human or computer)
	public void setType(String type) {
		this.type = type;
	}

	// return dice roll score
	public Integer getRoll() {
		return roll;
	}
	
	// set dice roll scored
	public void setRoll(Integer roll) {
		this.roll = roll;
	}

	// return tiles
	public ArrayList<ProductionTile> getProductionTiles() {
		return productionTiles;
	}

	// set tiles
	public void setProductionTiles(ArrayList<ProductionTile> tiles) {
		this.productionTiles = tiles;
	}
	
	// return a cubes array with the total board value of a type
	public Cubes getProductionValueOfType(String type) {
		Cubes cubes = new Cubes();
		for (ProductionTile tile : productionTiles) {
			if (tile.getType().equals(type)) {
				cubes.addToColor(tile.getColor(), tile.getValue());
			}
		}
		return cubes;
	}
	
	// return a cubes array with the total board value of a color
	public Cubes getProductionValueOfColor(String color) {
		Cubes cubes = new Cubes();
		for (ProductionTile tile : productionTiles) {
			if (tile.getColor().equals(color)) {
				cubes.addToColor(tile.getColor(), tile.getValue());
			}
		}
		return cubes;
	}

	// return cubes
	public Cubes getCubes() {
		return cubes;
	}

	// set cubes		// needed ??
	public void setCubes(Cubes cubes) {
		this.cubes = cubes;
	}
	
	// removes an array of cubes from the players cubes
	public void removeCubes(Cubes remove) {
		cubes.setCubes(cubes.getBlue() - remove.getBlue(), 
					   cubes.getGreen() - remove.getGreen(), 
					   cubes.getBrown() - remove.getBrown(), 
					   cubes.getYellow() - remove.getYellow());
	}
	
	//
	public boolean hasEnoughResources(Cubes cubesCost) {
		return (cubes.getBlue() >= cubesCost.getBlue() &&
				cubes.getGreen() >= cubesCost.getGreen() &&
				cubes.getBrown() >= cubesCost.getBrown() &&
				cubes.getYellow() >= cubesCost.getYellow());
	}


	public ArrayList<BuildingTile> getBuildingTiles() {
		return buildingTiles;
	}

	public void setBuildingTiles(ArrayList<BuildingTile> buildingTiles) {
		this.buildingTiles = buildingTiles;
	}


	// comparator for sorting the list by dice roll scored
    public static Comparator<Player> playerRollComparator = new Comparator<Player>() {
    	public int compare(Player p1, Player p2) {
    		return p2.getRoll() - p1.getRoll();
   }};
   
}
