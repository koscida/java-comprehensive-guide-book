import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
public class Board {

	Scanner sc;

	private int width;
	private int height;
	private int snakes;
	private int golds;
	private int doors;

	Point[] playerPositions;
	Point[] snakePositions;
	Point[] goldPositions;
	Point[] doorPositions;

	boolean rich;
	boolean isGameEnded;

	public Board() {
		this.sc = new Scanner(System.in);

		this.rich = false;
		this.isGameEnded = false;

		this.getSettings();
		this.placePieces();
	}

	private void getSettings() {
		System.out.println("Welcome to ZZZSnake!");

		System.out.println("Enter how wide you want the map (10-100) (default 40): ");
		int widthEntry = sc.nextInt();
		this.width = (widthEntry >= 10 && widthEntry <= 40) ? widthEntry : 40;

		System.out.println("Enter how tall you want the map (5-20) (default 10): ");
		int heightEntry = sc.nextInt();
		this.height = (heightEntry >= 5 && heightEntry <= 20) ? heightEntry : 10;

		System.out.println("Enter how many snakes you want (1-5) (default 1):");
		int snakesEntry = sc.nextInt();
		this.snakes = (snakesEntry >= 1 && snakesEntry <= 5) ? snakesEntry : 1;

		System.out.println("Enter how many gold pieces you want (1-5) (default 1):");
		int goldsEntry = sc.nextInt();
		this.golds = (goldsEntry >= 1 && goldsEntry <= 5) ? goldsEntry : 1;

		System.out.println("Enter how many doors you want (1-5) (default 1):");
		int doorsEntry = sc.nextInt();
		this.doors = (doorsEntry >= 1 && doorsEntry <= 5) ? doorsEntry : 1;
	}

	private void placePieces() {
		// randomly place items
		boolean isSamePos;
		Point posPositon;
		int posX;
		int posY;

		// place player
		playerPositions = new Point[5];
		// head
		posX = (int) Math.round(Math.random() * this.width);
		posY = (int) Math.round(Math.random() * this.height);
		this.playerPositions[0] = new Point(posX,posY);
		// tail
		for(int i=1;i<5;i++) {
			int moveVertical = 0;
			int moveHorizontal = 0;

			if((this.playerPositions[i-1].y) < (this.height - 1)) {
				// move down
				moveVertical = (1);
			} else if((this.playerPositions[i-1].x) > 0 ) {
				// move left
				moveHorizontal = (-1);
			} else if(this.playerPositions[i-1].y > 0) {
				// move up
				moveVertical = (-1);
			} else if(this.playerPositions[i-1].x < (this.width - 1)) {
				// move right
				moveHorizontal = (1);
			}

			posX = this.playerPositions[i-1].x + moveHorizontal;
			posY = this.playerPositions[i-1].y + moveVertical;
			this.playerPositions[i] = new Point(posX,posY);
		}

		// place snakes
		this.snakePositions = new Point[this.snakes];
		for(int i=0;i<this.snakes;i++) {
			do {
				posX = (int) Math.round(Math.random() * this.width);
				posY = (int) Math.round(Math.random() * this.height);
				posPositon = new Point( posX, posY );

				isSamePos = false;
				for (Point playerPosition: this.playerPositions) {
					isSamePos = isSamePos || playerPosition.equals(posPositon);
				}
			} while(isSamePos);
			this.snakePositions[i] = new Point( posX, posY );
		}

		// place golds
		this.goldPositions = new Point[this.golds];
		for(int i=0;i<this.golds;i++) {
			do {
				posX = (int) Math.round(Math.random() * this.width);
				posY = (int) Math.round(Math.random() * this.height);
				posPositon = new Point( posX, posY );

				isSamePos = false;
				for (Point playerPosition: this.playerPositions) {
					isSamePos = isSamePos || playerPosition.equals(posPositon);
				}
				for (Point snakePosition: this.snakePositions) {
					isSamePos = isSamePos || snakePosition.equals(posPositon);
				}

			} while(isSamePos);
			this.goldPositions[i] = new Point( posX, posY );
		}

		// place doors
		this.doorPositions = new Point[this.doors];
		for(int i=0;i<this.doors;i++) {
			do {
				posX = (int) Math.round(Math.random() * this.width);
				posY = (int) Math.round(Math.random() * this.height);
				posPositon = new Point(posX, posY);

				isSamePos = false;
				for (Point playerPosition: this.playerPositions) {
					isSamePos = isSamePos || playerPosition.equals(posPositon);
				}
				for (Point snakePosition : this.snakePositions) {
					isSamePos = isSamePos || snakePosition.equals(posPositon);
				}
				for (Point goldPosition : this.goldPositions) {
					isSamePos = isSamePos || goldPosition.equals(posPositon);
				}
			} while (isSamePos);
			this.doorPositions[i] = new Point(posX, posY);
		}
	}

	public void drawBoard() {
		// write instructions
		System.out.println(":: {& = Player}, {S = snake}, {$ = Gold}, {# = Door} ::");

		// Draw grid and symbols
		boolean placedSymbol = false;
		for ( int y = 0; y < this.height; y++ ) {
			for ( int x = 0; x < this.width; x++ ) {
				Point p = new Point(x, y);
				placedSymbol = false;

				for(Point playerPosition: this.playerPositions) {
					if (playerPosition.equals(p)) {
						if(playerPosition.equals(playerPositions[0]))
							System.out.print('&');
						else
							System.out.print('%');
						placedSymbol = true;
					}
				}
				for (Point snakePosition : this.snakePositions) {
					if (!placedSymbol && snakePosition.equals(p)) {
						System.out.print('S');
						placedSymbol = true;
					}
				}
				for (Point goldPosition : this.goldPositions) {
					if (!placedSymbol && goldPosition.equals(p)) {
						System.out.print('$');
						placedSymbol = true;
					}
				}
				for(Point doorPosition: this.doorPositions) {
					if (!placedSymbol && doorPosition.equals(p)) {
						System.out.print('#');
						placedSymbol = true;
					}
				}

				if(!placedSymbol)
					System.out.print( '.' );
			}
			System.out.println();
		}
	}

