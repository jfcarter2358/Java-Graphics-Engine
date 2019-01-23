/**
 *Class - InputHandler
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

//======End Import Statements======\\

//******Begin Class******\\

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener, ActionListener{

	//------Variables------\\

	public boolean[] key = new boolean[68836];
	public boolean play,quit,controls;
	public static int mouseX,mouseY;
	public static boolean moved;
	public static boolean leftMouse,rightMouse;
	public static boolean focusLost;

	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\
	
	//Called if an action has been performed
	public void actionPerformed(ActionEvent e){
		MainClass.startup();
	}
	
	//Called if the window focus has been gained
    public void focusGained(FocusEvent e){
    	focusLost = false;
    }
   
    //Called if the window focus has been lost
    public void focusLost(FocusEvent e){
    	for (int i = 0; i < key.length; i++){
    		key[i] = false; 
    	}
   		focusLost = true;
    }
    
    //Called if a key has been pressed
    public void keyPressed(KeyEvent e){
    	
    	int keyCode = e.getKeyCode();
    	if (keyCode > 0 && keyCode < key.length){
    		key[keyCode]=true;
    	}
    }
    
    //Called if a key has been released
    public void keyReleased(KeyEvent e){
   		int keyCode = e.getKeyCode();
    	if(keyCode > 0 && keyCode < key.length){
    		key[keyCode] = false;
    	}
    }
    
   	//Called if a key has been typed
    public void keyTyped(KeyEvent e){
    	int keyCode = e.getKeyCode();
    	if (keyCode > 0 && keyCode < key.length){
    		key[keyCode]=true;
    	}	
    }
    
    //Called if the mouse button has been clicked
    public void mouseClicked(MouseEvent e){}
    
    //Called if the mouse has been dragged
	public void mouseDragged(MouseEvent e){}
	
	//Called if the mouse has entered the frame
	public void mouseEntered(MouseEvent e){}
	
	//Called if the mouse has exited the frame
	public void mouseExited(MouseEvent e){}
	
	//Called if the mouse has been moved
    public void mouseMoved(MouseEvent e){
    	mouseX = e.getX();
    	mouseY = e.getY();
    	moved = true;
    }
    
    //Called if the mouse button has been pressed
    public void mousePressed(MouseEvent e){
    	leftMouse = SwingUtilities.isLeftMouseButton(e);
    	rightMouse = SwingUtilities.isRightMouseButton(e);
    }
    
    //Called if the mouse button has been released
    public void mouseReleased(MouseEvent e){
    	leftMouse = false;
    	rightMouse = false;
    }
    
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\