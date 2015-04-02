import java.util.*;
import javax.swing.*;
/* 
 * GameBoards:
 * - 
 */
/*
 * ADD:
 * 	-
 * FIX:
 * 	-
 */

@SuppressWarnings("serial")
public class GameBoards extends JPanel {
	
	//private ArrayList playerBoards = new ArrayList();
	private ArrayList<Player> pList = Main.gc.getPlayersList();

	// constructor
	public GameBoards() {
		setLayout(null);
		setSize(900, 600);
		// main view player board (bigger)
		JPanel mainBoardPanel = new PlayerBoard(pList.get(0));
		mainBoardPanel.setBounds(0, 0, 600, 600);
		
		// side view player boards (smaller)
		JPanel sideBoard1Panel = new PlayerBoard(pList.get(1));
		JPanel sideBoard2Panel = new PlayerBoard(pList.get(2));
		sideBoard1Panel.setBounds(600, 0, 300, 300);
		sideBoard2Panel.setBounds(600, 300, 300, 300);
		// add all components to view
		add(mainBoardPanel);
		add(sideBoard1Panel);
		add(sideBoard2Panel);
	}
		
}
