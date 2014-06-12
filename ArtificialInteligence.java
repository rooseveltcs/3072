import java.util.ArrayList;
import java.util.List;


public class ArtificialInteligence {
	//Tested
	private CodeRunner runner;

	public ArtificialInteligence(CodeRunner runner){
		this.runner = runner;
	}

	public int recommendMove(Board b) {
		List<Board> left = allPossibleOutcomesOfMove(b,2);
		List<Board> right = allPossibleOutcomesOfMove(b,3);
		List<Board> up = allPossibleOutcomesOfMove(b,0);
		List<Board> down = allPossibleOutcomesOfMove(b,1);
		double record = Double.MIN_VALUE;
		int best = -1;
		if (assessBoard(left) > record && b.canMove(2)){
			best = 2;
			record = assessBoard(left);
		}
		if (assessBoard(right) > record && b.canMove(3)){
			best = 3;
			record = assessBoard(right);
		}
		if (assessBoard(up) > record && b.canMove(0)){
			best = 0;
			record = assessBoard(up);
		}
		if (assessBoard(down) > record && b.canMove(1)){
			best = 1;
		}
		return best;
	}
	
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
//	public int recommendMove(Board b) {
//		double bestImprovement = Double.MIN_VALUE;
//		int bestDirection = -1;
//		for(int direction = 0; direction <= 3; direction++) {
//			List<Board> outcomes = allPossibleOutcomesOfMove(b, direction);
//			double improvement = assessBoard(outcomes);
//			if(improvement > bestImprovement) {
//				bestImprovement = improvement;
//				bestDirection = direction;
//			}
//		}
//		return bestDirection;
//	}

	public int recommendMove(Board b, int movesDeep) {
		if(movesDeep <= 0){
			return recommendMove(b);
		}
		List<Board> left = bestOutcome(allPossibleOutcomesOfMove(b,2), movesDeep - 1);
		List<Board> right =  bestOutcome(allPossibleOutcomesOfMove(b,3), movesDeep - 1);
		List<Board> up =  bestOutcome(allPossibleOutcomesOfMove(b,0), movesDeep - 1);
		List<Board> down =  bestOutcome(allPossibleOutcomesOfMove(b,1), movesDeep - 1);
		double record = Double.MIN_VALUE;
		int best = -1;
		if (assessBoard(left) > record && b.canMove(2)){
			best = 2;
			record = assessBoard(left);
		}
		if (assessBoard(right) > record && b.canMove(3)){
			best = 3;
			record = assessBoard(right);
		}
		if (assessBoard(up) > record && b.canMove(0)){
			best = 0;
			record = assessBoard(up);
		}
		if (assessBoard(down) > record && b.canMove(1)){
			best = 1;
		}
		return best;
	}
	//Tested
	public double assessBoard(Board board){
		return countOpenSpaces(board);
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
					new Tile(2,1,toAdd,x,y);
					setOfOutcomes.add(toAdd);
				}
			}
		}
		return setOfOutcomes;
	}


	//tested lightly
	public double assessBoard(List<Board> boards){
		double sum = 0;
		for(Board b : boards){
			sum += assessBoard(b);
		}
		return sum / boards.size();
	}


	//tested
	public List<Board> bestOutcome(Board b){
		return allPossibleOutcomesOfMove(b,recommendMove(b));
	}
	
	public List<Board> bestOutcome(Board b, int movesDeep){
		return allPossibleOutcomesOfMove(b,recommendMove(b, movesDeep));
	}

	public List<Board> bestOutcome(List<Board> boards){
		List<Board> toReturn = new ArrayList<Board>();
		for (Board b: boards){
			toReturn.addAll(bestOutcome(b));
		}
		return toReturn;
	}
	
	public List<Board> bestOutcome(List<Board> boards, int movesDeep){
		List<Board> toReturn = new ArrayList<Board>();
		for (Board b: boards){
			toReturn.addAll(bestOutcome(b, movesDeep));
		}
		return toReturn;
	}
	
	//successfully tested
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

	//Tested
	public void fillNthOpenTile(Board toChange, int n) {
		int count = 0;
		for (int x = 0; x < toChange.getSize(); x++){
			for (int y = 0; y < toChange.getSize(); y++){
				if (toChange.getSpace(x, y) == null){ 
					count++;
				}
				if (count == n){
					toChange.setSpace(new Tile(runner.TILE_MULTIPLIER,1,toChange,x,y), x, y);
					break;
				}
			}
		}			
	}
}
