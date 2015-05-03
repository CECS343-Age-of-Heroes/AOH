import java.awt.*;
import java.util.*;

import javafx.geometry.Bounds;

import javax.swing.*;
/*
 * PlayerBoard:
 * -
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	-
 */
@SuppressWarnings("serial")
public class PlayerBoard extends JPanel {
	
	private String type = "";
	
	//public PlayerBoard(Player player) {
	public PlayerBoard(Player player, String aType) {
		type = aType;
		setLayout(new GridLayout(2,1));
		//this.setPreferredSize(new Dimension(250,250));
		//this.setSize(500, 500);
		
		JPanel holdingArea = null;
		JPanel bottomArea = new JPanel();
		bottomArea.setLayout(new GridLayout(1,2));
		// production tiles
		JPanel productionArea = productionArea = setupPlayerProductionArea(player);
		if (type.equals("main")) {
			holdingArea = setupHoldingArea(player);
		}
		else if (type.equalsIgnoreCase("side")) {
			holdingArea = setupSmallHoldingArea(player);
//			productionArea = setupSmallPlayerProductionArea(player);
		}
		
		// buildings
		JPanel cityArea = setupPlayerCityArea(player);
		// add components
		bottomArea.add(productionArea);
		bottomArea.add(cityArea);
		add(holdingArea);
		add(bottomArea);
	}
	
