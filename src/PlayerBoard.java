import java.awt.*;
import java.util.*;
import javax.swing.*;
/*
 * PlayerBoard:
 * -
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	-
 */
@SuppressWarnings("serial")
public class PlayerBoard extends JPanel {

	public PlayerBoard(Player player) {
		setLayout(new GridLayout(2,1));
		//this.setPreferredSize(new Dimension(250,250));
		//this.setSize(500, 500);
		setBackground(Color.RED);
		
		JPanel holdingArea = new JPanel();
		holdingArea.setLayout(new GridLayout(8,1));
		holdingArea.setBackground(Color.gray);
		holdingArea.add(new JLabel(" #" + player.getPlace() +"  "+ player.getName()  
					+"  "+ player.ageToString()));
		holdingArea.add(new JLabel(player.getCubes().toString()));
		
		JPanel bottomArea = new JPanel();
		bottomArea.setLayout(new GridLayout(1,2));
		bottomArea.setBackground(Color.DARK_GRAY);
		
		// production tiles
		JPanel productionArea = setupPlayerProductionArea(player);
		// buildings
		JPanel cityArea = setupPlayerCityArea(player);
		
		// add components
		bottomArea.add(productionArea);
		bottomArea.add(cityArea);
		add(holdingArea);
		add(bottomArea);
	}
	
	//
	private JPanel setupPlayerCityArea(Player player) {
		ArrayList<BuildingTile> buildingTiles = player.getBuildingTiles();
		
		JPanel cityPanel = new JPanel();
		cityPanel.setLayout(new GridLayout(4,4));
		cityPanel.setBounds(134, 0, 267, 267);
		cityPanel.setBackground(Color.GRAY);
		
		// 
		int numSquares = 16;
		for (int i = 0; i < buildingTiles.size(); i++) {
			// text for panel or button in each square
			JTextArea title = new JTextArea();
			title.setEditable(false);
			title.setOpaque(true);
			title.setFont(new Font("Default", Font.PLAIN, 8));
			
			JButton tp = new JButton();
			title.setText(buildingTiles.get(i).getName());
			tp.setText(buildingTiles.get(i).getName());
						
			tp.add(title);
			cityPanel.add(tp);
		}
		
		// fill out the rest
		for (int i = 0; i < numSquares - buildingTiles.size(); i++) {
			JButton eb = new JButton();
			eb.setBackground(Color.ORANGE);
			cityPanel.add(eb);
		}
		
		return cityPanel;
	}
	
	
	// setup the production area
	private JPanel setupPlayerProductionArea(Player player) {
			
		ArrayList<String> productionList = player.getCulture().getProductionAreaList();
		ArrayList<ProductionTile> productionTileList = player.getProductionTiles();
				
		JPanel productionPanel = new JPanel();
		productionPanel.setLayout(new GridLayout(4,4));
		productionPanel.setBounds(134, 0, 267, 267);
		productionPanel.setBackground(Color.GRAY);
		
		// 
		for (int i = 0; i < productionTileList.size(); i++) {
			// text for panel or button in each square
			JTextArea title = new JTextArea();
			title.setEditable(false);
			//title.setOpaque(true);
			title.setFont(new Font("Default", Font.PLAIN, 8));
			
			JComponent tp;
			if (productionTileList.get(i).getType() == "") {		// no tile
				tp = new JPanel();
				title.setText(productionList.get(i));
				tp.setBackground(Color.DARK_GRAY);
			}
			//else if (tileList.get(i).getType() == list.get(i)) {
			else { 										// tile exist here
				tp = new JButton();
				title.setText(productionTileList.get(i).toString());
				tp.setBackground(Color.ORANGE);
			}
						
			tp.add(title);
			productionPanel.add(tp);
		}
		return productionPanel;
	}
	
}
