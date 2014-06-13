import java.util.List;


public class quickAI {
	//Tested
		private CodeRunner runner;

		public quickAI(CodeRunner runner){
			this.runner = runner;
		}
	
	public int recommendMove(Board b, int movesDeep) {
		double record = Double.NEGATIVE_INFINITY;
		int best = (int) (Math.random() * 4);
		for(int direction = 0; direction < 4; direction++){
			Board moveOutcome = b.clone();
			moveOutcome.moveContents(direction);
			moveOutcome.createRandomTile(runner.TILE_MULTIPLIER, runner.POWER_SPAWN_VALUE);
			double assessment = assessBoard(moveOutcome, movesDeep - 1);
			if (assessment > record && b.canMove(direction)){
				best = direction;
				record = assessment;
			}
		}
		return best;
	}
	
	public double assessBoard(Board b, int movesDeep) {
		if(movesDeep <= 0){
			return assessBoard(b);
		}
		double record = Double.NEGATIVE_INFINITY;
		for(int direction = 0; direction < 4; direction++){
			Board moveOutcome = b.clone();
			moveOutcome.moveContents(direction);
			moveOutcome.createRandomTile(runner.TILE_MULTIPLIER, runner.POWER_SPAWN_VALUE);
			double assessment = assessBoard(moveOutcome, movesDeep - 1);
			if (assessment > record && b.canMove(direction)){
				record = assessment;
			}
		}
		return record;
	}
	
	//Assess the value of a single board. Currently just finds the value of a single board.
	public double assessBoard(Board board){
		return countOpenSpaces(board) + 0 * monotonisity(board) / 20;
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
