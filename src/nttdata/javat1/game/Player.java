package nttdata.javat1.game;

/**
 * 
 * Clase Player
 * 
 * @author Samuel Calder�n Gonz�lez
 *
 */
public class Player {
	private static final int maxScore = 999999;
	private String name;
	private int score;
	private String scoreAux;
	/**
	 * Constructor
	 * @param name Nombre del jugador
	 * @param score Puntuaci�n del jugador
	 */
	public Player(String name, int score) {
		super();
		this.name = name.toUpperCase();
		this.score = score;
		this.scoreAux = "";
		scoreAuxLogic();
	}
	/**
	 * Getter Score
	 * @return Score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Getter Name
	 * @return Name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter fullscore
	 * @return String Name + Score
	 */
	public String getFullScore() {
		return this.name + "\t\t" + this.scoreAux + this.score;
	}
	/**
	 * A�ade ceros delante del score en funci�n a sus d�gitos.
	 */
	public void scoreAuxLogic() {
		if (this.score < 10) {
			scoreAux = "     ";
		} else if (this.score < 100) {
			scoreAux = "    ";
		} else if (this.score < 1000) {
			scoreAux = "  ";
		} else if (this.score < 10000) {
			scoreAux = "  ";
		} else if (this.score < 100000) {
			scoreAux = " ";
		} else if (this.score > 1000000) {
			this.score = maxScore;
		}
	}

}
