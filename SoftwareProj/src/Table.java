/**
 * The Table represents all cards currently on the table for players
 * to see, and determines allowed moves
 * @author Darwin
 * Referenced: http://math.hws.edu/javanotes/source/chapter5/Hand.java
 */

import java.util.ArrayList;

public class Table {
	private Deck d;
	private ArrayList<Card> table;
	
	//The Constructor creates a table with 
	//the minimum amount of cards(12) and a valid deck 
	public Table(){
		d = new Deck();
		table = new ArrayList<Card>();
		
		addCard(12); //puts initial 12 cards onto the table
	}
	
	//Adds n cards to the table from the deck, assuming cards still exist in the deck
	//If there's no cards in the deck,this command is ignored
	public void addCard(int a){
		for(int i = 0; i < a; i++){
			if(d.getCardsLeft() > 0){
				table.add(d.dealCard());
			}
			else{
				//throw new IllegalStateException("No Cards are left in the Deck to Draw onto the table");
			}
		}
	}
	
	public int getSize(){
		return table.size();
	}
	
	//remove a card from the table
	public void removeCard(Card c){
		if(!table.remove(c)){
			throw new IllegalStateException("Card doesn't exist on table!");
		}
	}
	
	//remove the card at a location from the table
	public void removeCard(int a){
		table.remove(a); //remove() handles out of bounds exception throwing
	}
	
	public int getCardCount(){
		return table.size();
	}
	
	public int getDeckCardCount(){
		return d.getCardsLeft();
	}
	
	public int getCardIndex(Card c){
		return table.indexOf(c);
	}
	
	//FUNCTIONS BELOW REGARD FINDING SETS FOR VALID GAMEPLAY
	
	//LET PLAYERS REQUEST DRAWING 3 MORE OR ENDING GAME GIVEN NO SETS FOUND
	
	//returns true if cards are all equal or all different on all properties
	//returns false otherwise
	public boolean isSet(Card a, Card b, Card c){
		if(a.equals(b) || a.equals(c) || b.equals(c)){ //duplicate cards
			return false;
		}
		boolean isNumberSet = false;
		boolean isSymbolSet = false;
		boolean isShadingSet = false;
		boolean isColorSet = false;
		//all equal
		if(a.getNumber() == b.getNumber() && b.getNumber() == c.getNumber()){
			isNumberSet = true;
		}
		if(a.getSymbol() == b.getSymbol() && b.getSymbol() == c.getSymbol()){
			isSymbolSet = true;
		}
		if(a.getShading() == b.getShading() && b.getShading() == c.getShading()){
			isShadingSet = true;
		}
		if(a.getColor() == b.getColor() && b.getColor() == c.getColor()){
			isColorSet = true;
		}
		
		//all different
		if(a.getNumber() != b.getNumber() && a.getNumber() != c.getNumber() && b.getNumber() != c.getNumber()){
			isNumberSet = true;
		}
		if(a.getSymbol() != b.getSymbol() && a.getSymbol() != c.getSymbol() && b.getSymbol() != c.getSymbol()){
			isSymbolSet = true;
		}
		if(a.getShading() != b.getShading() && a.getShading() != c.getShading() && b.getShading() != c.getShading()){
			isShadingSet = true;
		}
		if(a.getColor() != b.getColor() && a.getColor() != c.getColor() && b.getColor() != c.getColor()){
			isColorSet = true;
		}
		
		//return the result
		return (isNumberSet && isSymbolSet && isShadingSet && isColorSet);
	}
	
	public boolean isSet(int index1, int index2, int index3){
		return isSet(table.get(index1), table.get(index2), table.get(index3));
	}
	
	//Returns a string representation of this table
	public String toString(){		
		String s = "";
		s += "Deck size: " + getDeckCardCount() + "\n";
		s += "Table Size: " + getCardCount() + "\n";
		s += "Table: \n";
		//add all cards to the table
		for(int i = 0; i < table.size(); i++){
			//add card index for ASCII game
			s += "Card " + String.format("%02d", i) + ": ";
			
			//add card toString()
			s += table.get(i) + "\n";
		}
		return s;
	}
	
	//findSet returns a valid set in case we want to cheat and see them all :3
	//performs an inefficient exhaustive search, but since the search space
	//is small, it won't be too slow
	public ArrayList<ValidSet> findAllSets(){
		ArrayList<ValidSet> allSets = new ArrayList<ValidSet>();
		for(int i = 0; i < table.size(); i++){
			for(int j = i + 1; j < table.size(); j++){
				for(int k = j + 1; k < table.size(); k++){
					if(isSet(table.get(i), table.get(j), table.get(k))){
						ValidSet s = new ValidSet(table.get(i), table.get(j), table.get(k));
						allSets.add(s);
					}
				}
			}
		}
		return allSets;
	}
	
	public void showAllSets(){
		ArrayList<ValidSet> allSets = findAllSets();
		for(int i = 0; i < allSets.size(); i++){
			int i1 = getCardIndex(allSets.get(i).getCard1());
			int i2 = getCardIndex(allSets.get(i).getCard2());
			int i3 = getCardIndex(allSets.get(i).getCard3());
			System.out.println("Valid Set " + i + " (" + i1 + "," + i2 + "," + i3 + "):\n" + allSets.get(i));
		}
	}
	
	public boolean setsExist(){
		return (findAllSets().size() > 0);
	}
	
	public Card getCard(int i){
		if(i>table.size())
			return null;
		return table.get(i);
	}
}