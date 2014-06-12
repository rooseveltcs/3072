import java.util.Scanner;


public class runAI {
	public static void main(String[] args) {
		CodeRunner runner = new CodeRunner();
		Scanner console = new Scanner(System.in);
		ArtificialInteligence AI = new ArtificialInteligence(runner);
		runner.startGame();
		while(runner.board.canMove()) {
			int direction = AI.recommendMove(runner.board,2);
			System.out.println(direction);
			runner.excecuteTurn(direction);
		}
		System.out.println("end");
	}
}
