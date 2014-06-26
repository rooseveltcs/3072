package remake2048;


import java.awt.Color;
import java.awt.Graphics2D;


public class DrawTile {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
	public static final int ARC_WIDTH = 15;
	public static final int ARC_HEIGHT = 15;


	private int value;
	private int x;
	private int y; 
	private Tile tile;

	//makes a DrawTile based on the specifications of a Tile
	public DrawTile(Tile tile){
		this.tile = tile;
		this.value = tile.getValue();
		this.y = findX(tile.x);
		this.x = findY(tile.y);
	}
	//finds the pixel location X for a arr location X
	private int findX(int arrX){
		if(arrX == 0){
			return 10;
		}else if(arrX == 1){
			return 100;
		}else if(arrX == 2){
			return 190;
		}else if(arrX == 3){
			return 280;
		}else{
			return 300;
		}

	}
	//finds the pixel location Y for a arr location Y
	private int findY(int arrY){
		if(arrY == 0){
			return 10;
		}else if(arrY == 1){
			return 100;
		}else if(arrY == 2){
			return 190;
		}else if(arrY == 3){
			return 280;
		}else{
			return 300;
		}

	}

	//draws the color of the cube to the graphics object given based on the value of the tile value 
	public void render(Graphics2D g2){
		if(value == 3){
			g2.setColor(Color.RED);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 6){
			g2.setColor(Color.BLUE);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 12){
			g2.setColor(Color.PINK);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 24){
			g2.setColor(Color.GREEN);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 48){
			g2.setColor(Color.MAGENTA);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 96){
			g2.setColor(Color.ORANGE);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 192){
			g2.setColor(Color.CYAN);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 348){
			g2.setColor(Color.YELLOW);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 768){
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 1536){
			g2.setColor(Color.darkGray);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}else if(value == 3072){
			g2.setColor(Color.GRAY);
			g2.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}

	}
	public Boolean equals(int testValue){
		if(tile.getValue() == testValue){
			return true;
		}else{
			return false;
		}
	}
}
