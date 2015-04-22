import java.util.*;
/*
 * Cubes:
 * - represents the number of cubes for players and the bank
 * - ArrayList of Integers each integer is a color and the number is how many
 * of that color the player or bank has.
 */
/*
 * Add:
 * 	-
 * FIX:
 * 	-
 */

@SuppressWarnings("serial")
public class Cubes extends ArrayList<Integer> {
	// Integer name is the color of the cube and value is how many of each
	private Integer green = 0;
	private Integer blue = 0;
	private Integer brown = 0;
	private Integer yellow = 0;
	private Integer red = 0;
	
	// constructor
	public Cubes() {
		add(blue);
		add(green);
		add(brown);
		add(yellow);
		add(red);
	}
	
	// constructor
	public Cubes(Integer blues, Integer greens, Integer browns, 
				 Integer yellows, Integer reds) {
		blue = blues;
		green = greens;
		brown = browns;
		yellow = yellows;
		red = reds;
	}
	
	//
	public void setCubes(int numBlue, int numGreen, int numBrown, int numYellow) {
		blue = numBlue;
		green = numGreen;
		brown = numBrown;
		yellow = numYellow;
	}
	
	// add a value to a color of cube
	public void addToColor(String color, int value) {
		if (color.equals("blue")) {
			setBlue(getBlue() + value);
		}
		else if (color.equals("green")) {
			setGreen(getGreen() + value);
		}
		else if (color.equals("brown")) {
			setBrown(getBrown() + value);
		}
		else if (color.equals("yellow")) {
			setYellow(getYellow() + value);
		}
		else if (color.equals("red")) {
			setRed(getRed() + value);
		}
	}
	
	//
	public void addCubesToCubes(Cubes otherCubes) {
		blue += otherCubes.getBlue();
		green += otherCubes.getGreen();
		brown += otherCubes.getBrown();
		yellow += otherCubes.getYellow();
	}
	
	//
	public void removeCubesFromCubes(Cubes otherCubes) {
		blue -= otherCubes.getBlue();
		green -= otherCubes.getGreen();
		brown -= otherCubes.getBrown();
		yellow -= otherCubes.getYellow();
	}
	
	// get and set green
	public Integer getGreen() {
		return green;
	}

	public void setGreen(Integer green) {
		this.green = green;
	}

	// get and set blue
	public Integer getBlue() {
		return blue;
	}

	public void setBlue(Integer blue) {
		this.blue = blue;
	}

	// get and set brown
	public Integer getBrown() {
		return brown;
	}

	public void setBrown(Integer brown) {
		this.brown = brown;
	}

	// get and set yellow
	public Integer getYellow() {
		return yellow;
	}

	public void setYellow(Integer yellow) {
		this.yellow = yellow;
	}

	// get and set red
	public Integer getRed() {
		return red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}
	
	// override toString() to print
	public String toString() {
		String str = "";
		str += "Blue: " + getBlue() + "\n";
		str += "Green: " + getGreen() + " \n";
		str += "Brown: " + getBrown() + " \n";
		str += "Yellow: " + getYellow() + " \n";
		str += "Red: " + getRed() + " \n";
		return str;
	}
		
	//
	public String costToString() {
		String str = "";
		if (blue != 0) {
			//str += "Blue: " + getBlue() + "\n";
			str += "B:" + getBlue() + "\n";
		}
		if (green != 0) {
			//str += "Green: " + getGreen() + " \n";
			str += "G:" + getGreen() + " \n";
		}
		if (brown != 0) {
			//str += "Brown: " + getBrown() + " \n";
			str += "B:" + getBrown() + " \n";
		}
		if (yellow != 0) {
			//str += "Yellow: " + getYellow() + " \n";
			str += "Y:" + getYellow() + " \n";
		}
		if (red != 0) {
			//str += "Red: " + getRed() + " \n";
			str += "R:" + getRed() + " \n";
		}
		return str;
	}
}
