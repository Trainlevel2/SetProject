/**
 * @author Darwin
 * Used to perform unit and integration testing for classes and methods
 */

public class SetTester {
	public static void main(String[] args) {
		//cardTest();
		//deckTest();
		tableTest();
	}
	
	//tests the card class
	static void cardTest(){
		Card c = new Card(1,0,0,0);
		System.out.println(c);
	}
	
	//tests the deck class
	static void deckTest(){
		//Test cards and values in "deck"
		Deck d = new Deck();
		for(int i = 0; i < 81; i++){
			Card c = d.dealCard();
			//Shows Cards
			System.out.println("Card " + String.format("%02d", d.getCardsUsed()) + " (" + String.format("%02d", d.getCardsLeft()) + ") is " + c.toShortString());
			//System.out.println("Card " + d.getCardsUsed() + " = " + (c.toInteger()+1)); //when cards aren't shuffled, ensures all cards are in correct place
		}
	}
	
	//tests the table class
	static void tableTest(){
		Table t = new Table();
		System.out.println(t);
		t.addCard(3);
		System.out.println(t);
		
	}
}
