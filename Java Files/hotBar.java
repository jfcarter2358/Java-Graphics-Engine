/**
 *Class - hotBar
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;

//======End Import Statements======\\

//******Begin Class******\\

public class hotBar{

	//------Variables------\\

	public Polygon one;
	public Polygon two;
	public Polygon three;
	public Polygon four;
	public Polygon five;
	public Polygon six;
	public Polygon seven;
	public Polygon eight;
	public Polygon nine;
	public Polygon select;
	
	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\
	
	//Constructor
	public hotBar(){
		one = new Polygon();
		two = new Polygon();
		three = new Polygon();
		four = new Polygon();
		five = new Polygon();
		six = new Polygon();
	}
	
	//Sets the data and draws the hot bar
	public void draw(Graphics g){
		one.reset();
		two.reset();
		three.reset();
		four.reset();
		five.reset();
		six.reset();
		one.addPoint(10,10);
		one.addPoint(50,10);
		one.addPoint(50,50);
		one.addPoint(10,50);
		two.addPoint(10,60);
		two.addPoint(50,60);
		two.addPoint(50,100);
		two.addPoint(10,100);
		three.addPoint(10,110);
		three.addPoint(50,110);
		three.addPoint(50,150);
		three.addPoint(10,150);
		four.addPoint(10,160);
		four.addPoint(50,160);
		four.addPoint(50,200);
		four.addPoint(10,200);
		five.addPoint(10,210);
		five.addPoint(50,210);
		five.addPoint(50,250);
		five.addPoint(10,250);
		six.addPoint(10,260);
		six.addPoint(50,260);
		six.addPoint(50,300);
		six.addPoint(10,300);
		if(MainClass.placeVal > 0){
			g.setColor(Color.red);
			g.fillPolygon(one);
		}
		if(MainClass.placeVal > 1){
			g.setColor(Color.red);
			g.fillPolygon(two);
		}
		if(MainClass.placeVal > 2){
			g.setColor(Color.yellow);
			g.fillPolygon(three);
		}
		if(MainClass.placeVal > 3){
			g.setColor(Color.yellow);
			g.fillPolygon(four);
		}
		if(MainClass.placeVal > 4){
			g.setColor(Color.green);
			g.fillPolygon(five);
		}
		if(MainClass.placeVal > 5){
			g.setColor(Color.green);
			g.fillPolygon(six);
		}
		g.setColor(Color.black);
		g.drawPolygon(one);
		g.drawPolygon(two);
		g.drawPolygon(three);
		g.drawPolygon(four);
		g.drawPolygon(five);
		g.drawPolygon(six);
	}

	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\