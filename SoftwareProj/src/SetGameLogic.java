import java.util.ArrayList;

public class SetGameLogic {
	public static void main(String[] args) {
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		
		Game g = new Game(players);
		g.play();
	}		
}