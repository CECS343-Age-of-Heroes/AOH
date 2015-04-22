/*
 * ProductionTile:
 * - represents a tile in the game
 * - the type of the tile and the color and value of the cube printed on the 
 *   tile
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	- 
 */
public class ProductionTile {

	private String type;		// type of tile 
	private String color;		// color of cube on the tile
	private int value;			// number of cubes on the tile
	private boolean hasVillager = false;
	
	// constructor default		
	public ProductionTile() {
		type = "";
		color = "";
		value = 0;
	}
	
	// constructor with arguments
	public ProductionTile(String type, String color, int value) {
		this.setType(type);
		this.setColor(color);
		this.setValue(value);
	}

	// return type
	public String getType() {
		return type;
	}

	// set type
	public void setType(String type) {
		this.type = type;
	}

	// return color
	public String getColor() {
		return color;
	}

	// set color
	public void setColor(String color) {
		this.color = color;
	}

	// get value, check first if tile has a villager on it
	public int getValue() {
		if (hasVillager) {
			return value + 1;
		}
		return value;
	}

	// set value
	public void setValue(int value) {
		this.value = value;
	}
	
	// 
	public boolean getHasVillager() {
		return hasVillager;
	}
	
	//
	public void setHasVillager(boolean yesNo) {
		hasVillager = yesNo;
	}

	// override toString method to print tile information
	@Override
    public String toString() {
        return getType() + "\n" + getColor() + "\n" + getValue()
        		+ getHasVillager(); 
    } 
		
}
