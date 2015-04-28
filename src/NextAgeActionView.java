import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class NextAgeActionView extends JPanel {
	
	private ActionView av = Main.gvc.getGamePlayView().getActionView();
	private int upgradeCost = 0;
	private Player currentPlayer;
		
	// constructor
	public NextAgeActionView(Player player, int cardValue) {
		upgradeCost = cardValue;
		currentPlayer = player;
		
		setLayout(null);
		setBounds(0, 150, 300, 600);
		setBackground(Color.YELLOW);
		
		int theCost = 0;
		JTextArea costLabel = new JTextArea();
		if (upgradeCost == 345) {
			theCost = currentPlayer.getAge() - 1;
		}
		else if (upgradeCost == 456) {
			theCost = currentPlayer.getAge();
		}
		
		Cubes cubeCost = new Cubes(theCost, theCost, theCost, theCost, 0);
		costLabel.setBounds(0, 0, 300, 200);
		costLabel.setText("Cost: \n" + cubeCost.toString());
		add(costLabel);
				
		JButton button = new JButton("Confirm");
		button.setBounds(50, 250, 200, 50);
		
		if (currentPlayer.hasEnoughResources(cubeCost)) {
			button.setText("Confirm");
			button.addActionListener(new NextAgeCardListener());
		}
		else {
			button.setText("Not Enough Resources");
		}
		add(button);
		
		System.out.println("Upgrade Cost = " + upgradeCost);
		System.out.println("Current Player = " + currentPlayer.getName());
		System.out.println("Current Player Age = " + currentPlayer.getAge());
		System.out.println("Cubes: " + currentPlayer.getCubes().toString());		
	}
	
	
	// OPTION LISTENER // // // // // // // // // // // // // // // // // // //
	private class NextAgeCardListener implements ActionListener {   
		@Override
		public void actionPerformed(ActionEvent e) {
			//
			if (e.getSource() instanceof JButton) {
				JButton button = (JButton)e.getSource();
				
				// confirm next age upgrade
				if (button.getText().equals("Confirm")) {
					
					Cubes pCubes = currentPlayer.getCubes();
					
					int cost = 0;
					if (upgradeCost == 345) {
						cost = currentPlayer.getAge() - 1;
					}
					else { //if (upgradeCost == 456) {
						cost = currentPlayer.getAge();
					}
					Cubes cubeCost = new Cubes(cost, cost, cost, cost, 0);
					
					//
					if (currentPlayer.hasEnoughResources(cubeCost)) {
						pCubes.removeCubesFromCubes(cubeCost);	
						Main.gc.getBank().getCubes().addCubesToCubes(cubeCost);
						currentPlayer.setAge(currentPlayer.getAge()+1);
						av.updateBoardAndBank();
						button.setEnabled(false);
					}
				}
			}
		}
	}
	
}