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
 * 	- switch back to only showing human cards during play 
 */
@SuppressWarnings("serial")
public class GamePlayView extends JPanel {

	private Bank bank = Main.gc.getBank();			// reference to games bank
	private ArrayList<Player> pList = Main.gc.getPlayersList();
	// references to player objects
	private Player human = Main.gc.getHuman();
	private Player computer1 = Main.gc.getComputer1();
	private Player computer2 = Main.gc.getComputer2();
	
	private GameBoards gameBoardsPanel; // = new GameBoards();	// for temp	
//	private GameBoards gameBoardsPanel = new GameBoards();
	private JPanel bankPanel = new JPanel();
	private JPanel cardPanel = new JPanel();
	private ActionView actionView; // = new ActionView();	// for temp
//	private ActionView actionView = new ActionView();
	
	// array list of cultures action cards, changes with each player
	private ArrayList<ActionCard> permanentCards;
	private ArrayList<ActionCard> randomCards;
	private ArrayList<JButton> buttonsList = new ArrayList<>();
	
	private Player currentPlayer = pList.get(0);
	private String currentView = "";
	
	
	//-------------------------------------------------------------------------
	// temp method that sets up most of a game skipping views
	public void setupFakeGame() {
		ArrayList<ProductionTile> pt = Main.gc.getGameProductionTiles();
		
		human.setName("Matt");
		human.setCulture("Greek");
		human.setAge(4);
		human.setPlace(1);
		for (int i = 0; i < 6; i++) {
			setupPlayerProduction(human, pt.remove(0));
		}
		human.getCubes().setCubes(12, 12, 12, 12);
		
		computer1.setCulture("Norse");
		computer1.setAge(4);
		computer1.setPlace(2);
		for (int i = 0; i < 6; i++) {
			setupPlayerProduction(computer1, pt.remove(0));
		}
		
		computer2.setCulture("Egyptian");
		computer2.setAge(4);
		computer2.setPlace(3);
		for (int i = 0; i < 6; i++) {
			setupPlayerProduction(computer2, pt.remove(0));
		}
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
		
		gameBoardsPanel = new GameBoards();// temp
		if (actionView == null) {
			System.out.println("action view null");
			actionView = new ActionView();// temp
		}
		
	
		setLayout(null);
		
		setupActionPanel();
		setupBankPanel();
		setupCardPanel();
		// set locations and sizes of panels
		//actionView
		actionView.setBounds(0, 0, 300, 800);
		bankPanel.setBounds(300, 0, 900, 100);
        gameBoardsPanel.setBounds(300, 100, 900, 600);
        cardPanel.setBounds(300, 700, 900, 100);

        add(actionView);
        add(bankPanel);
        add(gameBoardsPanel);
        add(cardPanel); 
	}
	
	//
	public ActionView getActionView() {
		return actionView;
	}
	
	//
	public GameBoards getGameBoardsPanel() {
		return gameBoardsPanel;
	}
	
	//
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	//
	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
	
	// setup the action panel
	private void setupActionPanel() {
		setupCardSelection();
	}
		
	//
	private void setupCardSelection() {
		System.out.println("Card Selection Time");
		currentView = "Card Selection";
		
		actionView.removeAll();
		
		actionView.setLayout(null);
		actionView.setPreferredSize(new Dimension(300, 800));
		actionView.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.WHITE));
		actionView.setBackground(GameViewController.getGameColor("blue"));
		
		JPanel actionHeaderPanel = new JPanel();
		actionHeaderPanel.setLayout(new BorderLayout());
		actionHeaderPanel.setBounds(0, 0, 300, 100);
		actionHeaderPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		actionHeaderPanel.setOpaque(false);
		JLabel headerLabel = new JLabel("Select Your Cards:", JLabel.CENTER);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setFont(GameViewController.getGameFontSize(35));
		actionHeaderPanel.add(headerLabel, BorderLayout.CENTER);
		//tileButton.add(nameLabel, BorderLayout.NORTH);
		
		JPanel actionBodyPanel = new JPanel();
		actionBodyPanel.setLayout(new GridLayout(3,2));
		actionBodyPanel.setBounds(0, 110, 300, 600);
