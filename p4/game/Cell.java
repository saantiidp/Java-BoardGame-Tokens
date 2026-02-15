package ads.p4.game;

/**
 * Clase Cell
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class Cell implements ICell {
	private int column;
	private int row;
	private IToken token;
	
	/**
	 * Constructor de la clase Cell
	 * @param row
	 * @param column
	 */
	public Cell(int row, int column) {
		this.column = column;
		this.row = row;
	}
	
	/**
	 * Metodo que devuelve la columna
	 * @return
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Metodo que cambia la columna
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Metodo que devuelve la fila
	 * @return
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Metodo que permite cambiar la fila
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Metodo que devuelve el Token
	 * @return
	 */
	public IToken getToken() {
		return token;
	}
	
	/**
	 * Metodo que permite cambiar el Token
	 * @param token
	 */
	public void setToken(IToken token) {
		this.token = token;
	}
	
	/**
	 * Metodo que comprueba si una celda es vecina o no
	 * @param c
	 */
	@Override
	public boolean isNeighbor(ICell c) {
		if (this.column == c.getColumn() && this.row == c.getRow() ) {
			return false;
		}
		if ( (c.getRow() <= this.row + 1 && c.getRow() >= this.row -1) && (c.getColumn() <= this.column + 1 && c.getColumn() >= this.column - 1) ) {
			return true;
		}
		return false;
	}
}
