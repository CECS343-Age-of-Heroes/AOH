import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/*
 * GamePlayView:
 * - 
 */
/*
 * ADD:
 * 	- add the action card played to the action panel and the result of what
 * 	  the card did or going to do
 * 	 OR put up what the card will do in the action panel and if its allowed
 *    with the players current resources and then a button to confirm action
 * 	- an 'S' in the top corner for starting player
 * FIX:
 * 	- 
 */
@SuppressWarnings("serial")
public class GamePlayView extends JPanel {

	private Bank bank = Main.gc.getBank();			// reference to games bank
	private ArrayList<Player> pList = Main.gc.getPlayersList();
	// references to player objects
	private Player human = Main.gc.getHuman();
	private Player computer1 = Main.gc.getComputer1();
	private Player computer2 = Main.gc.getComputer2();
	
	//private JPanel gameBoardsPanel = new GameBoards();
	private GameBoards gameBoardsPanel; // = new GameBoards();	
	private JPanel bankPanel = new JPanel();
	private JPanel cardPanel = new JPanel();
	//private JPanel actionPanel = new JPanel();
	private ActionView actionView; // = new ActionView();
	
	// array list of cultures action cards, changes with each player
	private ArrayList<ActionCard> permanentCards;
	private ArrayList<ActionCard> randomCards;
	private ArrayList<JButton> buttonsList = new ArrayList<>();
	
	private Player currentPlayer = pList.get(0);
	
	
	// use to rotate players and player boards on view
	// Main.gc.rotateStartingPlayer();
	// gameBoardsPanel.updateGameBoard(pList);
	
	
	//-------------------------------------------------------------------------
	// temp method that sets up most of a game skipping views
	private void setupFakeGame() {
		ArrayList<ProductionTile> pt = Main.gc.getGameProductionTiles();
		
		human.setName("Matt");
		human.setCulture("Greek");
		human.setPlace(1);
		for (int i = 0; i < 6; i++) {
			ProductionTile t = pt.remove(0);
			setupPlayerProduction(human, t);
		}
		
		computer1.setCulture("Norse");
		computer1.setPlace(2);
		for (int i = 0; i < 6; i++) {
			ProductionTile t = pt.remove(0);
			setupPlayerProduction(computer1, t);
		}
		
		computer2.setCulture("Egyptian");
		computer2.setPlace(3);
		for (int i = 0; i < 6; i++) {
			ProductionTile t = pt.remove(0);
			setupPlayerProduction(computer2, t);
		}
		
		//currentPlayer = pList.get(0); //human;
	}
	
	// temp method
	private void setupPlayerProduction(Player player, ProductionTile tile) { 
		ArrayList<String> list = player.getCulture().getProductionAreaList();
		ArrayList<ProductionTile> tilesList = player.getProductionTiles();
		if (list.contains(tile.getType())) {
			int j = list.indexOf(tile.getType());
			tilesList.remove(j);
			tilesList.add(j, tile);
			list.set(j, "FILLED");
		}
	}
	//-------------------------------------------------------------------------
	
	
	// constructor
	public GamePlayView() {
		
		setupFakeGame();	// temp
		gameBoardsPanel = new GameBoards();
		actionView = new ActionView();
		

		setLayout(null);
		//setPreferredSize(new Dimension(1200, 822));
		setBackground(Color.ORANGE);
		
		setupActionPanel();
		setupBankPanel();
		setupCardPanel();
		
		// set locations and sizes of panels
		//actionView
		//actionPanel.setBounds(0, 0, 300, 800);
		bankPanel.setBounds(300, 0, 900, 100);
        gameBoardsPanel.setBounds(300, 100, 900, 600);
        cardPanel.setBounds(300, 700, 900, 100);
        
        
        //add(actionPanel);
        add(actionView);
        add(bankPanel);
        add(gameBoardsPanel);
        add(cardPanel); 
	}
	
	//
	public GameBoards getGameBoardsPanel() {
		return gameBoardsPanel;
	}
	
	//
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	
	// setup the action panel
	private void setupActionPanel() {
		setupCardSelection();
	}
	
