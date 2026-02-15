package ads.p4.game;
import java.util.List;

import ads.p4.ForbiddenToken.exceptions.*;
/**
 * Interfaz IBoard
 * @author Profesores de la asignatura de Analisis y Diseño de Software
 */

public interface IBoard {
	public ICell getCell(int row, int column);
	public int getRows();
	public int getColumns();
	public String toString();
	public void addToken(int row, int column, IToken f) throws ForbiddenToken;
	public List<ICell> getNeighbors(int row, int column);
	public ICell getSymmetric(ICell c);
}
