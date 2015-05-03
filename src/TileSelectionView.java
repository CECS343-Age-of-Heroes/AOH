import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
/*
 * TileSelectionView:
 * - give 18 random tiles from the game tile array for the 3 players to choose 
 *   from
 * - a player may select a tile or pass when it is there turn
 * - a player must only select a tile that can be filled on the cultures
 *   production area on the culture board. if the board has no available spots
 *   for the selected tile selection is disabled
 */
/*
 * Add:
 * 	- message for invalid selection ?? 
 * 	- run() for threading selection of buttons by computer
 * 
 * FIX:
 * 	- change check turn to return an int for the index of whos turn it is and
 * 	  use that to apply the move for the right player, 
 *    instead of check for all 6 possibilities
 * 	- change to just update player production area on selection not 
 * 	  whole player panel
 * 	- change start button to "ok" 
 */

@SuppressWarnings("serial")
public class TileSelectionView extends JPanel {
	// reference to the games game controller
	private GameController gc = Main.gc;
	// reference to the games player list
	private ArrayList<Player> pList = Main.gc.getPlayersList();
	private Player p1 = pList.get(0);
	private Player p2 = pList.get(1);
	private Player p3 = pList.get(2);
	private int count = 0;			// keep track of the number of selections
	private int numberOfTiles = 18; // number of tiles to pull/show
	
	// array of tiles pulled from game tiles
	private ArrayList<ProductionTile> tilesToPick = new ArrayList<>();
	// same ass tiles to pick but remove from this when a tile is used
	private ArrayList<ProductionTile> unusedTiles = new ArrayList<>();
	// array of buttons that can be selected
	private ArrayList<JButton> buttonsToPick = new ArrayList<>();
	// panel with 3 players and their production areas
	private JPanel playerTilesPanel = new JPanel();
	// panel with all tiles(buttons) and pass
	private JPanel tilesPanel = new JPanel();
	// separate panel for each player
	private JPanel p1Panel = new JPanel();
	private JPanel p2Panel = new JPanel();
	private JPanel p3Panel = new JPanel();
	// button to go to the next view
	public JButton nextButton = new JButton("Next");
	
	
	// constructor
	public TileSelectionView() {
		setLayout(null);
		setBackground(GameViewController.getGameColor("blue"));
		// setup
		setupPlayerPanel();
		getInitialProductionTiles();
		initialTilePanel();
		
		add(playerTilesPanel);
		add(tilesPanel);	
	}
	
	// SETUP // // // // // // // // // // // // // // // // // // // // // // 
	// removes 18 tiles from the GameTiles ArrayList
	private void getInitialProductionTiles() {
		for (int i = 0; i < numberOfTiles; i++) {
			// removes a tile from game tiles,
			ProductionTile t = gc.getGameProductionTiles().remove(0);
			// adds it to tiles to pick and unused tiles
			tilesToPick.add(t);
		    unusedTiles.add(t);
		}
	}
		
