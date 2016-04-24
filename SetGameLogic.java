//import java.util.Scanner;
import java.util.ArrayList;

/**
 * SetGameLogic should contain everything for the set game in ASCII format
 * Login is not handled as it is done elsewhere
 * @author Darwin
 */

//RUN THIS!
public class SetGameLogic {
	public static void main(String[] args) {
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		Player p3 = new Player("Player 3");
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		Game g = new Game(players);
		g.play();
	}
	
	//there's some UI prototyping below lol. Not using it tho
	
	/*
	//The Main Menu of the Set Game
	public static void mainMenu(){
		int n = 6;
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		while(n != 5){
			System.out.println("MAIN MENU");
			System.out.println("Welcome to the game of Set");
			System.out.println("1: Play Game");
			System.out.println("2: Help/Instructions");
			System.out.println("3: Settings/Options");
			System.out.println("4: Credits");
			System.out.println("5. Exit");
			
			System.out.println("Enter a number: ");
			n = reader.nextInt();
			
			System.out.println(); //for spacing
			
			if(n == 1){
				play();
			}
			else if(n == 2){
				help();
			}
			else if(n == 3){
				settings();
			}
			else if(n == 4){
				credits();
			}
			else if(n == 5){
				//exit
				System.out.println("CLOSING SCREEN");
				System.out.println("Goodbye");
			}
			else{
				//do nothing in GUI
				//reprompt in ASCII
				System.out.println("Invalid input. Please enter 1, 2, 3, 4, or 5.");
			}
		}
		reader.close();
	}
	
	public static void play(){
		System.out.println("PLAY");
		Who
		
	}
	
	//This screen provides instructions on how to play as a giant block of text
	public static void help(){
		//boolean goBack = !true;
		//while(!goBack){
			System.out.println("HELP");
			System.out.println("this is the help text or help screen");
			//goBack = goBackPrompt();
		//}
	}
	
	//This screen allows one to modify various settings
	public static void settings(){
		//boolean goBack = !true;
		//while(!goBack){
			System.out.println("SETTINGS");
			System.out.println("this screen allows you to change settings...");
			System.out.println("like maybe music, screen size, resolution, or something?");
			System.out.println("no settings to change in ASCII mode tho I think");
			//goBack = goBackPrompt();
		//}
	}
	
	//This screen provides credits text
	public static void credits(){
		//boolean goBack = false;
		//while(!goBack){
			System.out.println("CREDITS");
			System.out.println("This game was made by Darwin, Eric, Hetian, and Sebastien");
			System.out.println("Thanks to Sable for teaching, etc, etc.");
			System.out.println("Proudly made with freedom in the United States of America");
			//goBack = goBackPrompt();
		//}
	}
	
	//This screen provides ins
	
	public static boolean goBackPrompt(){
		Scanner reader2 = new Scanner(System.in);  // Reading from System.in
		System.out.println("Press 0 to go back");
		int i = reader2.nextInt();
		reader2.close();
		if(i == 0){
			return true;
		}
		return false;
	}
	*/
}
