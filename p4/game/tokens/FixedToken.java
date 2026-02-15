package ads.p4.game.tokens;

import ads.p4.game.Player;

/**
 * Clase FixedToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class FixedToken extends PlayerToken {
	
	/**
	 * Constructor de la clase FixedToken
	 * @param player
	 */
	public FixedToken(Player player) {
		super(player);
		this.setValor(1);
	}
	
	/**
	 * Metodo toString
	 */
	public String toString() {
		return "F" + this.getPlayer().getId();
	}
	
	/**
	 * Metodo que decide si el token es sobreescribible o no
	 */
	@Override
	public boolean canBeOverwritten() {
		return false;
	}

}
