package ads.p4.ForbiddenToken.exceptions;

import ads.p4.game.IToken;

/**
 * Excepcion OutofBoardMargins
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class OutofBoardMargins extends ForbiddenToken {
	
	/**
	 * Constructor de la excepcion OutfBoardMargins
	 * @param token
	 * @param row
	 * @param column
	 */
	public OutofBoardMargins(IToken token, int row, int column) {
		super(token, row, column);
	}

	/**
	 * Metodo toString
	 * @return
	 */
	@Override
	public String toString() {
		return super.toString() + "is out of the board margins";
	}
}
