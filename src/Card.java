import javax.swing.*;
/*
 * Card:
 * -
 */

/*
 * ADD:
 * 	-
 * FIX:
 * 	-
 */

@SuppressWarnings("serial")
public class Card extends JButton {
	
	private String name = "";
	
	// default constructor
	public Card() {
		
	}
	
	// constructor
	public Card(String aName) {
		name = aName;
	}

	// return name
	public String getName() {
		return name;
	}

	// set name
	public void setName(String name) {
		this.name = name;
	}	
	
}
