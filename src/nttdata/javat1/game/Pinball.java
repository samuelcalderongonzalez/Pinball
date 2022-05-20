package nttdata.javat1.game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Clase Pinball.
 * 
 * @author Samuel Calderón González.
 *
 */
public class Pinball {
	private ArrayList<Player> players;
	private static final int leaderboardMembers = 5;

	/**
	 * Constructor.
	 * 
	 * @param players arraylist de jugadores.
	 */
	public Pinball(ArrayList<Player> players) {
		super();
		this.players = players;
		// Cada vez que se inicialice, se abrirá un mainMenu().
		mainMenu();
	}

	/**
	 * Método vacío que ofrece un menú con el que el usuario puede interactuar por
	 * consola.
	 */
	public void mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---- Pinball ----\n1. Insert Coin!\n2. Leaderboard\n3. Exit");
		int opc = Integer.parseInt(sc.nextLine());

		switch (opc) {
		case 1:
			coinMenu();
			break;
		case 2:
			printLeaderBoard();
			break;

		case 3:
			System.exit(0);
			break;
		}

		sc.close();
	}

	/*
	 * Método vacío que ofrece un menú para elegir el número de bolas(attemps) que
	 * quiere.
	 * 
	 * La decisión del usuario repercuterá en el número de intentos que va a
	 * disponer en el juego.
	 */
	public void coinMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Insert Coin!\n1. 50 cent (1 ball)\n2. 1€ (2 balls)\n3. 2€ (4 balls)\nPress any key + Enter to back");
		String opc = sc.next();
		if (opc.equals("1") || opc.equals("2"))
			new Game(Integer.parseInt(opc), 0, this.players);
		else if (opc.equals("3"))
			new Game(4, 0, this.players);
		else
			mainMenu();
		sc.close();
	}

	/**
	 * Método vacío que imprimer la tabla de puntuaciones, hasta un máximo de 5
	 * jugadores.
	 * 
	 */
	public void printLeaderBoard() {
		sortPlayers();
		Scanner sc = new Scanner(System.in);
		System.out.println("\t---- Leaderboard ----\nRank\t\tPlayers\t\t Score");
		// Si el tamaño de jugadores NO es mayor que 5, imprime todos.
		if (!(this.players.size() > leaderboardMembers)) {
			for (int i = 0; i < this.players.size(); i++) {
				System.out.println((i + 1) + ".\t\t" + this.players.get(i).getFullScore());
			}
			// En caso contrario, imprime sólo 5.
		} else {
			for (int i = 0; i < leaderboardMembers; i++) {
				System.out.println((i + 1) + ".\t\t" + this.players.get(i).getFullScore());
			}
		}
		System.out.println("     (Press any key + Enter to back)");
		@SuppressWarnings("unused")
		String op = sc.next();

		mainMenu();
		sc.close();
	}

	/**
	 * Método vacío que ordenada por puntuación el arraylist de jugadores.
	 */
	public void sortPlayers() {
		// ArrayList ordenado
		ArrayList<Player> sorted = new ArrayList<Player>();
		int maxVal;
		int j = 0;
		Player aux = new Player("yyy", 0);
		// El bucle persiste mientras existan jugadores.
		while (!this.players.isEmpty()) {
			maxVal = 0;
			// Busca el jugador con más score y lo almacena.
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getScore() > maxVal) {
					maxVal = players.get(i).getScore();
					aux = players.get(i);
					j = i;
				}
			}
			// El jugador con mayor score queda almacenado en el arraylist ordenador,
			// posteriormente lo elimino del arraylist original.
			sorted.add(aux);
			players.remove(j);
		}
		this.players = sorted;
	}
}
