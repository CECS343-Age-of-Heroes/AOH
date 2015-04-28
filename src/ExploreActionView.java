import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ExploreActionView extends JPanel {
	
	private ActionView av = Main.gvc.getGamePlayView().getActionView();
	private Player currentPlayer;
	private int numberOfTiles = 0;	// number on the action card
	private JLabel playerLabel = new JLabel();
	private ArrayList<ProductionTile> tilesToPick = new ArrayList<>();
	private ArrayList<JButton> tileButtonsToPick = new ArrayList<>();
	private int count = 0;

	public ExploreActionView(Player player, int cardValue) {
		numberOfTiles = cardValue;
		currentPlayer = player;
		for (int i = 0; i < cardValue; i++) {
			ProductionTile t = Main.gc.getGameProductionTiles().remove(0);
			tilesToPick.add(t);
		}
		
		setLayout(null);
		setBounds(0, 150, 300, 600);
		setBackground(Color.YELLOW);
		
		playerLabel.setText(currentPlayer.getName() + " Select Your Tile:");
		playerLabel.setFont(new Font("Default", Font.PLAIN, 15));
		playerLabel.setBounds(0, 0, 300, 50);
		add(playerLabel);
		
		JPanel tilePanel = new JPanel();
		tilePanel.setOpaque(false);
		int rows = (numberOfTiles == 3 ? 2 : 3); 
		tilePanel.setLayout(new GridLayout(rows, 2));
		int tph = (numberOfTiles == 3 ? 300 : 450);
		tilePanel.setBounds(0, 50, 300, tph);
		
		for (ProductionTile tile : tilesToPick) {
			JButton tileButton = new JButton();
			tileButton.setBackground(Color.GREEN);
			tileButton.addActionListener(new ExploreCardListener());
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
		passButton.addActionListener(new ExploreCardListener());
		tileButtonsToPick.add(passButton);
		tilePanel.add(passButton);
		
		add(tilePanel);
	}
	
	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	private class ExploreCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			//
			if (e.getSource() instanceof JButton) {
				
				JButton button = (JButton)e.getSource();
				
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
//					System.out.println("button index = " + buttonIndex);
//					System.out.println("clicked tile: " + clicked.toString());
					
					GameProductionTiles gpt = new GameProductionTiles();
					if (gpt.checkIfValidTileSelection(currentPlayer, clicked)) {
						
						int j = list.indexOf(clicked.getType());
						tilesList.remove(j);
						tilesList.add(j, clicked);
						list.set(j, "FILLED");
				
						button.setEnabled(false);		
						count++;
						av.updateBoardAndBank();
						// rotate to next player
						currentPlayer = Main.gc.getNextPlayer(currentPlayer);
					}
				}
				
				// update players turn label
				playerLabel.setText(currentPlayer.getName() + " Select Your Tile:");
				
				// 
				if (count >= 3) {
					removeAll();
					revalidate();
					repaint();
				}
			}
		}
	}

}
