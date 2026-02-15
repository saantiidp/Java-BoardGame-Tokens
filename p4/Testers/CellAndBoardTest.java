package ads.p4.Testers;
import ads.p4.ForbiddenToken.exceptions.*;
import ads.p4.game.*;
import ads.p4.game.tokens.*;


public class CellAndBoardTest {
	
	public static void main(String args[]) {		
		Player p1 = new Player();
		Player p2 = new Player();
		IBoard board = new BoardGame(10, p1, p2);
		System.out.println(board);
		
		try {
		
			board.addToken(0, 0, new FixedToken(p1));
			System.out.println(board);
			board.addToken(0, 0, new FixedToken(p2));
		
		} catch (ForbiddenToken e) {
		
			System.out.println(e);
		
		} 
		System.out.println(board);

	}

		
}


