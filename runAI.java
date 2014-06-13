import java.util.Scanner;


public class runAI {
	public static void main(String[] args) {
		CodeRunner runner = new CodeRunner();
		Scanner console = new Scanner(System.in);
		quickAI shelbyMoonshine = new quickAI(runner);
		runner.startGame();
		while(runner.board.canMove()) {
			int direction = shelbyMoonshine.recommendMove(runner.board,4);
			System.out.println(direction);
			runner.excecuteTurn(direction);
		}
		System.out.println("end");
	}
}
