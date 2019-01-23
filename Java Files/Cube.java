/**
 *Class - Cube
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.*;

//======End Import Statements======\\

//******Begin Class******\\

public class Cube{
	
	//------Variables------\\
	
	public Color color;
    public Point3D topRightFront;
  	public Point3D topLeftFront;
  	public Point3D bottomRightFront;
  	public Point3D bottomLeftFront;
  	public Point3D topRightBack;
  	public Point3D topLeftBack;
  	public Point3D bottomRightBack;
  	public Point3D bottomLeftBack;
  	public boolean allowedFront;
  	public boolean allowedBack;
  	public boolean allowedRight;
  	public boolean allowedLeft;
  	public boolean allowedTop;
  	public boolean allowedBottom;
	public Texture texture;
  	public Graphics2D g2d;
    public boolean front;
    public boolean back;
    public boolean left;
    public boolean right;
    public boolean top;
    public boolean bottom;
    public double X;
    public double Y;
    public double Z; 
    public double rotateX;
    public double rotateY;
    public double rotateZ;
    public double Ztemp;
    public int moveDir;
    public int type;
    public boolean started;
    public FuelPellet pellet;
    public PlasmaShielding shields;
    public Terminal terminal;
    public boolean trueFront;
    public boolean trueBack;
    public boolean trueRight;
    public boolean trueLeft;
    public boolean trueTop;
    public boolean trueBottom;
    public Color[][] frontColorsTrue;
    public Color[][] backColorsTrue;
    public Color[][] rightColorsTrue;
    public Color[][] leftColorsTrue;
    public Color[][] topColorsTrue;
    public Color[][] bottomColorsTrue;
    public Color[][][] frontColors;
    public Color[][][] backColors;
    public Color[][][] rightColors;
    public Color[][][] leftColors;
    public Color[][][] topColors;
    public Color[][][] bottomColors;
    
   
   	//------End Variables------\\
   	
   	//~~~~~~Methods~~~~~~\\
   	
   	//Constructor 	
    public Cube() {
    	texture = new Texture();
    	pellet = new FuelPellet();
    	shields = new PlasmaShielding();
    	terminal = new Terminal();
    	trueFront = true;
    	trueBack = true;
    	trueLeft = true;
    	trueRight = true;
    	trueTop = true;
    	trueBottom = true;
    	started = false;
    }
    
    //Set the booleans to be the values from the main class
    public void changeBoolean(){
    	trueFront = MainClass.front;
    	trueBack = MainClass.back;
    	trueLeft = MainClass.left;
    	trueRight = MainClass.right;
    	trueTop = MainClass.top;
    	trueBottom = MainClass.bottom;
    } 
    	
    //Determine if the sides of the cube should be drawn
    public void clipping(){
    	allowedFront = true;
    	allowedBack = true;
    	allowedRight = true;
    	allowedLeft = true;
    	allowedTop = true;
    	allowedBottom = true;
    	front = trueFront;
    	back = trueBack;
    	right = trueRight;
    	left = trueLeft;
    	top = trueTop;
    	bottom = trueBottom;
    	if(MainClass.game.controls.z > Z){
    		bottom = false;
    	}
    	if(MainClass.game.controls.z < Z + 1){
    		top = false;
    	}
    	if(MainClass.game.controls.x > X){
    		left = false;
    	}
    	if(MainClass.game.controls.x < X + 1){
    		right = false;
    	}
    	if(MainClass.game.controls.y > Y){
    		front = false;
    	}
    	if(MainClass.game.controls.y < Y + 1){
    		back = false;
    	}
    	if(front){
    		if(topLeftFront.rotateW > 0){
				allowedFront = false;
			}
			if(topRightFront.rotateW > 0){
				allowedFront = false;
			}
			if(bottomLeftFront.rotateW > 0){
				allowedFront = false;
			}
			if(bottomRightFront.rotateW > 0){
				allowedFront = false;
			}
			
    	}
    	if(back){
    		if(topLeftBack.rotateW > 0){
				allowedBack = false;
			}
			if(topRightBack.rotateW > 0){
				allowedBack = false;
			}
			if(bottomRightBack.rotateW > 0){
				allowedBack = false;
			}
			if(bottomLeftBack.rotateW > 0){
				allowedBack = false;
			}
			
    	}
    	if(right){
    		if(topRightBack.rotateW > 0){
				allowedRight = false;
			}
			if(topRightFront.rotateW > 0){
				allowedRight = false;
			}
			if(bottomRightFront.rotateW > 0){
				allowedRight = false;
			}
			if(bottomRightBack.rotateW > 0){
				allowedRight = false;
			}
			
    	}
    	if(left){
    		if(topLeftFront.rotateW > 0){
				allowedLeft = false;
			}
			if(topLeftBack.rotateW > 0){
				allowedLeft = false;
			}
			if(bottomLeftBack.rotateW > 0){
				allowedLeft = false;
			}
			if(bottomLeftFront.rotateW > 0){
				allowedLeft = false;
			}
			
    	}
    	if(top){
    		if(topLeftFront.rotateW > 0){
				allowedTop = false;
			}
			if(topRightFront.rotateW > 0){
				allowedTop = false;
			}
			if(topRightBack.rotateW > 0){
				allowedTop = false;
			}
			if(topLeftBack.rotateW > 0){
				allowedTop = false;
			}
			
    	}
    	if(bottom){
    		if(bottomLeftFront.rotateW > 0){
				allowedBottom = false;
			}
			if(bottomRightFront.rotateW > 0){
				allowedBottom = false;
			}
			if(bottomRightBack.rotateW > 0){
				allowedBottom = false;
			}
			if(bottomLeftBack.rotateW > 0){
				allowedBottom = false;
			}
    	}
    	if(type == 4 || type == 5){
    		pellet.clipping();
    	}
    	if(type == 2 && (int)Z > 0 && (int)Z < 8){
    		shields.clipping();
    	}
    	if(type == 6){
       		terminal.clipping();
    	}
    }	
    
   	//Check collision with cube and other cubes in the world (cubes that can move vertically and fall with gravity)
    public void collision(int gd){
		if(gd == 5){
			if ((World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 1 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 7 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 2 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 3 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 9 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 8)){
				Z = ((int)Z);
			}
			else{
				Z -=.125;
			}
		}
		if(gd == 6){
			if((int)(Z+1) < 10){
			if ((World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 1 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 7 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 2 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 3 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 9 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 8)){
				Z = ((int)Z);
			}
			else{
				Z +=.125;
			}
			}
		}
		sendData(X,Y,Z);
	}
	
	//Check collision with cube and other cubes in the world (cubes that can move vertically and fall against gravity)
	public void collision2(int gd){
		if(gd == 6){
			if ((World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 1 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 7 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 2 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 3 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 9 || World.renderArray[(int)(Z-.1)][(int)Y][(int)X] == 8)){
				Z = ((int)Z);
			}
			else{
				Z -=.075;
			}
		}
		if(gd == 5){
			if((int)(Z+1) < 10){
			if ((World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 1 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 7 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 2 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 9 || World.renderArray[(int)(Z+1)][(int)Y][(int)X] == 8)){
				Z = ((int)Z);
			}
			else{
				Z +=.075;
			}
			}
		}
		sendData(X,Y,Z);
	}
	
	//Check collision with cube and other cubes in the world (cubes that can move horizontally)
	public void collision3(){
		if(moveDir == -1){
			if((int)(X-.1) > -1){
				if ((World.renderArray[(int)(Z)][(int)Y][(int)(X-.1)] > 0)){
					moveDir = 1;
				}
			}
		}else{
			if(moveDir == 1){
				if((int)(X+1) < 20){
					if ((World.renderArray[(int)(Z)][(int)Y][(int)(X+1)] > 0)){
						moveDir = -1;
					}
				}
			}
		}
		X+=(.1*(double)moveDir);
		sendData(X,Y,Z);
	}
	
	//Get the maximum value from a list
	public int max(int[] list){
		int max = list[0];
		for(int i = 1; i < list.length; i++){
			if(list[i] > max){
				max = list[i];
			}
		}
		return(max);
	}
	
	//Get the minimum value from a list
	public int min(int[] list){
		int min = list[0];
		for(int i = 1; i < list.length; i++){
			if(list[i] < min){
				min = list[i];
			}
		}
		return(min);
	}
	
	//Draw the faces of the cube
    public void draw(double x, double y, double z){
    	if(type == 1 || type == 3 || type == 7 || type == 8 || type == 9 || type == 10){
    		double distance = Math.sqrt(((X+.5 - MainClass.game.controls.x)*(X+.5 - MainClass.game.controls.x))+((Y+.5 - MainClass.game.controls.y)*(Y+.5 - MainClass.game.controls.y))+((Z+.5 - MainClass.game.controls.z)*(Z+.5 - MainClass.game.controls.z)));
			Polygon polygon = new Polygon();
			int delta = 1;
			if(distance > 3){
				delta = 2;
			}
			if(distance > 10){
				delta = 4;
			}
			int lx = texture.colors.length;
			int ly = texture.colors[0].length;
			int[][] dispX = new int[lx + 1][texture.colors[0].length + 1];
			int[][] dispY = new int[lx + 1][texture.colors[0].length + 1];
			if(front){
	    		if(allowedFront){
					double xx = (bottomLeftFront.rotateX - topLeftFront.rotateX) / (lx);
					double xy = (bottomLeftFront.rotateY - topLeftFront.rotateY) / (lx);
					double xz = (bottomLeftFront.rotateZ - topLeftFront.rotateZ) / (lx);
					double xw = (bottomLeftFront.rotateW - topLeftFront.rotateW) / (lx);
					double yx = (topRightFront.rotateX - topLeftFront.rotateX) / (ly);
					double yy = (topRightFront.rotateY - topLeftFront.rotateY) / (ly);
					double yz = (topRightFront.rotateZ - topLeftFront.rotateZ) / (ly);
					double yw = (topRightFront.rotateW - topLeftFront.rotateW) / (ly);
					for(int i = 0; i < dispX.length; i++){
						for(int j = 0; j < dispX[0].length; j++){
							dispX[i][j] = (int)((double)((topLeftFront.rotateX + i * xx + j * yx) * MainClass.WIDTH) / (2.0 * (topLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.WIDTH));
							dispY[i][j] = (int)((double)((topLeftFront.rotateZ + i * xz + j * yz) * MainClass.HEIGHT) / (2.0 * (topLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.HEIGHT));
						}
					}
	    			for(int i = 0; i < dispX.length - 1; i+=delta){
						for(int j = 0; j < dispX[0].length - 1; j+=delta){
							polygon.reset();
							polygon.addPoint(dispX[i][j],dispY[i][j]);
							polygon.addPoint(dispX[i+delta][j],dispY[i+delta][j]);
							polygon.addPoint(dispX[i+delta][j+delta],dispY[i+delta][j+delta]);
							polygon.addPoint(dispX[i][j+delta],dispY[i][j+delta]);
							g2d.setColor(frontColors[i][j][delta-1]);
							g2d.fillPolygon(polygon);
						}
					}
	    		}
    		}
    		if(back){
	    		if(allowedBack){
					double xx = (bottomLeftBack.rotateX - topLeftBack.rotateX) / (lx);
					double xy = (bottomLeftBack.rotateY - topLeftBack.rotateY) / (lx);
					double xz = (bottomLeftBack.rotateZ - topLeftBack.rotateZ) / (lx);
					double xw = (bottomLeftBack.rotateW - topLeftBack.rotateW) / (lx);
					double yx = (topRightBack.rotateX - topLeftBack.rotateX) / (ly);
					double yy = (topRightBack.rotateY - topLeftBack.rotateY) / (ly);
					double yz = (topRightBack.rotateZ - topLeftBack.rotateZ) / (ly);
					double yw = (topRightBack.rotateW - topLeftBack.rotateW) / (ly);
					for(int i = 0; i < dispX.length; i++){
						for(int j = 0; j < dispX[0].length; j++){
							dispX[i][j] = (int)((double)((topLeftBack.rotateX + i * xx + j * yx) * MainClass.WIDTH) / (2.0 * (topLeftBack.rotateW + i * xw + j * yw)) + (0.5 * MainClass.WIDTH));
							dispY[i][j] = (int)((double)((topLeftBack.rotateZ + i * xz + j * yz) * MainClass.HEIGHT) / (2.0 * (topLeftBack.rotateW + i * xw + j * yw)) + (0.5 * MainClass.HEIGHT));
						}
					}
	    			for(int i = 0; i < dispX.length - 1; i+=delta){
						for(int j = 0; j < dispX[0].length - 1; j+=delta){
						polygon.reset();
							polygon.addPoint(dispX[i][j],dispY[i][j]);
							polygon.addPoint(dispX[i+delta][j],dispY[i+delta][j]);
							polygon.addPoint(dispX[i+delta][j+delta],dispY[i+delta][j+delta]);
							polygon.addPoint(dispX[i][j+delta],dispY[i][j+delta]);
							g2d.setColor(backColors[i][j][delta-1]);
							g2d.fillPolygon(polygon);
						}
					}
	    		}
    		}
    		if(right){
	    		if(allowedRight){
					double xx = (bottomRightFront.rotateX - topRightFront.rotateX) / (lx);
					double xy = (bottomRightFront.rotateY - topRightFront.rotateY) / (lx);
					double xz = (bottomRightFront.rotateZ - topRightFront.rotateZ) / (lx);
					double xw = (bottomRightFront.rotateW - topRightFront.rotateW) / (lx);
					double yx = (topRightBack.rotateX - topRightFront.rotateX) / (ly);
					double yy = (topRightBack.rotateY - topRightFront.rotateY) / (ly);
					double yz = (topRightBack.rotateZ - topRightFront.rotateZ) / (ly);
					double yw = (topRightBack.rotateW - topRightFront.rotateW) / (ly);
					for(int i = 0; i < dispX.length; i++){
						for(int j = 0; j < dispX[0].length; j++){
							dispX[i][j] = (int)((double)((topRightFront.rotateX + i * xx + j * yx) * MainClass.WIDTH) / (2.0 * (topRightFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.WIDTH));
							dispY[i][j] = (int)((double)((topRightFront.rotateZ + i * xz + j * yz) * MainClass.HEIGHT) / (2.0 * (topRightFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.HEIGHT));
						}
					}
	    			for(int i = 0; i < dispX.length - 1; i+=delta){
						for(int j = 0; j < dispX[0].length - 1; j+=delta){
							polygon.reset();
							polygon.addPoint(dispX[i][j],dispY[i][j]);
							polygon.addPoint(dispX[i+delta][j],dispY[i+delta][j]);
							polygon.addPoint(dispX[i+delta][j+delta],dispY[i+delta][j+delta]);
							polygon.addPoint(dispX[i][j+delta],dispY[i][j+delta]);
							g2d.setColor(rightColors[i][j][delta-1]);
							g2d.fillPolygon(polygon);
						}
					}
    			}
    		}
    		if(left){
	    		if(allowedLeft){
					double xx = (bottomLeftFront.rotateX - topLeftFront.rotateX) / (lx);
					double xy = (bottomLeftFront.rotateY - topLeftFront.rotateY) / (lx);
					double xz = (bottomLeftFront.rotateZ - topLeftFront.rotateZ) / (lx);
					double xw = (bottomLeftFront.rotateW - topLeftFront.rotateW) / (lx);
					double yx = (topLeftBack.rotateX - topLeftFront.rotateX) / (ly);
					double yy = (topLeftBack.rotateY - topLeftFront.rotateY) / (ly);
					double yz = (topLeftBack.rotateZ - topLeftFront.rotateZ) / (ly);
					double yw = (topLeftBack.rotateW - topLeftFront.rotateW) / (ly);
					for(int i = 0; i < dispX.length; i++){
						for(int j = 0; j < dispX[0].length; j++){
							dispX[i][j] = (int)((double)((topLeftFront.rotateX + i * xx + j * yx) * MainClass.WIDTH) / (2.0 * (topLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.WIDTH));
							dispY[i][j] = (int)((double)((topLeftFront.rotateZ + i * xz + j * yz) * MainClass.HEIGHT) / (2.0 * (topLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.HEIGHT));
						}
					}
	    			for(int i = 0; i < dispX.length - 1; i+=delta){
						for(int j = 0; j < dispX[0].length - 1; j+=delta){
							polygon.reset();
							polygon.addPoint(dispX[i][j],dispY[i][j]);
							polygon.addPoint(dispX[i+delta][j],dispY[i+delta][j]);
							polygon.addPoint(dispX[i+delta][j+delta],dispY[i+delta][j+delta]);
							polygon.addPoint(dispX[i][j+delta],dispY[i][j+delta]);
							g2d.setColor(leftColors[i][j][delta-1]);
							g2d.fillPolygon(polygon);
						}
					}
	    		}
    		}
    		if(top){
	    		if(allowedTop){
					double xx = (topRightFront.rotateX - topLeftFront.rotateX) / (lx);
					double xy = (topRightFront.rotateY - topLeftFront.rotateY) / (lx);
					double xz = (topRightFront.rotateZ - topLeftFront.rotateZ) / (lx);
					double xw = (topRightFront.rotateW - topLeftFront.rotateW) / (lx);
					double yx = (topLeftBack.rotateX - topLeftFront.rotateX) / (ly);
					double yy = (topLeftBack.rotateY - topLeftFront.rotateY) / (ly);
					double yz = (topLeftBack.rotateZ - topLeftFront.rotateZ) / (ly);
					double yw = (topLeftBack.rotateW - topLeftFront.rotateW) / (ly);
					for(int i = 0; i < dispX.length; i++){
						for(int j = 0; j < dispX[0].length; j++){
							dispX[i][j] = (int)((double)((topLeftFront.rotateX + i * xx + j * yx) * MainClass.WIDTH) / (2.0 * (topLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.WIDTH));
							dispY[i][j] = (int)((double)((topLeftFront.rotateZ + i * xz + j * yz) * MainClass.HEIGHT) / (2.0 * (topLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.HEIGHT));
						}
					}
	    			for(int i = 0; i < dispX.length - 1; i+=delta){
						for(int j = 0; j < dispX[0].length - 1; j+=delta){
							polygon.reset();
							polygon.addPoint(dispX[i][j],dispY[i][j]);
							polygon.addPoint(dispX[i+delta][j],dispY[i+delta][j]);
							polygon.addPoint(dispX[i+delta][j+delta],dispY[i+delta][j+delta]);
							polygon.addPoint(dispX[i][j+delta],dispY[i][j+delta]);
							g2d.setColor(topColors[i][j][delta-1]);
							g2d.fillPolygon(polygon);
						}
					}
	    		}
    		}
    		if(bottom){
	    		if(allowedBottom){
					double xx = (bottomRightFront.rotateX - bottomLeftFront.rotateX) / (lx);
					double xy = (bottomRightFront.rotateY - bottomLeftFront.rotateY) / (lx);
					double xz = (bottomRightFront.rotateZ - bottomLeftFront.rotateZ) / (lx);
					double xw = (bottomRightFront.rotateW - bottomLeftFront.rotateW) / (lx);
					double yx = (bottomLeftBack.rotateX - bottomLeftFront.rotateX) / (ly);
					double yy = (bottomLeftBack.rotateY - bottomLeftFront.rotateY) / (ly);
					double yz = (bottomLeftBack.rotateZ - bottomLeftFront.rotateZ) / (ly);
					double yw = (bottomLeftBack.rotateW - bottomLeftFront.rotateW) / (ly);
					for(int i = 0; i < dispX.length; i++){
						for(int j = 0; j < dispX[0].length; j++){
							dispX[i][j] = (int)((double)((bottomLeftFront.rotateX + i * xx + j * yx) * MainClass.WIDTH) / (2.0 * (bottomLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.WIDTH));
							dispY[i][j] = (int)((double)((bottomLeftFront.rotateZ + i * xz + j * yz) * MainClass.HEIGHT) / (2.0 * (bottomLeftFront.rotateW + i * xw + j * yw)) + (0.5 * MainClass.HEIGHT));
						}
					}
	    			for(int i = 0; i < dispX.length - 1; i+=delta){
						for(int j = 0; j < dispX[0].length - 1; j+=delta){
							polygon.reset();
							polygon.addPoint(dispX[i][j],dispY[i][j]);
							polygon.addPoint(dispX[i+delta][j],dispY[i+delta][j]);
							polygon.addPoint(dispX[i+delta][j+delta],dispY[i+delta][j+delta]);
							polygon.addPoint(dispX[i][j+delta],dispY[i][j+delta]);
							g2d.setColor(bottomColors[i][j][delta-1]);
							g2d.fillPolygon(polygon);
						}
					}
	    		}
    		}
    	}
		if(type == 4 || type == 5){
			pellet.draw();
		}
		if(type == 2 && (int)Z > 0 && (int)Z < 8){
			shields.draw();
		}
		if(type == 6){
			terminal.draw();
		}
    }
    
    //Combine colors
    public Color addColor(Color color1, Color color2){
    	int r = color1.getRed() + color2.getRed();
    	int g = color1.getGreen() + color2.getGreen();
    	int b = color1.getBlue() + color2.getBlue();
    	if(r > 255){
    		r = 255;
    	}
    	if(g > 255){
    		g = 255;
    	}
    	if(b > 255){
    		b = 255;
    	}
    	return(new Color(r,g,b));
    }
    
    //Dot product
    public double dot(double x1, double y1, double z1, double x2, double y2, double z2){
    	return(x1*x2+y1*y2+z1*z2);
    }
    
    //Set the texture colors from light levels for the cube
    public void lighting(Color color){
    	if(type == 1 || type == 3 || type == 7 || type == 8 || type == 9 || type == 10){
	    	int lxh = texture.colors.length;
			int lyh = texture.colors[0].length;
			Color[][] colorArray = new Color[lxh +1][lyh + 1];
			for(int i = 0; i < frontColorsTrue.length; i++){
				for(int j = 0; j < frontColorsTrue[0].length; j++){
					frontColors[i][j][0] = frontColorsTrue[i][j];
					backColors[i][j][0] = backColorsTrue[i][j];	
					rightColors[i][j][0] = rightColorsTrue[i][j];	
					leftColors[i][j][0] = leftColorsTrue[i][j];	
					topColors[i][j][0] = topColorsTrue[i][j];	
					bottomColors[i][j][0] = bottomColorsTrue[i][j];	
				}
			}
			for(int k = 0; k < MainClass.lightSources.size(); k++){
				double x1 = X-MainClass.lightSources.get(k).X;
				double y1 = Y-MainClass.lightSources.get(k).Y;
				double z1 = Z-MainClass.lightSources.get(k).Z;
				double d = Math.sqrt((x1)*(x1)+(y1)*(y1)+(z1)*(z1));
				if(trueFront && dot(x1/d,y1/d,z1/d,0,-1,0) < 0){
					double xx = (bottomLeftFront.X - topLeftFront.X) / (lxh);
					double xy = (bottomLeftFront.Y - topLeftFront.Y) / (lxh);
					double xz = (bottomLeftFront.Z - topLeftFront.Z) / (lxh);
					double yx = (topRightFront.X - topLeftFront.X) / (lyh);
					double yy = (topRightFront.Y - topLeftFront.Y) / (lyh);
					double yz = (topRightFront.Z - topLeftFront.Z) / (lyh);
					double distance0 = Math.sqrt((topLeftFront.X-MainClass.lightSources.get(k).X-.5)*(topLeftFront.X-MainClass.lightSources.get(k).X-.5)+(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance1 = Math.sqrt((bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)*(bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)+(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance2 = Math.sqrt((topRightFront.X-MainClass.lightSources.get(k).X-.5)*(topRightFront.X-MainClass.lightSources.get(k).X-.5)+(topRightFront.Y-MainClass.lightSources.get(k).Y-.5)*(topRightFront.Y-MainClass.lightSources.get(k).Y-.5)+(topRightFront.Z-MainClass.lightSources.get(k).Z-.5)*(topRightFront.Z-MainClass.lightSources.get(k).Z-.5));
					int color0 = (int)(50/distance0);
					int color1 = (int)(50/distance1);
					int color2 = (int)(50/distance2);
					int dc1 = (color1-color0)/lxh;
					int dc2 = (color2-color0)/lyh;
					for(int i = 0; i < colorArray.length; i++){
						for(int j = 0; j < colorArray[0].length; j++){
							int colorVal = color0+dc1*i+dc2*j;
							if(colorVal > 255){
								colorVal = 255;
							}
							if(colorVal < 0){
								colorVal = 0;
							}
							colorArray[i][j] = new Color(colorVal, colorVal, colorVal);
						}
					}
					for(int i = 0; i < colorArray.length - 1; i++){
						for(int j = 0; j < colorArray[0].length - 1; j++){
							int r = 0;
							int g = 0;
							int b = 0;
							int val = (colorArray[i][j].getRed()+colorArray[i][j+1].getRed()+colorArray[i+1][j+1].getRed()+colorArray[i+1][j].getRed())/4;
							if(val > 255){
								val = 255;
							}
							frontColors[i][j][0] = addColor(frontColors[i][j][0],new Color(val,val,val));
						}
					}
				}
				if(trueBack && dot(x1/d,y1/d,z1/d,0,1,0) < 0){
					double xx = (bottomLeftBack.X - topLeftBack.X) / (lxh);
					double xy = (bottomLeftBack.Y - topLeftBack.Y) / (lxh);
					double xz = (bottomLeftBack.Z - topLeftBack.Z) / (lxh);
					double yx = (topRightBack.X - topLeftBack.X) / (lyh);
					double yy = (topRightBack.Y - topLeftBack.Y) / (lyh);
					double yz = (topRightBack.Z - topLeftBack.Z) / (lyh);
					double distance0 = Math.sqrt((topLeftBack.X-MainClass.lightSources.get(k).X-.5)*(topLeftBack.X-MainClass.lightSources.get(k).X-.5)+(topLeftBack.Y-MainClass.lightSources.get(k).Y-.5)*(topLeftBack.Y-MainClass.lightSources.get(k).Y-.5)+(topLeftBack.Z-MainClass.lightSources.get(k).Z-.5)*(topLeftBack.Z-MainClass.lightSources.get(k).Z-.5));
					double distance1 = Math.sqrt((bottomLeftBack.X-MainClass.lightSources.get(k).X-.5)*(bottomLeftBack.X-MainClass.lightSources.get(k).X-.5)+(bottomLeftBack.Y-MainClass.lightSources.get(k).Y-.5)*(bottomLeftBack.Y-MainClass.lightSources.get(k).Y-.5)+(bottomLeftBack.Z-MainClass.lightSources.get(k).Z-.5)*(bottomLeftBack.Z-MainClass.lightSources.get(k).Z-.5));
					double distance2 = Math.sqrt((topRightBack.X-MainClass.lightSources.get(k).X-.5)*(topRightBack.X-MainClass.lightSources.get(k).X-.5)+(topRightBack.Y-MainClass.lightSources.get(k).Y-.5)*(topRightBack.Y-MainClass.lightSources.get(k).Y-.5)+(topRightBack.Z-MainClass.lightSources.get(k).Z-.5)*(topRightBack.Z-MainClass.lightSources.get(k).Z-.5));
					int color0 = (int)(25/distance0);
					int color1 = (int)(25/distance1);
					int color2 = (int)(25/distance2);
					int dc1 = (color1-color0)/lxh;
					int dc2 = (color2-color0)/lyh;
					for(int i = 0; i < colorArray.length; i++){
						for(int j = 0; j < colorArray[0].length; j++){
							int colorVal = color0+dc1*i+dc2*j;
							if(colorVal > 255){
								colorVal = 255;
							}
							if(colorVal < 0){
								colorVal = 0;
							}
							colorArray[i][j] = new Color(colorVal, colorVal, colorVal);
						}
					}
					for(int i = 0; i < colorArray.length - 1; i++){
						for(int j = 0; j < colorArray[0].length - 1; j++){
							int r = 0;
							int g = 0;
							int b = 0;
							int val = (colorArray[i][j].getRed()+colorArray[i][j+1].getRed()+colorArray[i+1][j+1].getRed()+colorArray[i+1][j].getRed())/4;
							if(val > 255){
								val = 255;
							}
							backColors[i][j][0] = addColor(backColors[i][j][0],new Color(val,val,val));
						}
					}
				}
				if(trueRight && dot(x1/d,y1/d,z1/d,1,0,0) < 0){
					double xx = (bottomRightFront.X - topRightFront.X) / (lxh);
					double xy = (bottomRightFront.Y - topRightFront.Y) / (lxh);
					double xz = (bottomRightFront.Z - topRightFront.Z) / (lxh);
					double yx = (topRightBack.X - topRightFront.X) / (lyh);
					double yy = (topRightBack.Y - topRightFront.Y) / (lyh);
					double yz = (topRightBack.Z - topRightFront.Z) / (lyh);
					double distance0 = Math.sqrt((topLeftFront.X-MainClass.lightSources.get(k).X-.5)*(topLeftFront.X-MainClass.lightSources.get(k).X-.5)+(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance1 = Math.sqrt((bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)*(bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)+(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance2 = Math.sqrt((topRightBack.X-MainClass.lightSources.get(k).X-.5)*(topRightBack.X-MainClass.lightSources.get(k).X-.5)+(topRightBack.Y-MainClass.lightSources.get(k).Y-.5)*(topRightBack.Y-MainClass.lightSources.get(k).Y-.5)+(topRightBack.Z-MainClass.lightSources.get(k).Z-.5)*(topRightBack.Z-MainClass.lightSources.get(k).Z-.5));
					int color0 = (int)(25/distance0);
					int color1 = (int)(25/distance1);
					int color2 = (int)(25/distance2);
					int dc1 = (color1-color0)/lxh;
					int dc2 = (color2-color0)/lyh;
					for(int i = 0; i < colorArray.length; i++){
						for(int j = 0; j < colorArray[0].length; j++){
							int colorVal = color0+dc1*i+dc2*j;
							if(colorVal > 255){
								colorVal = 255;
							}
							if(colorVal < 0){
								colorVal = 0;
							}
							colorArray[i][j] = new Color(colorVal, colorVal, colorVal);
						}
					}
					for(int i = 0; i < colorArray.length - 1; i++){
						for(int j = 0; j < colorArray[0].length - 1; j++){
							int val = (colorArray[i][j].getRed()+colorArray[i][j+1].getRed()+colorArray[i+1][j+1].getRed()+colorArray[i+1][j].getRed())/4;
							if(val > 255){
								val = 255;
							}
							rightColors[i][j][0] = addColor(rightColors[i][j][0],new Color(val,val,val));
						}
					}
				}
				if(trueLeft && dot(x1/d,y1/d,z1/d,-1,0,0) < 0){
					double xx = (bottomLeftFront.X - topLeftFront.X) / (lxh);
					double xy = (bottomLeftFront.Y - topLeftFront.Y) / (lxh);
					double xz = (bottomLeftFront.Z - topLeftFront.Z) / (lxh);
					double yx = (topLeftBack.X - topLeftFront.X) / (lyh);
					double yy = (topLeftBack.Y - topLeftFront.Y) / (lyh);
					double yz = (topLeftBack.Z - topLeftFront.Z) / (lyh);
					double distance0 = Math.sqrt((topLeftFront.X-MainClass.lightSources.get(k).X-.5)*(topLeftFront.X-MainClass.lightSources.get(k).X-.5)+(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance1 = Math.sqrt((bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)*(bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)+(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance2 = Math.sqrt((topLeftBack.X-MainClass.lightSources.get(k).X-.5)*(topLeftBack.X-MainClass.lightSources.get(k).X-.5)+(topLeftBack.Y-MainClass.lightSources.get(k).Y-.5)*(topLeftBack.Y-MainClass.lightSources.get(k).Y-.5)+(topLeftBack.Z-MainClass.lightSources.get(k).Z-.5)*(topLeftBack.Z-MainClass.lightSources.get(k).Z-.5));
					int color0 = (int)(25/distance0);
					int color1 = (int)(25/distance1);
					int color2 = (int)(25/distance2);
					int dc1 = (color1-color0)/lxh;
					int dc2 = (color2-color0)/lyh;
					for(int i = 0; i < colorArray.length; i++){
						for(int j = 0; j < colorArray[0].length; j++){
							int colorVal = color0+dc1*i+dc2*j;
							if(colorVal > 255){
								colorVal = 255;
							}
							if(colorVal < 0){
								colorVal = 0;
							}
							colorArray[i][j] = new Color(colorVal, colorVal, colorVal);
						}
					}
					for(int i = 0; i < colorArray.length - 1; i++){
						for(int j = 0; j < colorArray[0].length - 1; j++){
							int r = 0;
							int g = 0;
							int b = 0;
							int val = (colorArray[i][j].getRed()+colorArray[i][j+1].getRed()+colorArray[i+1][j+1].getRed()+colorArray[i+1][j].getRed())/4;
							if(val > 255){
								val = 255;
							}
							leftColors[i][j][0] = addColor(leftColors[i][j][0],new Color(val,val,val));
						}
					}
				}
				if(trueTop && dot(x1/d,y1/d,z1/d,0,0,1) < 0){
					double xx = (topRightFront.X - topLeftFront.X) / (lxh);
					double xy = (topRightFront.Y - topLeftFront.Y) / (lxh);
					double xz = (topRightFront.Z - topLeftFront.Z) / (lxh);
					double yx = (topLeftBack.X - topLeftFront.X) / (lyh);
					double yy = (topLeftBack.Y - topLeftFront.Y) / (lyh);
					double yz = (topLeftBack.Z - topLeftFront.Z) / (lyh);
					double distance0 = Math.sqrt((topLeftFront.X-MainClass.lightSources.get(k).X-.5)*(topLeftFront.X-MainClass.lightSources.get(k).X-.5)+(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(topLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(topLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance1 = Math.sqrt((topRightFront.X-MainClass.lightSources.get(k).X-.5)*(topRightFront.X-MainClass.lightSources.get(k).X-.5)+(topRightFront.Y-MainClass.lightSources.get(k).Y-.5)*(topRightFront.Y-MainClass.lightSources.get(k).Y-.5)+(topRightFront.Z-MainClass.lightSources.get(k).Z-.5)*(topRightFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance2 = Math.sqrt((topLeftBack.X-MainClass.lightSources.get(k).X-.5)*(topLeftBack.X-MainClass.lightSources.get(k).X-.5)+(topLeftBack.Y-MainClass.lightSources.get(k).Y-.5)*(topLeftBack.Y-MainClass.lightSources.get(k).Y-.5)+(topLeftBack.Z-MainClass.lightSources.get(k).Z-.5)*(topLeftBack.Z-MainClass.lightSources.get(k).Z-.5));
					int color0 = (int)(25/distance0);
					int color1 = (int)(25/distance1);
					int color2 = (int)(25/distance2);
					int dc1 = (color1-color0)/lxh;
					int dc2 = (color2-color0)/lyh;
					for(int i = 0; i < colorArray.length; i++){
						for(int j = 0; j < colorArray[0].length; j++){
							int colorVal = color0+dc1*i+dc2*j;
							if(colorVal > 255){
								colorVal = 255;
							}
							if(colorVal < 0){
								colorVal = 0;
							}
							colorArray[i][j] = new Color(colorVal, colorVal, colorVal);
						}
					}
					for(int i = 0; i < colorArray.length - 1; i++){
						for(int j = 0; j < colorArray[0].length - 1; j++){
							int r = 0;
							int g = 0;
							int b = 0;
							int val = (colorArray[i][j].getRed()+colorArray[i][j+1].getRed()+colorArray[i+1][j+1].getRed()+colorArray[i+1][j].getRed())/4;
							if(val > 255){
								val = 255;
							}
							topColors[i][j][0] = addColor(topColors[i][j][0],new Color(val,val,val));
						}
					}
				}
				if(trueBottom && dot(x1/d,y1/d,z1/d,0,0,-1) < 0){
					double xx = (bottomRightFront.X - bottomLeftFront.X) / (lxh);
					double xy = (bottomRightFront.Y - bottomLeftFront.Y) / (lxh);
					double xz = (bottomRightFront.Z - bottomLeftFront.Z) / (lxh);
					double yx = (bottomLeftBack.X - bottomLeftFront.X) / (lyh);
					double yy = (bottomLeftBack.Y - bottomLeftFront.Y) / (lyh);
					double yz = (bottomLeftBack.Z - bottomLeftFront.Z) / (lyh);
					double distance0 = Math.sqrt((bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)*(bottomLeftFront.X-MainClass.lightSources.get(k).X-.5)+(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)*(bottomLeftFront.Y-MainClass.lightSources.get(k).Y-.5)+(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5)*(bottomLeftFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance1 = Math.sqrt((bottomRightFront.X-MainClass.lightSources.get(k).X-.5)*(bottomRightFront.X-MainClass.lightSources.get(k).X-.5)+(bottomRightFront.Y-MainClass.lightSources.get(k).Y-.5)*(bottomRightFront.Y-MainClass.lightSources.get(k).Y-.5)+(bottomRightFront.Z-MainClass.lightSources.get(k).Z-.5)*(bottomRightFront.Z-MainClass.lightSources.get(k).Z-.5));
					double distance2 = Math.sqrt((bottomLeftBack.X-MainClass.lightSources.get(k).X-.5)*(bottomLeftBack.X-MainClass.lightSources.get(k).X-.5)+(bottomLeftBack.Y-MainClass.lightSources.get(k).Y-.5)*(bottomLeftBack.Y-MainClass.lightSources.get(k).Y-.5)+(bottomLeftBack.Z-MainClass.lightSources.get(k).Z-.5)*(bottomLeftBack.Z-MainClass.lightSources.get(k).Z-.5));
					int color0 = (int)(25/distance0);
					int color1 = (int)(25/distance1);
					int color2 = (int)(25/distance2);
					int dc1 = (color1-color0)/lxh;
					int dc2 = (color2-color0)/lyh;
					for(int i = 0; i < colorArray.length; i++){
						for(int j = 0; j < colorArray[0].length; j++){
							int colorVal = color0+dc1*i+dc2*j;
							if(colorVal > 255){
								colorVal = 255;
							}
							if(colorVal < 0){
								colorVal = 0;
							}
							colorArray[i][j] = new Color(colorVal, colorVal, colorVal);
						}
					}
					for(int i = 0; i < colorArray.length - 1; i++){
						for(int j = 0; j < colorArray[0].length - 1; j++){
							int r = 0;
							int g = 0;
							int b = 0;
							int val = (colorArray[i][j].getRed()+colorArray[i][j+1].getRed()+colorArray[i+1][j+1].getRed()+colorArray[i+1][j].getRed())/4;
							if(val > 255){
								val = 255;
							}
							bottomColors[i][j][0] = addColor(bottomColors[i][j][0],new Color(val,val,val));
						}
					}
				}
			}
			for(int i = 0; i < colorArray.length - 1; i+=2){
				for(int j = 0; j < colorArray[0].length - 1; j+=2){
					int r1 = 0;
					int g1 = 0;
					int b1 = 0;
					int r2 = 0;
					int g2 = 0;
					int b2 = 0;
					int r3 = 0;
					int g3 = 0;
					int b3 = 0;
					int r4 = 0;
					int g4 = 0;
					int b4 = 0;
					int r5 = 0;
					int g5 = 0;
					int b5 = 0;
					int r6 = 0;
					int g6 = 0;
					int b6 = 0;
					for(int m = 0; m < 2; m++){
						for(int n = 0; n < 2; n++){
							if(trueFront){
								r1+=frontColors[i+m][j+n][0].getRed();
								g1+=frontColors[i+m][j+n][0].getGreen();
								b1+=frontColors[i+m][j+n][0].getBlue();
							}
							if(trueBack){
								r2+=backColors[i+m][j+n][0].getRed();
								g2+=backColors[i+m][j+n][0].getGreen();
								b2+=backColors[i+m][j+n][0].getBlue();
							}
							if(trueRight){
								r3+=rightColors[i+m][j+n][0].getRed();
								g3+=rightColors[i+m][j+n][0].getGreen();
								b3+=rightColors[i+m][j+n][0].getBlue();
							}
							if(trueLeft){
								r4+=leftColors[i+m][j+n][0].getRed();
								g4+=leftColors[i+m][j+n][0].getGreen();
								b4+=leftColors[i+m][j+n][0].getBlue();
							}
							if(trueTop){
								r5+=topColors[i+m][j+n][0].getRed();
								g5+=topColors[i+m][j+n][0].getGreen();
								b5+=topColors[i+m][j+n][0].getBlue();
							}
							if(trueBottom){
								r6+=bottomColors[i+m][j+n][0].getRed();
								g6+=bottomColors[i+m][j+n][0].getGreen();
								b6+=bottomColors[i+m][j+n][0].getBlue();
							}
						}
					}
					r1/=4;
					g1/=4;
					b1/=4;
					r2/=4;
					g2/=4;
					b2/=4;
					r3/=4;
					g3/=4;
					b3/=4;
					r4/=4;
					g4/=4;
					b4/=4;
					r5/=4;
					g5/=4;
					b5/=4;
					r6/=4;
					g6/=4;
					b6/=4;
					frontColors[i][j][1] = new Color(r1,g1,b1);
					backColors[i][j][1] = new Color(r2,g2,b2);
					rightColors[i][j][1] = new Color(r3,g3,b3);
					leftColors[i][j][1] = new Color(r4,g4,b4);
					topColors[i][j][1] = new Color(r5,g5,b5);
					bottomColors[i][j][1] = new Color(r6,g6,b6);	
				}
			}
			for(int i = 0; i < colorArray.length - 1; i+=4){
				for(int j = 0; j < colorArray[0].length - 1; j+=4){
					int r1 = 0;
					int g1 = 0;
					int b1 = 0;
					int r2 = 0;
					int g2 = 0;
					int b2 = 0;
					int r3 = 0;
					int g3 = 0;
					int b3 = 0;
					int r4 = 0;
					int g4 = 0;
					int b4 = 0;
					int r5 = 0;
					int g5 = 0;
					int b5 = 0;
					int r6 = 0;
					int g6 = 0;
					int b6 = 0;
					for(int m = 0; m < 4; m++){
						for(int n = 0; n < 4; n++){
							if(trueFront){
								r1+=frontColors[i+m][j+n][0].getRed();
								g1+=frontColors[i+m][j+n][0].getGreen();
								b1+=frontColors[i+m][j+n][0].getBlue();
							}
							if(trueBack){
								r2+=backColors[i+m][j+n][0].getRed();
								g2+=backColors[i+m][j+n][0].getGreen();
								b2+=backColors[i+m][j+n][0].getBlue();
							}
							if(trueRight){
								r3+=rightColors[i+m][j+n][0].getRed();
								g3+=rightColors[i+m][j+n][0].getGreen();
								b3+=rightColors[i+m][j+n][0].getBlue();
							}
							if(trueLeft){
								r4+=leftColors[i+m][j+n][0].getRed();
								g4+=leftColors[i+m][j+n][0].getGreen();
								b4+=leftColors[i+m][j+n][0].getBlue();
							}
							if(trueTop){
								r5+=topColors[i+m][j+n][0].getRed();
								g5+=topColors[i+m][j+n][0].getGreen();
								b5+=topColors[i+m][j+n][0].getBlue();
							}
							if(trueBottom){
								r6+=bottomColors[i+m][j+n][0].getRed();
								g6+=bottomColors[i+m][j+n][0].getGreen();
								b6+=bottomColors[i+m][j+n][0].getBlue();
							}
						}
					}
					r1/=16;
					g1/=16;
					b1/=16;
					r2/=16;
					g2/=16;
					b2/=16;
					r3/=16;
					g3/=16;
					b3/=16;
					r4/=16;
					g4/=16;
					b4/=16;
					r5/=16;
					g5/=16;
					b5/=16;
					r6/=16;
					g6/=16;
					b6/=16;
					frontColors[i][j][3] = new Color(r1,g1,b1);
					backColors[i][j][3] = new Color(r2,g2,b2);
					rightColors[i][j][3] = new Color(r3,g3,b3);
					leftColors[i][j][3] = new Color(r4,g4,b4);
					topColors[i][j][3] = new Color(r5,g5,b5);
					bottomColors[i][j][3] = new Color(r6,g6,b6);	
				}
			}
    	}
    }
    
    //Load the cube's texture
    public void loadTexture(){
    	if(MainClass.betterTextures){
	    	if(type == 1){
	    		texture.loadTexture("texture_1");
	    	}
	    	if(type == 3){
	    		texture.loadTexture("texture_3");
	    	}
	    	if(type == 7){
	    		texture.loadTexture("texture_7");
	    	}
	    	if(type == 8){
	    		texture.loadTexture("texture_8");
	    	}
	    	if(type == 9){
	    		texture.loadTexture("texture_9");
	    	}
	    	if(type == 10){
	    		texture.loadTexture("texture_10");
	    	}
    	}
    	else{
    		if(type == 1){
	    		texture.loadTexture("texture_1_fast");
	    	}
	    	if(type == 3){
	    		texture.loadTexture("texture_3_fast");
	    	}
	    	if(type == 7){
	    		texture.loadTexture("texture_7_fast");
	    	}
	    	if(type == 8){
	    		texture.loadTexture("texture_8_fast");
	    	}
	    	if(type == 9){
	    		texture.loadTexture("texture_9_fast");
	    	}
	    	if(type == 10){
	    		texture.loadTexture("texture_10_fast");
	    	}
    	}
    	if(type == 1 || type == 3 || type == 7 || type == 8 || type == 9 || type == 10){
    		frontColorsTrue = texture.colors;
    		backColorsTrue = texture.colors;
	    	rightColorsTrue = texture.colors;
	    	leftColorsTrue = texture.colors;
	    	topColorsTrue = texture.colors;
	    	bottomColorsTrue = texture.colors;
	    	frontColors = new Color[texture.colors.length][texture.colors[0].length][4];
	    	backColors = new Color[texture.colors.length][texture.colors[0].length][4];
	    	rightColors = new Color[texture.colors.length][texture.colors[0].length][4];
	    	leftColors = new Color[texture.colors.length][texture.colors[0].length][4];
	    	topColors = new Color[texture.colors.length][texture.colors[0].length][4];
	    	bottomColors = new Color[texture.colors.length][texture.colors[0].length][4];
    	}
    }
    
  	//Rotate the points of the cube
    public void rotate(double PlayerX, double PlayerY, double PlayerZ){
    	topLeftFront.rotate();
    	topRightFront.rotate();
    	bottomRightFront.rotate();
    	bottomLeftFront.rotate();
    	topRightBack.rotate();
    	topLeftBack.rotate();
    	bottomRightBack.rotate();
    	bottomLeftBack.rotate();
    	if(type == 4 || type == 5){
    		pellet.rotate();
    		pellet.sort(PlayerX,PlayerY,PlayerZ);
    	}
    	if(type == 2 && (int)Z > 0 && (int)Z < 8){
    		shields.rotate();
    		shields.sort(PlayerX,PlayerY,PlayerZ);
    	}
    	if(type == 6){
    		terminal.rotate();
    		terminal.sort(PlayerX,PlayerY,PlayerZ);
    	}
    }
    
    //Set the point data for the cube
    public void sendData(double x,double y,double z){
    	X = x;
    	Y = y;
    	Z = z;
		topRightFront = new Point3D(x+1,y,z+1);
		topLeftFront = new Point3D(x,y,z+1);
		bottomRightFront = new Point3D(x+1,y,z);
		bottomLeftFront = new Point3D(x,y,z);
		topRightBack = new Point3D(x+1,y+1,z+1);
		topLeftBack = new Point3D(x,y+1,z+1);
		bottomRightBack = new Point3D(x+1,y+1,z);
		bottomLeftBack = new Point3D(x,y+1,z);
    	pellet.set(x,y,z);
    	pellet.sendData();
    	shields.set(x,y,z);
    	shields.sendData();
    	terminal.set(x,y,z);
    	terminal.sendData();
    }
    
    //Set the display arays for the mesh models for the pellets, shields, and terminals
    public void setArrays(){
    	if(type == 4 || type == 5){
    		pellet.setArrays();
    	}
    	if(type == 2 && (int)Z > 0 && (int)Z < 8){
    		shields.setArrays();
    	}
    	if(type == 6){
    		terminal.setArrays();
    	}
    }
    
    //Set the colors for the pellets, shields, and terminals
    public void setColor(Color color, Graphics2D g){
    	g2d = g;	
    	if(type == 4 || type == 5){
    		pellet.setColor(Color.green,g);
    	}
    	if(type == 2){
    		shields.setColor(Color.red,g);
    	}
    	if(type == 6){
    		terminal.setColor(Color.yellow,g);
    	}
    }

	//Set the display values for the models
    public void setDispVals(){
    	if(type == 4 || type == 5){
    		pellet.setDispVals();
    	}
    	if(type == 2 && (int)Z > 0 && (int)Z < 8){
    		shields.setDispVals();
    	}
    	if(type == 6){
    		terminal.setDispVals();
    	}
    }
  
    //~~~~~~End Methods~~~~~~\\
    
}

//******End Class******\\