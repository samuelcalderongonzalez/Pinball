package nttdata.javat1.game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Game
 * 
 * @author Samuel Calderón González
 *
 */
public class Game {
	private ArrayList<Player> players;

	private int attemps;
	private int bouncesN;
	private int currentScore;
	private int randomScore;
	private int bestScore;
	private Ball ball;

	/**
	 * Constructor de la clase Game
	 * 
	 * @param attemps   Recibe por parámetro el número de intentos. Hace referencia
	 *                  al número de bolas
	 * @param bestScore Recibe por parámetro la mejor puntuación. Al principio debe
	 *                  ser 0, pero su valor varía en caso de tener varios intentos.
	 * @param players   Recibe el array de jugadores para añadir el jugador actual a
	 *                  él. Esta es una pequeña "solución" sustituta a utilizar base
	 *                  de datos
	 */
	public Game(int attemps, int bestScore, ArrayList<Player> players) {
		super();
		this.attemps = attemps;
		// Variable que genera una cantidad de rebotes aleatoria entre 1-20.
		this.bouncesN = (int) ((Math.random() * 19) + 1);
		this.currentScore = 0;
		this.players = players;
		this.bestScore = bestScore;
		this.ball = new Ball();

		Bounces();
		finalLogic();

	}

	/**
	 * Método vacío que cambia el bestScore si el currentScore es mayor.
	 */
	public void setBestScore() {
		if (this.currentScore >= this.bestScore)
			this.bestScore = this.currentScore;
	}

	/**
	 * Método vacío con un bucle de bounce() en función a la cantidad de rebotes.
	 */
	public void Bounces() {
		System.out.println("You've got a " + this.ball.getType() + " ball!. x" + this.ball.getScoreMultiplier()
				+ " score multiplier");
		timeSleep(1500);
		for (int i = 0; i < this.bouncesN; i++) {
			timeSleep((int)(Math.random()*2500));
			bounce();
		}
	}

	/**
	 * Getter bestScore
	 * 
	 * @return bestScore
	 */
	public int getFinalScore() {
		return bestScore;
	}

	/**
	 * Método vacío que se encarga de generar la puntuación de UN rebote, mostrando
	 * un mensaje por consola.
	 * 
	 * La puntuación es generada aleatoriamente en base a 9 puntuaciones
	 * predefinidas, haciendo referencia a los diferentes obstáculos en los que una
	 * bola pudiera rebotar
	 */
	public void bounce() {
		this.randomScore = (int) ((Math.random() * 8) + 1);
		int scr = 0;
		switch (randomScore) {
		case 1:
			scr = 50;
			break;
		case 2:
			scr = 500;
			break;
		case 3:
			scr = 1000;
			break;
		case 4:
			scr = 2500;
			break;
		case 5:
			scr = 5000;
			break;
		case 6:
			scr = 10000;
			break;
		case 7:
			scr = 25000;
			break;
		case 8:
			scr = 5;
		case 9:
			scr = (int) (Math.random() * 50000);
			break;
		}
		this.currentScore += (scr * this.ball.getScoreMultiplier());
		System.out.println("+" + ((int)(scr  * this.ball.getScoreMultiplier())) + " points!");

	}

	/**
	 * Método que permite al usuario introducir su nombre, controlando los
	 * diferentes errores que esto puede suponer; solo puede tener 3 caracteres, y
	 * el nombre no puede estar cogido.
	 * 
	 * @return String nombre del usuario
	 */
	public String nameMenu() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert player name (only 3 characters)");
		String name;
		boolean alreadyTaken = false;
		do {
			// Convierte el nombre a mayúsculas
			name = sc.nextLine().toUpperCase();
			if (name.length() > 3) {
				System.out.println("Type only 3 characters");
			}
			alreadyTaken = isNameTaken(name);

		} while (name.length() > 3 || alreadyTaken);
		return name;

	}

	/**
	 * Método que introduce un tiempo de espera.
	 * Utilizado para añadir realismo tanto a los rebotes de la pelota como a los
	 * tiempos de carga.
	 */
	public void timeSleep(int ms) {
		try {
			Thread.sleep((int) (Math.random() * ms));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método booleano que recibe un String por parámetro y comprueba si dicho
	 * string pertenece o no al nombre de alguno de los players.
	 * 
	 * @param name String a comprobar
	 * @return true en caso de que el nombre esté en uso, false en caso contrario.
	 */
	public boolean isNameTaken(String name) {
		boolean alreadyTaken = false;
		for (Player p : players) {
			if (p.getName().equals(name)) {
				System.out.println("That name is already used");
				alreadyTaken = true;
				break;
			}
		}
		return alreadyTaken;
	}

	/**
	 * Método vacío que realizar la lógica final de la clase, con mensajes que guían
	 * al usuario.
	 */
	public void finalLogic() {
		setBestScore();
		timeSleep(2500);
		// Decrementa la variable "attemps" en el momento.
		System.out.println("Your ball has fallen into a hole! Your score current score is " + this.currentScore
				+ ".\nYour best score is " + this.bestScore + ". " + --this.attemps + " balls remaining.\n");
		timeSleep(1000);
		// Si quedan intentos, lanza otra bola.
		if (this.attemps > 0) {
			System.out.println("Your next ball is currently launching...\n");
			timeSleep(1000);
			// Crea nuevo juego con la variable attemps ya decrementada. Esto actua como una
			// especie de recursividad.
			new Game(this.attemps, this.bestScore, this.players);
		} else {
			// Si NO quedan intentos, se añade el jugador a la colección, manteniendo su
			// puntuación mayor entre todos los intentos. Posteriormente creo un nuevo
			// pinball.
			this.players.add(
					new Player(nameMenu(), this.bestScore >= this.currentScore ? this.bestScore : this.currentScore));
			System.out.println("Going to the main menu...\n");
			timeSleep(1500);
			new Pinball(this.players);
		}
	}

}
