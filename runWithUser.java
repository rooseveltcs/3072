import java.util.Scanner;


public class runWithUser {
	public static void main(String[] args) {
		CodeRunner runner = new CodeRunner();
		Scanner console = new Scanner(System.in);
		ArtificialInteligence AI = new ArtificialInteligence(runner);
		runner.startGame();
		while(runner.board.canMove()) {
			int direction = runner.getMove(console);
			System.out.println(direction);
			runner.excecuteTurn(direction);
		}
		System.out.println("end");
	}
}
