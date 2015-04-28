import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class BuildActionView extends JPanel
{	
	private ActionView av = Main.gvc.getGamePlayView().getActionView();
	private Player currentPlayer;
	private int numToBuild = 0;
	private JButton doneButton = new JButton();
	private String radioButtonText;
	private int count = 0;
	
	
	public BuildActionView(Player player, int cardValue) {
		
		numToBuild = cardValue;
		currentPlayer = player;
		System.out.println("numToBUild = " + numToBuild);
		
		setLayout(null);
		setBounds(0, 150, 300, 600);
		setBackground(Color.YELLOW);
	
		setupBuildPanel();
	}
	
	private void setupBuildPanel() {
		removeAll(); 
		
		JPanel buildingPanel = new JPanel();
		//buildingPanel.setPreferredSize(new Dimension(300, 500));
		buildingPanel.setBounds(0, 0, 300, 500);
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
			rb.addActionListener(new BuildCardListener());
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
		
		add(buildingPanel);
			
		JButton buildButton = new JButton("Build It");
		buildButton.setBounds(50, 525, 200, 50);
		buildButton.addActionListener(new BuildCardListener());
		add(buildButton);
		
		
//				doneButton.addActionListener(new BuildCardListener());
//				doneButton.setVisible(false);
//				add(doneButton);
		
		revalidate();
		repaint();
	}

	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	private class BuildCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//
			if (e.getSource() instanceof JButton) {
				
				JButton button = (JButton)e.getSource();
				GameBuildingTiles gbt = Main.gc.getGameBuildingTiles();
				
				// add selected building to city area, move resources
				if (button.getText().equals("Build It")) {
					count++;
					BuildingTile building = gbt.removeBuildingWithName(radioButtonText);
					currentPlayer.getBuildingTiles().add(building);
					
					currentPlayer.getCubes().removeCubesFromCubes(building.getCubeCost());
					Main.gc.getBank().getCubes().addCubesToCubes(building.getCubeCost());
					
					av.updateBoardAndBank();
//					buildPanel();	// update panel
					setupBuildPanel();
					
//					doneButton.setVisible(true);
					
					if (count >= numToBuild) {
						removeAll();
						revalidate();
						repaint();
					}		
				}
//				else if (button.getText().equals("Done")) {
//					removeAll();
//					revalidate();
//					repaint();
//					// need to rotate to next player after done
//				}
			}
			// 
			else if (e.getSource() instanceof JRadioButton) {
				if ( ((JRadioButton)e.getSource()).isSelected() ) {
					radioButtonText = ((JRadioButton)e.getSource()).getText();
				}
			}
		}
	}
	
}
