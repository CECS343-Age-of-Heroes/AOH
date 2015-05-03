import java.awt.*;

import javax.swing.*;
/*
 * VictoryCard
 * - represents a victory card in the game, 
 *   name of card, number of victory cubes on that card
 * - need to set the bounds of the card in the class used 
 */
/*
 * ADD:
 * 	-
 * FIX:
 *	-
 */
@SuppressWarnings("serial")
public class VictoryCard extends Card {
	
	private Integer cubeCount = 0;
	private JLabel valueLabel;
	private JLabel nameLabel; 
	private JPanel cube = new JPanel();
	
	// constructor
	public VictoryCard(String name) {
		setLayout(new BorderLayout());
		setName(name);
				
		nameLabel = new JLabel(getName(), JLabel.CENTER);
		nameLabel.setFont(GameViewController.getGameFontSize(30));
		nameLabel.setForeground(Color.BLUE);
		nameLabel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));		
		
		JPanel cubesPanel = new JPanel();
		cubesPanel.setOpaque(false);
//		cubesPanel.setLayout(new BorderLayout());
		
		valueLabel = new JLabel(" x " + String.valueOf(getCubeCount()), JLabel.CENTER);
		valueLabel.setFont(GameViewController.getGameFontSize(24));
		valueLabel.setForeground(Color.BLUE);
			
		cube.setPreferredSize(new Dimension(18,18));
		cube.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		cube.setBackground(Color.RED);
	
		cubesPanel.add(cube, BorderLayout.CENTER);
		cubesPanel.add(valueLabel, BorderLayout.CENTER);
		
		add(nameLabel, BorderLayout.NORTH);
		add(cubesPanel, BorderLayout.CENTER);
	}
	
	// 
	public void updateBankVC() {
		nameLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		nameLabel.setForeground(Color.BLACK);
		valueLabel.setForeground(Color.BLACK);
		nameLabel.setFont(GameViewController.getGameFontSize(10));
		valueLabel.setFont(GameViewController.getGameFontSize(14));
//		valueLabel.setFont(new Font("Default", Font.PLAIN, 8));
		cube.setPreferredSize(new Dimension(9,9));
	}
	
	public void updateViewVC() {
		nameLabel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
		nameLabel.setFont(GameViewController.getGameFontSize(30));
		valueLabel.setFont(GameViewController.getGameFontSize(24));
		cube.setPreferredSize(new Dimension(18,18));
	}
		
	// return cube count
	public Integer getCubeCount() {
		return cubeCount;
	}

	// set cube count
	public void setCubeCount(Integer newCubeCount) {
		this.cubeCount = newCubeCount;
	}
	
	// update value label
	public void updateValueLabel() {
		valueLabel.setText(" x " + String.valueOf(getCubeCount()));
	}
	
}
