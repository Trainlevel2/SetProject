/**@author Darwin
 * Each deck contains all cards to be drawn and placed onto the table
 * Decks start with cards, and draw them randomly. This is implemented
 * differently than the expected creation of a deck then shuffling, but
 * practically works the same from an outsider's perspective
 * Referenced: http://math.hws.edu/javanotes/c5/s4.html
 */
public class Deck {
	
	//An array of up to 81 cards. Becomes smaller as the game progresses
	private Card[] deck = new Card[81];
	private int cardsUsed;
	
	//The constructor creates a shuffled deck
	public Deck(){
		//inserts all cards into the deck
		int cardCt = 0;
		for(int inumber = 1; inumber <= 3; inumber++){
			for(int isymbol = 0; isymbol <= 2; isymbol++){
				for(int ishading = 0; ishading <= 2; ishading++){
					for(int icolor = 0; icolor <= 2; icolor++){
						deck[cardCt] = new Card(ishading, isymbol, icolor, inumber);
						cardCt++;
					}
				}
			}
		}
		//after that, for the original game, all cards must be shuffled.
		shuffle();
		cardsUsed = 0;
	}
	
	/**
	 * Shuffle puts all used cards back into the deck (if any) and shuffles
	 * it into a random order
	 */
	public void shuffle(){
		for(int i = deck.length-1; i > 0; i--){
			int rand = (int)(Math.random()*(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
	}
	
	public int getCardsUsed(){
		return cardsUsed;
	}
	
	public int getCardsLeft(){
		return deck.length - cardsUsed;
	}
	
	public Card dealCard(){
		if(cardsUsed == deck.length){
			throw new IllegalStateException("No cards are left in the deck.");
		}
		cardsUsed++;
		return deck[cardsUsed - 1];
		//Note: Cards aren't actually removed from the array. They're
		//just used once iteratively
	}
}
