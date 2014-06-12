
public class EqualsTest {
	public static void main(String[] args){
	Board b = new Board(4);
	new Tile(2,1,b,2,2);
	new Tile(2,1,b,2,3);
	new Tile(2,1,b,2,1);
	Board c = new Board(4);
	new Tile(2,1,c,1,1);
	new Tile(2,2,c,1,2);
	new Tile(2,3,c,1,3);
	new Tile(2,4,c,1,0);
	new Tile(2,5,c,2,1);
	new Tile(2,6,c,2,2);
	new Tile(2,7,c,2,3);
	new Tile(2,8,c,2,0);
	new Tile(2,9,c,3,1);
	new Tile(2,10,c,3,2);
	new Tile(2,11,c,3,3);
	new Tile(2,12,c,3,0);
	new Tile(2,13,c,0,1);
	new Tile(2,14,c,0,2);
	new Tile(2,14,c,0,3);
	new Tile(2,15,c,0,0);
	System.out.println(c.toString() + c.canMove(2));
	}
}
