/**
 *Class - Point3D
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

//======End Import Statements======\\

//******Begin Class******\\

public class Point3D{

	//------Variables------\\

	double X;
	double Y;
	double Z;
	double rotateX;
	double rotateY;
	double rotateZ;
	double rotateW;
	double dispX;
	double dispY;
	public double tempX;
	public double tempY;
	public double tempZ;
	public double W;
	public boolean allowed;

	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\
	
	//Constructor
  	public Point3D(){
		X = 0;
		Y = 0;
		Z = 0;
		rotateX = 0;
		rotateY = 0;		
		rotateZ = 0;
		dispX = 0;
		dispY = 0;
	}
	
	//Constructor
	public Point3D(double X1,double Y1,double Z1){
		X = X1;
		Y = Y1;
		Z = Z1;
		rotateX = 0;
		rotateY = 0;		
		rotateZ = 0;
		dispX = 0;
		dispY = 0;
	}
	
	//Determine if the point can be seen or not
	public void clipping(){
		allowed = true;
		for(int i = 0; i < 4; i++){
			if(rotateY <= 0 || (rotateY < rotateX && rotateY < -rotateX)){
				if(rotateY <= 0 || (rotateY < rotateZ && rotateY < -rotateZ)){
					allowed = false;
				}
			}
		}
	}
	
	//Set the point
	public void set(double X1,double Y1,double Z1){
		X = X1;
		Y = Y1;
		Z = Z1;
		tempX = X1;
		tempY = Y1;
		tempZ = Z1;
	}
	
	//Set the temporary values for the point
	public void setTempPoints(double X1,double Y1,double Z1){
		tempX = X-X1;
		tempY = Y-Y1;
		tempZ = Z-Z1;
	}
	
	//Returns the point's X display value
	public double getDispX(){
		return(dispX);
	}
	
	//Returns the point's Y display value
	public double getDispY(){
		return(dispY);
	}
	
	//Returns the point's rotated X value
	public double getRotateX(){
		return(rotateX);	
	}
	
	//Returns the point's rotated Y value
	public double getRotateY(){
		return(rotateY);
	}
	
	//Returns the point's rotated Z value
	public double getRotateZ(){
		return(rotateZ);
	}
	
	//Returns the point's rotated W value
	public double getRotateW(){
		return(rotateW);
	}
	
	//Returns the point's X value
	public double getXVal(){
		return(X);	
	}
	
	//Returns the point's Y value
	public double getYVal(){
		return(Y);
	}
	
	//Returns the point's Z value
	public double getZVal(){
		return(Z);
	}
	
	//Returns the point's W value
	public double getWVal(){
		return(W);
	}
	
	//rotated the point's values
	public void rotate(){
		float[][] resultant = Matrix.PointMultiply(getXVal(),getYVal(),getZVal());
		float[][] resultant2 = Matrix.Multiply(MainClass.mainClass.projectionMatrix.matrix,resultant);
		rotateX = (double)resultant2[0][0];
		rotateZ = (double)resultant2[1][0];
		rotateY = (double)resultant2[2][0];
		rotateW = (double)resultant2[3][0];
	}	
			
	//Set's the point's display values
	public void setDispVals(){
		dispX = (double)(rotateX * MainClass.WIDTH) / (2.0 * rotateW) + (.5 * MainClass.WIDTH);
		dispY = (double)(rotateZ * MainClass.HEIGHT) / (2.0 * rotateW) + (.5 * MainClass.HEIGHT);
	}
	
	//sets the point's rotated X value
	public void setRotateX(double newRotateX){
		rotateX = newRotateX;
	}
	
	//sets the point's rotated Y value
	public void setRotateY(double newRotateY){
		rotateY = newRotateY;
	}
	
	//sets the point's rotated Z value
	public void setRotateZ(double newRotateZ){
		rotateZ = newRotateZ;
	}
				
	//Sets the point's X value
	public void setXVal(double X1){
		X = X1;
	}
	
	//Sets the point's Y value 
	public void setYVal(double Y1){
		Y = Y1;
	}
	
	//Sets the point's Z value
	public void setZVal(double Z1){
		Z = Z1;
	}	
	
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\
