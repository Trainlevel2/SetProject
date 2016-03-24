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
	
	//The constructor creates a game with the
	//specified amount of players and a 
	public Game(ArrayList<Player> playersIn){
		players = playersIn;
		t = new Table();
	}
	
	public void play(){
		//Setup
		int playerToMove;
		int cardIndex1;
		int cardIndex2;
		int cardIndex3;
		Card card1;
		Card card2;
		Card card3;
		Scanner gameReader = new Scanner(System.in);
		int[] playerScores = new int[players.size()]; //represents current scores of players
		
		//Game introduction
		System.out.println("Welcome to the Set Game");
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
			
			//Wait for a player to move
			System.out.println("To select a set, please enter the player number:");
			
			playerToMove = gameReader.nextInt();
			//player number validity check
			if(playerToMove < 0 || playerToMove >= playerScores.length){
				System.out.println("Player number out of bounds. Please select a valid player next time.");
				continue; //try again
			}
			
			//Accept a move
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
			
			
			//Determine validity of Set and react appropriately
			if(t.isSet(cardIndex1, cardIndex2, cardIndex3)){ //If correct, +1 to score, remove and replace cards from table
				playerScores[playerToMove]++;
				card1 = t.getCard(cardIndex1);
				card2 = t.getCard(cardIndex2);
				card3 = t.getCard(cardIndex3);
				t.removeCard(card1);
				t.removeCard(card2);
				t.removeCard(card3);
				if(t.getCardCount() < 12){ //only draws more cards if there are fewer than 12 on the table
					t.addCard(3);
				}
				System.out.println(players.get(playerToMove).getName() + " has discovered a valid set, and now has " + playerScores[playerToMove] + " points.");
				
				//add cards to table until set exists or no cards in deck
				while(!t.setsExist() && (t.getDeckCardCount() > 0)){
					t.addCard(3);
				}
				//if sets exist and no cards in deck... game over!
				if(!t.setsExist()){
					System.out.println(t);
					break;
				}
			}
			else{ //If incorrect -1 to score, no change to table
				playerScores[playerToMove]--;
				System.out.println(players.get(playerToMove).getName() + " has selected an invalid set, and now has " + playerScores[playerToMove] + " points.");
			}
		}
		//detect winner index (max score) (doesn't work for ties!)
		int winnerIndex = 0;
		for(int i = 0; i < playerScores.length; i++){
			if(playerScores[i] > playerScores[winnerIndex]){
				winnerIndex = i;
			}
		}
		
		//Game Over!
		System.out.println("GAME OVER!");
		System.out.println("The winner is " + players.get(winnerIndex).getName() + ", with " + playerScores[winnerIndex] + " points!");
		System.out.println();
		
		//Print final scores and update high scores
		System.out.println("Final scores: ");
		for(int i = 0; i < playerScores.length; i++){
			System.out.println(players.get(i).getName() + ": " + playerScores[i]);
			if(playerScores[i] > players.get(i).getHighScore()){
				players.get(i).setHighScore(playerScores[i]);
			}
		}
		gameReader.close();
	}	
}