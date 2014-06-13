
public class Board implements Cloneable {
	//BOARD_SIZE is the variable used to create both the X and Y sizes
	//	of playBoard
	private final int BOARD_SIZE;

	//playBoard is a two dimensional Array that holds all of the Tiles that will be seen
	//	and manipulated by the players
	public Tile[][] playBoard;

	//Board() is a creation method that imports an int that is used for the X and Y sizes of playBoard
	public Board(int size) {
		BOARD_SIZE = size;
		playBoard = new Tile[BOARD_SIZE][BOARD_SIZE];
	}

	//moveContents() is a method that imports the direction, specified by the player, as an int of 0, 1, 2, or 3
	//	and based on that scans the board and has each Tile in the Array playBoard move in the specified direction
	public void moveContents(int direction) {
		for(int i = 0; i < BOARD_SIZE; i ++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				Tile current = null;
				switch(direction) {
				case 0:
					current = playBoard[i][j];
					break;
				case 1:
					current = playBoard[BOARD_SIZE - 1 - i][j];
					break;
				case 2:
					current = playBoard[j][i];
					break;
				case 3:
					current = playBoard[j][BOARD_SIZE - 1 - i];
				}
				if(current != null) {
					current.act(direction);
				}
			}
		}
	}

	//clone() is a method that creates and returns a new Board that is identical to itself
	public Board clone() {
		Board copy = new Board(this.BOARD_SIZE);
		for(int i = 0; i < copy.BOARD_SIZE; i++) {
			for(int j = 0; j < copy.BOARD_SIZE; j++) {
				Tile current = this.getSpace(i,j);
				if(current != null) {
					Tile tileCopy = new Tile(current.getMultiplier(),current.getPower(),copy,current.x,current.y);
				}
			}
		}
		return copy;
	}

	//tests if a board can move in a given direction
	public boolean canMove(int direction) {
		Board clone = this.clone();
		clone.moveContents(direction);
		return !equals(clone);
	}

	//return if the board can move in any direction
	public boolean canMove(){
		for(int i = 0; i < 4; i++){
			if(canMove(i)){
				return true;
			}
		}
		return false;
	}

	//testTileMovement() returns a boolean on whether or not at least one Tile in the Board has moved in its last move
	public boolean testTileMovement() {
		boolean hasMoved = false;
		for(int i = 0; i < getSize(); i++) {
			for(int j = 0; j < getSize(); j++) {
				Tile current = getSpace(i, j);
				if(current != null && current.getMoved()) {
					hasMoved = true;
				}
			}
		}
		return hasMoved;
	}

	public void createRandomTile(int tileMultiplier, int powerSpanValue) {
		int x = (int)(Math.random() * getSize());
		int y = (int)(Math.random() * getSize());
		//problems happen when the board is full, it can't find an empty tile!!!	
		if(isFilled())
			return;
		while(getSpace(x,y) != null) {
			x = (int)(Math.random() * getSize());
			y = (int)(Math.random() * getSize());
		}
		this.setSpace(new Tile(tileMultiplier,powerSpanValue,this,x,y),x, y) ;
	}
	
	//tests if the values of every tile in the board are the same
	public boolean equals(Board other) {
		boolean equals = true;
		if(other.BOARD_SIZE == this.BOARD_SIZE) {
			for(int i = 0; i < other.BOARD_SIZE; i++) {
				for(int j = 0; j < other.BOARD_SIZE; j++) {
					Tile currentOther = other.getSpace(i, j);
					Tile currentThis = this.getSpace(i, j);
					if(currentOther == null || currentThis == null) {
						if(currentOther != currentThis) {
							equals = false;
						}
					} else if(currentOther.getValue() != currentThis.getValue()) {
						equals = false;
					}
				}
			}
		} else {
			equals = false;
		}
		return equals;
	}

	//returns whether or not every space in the playBoard in the board is full
	public boolean isFilled() {
		boolean filled = true;
		for(int i = 0; i < getSize(); i++) {
			for(int j = 0; j < getSize(); j++) {
				Tile current = getSpace(i, j);
				if(current == null) {
					filled = false;
				}
			}
		}
		return filled;
	}

	//setSpace() sets the X,Y specified to the parameter Tile
	public void setSpace(Tile tile, int x, int y) {
		playBoard[x][y] = tile;
	}

	//getSpace() returns the Tile in the specified X,Y coordinates
	public Tile getSpace(int x, int y) {
		return playBoard[x][y];
	}

	//getSize() returns the private int BOARD_SIZE
	public int getSize() {
		return BOARD_SIZE;
	}

	//getArray() returns the Tile[][] playBoard
	public Tile[][] getArray() {
		return playBoard;
	}


	//toString() prints out playBoard with formatting involving commas and square bracket
	public String toString() {
		String output = "";
		for(int i = 0; i < BOARD_SIZE; i++) {
			output += "[";
			for(int j = 0; j < BOARD_SIZE; j++) {
				if(playBoard[i][j] == null) {
					output += 0;
				} else {
					output += playBoard[i][j].getValue();
				}
				if(j <  BOARD_SIZE - 1) {
					output += ",";
				} 
			}
			output += "]\n";
		}
		return output;
	}
}
