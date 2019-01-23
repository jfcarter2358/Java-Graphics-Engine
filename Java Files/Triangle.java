/**
 *Class - Triangle
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Vector;

//======End Import Statements======\\

//******Begin Class******\\

public class Triangle extends Point3D{

	//------Variables------\\

	public Point3D[] points;
	public int[] drawValsX;
	public int[] drawValsY;
	public Color quadColor;
	public boolean allowed = true;
	public Graphics2D g2d;
	
	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\

	//Constructor
	public Triangle(){
		points = new Point3D[3];
		for(int i = 0; i < 3; i++){
			points[i] = new Point3D();
		}
		drawValsX = new int[3];
		drawValsY = new int[3];
	}

	//Clips the triangle so that it is only displayed if it is on screen
	public void clipping(){
		allowed = true;
		for(int i = 0; i < 3; i++){
			if(points[i].rotateW > 0){
				allowed = false;
			}
		}
	}
	
	//Draws the triangle on the screen
	public void draw(){
		if(allowed){
			Polygon polygon = new Polygon();
    		for(int i = 0; i < 3; i ++){
    			polygon.addPoint(drawValsX[i],drawValsY[i]);
    		}
			if(quadColor != null && g2d != null){
    			g2d.setColor(quadColor);
				g2d.fill(polygon);
			}
		}
	}
	
	//Rotates the triangle's points
	public void rotate(){
		for(int i = 0; i < 3; i++){
			points[i].rotate();
		}
	}
	
	//Sets the triangle's display arrays
	public void setArrays(){
		for(int i = 0; i < 3; i++){
			drawValsX[i]=(int)(points[i].getDispX());
			drawValsY[i]=(int)(points[i].getDispY());
		}
	}
	
	//Sets the color of the triangle
	public void setColor(Color color,Graphics2D graphic){
		g2d = graphic;
		quadColor = color;
	}
	public void setColor(Color color){
		quadColor = color;
	}
	
	//Sets the display values for the points
	public void setDispVals(){
		for(int i = 0; i < 3; i++){
			points[i].setDispVals();
		}
	}
	
	//Set's the values for the triangle's points
	public void setPoints(double X1, double Y1, double Z1, double X2, double Y2, double Z2, double X3, double Y3, double Z3){
			points[0].setXVal(X1);
			points[0].setYVal(Y1);
			points[0].setZVal(Z1);
			points[1].setXVal(X2);
			points[1].setYVal(Y2);
			points[1].setZVal(Z2);
			points[2].setXVal(X3);
			points[2].setYVal(Y3);
			points[2].setZVal(Z3);
	}
		
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\