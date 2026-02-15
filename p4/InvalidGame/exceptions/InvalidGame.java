package ads.p4.InvalidGame.exceptions;

/**
 * Excepcion InvalidGame
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public abstract class InvalidGame extends Exception {
	
	/**
	 * Metodo toString
	 * @return
	 */
	@Override public String toString() {
		return "Error. Invalid board. ";
	}
	
}
