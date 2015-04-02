import java.awt.*;
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

	Bank bank = Main.gc.getBank();			// reference to games bank

	JPanel gameBoardsPanel = new GameBoards();	// change back after testing
	//JPanel gameBoardsPanel = new JPanel();
	JPanel bankPanel = new JPanel();
	JPanel cardPanel = new JPanel();
	JPanel actionPanel = new JPanel();
	
	
	public GamePlayView() {
		setLayout(null);
		//setPreferredSize(new Dimension(1200, 822));
		setBackground(Color.ORANGE);
        
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
		actionPanel.setPreferredSize(new Dimension(300, 800));
		actionPanel.setBackground(Color.blue);
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
		JButton tiles = new JButton("Game Tiles #"+ Main.gc.getGameTiles().size());
		tiles.setPreferredSize(new Dimension(95, 95));
		tiles.setFont(new Font("Default", Font.PLAIN, 10));
		tiles.setEnabled(false);
		bankPanel.add(tiles);
	}
	
	// set up the card panel
	private void setupCardPanel() {
		cardPanel.setPreferredSize(new Dimension(900, 100));
		cardPanel.setBackground(Color.ORANGE);
		//cardPanel.setLayout(new GridLayout(1,4));
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.LINE_AXIS));
		
//		cardPanel.add(Box.createHorizontalGlue());
//		cardPanel.add(aCard);
//		cardPanel.add(Box.createRigidArea(new Dimension(10, 0)));
//		cardPanel.add(setButton);
		
		//cardPanel.add(Box.createVerticalGlue());
		
		JPanel playersCardPanel = new JPanel();
		playersCardPanel.setLayout(new GridLayout(2,1));
		playersCardPanel.setBackground(Color.ORANGE);
		JLabel pLabel = new JLabel("Players", JLabel.CENTER);
		JLabel cLabel = new JLabel("Cards", JLabel.CENTER);
		playersCardPanel.add(pLabel);
		playersCardPanel.add(cLabel);
		playersCardPanel.setMaximumSize(new Dimension(100, 100));
		cardPanel.add(playersCardPanel);
		
		Dimension cardDim = new Dimension(68, 95);
		
		JButton aCard = new Card("Action");
		//aCard.setSize(57, 80);
		//aCard.setPreferredSize(new Dimension(57, 80));
		//aCard.setMinimumSize(new Dimension(57, 80));
		aCard.setMaximumSize(cardDim);
		aCard.setText(aCard.getName());
		//aCard.setBounds(0, 0, 57, 80);
		cardPanel.add(aCard);
		
		cardPanel.add(Box.createRigidArea(new Dimension(20, 0))); // works
		
		JButton bCard = new Card("Action");
		bCard.setText(bCard.getName());
		bCard.setMaximumSize(cardDim);
		cardPanel.add(bCard);
		
		cardPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		JButton cCard = new Card("Action");
		cCard.setText(cCard.getName());
		cCard.setMaximumSize(cardDim);
		cardPanel.add(cCard);
		
		cardPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		JButton dCard = new Card("Random");
		dCard.setText(dCard.getName());
		dCard.setMaximumSize(cardDim);
		cardPanel.add(dCard);
		
		cardPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		JButton eCard = new Card("Random");
		eCard.setText(eCard.getName());
		eCard.setMaximumSize(cardDim);
		cardPanel.add(eCard);
		
	}
	
}
