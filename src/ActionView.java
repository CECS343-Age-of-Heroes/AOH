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
 *  - make a send to bank function for cubes -player and +bank
 * Fix:
 * 	- 
 */
@SuppressWarnings("serial")
public class ActionView extends JPanel {
	
	private GamePlayView gpv;
	public ActionCardListener acl = new ActionCardListener();
	private ActionCard ac = new ActionCard();
	private Player currentPlayer = Main.gc.getPlayersList().get(0);
	
	private JLabel cardLabel = new JLabel();	// card played
	private JLabel playerLabel = new JLabel();	// player
	
	private JPanel headerPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel footerPanel = new JPanel();
	
	private JButton passButton = new JButton("Pass");
	private JButton cancelButton = new JButton("Cancel");
	private JButton nextButton = new JButton("Next");
	
	private int cardValue = 0;	// number on the action card
	private int numCardsPerTurn = 9;
	private int numCardsPlayed = 0;
	private String mainView = "";
	
	
	// constructor
	public ActionView() {
		setLayout(null);	
		setBounds(0, 0, 300, 800);
		setBackground(Color.GREEN);
		
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	//
	public void setPreview() {
		cancelButton.setEnabled(false);
		passButton.setEnabled(true);
		
		headerPanel.removeAll();
		headerPanel.setLayout(null);	
		headerPanel.setBounds(0, 0, 300, 150);
		headerPanel.setOpaque(false);
		
		JLabel playerName = new JLabel(currentPlayer.getName());
		playerName.setBounds(10, 10, 290, 75);
		playerName.setFont(new Font("Default", Font.PLAIN, 33));
		playerName.setForeground(Color.WHITE);
		headerPanel.add(playerName);
		
		JLabel detail = new JLabel("Select Card To Play");
		detail.setFont(new Font("Default", Font.PLAIN, 22));
		detail.setForeground(Color.WHITE);
		detail.setBounds(10, 70, 290, 75);
		headerPanel.add(detail);
				
		headerPanel.revalidate();
		headerPanel.repaint();
		add(headerPanel);
		
		setupFooterPanel();
	}
	
	//
	private void setupHeaderPanel() {
		headerPanel.removeAll();
		headerPanel.setLayout(null);	
		headerPanel.setBounds(0, 0, 300, 150);

		cardLabel.setText(ac.getName());
		cardLabel.setBounds(10, 10, 200, 75);
		cardLabel.setFont(new Font("Default", Font.PLAIN, 33));
		cardLabel.setForeground(Color.WHITE);
		headerPanel.add(cardLabel);
		
		playerLabel.setText("Player: " + currentPlayer.getName());
		playerLabel.setFont(new Font("Default", Font.PLAIN, 22));
		playerLabel.setForeground(Color.WHITE);
		playerLabel.setBounds(10, 70, 200, 75);
		headerPanel.add(playerLabel);
		
		JButton card = new JButton();
		card.setBounds(200, 20, 80, 120);
		card.setText(ac.toString());
		headerPanel.add(card);
		
		headerPanel.revalidate();
		headerPanel.repaint();
		add(headerPanel);
	}
	
	//
	private void setupMainPanel() {
		mainPanel.removeAll();
		mainPanel.setBounds(0, 150, 300, 600);
		mainPanel.setBackground(Color.YELLOW);
		
		if (ac instanceof NextAgeCard) {
			cardValue = ((NextAgeCard) ac).getCost();
			mainPanel = new NextAgeActionView(currentPlayer, cardValue);
		}
		else if (ac instanceof TradeCard) {
			cardValue = ((TradeCard) ac).getTradeCost();
			mainPanel = new TradeActionView(currentPlayer, cardValue);	
		}
		else if (ac instanceof BuildCard) {
			cardValue = ((BuildCard) ac).getNumOfBuildings();
			mainPanel = new BuildActionView(currentPlayer, cardValue);
		}
		else if (ac instanceof ExploreCard) {
			cardValue = ((ExploreCard) ac).getNumOfTiles();
			mainPanel = new ExploreActionView(currentPlayer, cardValue);
//			testPrintAllPlayersProductionArea();	// testing
		}
		else if (ac instanceof GatherCard) {
			mainPanel = new GatherActionView(currentPlayer, ((GatherCard) ac).getResourceType());
		}
		
		mainPanel.revalidate();
		mainPanel.repaint();
		add(mainPanel);
	}
	
	//
	private void setupFooterPanel() {
		footerPanel.removeAll();
		footerPanel.setLayout(null);
		footerPanel.setBounds(0, 750, 300, 50);
		footerPanel.setBackground(Color.DARK_GRAY);
		
		passButton.setBounds(5, 5, 90, 40);
		if (passButton.getActionListeners().length == 0) {
			passButton.addActionListener(new ActionCardListener());
		}
		footerPanel.add(passButton);
		
		cancelButton.setBounds(105, 5, 90, 40);
		if (cancelButton.getActionListeners().length == 0) {
			cancelButton.addActionListener(new ActionCardListener());
		}
		footerPanel.add(cancelButton);
		
		nextButton.setEnabled(false);
		nextButton.setBounds(205, 5, 90, 40);
		if (nextButton.getActionListeners().length == 0) {
			nextButton.addActionListener(new ActionCardListener());
		}
		footerPanel.add(nextButton);
		
		footerPanel.revalidate();
		footerPanel.repaint();
		add(footerPanel);
	}
	
	//
	public ActionCardListener getActionCardListener() {
		if (acl == null) {
			acl = new ActionCardListener();
		}
		return acl;
	}
		
	//
	public void updateBoardAndBank() {
		gpv.getGameBoardsPanel().updateGameBoard(Main.gc.getPlayersList());
		gpv.setupBankPanel();
		passButton.setEnabled(false);
		cancelButton.setEnabled(false);
		nextButton.setEnabled(true);
	}
	
	//
	public void updateCardView() {
		gpv.setupCardPanel();
	}
	
	//
	public void updateActionView() {
		removeAll();
		setupHeaderPanel();
		setupMainPanel();
		setupFooterPanel();
		revalidate();
		repaint();
	}
	
	// clear action view
	public void clearView() {
		System.out.println("Clear View");
		removeAll();
		revalidate();
		repaint();
	}
	
	// rotate to next player, update board and clear action view
	private void nextPlayer() {
		currentPlayer = Main.gc.getNextPlayer(currentPlayer); // rotate to next player
		gpv.setCurrentPlayer(currentPlayer);
		updateBoardAndBank();
		passButton.setEnabled(true);
		updateCardView();	
		clearView();
		setPreview();
	}
	
	//
	@SuppressWarnings("unused")
	private void selectComputerActionCard(Player player) {
		
	
		// allow human to select a tile when computer plays an explore 
		// action card 
		
		// display summary of what computer action did
		// FOR trade, next age, gather, build
	}
	
	// 
	private void endOfActionCardPlay() {
		clearView();
		setLayout(null);
		JLabel completedLabel = new JLabel("ActionCard Play Completed");
		completedLabel.setFont(new Font("Default", Font.PLAIN, 35));
		completedLabel.setBounds(20, 200, 250, 30);
		add(completedLabel);
		
		JButton spoil = new JButton("Spoilage");
		spoil.addActionListener(new ActionCardListener());
		spoil.setBounds(50, 300, 200, 50);
		add(spoil);
	}
	
	//
	private void setupCardSelection() {
		removeAll();
		setLayout(null);
		
		Player human = Main.gc.getHuman();
		
		JPanel actionHeaderPanel = new JPanel();
		actionHeaderPanel.setLayout(new BorderLayout());
		actionHeaderPanel.setBounds(0, 0, 300, 100);
		actionHeaderPanel.setOpaque(false);
		//JLabel headerLabel = new JLabel(currentPlayer.getName() + " Select Discard:", JLabel.CENTER);
		JLabel headerLabel = new JLabel(human.getName() + " Select Discard:", JLabel.CENTER);
		headerLabel.setFont(new Font("Default", Font.BOLD, 25));
		actionHeaderPanel.add(headerLabel, BorderLayout.CENTER);
		add(actionHeaderPanel);
		
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(3,2));
		mainPanel.setBounds(0, 100, 300, 600);
		mainPanel.setOpaque(false);
		
//		for (ActionCard ac : currentPlayer.getActionCards()) {
		for (ActionCard ac : human.getActionCards()) {
			JPanel buttonPanel = new JPanel();
			buttonPanel.setOpaque(false);
			
			((JButton)ac).removeAll();
			((JButton)ac).setLayout(new BorderLayout());
			JLabel nameLabel = new JLabel(ac.getName(), JLabel.CENTER);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
			JLabel detailLabel = new JLabel(ac.getValue(), JLabel.CENTER);
			nameLabel.setFont(new Font("Default", Font.PLAIN, 15));
			detailLabel.setFont(new Font("Default", Font.PLAIN, 12));
			((JButton)ac).add(nameLabel, BorderLayout.NORTH);
			((JButton)ac).add(detailLabel, BorderLayout.CENTER);
			
			((JButton)ac).setPreferredSize(new Dimension(105, 150));
			
			if (!ac.isEnabled()) {
				ac.setEnabled(false);
			}
			buttonPanel.add(ac);
			mainPanel.add(buttonPanel);
		}
		
		mainPanel.revalidate();
		mainPanel.repaint();
		add(mainPanel);
		
		setupFooterPanel();
		passButton.setEnabled(false);
		cancelButton.setEnabled(true);
		nextButton.setEnabled(true);
		
		revalidate();
		repaint();
	}
	
	
	//OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	// action listener for all buttons
	private class ActionCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			if (gpv == null) {
				gpv = Main.gvc.getGamePlayView();
			}
			