	public void determineStatus() {
		for(Point doorPosition: doorPositions) {
			if (rich && Arrays.asList(playerPositions).contains(doorPosition)) {
				System.out.println("You won!");
				isGameEnded = true;
			}
		}
		for(Point snakePosition: snakePositions) {
			if (Arrays.asList(playerPositions).contains(snakePosition)) {
				System.out.println("SSSSSS. You were bitten by the snake!");
				isGameEnded = true;
			}
		}
		for(Point goldPosition: goldPositions) {
			if (Arrays.asList(playerPositions).contains(goldPosition)) {
				rich = true;
				goldPosition.setLocation(-1, -1);
			}
		}
	}

	public void movePlayer() {
		System.out.println(":: Your Move! {u = up}, {d = down}, {l = left}, {r = right} ::");
		// Console input and change player position
		switch (sc.next()) {
			case "u" :
				// can move up if head not on edge and if tail is not behind head
				if(this.playerPositions[0].y > 0 && this.playerPositions[1].y != (this.playerPositions[0].y - 1)) {
					System.out.println("Moving up!");
					// can move up
					this.playerPositions[0].y = this.playerPositions[0].y - 1;
					this.moveTailVertically();
					break;
				}
			case "d" :
				// can move down if head not on edge and if tail is not behind head
				if(this.playerPositions[0].y < (this.height-1) && this.playerPositions[1].y != (this.playerPositions[0].y + 1)) {
					System.out.println("Moving down!");
					// can move down
					this.playerPositions[0].y = this.playerPositions[0].y + 1;
					this.moveTailVertically();
					break;
				}
			case "l" :
				// can move left if head not on edge and if tail is not behind head
				if(this.playerPositions[0].x > 0 && this.playerPositions[1].x != (this.playerPositions[0].x - 1)) {
					System.out.println("Moving left!");
					// can move left
					this.playerPositions[0].x = this.playerPositions[0].x - 1;
					this.moveTailHorizontally();
					break;
				}
			case "r" :
				// can move right if head not on edge and if tail is not behind head
				if(this.playerPositions[0].x < (this.width-1) && this.playerPositions[1].x != (this.playerPositions[0].x + 1)) {
					System.out.println("Moving right!");
					// can move right
					this.playerPositions[0].x = this.playerPositions[0].x + 1;
					this.moveTailHorizontally();
					break;
				}
		}
	}

	private void moveTailVertically() {
		for(int i=1;i<5;i++) {
			if(this.playerPositions[i].x == this.playerPositions[i-1].x && this.playerPositions[i].y > this.playerPositions[i-1].y) {
				// if below, move up
				this.playerPositions[i].y = this.playerPositions[i].y - 1;
			}
			else if(this.playerPositions[i].x == this.playerPositions[i-1].x && this.playerPositions[i].y < this.playerPositions[i-1].y) {
				// if on above, move down
				this.playerPositions[i].y = this.playerPositions[i].y + 1;
			}
			else if(this.playerPositions[i].x < this.playerPositions[i-1].x) {
				// if on right side, move right
				this.playerPositions[i].x = this.playerPositions[i].x + 1;
			}
			else if(this.playerPositions[i].x > this.playerPositions[i-1].x) {
				// if on left side, move left
				this.playerPositions[i].x = this.playerPositions[i].x - 1;
			}

		}
	}

	private void moveTailHorizontally() {
		for(int i=1;i<5;i++) {
			if(this.playerPositions[i].y == this.playerPositions[i-1].y && this.playerPositions[i].x > this.playerPositions[i-1].x) {
				// if below, move up
				this.playerPositions[i].x = this.playerPositions[i].x - 1;
			}
//			else if(this.playerPositions[i].x == this.playerPositions[i-1].x && this.playerPositions[i].y < this.playerPositions[i-1].y) {
//				// if on above, move down
//				this.playerPositions[i].y = this.playerPositions[i].y + 1;
//			}
//			else if(this.playerPositions[i].x < this.playerPositions[i-1].x) {
//				// if on right side, move right
//				this.playerPositions[i].x = this.playerPositions[i].x + 1;
//			}
//			else if(this.playerPositions[i].x > this.playerPositions[i-1].x) {
//				// if on left side, move left
//				this.playerPositions[i].x = this.playerPositions[i].x - 1;
//			}

		}
	}

	public void moveSnake() {
		// Snake moves towards the player
		for(Point snakePosition: this.snakePositions) {
			if (this.playerPositions[0].x < snakePosition.x)
				snakePosition.x--;
			else if (this.playerPositions[0].x > snakePosition.x)
				snakePosition.x++;
			if (this.playerPositions[0].y < snakePosition.y)
				snakePosition.y--;
			else if (this.playerPositions[0].y > snakePosition.y)
				snakePosition.y++;
		}
	}

	public boolean getIsGameEnded() {
		return this.isGameEnded;
	}
}
