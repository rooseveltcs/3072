package remake2048;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable  {

	//board size
	public static int WIDTH = 370;
	public static int HEIGHT = 370;

	//Font
	public static final Font main = new Font("absender1.ttf", Font.PLAIN, 28);


	private Thread game;
	private boolean running;

	private GameBoard board;
	private CodeRunner runner;

	//constructs game and sets starting requirements
	public Game(CodeRunner runner){
		this.runner = runner;
		setLayout(null);
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		runner.startGame();
		board = new GameBoard(WIDTH/2 - GameBoard.BOARD_WIDTH/2, HEIGHT - GameBoard.BOARD_HEIGHT, runner.board.getArray());

	}



	//updates 
	private void update(){
		if(Key.typed(KeyEvent.VK_LEFT)){
			runner.excecuteTurn(2);
		}else if(Key.typed(KeyEvent.VK_RIGHT)){
			runner.excecuteTurn(3);
		} else if(Key.typed(KeyEvent.VK_UP)) {
			runner.excecuteTurn(0);
		} else if(Key.typed(KeyEvent.VK_DOWN)) {
			runner.excecuteTurn(1);
		}

		board.update(runner.board.getArray());
		Key.update();
	}



	private void render(){

		Graphics2D g2d = (Graphics2D) getGraphics();

		board.render(g2d);

		g2d.dispose();

	}



	//the main game loop
	@Override
	public void run() {
		long fpsTimer = System.currentTimeMillis();
		double nsPerUpdate = 1000000000.0 / 60;

		double then = System.nanoTime();
		double unprocessed = 0;

		while(running){
			boolean shouldRender = false;
			double now = System.nanoTime();
			unprocessed += (now - then) / nsPerUpdate;
			then = now;
			if(runner.gameOverTest()){
				running = false;
			}


			while(unprocessed >=1 ){
				update();
				unprocessed--;
				shouldRender = true;
			}
			if(shouldRender){
				render();
				shouldRender = false;
			}else{
				try{
					Thread.sleep(1);
				}catch(Exception e){
					e.printStackTrace();
				}

			}
		}
		if(System.currentTimeMillis() - fpsTimer > 1000){
			fpsTimer += 1000;
			System.out.println();
			System.out.println("Game Over");
			System.out.println();
			System.out.println("thanks for playing");
		}
	}

	// starts the game
	public synchronized void start(){
		if (running)		
			return;
		running = true;
		game = new Thread(this, "game");
		game.start();
	}
	//stops the game
	public synchronized void stop(){
		if(!running)
			return;
		running = false;

		System.exit(0);
	}

	@Override
	//records a key code for a key that is pressed
	public void keyPressed(KeyEvent e) {
		Key.KeyPressed(e);

	}




	@Override
	//records the key code for a key that is released
	public void keyReleased(KeyEvent e) {
		Key.keyReleased(e);

	}




	@Override
	//records previosly typed keys
	public void keyTyped(KeyEvent e) {


	}
}








