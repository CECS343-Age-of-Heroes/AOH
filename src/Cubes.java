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
		add(green);
		add(blue);
		add(brown);
		add(yellow);
		add(red);
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
		str += "Green: " + getGreen() + " \n";
		str += "Blue: " + getBlue() + "\n";
		str += "Brown: " + getBrown() + " \n";
		str += "Yellow: " + getYellow() + " \n";
		str += "Red: " + getRed() + " \n";
		return str;
	}
		
}
