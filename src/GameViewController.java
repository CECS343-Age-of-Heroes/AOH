/*
 * GameViewController:
 * - is the controller of all the views 
 * - it listens for events on each screen that will switch to the next view in
 *   the game
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/*
 * ADD:
 *	- put all views in an array then just go through the list to the next one?
 *	- use card layout ??
 * FIX:
 * 	- change button names to go to next screen
 * 	- is the extra 22 from 822 (height) because of the top of the JFrame ??
 */
		
@SuppressWarnings("serial")
public class GameViewController extends JFrame { 

	private StartScreen startScreen;
	private RollDiceScreen rollDiceScreen;
	private TileSelectionView tileSelectionView;
	private VictoryCubePlacementView victoryCubePlacementView;
	private GamePlayView gamePlayView;
	
	// constructor
	public GameViewController() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Game Frame");
        setLocation(250, 250);	// sets location of the program window
        
        setPreferredSize(new Dimension(1200, 822));  
		
        // initial screen for a new game
		startScreen = new StartScreen();
		startScreen.startButton.addActionListener(new GameOptionListener());
		
//		add(new GamePlayView());
		add(startScreen);
        pack();
        setVisible(true);
	}

	// game button listener to respond when a button is pressed on a different
	// view, removes current view and displays the next view
	public class GameOptionListener implements ActionListener
	{   
		@Override
		public void actionPerformed(ActionEvent e) {
			 
			if (e.getSource() instanceof JButton) {
				JButton button = (JButton)e.getSource();
				
				// start screen -> roll dice screen
				if (button.equals(startScreen.startButton)) {
					startScreen.saveName();   // save human name from TextField
					System.out.println("Go to roll dice screen");
					getContentPane().remove(startScreen);
					
					rollDiceScreen = new RollDiceScreen();
					rollDiceScreen.rollButton.
									addActionListener(new GameOptionListener());
	                getContentPane().add(rollDiceScreen);
	                invalidate();
	                validate();
				}	
				// roll dice screen -> tile selection screen
				else if (button.getText().equals("Done")) {
				//else if (b.equals(rollDiceScreen.rollButton ) && 
				//		 b.getText().equals("Done")) {
					System.out.println("Go to tile selection view");
					getContentPane().remove(rollDiceScreen);
					// need action listener here ??
					
					tileSelectionView = new TileSelectionView();
					tileSelectionView.nextButton.
									addActionListener(new GameOptionListener());
	                getContentPane().add(tileSelectionView);
	                invalidate();
	                validate();
				}
				// tile selection screen -> victory cube placement view
				else if (button.getText() == "Next") {
					System.out.println("Go to victory cube placement view");
					getContentPane().remove(tileSelectionView);
					
					victoryCubePlacementView = new VictoryCubePlacementView();
					victoryCubePlacementView.victoryDoneButton.
								addActionListener(new GameOptionListener());
	                // add game action listener here for next view (game play view?)
					getContentPane().add(victoryCubePlacementView);
	                invalidate();
	                validate();
				}	
				// victory cube placement view -> game play view
				else if (button.getText() == "Finish") {
				//else if (b.equals(victoryCubePlacementView.victoryDoneButton)) {
					System.out.println("Go to game play view");
					getContentPane().remove(victoryCubePlacementView);
					gamePlayView = new GamePlayView();
					
					getContentPane().add(gamePlayView);
	                invalidate();
	                validate();
				}	
				
			}
		}	
	}
	
}

