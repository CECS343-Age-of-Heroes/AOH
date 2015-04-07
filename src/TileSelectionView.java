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
	private ArrayList<Tile> tilesToPick = new ArrayList<>();
	// same ass tiles to pick but remove from this when a tile is used
	private ArrayList<Tile> unusedTiles = new ArrayList<>();
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
		//setPreferredSize(new Dimension(1200, 800));
		
		// setup
		setupPlayerPanel();
		getInitialTiles();
		initialTilePanel();
		
		add(playerTilesPanel);
		add(tilesPanel);	
	}
	
	// SETUP // // // // // // // // // // // // // // // // // // // // // // 
	// removes 18 tiles from the GameTiles ArrayList
	private void getInitialTiles() {
		for (int i = 0; i < numberOfTiles; i++) {
			//Tile t = GameController.removeGameTile();
			// removes a tile from game tiles,
			Tile t = gc.getGameTiles().remove(0);
			// adds it to tiles to pick and unused tiles
			tilesToPick.add(t);
		    unusedTiles.add(t);
		}
	}
		
	// setup tile panel with initial state
	private void initialTilePanel() {
		tilesPanel.setLayout(null);
		tilesPanel.setBounds(400, 0, 800, 800);
		tilesPanel.setBackground(Color.GREEN);
		tilesPanel.setBorder(BorderFactory.createMatteBorder(3, 4, 5, 6, Color.BLUE));
		
		JLabel headerLabel = new JLabel("- Tile Selection - ", JLabel.CENTER);
		headerLabel.setSize(800, 100);
		headerLabel.setForeground(Color.BLUE);
		headerLabel.setFont(new Font("Default", Font.BOLD, 35));
		
		JTextArea text = new JTextArea();
		text.setText("Each player will select up to 6 tiles each.\n"
					+ "You may select a tile or pass when it is your turn.\n"
					+ "The selected tile must have an available spot on \nyour"
					+ " cultures production area.\n"
					+ "Click start to begin tile selection.");
		text.setBounds(150, 200, 500, 100);
		text.setBackground(Color.GREEN);
		text.setForeground(Color.BLUE);
		
		JButton startButton = new JButton("Start");
		startButton.setSize(200, 50);
		int centerTilesPanel = tilesPanel.getWidth() / 2 
							  - startButton.getWidth() / 2;
		startButton.setLocation(centerTilesPanel, 350);
		startButton.addActionListener(new OptionListener());
		
		tilesPanel.add(headerLabel);
		tilesPanel.add(text);
		tilesPanel.add(startButton);
	}
	// // // // // // // // // // // // // // // // // // // // // // // // // 
	
	// PLAYER PANEL // // // // // // // // // // // // // // // // // // // //  
	//	
	private void setupPlayerPanel() {
		playerTilesPanel.setLayout(new GridLayout(3, 1));
		playerTilesPanel.setBounds(0, 0, 400, 800);
		playerTilesPanel.setBackground(Color.GREEN);
		
		updatePlayerPanel(p1Panel, p1, new Tile());
		updatePlayerPanel(p2Panel, p2, new Tile());
		updatePlayerPanel(p3Panel, p3, new Tile());
		
		playerTilesPanel.add(p1Panel);
		playerTilesPanel.add(p2Panel);
		playerTilesPanel.add(p3Panel);
	}

	private void updatePlayerPanel(JPanel pPanel, Player player, Tile tile) {
		pPanel.removeAll();
		pPanel.setLayout(null);
		
		JPanel pName = new JPanel();
		pName.setBounds(0, 0, 133, 267);
		pName.setBackground(Color.GREEN);
		pName.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
		
		JTextArea pText = new JTextArea(player.getName() +"\n"+ player.getCulture().getName());
		pText.setForeground(Color.BLUE);
		pText.setBackground(Color.GREEN);
		pText.setOpaque(true);
		pText.setFont(new Font("Default", Font.PLAIN, 18));
		pName.add(pText);
		
		pPanel.add(pName);
		pPanel.add(setupPlayerProductionArea(player, tile));
		
		pPanel.revalidate();
		pPanel.repaint();
	}
	
	//
	private JPanel setupPlayerProductionArea(Player player, Tile tile) { 
		// production area list of spots
		ArrayList<String> list = player.getCulture().getProductionAreaList();
		// production area tiles list
		ArrayList<Tile> tilesList = player.getTiles();
		
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
			productionSquare.setBorder(BorderFactory.
									   createLineBorder(Color.BLUE, 2, true));
			// text inside production square
			JTextArea title = new JTextArea();
			title.setEditable(false);
			title.setFont(new Font("Default", Font.PLAIN, 12));
			title.setOpaque(true);
			
			// if no tile in that spot set text from list
			if (tilesList.get(i).getType() == "") {
				title.setText(list.get(i));
				title.setForeground(Color.BLUE);
				title.setBackground(Color.WHITE);
				productionSquare.setBackground(Color.WHITE);
			}
			// set text from tile details
			else {
				title.setText(tilesList.get(i).toString());
				title.setForeground(Color.WHITE);
				title.setBackground(Color.BLUE);
				productionSquare.setBackground(Color.BLUE);
				productionSquare.setBorder(BorderFactory.
						   createLineBorder(Color.GREEN, 2, true));
			}
			
			productionSquare.add(title);
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
		
		for (Tile tile : tilesToPick) {
			JButton tileButton = new JButton();
			tileButton.addActionListener(new OptionListener());
			tileButton.setLayout(new BorderLayout());
			//tileButton.setContentAreaFilled(false); // use if button has image
			
			Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
			JLabel nameLabel = new JLabel(tile.getType(), JLabel.CENTER);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
			nameLabel.setForeground(Color.BLUE);
			nameLabel.setFont(labelFont);
			
			JLabel valueLabel = new JLabel(tile.getColor() +"   "+ 
										   tile.getValue(), JLabel.CENTER);
			valueLabel.setForeground(Color.BLUE);
			valueLabel.setFont(labelFont);
			
			tileButton.add(nameLabel, BorderLayout.NORTH);
			tileButton.add(valueLabel, BorderLayout.CENTER);
			
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
	private boolean checkIfValidTileSelection(Player p, Tile tile, JButton b) {
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
		tilesPanel.setBorder(BorderFactory.createMatteBorder(3, 4, 5, 6, Color.BLUE));
		
		JLabel nextLabel = new JLabel("Tile Selection Completed", JLabel.CENTER);
		nextLabel.setSize(tilesPanel.getWidth(), 250);
		nextLabel.setForeground(Color.WHITE);
		nextLabel.setFont(new Font("Default", Font.BOLD, 30));
		
		nextButton.addActionListener(new OptionListener());
		nextButton.setSize(200, 50);
		int centerTilesPanel = tilesPanel.getWidth() / 2 
							  - nextButton.getWidth() / 2;
		nextButton.setLocation(centerTilesPanel, 350);
		
		tilesPanel.add(nextLabel);
		tilesPanel.add(nextButton);
		
		tilesPanel.revalidate();
		tilesPanel.repaint();
		
		//System.out.println("Unused Tiles Size: " + unusedTiles.size());
		// put unselected tiles back into the GameTiles
		for (Tile tile : unusedTiles) {
			//GameController.addTile(tile);
			gc.addTile(tile);
			//System.out.println("adding back tile");
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
					Tile clickTile = tilesToPick.get(buttonIndex);
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
					checkTurn();		// check whos turn it is
				}
				else {
					displayCompletedMessage();	// display final message
				}
			}	
		}	
	}
		
}
	
	
