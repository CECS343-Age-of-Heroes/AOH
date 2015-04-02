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
		setBackground(Color.GREEN);
		setBorder(BorderFactory.createMatteBorder(3, 4, 5, 6, Color.BLUE));

		// set up roll button
		rollButton.addActionListener(new OptionListener());
		rollButton.setSize(200, 50);
		int centerX = 600 - rollButton.getWidth() / 2 ;
		rollButton.setLocation(centerX, 650);
		
		// view header
		add(setupHeaderLabel());
		// player header
		add(setupPlayerLabel());
		add(rollButton);
		addPanels();
	}
	
	// setup // // // // // // // // // // // // // // // // // // // // // //
	// setup and add 3 panels
	private void addPanels() {
		setupNamesPanel();
		add(playersPanel);
		
		setupRollPanel();
		add(rollPanel);
		
		setupPlacePanel();
		add(placePanel);
	}
	
	// return header label for view
	private JLabel setupHeaderLabel() {
		JLabel headerLabel = new JLabel("- Roll for Starting Player -", 
							 		    JLabel.CENTER);
		headerLabel.setSize(1200, 100);
		headerLabel.setForeground(Color.BLUE);
		headerLabel.setFont(new Font("Default", Font.BOLD, 35));
		return headerLabel;
	}
	
	// return player label for view
	private JLabel setupPlayerLabel() {
		JLabel playerHeader = new JLabel("Players:");
		playerHeader.setBounds(200, 200, 300, 50);
		playerHeader.setForeground(Color.BLUE);
		playerHeader.setFont(new Font("Default", Font.BOLD, 20));
		return playerHeader;
	}
	
	// NAMES PANEL // // // // // // // // // // // // // // // // // // // // 
	// sets up the panel with players name and culture
	private void setupNamesPanel() {
		playersPanel.setLayout(new GridLayout(3,1));
		playersPanel.setBounds(200, 250, 200, 300);
		playersPanel.setBackground(Color.BLUE);
		// create and add all users
		playersPanel.add(singleUserPanel(human.getName(), human.getCulture()));
		playersPanel.add(singleUserPanel(comp1.getName(), comp1.getCulture()));
		playersPanel.add(singleUserPanel(comp2.getName(), comp2.getCulture()));
	}
	
	// return a panel with a single players information
	private JPanel singleUserPanel(String name, Culture culture) {
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(5,1));
		userPanel.setBackground(Color.BLUE);
		JLabel nameLabel = new JLabel("User: " + name);
		nameLabel.setForeground(Color.WHITE);
		JLabel cultureLabel = new JLabel("Culture: " + culture);
		cultureLabel.setForeground(Color.WHITE);
		
		userPanel.add(new JLabel()); // blank
		userPanel.add(nameLabel);
		userPanel.add(cultureLabel);
		return userPanel;
	}
	// // // // // // // // // // // // // // // // // // // // // // // // //
	
	// ROLL PANEL // // // // // // // // // // // // // // // // // // // // //
	// initial setup of roll panel
	public void setupRollPanel() {
		rollPanel.setLayout(new GridLayout(3,1));
		rollPanel.setBounds(400, 250, 300, 300);
		rollPanel.setBackground(Color.BLUE);
	}
	
	// updates roll panel after rolling dice
	public void updateRollPanel() {
		rollPanel.removeAll();
		JLabel humanRoll = new JLabel(human.getName() + " rolled: " 
				  					  + Integer.toString(human.getRoll()));
		JLabel comp1Roll = new JLabel(comp1.getName() + " rolled: " 
		  		  					  + Integer.toString(comp1.getRoll()));
		JLabel comp2Roll = new JLabel(comp2.getName() + " rolled: " 
		  		  					  + Integer.toString(comp2.getRoll()));
		humanRoll.setForeground(Color.WHITE);
		comp1Roll.setForeground(Color.WHITE);
		comp2Roll.setForeground(Color.WHITE);
		rollPanel.add(humanRoll);
		rollPanel.add(comp1Roll);
		rollPanel.add(comp2Roll);
		
		rollPanel.revalidate();
		rollPanel.repaint();
	}
	// // // // // // // // // // // // // // // // // // // // // // // // //
	
	// PLACE PANEL // // // // // // // // // // // // // // // // // // // // // // // //
	// initial setup of place panel
	public void setupPlacePanel() {
		placePanel.setLayout(new GridLayout(3,1));
		placePanel.setBounds(700, 250, 300, 300);
		placePanel.setBackground(Color.BLUE);
	}
	
	// updates place panel after rolling
	public void updatePlacePanel() {
		placePanel.removeAll();
		
		if (checkForTie() == 0) {
			JLabel humanPlaceLabel = new JLabel(human.getName() + " Place: " +  
									 Integer.toString(human.getPlace()));
			JLabel comp1PlaceLabel = new JLabel(comp1.getName() + " Place: " +  
									 Integer.toString(comp1.getPlace()));
			JLabel comp2PlaceLabel = new JLabel(comp2.getName() + " Place: " +  
									 Integer.toString(comp2.getPlace()));
			humanPlaceLabel.setForeground(Color.WHITE);
			comp1PlaceLabel.setForeground(Color.WHITE);
			comp2PlaceLabel.setForeground(Color.WHITE);
			placePanel.add(humanPlaceLabel);
			placePanel.add(comp1PlaceLabel);
			placePanel.add(comp2PlaceLabel);
		}
		placePanel.revalidate();
		placePanel.repaint();	
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
				// delete this, not needed
				//else if (( (JButton)e.getSource()).getText() == "Done") {
					//System.out.println("Done - Rolling");
				//}
			}
		}	
	}

}
