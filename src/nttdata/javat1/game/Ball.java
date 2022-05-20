package nttdata.javat1.game;

/**
 * Clase Ball. Al iniciar una partida, puedes obetener diferentes tipos de bola
 * de manera aleatoria. Mientras más calidad tenga la bola, mayor bonificador de
 * score
 * 
 * @author Samuel Calderón González
 *
 */
public class Ball {
	private String type;
	private double scoreMultiplier;

	/**
	 * Constructor ball.
	 */
	public Ball() {
		super();
		setTypes();

	}

	/**
	 * Método vacío para establecer las probabilidades de cada tipo de bola.
	 */
	public void setMultipliers() {
		int randomizer = (int) (Math.random() * 20000);
		if (randomizer < 20)
			this.type = "diamond";
		else if (randomizer < 100)
			this.type = "platinum";
		else if (randomizer < 400)
			this.type = "golden";
		else if (randomizer < 1000)
			this.type = "silver";
		else if (randomizer < 5000)
			this.type = "bronze";
		else
			this.type = "normal";
	}

	/**
	 * Método vacío para establecer los multiplicadores en función al tipo de bola.
	 */
	public void setTypes() {
		setMultipliers();
		switch (this.type) {
		case "bronze":
			this.scoreMultiplier = 1.1;
			break;
		case "silver":
			this.scoreMultiplier = 1.2;
			break;
		case "golden":
			this.scoreMultiplier = 1.5;
			break;
		case "platinum":
			this.scoreMultiplier = 2;
			break;
		case "diamond":
			this.scoreMultiplier = 3;
			break;
		default:
			this.scoreMultiplier = 1;
		}
	}

	public String getType() {
		return type;
	}

	public double getScoreMultiplier() {
		return scoreMultiplier;
	}
}
