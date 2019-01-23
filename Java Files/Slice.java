/**
 *Class - Slice
 *Author - John Carter
 *Last Updated - 12/2/13
 */

//======Import Statements======\\

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

//======End Import Statements======\\

//******Begin Class******\\

class Slice{

	//------Variables------\\

	public int y;
	public long startTime = 0;
	public Cube[][] cubes = new Cube[9][8];
	
	//------End Variables------\\

	//~~~~~~Constructor~~~~~~\\

	public Slice(){
		for(int z = 0; z < 9; z++){
			for(int x = 0; x < 8; x++){
				cubes[z][x] = new Cube();
			}
		}
	}
	
	//~~~~~~End COnstructor~~~~~~\\
	
	//~~~~~~Methods~~~~~~\\
	
	//Checks to see if the cube has any adjacent to it and if so do not draw the wall that is covered
	public void checkSides(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type > 0){
					MainClass.checkDir((int)cubes[i][j].X,(int)cubes[i][j].Y,(int)cubes[i][j].Z);
					cubes[i][j].changeBoolean(MainClass.front,MainClass.back,MainClass.left,MainClass.right,MainClass.top,MainClass.bottom);
				}
			}
		}
	}
	
	//Clips the cubes against the player's field of vision
	public void clipping(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type > 0){
					cubes[i][j].clipping();
				}
			}
		}
	}
	
	//Checks to see if the mobile blocks have collided with any others
	public void cubeCollision(int gd){
		for(int i = 8; i > 0; i--){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type == 9){
					cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 0;
					World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 0;
					cubes[i][j].collision(gd);
					cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].sendData(cubes[i][j].X,cubes[i][j].Y,cubes[i][j].Z);
					cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 9;
					World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 9;
				}else{
					if(cubes[i][j].type == 8){
						cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 0;
						World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 0;
						cubes[i][j].collision2(gd);
						cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].sendData(cubes[i][j].X,cubes[i][j].Y,cubes[i][j].Z);
						cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 8;
						World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 8;
					}else{
						if(cubes[i][j].type == 5){
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 0;
							int oldX = (int)cubes[i][j].X-6;
							int oldZ = (int)cubes[i][j].Z;
							World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 0;
							cubes[i][j].collision3();
							int m = cubes[oldZ][oldX].moveDir;
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].sendData(cubes[i][j].X,cubes[i][j].Y,cubes[i][j].Z);
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 5;
							World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 5;
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].moveDir = m;
						}else{
							if(cubes[i][j].type == 4){
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 0;
							int oldX = (int)cubes[i][j].X-6;
							int oldZ = (int)cubes[i][j].Z;
							World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 0;
							cubes[i][j].collision3();
							int m = cubes[oldZ][oldX].moveDir;
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].sendData(cubes[i][j].X,cubes[i][j].Y,cubes[i][j].Z);
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 4;
							World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 4;
							cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].moveDir = m;
							}else{
								if(cubes[i][j].type == 3){
									if(System.currentTimeMillis() - startTime >= 500) {
										if(cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].started){
											cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 0;
											cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].started = false;
											World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 0;
											cubes[i][j].collision(gd);
											cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].sendData(cubes[i][j].X,cubes[i][j].Y,cubes[i][j].Z);
											cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].type = 3;
											cubes[(int)cubes[i][j].Z][(int)cubes[i][j].X-6].started = true;
											World.renderArray[(int)cubes[i][j].Z][(int)cubes[i][j].Y][(int)cubes[i][j].X] = 3;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//Draws the cubes
	public void draw(double X, double Y, double Z){
		for(int z = 0; z < Z; z++){
			for(int x = 0; x+6 < X; x++){
				if(cubes[z][x].type > 0){
					cubes[z][x].draw(X,Y,Z);
				}
			}
			for(int x = 7; x+6 > X; x--){
				if(cubes[z][x].type > 0){
					cubes[z][x].draw(X,Y,Z);
				}
			}
			if(cubes[z][(int)X-6].type > 0){
					cubes[z][(int)X-6].draw(X,Y,Z);
			}
		}
		for(int z = 8; z > Z && z > -1; z--){
			for(int x = 0; x+6 < X; x++){
				if(cubes[z][x].type > 0){
					cubes[z][x].draw(X,Y,Z);
				}
			}
			for(int x = 7; x+6 > X; x--){
				if(cubes[z][x].type > 0){
					cubes[z][x].draw(X,Y,Z);
				}
			}
			if(cubes[z][(int)X-6].type > 0){
					cubes[z][(int)X-6].draw(X,Y,Z);
			}
		}
		for(int x = 0; x+6 < X; x++){
			if(cubes[(int)Z][x].type > 0){
				cubes[(int)Z][x].draw(X,Y,Z);
			}
		}
		for(int x = 7; x+6 > X; x--){
			if(cubes[(int)Z][x].type > 0){
				cubes[(int)Z][x].draw(X,Y,Z);
			}
		}
		if(cubes[(int)Z][(int)X-6].type > 0){
				cubes[(int)Z][(int)X-6].draw(X,Y,Z);
		}
	}
	
	//Sets the lighting for the cubes
	public void lighting(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type == 1 || cubes[i][j].type == 10){
					cubes[i][j].lighting(Color.white);
				}
				if(cubes[i][j].type == 2 || cubes[i][j].type == 4 || cubes[i][j].type == 5){
					cubes[i][j].lighting(Color.red);
				}
				if(cubes[i][j].type == 3){
					cubes[i][j].lighting(Color.yellow);
				}
				if(cubes[i][j].type == 7){
					cubes[i][j].lighting(Color.green);
				}
				if(cubes[i][j].type == 8){
					cubes[i][j].lighting(Color.cyan);
				}
				if(cubes[i][j].type == 9){
					cubes[i][j].lighting(Color.blue);
				}
				if(cubes[i][j].type == 11){
					cubes[i][j].lighting(Color.blue);
				}
				if(cubes[i][j].type == 6){
					cubes[i][j].lighting(Color.magenta);
				}
			}
		}
	}
	
	//Rotates all of the cubes in the slice
	public void rotate(double playerX, double playerY, double playerZ){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type > 0){
					cubes[i][j].rotate(playerX, playerY, playerZ);
				}
			}
		}
	}
	
	//Sets the display arrays for the cubes
	public void setArrays(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type > 0){
					cubes[i][j].setArrays();
				}
			}
		}
	}
	
	//Sets the color of the cubes
	public void setColor(Color color, Graphics2D g, Graphics2D g2){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type == 1 || cubes[i][j].type == 10){
					cubes[i][j].setColor(Color.white,g,g2);
				}
				if(cubes[i][j].type == 2 || cubes[i][j].type == 4 || cubes[i][j].type == 5){
					cubes[i][j].setColor(Color.red,g,g2);
				}
				if(cubes[i][j].type == 3){
					cubes[i][j].setColor(Color.yellow,g,g2);
				}
				if(cubes[i][j].type == 7){
					cubes[i][j].setColor(Color.green,g,g2);
				}
				if(cubes[i][j].type == 8){
					cubes[i][j].setColor(Color.cyan,g,g2);
				}
				if(cubes[i][j].type == 9){
					cubes[i][j].setColor(Color.blue,g,g2);
				}
				if(cubes[i][j].type == 11){
					cubes[i][j].setColor(Color.blue,g,g2);
				}
				if(cubes[i][j].type == 6){
					cubes[i][j].setColor(Color.magenta,g,g2);
				}
			}
		}
	}
	
	//Sets the display values for the cubes
	public void setDispVals(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 8; j++){
				if(cubes[i][j].type > 0){
					cubes[i][j].setDispVals();
				}	
			}	
		}
	}
	
	//Sets the move direction for all blocks
	public void setMoveDir(){
		for(int z = 0; z < 9; z++){
			for(int x = 0; x < 8; x++){
				if(cubes[z][x].type == 5){
					cubes[z][x].moveDir = -1;
				}else{
					cubes[z][x].moveDir = 1;
				}
			}
		}
	}
	
	//Sets the points for all blocks and sets what type of block they are
	public void setPoints(){
		for(int z = 0; z < 9; z++){
			for(int x = 0; x < 8; x++){
				cubes[z][x] = new Cube();	
				cubes[z][x].type = World.renderArray[z][y][x+6];
				cubes[z][x].sendData(x+6,y,z);
			}
		}
	}

	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\