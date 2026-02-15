package ads.p4.InvalidGame.exceptions;

/**
 * Excepcion MinimunSize
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class MinimunSize extends InvalidGame {
	private int size;
	
	/**
	 * Constructor de la excepcion MinimunSize
	 * @param size
	 */
	public MinimunSize(int size) {
		this.size = size;
	}
	
	/**
	 * Metodo toString
	 * @return
	 */
	@Override public String toString() {
		return "Minimun size must be 5. The size of the board is " + size; 
	}

}