	//
	private void actionPanelDoneButton(String headerText) {
		//actionPanel.removeAll();
		//actionPanel.setLayout(null);
		actionView.removeAll();
		actionView.setLayout(null);
		
		JPanel actionHeaderPanel = new JPanel();
		actionHeaderPanel.setLayout(new BorderLayout());
		actionHeaderPanel.setBounds(0, 200, 300, 200);
		actionHeaderPanel.setBackground(Color.ORANGE);
		
		//JLabel headerLabel = new JLabel(headerText, JLabel.CENTER);
		JTextArea headerLabel = new JTextArea();
		headerLabel.setText(headerText);
		headerLabel.setLineWrap(true);
		headerLabel.setWrapStyleWord(true);
		
		headerLabel.setFont(new Font("Default", Font.BOLD, 25));
		actionHeaderPanel.add(headerLabel, BorderLayout.CENTER);
		
		JButton doneButton = new JButton("Done");
		doneButton.setBounds(50, 150, 200, 50);
		doneButton.addActionListener(new OptionListener());
		actionHeaderPanel.add(doneButton, BorderLayout.SOUTH);
		
//		actionPanel.add(actionHeaderPanel);
//		actionPanel.revalidate();
//		actionPanel.repaint();
		actionView.add(actionHeaderPanel);
		actionView.revalidate();
		actionView.repaint();
	}
	
	
	private void setupCardSelection() {
//		actionPanel.removeAll();
//		
//		actionPanel.setLayout(null);
//		actionPanel.setPreferredSize(new Dimension(300, 800));
//		actionPanel.setBackground(Color.BLUE);
		actionView.removeAll();
		
		actionView.setLayout(null);
		actionView.setPreferredSize(new Dimension(300, 800));
		actionView.setBackground(Color.BLUE);
		
		JPanel actionHeaderPanel = new JPanel();
		actionHeaderPanel.setLayout(new BorderLayout());
		actionHeaderPanel.setBounds(0, 0, 300, 100);
		actionHeaderPanel.setBackground(Color.ORANGE);
		JLabel headerLabel = new JLabel("Select Your Cards:", JLabel.CENTER);
		headerLabel.setFont(new Font("Default", Font.BOLD, 25));
		actionHeaderPanel.add(headerLabel, BorderLayout.CENTER);
		//tileButton.add(nameLabel, BorderLayout.NORTH);
		
		JPanel actionBodyPanel = new JPanel();
		actionBodyPanel.setLayout(new GridLayout(3,2));
		actionBodyPanel.setBounds(0, 100, 300, 600);
		actionBodyPanel.setBackground(Color.YELLOW);
		
		Dimension cardDim = new Dimension(105, 150);
		
		// retrieve the players cultures possible permanent cards
		for (ActionCard ac : human.getCulture().getPermanentCards()) {
			JPanel buttonPanel = new JPanel();
			buttonPanel.setOpaque(false);
			
			JButton button = new JButton();
			button.setText(ac.toString());
			button.setPreferredSize(cardDim);
			button.addActionListener(new OptionListener());
			buttonsList.add(button);
			
			if (human.getActionCards().contains(ac)) {
				button.setEnabled(false);
			}
			buttonPanel.add(button);
			actionBodyPanel.add(buttonPanel);
		}
		
		// players cultures random cards 
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		
		JButton randButton = new JButton();
		randButton.setText("random deck");
		randButton.setPreferredSize(cardDim);
		randButton.addActionListener(new OptionListener());
		buttonsList.add(randButton);
		buttonPanel.add(randButton);
		actionBodyPanel.add(buttonPanel);
		
//		actionPanel.add(actionHeaderPanel);
//		actionPanel.add(actionBodyPanel);
//		
//		actionPanel.revalidate();
//		actionPanel.repaint();
		actionView.add(actionHeaderPanel);
		actionView.add(actionBodyPanel);
		
		actionView.revalidate();
		actionView.repaint();
	}
	
	// setup the bank panel
	public void setupBankPanel() {
		bankPanel.removeAll();
		
		JLabel bankLabel = new JLabel("Bank:  ");
		bankLabel.setFont(new Font("Default", Font.BOLD, 25));
		bankPanel.add(bankLabel);
		
		bankPanel.setPreferredSize(new Dimension(900, 100));
		bankPanel.setBackground(Color.GREEN);
		
		bankPanel.add(new JLabel(bank.getCubes().toString()));
		
		VictoryCard theWonder = bank.getTheWonder();
		VictoryCard mostBuildings = bank.getMostBuildings();
		
		Dimension cardDim = new Dimension(75, 95);
		Font cF = new Font("Default", Font.PLAIN, 8);
		theWonder.setPreferredSize(cardDim);
		theWonder.updateFont(cF);
		
		mostBuildings.setPreferredSize(cardDim);
		mostBuildings.updateFont(cF);
		theWonder.setEnabled(false);
		mostBuildings.setEnabled(false);
		
		bankPanel.add(theWonder);
		bankPanel.add(mostBuildings);
		
		JButton prodTiles = new JButton("Production Tiles #"+ Main.gc.getGameProductionTiles().size());
		prodTiles.setPreferredSize(new Dimension(95, 95));
		prodTiles.setFont(new Font("Default", Font.PLAIN, 10));
		prodTiles.setEnabled(false);
		bankPanel.add(prodTiles);
		
		JButton buildTiles = new JButton("Building Tiles #"+ Main.gc.getGameBuildingTiles().size());
		buildTiles.setPreferredSize(new Dimension(95, 95));
		buildTiles.setFont(new Font("Default", Font.PLAIN, 10));
		buildTiles.setEnabled(false);
		bankPanel.add(buildTiles);
		
		bankPanel.revalidate();
		bankPanel.repaint();
	}
	
