
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
 * 	- an 'S' in the top corner for starting player
 * FIX:
 * 	- 
 */


@SuppressWarnings("serial")
public class GamePlayView extends JPanel {

	private Bank bank = Main.gc.getBank();			// reference to games bank
	private ArrayList<Player> pList = Main.gc.getPlayersList();
	
	private JPanel gameBoardsPanel = new GameBoards();	// change back after testing
//	private JPanel gameBoardsPanel = new JPanel();
	private JPanel bankPanel = new JPanel();
	private JPanel cardPanel = new JPanel();
	private JPanel actionPanel = new JPanel();
	
	// array list of cultures action cards, changes with each player
	private ArrayList<ActionCard> permanentCards;
	private ArrayList<ActionCard> randomCards;
	private ArrayList<JButton> buttonsList = new ArrayList<>();
	
	private Player human = Main.gc.getHuman();
	
	
	// constructor
	public GamePlayView() {
		setLayout(null);
		//setPreferredSize(new Dimension(1200, 822));
		setBackground(Color.ORANGE);
        
		// temp player
//		human.setName("MH");
//		human.setCulture("Greek");
//        System.out.println("Name: " + tPlayer.getName());
//        System.out.println("Culture: " + tPlayer.getCulture().getName());
		
		setupActionPanel();
		setupBankPanel();
		setupCardPanel();
		
		// set locations and sizes of panels
		actionPanel.setBounds(0, 0, 300, 800);
		bankPanel.setBounds(300, 0, 900, 100);
        gameBoardsPanel.setBounds(300, 100, 900, 600);
        cardPanel.setBounds(300, 700, 900, 100);
        
//        System.out.println("Panel Sizes: ");
//        System.out.println("av: " + actionPanel.getWidth()+","+ actionPanel.getHeight());
//        System.out.println("bv: " + bankPanel.getWidth()+","+ bankPanel.getHeight());
//        System.out.println("gb: " + gameBoardsPanel.getWidth()+","
//        					+ gameBoardsPanel.getHeight());
//        System.out.println("cv: " + cardPanel.getWidth()+","+ cardPanel.getHeight());
        
        add(actionPanel);
        add(bankPanel);
        add(gameBoardsPanel);
        add(cardPanel); 
	}
	
	
	// setup the action panel
	private void setupActionPanel() {
		setupCardSelection();
	}
	
	private void setupCardSelection() {
		actionPanel.removeAll();
		
		actionPanel.setLayout(null);
		actionPanel.setPreferredSize(new Dimension(300, 800));
		actionPanel.setBackground(Color.BLUE);
		
		JPanel actionHeaderPanel = new JPanel();
		actionHeaderPanel.setLayout(new BorderLayout());
		actionHeaderPanel.setBounds(0, 0, 300, 100);
		actionHeaderPanel.setBackground(Color.ORANGE);
		JLabel headerLabel = new JLabel("Select Your Cards:", JLabel.CENTER);
		headerLabel.setFont(new Font("Default", Font.BOLD, 25));
		actionHeaderPanel.add(headerLabel, BorderLayout.CENTER);
		//tileButton.add(nameLabel, BorderLayout.NORTH);
		
		JPanel actionBodyPanel = new JPanel();
		
		if (human.getActionCards().size() >= human.getAge()) {
			System.out.println("**equal size - put done button up");
//			actionBodyPanel.removeAll();
			
//			actionBodyPanel.setLayout(null);
			actionBodyPanel.setBounds(0, 100, 300, 600);
			actionBodyPanel.setBackground(Color.YELLOW);
			
			JButton doneButton = new JButton("Done");
			doneButton.setPreferredSize(new Dimension(200, 50));
//			doneButton.setBounds(50, 100, 200, 50);
			actionBodyPanel.add(doneButton);
			
//			actionBodyPanel.revalidate();
//			actionBodyPanel.repaint();
		}
		else {
//			actionBodyPanel.removeAll();
			
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
				
				buttonPanel.add(button);
				//actionBodyPanel.add(button);
				actionBodyPanel.add(buttonPanel);
			}
			
			// players cultures random cards // needs to be a deck
			JPanel buttonPanel = new JPanel();
			buttonPanel.setOpaque(false);
			
			JButton randButton = new JButton();
			randButton.setText("random deck");
			randButton.setPreferredSize(cardDim);
			randButton.addActionListener(new OptionListener());
			buttonsList.add(randButton);
			buttonPanel.add(randButton);
			//actionBodyPanel.add(randButton);
			actionBodyPanel.add(buttonPanel);
			
//			actionBodyPanel.revalidate();
//			actionBodyPanel.repaint();
		}
		
