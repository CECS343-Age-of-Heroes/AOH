import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class TradeActionView extends JPanel {

	private ActionView av = Main.gvc.getGamePlayView().getActionView();
	private Player currentPlayer;
	private int tradeCost = 0;
//	private JButton doneButton = new JButton();
	private ArrayList<JTextField> sendTFs = new ArrayList<>();
	private ArrayList<JTextField> recieveTFs = new ArrayList<>();
	private String radioButtonText = "";
	
	// constructor
	public TradeActionView(Player player, int cardValue) {
		currentPlayer = player;
		tradeCost = cardValue;
		
		//setLayout(null);
		setLayout(new FlowLayout());
		setBounds(0, 150, 300, 600);
//		setSize(new Dimension(300,600));
		setBackground(Color.GREEN);
		
		
//		if (currentPlayer.getCubes().getBlue() >= 8) {
		if (currentPlayer.getCubes().getBlue() >= 2) {
			JLabel victoryLabel = new JLabel("Trade 8 Blue Cubes for 1 Red:");
			add(victoryLabel);
			JPanel rbPanel = new JPanel();
			rbPanel.setOpaque(false);
			//rbPanel.setPreferredSize(new Dimension(300,30));
			JRadioButton yesRB = new JRadioButton("Yes");
//			JRadioButton noRB = new JRadioButton("No");
			yesRB.addActionListener(new TradeCardListener());
//			noRB.addActionListener(new TradeCardListener());
//			ButtonGroup choices = new ButtonGroup();
//			choices.add(yesRB);
//			choices.add(noRB);
			rbPanel.add(yesRB);
//			rbPanel.add(noRB);
			add(rbPanel);
		}
		
		
		JLabel costLabel = new JLabel("Select Resource to Pay with:");
//		costLabel.setBounds(0, 180, 300, 20);
//		costLabel.setPreferredSize(new Dimension(300,20));
//		costLabel.setSize(new Dimension(300,20));
		add(costLabel);
		
		
		String[] colors = new String[]{"blue", "green", "brown", "yellow"};
		
		for (int j = 0; j < cardValue; j++) {
			JPanel radioPanel = new JPanel();
			radioPanel.setOpaque(false);
			//radioPanel.setBounds(0, 200, 300, 50);
//			radioPanel.setPreferredSize(new Dimension(300,50));
//			radioPanel.setSize(new Dimension(300,50));
			radioPanel.setLayout(new GridLayout(1,4));
			//radioPanel.setBackground(Color.GREEN);
			ButtonGroup bg = new ButtonGroup();
			for (int i = 0; i < colors.length; i++) {
				JRadioButton rb = new JRadioButton(colors[i]);
				rb.addActionListener(new TradeCardListener());
				bg.add(rb);
				radioPanel.add(rb);
			}
			add(radioPanel);
		}
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,3));
		mainPanel.setPreferredSize(new Dimension(300,200));
//		mainPanel.setBounds(0, 250, 300, 200);
		mainPanel.setBackground(Color.GREEN);
		
		JPanel colorsPanel = new JPanel();
		colorsPanel.setLayout(new GridLayout(5,1));
		colorsPanel.add(new JLabel("Resource:", JLabel.CENTER));
		colorsPanel.setOpaque(false);
		for (int i = 0; i < colors.length; i++) {
			colorsPanel.add(new JLabel(colors[i] + ":", JLabel.CENTER));
//			colorsPanel.add(new JLabel(colors[i] + ":", JLabel.RIGHT));
		}
		mainPanel.add(colorsPanel);
		
		JPanel sendPanel = new JPanel();
		sendPanel.setLayout(new GridLayout(5,1));
		sendPanel.add(new JLabel("Send:", JLabel.CENTER));
		sendPanel.setOpaque(false);
		for (int i = 0; i < 4; i++) {
			JTextField stf = new JTextField();
			stf.setText("0");
			sendTFs.add(stf);
			sendPanel.add(stf);
		}
		mainPanel.add(sendPanel);
		
		JPanel recievePanel = new JPanel();
		recievePanel.setLayout(new GridLayout(5,1));
		recievePanel.setOpaque(false);
		recievePanel.add(new JLabel("Recieve:", JLabel.CENTER));
		for (int i = 0; i < 4; i++) {
			JTextField rtf = new JTextField();
			rtf.setText("0");
			recieveTFs.add(rtf);
			recievePanel.add(rtf);
		}
		mainPanel.add(recievePanel);
		add(mainPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(300,70));
		
		JButton button = new JButton("Trade");
		//button.setBounds(50, 450, 200, 50);
		button.setBounds(50, 10, 200, 50);
		button.addActionListener(new TradeCardListener());
		buttonPanel.add(button);
		add(buttonPanel);
//		add(button);
		
	}

	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	private class TradeCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() instanceof JButton) {
				
				JButton button = (JButton)e.getSource();
				
				//
				if (button.getText().equals("Trade")) {
					// cost to trade
					if (radioButtonText != "") {
						// need to change to cost on the card to do a trade, can be 2,1,0
						currentPlayer.getCubes().addToColor(radioButtonText, -tradeCost);
						Main.gc.getBank().getCubes().addToColor(radioButtonText, tradeCost);
							
						int toSendCount = 0;
						for (JTextField tf : sendTFs) {
							toSendCount += Integer.valueOf(tf.getText()); 
						}
						
						int toRecieveCount = 0;
						for (JTextField tf : recieveTFs) {
							toRecieveCount += Integer.valueOf(tf.getText());
						}
						
						if (toSendCount == toRecieveCount) {	
							Cubes sendCubes = new Cubes(
								Integer.valueOf(sendTFs.get(0).getText()),
								Integer.valueOf(sendTFs.get(1).getText()),
								Integer.valueOf(sendTFs.get(2).getText()),
								Integer.valueOf(sendTFs.get(3).getText()), 0);
						
							Cubes recieveCubes = new Cubes(
								Integer.valueOf(recieveTFs.get(0).getText()),
								Integer.valueOf(recieveTFs.get(1).getText()),
								Integer.valueOf(recieveTFs.get(2).getText()),
								Integer.valueOf(recieveTFs.get(3).getText()), 0);
						
							currentPlayer.getCubes().removeCubesFromCubes(sendCubes);
							Main.gc.getBank().getCubes().addCubesToCubes(sendCubes);
							currentPlayer.getCubes().addCubesToCubes(recieveCubes);
							Main.gc.getBank().getCubes().removeCubesFromCubes(recieveCubes);
							
//							doneButton.setText("Done");
							av.updateBoardAndBank();
							button.setEnabled(false);
						}
						else {
							// number to send and recieve do not match
							// set a button or label to tell user to fix numbers
						}
					}
				}
//				else if (button.getText().equals("Done")) {
//					removeAll();
//					revalidate();
//					repaint();
//					// need to rotate to next player after done
//				}	
			}
			//
			else if (e.getSource() instanceof JRadioButton) {
				if ( ((JRadioButton)e.getSource()).isSelected() ) {
					
					if (((JRadioButton)e.getSource()).getText().equals("Yes")) {
						// take 8 blue for 1 red
					}
					else if (((JRadioButton)e.getSource()).getText().equals("No")) {
						
					}
					else {
						radioButtonText = ((JRadioButton)e.getSource()).getText();
					}
				}
			}			
		}
	}
	
}