//		actionBodyPanel.setBackground(Color.YELLOW);
		actionBodyPanel.setOpaque(false);
		
		Dimension cardDim = new Dimension(105, 150);
		
		// retrieve the players cultures possible permanent cards
		for (ActionCard ac : human.getCulture().getPermanentCards()) {
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setOpaque(false);
			
			JButton button = new JButton();
			button.setLayout(new BorderLayout());
			
			JLabel nameLabel = new JLabel(ac.getName(), JLabel.CENTER);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
			JLabel detailLabel = new JLabel(ac.getValue(), JLabel.CENTER);
			
			nameLabel.setFont(new Font("Default", Font.PLAIN, 15));
			detailLabel.setFont(new Font("Default", Font.PLAIN, 12));
			
			button.add(nameLabel, BorderLayout.NORTH);
			button.add(detailLabel, BorderLayout.CENTER);
			
			//button.setText(ac.getName());
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
//		randButton.setText("random deck");
		randButton.setText("Random Deck");
		randButton.setPreferredSize(cardDim);
		randButton.addActionListener(new OptionListener());
		buttonsList.add(randButton);
		buttonPanel.add(randButton);
		actionBodyPanel.add(buttonPanel);
		
		actionView.add(actionHeaderPanel);
		actionView.add(actionBodyPanel);
		actionView.revalidate();
		actionView.repaint();
	}
	
	// setup the bank panel
	public void setupBankPanel() {
		bankPanel.removeAll();
		bankPanel.setLayout(null);
		bankPanel.setPreferredSize(new Dimension(900, 100));
//		bankPanel.setBackground(Color.GREEN);
//		bankPanel.setBackground(GameViewController.getGameColor("green"));
		bankPanel.setBackground(new Color(0,110,0));
//		bankPanel.setBackground(new Color(0,0,210));
		
		JPanel bankP = new JPanel();
		bankP.setLayout(new BorderLayout());
		bankP.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
		bankP.setBounds(0, 0, 140, 100);
		bankP.setOpaque(false);
		JLabel bankLabel = new JLabel("Bank", JLabel.CENTER);
		bankLabel.setForeground(Color.WHITE);
		bankLabel.setFont(GameViewController.getGameFontSize(52));
		bankP.add(bankLabel, BorderLayout.CENTER);
		bankPanel.add(bankP);
		
		//
		JPanel cubesP = new JPanel();
		cubesP.setLayout(new BorderLayout());
		cubesP.setBounds(150, 0, 80, 100);
		cubesP.setOpaque(false);
		JLabel cubesLabel = new JLabel("Cubes", JLabel.CENTER);
		cubesLabel.setForeground(Color.WHITE);
		cubesLabel.setFont(GameViewController.getGameFontSize(28));
		cubesP.add(cubesLabel, BorderLayout.CENTER);
		bankPanel.add(cubesP);
		
		//
		JPanel cubesList = new JPanel();
		cubesList.setLayout(new GridLayout(5,1));
		cubesList.setBounds(240, -2, 45, 99);
		cubesList.setOpaque(false);
		
		for (int i = 0 ; i < bank.getCubes().size(); i++) {
			JPanel cubePanel = new JPanel();
			cubePanel.setOpaque(false);
			JPanel cube = new JPanel();
			cube.setPreferredSize(new Dimension(15,18));
			cube.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
			JLabel cubeValue = new JLabel();
			cubeValue.setForeground(Color.WHITE);
			cubeValue.setFont(new Font("Default", Font.BOLD, 14));
//			cubeValue.setFont(GameViewController.getGameFontSize(18));
			switch (i) {
				case 0:
					cube.setBackground(Cubes.cubeColor("blue"));
					cubeValue.setText(Integer.toString(bank.getCubes().getBlue()));
					break;
				case 1:
					cube.setBackground(Cubes.cubeColor("green"));
					cubeValue.setText(Integer.toString(bank.getCubes().getGreen()));
					break;
				case 2:
					cube.setBackground(Cubes.cubeColor("brown"));
					cubeValue.setText(Integer.toString(bank.getCubes().getBrown()));
					break;
				case 3:
					cube.setBackground(Cubes.cubeColor("yellow"));
					cubeValue.setText(Integer.toString(bank.getCubes().getYellow()));
					break;
				case 4:
					cube.setBackground(Cubes.cubeColor("red"));
					cubeValue.setText(Integer.toString(bank.getCubes().getRed()));
					break;
			}
			cubePanel.add(cube);
			cubePanel.add(cubeValue);
			cubesList.add(cubePanel);
		}
		bankPanel.add(cubesList);
		
		// add victory cards
		VictoryCard theWonder = bank.getTheWonder();
		theWonder.setBounds(330, 0, 80, 98);
		theWonder.updateBankVC();
		VictoryCard mostBuildings = bank.getMostBuildings();
		mostBuildings.setBounds(420, 0, 80, 98);
		mostBuildings.updateBankVC();
		bankPanel.add(theWonder);
		bankPanel.add(mostBuildings);
		
		int WH = 98;	// width and height of tiles (buttons) for bank view
		// production tiles
		JButton prodTiles = new JButton();
		prodTiles.setLayout(new BorderLayout());
		prodTiles.setBounds(540, 0, WH, WH);
		JLabel prodText = new JLabel("Production Tiles", JLabel.CENTER);
		prodText.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		prodText.setFont(GameViewController.getGameFontSize(12));
		prodTiles.add(prodText, BorderLayout.NORTH);
		JLabel prodValueLabel = new JLabel(Integer.toString(Main.gc.getGameProductionTiles().size()),
											JLabel.CENTER);
		prodValueLabel.setFont(GameViewController.getGameFontSize(30));
		prodTiles.add(prodValueLabel, BorderLayout.CENTER);
		bankPanel.add(prodTiles);
		
		// building tiles 
		JButton buildTiles = new JButton();
		buildTiles.setLayout(new BorderLayout());
		buildTiles.setBounds(640, 0, WH, WH);
		JLabel buildText = new JLabel("Building Tiles", JLabel.CENTER);
		buildText.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		buildText.setFont(GameViewController.getGameFontSize(12));
		buildTiles.add(buildText, BorderLayout.NORTH);
		JLabel buildValueLabel = new JLabel(Integer.toString(Main.gc.getGameBuildingTiles().size()),
											JLabel.CENTER);
		buildValueLabel.setFont(GameViewController.getGameFontSize(30));
		buildTiles.add(buildValueLabel, BorderLayout.CENTER);
		bankPanel.add(buildTiles);
		
		// players turn list
		JPanel playersPanel = new JPanel();
		playersPanel.setBounds(780, 0, 120, 100);
		playersPanel.setLayout(new GridLayout(4,1));
		playersPanel.setOpaque(false);
		JLabel turnLabel = new JLabel("Turn Order:");
		turnLabel.setForeground(Color.WHITE);
		turnLabel.setFont(GameViewController.getGameFontSize(23));
		playersPanel.add(turnLabel);
		
		for (int i = 0; i < pList.size(); i++) {
			JLabel pLabel = new JLabel("#"+(i+1)+": " + pList.get(i).getName());
			pLabel.setForeground(Color.WHITE);
			pLabel.setFont(GameViewController.getGameFontSize(20));
			playersPanel.add(pLabel);
		}
		bankPanel.add(playersPanel);
		
		bankPanel.revalidate();
		bankPanel.repaint();
	}
	
	// set up the card panel
	public void setupCardPanel() {
		cardPanel.removeAll();
		cardPanel.setLayout(null);
		cardPanel.setPreferredSize(new Dimension(900, 100));
		cardPanel.setBackground(GameViewController.getGameColor("blue"));
				
		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(0, 5, 150, 100);
		playerPanel.setLayout(new GridLayout(3,1));
		playerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));
		playerPanel.setOpaque(false);
		
