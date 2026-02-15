package ads.p4.ForbiddenToken.exceptions;

import ads.p4.game.IToken;

/**
 * Excepcion Noneighborhoodcell
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class Noneighborhoodcell extends ForbiddenToken {
	
	/**
	 * Constructor de la excepcion Noneighborhoodcell
	 * @param token
	 * @param row
	 * @param column
	 */
	public Noneighborhoodcell(IToken token, int row, int column) {
		super(token, row, column);
	}

	/**
	 * Metodo toString
	 * @return
	 */
	@Override
	public String toString() {
		return super.toString() + "the token has been placed in non neighborhood cell to last moving token cell";
	}
}