		actionPanel.add(actionHeaderPanel);
		actionPanel.add(actionBodyPanel);
		
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	// setup the bank panel
	private void setupBankPanel() {
		JLabel bankLabel = new JLabel("Bank:  ");
		bankLabel.setFont(new Font("Default", Font.BOLD, 25));
		bankPanel.add(bankLabel);
		
		bankPanel.setPreferredSize(new Dimension(900, 100));
		bankPanel.setBackground(Color.GREEN);
		
		bankPanel.add(new JLabel(bank.getCubes().toString()));
		
		VictoryCard theWonder = bank.getTheWonder();
		VictoryCard mostBuildings = bank.getMostBuildings();

		//Dimension cardDim = new Dimension(68, 95); // 5x7
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
		
		//System.out.println("GameTiles: " + GameController.getGameTiles().size());
		//bankPanel.add(new JButton("Game Tiles #" + GameController.getGameTiles().size()));
		//System.out.println("GameTiles: " + MainTemp.gc.getGameTiles().size());
		JButton tiles = new JButton("Game Tiles #"+ Main.gc.getGameProductionTiles().size());
		tiles.setPreferredSize(new Dimension(95, 95));
		tiles.setFont(new Font("Default", Font.PLAIN, 10));
		tiles.setEnabled(false);
		bankPanel.add(tiles);
	}
	
	// set up the card panel
	private void setupCardPanel() {
		cardPanel.removeAll();
		
		cardPanel.setPreferredSize(new Dimension(900, 100));
		cardPanel.setBackground(Color.ORANGE);
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.LINE_AXIS));
		
//		cardPanel.add(Box.createHorizontalGlue());
//		cardPanel.add(aCard);
//		cardPanel.add(Box.createRigidArea(new Dimension(10, 0)));
//		cardPanel.add(setButton);
		
		//cardPanel.add(Box.createVerticalGlue());
		
		
		JPanel playersCardPanel = new JPanel();
		playersCardPanel.setLayout(new GridLayout(3,1));
		playersCardPanel.setBackground(Color.ORANGE);
		JLabel pLabel = new JLabel("Players", JLabel.CENTER);
		JLabel cLabel = new JLabel("Cards", JLabel.CENTER);
		playersCardPanel.add(pLabel);
		playersCardPanel.add(cLabel);
		playersCardPanel.setMaximumSize(new Dimension(100, 100));
		cardPanel.add(playersCardPanel);
		
		Dimension cardDim = new Dimension(68, 95);
		
		for (ActionCard ac : human.getActionCards()) {
			
			((JButton)ac).setMaximumSize(cardDim);
//			((JButton)ac).setSize(cardDim);
//			((JButton)ac).setPreferredSize(cardDim);
//			((JButton)ac).setMinimumSize(cardDim);
			//ac.setMaximumSize(cardDim);
			
			ac.setText(ac.toString());
			ac.setFont(new Font("Default", Font.PLAIN, 10));
			cardPanel.add((JButton)ac);
			
//			aCard.setMaximumSize(cardDim);
//			aCard.setText(aCard.getName());
//			cardPanel.add(aCard);
//			
			cardPanel.add(Box.createRigidArea(new Dimension(20, 0))); // works
			
		}
		
		cardPanel.revalidate();
		cardPanel.repaint();
	}
	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
		// action listener for buttons
		private class OptionListener implements ActionListener
		{   
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (e.getSource() instanceof JButton) {
					System.out.println("---player age = " + human.getAge());
					System.out.println("player ac size = " + human.getActionCards().size());
					
					if (human.getActionCards().size() < human.getAge()) {
						// remove card from perm/rand cultures array list
						// add card to players action card array list
						
						permanentCards = human.getCulture().getPermanentCards();
						randomCards = human.getCulture().getRandomCards();
						
						
						if (((JButton)e.getSource()).getText().equals("random deck")) {
							System.out.println("Random Card");
							int index = new Random().nextInt(randomCards.size());
							
							//tPlayer.getActionCards().add(randomCards.get(index));						
							human.getActionCards().add(randomCards.remove(index));
						}
						else {
							System.out.println("Permanent Card");
							int index = buttonsList.indexOf(e.getSource());
							System.out.println("Index of card clicked: " + index);
							
							// not the best or when discarding just remove from 
							// players action array
							human.getActionCards().add(permanentCards.get(index));
							//tPlayer.getActionCards().add(permanentCards.remove(index));
							((JButton)e.getSource()).setEnabled(false);
						}
						
						//((JButton)e.getSource()).setEnabled(false);
						setupCardPanel();
						
						System.out.println("Player ActCard Size: " + human.getActionCards().size());
						System.out.println("PermCard Size: " + human.getCulture().getPermanentCards().size());
						System.out.println("RandCard Size: " + human.getCulture().getRandomCards().size());
					}
					
					if (human.getActionCards().size() >= human.getAge()) {
						setupCardSelection();
						// update action panel here with message and button
						// function call
					}
				}
				
			}	
		}

	
}
