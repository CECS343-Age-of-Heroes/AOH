import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/*
 * VictoryCubePlacementView:
 * - shows the victory cards and lets players select what card they would like
 *   to place there victory cube on for this round
 */
/*
 * ADD:
 *	- 
 * FIX:
 * 	-
 */
@SuppressWarnings("serial")
public class VictoryCubePlacementView extends JPanel {
	// get the games victory cards from the bank 
//	private Cubes cubes = Main.gc.getBank().getCubes();
	private VictoryCard theWonder = Main.gc.getBank().getTheWonder();
	private VictoryCard mostBuildings = Main.gc.getBank().getMostBuildings();
	public JButton victoryDoneButton = new JButton("Finish");
	// keeps track of number of cubes placed this round
	private int clickCount = 0;		// and keeps track of card clicks
	private int maxClicks = 3;		// max number of card clicks
	
	// constructor
	public VictoryCubePlacementView() {
		setLayout(null);
		setSize(1200, 822); // ??
		setBackground(Color.GREEN);
		setBorder(BorderFactory.createMatteBorder(3, 4, 5, 6, Color.BLUE));
		
		JLabel headerLabel = new JLabel("- Victory Cube Placement -",
										JLabel.CENTER);
		headerLabel.setSize(1200, 100);
		Font hF = new Font("Default", Font.BOLD, 35);
		headerLabel.setFont(hF);
		headerLabel.setForeground(Color.BLUE);
		
		// add action listener for buttons
		theWonder.addActionListener(new OptionListener());
		mostBuildings.addActionListener(new OptionListener());
		// set size for card/buttons
		theWonder.setBounds(300, 200, 250, 350);
		mostBuildings.setBounds(650, 200, 250, 350);
		
		victoryDoneButton.setSize(200, 50);
		int centerX = getWidth() / 2 - victoryDoneButton.getWidth() / 2;
		victoryDoneButton.setLocation(centerX, 650);
		victoryDoneButton.setVisible(false);	// hide button until 3 cubes
		
		// add all components
		add(headerLabel);
		add(theWonder);
		add(mostBuildings);
		add(victoryDoneButton);
		// simulate computer choices for victory cube choices
		placeComputerCubes();
	}
	
	// random victory card selection for computer players
	private void placeComputerCubes() {
		for (int i = 0; i < maxClicks-1; i++) {
			if (new Random().nextInt(2) == 0) {
				theWonder.doClick();
			}
			else {
				mostBuildings.doClick();
			}
		}
	}
	
// // OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	private class OptionListener implements ActionListener
	{   
		@Override
		public void actionPerformed(ActionEvent e) {
			// dont do anything if click count is reached 
			if (clickCount < maxClicks) {
				if (e.getSource() instanceof JButton) {
					if (( (JButton)e.getSource()).equals(theWonder)) {
						theWonder.setCubeCount(theWonder.getCubeCount() + 1);
						theWonder.updateValueLabel();
					}
					// 
					else if (( (JButton)e.getSource()).equals(mostBuildings)) {
						mostBuildings.setCubeCount(mostBuildings.getCubeCount() + 1);
						mostBuildings.updateValueLabel();
					}
					// take a red cube from bank and put it on the card
					//cubes.setRed(cubes.getRed() - 1);
					Main.gc.getBank().removeCubes("red", 1);
					clickCount++;		// increment click count
				}
			}
			// after 3 clicks (2 by computer) on victory cards, show done button
			if (clickCount == maxClicks) {
				victoryDoneButton.setVisible(true);
			}
		}	
	}
}
