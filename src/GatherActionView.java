import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class GatherActionView extends JPanel {

	private ActionView av = Main.gvc.getGamePlayView().getActionView();
	private Player currentPlayer;		
	private String resourceType = "";	// card value
	private String radioButtonText = "";	// selected button text
	private ArrayList<String> colors = new ArrayList<String>(Arrays.asList
									("blue", "green", "brown", "yellow"));
	private ArrayList<String> terrains = new ArrayList<String>(Arrays.asList
				("desert", "swamp", "forest", "fertile", "hill", "mountain"));
	
	// constructor
	public GatherActionView(Player player, String cardValue) {
		currentPlayer = player;
		resourceType = cardValue;
		
		setLayout(null);
		setBounds(0, 150, 300, 600);
		setBackground(Color.YELLOW);
		
		JLabel headerLabel = new JLabel("Select Resource Type to Gather:");
		headerLabel.setBounds(10, 10, 290, 50);
		add(headerLabel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(25, 50, 275, 350);
		buttonPanel.setBackground(Color.GREEN);

		if (resourceType.equalsIgnoreCase("type")) {
			buttonPanel.setLayout(new GridLayout(1,2));
			ButtonGroup bg = new ButtonGroup();
			
			JPanel terrainPanel = new JPanel();
			terrainPanel.setBackground(Color.YELLOW);
			terrainPanel.setLayout(new GridLayout(7,1));
			
			JLabel terrainLabel = new JLabel("Terrain");
			terrainPanel.add(terrainLabel);
			
			for (int i = 0; i < terrains.size(); i++) {
				JRadioButton rb = new JRadioButton(terrains.get(i));
				rb.addActionListener(new GatherCardListener());
				bg.add(rb);
				terrainPanel.add(rb);
			}
			
			JPanel colorsPanel = new JPanel();
			colorsPanel.setBackground(Color.YELLOW);
			colorsPanel.setLayout(new GridLayout(7,1));
			
			JLabel colorLabel = new JLabel("Type");
			colorsPanel.add(colorLabel);
			
			for (int i = 0; i < colors.size(); i++) {
				JRadioButton rb = new JRadioButton(colors.get(i));
				rb.addActionListener(new GatherCardListener());
				bg.add(rb);
				colorsPanel.add(rb);
			}
			buttonPanel.add(terrainPanel);
			buttonPanel.add(colorsPanel);
			
		}
		else if (resourceType.equalsIgnoreCase("all")) {
			buttonPanel.setLayout(null);
			
			JLabel allLabel = new JLabel("All Resources", JLabel.CENTER);
			allLabel.setBounds(20, 50, 250, 100);
			buttonPanel.add(allLabel);
			
		}
		add(buttonPanel);
				
		JButton button = new JButton("Gather");
		button.setBounds(50, 450, 200, 50);
		button.addActionListener(new GatherCardListener());
		add(button);
		
	}
	
	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	private class GatherCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			//
			if (e.getSource() instanceof JButton) {
				JButton button = (JButton)e.getSource();
				
				// 
				if (button.getText().equals("Gather")) {
					
					for (int i = 0; i < 3; i++) {
						Cubes cubesValue = new Cubes();
						if (resourceType.equals("type")) {
							if (radioButtonText != "") {
								if (terrains.contains(radioButtonText)) {
									cubesValue = currentPlayer.getProductionValueOfType(radioButtonText);
								}
								else if (colors.contains(radioButtonText)) {
									cubesValue = currentPlayer.getProductionValueOfColor(radioButtonText);
								}
							}
						}
						else if (resourceType.equalsIgnoreCase("all")) {
							cubesValue = currentPlayer.getProductionValueAll();
						}
						
						currentPlayer.getCubes().addCubesToCubes(cubesValue);
						Main.gc.getBank().getCubes().removeCubesFromCubes(cubesValue);
						currentPlayer = Main.gc.getNextPlayer(currentPlayer);
					}
					
					button.setEnabled(false);
					av.updateBoardAndBank();
					
					/* 
					 * need to check if bank has enough reources
					 * just put out what is avaliable
					*/				
				}
			}
			else if (e.getSource() instanceof JRadioButton) {
				if ( ((JRadioButton)e.getSource()).isSelected() ) {
					radioButtonText = ((JRadioButton)e.getSource()).getText();
				}
			}	
		}
	}

}
