/**Each card has four features (all cards are unique):
 * Number: 1, 2, 3
 * Symbol: Diamond, Squiggle, Oval
 * Shading: Solid, Striped, Clear
 * Color: Red, Green, Purple
 * Referenced: http://math.hws.edu/javanotes/c5/s4.html
 */
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
 
public class Card {

	//Codes for Symbols 
	public final static int DIAMOND = 0;
	public final static int OVAL = 1;
	public final static int SQUIGGLE = 2;
	
	//Codes for Shading
	public final static int SOLID = 0;
	public final static int STRIPED = 1;
	public final static int CLEAR = 2;
	
	//Codes for Color
	public final static int RED = 0;
	public final static int GREEN = 1;
	public final static int PURPLE = 2;
	
	//Following are properties of the card. 
	//They cannot be changed once the card is made.
	private final int number; //1, 2, or 3
	private final int symbol; //diamond, squiggle, or oval (0, 1, or 2)
	private final int shading; //solid, striped, or clear (0, 1 or 2)
	private final int color; //red, green, or purple (0, 1, or 2)
	
	//Graphics Properties
	private String filename;
	private int x;
	private int y;
	private int width = 42;
	private int height = 68;
	
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
		
		//Graphics adjustment settings
		switch(shading){
		case SOLID	: filename = "set_solid.png";  break;
		case STRIPED	: filename = "set_shaded.png"; break;
		case CLEAR  	: filename = "set_clear.png";  break;
		default		: System.err.println("Error: " + shade +  " is not a valid shade"); return;
		}
		switch(symbol) {
		case DIAMOND 	: y = 6;    break;
		case OVA	: y = 83;   break;
		case SQUIGGLE	: y = 160;  break;
		default		: System.err.println("Error: " + shape +  " is not a valid shape"); return;
		}
		switch(color) {
		case RED	: x = 10;    break;
		case GREEN	: x = 205;   break;
		case PURPLE 	: x = 395;   break;
		default		: System.err.println("Error: " + color +  " is not a valid color"); return;
		}
		switch(number) {
		case 1 		: break;
		case 2 		: x+= width + 17; break;
		case 3 		: x+= width + 78; break;
		default		: System.err.println("Error: " + number + " is not a valid color"); return;
		}
		
	}
	
	
	
	
	//getter functions for all properties below
	public BufferedImage getImage(){
		BufferedImage before = null;
		BufferedImage after = null;
		try {
			before = ImageIO.read(new File(filename));
			int w = before.getWidth();
			int h = before.getHeight();
			after = new BufferedImage(w*2, h*2, BufferedImage.TYPE_INT_ARGB);
			AffineTransform at = new AffineTransform();
			at.scale(2.0, 2.0);
			AffineTransformOp scaleOp = 
			   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			after = scaleOp.filter(before, after);
			after = after.getSubimage(x*2, y*2, width*2, height*2);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return after;
	}
	
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
		case CLEAR: return "Clear";
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
