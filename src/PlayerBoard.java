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
		holdingArea.setBackground(Color.gray);
		holdingArea.add(new JLabel(player.getCubes().toString()));
		holdingArea.add(new JLabel(player.getName() + " #" + player.getPlace()));
		
		JPanel bottomArea = new JPanel();
		bottomArea.setLayout(new GridLayout(1,2));
		bottomArea.setBackground(Color.DARK_GRAY);
		
		// production tiles
		JPanel productionArea = setupPlayerProductionArea(player);
		productionArea.setLayout(new GridLayout(4, 4));
		productionArea.setBackground(Color.GREEN);
		
		// buildings
		JPanel cityArea = new JPanel();
		cityArea.setLayout(new GridLayout(4, 4));
		cityArea.setBackground(Color.ORANGE);
		
		// temp buildings
		for (int x = 0; x < 16; x++) {
			JButton bb = new JButton();
			bb.setText(Integer.toString(x));
			cityArea.add(bb);
		}
		
		// add components
		bottomArea.add(productionArea);
		bottomArea.add(cityArea);
		add(holdingArea);
		add(bottomArea);
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
			
			//JPanel tp = new JPanel();
			//JButton tp = new JButton();
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