	// set up the card panel
	private void setupCardPanel() {
		cardPanel.removeAll();
		
		cardPanel.setPreferredSize(new Dimension(900, 100));
		cardPanel.setBackground(Color.ORANGE);
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.LINE_AXIS));
				
		JPanel playersCardPanel = new JPanel();
		playersCardPanel.setLayout(new GridLayout(3,1));
		playersCardPanel.setBackground(Color.ORANGE);
		JLabel pLabel = new JLabel(human.getName(), JLabel.CENTER);
		JLabel cLabel = new JLabel("Action Cards", JLabel.CENTER);
		playersCardPanel.add(pLabel);
		playersCardPanel.add(cLabel);
		playersCardPanel.setMaximumSize(new Dimension(100, 100));
		cardPanel.add(playersCardPanel);
		
		for (ActionCard ac : human.getActionCards()) {
			((JButton)ac).setMaximumSize(new Dimension(68, 95));
			ac.setText(ac.toString());
			ac.setFont(new Font("Default", Font.PLAIN, 10));
			if (ac.getActionListeners().length == 0) {
				ac.addActionListener(actionView.getActionCardListener());
			}
			cardPanel.add(ac);
			cardPanel.add(Box.createRigidArea(new Dimension(20, 0))); // works			
		}
		cardPanel.revalidate();
		cardPanel.repaint();
	}
	
	// fill up computers action cards
	private void selectComputerCards(Player p) {
		permanentCards = p.getCulture().getPermanentCards();
		randomCards = p.getCulture().getRandomCards();
		ArrayList<ActionCard> ac = p.getActionCards();
		
		int numCards = p.getAge();
		int numPerm = (numCards/2) + 1;
		int numRand = numCards - numPerm;
		System.out.println(p.getName() + " HAS " + numPerm + "p,  " + numRand + "r");
		// permanent
		for (int i = 0; i < numPerm; i++) {
			//ac.add(permanentCards.remove(new Random().nextInt(permanentCards.size())));
			ac.add(permanentCards.get(new Random().nextInt(permanentCards.size())));
		}
		// random
		for (int i = 0; i < numRand; i++) {
			ac.add(randomCards.remove(new Random().nextInt(randomCards.size())));
		}
	}
		
	//
	public OptionListener getOptionListener() {
		return new OptionListener();
	}
	
	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	// action listener for buttons
	private class OptionListener implements ActionListener
	{   
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() instanceof JButton) {
				JButton button = (JButton)e.getSource();
				
				if (human.getActionCards().size() < human.getAge()) {
					// remove card from permanent or random cultures array list
					// of cards and add card to players action card array list		
					permanentCards = human.getCulture().getPermanentCards();
					randomCards = human.getCulture().getRandomCards();
					
					if (button.getText().equals("random deck")) {
						int index = new Random().nextInt(randomCards.size());						
						human.getActionCards().add(randomCards.remove(index));
					}
					else {
						int index = buttonsList.indexOf(button);
						human.getActionCards().add(permanentCards.get(index));
						button.setEnabled(false);
					}
					setupCardPanel();
					if (human.getActionCards().size() == human.getAge()) {
						actionPanelDoneButton("Action Card \nSelection Completed");
					}
				}
				else if (button.getText().equals("Done")) {
					System.out.println("select for computers + done clicked");
					selectComputerCards(computer1);
					selectComputerCards(computer2);
					actionView.removeAll();
					actionView.revalidate();
					actionView.repaint();
					testing();
				}
				
			}
		}	
	}
	
	
	
	// testing funciton
	private void testing() {
		System.out.println("\nPRINTING CARDS...");
		System.out.println("Human Cards:");
		for (ActionCard ac : human.getActionCards()) {
			System.out.println(ac.toString());
		}
		System.out.println("\nComputer1 Cards:");
		for (ActionCard ac : computer1.getActionCards()) {
			System.out.println(ac.toString());
		}
		System.out.println("\nComputer2 Cards:");
		for (ActionCard ac : computer2.getActionCards()) {
			System.out.println(ac.toString());
		}
	}

	
}
