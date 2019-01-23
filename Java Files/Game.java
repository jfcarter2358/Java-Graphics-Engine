/**
 *Class - Game
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.event.KeyEvent;

//======End Import Statements======\\

//******Begin Class******\\

public class Game{

	//------Variables------\\

	public int time;
	public Controller controls;

	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\

	//Constructor
	public Game(){
		controls = new Controller();
	}
	
	//Sends weather or not a key has been pressed to the controls object to be acted upon
	public void tick(boolean[] key, boolean moved){
		time++;
		boolean lookLeft = key[KeyEvent.VK_LEFT];
		boolean lookRight = key[KeyEvent.VK_RIGHT];
		boolean lookUp = key[KeyEvent.VK_UP];
		boolean lookDown = key[KeyEvent.VK_DOWN];
		boolean w = key[KeyEvent.VK_W];
		boolean s = key[KeyEvent.VK_S];
		boolean d = key[KeyEvent.VK_D];
		boolean a = key[KeyEvent.VK_A];
		boolean space = key[KeyEvent.VK_SPACE];
		boolean shift = InputHandler.leftMouse;
		boolean up = key[KeyEvent.VK_NUMPAD8];
		boolean down = key[KeyEvent.VK_NUMPAD2];
		boolean right = key[KeyEvent.VK_NUMPAD6];
		boolean left = key[KeyEvent.VK_NUMPAD4];
		boolean forward = key[KeyEvent.VK_NUMPAD7];
		boolean back = key[KeyEvent.VK_NUMPAD9];
		boolean esc = key[KeyEvent.VK_ESCAPE];
		boolean reset = InputHandler.rightMouse;
		boolean move = moved;
//		if(key[KeyEvent.VK_ALT] && key[KeyEvent.VK_TAB]){
//			PauseMenu pauseMenu = new PauseMenu();
//			pauseMenu.drawButton();
//			while(!pauseMenu.start){
//				System.out.print("");
//			}
//			pauseMenu.dispose();
////			MainClass.frame.toFront();
//		}
		controls.tick(lookLeft,lookRight,lookUp,lookDown,w,s,d,a,shift,space,move,up,down,left,right,forward,back,reset);
	}

	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\