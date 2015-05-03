import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
/*
 * RollDiceScreen:
 * - rolls two dice to determine the starting order for the game
 * - reorders the player array list to represent that order
 */
/*
 * Add:
 * 	- dice images to represent the number rolled
 * FIX:
 * 	- locations and sizes
 */
@SuppressWarnings("serial")
public class RollDiceScreen extends JPanel {
	// get players list from game controller
	private ArrayList<Player> pList = Main.gc.getPlayersList();
	// panel with players name and culture
	private JPanel playersPanel = new JPanel();
	// panel with the dice value rolled
	private JPanel rollPanel = new JPanel();
	// panel with the place calculated from players roll
	private JPanel placePanel = new JPanel();
	public JButton rollButton = new JButton("Roll");
	private Player human = pList.get(0);
	private Player comp1 = pList.get(1);
	private Player comp2 = pList.get(2);

	// constructor
	public RollDiceScreen() {
		setLayout(null);
		setSize(1200, 822);
//		setBackground(Color.BLUE);
		setBackground(GameViewController.getGameColor("blue"));
		setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GREEN));

		// set up roll button
		rollButton.addActionListener(new OptionListener());
		rollButton.setSize(200, 50);
		int centerX = 600 - rollButton.getWidth() / 2 ;
		rollButton.setLocation(centerX, 650);
		
		// view header
		add(setupHeaderLabel());
		// player header
		add(setupTableHeaderLabels());
		add(rollButton);
		addPanels();
	}
	
	// setup // // // // // // // // // // // // // // // // // // // // // //
	// setup and add 3 panels
	private void addPanels() {
		setupPlayersPanel();
		add(playersPanel);
		
		setupRollPanel();
		add(rollPanel);
		
		setupPlacePanel();
		add(placePanel);
	}
	
	// return header label for view
	private JLabel setupHeaderLabel() {
		JLabel headerLabel = new JLabel("~ Roll for Starting Player ~", 
							 		    JLabel.CENTER);
		headerLabel.setBounds(0, 50, 1200, 100);
		headerLabel.setForeground(Color.GREEN);
		headerLabel.setFont(GameViewController.getGameFontSize(45));
		return headerLabel;
	}
	
	// return player label for view
	private JPanel setupTableHeaderLabels() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBounds(200, 200, 800, 50);
		
		JPanel playerHeader = setupTabelLabel("Players");
		playerHeader.setBounds(0, 0, 140, 50);
		JPanel rollHeader = setupTabelLabel("Roll");
		rollHeader.setBounds(200, 0, 300, 50);
		JPanel placeHeader = setupTabelLabel("Place");
		placeHeader.setBounds(500, 0, 300, 50);
		
		panel.add(playerHeader);
		panel.add(rollHeader);
		panel.add(placeHeader);
//		JLabel playerHeader = new JLabel("Players:");
////		playerHeader.setBounds(200, 200, 300, 50);
//		playerHeader.setForeground(Color.GREEN);
//		playerHeader.setFont(GameViewController.getGameFontSize(40));
		
		return panel;
	}
	
	private JPanel setupTabelLabel(String text) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		JLabel label = new JLabel(text, JLabel.CENTER);
