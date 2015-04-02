import java.awt.*;
import javax.swing.*;
/*
 * VictoryCard
 * - represents a victory card in the game, 
 *   name of card, number of victory cubes on that card
 * - need to set the bounds of the card in the class used 
 */
/*
 * ADD:
 * 	-
 * FIX:
 *	-
 */
@SuppressWarnings("serial")
public class VictoryCard extends Card {
	
	private Integer cubeCount = 0;
	private JLabel valueLabel;
	private JLabel nameLabel; 
	//private JTextArea nameLabel;
	
	// constructor
	public VictoryCard(String name) {
		setLayout(new BorderLayout());
		setName(name);
		//victoryCard.setSize(new Dimension(250, 350));
		
		nameLabel = new JLabel(getName(), JLabel.CENTER);
		//nameLabel = new JLabel("<html>" + getName() + "</html>", JLabel.CENTER);
		//nameLabel = new JTextField(getName(), JLabel.CENTER);
		
		//nameLabel = new JTextArea(getName());
		//nameLabel.setOpaque(false);
		//nameLabel.setLineWrap(true);
		//nameLabel.setWrapStyleWord(true);
		
		nameLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		valueLabel = new JLabel("Red x" + String.valueOf(getCubeCount()), 
							    JLabel.CENTER);

		add(nameLabel, BorderLayout.NORTH);
		add(valueLabel, BorderLayout.CENTER);
	}
	
	// update font
	public void updateFont(Font vcf) {
		nameLabel.setFont(vcf);
	}
		
	// return cube count
	public Integer getCubeCount() {
		return cubeCount;
	}

	// set cube count
	public void setCubeCount(Integer newCubeCount) {
		this.cubeCount = newCubeCount;
	}
	
	// update value label
	public void updateValueLabel() {
		valueLabel.setText("Red x" + String.valueOf(getCubeCount()));
	}
	
}
