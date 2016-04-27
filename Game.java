/**
 * A game is an instance of the Set game
 * @author Darwin
 *
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private ArrayList<Player> players;
	private Table t;
	int[] playerScores; //represents current scores of players
	
	//The constructor creates a game with the
	//input array of players who want to play
	public Game(ArrayList<Player> playersIn){
		players = playersIn;
		t = new Table();
		playerScores = new int[players.size()]; //represents current scores of players
	}
	
	//move takes in the player moving, as well as their three choices of cards
	//increments points if the set is correct, decrements points if the set is incorrect
	//also updates the table and deck to prepare for the next turn
	//returns 0 if the move is incorrect, 1 if it is correct
	public boolean move(int playerToMove, int cardIndex1, int cardIndex2, int cardIndex3){
		Card card1;
		Card card2;
		Card card3;
		if(t.isSet(cardIndex1, cardIndex2, cardIndex3)){ //If correct, +1 to score, remove and replace cards from table
			playerScores[playerToMove]++;
			//TODO: send to server a message to increment player points
			card1 = t.getCard(cardIndex1);
			card2 = t.getCard(cardIndex2);
			card3 = t.getCard(cardIndex3);
			t.removeCard(card1);
			t.removeCard(card2);
			t.removeCard(card3);
			if(t.getCardCount() < 12){ //only draws more cards if there are fewer than 12 on the table
				//TODO: send to server a message to update tables in all clients
				t.addCard(3);
			}
			
			//add cards to table until set exists or no cards in deck
			while(!t.setsExist() && (t.getDeckCardCount() > 0)){
				//TODO: send to server a message to update tables in all clients
				t.addCard(3);
			}
			//TODO: receive from server a message to update clients' tables and player scores
			return true;
		}
		else{ //If incorrect -1 to score, no change to table
			playerScores[playerToMove]--;
			//TODO: send to server a message to decrement player points
			//TODO: receive from server a message to update clients' player scores
			return false;
		}		
	}
	
	//Finds the index of the player with the most points
	public int mostPointsPlayerIndex(){
		int mostIndex = 0;
		for(int i = 0; i < playerScores.length; i++){
			if(playerScores[i] > playerScores[mostIndex]){
				mostIndex = i;
			}
		}
		return mostIndex;
	}
	
	//Updates high scores for all players
	//Returns a list of players who have higher high scores now,
	//with their corresponding names and new high scores
	public void updateHighScores(){
		ArrayList<Player> newHighScorePlayers = new ArrayList<Player>();
		for(int i = 0; i < playerScores.length; i++){
			if(playerScores[i] > players.get(i).getHighScore()){
				players.get(i).setHighScore(playerScores[i]);
				newHighScorePlayers.add(players.get(i));
			}
		}
		//TODO: send newHighScorePlayers to server to update high scores in the database
	}
	
	//The main function in Game, provides a fun textual experience with playing Set.
	public void play(){
		//Setup
		int playerToMove;
		boolean moveCorrect;
		int cardIndex1;
		int cardIndex2;
		int cardIndex3;
		Scanner gameReader = new Scanner(System.in);
		
		
		//Game introduction
		System.out.println("Welcome to the Set Game!!");
		System.out.println("Today's players are: ");
		//Print participating players
		for(int i = 0; i < players.size(); i++){
			System.out.println(players.get(i)); //print players
			playerScores[i] = 0;
		}
		
		//Main game loop
		while(t.getCardCount() > 0){
			//Show cards
			System.out.println(t);
			t.showAllSets(); //SHOW ALL SETS FOR TESTING ONLY!
			
			//Enter a move (player number)
			System.out.println("To select a set, please enter the player number:");
			
			playerToMove = gameReader.nextInt();
			//player number validity check
			if(playerToMove < 0 || playerToMove >= playerScores.length){
				System.out.println("Player number out of bounds. Please select a valid player next time.");
				continue; //try again
			}
			
			//Enter a move (card indices)
			System.out.println("Select the first card index: ");
			cardIndex1 = gameReader.nextInt();
			System.out.println("Select the second card index: ");
			cardIndex2 = gameReader.nextInt();
			System.out.println("Select the third card index: ");
			cardIndex3 = gameReader.nextInt();
			
			//catch out of bounds card indices
			if(cardIndex1 < 0 || cardIndex2 < 0 || cardIndex3 < 0 || cardIndex1 >= t.getCardCount() || cardIndex2 >= t.getCardCount() || cardIndex3 >= t.getCardCount()){
				System.out.println("Invalid card choice. One or more of the card indices chosen was out of bounds");
				continue;
			}
			
			//Execute move: Determine validity of Set and react appropriately
			moveCorrect = move(playerToMove, cardIndex1, cardIndex2, cardIndex3);
			if(moveCorrect){
				System.out.println(players.get(playerToMove).getName() + " has discovered a valid set, and now has " + playerScores[playerToMove] + " points.");
			}
			else{
				System.out.println(players.get(playerToMove).getName() + " has selected an invalid set, and now has " + playerScores[playerToMove] + " points.");
			}
			
			//Check for game over
			if(!t.setsExist()){
				System.out.println(t);
				break;
			}
		}
		//Game Over!
		
		//detect winner index (max score) (doesn't work well for ties!)
		
		int winnerIndex = mostPointsPlayerIndex();
		System.out.println("GAME OVER!");
		System.out.println("The winner is " + players.get(winnerIndex).getName() + ", with " + playerScores[winnerIndex] + " points!");
		System.out.println();
		
		//Print final scores
		System.out.println("Final scores: ");
		for(int i = 0; i < playerScores.length; i++){
			System.out.println(players.get(i).getName() + ": " + playerScores[i]);
		}
		
		//Update high scores
		updateHighScores();
		gameReader.close();
	}	
}