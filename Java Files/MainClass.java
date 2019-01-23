/**
 *Class - MainClass
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.VolatileImage;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.BasicStroke;
import java.util.ArrayList;

//======End Import Statements======\\

//******Begin Class******\\

public class MainClass extends JPanel{

	//------Variables------\\
	
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension dim = toolkit.getScreenSize();
	public static JPanel window = new JPanel();
	public static int WIDTH = dim.width;
	public static int HEIGHT = dim.height;
	public static MainClass mainClass;
	public static JFrame frame;
	public static Game game = new Game();
	public static InputHandler input;
	public static World world;
	public static boolean front;
	public static boolean back;
	public static boolean left;
	public static boolean right;
	public static boolean top;
	public static boolean bottom;
	public static FPSCounter fps;
	public static long startTime = 0;
	public static boolean alive;
	public static boolean moved;
	public static boolean pause = false;
	public static boolean start = false;
	public static boolean retro = false;
	public static hotBar hotbar = new hotBar();
	public static int placeVal = 6;
	public static int hudOn = 2;
	public static LoadingScreen loadingScreen;
	public static long nanoTime = 0;
	public static boolean custom = false;
	public static int mouseYOffset;
	public static int mouseXOffset;
	public static Image space = toolkit.getImage("space.jpg");
    public static Image scaledSpace = space.getScaledInstance(WIDTH,HEIGHT,Image.SCALE_DEFAULT);
    public static Image helmet = toolkit.getImage("Resources/Images/helmet.png");
    public static Image scaledHelmet = helmet.getScaledInstance(WIDTH,HEIGHT,Image.SCALE_DEFAULT);
    public static boolean loading;
    public static Thread drawingThread;
    public static Runnable drawingRunnable;
    public static Matrix translationMatrix;
    public static Matrix viewMatrix;
    public static Matrix projectionMatrix;
    public static Matrix viewProjectionTransform;
    public static float sy,cy,sp,cp,sr,cr;
    public static Point3D frontVector;
    public static boolean oneWayFlag;
    public static double[] distances;
    public static double[] phiVals;
    public static double[] thetaVals;
    public static Cube[] cubes;
    public static long timeSinceTick;
    public static ArrayList<Cube> allCubes;
    public static double H;
    public static double V;
    public static boolean betterTextures = true;
    public static ArrayList<Cube> lightSources;
    public Image offscreenImage;
    public Dimension offscreenDimension;
    public Graphics2D g2d;
    public VolatileImage volatileImg;
    
	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\

	//Constructor
	public MainClass(){
		timeSinceTick = 0;
		input = new InputHandler();
		world = new World();
		setFocusTraversalKeysEnabled(false);
		addKeyListener(input);
		addFocusListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		game.controls.x = 10.01;
		game.controls.y = 2.5;
		fps = new FPSCounter();
		alive = true;
		distances = new double[4096];
		phiVals = new double[4096];
		thetaVals = new double[4096];
		cubes = new Cube[4096];
		new robot();
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
		allCubes = new ArrayList<Cube>();
		lightSources = new ArrayList<Cube>();
	}
	
	//Checks for any adjacent blocks to the block in question
	public static void checkDir(int index){
		front = true;
		back = true;
		left = true;
		right = true;
		top = true;
		bottom = true;
		int i = (int)allCubes.get(index).X;
		int j = (int)allCubes.get(index).Y;
		int k = (int)allCubes.get(index).Z;
		if(j < 29){
			if(world.renderArray[k][j+1][i] == 1 || world.renderArray[k][j+1][i] == 2 || world.renderArray[k][j+1][i] == 7){
				if(world.renderArray[k][j][i] != 9 && world.renderArray[k][j][i] != 8 && world.renderArray[k][j][i] != 5){	
					back = false;
				}
			}
		}
		if(j > 0){
			if(world.renderArray[k][j-1][i] == 1 || world.renderArray[k][j-1][i] == 2 || world.renderArray[k][j-1][i] == 7){
				if(world.renderArray[k][j][i] != 9 && world.renderArray[k][j][i] != 8 && world.renderArray[k][j][i] != 5){
					front = false;
				}
			}
		}
		if(i < 19){
			if(world.renderArray[k][j][i+1] == 1 || world.renderArray[k][j][i+1] == 2 || world.renderArray[k][j][i+1] == 7){
				if(world.renderArray[k][j][i] != 9 && world.renderArray[k][j][i] != 8 && world.renderArray[k][j][i] != 5){
					right = false;
				}
			}
		}
		if(i > 0){
			if(world.renderArray[k][j][i-1] == 1 || world.renderArray[k][j][i-1] == 2 || world.renderArray[k][j][i-1] == 7){
				if(world.renderArray[k][j][i] != 9 && world.renderArray[k][j][i] != 8 && world.renderArray[k][j][i] != 5){
					left = false;
				}
			}
		}
		if(k < 8){
			if(world.renderArray[k+1][j][i] == 1 || world.renderArray[k+1][j][i] == 2 || world.renderArray[k+1][j][i] == 7){
				if(world.renderArray[k][j][i] != 9 && world.renderArray[k][j][i] != 8 && world.renderArray[k][j][i] != 5){
					top = false;
				}
			}
		}
		if(k > 0){
			if(world.renderArray[k-1][j][i] == 1 || world.renderArray[k-1][j][i] == 2 || world.renderArray[k-1][j][i] == 7){
				if(world.renderArray[k][j][i] != 9 && world.renderArray[k][j][i] != 8 && world.renderArray[k][j][i] != 5){
					bottom = false;
				}
			}
		}
	}
	
	//Collision detection
	public static void collision(double j, double i, double h, int w){
		if(w > 0){
			Point3D player = new Point3D(game.controls.x,game.controls.y,game.controls.z);
			BoundingBox left = new BoundingBox(new Point3D(j, i + 1, h + 1), new Point3D(j - .2, i, h));
			BoundingBox right = new BoundingBox(new Point3D(j + 1.2, i + 1, h + 1), new Point3D(j + 1, i, h));
			BoundingBox front = new BoundingBox(new Point3D(j + 1, i, h + 1), new Point3D(j, i - .2, h));
			BoundingBox back = new BoundingBox(new Point3D(j + 1, i + 1.2, h + 1), new Point3D(j, i + 1, h));
			if(left.contains(player)){
				game.controls.x = j - .2;
				if(w == 2 || w == 4 || w == 5){
					MainClass.load();
				}
				if(w == 6){
					if(!custom){
						if(world.levelNum == 33){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;			
						MainClass.load();
					}else{
						if(world.levelNum == world.numOfCustomLevels){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;	
						MainClass.load();
					}
				}
				if(w == 3){
					int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
					cubes[hash].started = true;
				}
			}
			if(right.contains(player)){
				game.controls.x = j + 1.2;
				if(w == 2 || w == 4 || w == 5){
					MainClass.load();
				}
				if(w == 6){
					if(!custom){
						if(world.levelNum == 33){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;			
						MainClass.load();
					}else{
						if(world.levelNum == world.numOfCustomLevels){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;	
						MainClass.load();
					}
				}
				if(w == 3){
					int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
					cubes[hash].started = true;
				}
			}
			if(front.contains(player)){
				game.controls.y = i - .2;
				if(w == 2 || w == 4 || w == 5){
					MainClass.load();
				}
				if(w == 6){
					if(!custom){
						if(world.levelNum == 33){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;			
						MainClass.load();
					}else{
						if(world.levelNum == world.numOfCustomLevels){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;	
						MainClass.load();
					}
				}
				if(w == 3){
					int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
					cubes[hash].started = true;
				}
			}
			if(back.contains(player)){
				game.controls.y = i + 1.2;
				if(w == 2 || w == 4 || w == 5){
					MainClass.load();
				}
				if(w == 6){
					if(!custom){
						if(world.levelNum == 33){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;			
						MainClass.load();
					}else{
						if(world.levelNum == world.numOfCustomLevels){
							alive = false;
							VictoryScreen victoryScreen = new VictoryScreen();
							victoryScreen.drawButton();
							while(!victoryScreen.start){
								System.out.print("");
							}
						}
						world.levelNum ++;	
						MainClass.load();
					}
				}
				if(w == 3){
					int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
					cubes[hash].started = true;
				}
			}
			if(game.controls.gd == 5){
				BoundingBox bottom = new BoundingBox(new Point3D(j + 1, i + 1, h), new Point3D(j, i, h - .2));
				BoundingBox top = new BoundingBox(new Point3D(j + 1, i + 1, h + 2.5), new Point3D(j, i, h + 1));
				if(bottom.contains(player)){
					game.controls.z = h - .2;
					if(w == 2 || w == 4 || w == 5){
						MainClass.load();
					}
					if(w == 6){
						if(!custom){
							if(world.levelNum == 33){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;			
							MainClass.load();
						}else{
							if(world.levelNum == world.numOfCustomLevels){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;	
							MainClass.load();
						}
					}
					if(w == 3){
						int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
						cubes[hash].started = true;
					}
				}
				if(top.contains(player)){
					game.controls.z = h + 2.5;
					game.controls.jumping = false;
					game.controls.velocityZ1 = 0;
					game.controls.radians = 0;
					game.controls.velocityX1 = 0;
					game.controls.gravVectorX1 = 0;
					game.controls.gravVectorZ1 = 0;
					game.controls.oldRadians = 0;
					if(w == 2 || w == 4 || w == 5){
						MainClass.load();
					}
					if(w == 6){
						if(!custom){
							if(world.levelNum == 33){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;			
							MainClass.load();
						}else{
							if(world.levelNum == world.numOfCustomLevels){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;	
							MainClass.load();
						}
					}
					if(w == 3){
						int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
						cubes[hash].started = true;
					}
				}
			}
			if(game.controls.gd == 6){
				BoundingBox bottom = new BoundingBox(new Point3D(j + 1, i + 1, h), new Point3D(j, i, h - 1.5));
				BoundingBox top = new BoundingBox(new Point3D(j + 1, i + 1, h + 1.2), new Point3D(j, i, h + 1));
				if(bottom.contains(player)){
					game.controls.z = h - 1.5;
					game.controls.jumping = false;
					game.controls.velocityZ1 = 0;
					game.controls.radians = 0;
					game.controls.velocityX1 = 0;
					game.controls.gravVectorX1 = 0;
					game.controls.gravVectorZ1 = 0;
					game.controls.oldRadians = 0;
					if(w == 2 || w == 4 || w == 5){
						MainClass.load();
					}
					if(w == 6){
						if(!custom){
							if(world.levelNum == 33){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;			
							MainClass.load();
						}else{
							if(world.levelNum == world.numOfCustomLevels){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;	
							MainClass.load();
						}
					}
					if(w == 3){
						int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
						cubes[hash].started = true;
					}
				}
				if(top.contains(player)){
					game.controls.z = h + 1.2;
					if(w == 2 || w == 4 || w == 5){
						MainClass.load();
					}
					if(w == 6){
						if(!custom){
							if(world.levelNum == 33){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;			
							MainClass.load();
						}else{
							if(world.levelNum == world.numOfCustomLevels){
								alive = false;
								VictoryScreen victoryScreen = new VictoryScreen();
								victoryScreen.drawButton();
								while(!victoryScreen.start){
									System.out.print("");
								}
							}
							world.levelNum ++;	
							MainClass.load();
						}
					}
					if(w == 3){
						int hash = Integer.parseInt(String.valueOf((int)i)+String.valueOf((int)j-6)+String.valueOf((int)h)) % cubes.length;
						cubes[hash].started = true;
					}
				}
			}
			BoundingBox interior = new BoundingBox(new Point3D(j + 1, i + 1, h + 1), new Point3D(j, i, h));
			if(interior.contains(player)){
				MainClass.load();
			}
		}
	}
		
	//Clears screen and calls for objects to be drawn
    public void drawing(){
		repaint();
	}
	
	//Starts up the launcher
	public static void launch(){
		Launcher launcher = new Launcher();
		launcher.drawButton();
		while(start == false){
			System.out.print("");
		}
		launcher.dispose();
		world.load();
		new robot();
		MainClass.startup();
	}
	
	//loads the level
	public static void load(){
		loading = true;
		loadingScreen = new LoadingScreen();
		input.focusLost = false;
  		game.controls.y = 2.5;
		game.controls.x = 10.01;
		game.controls.z = 5;
		game.controls.gd = 5;
		game.controls.flip = 0;
		game.controls.pitch = 0;
		game.controls.yaw = 0;
		game.controls.roll = 0;
		game.controls.velocityZ1 = 0;
		game.controls.gravVectorZ1 = 0;
		game.controls.oldRadians = 0;
		game.controls.radians = 0;
		game.controls.tempVelocityZ = 0;
		game.controls.jumping = false;
		float fov = 1;
		H = Math.PI/4;
		V = Math.PI/4;
		float aspectRatio = (float)WIDTH/(float)HEIGHT;
		aspectRatio = (float)(Math.tan(H/2)/Math.tan(V/2));
		float zFar = 50f;
   	 	float zNear = 1f;
   	 	float q = zFar / (zFar - zNear);
		projectionMatrix = new Matrix(aspectRatio,0,0,0,0,fov,0,0,0,0,q,1,0,0,-q*zNear,0);
		world.load();
		cubes = new Cube[4096];
		game.controls.lookMultiplier = 1;
		allCubes.clear();
		lightSources.clear();
		int counter = 0;
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 30; j++){
				for(int k = 0; k < 9; k++){
					if(world.renderArray[k][j][i] > 0){
						Cube cube = new Cube();
						cube.sendData(i,j,k);
						cube.type = world.renderArray[k][j][i];
						if(cube.type == 4){
							cube.moveDir = 1;
						}
						if(cube.type == 5){
							cube.moveDir = -1;
						}
						if(world.lighting[k][j][i] == 1){
							lightSources.add(cube);
						}
						int hash = Integer.parseInt(String.valueOf(j)+String.valueOf(i-6)+String.valueOf(k)) % cubes.length;
						cubes[hash] = cube;
						allCubes.add(cube);
						MainClass.checkDir(counter);
						cube.changeBoolean();
						allCubes.set(counter, cube);
						counter++;
					}
				}
			}
		}
		for(int i = 0; i < allCubes.size(); i++){
			Cube cube = allCubes.get(i);	
			cube.loadTexture();
			cube.lighting(Color.white);
			allCubes.set(i,cube);
		}	
		input.focusLost = false;
		oneWayFlag = true;
		
		loadingScreen.dispose();
		
	}
	
	//Starts the game
	public static void main(String[]args){
		while(true){
			World.loadSettings();
			MainClass.launch();
		}
	}
	
	//Saves the game's settings
	public static void saveSettings(){
		try{
			File file = new File("Resources/Settings/settings.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Double.toString(game.controls.rotationSpeed));
			bw.newLine();
			bw.write(Integer.toString(mouseXOffset));
			bw.newLine();
			bw.write(Integer.toString(mouseYOffset));
			bw.close();	
		}catch(IOException e){
			
		}
	}
	
	//Change the textures for the cubes
	public static void changeTextures(){
		for(int i = 0; i < allCubes.size(); i++){
			Cube cube = allCubes.get(i);
			cube.loadTexture();
			cube.lighting(Color.white);
			allCubes.set(i,cube);
		}
	}
	
	//Move the cubes
	public void moveCubes(int index){
		Cube cube = allCubes.get(index);
		int hash = Integer.parseInt(String.valueOf((int)cube.Y)+String.valueOf((int)cube.X-6)+String.valueOf((int)cube.Z)) % cubes.length;
		
		if(cube.type == 9){
			MainClass.cubes[hash] = null;
			cube.type = 0;
			World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 0;
			cube.collision(game.controls.gd);
			cube.sendData(cube.X,cube.Y,cube.Z);
			cube.type = 9;
			MainClass.checkDir(index);
			cube.changeBoolean();
			World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 9;
			cube.lighting(Color.white);
			allCubes.set(index,cube);
			hash = Integer.parseInt(String.valueOf((int)cube.Y)+String.valueOf((int)cube.X-6)+String.valueOf((int)cube.Z)) % cubes.length;
			MainClass.cubes[hash] = cube;
		}else{
			if(cube.type == 8){
				MainClass.cubes[hash] = null;
				cube.type = 0;
				World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 0;
				cube.collision2(game.controls.gd);
				cube.sendData(cube.X,cube.Y,cube.Z);
				cube.type = 8;
				World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 8;
				MainClass.checkDir(index);
				cube.changeBoolean();
				cube.lighting(Color.white);
				allCubes.set(index,cube);
				hash = Integer.parseInt(String.valueOf((int)cube.Y)+String.valueOf((int)cube.X-6)+String.valueOf((int)cube.Z)) % cubes.length;
				MainClass.cubes[hash] = cube;
			}else{
				if(cube.type == 5){
					cube.type = 0;
					int oldX = (int)cube.X-6;
					int oldZ = (int)cube.Z;
					World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 0;
					cube.collision3();
					cube.sendData(cube.X,cube.Y,cube.Z);
					cube.type = 5;
					World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 5;
					MainClass.checkDir(index);
					cube.changeBoolean();
					cube.lighting(Color.white);
					allCubes.set(index,cube);
					hash = Integer.parseInt(String.valueOf((int)cube.Y)+String.valueOf((int)cube.X-6)+String.valueOf((int)cube.Z)) % cubes.length;
					MainClass.cubes[hash] = cube;
				}else{
					if(cube.type == 4){
						cube.type = 0;
						int oldX = (int)cube.X-6;
						int oldZ = (int)cube.Z;
						World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 0;
						cube.collision3();
						cube.sendData(cube.X,cube.Y,cube.Z);
						cube.type = 4;
						World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 4;
						MainClass.checkDir(index);
						cube.changeBoolean();
						cube.lighting(Color.white);
						allCubes.set(index,cube);
						hash = Integer.parseInt(String.valueOf((int)cube.Y)+String.valueOf((int)cube.X-6)+String.valueOf((int)cube.Z)) % cubes.length;
						MainClass.cubes[hash] = cube;
					}
					else{
						if(cube.type == 3){
							if(System.currentTimeMillis() - startTime >= 500) {
								if(cube.started){
									cube.type = 0;
									cube.started = false;
									World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 0;
									cube.collision(game.controls.gd);
									cube.sendData(cube.X,cube.Y,cube.Z);
									cube.type = 3;
									cube.started = true;
									World.renderArray[(int)cube.Z][(int)cube.Y][(int)cube.X] = 3;
									MainClass.checkDir(index);
									cube.changeBoolean();
									cube.lighting(Color.white);
									allCubes.set(index,cube);
									hash = Integer.parseInt(String.valueOf((int)cube.Y)+String.valueOf((int)cube.X-6)+String.valueOf((int)cube.Z)) % cubes.length;
									MainClass.cubes[hash] = cube;
								}
							}
						}
					}
				}
			}
		}
	}
	
	//Set the spherical coordinates for the cubes
	public void setPhiThetaAndR(){
		distances = new double[4096];
		phiVals = new double[4096];
		thetaVals = new double[4096];
		for(int i = 0; i < allCubes.size(); i++){
			Cube cube = allCubes.get(i);
			double r = Math.sqrt((cube.X-game.controls.x)*(cube.X-game.controls.x)+(cube.Y-game.controls.y)*(cube.Y-game.controls.y)+(cube.Z-game.controls.z)*(cube.Z-game.controls.z));
			double phi = Math.atan2((cube.Y-game.controls.y),(cube.X-game.controls.x)) - Math.PI/2;
			double theta = Math.acos((cube.Z-game.controls.z)/r)-Math.PI/2;
			distances[i] = r;
			phiVals[i] = phi;
			thetaVals[i] = theta;
		}
	}
 
	//Draws object to the screen
	public void paintComponent(Graphics graphic){
		super.paintComponent(graphic);
		graphic.setClip(0,0,WIDTH,HEIGHT);
	   	g2d = (Graphics2D)graphic;
		setPhiThetaAndR();
		ArrayList<Double> distanceArray = new ArrayList<Double>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for(int i = 0; i < allCubes.size(); i++){
			double phi = phiVals[i];
			double theta = thetaVals[i];
			if((Math.abs(Math.abs(phi) - Math.abs(Math.toRadians(game.controls.yaw))) < Math.PI / 2 || Math.abs(Math.abs(phi) - Math.abs(Math.abs(Math.toRadians(game.controls.yaw)) - Math.PI * 2)) < Math.PI / 2) && (Math.abs(Math.abs(theta) - Math.abs(Math.toRadians(game.controls.pitch))) < Math.PI / 2 || Math.abs(Math.abs(theta) - Math.abs(Math.abs(Math.toRadians(game.controls.pitch)) - Math.PI * 2)) < Math.PI / 2)){
					distanceArray.add(distances[i]);
					indices.add(i);
			}
		}
		for(int i = 0; i < distanceArray.size() - 1; i++){
			for(int j = i + 1; j < distanceArray.size(); j++){
				if(distanceArray.get(j) > distanceArray.get(i)){
					int indexi = indices.get(i);
					int indexj = indices.get(j);
					double distancei = distanceArray.get(i);
					double distancej = distanceArray.get(j);
					int tempIndex = indexi;
					double tempDistance = distancei;
					indexi = indexj;
					indexj = tempIndex;
					distancei = distancej;
					distancej = tempDistance;
					indices.set(i,indexi);
					indices.set(j,indexj);
					distanceArray.set(i,distancei);
					distanceArray.set(j,distancej);
				}
			}
		}
		distanceArray.clear();
		for(int i = 0; i < indices.size(); i++){
			Cube cube = allCubes.get(indices.get(i));
			moveCubes(indices.get(i));
			cube.rotate(game.controls.x, game.controls.y, game.controls.z);
			cube.setDispVals();
			cube.setArrays();
			cube.setColor(Color.white,g2d);
			allCubes.set(indices.get(i),cube);
		}
		for(int i = 0; i < indices.size(); i++){
			Cube cube = allCubes.get(indices.get(i));
			cube.clipping();
			cube.draw(game.controls.x,game.controls.y,game.controls.z);
		}
		indices.clear();
		fps.tick();
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		g2d.drawString("Level - " + Integer.toString(World.levelNum+1),10,330);
		if(mainClass.retro){
			graphic.drawImage(scaledHelmet,0,0,null);
		}
		if(hudOn == 2){
			hotbar.draw(graphic);
		}		
	}
	
	//Main game loop, keeps game running
    public void run(){
    	MainClass.load();
    	translationMatrix = new Matrix(1,0,0,-(float)game.controls.x,0,1,0,-(float)game.controls.z,0,0,1,(float)-game.controls.y,0,0,0,1);
   	 	viewMatrix = Matrix.createFromYawPitchRoll((float)game.controls.yaw,(float)game.controls.pitch,(float)game.controls.roll);
   	 	viewProjectionTransform = Matrix.Multiply(Matrix.Multiply(viewMatrix,translationMatrix),projectionMatrix);
   		input.focusLost = false;
   		timeSinceTick = System.currentTimeMillis();
   	 	while(alive){
   	 		if(System.currentTimeMillis() - nanoTime > 1){
   	 			fps.tick();
   	 			nanoTime = System.currentTimeMillis();
   	 			pause = false;
   	 			requestFocus();
   	 			game.tick(input.key,moved);
   	 			for(int i = (int)game.controls.x - 2; i < (int)game.controls.x + 2; i++){
					for(int j =(int)game.controls.y - 2; j < (int)game.controls.y + 2; j++){
						for(int k = (int)game.controls.z - 3; k < (int)game.controls.z + 3; k++){
							if(k > -1 && j > -1 && i > 5 && i < 14 && j < 30 && k < 9){
								int hash = Integer.parseInt(String.valueOf(j)+String.valueOf(i-6)+String.valueOf(k)) % cubes.length;
								if(MainClass.cubes[hash] != null){
									if(MainClass.cubes[hash].type > 0){
										Cube cube = MainClass.cubes[hash];
										MainClass.collision(cube.X,cube.Y,cube.Z,cube.type);
									}
								}
							}
						}
					}
				}	
   	 			translationMatrix = new Matrix(1,0,0,-(float)game.controls.x,0,1,0,-(float)game.controls.z,0,0,1,-(float)game.controls.y,0,0,0,1);
   	 			viewMatrix = Matrix.createFromYawPitchRoll((float)Math.toRadians(game.controls.yaw),(float)Math.toRadians(game.controls.pitch),(float)Math.toRadians(game.controls.roll));
   	 			viewProjectionTransform = Matrix.Multiply(viewMatrix,translationMatrix);
   	 			placeVal = world.numberOfFlips;
  				if(input.key[KeyEvent.VK_ESCAPE]){
  					PauseMenu pauseMenu = new PauseMenu();
					pauseMenu.drawButton();
					while(!pauseMenu.start){
						System.out.print("");
					}
					pauseMenu.dispose();
					frame.toFront();
  				}
  				input.key[KeyEvent.VK_ESCAPE] = false;
  				timeSinceTick = System.currentTimeMillis();
  				repaint();
   	 		}
   	 	}
    }
    
    //Loads the data to begin the game
	public static void startup(){
		mainClass = new MainClass();
		frame = new JFrame();
		frame.setUndecorated(true); 
		frame.add(mainClass);
		frame.pack();
		frame.setSize(WIDTH,HEIGHT);
		frame.setTitle("Freefall");
		mainClass.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		mainClass.run();
	}
    
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\