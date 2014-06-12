
public class Tile {
	private boolean canMerge;
	private boolean hasMoved;
	private Board board;
	private int power = 0;
	private int multiplier;
	public int x;
	public int y;

	public Tile(int multiplier, int power, Board board, int x, int y) {
		this.multiplier = multiplier;
		this.board = board;
		this.power = power;
		this.x = x;
		this.y = y;
		board.setSpace(this,x,y);
	}

	public void act(int direction) {
		//System.out.print(this.power + " - ");
		boolean play = true;
		canMerge = true;
		hasMoved = false;
		while(play) {
			try {
				Tile neighbor = getNeighbor(direction);
				try {
					merge(neighbor,direction);
					play = false;
				} catch (NullPointerException e) {
					move(direction);
				} 
			}  catch (ArrayIndexOutOfBoundsException e) {
				//System.out.print("OOB\n" + board);
				play = false;
			}
		}
	}

	public Tile getNeighbor(int direction) {
		Tile neighbor = null;
		switch(direction) {
		case 0:
			neighbor = board.getSpace(x - 1,y);
			break;
		case 1:
			neighbor = board.getSpace(x + 1,y);
			break;
		case 2:
			neighbor = board.getSpace(x,y - 1);
			break;
		case 3:
			neighbor = board.getSpace(x,y + 1);
			break;
		}

		return neighbor;
	}

	public void merge(Tile neighbor, int direction) {
		int neighborPower = neighbor.getPower();
		if(neighborPower == this.power && neighbor.getMerge() && this.canMerge) {
			move(direction);
			canMerge = false;
			power++;
		}
		//System.out.print(neighborPower + "\n" + board);
	}

	public void move(int direction) {
		//System.out.print("Empty\n" + board);
		board.setSpace(null,x,y);
		hasMoved = true;
		switch(direction) {
		case 0:
			this.x--;
			break;
		case 1:
			this.x++;
			break;
		case 2:
			this.y--;
			break;
		case 3:
			this.y++;
			break;
		}
		board.setSpace(this,x,y);
	}
	
	public int getValue() {
		return (int)(Math.pow(2, power - 1) * multiplier);
	}

	public int getMultiplier() {
		return multiplier;
	}

	public int getPower() {
		return power;
	}

	public boolean getMerge() {
		return canMerge;
	}

	public boolean getMoved() {
		return hasMoved;
	}
}
