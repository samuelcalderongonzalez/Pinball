package nttdata.javat1;

import java.util.ArrayList;

import nttdata.javat1.game.Pinball;
import nttdata.javat1.game.Player;

/**
 * Clase Main
 * 
 * @author Samuel Calderón González
 *
 */
public class T1MainSCG {
	/*
	 * Método main
	 */
	public static void main(String[] args) {
		//Lista de jugadores creada junto con jugadores de ejemplo.
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("SCG", 7373));
		players.add(new Player("YM2", 69420));
		players.add(new Player("SKT", 999999));
		players.add(new Player("NTT", 123456));	
		new Pinball(players);
	}

}

