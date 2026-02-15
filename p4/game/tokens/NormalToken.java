package ads.p4.game.tokens;

import ads.p4.game.Player;

/**
 * Clase NormalToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class NormalToken extends PlayerToken{

	
	/**
	 * Constructor del la clase NormalToken
	 * @param player
	 */
	public NormalToken(Player player) {
		super(player);
		this.setValor(1);
	}

	/**
	 * Metodo toString
	 * @return
	 */
	public String toString() {
		return "N" + this.getPlayer().getId();
	}
	
	/**
	 * Metodo que decide si el token es sobreescribible o no
	 */
	@Override
	public boolean canBeOverwritten() {
		return true;
	}

}
