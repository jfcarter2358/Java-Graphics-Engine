/**
 *Class - Matrix
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.lang.Math;

//======End Import Statements======\\

//******Begin Class******\\

public class Matrix{
	
	//------Variables------\\
	
	float[][] matrix;
	static float[][] IDENTITY = (new Matrix(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1)).matrix;
	
	//------End Variables------\\
	
	//~~~~~~Methods~~~~~~\\
	
	//Constructor
	public Matrix(){
		matrix = new float[4][4];
		matrix[0][0] = 0;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[0][3] = 0;
		matrix[1][0] = 0;
		matrix[1][1] = 0;
		matrix[1][2] = 0;
		matrix[1][3] = 0;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = 0;
		matrix[2][3] = 0;
		matrix[3][0] = 0;
		matrix[3][1] = 0;
		matrix[3][2] = 0;
		matrix[3][3] = 0;
	}
	
	//Constructor
	public Matrix(float element00, float element01, float element02, float element03, float element10, float element11, float element12, float element13, float element20, float element21, float element22, float element23, float element30, float element31, float element32, float element33){
		matrix = new float[4][4];
		matrix[0][0] = element00;
		matrix[0][1] = element01;
		matrix[0][2] = element02;
		matrix[0][3] = element03;
		matrix[1][0] = element10;
		matrix[1][1] = element11;
		matrix[1][2] = element12;
		matrix[1][3] = element13;
		matrix[2][0] = element20;
		matrix[2][1] = element21;
		matrix[2][2] = element22;
		matrix[2][3] = element23;
		matrix[3][0] = element30;
		matrix[3][1] = element31;
		matrix[3][2] = element32;
		matrix[3][3] = element33;
	}
	
	//Multiply matrices
	public static Matrix Multiply(Matrix matrix1, Matrix matrix2){
        int aRows = matrix1.matrix.length;
        int aColumns = matrix1.matrix[0].length;
        int bRows = matrix2.matrix.length;
        int bColumns = matrix2.matrix[0].length;
		float[][] A = matrix1.matrix;
		float[][] B = matrix2.matrix;
		float sum = 0;
		float[][] resultantArray = new float[4][4];
		for (int c = 0 ; c < aRows ; c++)
	    {
	       for (int d = 0 ; d < bColumns ; d++)
	       {   
	          for (int k = 0 ; k < bRows ; k++)
	          {
	             sum = sum + matrix1.matrix[c][k]*matrix2.matrix[k][d];
	          }
	          resultantArray[c][d] = sum;
	          sum = 0;
	       }
	    }
	    Matrix resultant = new Matrix();
	    resultant.matrix = resultantArray;
        return(resultant);
	}
	
	//Multiply matrices
	public static float[][] Multiply(float[][] matrix1, float[][] matrix2){
        int aRows = matrix1.length;
        int aColumns = matrix1[0].length;
        int bRows = matrix2.length;
        int bColumns = matrix2[0].length;
		float[][] A = matrix1;
		float[][] B = matrix2;
		float sum = 0;
		float[][] resultant = new float[4][1];
		for (int c = 0 ; c < aRows ; c++)
	    {
	       for (int d = 0 ; d < bColumns ; d++)
	       {   
	          for (int k = 0 ; k < bRows ; k++)
	          {
	             sum = sum + matrix1[c][k]*matrix2[k][d];
	          }
	          resultant[c][d] = sum;
	          sum = 0;
	       }
	    }
        return(resultant);
	}
	
	//Create matrix from yaw pitch and roll
	public static Matrix createFromYawPitchRoll(float yaw, float pitch, float roll){
		Matrix rotationXAxis = new Matrix(1,0,0,0,0,(float)Math.cos(pitch),(float)-Math.sin(pitch),0,0,(float)Math.sin(pitch),(float)Math.cos(pitch),0,0,0,0,1);
		Matrix rotationYAxis = new Matrix((float)Math.cos(yaw),0,(float)Math.sin(yaw),0,0,1,0,0,(float)-Math.sin(yaw),0,(float)Math.cos(yaw),0,0,0,0,1);
		Matrix rotationZAxis = new Matrix((float)Math.cos(roll),(float)-Math.sin(roll),0,0,(float)Math.sin(roll),(float)Math.cos(roll),0,0,0,0,1,0,0,0,0,1);
		Matrix resultant = Multiply(Multiply(rotationXAxis,rotationYAxis),rotationZAxis);
		return(resultant);
	}
	
	//Multiply point by matrix
	public static float[][] PointMultiply(double x, double y, double z){
		float[][] points = {{(float)x},{(float)z},{(float)y},{1}};
		float[][] resultant = Multiply(MainClass.mainClass.viewProjectionTransform.matrix, points);
		return(resultant);
	}

	//Multiply point by matrix
	public static float[][] pointMultiply(float[][] rotationMatrix, float[][] points){
		float[][] resultant = Multiply(rotationMatrix, points);
		return(resultant);
	}
	
	//~~~~~~End Methods~~~~~~\\
	
}

//******End Class*******\\