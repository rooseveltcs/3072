package remake2048;


import java.awt.event.KeyEvent;

public class Key {
	
	public static boolean[] pressed = new boolean[256];
	public static boolean[] prev = new boolean[256];
	
	//does nothing
	private Key(){}
	
	//when key is pressed sets pressed to true
	public static void KeyPressed(KeyEvent e){
		pressed[e.getKeyCode()] = true;
		
	}
	//when key is released sets pressed to true
	public static void keyReleased(KeyEvent e){
		pressed[e.getKeyCode()] = false;
	}
	// updates prev boolean arr
	public static void update(){
		for(int p = 0; p < 4 ; p++){
			if(p == 0)prev[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
			if(p == 1)prev[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
			if(p == 2)prev[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
			if(p == 3)prev[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];
		}
	
	}
	//returns boolean if key was typed
	public static boolean typed(int key){
		return !pressed[key] && prev[key];
	
	}
}
