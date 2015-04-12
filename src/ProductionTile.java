/*
 * Tile:
 * - represents a tile in the game
 * - the type of the tile and the color and value of the cube printed on the 
 *   tile
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	- change to production tile only ??
 */
public class ProductionTile {

	private String type;		// type of tile 
	private String color;		// color of cube on the tile
	private int value;			// number of cubes on the tile
	
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

	// get value
	public int getValue() {
		return value;
	}

	// set value
	public void setValue(int value) {
		this.value = value;
	}

	// override toString method to print tile information
	@Override
    public String toString() {
        return getType() + "\n" + getColor() + "\n" + getValue(); 
    } 
		
}
