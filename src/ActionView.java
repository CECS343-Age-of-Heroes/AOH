import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/*
 * ActionView:
 * 
 */
/*
 * Add:
 * 	- need to allow up to 10 houses
 *  - 8 favors for 1 victory on trade card
 *  -
 * Fix:
 * 	- 
 */
@SuppressWarnings("serial")
public class ActionView extends JPanel {

	private Player currentPlayer = Main.gc.getHuman();
	public ActionCardListener acl = new ActionCardListener();
	private String currentView = "";
	
	private JLabel cardLabel = new JLabel();	// card played
	private JLabel playerLabel = new JLabel();  // whos turn it is

	private GamePlayView gpv; // = Main.gvc.getGamePlayView();
	
	private String radioButtonText;
	private int count = 0;
	private int numToBuild = 0;
	private int cardValue = 0;
	private JButton doneButton = new JButton("Done");
	
	private ArrayList<JTextField> sendTFs = new ArrayList<>();
	private ArrayList<JTextField> recieveTFs = new ArrayList<>();
	
	// array of tiles pulled from game tiles
	private ArrayList<ProductionTile> tilesToPick = new ArrayList<>();
	private ArrayList<JButton> tileButtonsToPick = new ArrayList<>();
	
	public ActionView() {
		//setBounds(0, 0, 300, 800);
		
	}
	
	public ActionCardListener getActionCardListener() {
		return acl;
	}
	
	public void displayCurrentPlayer() {
		removeAll();
		setLayout(new FlowLayout());
		
		
		
		
		revalidate();
		repaint();
	}
	
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
	
	//
	private void nextAgePanel() {
		currentView = "next age";
		removeAll();
		setLayout(null);
//		setLayout(new FlowLayout());
		
		//add(cardLabel);	// name of card
		cardLabel.setBounds(0, 0, 300, 100);
		cardLabel.setFont(new Font("Default", Font.PLAIN, 18));
		cardLabel.setForeground(Color.WHITE);
		add(cardLabel);
		
		playerLabel.setText(currentPlayer.getName());
		playerLabel.setFont(new Font("Default", Font.PLAIN, 15));
		playerLabel.setForeground(Color.WHITE);
		playerLabel.setBounds(0, 100, 300, 100);
		add(playerLabel);
		
		JPanel mainPanel = new JPanel();
//		mainPanel.setLayout(new GridLayout(4,1));
		mainPanel.setBounds(0, 200, 300, 200);
//		mainPanel.setPreferredSize(new Dimension(300, 150));
		mainPanel.setBackground(Color.GREEN);
//		mainPanel.add(cardLabel);
		
		int theCost = 0;
		JTextArea costLabel = new JTextArea();
		if (cardValue == 345) {
			theCost = currentPlayer.getAge() - 1;
		}
		else if (cardValue == 456) {
			theCost = currentPlayer.getAge();
		}
		
		Cubes cubeCost = new Cubes(theCost, theCost, theCost, theCost, 0);
		costLabel.setText("Cost: \n" + cubeCost.toString());
		mainPanel.add(costLabel);
		
		if (currentPlayer.hasEnoughResources(cubeCost)) {
			doneButton.setText("Confirm");
			doneButton.addActionListener(new ActionCardListener());
		}
		else {
			doneButton.setText("Not Enough Resources");
		}
//		mainPanel.add(doneButton);
		doneButton.setBounds(50, 500, 200, 50);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionCardListener());
		cancelButton.setBounds(50, 600, 200, 50);
//		mainPanel.add(cancelButton);
		
