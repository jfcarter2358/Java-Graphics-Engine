/**
 *Class - FPSCounter
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

//======End Import Statements======\\

//******Begin Class******\\

public class FPSCounter {

	//------Variables------\\
	
    public int currentFPS = 0;
    public int FPS = 0;
    public long start = 0;
    
	//------End Variables------\\
	
   	//~~~~~~Methods~~~~~~\\
   	
    //Calculates the frames per second
    public void tick() {
        currentFPS++;
        if(System.currentTimeMillis() - start >= 1000) {
            FPS = currentFPS;
            currentFPS = 0;
            start = System.currentTimeMillis();
        }
    }
    
    //Returns the frames per second
    public int getFPS() {
        return FPS;
    }
   
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\