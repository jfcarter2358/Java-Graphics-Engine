/**
 * @(#)TexturePoint.java
 *
 *
 * @author 
 * @version 1.00 2015/5/6
 */


public class TexturePoint {

	public double X;
	public double Y;
	public double Z;
	public int dispX;
	public int dispY;
	
    public TexturePoint(double x, double y, double z) {
    	X = x;
    	Y = y;
    	Z = z;
    }
    
    public void setDispVals(){
    	dispX = ((X/Y) * 1000 + (.5*MainClass.WIDTH));
		dispY = ((-Z/Y) * 1000 + (.5*MainClass.HEIGHT));
    }
    
}