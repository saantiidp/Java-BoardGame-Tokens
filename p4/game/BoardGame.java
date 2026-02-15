package ads.p4.game;
import java.util.ArrayList;
import java.util.List;

import ads.p4.ForbiddenToken.exceptions.*;
import ads.p4.game.tokens.*;
/**
 * Clase BoardGame
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class BoardGame implements IBoard{
	private final int size;
	
	private ArrayList<ICell> cells = new ArrayList<>();
	
	private Player p1;
	private Player p2;
	
	private EnhancerToken enToken1;
	private EnhancerToken enToken2;
	
	private ArrayList<ICell[]> wallTokensPairsPosition = new ArrayList<>();
	
	/**
	 * Constructor de la clase BoardGame
	 * @param size
	 * @param p1
	 * @param p2
	 * @param nWallPairs
	 */
	public BoardGame(int size, Player p1, Player p2, int nWallPairs) {
		
		this.size = size;
		this.p1 = p1;
		this.p2 = p2;
		
		
		PlayerToken N1 = new NormalToken(p1);
		PlayerToken N2 = new NormalToken(p2);
		
		p1.setToken(N1);
		p2.setToken(N2);
		
		createCells();
		
		getCell(0,0).setToken(N1);
		getCell(size-1, size-1).setToken(N2);
		
		createWallTokens(nWallPairs);
		
		setInitialEnhancerTokenPositions();
	}
	
	/**
	 * Constructor simplificado de la clase BoardGame
	 * @param size
	 * @param p1
	 * @param p2
	 */
	public BoardGame(int size, Player p1, Player p2) {
		this(size, p1, p2, 0);
	}
	
	/**
	 * Metodo que retorna el tamaño
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Metodo que retorna el jugador 1
	 * @return
	 */
	public Player getP1() {
		return p1;
	}

	/**
	 * Metodo que cambia al jugador 1
	 * @param p1
	 */
	public void setP1(Player p1) {
		this.p1 = p1;
	}

	/**
	 * Metodo que retorna el jugador 2
	 * @return
	 */
	public Player getP2() {
		return p2;
	}

	/**
	 * Metodo que cambia al jugador 2
	 * @param p2
	 */
	public void setP2(Player p2) {
		this.p2 = p2;
	}
	
	/**
	 * Metodo que devuelve el ArrayList de ICells
	 * @return
	 */
	public ArrayList<ICell> getCells() {
		return this.cells;
	}
	
	/**
	 * Metodo que permite cambiar el array de ICells
	 * @param cells
	 */
	public void setCells(ArrayList<ICell> cells) {
		this.cells = cells;
	}
	
	/**
	 * Metodo que devuelve una celda en particular
	 */
	@Override
	public ICell getCell(int row, int column) {
		for(ICell c: this.cells) {
			if(c.getColumn() == column && c.getRow() == row) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que devuelve el numero de filas
	 * @return
	 */
	@Override
	public int getRows() {
		return size;
	}

	/**
	 * Metodo que devuelve el numero de columnas
	 * @return
	 */
	@Override
	public int getColumns() {
		return size;
	}

	/**
	 * Metodo que permite añadir Tokens al tablero
	 * @param row
	 * @pram column
	 * @param f
	 */
	@Override public void addToken(int row, int column, IToken f) throws ForbiddenToken {
		// TODO Auto-generated method stub
		
		if(row > size || column > size || column < 0 || row < 0) {
			throw new OutofBoardMargins(f,row, column);
		}
		
		else if(getCell(row, column).getToken() != null && !getCell(row, column).getToken().canBeOverwritten()) {
			throw new Nooverwrittringcell(f,row, column);
		}
		
		else {
			
			getCell(row,column).setToken(f);
			
		}
	}
	
	/**
	 * Metodo que devuelve las celdas vecinas a otra dada
	 * @param row
	 * @param column
	 */
	@Override
	public List<ICell> getNeighbors(int row, int column) {
		ICell cell = this.getCell(row, column);
		List<ICell> cellsAux = new ArrayList<>();
		for(ICell c: this.cells) {
			if (c.isNeighbor(cell) == true) {
				cellsAux.add(c);
			}
		}
		return cellsAux;
	}
	/**
	 * Metodo que devuelve la celda simetrica a otra
	 * @return c
	 */
	@Override
	public ICell getSymmetric(ICell c) {
		int columnAux = this.size - 1 - c.getColumn();
		int rowAux = this.size - 1 - c.getRow();
		
		return this.getCell(rowAux, columnAux);
	}
	
	/**
	 * Metodo ToString
	 * @return 
	 */
	@Override
	public String toString() {
		String estadoActual = "";
		int n_columns = getColumns();
		int n_rows = getRows();
		
		for(int i=0; i < n_rows; i++) {
			
			for(int j=0; j < n_columns; j++) {
				ICell celda = getCell(i, j);
				IToken ficha = celda.getToken();
				if (ficha == null) {
					estadoActual += "--";
				}
				else {
					estadoActual += ficha.toString();
				}
				estadoActual += " ";
					
			}
			estadoActual += "\n";
		}
		return estadoActual;
	}
	
	/**
	 * Metodo que añade un token de player al tablero
	 * @param row
	 * @param column
	 * @param pToken
	 * @throws ForbiddenToken
	 */
	public void addPlayerToken(int row, int column, PlayerToken pToken) throws ForbiddenToken{
		if(row > size || column > size || row <0 || column<0) {
			throw new OutofBoardMargins(pToken,row, column);
		}
		
		else if(getCell(row, column).getToken() != null && !getCell(row, column).getToken().canBeOverwritten()) {
			throw new Nooverwrittringcell(pToken,row, column);
		}
		else {
			
		
		List<ICell> neighboorCells = getNeighbors(row, column);
		boolean esVecina = false;
			for(ICell cell: neighboorCells) {
				if (pToken.getPlayer().getlasttoken().equals(cell.getToken()))
					esVecina = true;
			}
			if(!esVecina) throw new Noneighborhoodcell(pToken, row, column);
		
			if(checkEnhancerToken(row,column) == true) {
			
				enhancerExecution(row, column, pToken.getPlayer());
			}
			else { 
				addToken(row, column, pToken);
				pToken.getPlayer().setToken(pToken);
			}
			
		}
	}
	
	/**
	 * Metodo que investiga si una celda es enhancer o no
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean checkEnhancerToken(int row, int column) {
		if(getCell(row, column).getToken() != null && getCell(row, column).getToken().toString() == "EN") return true;
		
		return false;
	}
	
	/**
	 * Metodo que ejecuta la funcioalidad de los Tokens enhancer
	 * @param row
	 * @param column
	 * @param jugador
	 */
	public void enhancerExecution(int row, int column, Player jugador) {
		int i;
		NormalToken n1;
		IToken en = getCell(row,column).getToken();
		getCell(row,column).setToken(null);
		///Puede ocurrir 3 opciones, las 3 con la misma probabilidad///
		
		
		int rand = (int) (Math.random()*(2));
		try {
			///Se colocan fichas normales sobre toda la columna///
			if (rand == 0) {
				
				for(i = row; i < size; i++ ) {
					if(getCell(row, column).getToken() == null || getCell(row, column).getToken().canBeOverwritten()) {
						n1 = new NormalToken(jugador);
						addToken(i, column, n1);
						jugador.setToken(n1);
					}
					else break;
				}
			}
		
			///se colocan fichas normales sobre toda la fila
			else if (rand == 1) {
				for(i = column; i < size; i++ ) {
					if(getCell(row, column).getToken() == null || getCell(row, column).getToken().canBeOverwritten()) {
						n1 = new NormalToken(jugador);
						addToken(row,i, n1);
						jugador.setToken(n1);
					}
					else break;
				}
			}
		
			//se colocan fichas normales sobre todas las celdas vecinas 
			else {
				List<ICell> vecinas = getNeighbors(row, column);
				for (ICell vecina : vecinas)
					if(getCell(row, column).getToken() == null || getCell(row, column).getToken().canBeOverwritten()) {
						n1 = new NormalToken(jugador);
						addToken(vecina.getRow(),vecina.getColumn(), n1);
						jugador.setToken(n1);
					}
					else break;
			}
		
			setRandomCellPositionToken(en);
		} catch (ForbiddenToken excp) {
			System.out.println(excp);
		}
	}
	
	/**
	 * Metodo que coloca un Token en una celda aleatoria
	 * @param en
	 */
	public void setRandomCellPositionToken(IToken en) {
		int randomF, randomC;
		do{
			randomF = (int) (Math.random()*(size-1));
			randomC = (int) (Math.random()*(size-1));
		}while(getCell(randomF, randomC).getToken() != null);
		try {
			addToken(randomF, randomC, en);
		} catch (ForbiddenToken excp) {
			System.out.println(excp);
		}
			
	}
	
	/**
	 * Metodo que cambia de posicion las fichas wall
	 * @param fichaMuralla
	 */
	public void wallTokenChangePosition(WallToken fichaMuralla) {
		int fMuralla, cMuralla;
		do{
			fMuralla = (int) (Math.random()*size);
			cMuralla = (int) (Math.random()*size);
		
		}while(getCell(fMuralla, cMuralla).getToken() != null);
		try {
			addToken(fMuralla, cMuralla, fichaMuralla);
		} catch (ForbiddenToken excp) {
			System.out.println(excp);
		}
	}
	
	/**
	 * Metodo que permite crear las celdas
	 */
	private void createCells() {
		int i,j;
		for(i=0; i <= this.size; i++) {
			for(j=0; j <= this.size; j++) {
				cells.add(new Cell(i,j));
			}
		}
	}
	
	/**
	 * Metodo que permite crear las fichas wall
	 * @param pairs
	 */
	private void createWallTokens(int pairs) {
		int i, randomF, randomC;
		
		ICell[] parejas = new ICell[2];
		
		for(i=0; i < pairs; i++) {
			do{
				randomF = (int) (Math.random()*(size-1));
				randomC = (int) (Math.random()*(size-1));
			}while((getCell(randomF, randomC).getToken() != null)&& (getCell(randomF, randomC).getToken().canBeOverwritten() != true)||((getCell(size -1 -randomF,size -1- randomC).getToken() != null)&& (getCell(size -1 -randomF ,size -1 - randomC).getToken().canBeOverwritten() != true)) || (randomF== 0 && randomC == 0) || (randomF == size-1 && randomC == size-1));
			
			parejas[0] = getCell(randomF, randomC);
			getCell(randomF,randomC).setToken(new WallToken());
			//lo mismo con lo inversos
			parejas[1] = getCell(size - 1 -randomF, size - 1 - randomC);
			getCell(size -1 -randomF,size -1 - randomC).setToken(new WallToken());
			
			wallTokensPairsPosition.add(parejas);
		}
	}
	
	/**
	 * Metodo para cambiar las fichas wall de posicion
	 */
	public void changeWallTokensPosition() {
		int randomF, randomC;
		WallToken W1, W2;
		for(ICell[] parejas: wallTokensPairsPosition) {
			W1 = (WallToken)parejas[0].getToken();
			W2 = (WallToken)parejas[1].getToken();
			
			do{
				randomF = (int) (Math.random()*(size-1));
				randomC = (int) (Math.random()*(size-1));
			}while((getCell(randomF, randomC).getToken() != null && (getCell(randomF, randomC).getToken().canBeOverwritten()) != true)|| (getCell(size -1 - randomF, size -1 -randomC).getToken() != null && getCell(size -1 -randomF ,size -1 - randomC).getToken().canBeOverwritten() != true));
		
			getCell(randomF,randomC).setToken(W1);
			getCell(size -1- randomF,size -1 -randomC).setToken(W2);
			parejas[0].setToken(null);
			parejas[1].setToken(null);
			parejas[0]= getCell(randomF,randomC);
			parejas[1]= getCell(size -1 -randomF,size -1 -randomC);
			
		}
	}
	
	/**
	 * Metodo que crea y coloca las fichas Enhancer
	 */
	private void setInitialEnhancerTokenPositions() {
		int randomF, randomC;
		do{
			randomF = (int) (Math.random()*(size-1));
			randomC = (int) (Math.random()*(size-1));
		}while(((getCell(randomF, randomC).getToken() != null) && (getCell(randomF, randomC).getToken().canBeOverwritten() != true )) || ((getCell(randomF, randomC).getToken() != null) && (getCell(size -1 -randomF, size -1 - randomC).getToken().canBeOverwritten() != true))  || (randomF == 0 && randomC == 0) || (randomF == size-1 && randomC == size-1));
		enToken1 = new EnhancerToken();
		getCell(randomF, randomC).setToken(enToken1);
		
		if (randomF != size-1-randomF || randomC != size-1-randomC) {
			enToken2 = new EnhancerToken();
			getCell(size-1-randomF, size-1-randomC).setToken(enToken2);
		}
	}
	
	
}
