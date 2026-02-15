package ads.p4.game;
import java.util.ArrayList;

import ads.p4.game.tokens.*;

/**
 * Clase Player
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class Player {
	static int number = 0;
	public int id;
	private ArrayList<PlayerToken> tokens = new ArrayList<>();
	/**
	 * Constructor de la clase Player
	 */
	public Player() {
		Player.number++;
		this.id = Player.number;
	}
	
	/**
	 * Metodo que devuelve el array de Tokens del jugador
	 * @return
	 */
	public ArrayList<PlayerToken> getTokens() {
		return tokens;
	}
	
	/**
	 * Metodo que permite cambiar el añadir un token
	 * @param token
	 */
	public void setToken(PlayerToken token){
		tokens.add(token);
	}
	
	/**
	 * Metodo que permite cambiar el array de Tokens
	 * @param tokens
	 */
	public void setTokens(ArrayList<PlayerToken> tokens) {
		this.tokens = tokens;
	}
	
	/**
	 * Metodo que devuelve el id
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Metodo que devuelve el ultimo token utilizado
	 * @return
	 */
	public PlayerToken getlasttoken() {
		return this.tokens.get(this.tokens.size()-1);
	}




	
}
