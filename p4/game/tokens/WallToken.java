package ads.p4.game.tokens;

/**
 * Clase WallToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class WallToken extends GameToken{

	/**
	 * Constructor de la clase WallToken
	 */
	public String toString() {
		return "WA";
	}
	
	/**
	 * Metodo que decide si el token es sobreescribible o no
	 */
	@Override
	public boolean canBeOverwritten() {
		return false;
	}
	
	
}
