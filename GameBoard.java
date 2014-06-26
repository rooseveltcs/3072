package remake2048;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameBoard {

	public static final int ROWS = 4;
	public static final int COLUMNS = 4;


	private DrawTile[][] board;

	private BufferedImage underBoard;
	
	private int width;
	private int height;
	
	private static int SPACEING = 10;
	public static int BOARD_WIDTH = (COLUMNS + 1) * SPACEING + COLUMNS * DrawTile.WIDTH;
	public static int BOARD_HEIGHT = (ROWS + 1 ) * SPACEING + ROWS * DrawTile.HEIGHT;

	//draws the 4x4 board
	public GameBoard(int x, int y,Tile[][] boardT){
		board = new DrawTile[4][4];
		this.width = x;
		this.height = y;
		update(boardT);
		underBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT,BufferedImage.TYPE_INT_RGB);



	}
	//builds the board interface / grid; it also draws DrawTiles
	private void BuildBoardI(Graphics2D penCurrentUnderBoard){
		//Grid
		penCurrentUnderBoard.setColor(Color.BLACK);
		penCurrentUnderBoard.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
		penCurrentUnderBoard.setColor(Color.WHITE);
		for(int r = 0; r< ROWS; r++){
			for(int c = 0; c < COLUMNS; c++){

				int width = SPACEING + SPACEING * c + DrawTile.WIDTH*c;
				int height = SPACEING + SPACEING * r + DrawTile.HEIGHT*r;

				penCurrentUnderBoard.fillRoundRect(width, height, DrawTile.WIDTH, DrawTile.HEIGHT, DrawTile.ARC_WIDTH, DrawTile.ARC_HEIGHT);;
			}
		}

		
		
		
		
		//DrawTiles
		for(int r = 0; r< ROWS; r++){
			for(int c = 0; c < COLUMNS; c++ ){
				if (board[r][c] != null) {
					DrawTile tileAsInArray = board[r][c]; 
					tileAsInArray.render(penCurrentUnderBoard);
				}


			}
		}


	}


	//is called when a key is pressed and updates the array
	public void update(Tile[][] boardT){
		for(int r = 0; r< ROWS; r++ ){
			for(int c = 0; c < COLUMNS; c++){

				if(boardT[r][c] == null){
					board[r][c] = null;
				}else{

					board[r][c] = new DrawTile(boardT[r][c]);

				}
			}
		}
	}	

	//renders the graphics board on top of previous one
	public void render(Graphics2D penGame){
		Graphics2D penCurrentUnderBoard = (Graphics2D)underBoard.getGraphics();
		BuildBoardI(penCurrentUnderBoard);



		penGame.drawImage(underBoard, width, height, null);
		penCurrentUnderBoard.dispose();

	}


}
