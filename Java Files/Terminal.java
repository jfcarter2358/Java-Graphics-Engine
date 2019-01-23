/**
 *Class - Terminal
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.Color;
import java.awt.Graphics2D;

//======End Import Statements======\\

//******Begin Class******\\

public class Terminal{
	
	//------Variables------\\
	
    public double x;
    public double y;
   	public double z;
   	public int numOfTriangles = 16;
   	public Triangle[] walls;
   	public int g = 2;
   	public int lg = 3;
   	public int dg = 1;
   	public int w = 4;
   	public int b = 0;
   	public double[] x1 = {0.0,1.0,0.0,1.0,0.0,1.0,0.3,0.7,0.3,0.7,0.3,0.7,0.0,0.0,1.0,0.9};
   	public double[] x2 = {0.0,1.0,0.0,1.0,0.0,1.0,0.3,0.7,0.4,0.6,0.4,0.6,0.1,0.1,0.9,1.0};
   	public double[] x3 = {1.0,0.0,1.0,0.0,1.0,0.0,0.7,0.3,0.7,0.4,0.7,0.4,0.0,0.1,0.9,1.0};
   	public double[]	y1 = {0.9,0.9,0.9,0.9,0.8,0.5,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8};
   	public double[] y2 = {0.9,0.9,0.9,0.9,0.5,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8};
   	public double[] y3 = {0.9,0.9,0.9,0.9,0.5,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8,0.8};
   	public double[] z1 = {0.6,1.0,0.4,0.0,0.5,0.5,0.4,0.6,0.6,0.6,0.4,0.4,0.1,0.9,0.1,0.9};
   	public double[] z2 = {1.0,0.6,0.0,0.4,0.5,0.5,0.6,0.4,0.7,0.7,0.3,0.3,0.1,0.9,0.1,0.9};
   	public double[] z3 = {1.0,0.6,0.0,0.4,0.5,0.5,0.6,0.4,0.6,0.7,0.4,0.3,0.9,0.1,0.9,0.1};
    public int[] colors = {b,b,b,b,dg,dg,g,g,g,g,g,g,lg,lg,lg,lg};
    public double[] distance;
    
    //------End Variables------\\
    
    //~~~~~~Methods~~~~~~\\
    
    //Constructor
    public Terminal() {
    	walls = new Triangle[numOfTriangles];

		for(int i = 0; i < numOfTriangles; i++){
			walls[i] = new Triangle();
		}
		distance = new double[numOfTriangles];
    }
    
    //Clips the triangles agains the player's field of vision
    public void clipping(){
		for(int i = 0; i < numOfTriangles; i++){
 			walls[i].clipping();
	    }
    }
    
    //Draws the triangles
    public void draw(){
		for(int i = 0; i < numOfTriangles; i++){
 			walls[i].draw();
	   	}
    }
    
    //Rotates the triangles
    public void rotate(){
	   	for(int i = 0; i < numOfTriangles; i++){
 			walls[i].rotate();
	   	}
    }
    
    //Sets the data for the triangles
    public void sendData(){
    	for(int i = 0; i < numOfTriangles; i++){
    		walls[i].setPoints(x+x1[i],y+y1[i],z+z1[i],x+x2[i],y+y2[i],z+z2[i],x+x3[i],y+y3[i],z+z3[i]);
    	}
    }
    
    //Sets the object's main points
    public void set(double x1, double y1, double z1){
    	x = x1;
    	y = y1;
    	z = z1;
    }
    
    //Sets the display arrays for the triangles
    public void setArrays(){
    	for(int i = 0; i < numOfTriangles; i++){
 			walls[i].setArrays();
	    }
    }
    
    //Sets the color of the triangles
    public void setColor(Color color,Graphics2D graphic){
   		for(int i = 0; i < numOfTriangles; i++){
   			if(colors[i] == 0){
 				walls[i].setColor(color,graphic);
   			}
   			if(colors[i] == 1){
 				walls[i].setColor(color.darker(),graphic);
   			}
   			if(colors[i] == 2){
 				walls[i].setColor(Color.orange,graphic);
   			}
   			if(colors[i] == 3){
   				walls[i].setColor(Color.orange.darker(),graphic);
   			}
	    }
    }
    
    //Sets the color of the triangles
    public void setColor(Color color){
   		for(int i = 0; i < numOfTriangles; i++){
   			if(colors[i] == 0){
 				walls[i].setColor(color);
   			}
   			if(colors[i] == 1){
 				walls[i].setColor(color.darker());
   			}
   			if(colors[i] == 2){
 				walls[i].setColor(Color.orange);
   			}
   			if(colors[i] == 3){
   				walls[i].setColor(Color.orange.darker());
   			}
	    }
    }
    
    //Sets the display values for the triangles
    public void setDispVals(){
    	for(int i = 0; i < numOfTriangles; i++){
 			walls[i].setDispVals();
	    }
    }
    
    //Sorts the triangles according to distance away
    public void sort(double X, double Y, double Z){
    	double td1;
    	double td2;
    	double td3;
		for(int i = 0; i < numOfTriangles; i++){
				td1 = Math.sqrt(Math.pow(X-walls[i].points[0].X,2)+Math.pow(Y-walls[i].points[0].Y,2)+Math.pow(Z-walls[i].points[0].Z,2));
				td2 = Math.sqrt(Math.pow(X-walls[i].points[1].X,2)+Math.pow(Y-walls[i].points[1].Y,2)+Math.pow(Z-walls[i].points[1].Z,2));
				td3 = Math.sqrt(Math.pow(X-walls[i].points[2].X,2)+Math.pow(Y-walls[i].points[2].Y,2)+Math.pow(Z-walls[i].points[2].Z,2));
				if(td1 < td2 && td1 < td3){
					distance[i] = td1;
				}
				if(td2 < td1 && td2 < td3){
					distance[i] = td2;
				}
				if(td3 < td1 && td3 < td2){
					distance[i] = td3;
				}
		}
		for(int i = 0; i < numOfTriangles-1; i++){
			for(int j = i+1; j < numOfTriangles; j++){
				if(distance[i]<=distance[j]){
					Triangle temp = walls[i];
					double tempDistance = distance[i];
					int tempColor = colors[i];
					walls[i] = walls[j];
					distance[i] = distance[j];
					colors[i] = colors[j];
					walls[j] = temp;
					distance[j] = tempDistance;
					colors[j] = tempColor;
				}
			}
		}
	}
    
    //~~~~~~End Methods~~~~~~\\
    
}

//******End Class******\\