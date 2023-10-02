import java.awt.*;
import java.util.Scanner;
public class LongSnake {

	public static void main( String[] args ) {

		// initialize a new board
		//  will ask user for board settings and randomly place pieces
		Board board = new Board();

		// begin game instructions
		System.out.println("Begin! Collect gold and avoid the snakes!");

		// play game!
		while ( true ) {

			// Draw Board
			board.drawBoard();

			// Determine status
			board.determineStatus();
			if(board.getIsGameEnded())
				break;

			// Get player move
			board.movePlayer();

			// Move snake
			board.moveSnake();

		} // end while
	}

}
