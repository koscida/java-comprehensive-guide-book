import java.util.*;
import java.awt.Point;
public class ZZZZZnake {
	public static void main( String[] args ) {

		// print set-up instructions
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to ZZZSnake!");
		System.out.println("Enter how wide you want the map (10-100) (default 40): ");
		int widthEntry = sc.nextInt();
		int width = (widthEntry >= 10 && widthEntry <= 40) ? widthEntry : 40;

		System.out.println("Enter how tall you want the map (5-20) (default 10): ");
		int heightEntry = sc.nextInt();
		int height = (heightEntry >= 5 && heightEntry <= 20) ? heightEntry : 10;

		System.out.println("Enter how many snakes you want (1-5) (default 1):");
		int snakesEntry = sc.nextInt();
		int snakes = (snakesEntry >= 1 && snakesEntry <= 5) ? snakesEntry : 1;

		System.out.println("Enter how many gold pieces you want (1-5) (default 1):");
		int goldsEntry = sc.nextInt();
		int golds = (goldsEntry >= 1 && goldsEntry <= 5) ? goldsEntry : 1;


		// randomly place items
		boolean isSamePos;
		Point posPositon;
		int posX;
		int posY;

		// place player
		int playerX = (int) Math.round(Math.random() * width);
		int playerY = (int) Math.round(Math.random() * height);
		Point playerPosition = new Point(playerX,playerY);

		// place snakes
		Point snakePositions[] = new Point[snakes];
		for(int i=0;i<snakes;i++) {
			do {
				posX = (int) Math.round(Math.random() * width);
				posY = (int) Math.round(Math.random() * height);

				posPositon = new Point( posX, posY );
				isSamePos = playerPosition.equals( posPositon );
			} while(isSamePos);
			snakePositions[i] = new Point( posX, posY );
		}

		// place golds
		Point goldPositions[] = new Point[golds];
		for(int i=0;i<golds;i++) {
			do {
				posX = (int) Math.round(Math.random() * width);
				posY = (int) Math.round(Math.random() * height);

				posPositon = new Point( posX, posY );
				isSamePos = playerPosition.equals( posPositon );
				for (Point snakePosition: snakePositions) {
					isSamePos = isSamePos || snakePosition.equals(posPositon);
				}
				
			} while(isSamePos);
			goldPositions[i] = new Point( posX, posY );
		}

		// place doors
		do {
			posX = (int) Math.round(Math.random() * width);
			posY = (int) Math.round(Math.random() * height);

			posPositon = new Point( posX, posY );
			isSamePos = playerPosition.equals( posPositon );
			for (Point snakePosition: snakePositions) {
				isSamePos = isSamePos || snakePosition.equals(posPositon);
			}
			for (Point goldPosition: goldPositions) {
				isSamePos = isSamePos || goldPosition.equals(posPositon);
			}
		} while(isSamePos);
		Point doorPosition = new Point( posX, posY );




		// begin game instructions
		System.out.println("Begin! Collect gold and avoid the snakes!");

		// game vars
		boolean rich = false;

		// play game!
		while ( true ) {
			// write instructions
			System.out.println(":: {u = up}, {d = down}, {l = left}, {r = right} -- {& = Player}, {S = snake}, {$ = Gold}, {# = Door} ::");

			// Draw grid and symbols
			boolean placedSymbol = false;
			for ( int y = 0; y < height; y++ ) {
				for ( int x = 0; x < width; x++ ) {
					Point p = new Point(x, y);
					placedSymbol = false;

					if (playerPosition.equals(p)) {
						System.out.print('&');
						placedSymbol = true;
					}
					for (Point snakePosition : snakePositions) {
						if (!placedSymbol && snakePosition.equals(p)) {
							System.out.print('S');
							placedSymbol = true;
						}
					}
					for (Point goldPosition : goldPositions) {
						if (!placedSymbol && goldPosition.equals(p)) {
							System.out.print('$');
							placedSymbol = true;
						}
					}
					if (!placedSymbol && doorPosition.equals(p)) {
						System.out.print('#');
						placedSymbol = true;
					}

					if(!placedSymbol)
						System.out.print( '.' );
				}
				System.out.println();
			}

			// Determine status
			if ( rich && playerPosition.equals( doorPosition ) ) {
				System.out.println("You won!");
				return;
			}
			for(Point snakePosition: snakePositions) {
				if (playerPosition.equals(snakePosition)) {
					System.out.println("SSSSSS. You were bitten by the snake!");
					return;
				}
			}
			for(Point goldPosition: goldPositions) {
				if (playerPosition.equals(goldPosition)) {
					rich = true;
					goldPosition.setLocation(-1, -1);
				}
			}

			// Console input and change player position
			// Keep playing field between 0/0.. 39/9
			switch ( new java.util.Scanner( System.in ).next() ) {
				case "u" /* p */ -> playerPosition.y = Math.max( 0, playerPosition.y - 1 );
				case "d" /* own */ -> playerPosition.y = Math.min( 9, playerPosition.y + 1 );
				case "l" /* eft */ -> playerPosition.x = Math.max( 0, playerPosition.x - 1 );
				case "r" /* ight */ -> playerPosition.x = Math.min( 39, playerPosition.x + 1 );
			}

			// Snake moves towards the player
			for(Point snakePosition: snakePositions) {
				if (playerPosition.x < snakePosition.x)
					snakePosition.x--;
				else if (playerPosition.x > snakePosition.x)
					snakePosition.x++;
				if (playerPosition.y < snakePosition.y)
					snakePosition.y--;
				else if (playerPosition.y > snakePosition.y)
					snakePosition.y++;
			}
		} // end while
	}
}