	//
	private JPanel setupHoldingArea(Player player) {
		JPanel holding = new JPanel();
//		holding.setLayout(new GridLayout(8,1));
		holding.setLayout(null);
//		holding.setBackground(Color.GREEN);
		holding.setBackground(new Color(224,224,224));
		holding.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		JPanel namePanel = new JPanel();
		namePanel.setOpaque(false);
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		namePanel.setBounds(10, 10, 290, 50);
		JLabel nameLabel = new JLabel(player.getName(), JLabel.LEFT);
		nameLabel.setFont(GameViewController.getGameFontSize(50));
		namePanel.add(nameLabel);
		holding.add(namePanel);
		
		JPanel agePanel = new JPanel();
		agePanel.setOpaque(false);
		agePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		agePanel.setBounds(10, 55, 290, 40);
		JLabel ageLabel = new JLabel(player.ageToString());
		ageLabel.setFont(GameViewController.getGameFontSize(30));
		agePanel.add(ageLabel);
		holding.add(agePanel);
		
		
		JPanel culturePanel = new JPanel();
		culturePanel.setOpaque(false);
		culturePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		culturePanel.setBounds(300, 10, 290, 50);
		JLabel cultureLabel = new JLabel(player.getCulture().getName());
		cultureLabel.setFont(GameViewController.getGameFontSize(40));
		culturePanel.add(cultureLabel);
		holding.add(culturePanel);
		
		
		JPanel placePanel = new JPanel();
		placePanel.setOpaque(false);
		placePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		placePanel.setBounds(300, 55, 290, 40);
		JLabel placeLabel = new JLabel("Player " + player.getPlace().toString());
		placeLabel.setFont(GameViewController.getGameFontSize(30));
		placePanel.add(placeLabel);
		holding.add(placePanel);
		
		
		
		JPanel cubesPanel = new JPanel();
		cubesPanel.setOpaque(false);
		cubesPanel.setLayout(new GridLayout(6,1));
		cubesPanel.setBounds(250, 100, 100, 150);
		
		
		JLabel cubesLabel = new JLabel("Cubes", JLabel.CENTER);
		cubesLabel.setFont(GameViewController.getGameFontSize(25));
		cubesPanel.add(cubesLabel);

		Cubes playerCubes = player.getCubes();
		
		for (int i = 0; i < playerCubes.size(); i++) {
			
			String cubeColor = "";
			switch (i) {
				case 0: cubeColor = "blue";
				break;
				case 1: cubeColor = "green";
				break;
				case 2: cubeColor = "brown";
				break;
				case 3: cubeColor = "yellow";
				break;
				case 4: cubeColor = "red";
				break;
			}
			JPanel cubePanel = new JPanel();
			cubePanel.setOpaque(false);
			JPanel cube = new JPanel();
			cube.setPreferredSize(new Dimension(15,15));
			cube.setBackground(Cubes.cubeColor(cubeColor));
//			cube.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
			cubePanel.add(cube);
			
			JLabel value = new JLabel(Integer.toString(playerCubes.getValueOfColor(cubeColor)));
			cubePanel.add(value);
			
			cubesPanel.add(cubePanel);
		}
		holding.add(cubesPanel);
		
		
		// production area label 
		JPanel prodAreaPanel = new JPanel();
		prodAreaPanel.setOpaque(false);
		prodAreaPanel.setBounds(0, 260, 300, 35);
		JLabel prodAreaLabel = new JLabel("Production Area");
		prodAreaLabel.setFont(GameViewController.getGameFontSize(30));
		prodAreaPanel.add(prodAreaLabel);
		holding.add(prodAreaPanel);
		
		// city area label
		JPanel cityAreaPanel = new JPanel();
		cityAreaPanel.setOpaque(false);
		cityAreaPanel.setBounds(300, 260, 300, 35);
		JLabel cityAreaLabel = new JLabel("City Area");
		cityAreaLabel.setFont(GameViewController.getGameFontSize(30));
		cityAreaPanel.add(cityAreaLabel);
		holding.add(cityAreaPanel);
		
		return holding;
	}
	
	
	private JPanel setupSmallHoldingArea(Player player) {
		JPanel holding = new JPanel();
		holding.setLayout(null);
//		holding.setBackground(new Color(224,224,224));
		holding.setBackground(GameViewController.getGameColor("green"));
		holding.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JPanel namePanel = new JPanel();
		namePanel.setOpaque(false);
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//namePanel.setBounds(10, 10, 290, 50);
		namePanel.setBounds(5, 5, 145, 30);
		JLabel nameLabel = new JLabel(player.getName(), JLabel.LEFT);
//		nameLabel.setFont(GameViewController.getGameFontSize(50));
		nameLabel.setFont(GameViewController.getGameFontSize(25));
		namePanel.add(nameLabel);
		holding.add(namePanel);
		
		JPanel agePanel = new JPanel();
		agePanel.setOpaque(false);
		agePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		agePanel.setBounds(10, 55, 290, 40);
		agePanel.setBounds(5, 30, 145, 20);
		JLabel ageLabel = new JLabel(player.ageToString());
//		ageLabel.setFont(GameViewController.getGameFontSize(30));
		ageLabel.setFont(GameViewController.getGameFontSize(15));
		agePanel.add(ageLabel);
		holding.add(agePanel);
		
		
		JPanel culturePanel = new JPanel();
		culturePanel.setOpaque(false);
		culturePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		culturePanel.setBounds(300, 10, 290, 50);
		culturePanel.setBounds(150, 5, 145, 30);
		JLabel cultureLabel = new JLabel(player.getCulture().getName());
//		cultureLabel.setFont(GameViewController.getGameFontSize(40));
		cultureLabel.setFont(GameViewController.getGameFontSize(20));
		culturePanel.add(cultureLabel);
		holding.add(culturePanel);
		
		
		JPanel placePanel = new JPanel();
		placePanel.setOpaque(false);
		placePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		placePanel.setBounds(300, 55, 290, 40);
		placePanel.setBounds(150, 30, 145, 20);
		JLabel placeLabel = new JLabel("Player " + player.getPlace().toString());
//		placeLabel.setFont(GameViewController.getGameFontSize(30));
		placeLabel.setFont(GameViewController.getGameFontSize(15));
		placePanel.add(placeLabel);
		holding.add(placePanel);
		
		
		
		JPanel cubesPanel = new JPanel();
		cubesPanel.setOpaque(false);
		cubesPanel.setLayout(new GridLayout(6,1));
//		cubesPanel.setLayout(new GridLayout(1,6));
//		cubesPanel.setBounds(250, 100, 100, 150);
		cubesPanel.setBounds(125, 40, 50, 90);
		
		
		JLabel cubesLabel = new JLabel("Cubes", JLabel.CENTER);
//		cubesLabel.setFont(GameViewController.getGameFontSize(25));
		cubesLabel.setFont(GameViewController.getGameFontSize(13));
		cubesPanel.add(cubesLabel);

		Cubes playerCubes = player.getCubes();
		
		for (int i = 0; i < playerCubes.size(); i++) {
			String cubeColor = "";
			switch (i) {
				case 0: cubeColor = "blue";
				break;
				case 1: cubeColor = "green";
				break;
				case 2: cubeColor = "brown";
				break;
				case 3: cubeColor = "yellow";
				break;
				case 4: cubeColor = "red";
				break;
			}
			JPanel cubePanel = new JPanel();
			cubePanel.setOpaque(false);
			JPanel cube = new JPanel();
//			cube.setPreferredSize(new Dimension(15,15));
			cube.setPreferredSize(new Dimension(6,6));
			cube.setBackground(Cubes.cubeColor(cubeColor));
//			cube.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
			cubePanel.add(cube);
			
			JLabel value = new JLabel(Integer.toString(playerCubes.getValueOfColor(cubeColor)));
			value.setFont(GameViewController.getGameFontSize(13));
			cubePanel.add(value);
			
			cubesPanel.add(cubePanel);
		}
		holding.add(cubesPanel);
		
		
		// production area label 
		JPanel prodAreaPanel = new JPanel();
		prodAreaPanel.setOpaque(false);
//		prodAreaPanel.setBounds(0, 265, 300, 35);
		prodAreaPanel.setBounds(0, 125, 150, 20);
		JLabel prodAreaLabel = new JLabel("Production Area");
//		prodAreaLabel.setFont(GameViewController.getGameFontSize(30));
		prodAreaLabel.setFont(GameViewController.getGameFontSize(15));
		prodAreaPanel.add(prodAreaLabel);
		holding.add(prodAreaPanel);
		
		// city area label
		JPanel cityAreaPanel = new JPanel();
		cityAreaPanel.setOpaque(false);
//		cityAreaPanel.setBounds(300, 265, 300, 35);
		cityAreaPanel.setBounds(150, 125, 150, 20);
		JLabel cityAreaLabel = new JLabel("City Area");
//		cityAreaLabel.setFont(GameViewController.getGameFontSize(30));
		cityAreaLabel.setFont(GameViewController.getGameFontSize(15));
		cityAreaPanel.add(cityAreaLabel);
		holding.add(cityAreaPanel);
		
		return holding;
	}
	
	
	
