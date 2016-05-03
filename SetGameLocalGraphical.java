/**@author Eric
 * The main class that implements all of the necessary graphical
 * perspectives including the menu and main game table
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SetGameLocalGraphical {
	private static JButton button[] = new JButton[21];
	private static int numPressed;
	private static int numCards;
	private static ArrayList<Player> players;
	private static Table table;
	private static int[] playerScores; //represents current scores of players
	private static Card[] chosen; //represents chosen cards
	private static JFrame mainFrame;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		chosen = new Card[3];
		table = new Table();
		numCards = 12;
		players = new ArrayList<Player>();
		int numnames = 0;
		System.out.println("Welcome to the game of set. Enter a player name and press enter: ");
		players.add(new Player(scan.nextLine()));
		System.out.println("Enter a player name or type 'quit': ");
		String s = scan.nextLine();
		while(!s.equals("quit")){
			players.add(new Player(s));
			s = scan.nextLine();
		}
		playerScores = new int[players.size()]; //represents current scores of players
		for(int i : playerScores)
			i = 0;
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}


	public static void createAndShowGUI(){
		/*JFrame*/ mainFrame = new JFrame();
		JMenuBar menubar = new JMenuBar();
		JMenu menu = getMenu();
		menubar.add(menu);

		JPanel MainPanel = new JPanel();
		JPanel RightBar = getSidebar();
		JPanel ButtonPanel = getButtonPanel(numCards/3);
		MainPanel.add(ButtonPanel,0);
		MainPanel.add(RightBar,1);
		
		mainFrame.add(MainPanel,0);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setJMenuBar(menubar);
		mainFrame.setVisible(true);
		mainFrame.pack();
		//		frame.setBackground(new Color(154,165,127));

	}

	public static JMenu getMenu(){
		JMenu menu = new JMenu("Main Menu");
		JMenuItem newgame = new JMenuItem("Join Game");
		JMenuItem settings = new JMenuItem("Settings");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		JMenuItem credits = new JMenuItem("Credits");
		credits.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame = new JFrame();
				JLabel info = new JLabel();
				info.setText("<html>Created by:<br>Darwin Huang"
						+ "<br>Eric Mendoza-Conner<br>Sebastien Charles"
						+ "<br>Hetian Wu<br><br>Under Professor Carl Sable in  the Software"
						+ " Engineering course at the Cooper Union for the Advancement of "
						+ "Science and Art<br>May 2016</html>");
				info.setVisible(true);
				JPanel textPanel = new JPanel(new BorderLayout());
				textPanel.setPreferredSize(new Dimension(200,200));
				textPanel.add(info);
				frame.add(textPanel);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				frame.setPreferredSize(new Dimension(300,240));
				frame.pack();
				return;
			}
		});
		menu.add(newgame);
		menu.add(settings);
		menu.add(credits);
		menu.add(exit);
		return menu;
	}

	//Sets up the table Panel which contains all the cards as clickable
	// buttons.
	public static JPanel getButtonPanel(int cols){
		JPanel ButtonPanel = new JPanel(new GridLayout(3,7,20,20));
		ImageIcon [] cards = new ImageIcon[21];
		for(int i = 0; i<table.getSize(); i++){
			final int j = i;
			cards[j] = new ImageIcon(table.getCard(i).getImage());
			button[j] = new JButton(cards[j]);
			button[i].setBackground(new Color(29,77,22));
			button[i].setBorderPainted(false);
			button[j].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(numPressed<3){
						button[j].setBackground(new Color(72,191,48));
						chosen[numPressed] = table.getCard(j);
						numPressed++;
					}
				}
			});
			ButtonPanel.add(button[i],i);
		}
		ButtonPanel.setBackground(new Color(41,110,32));
		ButtonPanel.setPreferredSize(new Dimension(700+175,400));
		return ButtonPanel;
	}

	//Sets up a sidebar that lists players and their scores
	// as well as contains the SET and Clear buttons for the game
	public static JPanel getSidebar(){
//		BufferedImage pikachu = null;
//		try{
//			pikachu = ImageIO.read(new File("Pikachu2.png"));
//		} catch(Exception e){
//			System.err.println("No File found");
//		}
//		JLabel pika = new JLabel(new ImageIcon(pikachu));
		JPanel RightBar = new JPanel(new GridLayout(players.size()+3,1,10,10));
		JButton set   = new JButton("SET");
		set.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int num = getPlayerNumber();
				checkSet(num);
			}
		});
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearSelection();
			}
		});
		RightBar.add(clear,0);
		RightBar.add(set,1);

		//Edit for modified Player class later
		JLabel []playerlist = new JLabel[players.size()];
		for(int i = 0; i<players.size(); i++){
			playerlist[i] = new JLabel("Player: "+players.get(i).getName()+", "+playerScores[i]+"\n");
			playerlist[i].setFont(new Font("Arial",1,18));
			RightBar.add(playerlist[i],i+2);
		}
		RightBar.setBackground(new Color(232,232,232));
		return RightBar;
	}

	public static void clearSelection(){
		for(int i = 0; i<table.getCardCount(); i++){
			button[i].setBackground(new Color(29,77,22));
		}
		numPressed=0;		
	}

	public static void checkSet(int playernum){
		if(numPressed!=3){
			clearSelection();
			return;
		}
		clearSelection();
		if(!table.isSet(chosen[0], chosen[1], chosen[2])){
			playerScores[playernum]--;
			System.out.println("Player "+(playernum+1)+" lost a point! (Score = "+playerScores[playernum]+")");
			return;
		}
		else{
			playerScores[playernum]++;
			table.removeCard(chosen[0]);
			table.removeCard(chosen[1]);
			table.removeCard(chosen[2]);
			if(table.getCardCount() < 12){ //only draws more cards if there are fewer than 12 on the table
				table.addCard(3);
				numCards = table.getCardCount();
			}
			
			//add cards to table until set exists or no cards in deck
			while(!table.setsExist() && (table.getDeckCardCount() > 0)){
				System.out.print("No sets on the board! 3 added.\n");
				table.addCard(3);
				numCards+=3;
			}
			if(!table.setsExist() && table.getDeckCardCount()==0){
				int max = -10000;
				int win = 0;
				for(int i = 0; i<playerScores.length; i++)
					if(max<playerScores[i]){
						max = playerScores[i];
						win = i;
					}
				System.out.println("Player "+ (win+1)+" Wins! "+max+" points!\n");
				System.exit(0);
			}
			System.out.println("Player "+(playernum+1)+" got a point! (Score = "+playerScores[playernum]+")");
			System.out.println("There are "+table.getDeckCardCount()+" cards left.");
			JPanel ButtonPanel = getButtonPanel(numCards/3);
			JPanel Sidebar = getSidebar();
			JPanel MainPanel = new JPanel();
			MainPanel.add(ButtonPanel);
			MainPanel.add(Sidebar);
			mainFrame.setContentPane(MainPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			
		}
	}

	public static int getPlayerNumber(){
		JFrame newframe = new JFrame();
		newframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Object[] possibilities = new Object[players.size()];
		for(int i = 0; i<players.size(); i++)
			possibilities[i] = players.get(i).getName();
		String s = (String)JOptionPane.showInputDialog(newframe,
				"Who got the set?",
				"Customized Dialog",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				players.get(0).getName());
		newframe.pack();
		for(int i = 0; i<players.size(); i++)
			if(players.get(i).getName().equals(s))
				return i;
		return 0;
	}
	
	public static int random(){
		return (int)(Math.random()*3);
	}


}
