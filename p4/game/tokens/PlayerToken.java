package ads.p4.game.tokens;
import ads.p4.game.IToken;
import ads.p4.game.Player;

/**
 * Clase PlayerToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public abstract class PlayerToken implements IToken {
	private int valor;
	private Player player;
	
	/**
	 * Constructor de la clase PlayerToken
	 * @param player
	 */
	public PlayerToken(Player player) {
		this.player = player;
		
	}
	
	/**
	 * Metodo que devuelve el valor
	 * @return
	 */
	public int getValor() {
		return valor;
	}
	
	/**
	 * Metodo que permite cambiar el valor
	 * @param valor
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	/**
	 * Meotod que devuelve el jugador
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Metodo que permite cambiar el jugador
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Metodo que decide si el token es sobreescribible o no
	 */
	public abstract boolean canBeOverwritten();
	
	

}