	// setup tile panel with initial state
	private void initialTilePanel() {
		tilesPanel.setLayout(null);
		tilesPanel.setBounds(400, 0, 800, 800);
		tilesPanel.setOpaque(false);
		tilesPanel.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.GREEN));
		
		JLabel headerLabel = new JLabel("~ Production Tile Selection ~ ", JLabel.CENTER);
		headerLabel.setBounds(0, 50, 800, 100);
		headerLabel.setForeground(Color.GREEN);
		headerLabel.setFont(GameViewController.getGameFontSize(45));
		
		JButton startButton = new JButton("Start");
		startButton.setSize(200, 50);
		int centerTilesPanel = tilesPanel.getWidth() / 2 
							  - startButton.getWidth() / 2;
		startButton.setLocation(centerTilesPanel, 350);
		startButton.addActionListener(new OptionListener());
		
		tilesPanel.add(headerLabel);
		tilesPanel.add(startButton);
	}
	// // // // // // // // // // // // // // // // // // // // // // // // // 
	
	// PLAYER PANEL // // // // // // // // // // // // // // // // // // // //  
	//	
	private void setupPlayerPanel() {
		playerTilesPanel.setLayout(new GridLayout(3, 1));
		playerTilesPanel.setBounds(0, 0, 400, 800);
//		playerTilesPanel.setBackground(Color.BLUE);
		playerTilesPanel.setOpaque(false);
		
		updatePlayerPanel(p1Panel, p1, new ProductionTile());
		updatePlayerPanel(p2Panel, p2, new ProductionTile());
		updatePlayerPanel(p3Panel, p3, new ProductionTile());
		
		playerTilesPanel.add(p1Panel);
		playerTilesPanel.add(p2Panel);
		playerTilesPanel.add(p3Panel);
	}

	private void updatePlayerPanel(JPanel pPanel, Player player, ProductionTile tile) {
		pPanel.removeAll();
		pPanel.setLayout(null);
		pPanel.setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(0, 0, 133, 267);
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, false));
		
		JLabel name = new JLabel(player.getName(), JLabel.CENTER);
		name.setFont(GameViewController.getGameFontSize(30));
		name.setForeground(Color.WHITE);
		name.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
		
		JLabel cultureName = new JLabel(player.getCulture().getName(), JLabel.CENTER);
		cultureName.setFont(GameViewController.getGameFontSize(20));
		cultureName.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		cultureName.setForeground(Color.WHITE);
			
		panel.add(name, BorderLayout.CENTER);
		panel.add(cultureName, BorderLayout.NORTH);
		pPanel.add(panel);
		pPanel.add(setupPlayerProductionArea(player, tile));
		
		pPanel.revalidate();
		pPanel.repaint();
	}
	
	//
	private JPanel setupPlayerProductionArea(Player player, ProductionTile tile) { 
		// production area list of spots
		ArrayList<String> list = player.getCulture().getProductionAreaList();
		// production area tiles list
		ArrayList<ProductionTile> tilesList = player.getProductionTiles();
		
		// check if production list has a spot for the selected tile
		// if it does then it removes the empty tile at that index and 
		// replaces it with the new tile, and marks the list spot as filled
		if (list.contains(tile.getType())) {
			int j = list.indexOf(tile.getType());
			tilesList.remove(j);
			tilesList.add(j, tile);
			// change same index used in tileList to filled in list
			// so it cant be used again
			list.set(j, "FILLED");
		}
		
		// production area for a player
		JPanel productionPanel = new JPanel();
		productionPanel.setLayout(new GridLayout(4,4));
		productionPanel.setBounds(134, 0, 267, 267);
		productionPanel.setBackground(Color.GRAY);
		
		// loop through each spot if there is a tile in that index 
		// then print tile details, if not then print the spots title
		for (int i = 0; i < tilesList.size(); i++) {
			// one square in the production panel
			JPanel productionSquare = new JPanel();
			productionSquare.setLayout(new BorderLayout());
			productionSquare.setBackground(new Color(199,199,199));
			productionSquare.setBorder(BorderFactory.
					   createLineBorder(Color.BLUE, 1, true));
			
			// text inside production square			
			JLabel nameLabel = new JLabel("", JLabel.CENTER);
			nameLabel.setFont(GameViewController.getGameFontSize(11));
//			nameLabel.setForeground(Color.BLUE);
			nameLabel.setForeground(GameViewController.getGameColor("blue"));
			nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
			
			JPanel cubesPanel = new JPanel();
			cubesPanel.setOpaque(false);
			// if no tile in that spot set text from list
			if (tilesList.get(i).getType() == "") {
				nameLabel.setText(list.get(i).toUpperCase());
			}
			// set text from tile details
			else {
				nameLabel.setText(tilesList.get(i).getType().toUpperCase());
				
				for (int j = 0; j < tilesList.get(i).getValue(); j++) {
					JPanel cubePanel = new JPanel();
					cubePanel.setPreferredSize(new Dimension(10,10));
					cubePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
					
					cubePanel.setBackground(Cubes.cubeColor(tilesList.get(i).getColor()));
					cubesPanel.add(cubePanel, BorderLayout.CENTER);
				}
				productionSquare.setBackground(Color.WHITE);
			}
			productionSquare.add(nameLabel, BorderLayout.NORTH);
			productionSquare.add(cubesPanel, BorderLayout.CENTER);
			
			productionPanel.add(productionSquare);
		}
		return productionPanel;
	}
	// // // // // // // // // // // // // // // // // // // // // // // // // 
	
	// TILES PANEL // // // // // // // // // // // // // // // // // // // //  
	//
	private void setupTilesPanel() {
		tilesPanel.removeAll();
		tilesPanel.setLayout(new GridLayout(5, 5));
		
		for (ProductionTile tile : tilesToPick) {
			JButton tileButton = new JButton();
			tileButton.addActionListener(new OptionListener());
			tileButton.setLayout(new BorderLayout());
			
			
			JLabel nameLabel = new JLabel(tile.getType().toUpperCase(), JLabel.CENTER);
			nameLabel.setFont(GameViewController.getGameFontSize(15));
			nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
			nameLabel.setForeground(Color.BLUE);
			
			JPanel cubesPanel = new JPanel();
			cubesPanel.setOpaque(false);
			for (int j = 0; j < tile.getValue(); j++) {
				JPanel cubePanel = new JPanel();
				cubePanel.setPreferredSize(new Dimension(15,15));
				cubePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
				
				cubePanel.setBackground(Cubes.cubeColor(tile.getColor()));
				cubesPanel.add(cubePanel, BorderLayout.CENTER);
			}
						
			tileButton.add(nameLabel, BorderLayout.NORTH);
			tileButton.add(cubesPanel, BorderLayout.CENTER);
			
			buttonsToPick.add(tileButton);
			tilesPanel.add(tileButton);
		}
		
		JButton blankButton = new JButton();
		blankButton.setEnabled(false);
		JButton passButton = new JButton("Pass");
		passButton.addActionListener(new OptionListener());
		
		buttonsToPick.add(passButton);
		tilesPanel.add(blankButton);
		tilesPanel.add(passButton);
		
		tilesPanel.revalidate();
		tilesPanel.repaint();
	}
	// // // // // // // // // // // // // // // // // // // // // // // // // 
	
	// HELPER METHODS // // // // // // // // // // // // // // // // // // //
	// return a random integer from 0-18
	private int randomTile() {
		return new Random().nextInt(19);
	}
	
	// programmatically click a tile button if it is the computers turn
	private void computerSelectTile() {		// fix this function up
		int index = -1;
		do {
			index = randomTile();
		} while (!(buttonsToPick.get(index).isEnabled()));
			
		if (buttonsToPick.get(index).isEnabled()) {
			buttonsToPick.get(index).doClick();
		}
	}
	
	// checks to see if it is the computers turn
	private void checkTurn() {
		int humanIndex = gc.getHumanIndex();
		int mod = count % 6;
		if (mod != humanIndex && mod != (5-humanIndex)) {
			if (count < 18)
				computerSelectTile();
		}
	}
	
	// returns if the selected tile exist and is open on a players prod board
	private boolean checkIfValidTileSelection(Player p, ProductionTile tile, JButton b) {
		ArrayList<String> list = p.getCulture().getProductionAreaList();
		if (list.contains(tile.getType())) {
			unusedTiles.remove(tile);
			b.setEnabled(false);
			count++;
			return true;
		}
		return false;
	}
	
	// remove all tiles and display message after every player had
	// 6 chances for tile selection
	private void displayCompletedMessage() {
		tilesPanel.removeAll();
		tilesPanel.setLayout(null);
		tilesPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
		
		JLabel nextLabel = new JLabel("Tile Selection Completed", JLabel.CENTER);
		nextLabel.setSize(tilesPanel.getWidth(), 250);
		nextLabel.setForeground(Color.WHITE);
		nextLabel.setFont(GameViewController.getGameFontSize(35));
		
		nextButton.addActionListener(new OptionListener());
		nextButton.setSize(200, 50);
		int centerTilesPanel = tilesPanel.getWidth() / 2 
							  - nextButton.getWidth() / 2;
		nextButton.setLocation(centerTilesPanel, 350);
		
		tilesPanel.add(nextLabel);
		tilesPanel.add(nextButton);
		
		tilesPanel.revalidate();
		tilesPanel.repaint();
		
		// put unselected tiles back into the GameTiles
		for (ProductionTile tile : unusedTiles) {
			gc.addProductionTile(tile);
		}
	}
	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	// action listener for buttons
	private class OptionListener implements ActionListener
	{   
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() instanceof JButton && count < numberOfTiles) {
				
				if (((JButton)e.getSource()).getText() == "Start") {
					setupTilesPanel();
				}
				else if (((JButton)e.getSource()).getText() == "Next") {
					// put unselected tiles back into the GameTiles
					//for (Tile tile : unusedTiles) {
					//	gc.addTile(tile);
						//unusedTiles.remove(tile);
					//}
				}
				else if (((JButton)e.getSource()).getText() == "Pass") {
					count++;
				}
				else {
					int buttonIndex = buttonsToPick.indexOf(e.getSource());
					ProductionTile clickTile = tilesToPick.get(buttonIndex);
					JButton button = ((JButton)e.getSource());
					
					int turn = count % 6;
					if (turn == 0 || turn == 5) {		// player 1
						if (checkIfValidTileSelection(p1, clickTile, button)) {
							updatePlayerPanel(p1Panel, p1, clickTile);
						}
					}
					else if (turn == 1 || turn == 4) {	// player 2
						if (checkIfValidTileSelection(p2, clickTile, button)) {
							updatePlayerPanel(p2Panel, p2, clickTile);
						}
					}
					else if (turn == 2 || turn == 3) {  // player 3
						if (checkIfValidTileSelection(p3, clickTile, button)) {
							updatePlayerPanel(p3Panel, p3, clickTile);
						}
					}
				}
				
				// only give numberOfTiles turns
				if (count < numberOfTiles) {
					checkTurn();		// check who's turn it is
				}
				else {
					displayCompletedMessage();	// display final message
				}
			}	
		}	
	}
		
}
	
	
