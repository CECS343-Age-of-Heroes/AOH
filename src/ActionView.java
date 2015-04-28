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
 *  - 8 favors for 1 victory on trade card
 *  - make a send to bank function for cubes -player and +bank
 * Fix:
 * 	- 
 */
@SuppressWarnings("serial")
public class ActionView extends JPanel {
	
	private GamePlayView gpv; // = Main.gvc.getGamePlayView();
	public ActionCardListener acl = new ActionCardListener();
	private ActionCard ac = new ActionCard();
	private Player currentPlayer = Main.gc.getPlayersList().get(0);
	
	private JLabel cardLabel = new JLabel();	// card played
	private JLabel playerLabel = new JLabel();	// player
	
	private JPanel headerPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel footerPanel = new JPanel();
	private JButton nextButton = new JButton("Next");
	
	private int cardValue = 0;	// number on the action card
	
	private int numCardsPerTurn = 9;
	private int numCardsPlayed = 0;
	
	
	// constructor
	public ActionView() {
		setLayout(null);	
		setBounds(0, 0, 300, 800);
		setBackground(Color.GREEN);
		
	}
	
	
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
	public void setPreview() {
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
		
		JButton pass = new JButton("Pass");
		pass.setBounds(5, 5, 90, 40);
		pass.addActionListener(new ActionCardListener());
		footerPanel.add(pass);
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(105, 5, 90, 40);
		if (cancel.getActionListeners().length == 0) {
			cancel.addActionListener(new ActionCardListener());
		}
		footerPanel.add(cancel);
		
//		JButton next = new JButton("Next");
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
		return new ActionCardListener();
	}
		
	//
	public void updateBoardAndBank() {
		//Main.gvc.getGamePlayView().getGameBoardsPanel().updateGameBoard(Main.gc.getPlayersList());
		//Main.gvc.getGamePlayView().setupBankPanel();
		if (gpv == null) {
			gpv = Main.gvc.getGamePlayView();
		}
		gpv.getGameBoardsPanel().updateGameBoard(Main.gc.getPlayersList());
		gpv.setupBankPanel();
		nextButton.setEnabled(true);
	}
	
	//
	public void updateCardView() {
		//Main.gvc.getGamePlayView().setupCardPanel();
		gpv.setupCardPanel();
	}
	
	//
	public void updateActionView() {
		if (gpv == null) {
			gpv = Main.gvc.getGamePlayView();
		}
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
		Main.gc.rotateStartingPlayer();	// ??
		currentPlayer = Main.gc.getNextPlayer(currentPlayer); // rotate to next player
		gpv.setCurrentPlayer(currentPlayer);
		updateBoardAndBank();
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
		spoil.setBounds(50, 300, 200, 50);
		add(spoil);
	}
	
	
	//OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	// action listener for all buttons
	private class ActionCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			//
			if (e.getSource() instanceof JButton) {
				JButton button = (JButton)e.getSource();
				
				if (button.getText().equals("Next")) {
					numCardsPlayed++;
					System.out.println("Num Cards Played = " + numCardsPlayed);
					if (numCardsPlayed >= numCardsPerTurn) {
						endOfActionCardPlay();
					}
					else {
						nextPlayer();
					}
				}
				// re enable action card and clear action view
				else if (button.getText().equals("Cancel")) {
					ac.setEnabled(true);
					clearView();
				}
				else if (button.getText().equals("Pass")) {
					// allow user to discard a card, just disable it for now
					
				}
			}
			if (e.getSource() instanceof ActionCard) {
				ac =  ((ActionCard)e.getSource());	
				updateActionView();
//				updateBoardAndBank();
				ac.setEnabled(false);
			}
		}
	}
	
	
	// TESTING STUFF HERE //
	
	
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




