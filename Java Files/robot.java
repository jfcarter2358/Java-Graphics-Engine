/**
 *Class - robot
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.AWTException; 
import java.awt.Robot; 
import java.awt.event.KeyEvent; 

//======End Import Statements======\\

//******Begin Class******\\

public class robot { 

	
	//------Variables------\\
	
	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\

	public robot() { 
		try { 
			Robot robot = new Robot(); 
			robot.mouseMove(MainClass.WIDTH/2+MainClass.mouseXOffset,MainClass.HEIGHT/2+MainClass.mouseYOffset); 
		} catch (AWTException e) { 
			e.printStackTrace(); 
		}	 
	}

	//~~~~~~End Methods~~~~~~\\
		 
}

//******End Class******\\