		add(mainPanel);
		add(doneButton);
		add(cancelButton);
		revalidate();
		repaint();
	}
	
	
	//
	private void tradePanel() {
		currentView = "trade";
		removeAll();
		setLayout(null);
		setBackground(Color.GREEN);
		
		cardLabel.setBounds(0, 100, 300, 100);
		add(cardLabel);
		
		JLabel costLabel = new JLabel("Select Resource to Pay with:");
		costLabel.setBounds(0, 180, 300, 20);
		add(costLabel);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(2,2));
		radioPanel.setBounds(0, 200, 300, 100);
		radioPanel.setBackground(Color.GRAY);
		//radioPanel.add(new JLabel("Select Resource to Pay With:"));
		ButtonGroup bg = new ButtonGroup();
		JRadioButton blue = new JRadioButton("blue");
		JRadioButton green = new JRadioButton("green");
		JRadioButton brown = new JRadioButton("brown");
		JRadioButton yellow = new JRadioButton("yellow");
		blue.addActionListener(new ActionCardListener());
		green.addActionListener(new ActionCardListener());
		brown.addActionListener(new ActionCardListener());
		yellow.addActionListener(new ActionCardListener());
		bg.add(blue);
		bg.add(green);
		bg.add(brown);
		bg.add(yellow);
		radioPanel.add(blue);
		radioPanel.add(green);
		radioPanel.add(brown);
		radioPanel.add(yellow);
		add(radioPanel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,3));
		mainPanel.setBounds(0, 300, 300, 200);
		mainPanel.setBackground(Color.GREEN);
		
		JPanel colorsPanel = new JPanel();
		colorsPanel.setLayout(new GridLayout(5,1));
		colorsPanel.add(new JLabel("Resource:", JLabel.CENTER));
		colorsPanel.add(new JLabel("Blue:", JLabel.CENTER));
		colorsPanel.add(new JLabel("Green:", JLabel.CENTER));
		colorsPanel.add(new JLabel("Brown:", JLabel.CENTER));
		colorsPanel.add(new JLabel("Yellow:", JLabel.CENTER));
		
		initTextFieldsArray();
		
		JPanel sendPanel = new JPanel();
		sendPanel.setLayout(new GridLayout(5,1));
		sendPanel.add(new JLabel("Send:", JLabel.CENTER));
		for (JTextField tf : sendTFs) {
			sendPanel.add(tf);
		}
		
		JPanel recievePanel = new JPanel();
		recievePanel.setLayout(new GridLayout(5,1));
		recievePanel.add(new JLabel("Recieve:", JLabel.CENTER));
		for (JTextField tf : recieveTFs) {
			recievePanel.add(tf);
		}
		
		mainPanel.add(colorsPanel);
		mainPanel.add(sendPanel);
		mainPanel.add(recievePanel);
		add(mainPanel);
		
		doneButton.setText("Trade");
		doneButton.setBounds(50, 550, 200, 50);
		doneButton.addActionListener(new ActionCardListener());
		add(doneButton);
		
		revalidate();
		repaint();
	}
	
	//
	private void initTextFieldsArray() {
		JTextField blueTFsend = new JTextField();
		JTextField greenTFsend = new JTextField();
		JTextField brownTFsend = new JTextField();
		JTextField yellowTFsend = new JTextField();
		blueTFsend.setText("0");
		greenTFsend.setText("0");
		brownTFsend.setText("0");
		yellowTFsend.setText("0");
		sendTFs.add(blueTFsend);
		sendTFs.add(greenTFsend);
		sendTFs.add(brownTFsend);
		sendTFs.add(yellowTFsend);
		
		JTextField blueTFrecieve = new JTextField();
		JTextField greenTFrecieve = new JTextField();
		JTextField brownTFrecieve = new JTextField();
		JTextField yellowTFrecieve = new JTextField();
		blueTFrecieve.setText("0");
		greenTFrecieve.setText("0");
		brownTFrecieve.setText("0");
		yellowTFrecieve.setText("0");
		recieveTFs.add(blueTFrecieve);
		recieveTFs.add(greenTFrecieve);
		recieveTFs.add(brownTFrecieve);
		recieveTFs.add(yellowTFrecieve);
	}
	
	private void explorePanel() {
		currentView = "explore";
		removeAll();
		setLayout(null);
		setBackground(Color.GREEN);
		
		cardLabel.setBounds(0, 0, 300, 100);
		cardLabel.setFont(new Font("Default", Font.PLAIN, 18));
		add(cardLabel);
		
		playerLabel.setText(currentPlayer.getName());
		playerLabel.setFont(new Font("Default", Font.PLAIN, 15));
		playerLabel.setBounds(0, 100, 300, 100);
		add(playerLabel);
		
		JPanel tilePanel = new JPanel();
		tilePanel.setOpaque(false);
		int rows = (cardValue == 3 ? 2 : 3); 
		tilePanel.setLayout(new GridLayout(rows, 2));
		int tph = (cardValue == 3 ? 300 : 450);
		tilePanel.setBounds(0, 200, 300, tph);
		
		for (ProductionTile tile : tilesToPick) {
			JButton tileButton = new JButton();
			tileButton.addActionListener(new ActionCardListener());
			tileButton.setLayout(new BorderLayout());
			
			Font labelFont = new Font("Default", Font.PLAIN, 13);
			JLabel nameLabel = new JLabel(tile.getType(), JLabel.CENTER);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
			//nameLabel.setForeground(Color.BLUE);
			nameLabel.setFont(labelFont);
			
			JLabel valueLabel = new JLabel(tile.getColor() +"   "+ 
										   tile.getValue(), JLabel.CENTER);
			//valueLabel.setForeground(Color.BLUE);
			valueLabel.setFont(labelFont);
			
			tileButton.add(nameLabel, BorderLayout.NORTH);
			tileButton.add(valueLabel, BorderLayout.CENTER);
			tileButtonsToPick.add(tileButton);
			tilePanel.add(tileButton);
		}
		
		JButton passButton = new JButton("Pass");
		passButton.addActionListener(new ActionCardListener());
		tileButtonsToPick.add(passButton);
		tilePanel.add(passButton);
		
		add(tilePanel);
		revalidate();
		repaint();	
	}
	
	
	//OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	// action listener for all buttons
	private class ActionCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			
			gpv = Main.gvc.getGamePlayView();
			
			if (e.getSource() instanceof ActionCard) {
				ActionCard ac =  ((ActionCard)e.getSource());
				
				cardLabel.setText(ac.getName());
				doneButton.setVisible(true);
				
				if (ac instanceof BuildCard) {
					System.out.println("Add Build Action/Buttons");
					count = 0;
					buildingPanel();
					numToBuild = ((BuildCard) ac).getNumOfBuildings();
					System.out.println("Num to build = " + numToBuild);
				}
				else if (ac instanceof NextAgeCard) {
					System.out.println("Add Next Age Panel");
					cardValue = ((NextAgeCard) ac).getCost();
					nextAgePanel();
					//ac.executeActionCard(player);
				}
				else if (ac instanceof TradeCard) {
					System.out.println("Add Trade Panel");
					cardValue = ((TradeCard) ac).getTradeCost();
					tradePanel();
				}
				else if (ac instanceof GatherCard) {
					System.out.println("Add Gather Panel");
					
				}
				else if (ac instanceof ExploreCard) {
					System.out.println("Add Gather Panel");
					cardValue = ((ExploreCard) ac).getNumOfTiles();
					// add tiles to array for players to choose from
					for (int i = 0; i < cardValue; i++) {
						ProductionTile t = Main.gc.getGameProductionTiles().remove(0);
						tilesToPick.add(t);
					}
					explorePanel();
				}
				
				ac.setEnabled(false);
			}
			//
			else if (e.getSource() instanceof JButton) {
				
				JButton button = (JButton)e.getSource();
				GameBuildingTiles gbt = Main.gc.getGameBuildingTiles();
				
				if (currentView.equals("explore")) {
					
					if (button.getText() == "Pass") {
						count++;
						// rotate to next player
						currentPlayer = Main.gc.getNextPlayer(currentPlayer);
					}
					else {
						ArrayList<String> list = currentPlayer.getCulture().getProductionAreaList();
						ArrayList<ProductionTile> tilesList = currentPlayer.getProductionTiles();
						
						int buttonIndex = tileButtonsToPick.indexOf(button);
						ProductionTile clicked = tilesToPick.get(buttonIndex);
						System.out.println("button index = " + buttonIndex);
						System.out.println("clicked tile: " + clicked.toString());
						
						GameProductionTiles gpt = new GameProductionTiles();
						
						if (gpt.checkIfValidTileSelection(currentPlayer, clicked)) {
							
							int j = list.indexOf(clicked.getType());
							tilesList.remove(j);
							tilesList.add(j, clicked);
							list.set(j, "FILLED");
					
							button.setEnabled(false);		
							count++;
							gpv.getGameBoardsPanel().updateGameBoard(Main.gc.getPlayersList());
							// rotate to next player
							currentPlayer = Main.gc.getNextPlayer(currentPlayer);
						}
					}
					playerLabel.setText(currentPlayer.getName());
					
					// 
					if (count >= 3) {
						removeAll();
						revalidate();
						repaint();
					}
				}
				// add selected building to city area, move resources
				if (button.getText().equals("Build It")) {
					count++;
					BuildingTile building = gbt.removeBuildingWithName(radioButtonText);
					currentPlayer.getBuildingTiles().add(building);
					currentPlayer.getCubes().removeCubesFromCubes(building.getCubeCost());
					Main.gc.getBank().getCubes().addCubesToCubes(building.getCubeCost());
					gpv.getGameBoardsPanel().updateGameBoard(Main.gc.getPlayersList());
					gpv.setupBankPanel();
					
					buildingPanel();
					doneButton.setVisible(true);
					
					if (count >= numToBuild) {
						removeAll();
						revalidate();
						repaint();
					}		
				}
				// confirm next age upgrade
				else if (button.getText().equals("Confirm")) {
					
					Cubes pCubes = currentPlayer.getCubes();
					
					int theCost = 0;
					if (cardValue == 345) {
						theCost = currentPlayer.getAge() - 1;
					}
					else if (cardValue == 456) {
						theCost = currentPlayer.getAge();
					}
					//cardValue = theCost;
					//cubeCost.setCubes(theCost, theCost, theCost, theCost);
					Cubes cubeCost = new Cubes(theCost, theCost, theCost, theCost, 0);
					doneButton.setText("Not Enough Resources");
					//
					if (currentPlayer.hasEnoughResources(cubeCost)) {
						
						pCubes.addCubesToCubes(cubeCost);
						
						// need tp add those cubes to the bank after
						theCost *= -1;
						cubeCost.setCubes(theCost, theCost, theCost, theCost);
						Main.gc.getBank().getCubes().addCubesToCubes(cubeCost);
						gpv.getGameBoardsPanel().updateGameBoard(Main.gc.getPlayersList());
						gpv.setupBankPanel();
						
						removeAll();
						revalidate();
						repaint();
					}
					
				}
				//
				else if (button.getText().equals("Trade")) {
					int toSendCount = 0;
					int toRecieveCount = 0;
					int blue = 0;
					int green = 0;
					int brown = 0;
					int yellow = 0;
					
					// cost to trade
					currentPlayer.getCubes().addToColor(radioButtonText, -1);
					
					for (JTextField tf : sendTFs) {
						toSendCount += Integer.valueOf(tf.getText()); 
					}
					blue = Integer.valueOf(sendTFs.get(0).getText());
					green = Integer.valueOf(sendTFs.get(1).getText());
					brown = Integer.valueOf(sendTFs.get(2).getText());
					yellow = Integer.valueOf(sendTFs.get(3).getText());
					
					
					Cubes addBankCubes = new Cubes();
					Cubes removePlayerCubes = new Cubes();
					addBankCubes.setCubes(blue, green, brown, yellow);
					removePlayerCubes.setCubes(-blue, -green, -brown, -yellow);
					
					for (JTextField tf : recieveTFs) {
						toRecieveCount += Integer.valueOf(tf.getText());
					}
					blue = Integer.valueOf(recieveTFs.get(0).getText());
					green = Integer.valueOf(recieveTFs.get(1).getText());
					brown = Integer.valueOf(recieveTFs.get(2).getText());
					yellow = Integer.valueOf(recieveTFs.get(3).getText());
					
					Cubes addPlayerCubes = new Cubes();
					Cubes removeBankCubes = new Cubes();
					addPlayerCubes.setCubes(blue, green, brown, yellow);
					removeBankCubes.setCubes(-blue, -green, -brown, -yellow);
					 
					 
					if (toSendCount == toRecieveCount) {
						currentPlayer.getCubes().addCubesToCubes(removePlayerCubes);
						currentPlayer.getCubes().addCubesToCubes(addPlayerCubes);
						Main.gc.getBank().getCubes().addCubesToCubes(addBankCubes);
						Main.gc.getBank().getCubes().addCubesToCubes(removeBankCubes);
						
						gpv.getGameBoardsPanel().updateGameBoard(Main.gc.getPlayersList());
						gpv.setupBankPanel();
						
						doneButton.setText("Done");
					}
					else {
						
					}
				}
				
				else if (button.getText().equals("Done")) {
					removeAll();
					revalidate();
					repaint();
					// need to rotate to next player after done
				}
				
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