//		JLabel pLabel = new JLabel(human.getName(), JLabel.CENTER);
		JLabel pLabel = new JLabel(currentPlayer.getName() + "'s", JLabel.CENTER);
		pLabel.setForeground(Color.WHITE);
		pLabel.setFont(GameViewController.getGameFontSize(25));
		JLabel cLabel = new JLabel("Action Cards", JLabel.CENTER);
		cLabel.setForeground(Color.WHITE);
		cLabel.setFont(GameViewController.getGameFontSize(25));
		
		int numCards = 0;
//		for (ActionCard ac : currentPlayer.getActionCards()) {
		for (ActionCard ac : human.getActionCards()) {
			if (ac.isEnabled()) {
				System.out.println("AC enabled: " + ac.getName());
				numCards++;
			}
		}
//		JLabel dLabel = new JLabel(Integer.toString(currentPlayer.getActionCards().size()) +
//									" of " + Integer.toString(currentPlayer.getAge()), JLabel.CENTER);
		JLabel dLabel = new JLabel(Integer.toString(numCards) +
				" of " + Integer.toString(currentPlayer.getAge()), JLabel.CENTER);
		dLabel.setForeground(Color.WHITE);
		dLabel.setFont(GameViewController.getGameFontSize(25));
				
		playerPanel.add(pLabel);
		playerPanel.add(cLabel);
		playerPanel.add(dLabel);
		playerPanel.setMaximumSize(new Dimension(150, 100));
		cardPanel.add(playerPanel);
		
		JPanel playerCardsPanel = new JPanel();
		playerCardsPanel.setBounds(150, 0, 650, 100);
		playerCardsPanel.setOpaque(false);
		playerCardsPanel.setLayout(new BoxLayout(playerCardsPanel, BoxLayout.LINE_AXIS));
		
		for (ActionCard ac : human.getActionCards()) {
//		for (ActionCard ac : currentPlayer.getActionCards()) {
			((JButton)ac).removeAll();
			((JButton)ac).setLayout(new BorderLayout());
			((JButton)ac).setMaximumSize(new Dimension(68, 95));
			
			JLabel nameLabel = new JLabel(ac.getName(), JLabel.CENTER);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
			JLabel detailLabel = new JLabel(ac.getValue(), JLabel.CENTER);
			((JButton)ac).add(nameLabel, BorderLayout.NORTH);
			((JButton)ac).add(detailLabel, BorderLayout.CENTER);
			
			nameLabel.setFont(new Font("Default", Font.PLAIN, 12));
			detailLabel.setFont(new Font("Default", Font.PLAIN, 10));
			
			if (!currentView.equals("Card Selection")) {
//				if (ac.getActionListeners().length == 0) {
					ac.addActionListener(actionView.getActionCardListener()); 
//				}
			}
			
			playerCardsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
			playerCardsPanel.add(ac); 		
		}
		cardPanel.add(playerCardsPanel);
		
		JPanel peekPanel = peekControlPanel();
		peekPanel.setBounds(780, 0, 120, 100);
		cardPanel.add(peekPanel);
		
		cardPanel.revalidate();
		cardPanel.repaint();
	}
	
	public JPanel peekControlPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.setBackground(new Color(0,115,0));
		panel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.WHITE));
		
		JLabel peekLabel = new JLabel("Show Board", JLabel.CENTER);
		peekLabel.setForeground(Color.WHITE);
		peekLabel.setFont(GameViewController.getGameFontSize(22));
		
		JPanel b1Panel = new JPanel();
		b1Panel.setOpaque(false);
