import java.util.Scanner;

public class CodeRunner {
	public int TILE_MULTIPLIER;
	public int POWER_SPAWN_VALUE;
	public int moves = 0;
	public volatile boolean runGame;
	public Board board;

	public void setRunGameValues() {
		Scanner console = new Scanner(System.in);
		moves = 0;
		System.out.print("Multiplier: ");
		TILE_MULTIPLIER = console.nextInt();
		System.out.print("Power Spawn Value: ");
		POWER_SPAWN_VALUE = console.nextInt();
		System.out.print("Board Size: ");
		board = new Board(console.nextInt());
	}

	//startGame() is run at the beginning of the game and randomly generates two tiles on the board
	//	based on the size of the board by calling createRandomTile() in a for() loop
	public void startGame() {
		setRunGameValues();
		this.runGame = true;
		for(int i = 0; i < board.getSize()/2; i++) {
			createRandomTile();
		}
		System.out.println(board);
	}

	public void excecuteTurn(int direction) {
		if(!gameOverTest()) {
			board.moveContents(direction);
			if(testTileMovement()) {
				createRandomTile();
				moves++;
			}
			System.out.println(board + "\n" + moves);
		} else {
			runGame = false;
		}
	}

	//createRandomTile() randomly generates a tile in one of the open spaces on the board
	public void createRandomTile() {
		int x = (int)(Math.random() * board.getSize());
		int y = (int)(Math.random() * board.getSize());
		//problems happen when the board is full, it can't find an empty tile!!!
		
			while(board.getSpace(x,y) != null) {
				x = (int)(Math.random() * board.getSize());
				y = (int)(Math.random() * board.getSize());
			}
		
			System.out.println("create");
		board.setSpace(new Tile(TILE_MULTIPLIER,POWER_SPAWN_VALUE,board,x,y),x,y);
	}

	//testTileMovement() returns a boolean on whether or not at least one Tile in the Board has moved after a move
	public boolean testTileMovement() {
		boolean hasMoved = false;
		for(int i = 0; i < board.getSize(); i++) {
			for(int j = 0; j < board.getSize(); j++) {
				try {
					Tile current = board.getSpace(i, j);
					if(current.getMoved()) {
						hasMoved = true;
					}
				} catch(NullPointerException e) {}
			}
		}
		return hasMoved;
	}

	//gameOverTest() returns a boolean based on whether or not all of the spaces in the Board are filled up
	public boolean gameOverTest() {
		boolean filled = board.isFilled();
		if(filled) {
			for(int i = 0; i < 4; i++) {
				Board current = board.clone();

				current.moveContents(i);

				if(!current.isFilled()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	//getMove() returns what direction the board will scan in and move all of the Tiles in; be it through a System.in scanner
	//	or a randomly generated number, AI, ect.
	public int getMove(Scanner console) {
		int direction = 0;
		direction = console.nextInt();
		return direction;
	}

	//cheatGame() is a fun method I added for testing the code, that fills the entire Board with Tiles
	public void cheatGame() {
		for(int i = 0; i < board.getSize(); i++) {
			for(int j = 0; j < board.getSize(); j++) {
				Tile current = new Tile(TILE_MULTIPLIER,POWER_SPAWN_VALUE,board,i,j);
			}
		}
	}
}
