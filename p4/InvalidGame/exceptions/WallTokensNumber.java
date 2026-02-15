package ads.p4.InvalidGame.exceptions;

/**
 * Excepcion WallTokenNumber
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class WallTokensNumber extends InvalidGame{
	private int size;
	
	/**
	 * Construcotr de la excepcion WallTokenNumber
	 * @param size
	 */
	public WallTokensNumber(int size) {
		this.size = size;
	}
	
	/**
	 * Metodo toString
	 * @return
	 */
	@Override public String toString() {
		return "Number of rows and columns must be "  + size + ". Number of wall tokens not exceed " + (size/2 - 1);
	}
}
