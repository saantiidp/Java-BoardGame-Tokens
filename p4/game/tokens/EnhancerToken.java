package ads.p4.game.tokens;

/**
 * Clase EnhancerToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class EnhancerToken extends GameToken{
	/**
	 * Constructor de la clase EnhancerToken
	 */
	public EnhancerToken() {
		
	}
	/**
	 * Metodo ToString
	 */
	public String toString() {
		return "EN";
	}
	
	/**
	 * Metodo que devuelve si el token se pueden sobreescribir o no
	 */
	@Override
	public boolean canBeOverwritten() {
		return true;
	}
	
}
