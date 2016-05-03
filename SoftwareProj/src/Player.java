/**Each Player has:
 * highScore
 * name
 * @author Darwin
 */
public class Player {
	private int highScore;
	private String name;
	
	public Player(String newName){
		highScore = 0;
		name = newName;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public int getHighScore(){
		return highScore;
	}

	public void setHighScore(int newScore){
		highScore = newScore;
	}
	
	public String toString(){
		return name + " (" + highScore + ")";
	}
}