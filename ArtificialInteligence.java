import java.util.ArrayList;
import java.util.List;


public class ArtificialInteligence {
	//Tested
	private CodeRunner runner;

	public ArtificialInteligence(CodeRunner runner){
		this.runner = runner;
	}

	public int recommendMove(Board b) {
		double record = Double.NEGATIVE_INFINITY;
		int best = -1;
		for(int direction = 0; direction < 4; direction++){
			List<Board> moveOutcomes = allPossibleOutcomesOfMove(b,direction);
			double assessment = assessBoard(moveOutcomes);
			if (assessment > record && b.canMove(direction)){
				best = direction;
				record = assessment;
			}
		}
		return best;
	}

	public int recommendMove(Board b, int movesDeep) {
		if(movesDeep <= 1){
			return recommendMove(b);
		}
		double record = Double.NEGATIVE_INFINITY;
		int best = (int) (Math.random() * 4);
		for(int direction = 0; direction < 4; direction++){
			List<Board> moveOutcomes = bestOutcome(allPossibleOutcomesOfMove(b,direction), movesDeep - 1);
			double assessment = assessBoard(moveOutcomes);
			if (assessment > record && b.canMove(direction)){
				best = direction;
				record = assessment;
			}
		}
		return best;
	}

	public List<Board> bestOutcome(Board b, int movesDeep){
		return allPossibleOutcomesOfMove(b,recommendMove(b, movesDeep));
	}

	public List<Board> bestOutcome(List<Board> boards, int movesDeep){
		List<Board> toReturn = new ArrayList<Board>();
		for (Board b: boards){
			toReturn.addAll(bestOutcome(b, movesDeep));
		}
		return toReturn;
	}

	//Tested
	public List<Board> allPossibleOutcomesOfMove(Board initial, int direction){
		Board moved = initial.clone();
		moved.moveContents(direction);
		List<Board> setOfOutcomes = new ArrayList<>();
		for (int x = 0; x < moved.getSize(); x++){
			for (int y = 0; y < moved.getSize(); y++){
				if (moved.getSpace(x, y) == null){ 
					Board toAdd = moved.clone(); 
					new Tile(runner.TILE_MULTIPLIER,runner.POWER_SPAWN_VALUE,toAdd,x,y);
					setOfOutcomes.add(toAdd);
				}
			}
		}
		return setOfOutcomes;
	}

	//assess a set of Boards by averaging the set of boards
	public double assessBoard(List<Board> boards){
		double sum = 0;
		for(Board b : boards){
			sum += assessBoard(b);
		}
		return sum / boards.size();
	}

	//Assess the value of a single board. Currently just finds the value of a single board.
	public double assessBoard(Board board){
		return countOpenSpaces(board) + monotonisity(board) / 20;
	}

	//Counts the number of open spaces in a board, Successfully tested
	public int countOpenSpaces(Board board){
		int count = 0;
		for (int i = 0; i < board.playBoard.length; i++){
			for (Tile t : board.playBoard[i]){
				if (t == null)
					count++;
			}
		}
		return count;
	}

	public double monotonisity(Board board){
		double monotonisityRightLeft = 0;
		for (int i = 0; i < board.getSize(); i++){
			Tile previousTile = null;
			for (Tile t: board.playBoard[i]){
				if(t != null){
					if (previousTile != null)
						monotonisityRightLeft += Math.signum(t.getValue()-previousTile.getValue());
					previousTile = t;
				}
			}
		}
		double monotonisityUpDown = 0;
		for (int j = 0; j < board.getSize(); j++){
			Tile previousTile = null;
			for (int i = 0; i < board.getSize(); i++){
				Tile t = board.getSpace(i, j);
				if(t != null){
					if (previousTile != null)
						monotonisityUpDown += Math.signum(t.getValue()-previousTile.getValue());
					previousTile = t;
				}
			}
		}
		return Math.abs(monotonisityRightLeft) +  Math.abs(monotonisityUpDown);
	}

}
