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

//	// constructor
	public GameBoards() {
	setLayout(null);
	setSize(900, 600);
	// main view player board (bigger)
//	JPanel mainBoardPanel = new PlayerBoard(pList.get(0), "main");
	JPanel mainBoardPanel = new PlayerBoard(Main.gc.getHuman(), "main");
	mainBoardPanel.setBounds(0, 0, 600, 600);
	
	// side view player boards (smaller)
//	JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
//	JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
	JPanel sideBoard1Panel = new PlayerBoard(Main.gc.getComputer1(), "side");
	JPanel sideBoard2Panel = new PlayerBoard(Main.gc.getComputer2(), "side");
	sideBoard1Panel.setBounds(600, 0, 300, 300);
	sideBoard2Panel.setBounds(600, 300, 300, 300);
	// add all components to view
	add(mainBoardPanel);
	add(sideBoard1Panel);
	add(sideBoard2Panel);
}
	
public void updateGameBoard(ArrayList<Player> list) {
	removeAll();

	JPanel mainBoardPanel = new PlayerBoard(Main.gc.getHuman(), "main");
	mainBoardPanel.setBounds(0, 0, 600, 600);
	
	// side view player boards (smaller)
//		JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
//		JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
	JPanel sideBoard1Panel = new PlayerBoard(Main.gc.getComputer1(), "side");
	JPanel sideBoard2Panel = new PlayerBoard(Main.gc.getComputer2(), "side");
	sideBoard1Panel.setBounds(600, 0, 300, 300);
	sideBoard2Panel.setBounds(600, 300, 300, 300);
	// add all components to view
	add(mainBoardPanel);
	add(sideBoard1Panel);
	add(sideBoard2Panel);
	revalidate();
	repaint();
}
	
public void showSmallTopBoard() {
	removeAll();
	JPanel mainBoardPanel = new PlayerBoard(Main.gc.getComputer1(), "main");
	mainBoardPanel.setBounds(0, 0, 600, 600);
	
	// side view player boards (smaller)
//		JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
//		JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
	JPanel sideBoard1Panel = new PlayerBoard(Main.gc.getHuman(), "side");
	JPanel sideBoard2Panel = new PlayerBoard(Main.gc.getComputer2(), "side");
	sideBoard1Panel.setBounds(600, 0, 300, 300);
	sideBoard2Panel.setBounds(600, 300, 300, 300);
	// add all components to view
	add(mainBoardPanel);
	add(sideBoard1Panel);
	add(sideBoard2Panel);
	revalidate();
	repaint();
}

public void showSmallBottomBoard() {
	removeAll();
	JPanel mainBoardPanel = new PlayerBoard(Main.gc.getComputer2(), "main");
	mainBoardPanel.setBounds(0, 0, 600, 600);
	
	// side view player boards (smaller)
//		JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
//		JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
	JPanel sideBoard1Panel = new PlayerBoard(Main.gc.getComputer1(), "side");
	JPanel sideBoard2Panel = new PlayerBoard(Main.gc.getHuman(), "side");
	sideBoard1Panel.setBounds(600, 0, 300, 300);
	sideBoard2Panel.setBounds(600, 300, 300, 300);
	// add all components to view
	add(mainBoardPanel);
	add(sideBoard1Panel);
	add(sideBoard2Panel);
	revalidate();
	repaint();
}
	
	
	// constructor
//		public GameBoards() {
//			setLayout(null);
//			setSize(900, 600);
//			// main view player board (bigger)
//			JPanel mainBoardPanel = new PlayerBoard(pList.get(0), "main");
//			mainBoardPanel.setBounds(0, 0, 600, 600);
//			
//			// side view player boards (smaller)
//			JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
//			JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
//			sideBoard1Panel.setBounds(600, 0, 300, 300);
//			sideBoard2Panel.setBounds(600, 300, 300, 300);
//			// add all components to view
//			add(mainBoardPanel);
//			add(sideBoard1Panel);
//			add(sideBoard2Panel);
//		}

	
//	public void updateGameBoard(ArrayList<Player> list) {
//		removeAll();
////		JPanel mainBoardPanel = new PlayerBoard(pList.get(0), "main");
//		JPanel mainBoardPanel = new PlayerBoard(list.get(0), "main");
//		mainBoardPanel.setBounds(0, 0, 600, 600);
//		
//		// side view player boards (smaller)
////		JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
////		JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
//		JPanel sideBoard1Panel = new PlayerBoard(list.get(1), "side");
//		JPanel sideBoard2Panel = new PlayerBoard(list.get(2), "side");
//		sideBoard1Panel.setBounds(600, 0, 300, 300);
//		sideBoard2Panel.setBounds(600, 300, 300, 300);
//		// add all components to view
//		add(mainBoardPanel);
//		add(sideBoard1Panel);
//		add(sideBoard2Panel);
//		revalidate();
//		repaint();
//	}
//	
//	public void showSmallTopBoard(ArrayList<Player> list) {
//		removeAll();
//		JPanel mainBoardPanel = new PlayerBoard(list.get(1), "main");
//		mainBoardPanel.setBounds(0, 0, 600, 600);
//		
//		// side view player boards (smaller)
////		JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
////		JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
//		JPanel sideBoard1Panel = new PlayerBoard(list.get(0), "side");
//		JPanel sideBoard2Panel = new PlayerBoard(list.get(2), "side");
//		sideBoard1Panel.setBounds(600, 0, 300, 300);
//		sideBoard2Panel.setBounds(600, 300, 300, 300);
//		// add all components to view
//		add(mainBoardPanel);
//		add(sideBoard1Panel);
//		add(sideBoard2Panel);
//		revalidate();
//		repaint();
//	}
//	
//	public void showSmallBottomBoard(ArrayList<Player> list) {
//		removeAll();
//		JPanel mainBoardPanel = new PlayerBoard(list.get(2), "main");
//		mainBoardPanel.setBounds(0, 0, 600, 600);
//		
//		// side view player boards (smaller)
////		JPanel sideBoard1Panel = new PlayerBoard(pList.get(1), "side");
////		JPanel sideBoard2Panel = new PlayerBoard(pList.get(2), "side");
//		JPanel sideBoard1Panel = new PlayerBoard(list.get(1), "side");
//		JPanel sideBoard2Panel = new PlayerBoard(list.get(0), "side");
//		sideBoard1Panel.setBounds(600, 0, 300, 300);
//		sideBoard2Panel.setBounds(600, 300, 300, 300);
//		// add all components to view
//		add(mainBoardPanel);
//		add(sideBoard1Panel);
//		add(sideBoard2Panel);
//		revalidate();
//		repaint();
//	}
	
		
}
