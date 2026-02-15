package ads.p4.game.tokens;

import ads.p4.game.Player;

/**
 * Clase MultiplierToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class MultiplierToken extends PlayerToken{

	/**
	 * Constructor de la clase Multiplier
	 * @param player
	 */
	public MultiplierToken(Player player) {
		super(player);
		this.setValor(3);
	}

	/** 
	 * Metodo to String
	 */
	public String toString() {
		return "M" + this.getPlayer().getId();
	}
	
	/**
	 * Metodo que decide si el token es sobreescribible o no
	 */
	@Override
	public boolean canBeOverwritten() {
		return true;
	}

}