	//
//	private JPanel setupPlayerCube(Player player, String color) {
//		Cubes playerCubes = player.getCubes();
//		
//		JPanel cubePanel = new JPanel();
//		
//		JPanel cube = new JPanel();
//		cube.setPreferredSize(new Dimension(15,15));
//		cube.setBackground(cubeColor("blue"));
//		cube.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
//		cubePanel.add(cube);
//		
//		
//		JLabel value = new JLabel(Integer.toString(playerCubes.get(i)));
////		String num = Integer.toString(playerCubes.indexOf(cubeColor));
////		String num = Integer.toString(playerCubes.);
//		
////		JLabel value = new JLabel(num);
////		cubePanel.add(value);
//		
//		cubesPanel.add(cubePanel);
//		
//
//		for (int i = 0; i < playerCubes.size(); i++) {
//			JPanel cubePanel = new JPanel();
//			
//			JPanel cube = new JPanel();
//			cube.setPreferredSize(new Dimension(15,15));
//			String cubeColor = "";
//			switch (i) {
//				case 0: cubeColor = "blue";
//				break;
//				case 1: cubeColor = "green";
//				break;
//				case 2: cubeColor = "brown";
//				break;
//				case 3: cubeColor = "yellow";
//				break;
//				case 4: cubeColor = "red";
//				break;
//			}
//			
//			cube.setBackground(cubeColor(color));
//			cube.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
//			cubePanel.add(cube);
//			
//			JLabel value = new JLabel(Integer.toString(playerCubes));
////			String num = Integer.toString(playerCubes.indexOf(cubeColor));
////			String num = Integer.toString(playerCubes.);
//			
////			JLabel value = new JLabel(num);
////			cubePanel.add(value);
//			
//			cubesPanel.add(cubePanel);
//		}
//		
//		return cubePanel;
//	}
	
