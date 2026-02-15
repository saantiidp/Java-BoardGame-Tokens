package ads.p4.ForbiddenToken.exceptions;

import ads.p4.game.*;

/**
 * Excepcion ForbiddenToken
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public abstract class ForbiddenToken extends Exception {
	private IToken token;
	private int f;
	private int c;

	/**
	 * Constructor de la excepcion ForbiddenToken
	 * @param token
	 * @param row
	 * @param column
	 */
	public ForbiddenToken(IToken token, int row, int column) {
		this.token = token;
		c = column;
		f = row;
	}

	/**
	 * Metodo que devuelve el Token
	 * @return
	 */
	public IToken getToken() {
		return token;
	}

	/**
	 * Metodo que devuelve la fila
	 * @return
	 */
	public int getRow() {
		return f;
	}

	/**
	 * Metodo que devuelve la columna
	 * @return
	 */
	public int getColumn() {
		return c;
	}

	/**
	 * Metodo toString
	 * @return
	 */
	@Override
	public String toString() {
		return "Error. the token " + token + " can not be placed in position " + f + " ," + c + " because: ";
	}

}
