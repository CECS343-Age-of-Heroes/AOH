import java.awt.*;

import javax.swing.*;
/*
 * Bank:
 * - holds the number of cubes available for the game
 * - hold the games victory cards
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	- change to just "mostBuildings"
 */

@SuppressWarnings("serial")
public class Bank extends JPanel {
	
	private Cubes cubes = new Cubes();
	private VictoryCard theWonder = new VictoryCard("The Wonder");
	private VictoryCard mostBuildings = new VictoryCard("Most Buildings");

	// constructor
	public Bank() {
		System.out.println("Creating Bank");
		setBackground(Color.red);
		setPreferredSize(new Dimension(750, 100));
		// set initial values for a new bank with 3 players
		cubes.setBlue(25);
		cubes.setGreen(25);
		cubes.setBrown(25);
		cubes.setYellow(25);
		cubes.setRed(30);
		//System.out.println("Bank Cubes: " + "\n" + cubes);
	}
	
	// returns the banks cubes array list
	public Cubes getCubes() {
		return cubes;
	}
	
	// removes a number of cubes of a given color
	public void removeCubes(String color, Integer num) {
		if (color.equals("blue")) {
			cubes.setBlue(cubes.getBlue() - num);
		}
		else if (color.equals("green")) {
			cubes.setGreen(cubes.getGreen() - num);
		}
		else if (color.equals("brown")) {
			cubes.setBrown(cubes.getBrown() - num);
		}
		else if (color.equals("yellow")) {
			cubes.setYellow(cubes.getYellow() - num);
		}
		else if (color.equals("red")) {
			cubes.setRed(cubes.getRed() - num);
		}
		else {	// temp for testing purposes // del
			System.out.println("Something Wrong");
		}
	}

	// return "the wonder" victory card
	public VictoryCard getTheWonder() {
		return theWonder;
	}
	
	// return "most buildings" victory card
	public VictoryCard getMostBuildings() {
		return mostBuildings;
	}
		
}
