package ads.p4.game;

import java.io.*;

import ads.p4.InvalidGame.exceptions.InvalidGame;

/**
 * Clase GameLoader
 * @author Carlos Riveira -> carlos.riveira.ramos@estudiante.uam.es
 * @author Santiago de Prada -> santiago.prada@estudiante.uam.es
 */

public class GameLoader {

		/**
		 * Metodo que permite cargar el juego desde un fichero
		 * @param s
		 * @return
		 * @throws NumberFormatException
		 * @throws IOException
		 * @throws InvalidGame
		 */
		public static Game load(String s) throws NumberFormatException ,IOException, InvalidGame {
			File archivo = new File (s);
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			int size = Integer.parseInt(br.readLine()) ;
			int turns = Integer.parseInt(br.readLine());
			int wallTokens = Integer.parseInt(br.readLine());
			
			Game game = new Game(turns, size, wallTokens);
			
			br.close();
			return game;
		}
		
}
