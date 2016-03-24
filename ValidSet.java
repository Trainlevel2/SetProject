/**Each validSet contains three cards that form a valid set together
 */
public class ValidSet {
	final Card[] validSet; //not private! allows easy access to cards in set
	
	//the constructor creates a cardSet
	public ValidSet(Card a, Card b, Card c){
		validSet = new Card[3];
		validSet[0] = a;
		validSet[1] = b;
		validSet[2] = c;
	}
	
	public Card getCard1(){
		return validSet[0];
	}
	
	public Card getCard2(){
		return validSet[1];
	}
	
	public Card getCard3(){
		return validSet[2];
	}
	
	public String toString(){
		return validSet[0].toString() + "\n" +  validSet[1].toString() + "\n" +  validSet[2].toString();
	}
}