//		playerHeader.setBounds(200, 200, 300, 50);
		label.setForeground(Color.GREEN);
		label.setFont(GameViewController.getGameFontSize(40));
		panel.add(label);
		return panel;
	}
	
	// NAMES PANEL // // // // // // // // // // // // // // // // // // // // 
	// sets up the panel with players name and culture
	private void setupPlayersPanel() {
		playersPanel.setLayout(new GridLayout(3,1));
		playersPanel.setBounds(200, 250, 200, 300);
		playersPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.GREEN));
		// create and add all users
		playersPanel.add(singleUserPanel(human.getName(), human.getCulture()));
		playersPanel.add(singleUserPanel(comp1.getName(), comp1.getCulture()));
		playersPanel.add(singleUserPanel(comp2.getName(), comp2.getCulture()));
	}
	
	// return a panel with a single players information
	private JPanel singleUserPanel(String name, Culture culture) {
		JPanel userPanel = new JPanel();
		userPanel.setBorder(BorderFactory.createMatteBorder(3,3,3,0, Color.BLUE));
		userPanel.setLayout(new BorderLayout());
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setFont(GameViewController.getGameFontSize(35));
		nameLabel.setForeground(Color.BLUE);
		
		JLabel cultureLabel = new JLabel(culture.getName());
		cultureLabel.setFont(GameViewController.getGameFontSize(25));
		cultureLabel.setForeground(Color.BLUE);
	
		userPanel.add(nameLabel, BorderLayout.NORTH);
		userPanel.add(cultureLabel, BorderLayout.CENTER);
		return userPanel;
	}
	// // // // // // // // // // // // // // // // // // // // // // // // //
	
	// ROLL PANEL // // // // // // // // // // // // // // // // // // // // //
	// initial setup of roll panel
	public void setupRollPanel() {
		rollPanel.setLayout(new GridLayout(3,1));
		rollPanel.setBounds(400, 250, 300, 300);
		rollPanel.setBorder(BorderFactory.createMatteBorder(5,0,5,0, Color.GREEN));
		updateRollPanel();
	}
	
	// updates roll panel after rolling dice
	public void updateRollPanel() {
		rollPanel.removeAll();
		
		rollPanel.add(playerRollPanel(human));
		rollPanel.add(playerRollPanel(comp1));
		rollPanel.add(playerRollPanel(comp2));
		
		rollPanel.revalidate();
		rollPanel.repaint();
	}
	
	private JPanel playerRollPanel(Player player) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createMatteBorder(3,0,3,0, Color.BLUE));
		JLabel rollLabel = new JLabel(Integer.toString(player.getRoll()), JLabel.CENTER);
		if (player.getRoll() == 0) {
			rollLabel.setText("");
		}
		rollLabel.setFont(GameViewController.getGameFontSize(40));
		rollLabel.setForeground(Color.BLUE);
		panel.add(rollLabel, BorderLayout.CENTER);
		return panel;
	}
	
	// // // // // // // // // // // // // // // // // // // // // // // // //
	
	// PLACE PANEL // // // // // // // // // // // // // // // // // // // // // // // //
	// initial setup of place panel
	public void setupPlacePanel() {
		placePanel.setLayout(new GridLayout(3,1));
		placePanel.setBounds(700, 250, 300, 300);
		placePanel.setBorder(BorderFactory.createMatteBorder(5,0,5,5, Color.GREEN));
		updatePlacePanel();
	}
	
	// updates place panel after rolling
	public void updatePlacePanel() {
		placePanel.removeAll();
					
		placePanel.add(playerPlacePanel(human));
		placePanel.add(playerPlacePanel(comp1));			
		placePanel.add(playerPlacePanel(comp2));
		
		placePanel.revalidate();
		placePanel.repaint();	
	}
	
	//
	private JPanel playerPlacePanel(Player player) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createMatteBorder(3,0,3,3, Color.BLUE));
		JLabel placeLabel = new JLabel("#" + Integer.toString(player.getPlace()),
									JLabel.CENTER);
		if (player.getPlace() == 0) {
			placeLabel.setText("");
		}
		placeLabel.setFont(GameViewController.getGameFontSize(40));
		placeLabel.setForeground(Color.BLUE);
		panel.add(placeLabel, BorderLayout.CENTER);
		return panel;
	}
	// // // // // // // // // // // // // // // // // // // // // // // // //
	
	// ROLLING // // // // // // // // // // // // // // // // // // // // // // // //
	// random roll for all 3 players
	public void playersRoll() {
		human.setRoll(getRandomRoll());
		comp1.setRoll(getRandomRoll());
		comp2.setRoll(getRandomRoll());
	}
	
	// gets a new random roll for two players that tied at the top of the list
	private void breakTie(Player a, Player b) {
		a.setRoll(getRandomRoll());
		b.setRoll(getRandomRoll());
	}
	
	// returns the number of ties found, and changes button text
	private int checkForTie() {
		// all 3 players tied
		if (pList.get(0).getRoll() == pList.get(1).getRoll() && 
			pList.get(1).getRoll() == pList.get(2).getRoll()) {
			rollButton.setText("Break Tie");
			return 3;
		}
		// top two players tie
		else if (pList.get(0).getRoll() == pList.get(1).getRoll()) {
			rollButton.setText("Break Tie");
			return 2;
		}
		rollButton.setText("Done");
		return 0;	// no ties
	}
	
	// sorts the players array by roll, high to low
	// checks for any ties, then sets the players place by sorted array index
	private void setOrder() {
		// sort array list by roll value
		Collections.sort(pList, Player.playerRollComparator);
		// if any ties, roll again
		if (checkForTie() == 0) {
			for (int i = 0; i < pList.size(); i++) {
				pList.get(i).setPlace(i+1);
			}
		}	
	}

	// returns a random integer, represents rolling of two dice
	private Integer getRandomRoll() {
		return new Random().nextInt(11) + 2;	// returns (0 to 10) + 2
	}	
	// // // // // // // // // // // // // // // // // // // // // // // // //
	
	// updates roll and place panels after roll button clicked
	private void updatePanels() {
		updateRollPanel();
		setOrder();
		if (checkForTie() == 0) {
			updatePlacePanel();
		}
	}
	
	// OPTIONS LISTENTER // // // // // // // // // // // // // // // // // // 
	//
	private class OptionListener implements ActionListener
	{   
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() instanceof JButton) {
				// initial roll
				if (( (JButton)e.getSource()).getText() == "Roll") {
					playersRoll();
					updatePanels();
				}
				// break tie
				else if (( (JButton)e.getSource()).getText() == "Break Tie") {
					if (checkForTie() == 3) {
						playersRoll();
					}
					if (checkForTie() == 2) {
						breakTie(pList.get(0), pList.get(1));
						pList.get(2).setRoll(0);
					}
					updatePanels();
				}
			}
		}	
	}

}
