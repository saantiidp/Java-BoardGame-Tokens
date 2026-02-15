package ads.p4.game.tokens;
import ads.p4.game.IToken;

/**
 * Clase GameToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public abstract class GameToken implements IToken {
	
	/**
	 * Metodo que decide si el token es sobreescribible o no
	 */
	public abstract boolean canBeOverwritten();
	
	
}
