import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
/*
 * StartScreen:
 * - is the first screen 
 * - saves the players name and culture choice
 */
/*
 * Add:
 * 	- 
 * FIX:
 * 	-
 */
@SuppressWarnings("serial")
public class StartScreen extends JPanel {
	// get the player list from game controller
	private ArrayList<Player> pList = Main.gc.getPlayersList();
	// var for each player
	//private Player human = pList.get(0);
	private Player human = Main.gc.getHuman();
	private Player comp1 = pList.get(1);
	private Player comp2 = pList.get(2);
	// main panel to hold labels and buttons
	private JPanel mainPanel = new JPanel();
	// text field and radio buttons for user input
	private JTextField nameTextField = new JTextField();		
	private JRadioButton greekButton = new JRadioButton("Greek");
	private JRadioButton norseButton = new JRadioButton("Norse");
	private JRadioButton egyptianButton = new JRadioButton("Egyptian");
	public JButton startButton = new JButton("Start Game");
	
	// constructor
	public StartScreen() {
		setLayout(null);
		setSize(new Dimension(1200, 800));
//		setBackground(Color.BLUE);
//		setBackground(new Color(0,0,210));
		setBackground(GameViewController.getGameColor("blue"));
		setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GREEN));

		add(setupHeaderLabel());	// add header
		setupMainPanel();			//
		add(mainPanel);
	}
	
	// SETUP // // // // // // // // // // // // // // // // // // // // // //
	// returns the header label 
	private JLabel setupHeaderLabel() {
		JLabel headerLabel = new JLabel("~ Age of Heroes ~", JLabel.CENTER);
		headerLabel.setBounds(0, 50, 1200, 100);
		headerLabel.setForeground(Color.GREEN);
//		headerLabel.setForeground(GameViewController.getGameColor("green"));
//		headerLabel.setForeground(new Color(50,205,50));
		headerLabel.setFont(GameViewController.getGameFontSize(45));
		return headerLabel;
	}
	
	// sets up the panel to hold the text field and radio buttons, start button
	private void setupMainPanel() {
		mainPanel.setLayout(new GridLayout(7,1));
		mainPanel.setSize(new Dimension(250, 400));
		
		int centerX = (this.getWidth() / 2 - mainPanel.getWidth() / 2);
		int centerY = (this.getHeight() / 2 - mainPanel.getHeight() / 2);
		mainPanel.setLocation(centerX, centerY);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
		
		JLabel nameLabel = new JLabel("Name:", JLabel.CENTER);
		nameLabel.setBackground(Color.GREEN);
		nameLabel.setFont(GameViewController.getGameFontSize(35));
		JLabel chooseCultureLabel = new JLabel("Choose Culture:", JLabel.CENTER);
		ButtonGroup buttonGroup = new ButtonGroup();	// holds 3 radio buttons
		buttonGroup.add(greekButton);
		buttonGroup.add(norseButton);
		buttonGroup.add(egyptianButton);
		// add action listener to each button
		greekButton.addActionListener(new OptionListener());
		norseButton.addActionListener(new OptionListener());
		egyptianButton.addActionListener(new OptionListener());
		startButton.addActionListener(new OptionListener());
		
		// put start button in a panel to resize start button
		JPanel startButtonPanel = new JPanel();
		startButton.setPreferredSize(new Dimension(200, 50));
		startButtonPanel.setOpaque(false);
		startButtonPanel.add(startButton);
		
		// add all components to main panel
		mainPanel.add(nameLabel);
		mainPanel.add(nameTextField);
		mainPanel.add(chooseCultureLabel);
		mainPanel.add(greekButton);
		mainPanel.add(norseButton);
		mainPanel.add(egyptianButton);
		mainPanel.add(startButtonPanel);
	}
	
	//
	public void setPlayersInfo() {
		if (nameTextField.getText().isEmpty()) {
			human.setName("Human");
		}
		else {
			human.setName(nameTextField.getText());
		}
		if (human.getCulture() == null) {
			human.setCulture("Greek");
			comp1.setCulture("Norse");
			comp2.setCulture("Egyptian");
		}
	}
	
	// assigns the unselected cultures to the computers
	private void assignComputerCulture() {
		if (greekButton.isSelected()) {
			comp1.setCulture("Norse");
			comp2.setCulture("Egyptian");
		}
		else if (norseButton.isSelected()) {
			comp1.setCulture("Greek");
			comp2.setCulture("Egyptian");
		}
		else if (egyptianButton.isSelected()) {
			comp1.setCulture("Greek");
			comp2.setCulture("Norse");
		}
	}
	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	//
	private class OptionListener implements ActionListener
	{   
		@Override
		public void actionPerformed(ActionEvent e) {
			//
			if (e.getSource() instanceof JRadioButton) {	
				if ( ((JRadioButton)e.getSource()).isSelected() ) {
					human.setName(nameTextField.getText());
					human.setCulture(((JRadioButton)e.getSource()).getText());
					assignComputerCulture();
				}
			}
		}	
	}
	
}
