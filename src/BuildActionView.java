import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ActionView.ActionCardListener;


public class BuildActionView {

	public BuildActionView() {
		// TODO Auto-generated constructor stub
	}
	
	public void asdfasdf() {
		// put radioButtons in the action panel with buildings that the player
		// can build
		private void buildingPanel() {
			currentView = "build";
			removeAll();
			setLayout(null);
			
			JPanel mainPanel = new JPanel();
			mainPanel.setBounds(0, 0, 300, 800);
			mainPanel.setBackground(Color.GREEN);
		
			//JLabel header = new JLabel("Select Building:");
			cardLabel.setPreferredSize(new Dimension(300, 50));
			mainPanel.add(cardLabel);
		
			JPanel buildingPanel = new JPanel();
			buildingPanel.setPreferredSize(new Dimension(300, 500));
			ArrayList<BuildingTile> buildings = Main.gc.getGameBuildingTiles().getBuildingsList();
			buildingPanel.setLayout(new GridLayout(buildings.size(), 2));
			ButtonGroup buttonGroup = new ButtonGroup();	// holds 3 radio buttons	
			
			for (BuildingTile building: buildings) {
				// if player does not have this building then add it as an option
				
				boolean hasOne = false;
				int numHouses = 0;
				for (BuildingTile tile : currentPlayer.getBuildingTiles()) {
					if (tile.getName().equals(building.getName())) {
						hasOne = true;
						if (tile.getName().equals("House")) {
							numHouses++;
						}
					}
				}
				boolean canPay = false;
				if (currentPlayer.hasEnoughResources(building.getCubeCost())) {
					canPay = true;
				}
				
				JRadioButton rb = new JRadioButton(building.getName());
				rb.addActionListener(new ActionCardListener());
				buttonGroup.add(rb);
				JLabel costLabel = new JLabel(building.getCubeCost().costToString());
				
				if (canPay) {
					if (building.getName().equals("House") && 
							numHouses <= 10 || !hasOne) {
						buildingPanel.add(rb);
						buildingPanel.add(costLabel);
					}
				}
				else if (!canPay) {
					if (building.getName().equals("House") && 
							numHouses <= 10 || !hasOne) {
						JLabel b = new JLabel(building.getName() + " (More$)");
						b.setForeground(Color.RED);
						buildingPanel.add(b);
						costLabel.setForeground(Color.RED);
						buildingPanel.add(costLabel);
					}
				}
			}
			mainPanel.add(buildingPanel);
				
			JButton buildButton = new JButton("Build It");
			buildButton.addActionListener(new ActionCardListener());
			mainPanel.add(buildButton);
			
			doneButton.addActionListener(new ActionCardListener());
			doneButton.setVisible(false);
			mainPanel.add(doneButton);
			
			add(mainPanel);
			revalidate();
			repaint();
		}
	}

}
