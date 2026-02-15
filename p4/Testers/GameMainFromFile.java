package ads.p4.Testers;

import java.io.IOException;

import ads.p4.InvalidGame.exceptions.InvalidGame;
import ads.p4.game.Game;
import ads.p4.game.GameLoader;

public class GameMainFromFile {
	
	public static void main(String args[]) throws NumberFormatException, IOException, InvalidGame {

		Game g = GameLoader.load(args[0]);
		if (g != null) 
			g.play();

	}

}