	//
	private JPanel setupPlayerCityArea(Player player) {
		ArrayList<BuildingTile> buildingTiles = player.getBuildingTiles();
		
		JPanel cityPanel = new JPanel();
		cityPanel.setLayout(new GridLayout(4,4));
		cityPanel.setBounds(134, 0, 267, 267);
		cityPanel.setBackground(Color.GRAY);
		cityPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		// 
		int numSquares = 16;
		for (int i = 0; i < buildingTiles.size(); i++) {
			// text for button in each square
			JButton button = new JButton();
			button.setLayout(new BorderLayout());
			
			JLabel nameLabel = null; // = new JLabel(buildingTiles.get(i).getShortBuildingName(), JLabel.CENTER);
			if (type.equals("main")) {// the big one
				nameLabel = new JLabel(buildingTiles.get(i).getName(), JLabel.CENTER);
				nameLabel.setFont(new Font("Default", Font.PLAIN, 12));
			}
			else {
				nameLabel = new JLabel(buildingTiles.get(i).getShortBuildingName(), JLabel.CENTER);
				nameLabel.setFont(new Font("Default", Font.PLAIN, 7));
			}
			
			button.add(nameLabel, BorderLayout.CENTER);
			cityPanel.add(button);
		}
		
		// fill out the rest
		for (int i = 0; i < numSquares - buildingTiles.size(); i++) {
			JButton eb = new JButton();
			eb.setEnabled(false);
			cityPanel.add(eb);
		}
		
		return cityPanel;
	}
	
	
	// setup the production area
	private JPanel setupPlayerProductionArea(Player player) {
			
		ArrayList<String> productionList = player.getCulture().getProductionAreaList();
		ArrayList<ProductionTile> productionTileList = player.getProductionTiles();
				
		JPanel productionPanel = new JPanel();
		productionPanel.setLayout(new GridLayout(4,4));
		productionPanel.setBounds(134, 0, 267, 267);
		productionPanel.setBackground(new Color(0,100,0));
		productionPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		// 
		for (int i = 0; i < productionTileList.size(); i++) {
			
			//
			int bWth = 59;
			int bHt = 62;
			int cubeSize = 10;
			Font labelFont;
			if (type.equals("main")) {// the big one
				labelFont = new Font("Default", Font.PLAIN, 9);
				
			}
			else {
//				cubeSize /= 2;
				cubeSize = 6;
				bWth = 59/3;
				bHt = 62/3;
				labelFont = new Font("Default", Font.PLAIN, 6);
			}
			
			//JComponent tp;
			JButton tp;
			if (productionTileList.get(i).getType() == "") {		// no tile
				tp = new JButton();
				tp.setLayout(new BorderLayout());
				tp.setEnabled(false);
				
				JLabel nameLabel = new JLabel(productionList.get(i).toUpperCase(), JLabel.CENTER);
				nameLabel.setFont(labelFont);
				tp.add(nameLabel, BorderLayout.CENTER);
			}
			//else if (tileList.get(i).getType() == list.get(i)) {
			else { 										// tile exist here
				tp = new JButton();
				tp.setLayout(new BorderLayout());
				
				JLabel nameLabel = new JLabel(productionTileList.get(i).getType().toUpperCase(), JLabel.CENTER);
				nameLabel.setFont(labelFont);
				
				JPanel cubesPanel = new JPanel();
				for (int j = 0; j < productionTileList.get(i).getValue(); j++) {
					JPanel cubePanel = new JPanel();
					cubePanel.setPreferredSize(new Dimension(cubeSize,cubeSize));
					cubePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
					
					//cubePanel.setBackground(cubeColor(productionTileList.get(i).getColor()));
					cubePanel.setBackground(Cubes.cubeColor(productionTileList.get(i).getColor()));
					cubesPanel.add(cubePanel, BorderLayout.CENTER);
				}
			
				if (type.equals("main")) {// the big one
					nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

				}
				else {
					nameLabel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

				}
								
				tp.add(nameLabel, BorderLayout.NORTH);
				tp.add(cubesPanel, BorderLayout.CENTER);
			}
			
			productionPanel.add(tp);
		}
		return productionPanel;
	}
	
	//
//	private Color cubeColor(String color) {
//		if (color.equals("blue")) {
//			return Color.BLUE;
//		}
//		else if (color.equals("green")) {
//			return Color.GREEN;
//		}
//		else if (color.equals("brown")) {
//			return new Color(160,82,45);
//		}
//		else if (color.equals("yellow")) {
//			return Color.YELLOW;
//		}
//		else if (color.equals("red")) {
//			return Color.RED;
//		}
//		return Color.BLACK;
//	}
	
}
