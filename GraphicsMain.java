package remake2048;


import javax.swing.JFrame;


public class GraphicsMain extends JFrame{

	public static void main(String[]args){
		CodeRunner runner = new CodeRunner();
		Game game = new Game(runner);
		JFrame w = new JFrame();
	
		w.setSize(370,370);
		w.setResizable(false);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setTitle("3072");
		w.add(game);
		w.pack();
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		
		
		game.start();
	}
	}