//		b1Panel.setLayout(null);
		JButton show1Button = new JButton("Comp1");
		show1Button.setPreferredSize(new Dimension(110,25));
//		show1Button.setBounds(0, 0, 100, 30);
		show1Button.addActionListener(new OptionListener());
		b1Panel.add(show1Button);
		
		JButton show2Button = new JButton("Comp2");
		show2Button.addActionListener(new OptionListener());
		
		panel.add(peekLabel);
		panel.add(show1Button);
//		panel.add(b1Panel);
		panel.add(show2Button);
		
		return panel;
	}
	
	// fill up computers action cards
	private void autoSelectActionCards(Player p) {
		permanentCards = p.getCulture().getPermanentCards();
		randomCards = p.getCulture().getRandomCards();
		ArrayList<ActionCard> ac = p.getActionCards();
		
		int numCards = p.getAge();
		int numPerm = (numCards/2) + 1;
		int numRand = numCards - numPerm;
//		System.out.println(p.getName() + " HAS " + numPerm + "p,  " + numRand + "r");
		// permanent
		int pSize = permanentCards.size();
		for (int i = 0; i < numPerm; i++) {
			ac.add(permanentCards.remove(new Random().nextInt(pSize--)));
//			ac.add(permanentCards.get(new Random().nextInt(pSize--)));
//			System.out.println("per card size = " + permanentCards.size());
//			System.out.println("pSize = " + pSize);
			//ac.add(permanentCards.remove(new Random().nextInt(permanentCards.size())));
			//ac.add(permanentCards.get(new Random().nextInt(permanentCards.size())));
		}
		// random
		int rSize = randomCards.size();
		for (int i = 0; i < numRand; i++) {
			ac.add(randomCards.remove(new Random().nextInt(rSize--)));
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
				
				if (button.getText().equals("Comp1")) {
					button.setText("Return");
					gameBoardsPanel.showSmallTopBoard();
				}
				else if (button.getText().equals("Comp2")) {
					button.setText("Return");
					gameBoardsPanel.showSmallBottomBoard();
				}
				else if (button.getText().equals("Return")) {
					setupCardPanel();
					gameBoardsPanel.updateGameBoard(pList);
				}
				//
				else if (human.getActionCards().size() < human.getAge()) {
					// remove card from permanent or random cultures array list
					// of cards and add card to players action card array list		
					permanentCards = human.getCulture().getPermanentCards();
					randomCards = human.getCulture().getRandomCards();
					
					if (button.getText().equalsIgnoreCase("Random Deck")) {
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
						System.out.println("select for computers + done clicked");
						currentView = "";
						autoSelectActionCards(computer1);
						autoSelectActionCards(computer2);
						actionView.clearView();
						actionView.setPreview();
						setupCardPanel();
						testing();	// testing only
					}
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
