//import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SetGameLogic should contain everything for the set game in ASCII format
 * Login is not handled as it is done elsewhere
 * @author Darwin
 */

//RUN THIS!
public class GameRoom {
	public static void main(String[] args) {
		Scanner gameRoomReader = new Scanner(System.in);
		
		//TODO: Have a GUI for the Set game room to join a room, with just whether they wanna join
		//and maybe a countdown timer or sth before you get jacked into a game
		System.out.println("Would you like to join a Set game? (Yes/No)");
		System.out.println("(can't actually say no here tho cuz this isn't the GUI lol)");
		System.out.println("...");
		System.out.println("Waiting for other players to join");
		System.out.println("...");
		
		System.out.println("Game is now beginning...");
		
		//TODO: Server will be able to detect which clients chose to join the game, and will import
		//their information from the server
		//In this fun fun example, 3 players have decided to join.
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		Player p3 = new Player("Player 3");
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		Game g = new Game(players);
		g.play();
		System.out.println("Thank you for playing. The game room will now terminate.");
		gameRoomReader.close();
	}
}
