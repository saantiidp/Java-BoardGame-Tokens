package ads.p4.ForbiddenToken.exceptions;

import ads.p4.game.IToken;

/**
 * Excepcion Nooverwrittingcell
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

@SuppressWarnings("serial")
public class Nooverwrittringcell extends ForbiddenToken {

	/**
	 * Constructor de la excepcion Nooverwrittingcell
	 * @param token
	 * @param row
	 * @param column
	 */
	public Nooverwrittringcell(IToken token, int row, int column) {
		super(token, row, column);
	}

	/**
	 * Metodo toString
	 * @return
	 */
	@Override
	public String toString() {
		return super.toString() + "the cell contains a token that cannot be overwritten";
	}

}
