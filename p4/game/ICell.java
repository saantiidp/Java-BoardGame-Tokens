package ads.p4.game;

/**
 * Interfaz ICell
 * @author Profesores de la asignatura de Analisis y Diseño de Software
 */

public interface ICell {
	public int getRow();
	public int getColumn();
	public IToken getToken();
	public void setToken(IToken f);
	public boolean isNeighbor(ICell c);
}