			//
			if (e.getSource() instanceof ActionCard) {
				System.out.println("Action Card clicked...");
				ac =  ((ActionCard)e.getSource());
				System.out.println(ac.toString() + "  "+ ac.getActionListeners().toString());
				
				if (mainView.equals("")) {
					System.out.println("Action Card PLAY");
					updateActionView();
					cancelButton.setEnabled(true);
					ac.setEnabled(false);
//					updateBoardAndBank();
				}
				else if (mainView.equalsIgnoreCase("Pass")) {
					System.out.println("Action Card DISCARD");
					clearView();
//					setPreview();
					updateCardView();	
					setupFooterPanel();
					
					ac.setEnabled(false);
					passButton.setEnabled(false);
					cancelButton.setEnabled(false);
					nextButton.setEnabled(true);
					mainView = "";
				}
				else if (mainView.equalsIgnoreCase("Discarding")) {
					System.out.println("Action Card END OF ROUND DISCARDing");
					
//					clearView();
//					setPreview();
//					updateCardView();	
//					setupFooterPanel();
					
					ac.setEnabled(false);
					
					passButton.setEnabled(false);
					cancelButton.setEnabled(false);
					nextButton.setEnabled(true);
//					mainView = "";
				}
			}
			//
			else if (e.getSource() instanceof JButton) {
				System.out.println("Button clicked");
				
				JButton button = (JButton)e.getSource();
				
//				if (button.equals(nextButton)) {
				if (button.getText().equals("Next")) {
					numCardsPlayed++;
					System.out.println("Num Cards Played = " + numCardsPlayed);
					//if (numCardsPlayed < numCardsPerTurn) {
					if (numCardsPlayed < 1) {
//						nextPlayer();		// put back after testing
					}
					else {
						endOfActionCardPlay();
						numCardsPlayed = 0;
					}
				}
				// re enable action card and clear action view
				else if (button.equals(cancelButton)) {
					ac.setEnabled(true);
					clearView();
					setPreview();
				}
				else if (button.equals(passButton)) {
					// allow user to discard a card, just disable it for now
					mainView = "Pass";
					setupCardSelection();
				}
				else if (button.getText().equals("Spoilage")) {
					Main.gc.getBank().spoilage();
					updateBoardAndBank();
					
					clearView();
//					Main.gc.rotateStartingPlayer();  // put back after testing
//					setPreview();
					setupCardSelection();
					mainView = "Discarding";
					nextButton.setText("Finished");
				}
				else if (button.getText().equals("Finished")) {
					System.out.println("Finished Discarding");
					mainView = "";
					// finished discarding	
					clearView();
					putUsedCardsBack();
					Main.gvc.backToVictoryCubePlacement();
					
					// put used cards back
//					setPreview();
//					setupCardSelection();
//					updateCardView();
					nextButton.setText("Next");
				}
			}
		}
	}
	
	//
	private void putUsedCardsBack() {
		
//		for (Player p : Main.gc.getPlayersList()) {
			
			ArrayList<ActionCard> temp = new ArrayList<>();
			
//			if (p.getType().equalsIgnoreCase("human")) {
			Player p = Main.gc.getHuman();
				for (ActionCard ac : p.getActionCards()) {
					if (ac.isEnabled()) {
						temp.add(ac);
					}
				}
				p.getActionCards().clear();
				p.setActionCards(temp);
//				for (ActionCard ac : p.getActionCards()) {
//					ac.addActionListener(new ActionCardListener());
//				}
				testPrintPlayerActionCards(p);
//			}
//		}
	}
	
	
	// TESTING STUFF HERE //
	
	private void testPrintPlayerActionCards(Player p) {
		System.out.println("");
		System.out.println(p.getName() + "  action cards:");
		for (ActionCard ac : p.getActionCards()) {
			System.out.println(ac.toString()+"  "+ ac.getActionListeners().length);
//			System.out.println();
		}
		System.out.println("");
	}
	
	// testing only, player production
	private void testPrintAllPlayersProductionArea() {
		ArrayList<Player> p = Main.gc.getPlayersList();
		for (Player player : p) {
			System.out.println(player.getName()+"------------------");
			System.out.println(player.getProductionTiles().size());
			for (ProductionTile tile : player.getProductionTiles()) {
				System.out.println(tile.getType() +" "+ tile.getColor() +" "+ tile.getValue());
			}
			System.out.println("---------------------------");
		}
	}
	
	
}




