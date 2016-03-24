/**Each card has four features (all cards are unique):
 * Number: 1, 2, 3
 * Symbol: Diamond, Squiggle, Oval
 * Shading: Solid, Striped, Open
 * Color: Red, Green, Purple
 * Referenced: http://math.hws.edu/javanotes/c5/s4.html
 */
public class Card {

	//Codes for Symbols 
	public final static int DIAMOND = 0;
	public final static int SQUIGGLE = 1;
	public final static int OVAL = 2;
	
	//Codes for Shading
	public final static int SOLID = 0;
	public final static int STRIPED = 1;
	public final static int OPEN = 2;
	
	//Codes for Color
	public final static int RED = 0;
	public final static int GREEN = 1;
	public final static int PURPLE = 2;
	
	//Following are properties of the card. 
	//They cannot be changed once the card is made.
	private final int number; //1, 2, or 3
	private final int symbol; //diamond, squiggle, or oval (0, 1, or 2)
	private final int shading; //solid, striped, or open (0, 1 or 2)
	private final int color; //red, green, or purple (0, 1, or 2)
	
	//The card's constructor creates a card with specified properties
	public Card(int theNumber, int theSymbol, int theShading, int theColor){
		//Ensure legality of inputs
		//Exceptions shouldn't be thrown assuming values are set by the Deck class
		if(theNumber < 1 || theNumber > 3){
			throw new IllegalArgumentException("Illegal playing card number!");
		}
		if(theSymbol < 0 || theSymbol > 2){
			throw new IllegalArgumentException("Illegal playing card symbol!");
		}
		if(theShading < 0 || theShading > 2){
			throw new IllegalArgumentException("Illegal playing card shading!");
		}
		if(theColor < 0 || theColor > 2){
			throw new IllegalArgumentException("Illegal playing card color!");
		}
		//assign values to variables
		number = theNumber;
		symbol = theSymbol;
		shading = theShading;
		color = theColor;
	}
	
	//getter functions for all properties below
	public int getNumber(){
		return number;
	}
	
	public int getSymbol(){
		return symbol;
	}
	
	public int getShading(){
		return shading;
	}
	
	public int getColor(){
		return color;
	}
	
	public String getSymbolAsString(){
		switch(symbol){
		case DIAMOND: return "Diamond";
		case SQUIGGLE: return "Squiggle";
		case OVAL: return "Oval";
		default: return "No Symbol Found"; //is "No Symbol Found" appropriate? Shouldn't happen anyways.
		}
	}
	
	public String getShadingAsString(){
		switch(shading){
		case SOLID: return "Solid";
		case STRIPED: return "Striped";
		case OPEN: return "Open";
		default: return "No Shading Found";
		}
	}
	
	public String getColorAsString(){
		switch(color){
		case RED: return "Red";
		case GREEN: return "Green";
		case PURPLE: return "Purple";
		default: return "No Color Found";
		}
	}
	
	//Returns a string representation of this card
	public String toString(){
		return getNumber() + " " + getSymbolAsString() + " " + getShadingAsString() + " " + getColorAsString(); 
	}
	
	//Returns a shorter string representation of this card (simplify debugging
	public String toShortString(){
		return getNumber()-1 + " " + getSymbol() + " " + getShading() + " " + getColor();
	}
	
	public int toInteger(){
		return (getNumber()-1)*27 + getSymbol()*9 + getShading()*3 + getColor();
	}
}