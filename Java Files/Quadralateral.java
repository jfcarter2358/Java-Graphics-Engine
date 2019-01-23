/**
 *Class - Quadralateral
 *Author - John Carter
 *Last Updated - 12/2/13
 */

//======Import Statements======\\

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.BasicStroke;

//======End Import Statemenst======\\

//******Begin Class******\\

public class Quadralateral extends Point3D{

	//------Variables------\\

	public Point3D[] points;
	public int[] drawValsX;
	public int[] drawValsY;
	public Color quadColor = Color.white;
	public boolean allowed = true;
	public Graphics2D g2d;
	public Polygon polygon = new Polygon();
	public int counter = 4;
	public boolean allowed1;
	public boolean allowed2;
	public boolean allowed3;
	public boolean allowed4;
	public boolean outlineAllowed;
	public Point3D[][] pointsArray;
	public Point3D[][] lightingPoints;
	public Texture texture = new Texture();	
	public Color[][] colors;

	//------End Variables------\\

	//~~~~~~Constructor~~~~~~\\

	public Quadralateral(){
		points = new Point3D[4];
		for(int i = 0; i < 4; i++){
			points[i] = new Point3D();
		}
		drawValsX = new int[4];
		drawValsY = new int[4];
	}
	
	//~~~~~~End Constructor~~~~~~\\
	
	//~~~~~~Methods~~~~~~\\
	
	//Clips the quadralateral so that it is only displayed if it is on screen
	public void clipping(){
		counter = 4;
		outlineAllowed = true;
		allowed = true;
		//allowed1 = true;
		//allowed2 = true;
		//allowed3 = true;
		//allowed4 = true;
		/*
		if(points[0].rotateY < 0 || (points[0].rotateY < points[0].rotateX+1 && points[0].rotateY < -points[0].rotateX+1)){
			if(points[0].rotateY < 0 || (points[0].rotateY < points[0].rotateZ && points[0].rotateY < -points[0].rotateZ)){
				allowed1 = false;
			}
		}
		if(points[1].rotateY < 0 || (points[1].rotateY < points[1].rotateX+1 && points[1].rotateY < -points[1].rotateX+1)){
			if(points[1].rotateY < 0 || (points[1].rotateY < points[1].rotateZ && points[1].rotateY < -points[1].rotateZ)){
				allowed2 = false;
			}
		}
		if(points[2].rotateY < 0 || (points[2].rotateY < points[2].rotateX+1 && points[2].rotateY < -points[2].rotateX+1)){
			if(points[2].rotateY < 0 || (points[2].rotateY < points[2].rotateZ && points[2].rotateY < -points[2].rotateZ)){
				allowed3 = false;
			}
		}
		if(points[3].rotateY < 0 || (points[3].rotateY < points[3].rotateX+1 && points[3].rotateY < -points[3].rotateX+1)){
			if(points[3].rotateY < 0 || (points[3].rotateY < points[3].rotateZ && points[3].rotateY < -points[3].rotateZ)){
				allowed4 = false;
			}
		}
		*/
		/*
		if((points[0].rotateX > points[0].rotateW && points[0].rotateX < -points[0].rotateW) && (points[0].rotateY > points[0].rotateW && points[0].rotateY < -points[0].rotateW) && (points[0].rotateZ > points[0].rotateW && points[0].rotateZ < -points[0].rotateW)){

		}
		else{
			allowed = false;
		}
		if((points[1].rotateX > points[1].rotateW && points[1].rotateX < -points[1].rotateW) && (points[1].rotateY > points[1].rotateW && points[1].rotateY < -points[1].rotateW) && (points[1].rotateZ > points[1].rotateW && points[1].rotateZ < -points[1].rotateW)){

		}
		else{
			allowed = false;
		}
		if((points[2].rotateX > points[2].rotateW && points[2].rotateX < -points[2].rotateW) && (points[2].rotateY > points[2].rotateW && points[2].rotateY < -points[2].rotateW) && (points[2].rotateZ > points[2].rotateW && points[2].rotateZ < -points[2].rotateW)){

		}
		else{
			allowed = false;
		}
		if((points[3].rotateX > points[3].rotateW && points[3].rotateX < -points[3].rotateW) && (points[3].rotateY > points[3].rotateW && points[3].rotateY < -points[3].rotateW) && (points[3].rotateZ > points[3].rotateW && points[3].rotateZ < -points[3].rotateW)){

		}
		else{
			allowed = false;
		}
		*/
		if(points[0].rotateW > 0){
			allowed = false;
		}
		if(points[1].rotateW > 0){
			allowed = false;
		}
		if(points[2].rotateW > 0){
			allowed = false;
		}
		if(points[3].rotateW > 0){
			allowed = false;
		}
		/*
		if(allowed4 == false){
			counter--;
		}
		
		if(allowed3 == false){
			for(int i = 3; i < counter; i++){
				drawValsX[i-1] = drawValsX[i];
				drawValsY[i-1] = drawValsY[i];
			}
			counter--;
		}
		if(allowed2 == false){
			for(int i = 2; i < counter; i++){
				drawValsX[i-1] = drawValsX[i];
				drawValsY[i-1] = drawValsY[i];
			}
			counter--;
		}
		if(allowed1 == false){
			for(int i = 1; i < counter; i++){
				drawValsX[i-1] = drawValsX[i];
				drawValsY[i-1] = drawValsY[i];
			}
			counter--;
		}
		if(!allowed1 || !allowed2 || !allowed3 || !allowed4){
			outlineAllowed = false;
		}
		*/
	}

	//Draws the quadralateral on the screen
	public void draw(){
		if(allowed){
   		 	if(quadColor != null && g2d != null){	
    			g2d.setColor(quadColor);
				g2d.fill(polygon);

			}
		}
	}
	
	//Outlines the quadralateral on the screen
	public void outline(){
		if(outlineAllowed){
			Polygon polygon = new Polygon();
 		   	for(int i = 0; i < 4; i ++){
 		   		polygon.addPoint(drawValsX[i],drawValsY[i]);
   		 	}
   		 	if(quadColor != null && g2d != null){
    			g2d.setColor(quadColor);
				g2d.draw(polygon);
   		 	}
		}
		
	}
	
	//Rotates the quadralateral's points
	public void rotate(){
		for(int i = 0; i < 4; i++){
			points[i].rotate();
		}
	}
	
	//Sets the quadralateral's display arrays
	public void setArrays(){
		for(int i = 0; i < 4; i++){
			drawValsX[i]=(int)(points[i].getDispX());
			drawValsY[i]=(int)(points[i].getDispY());
		}
	}
	
	//Sets the color of the quadralateral
	public void setColor(Color color,Graphics2D graphic){
		g2d = graphic;
		quadColor = color;
		polygon.reset();
		for(int i = 0; i < counter; i ++){
 		  	polygon.addPoint(drawValsX[i],drawValsY[i]);
   		}
	}
	public void setColor(Color color){
		quadColor = color;
	}
	//Sets the display values for the points
	public void setDispVals(){
		for(int i = 0; i < 4; i++){
			points[i].setDispVals();
		}
	}
	
	//Set's the values for the quadralateral's points
	public void setPoints(double X1, double Y1, double Z1, double X2, double Y2, double Z2, double X3, double Y3, double Z3, double X4, double Y4, double Z4){
		points[0].set(X1,Y1,Z1);
		points[1].set(X2,Y2,Z2);
		points[2].set(X3,Y3,Z3);
		points[3].set(X4,Y4,Z4);
	}	
	
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\