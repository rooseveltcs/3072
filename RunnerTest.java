import java.util.Scanner;


public class RunnerTest {
	public static void main(String[] args) {
		CodeRunner runner = new CodeRunner();
		Scanner console = new Scanner(System.in);
		ArtificialInteligence AI = new ArtificialInteligence(runner);
		runner.startGame();
		while(runner.runGame) {
			int direction = AI.recommendMove(runner.board, 1);
			System.out.println(direction);
			runner.excecuteTurn(direction);
		}
		System.out.println("end");
	}

}
