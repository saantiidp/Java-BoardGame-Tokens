package ads.p4.Testers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ads.p4.InvalidGame.exceptions.InvalidGame;
import ads.p4.game.Game;


public class GameMain {

	public static void main(String args[])  {
		

		try {			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Indicate the size of the board");
			int size = Integer.parseInt(br.readLine());
	
			System.out.println("Indicate the maximum turns");
			int turns = Integer.parseInt(br.readLine());
	
			System.out.println("Indicate the number of wall tokens per user");
			int wallTokens = Integer.parseInt(br.readLine());
	
			Game j = new Game(turns, size, wallTokens);
			j.play();
		} catch (NumberFormatException nfe) {
			System.out.println("Incorrect format");
		} catch (InvalidGame e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("Error reading the values");
		}
		
	}

}
