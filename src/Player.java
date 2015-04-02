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
	private ArrayList<Tile> tiles = new ArrayList<>();	
	//private ArrayList<Card> randomCards = new ArrayList<>();
	//private ArrayList<Card> perminantCards = new ArrayList<>();
	private int maxTiles = 16;		// max tiles that fit on a board
	
	// constructor
	public Player() {
		initTileList();
	}
	
	// sets up the players tile list with blank tiles to start
	public void initTileList() {
		for (int i = 0; i < maxTiles; i++) {
			tiles.add(new Tile());
		}
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
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	// set tiles
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

	// return cubes
	public Cubes getCubes() {
		return cubes;
	}

	// set cubes		// needed ??
	public void setCubes(Cubes cubes) {
		this.cubes = cubes;
	}


	// comparator for sorting the list by dice roll scored
    public static Comparator<Player> playerRollComparator = new Comparator<Player>() {
    	public int compare(Player p1, Player p2) {
    		return p2.getRoll() - p1.getRoll();
   }};
   
}
