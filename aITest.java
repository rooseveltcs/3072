import java.util.ArrayList;


public class aITest {
	public static void main(String args[]){
		Board b = new Board(4);
		new Tile(2,1,b,2,2);
		new Tile(2,1,b,2,3);
		new Tile(2,1,b,2,1);
		System.out.println(b);
		CodeRunner runner = new CodeRunner();
		ArtificialInteligence AI = new ArtificialInteligence(runner);
		int a = AI.recommendMove(b);
		System.out.println(a);

		
		//		ArrayList<Board> outcomes = ArtificialInteligence.bestOutcome(b);
//		System.out.println(outcomes);
		
//		
//		b.moveContents(1);
//		Board c = b.clone();
//		System.out.println(c);
//		new Tile (2,1,c,1,1);
//		System.out.println(c);
//		System.out.println(ArtificialInteligence.countOpenSpaces(b));
//		ArtificialInteligence.fillNthOpenTile(b, 2);
//		System.out.println(b);
//		System.out.println(ArtificialInteligence.countOpenSpaces(b));
//		while (ArtificialInteligence.countOpenSpaces(b) > 3){
//			ArtificialInteligence.fillNthOpenTile(b, 1);
//			System.out.println(b);
//			System.out.println(ArtificialInteligence.countOpenSpaces(b));
//		}
//		System.out.println(ArtificialInteligence.assessBoard(b));
//	
	}
}
