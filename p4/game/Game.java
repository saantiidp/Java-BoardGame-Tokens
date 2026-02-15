package ads.p4.game;

/**
 * Clase Game
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import ads.p4.ForbiddenToken.exceptions.*;
import ads.p4.InvalidGame.exceptions.InvalidGame;
import ads.p4.InvalidGame.exceptions.MinimunSize;
import ads.p4.InvalidGame.exceptions.WallTokensNumber;
import ads.p4.game.tokens.*;

/**
 * Clase Game
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class Game {
	private BoardGame tablero;
	
	private Player p1 = new Player();
	private Player p2 = new Player();
	
	private int turnos;
	
	
	
	/**
	 * Constructor de la clase Game
	 * @param turns
	 * @param size
	 * @param WallTokens
	 * @throws InvalidGame
	 */
	public Game(int turns, int size, int WallTokens) throws InvalidGame{
		if(size < 5) {
			throw new MinimunSize(size);
		}
		
		if(WallTokens >= size - (WallTokens + 1)) {
			throw new WallTokensNumber(size);
		}
		
		tablero = new BoardGame(size, p1, p2, WallTokens);
		turnos = turns;
		
		
		
	}
	
	/**
	 * Metodo que desarrolla toda la jugabilidad de la practica
	 * @throws InvalidGame
	 * @throws IOException
	 */
	public void play() throws InvalidGame, IOException {
		boolean seguirJugando = true;
		int noQuedanMovimientos = 0;
		int turnos = 0, turno, murallas = 0;
		float puntosP1=1, puntosP2=1;
		int fP1 = 0, cP1 = 0, fP2 = tablero.getSize() -1 , cP2 = tablero.getSize() -1 ;
		
		//mientras seguirJugando == true ...
		do{
			turnos+=1;
			murallas ++;
			
			if (murallas == 3) {
				
				tablero.changeWallTokensPosition();
				
			}
			
			System.out.println("Starting turn " + turnos + "\n");
			turno = 1;
			
			while (turno <= 2) {
				System.out.println(this.tablero);
				System.out.println("1: points " + puntosP1 + " last cell: " + fP1 + "," + cP1 + "\n");
				System.out.println("2: points " + puntosP2 + " last cell: " + fP2 + "," + cP2 + "\n" );
				
				System.out.println("Turn " + turnos);
			
				//turno es o jugador 1 o jugador 2 //No confundir con turnos!!!!
			
				if (turno == 1 && (jugadorPuedeSeguirJugando(p1, fP1, cP1) == false) || turno == 2 && (jugadorPuedeSeguirJugando(p2, fP2, cP2) == false) ) {
					System.out.println("Player " + turno + "no puedes realizar ning�n movimiento\n"	);
					noQuedanMovimientos ++;
					turno++;
					
					if (noQuedanMovimientos == 2) {
						return;
					}
	
				}
			
				else {
					noQuedanMovimientos = 0;
					
					System.out.println("Player " + turno + " enter the coordinates of the new token:\n");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String string = br.readLine();
				
					//Si introduce la cadena END termina el juego
				
					if (string.equals("END")) {
						return;
					
					}
					
					
					// si no, seguimos jugando
				
					else {
						String[] posiciones  = string.split(",");
						
						try {
							if (turno == 1) {
								int auxfP1 = Integer.parseInt(posiciones[0]);
								int auxcP1 = Integer.parseInt(posiciones[1]);
								
								tablero.addPlayerToken(auxfP1, auxcP1, createToken(p1));
								fP1 = auxfP1;
								cP1 = auxcP1;
								puntosP1 += p1.getlasttoken().getValor();
								
							}
						
							else {
								int auxfP2 = Integer.parseInt(posiciones[0]);
								int auxcP2 = Integer.parseInt(posiciones[1]);
								tablero.addPlayerToken(auxfP2, auxcP2, createToken(p2));
								fP2 = auxfP2;
								cP2 = auxcP2;
								puntosP2 += p2.getlasttoken().getValor();
							}
						
							turno++;
						} catch(ForbiddenToken excp) {
							System.out.println(excp);
						}
					}
				
				}
			}
			if (turnos == this.turnos ) seguirJugando = false;
			
		}while(seguirJugando);
		
		
	}
	
	/**
	 * Metodo que comprueba si es posible continuar el juego o no
	 * @param p
	 * @param f
	 * @param c
	 * @return
	 */
	private boolean jugadorPuedeSeguirJugando(Player p, int f, int c) {
		
		List<ICell> celdasVecinas  = this.tablero.getNeighbors(f, c);
		for(ICell celda: celdasVecinas) {
			if (celda.getToken() == null || celda.getToken().canBeOverwritten()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Metodo que crea los Tokens a colocar por el jugador
	 * @param p
	 * @return
	 */
	public static PlayerToken createToken(Player p) {
		int tipoToken;
		
		tipoToken = (int) (Math.random()*10)+1;
		
		if (tipoToken < 2) return new MultiplierToken(p);
			
		else if (tipoToken < 4) return new FixedToken(p);
			
		else return new NormalToken(p);
	}

}
