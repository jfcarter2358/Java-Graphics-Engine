/**
 *Class - World
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.Color;
import java.util.Properties;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.AWTException;

//======End Import Statements======\\

//******Begin Class******\\

public class World{
	
	//------Variables------\\
	
	public static String line;
	public static BufferedReader in;
	public static String worlds[] = {"Resources/levels/level1","Resources/levels/level2","Resources/levels/level3","Resources/levels/level4","Resources/levels/level5","Resources/levels/level6","Resources/levels/level7","Resources/levels/level8","Resources/levels/level9","Resources/levels/level10","Resources/levels/level11","Resources/levels/level12","Resources/levels/level13","Resources/levels/level14","Resources/levels/level15","Resources/levels/level16","Resources/levels/level17","Resources/levels/level18","Resources/levels/level19","Resources/levels/level20","Resources/levels/level21","Resources/levels/level22","Resources/levels/level23","Resources/levels/level24","Resources/levels/level25","Resources/levels/level26","Resources/levels/level27","Resources/levels/level28","Resources/levels/level29","Resources/levels/level30","Resources/levels/level31","Resources/levels/level32","Resources/levels/level33","Resources/levels/level34","Resources/levels/level35","Resources/levels/level36","Resources/levels/level37","Resources/levels/level38","Resources/levels/level0"};
	public static String customWorlds[] = {"Resources/CustomLevels/level1","Resources/CustomLevels/level2","Resources/CustomLevels/level3","Resources/CustomLevels/level4","Resources/CustomLevels/level5","Resources/CustomLevels/level6","Resources/CustomLevels/level7","Resources/levels/level8","Resources/CustomLevels/level9","Resources/CustomLevels/level10","Resources/CustomLevels/level11","Resources/CustomLevels/level12","Resources/CustomLevels/level13","Resources/CustomLevels/level14","Resources/CustomLevels/level15","Resources/CustomLevels/level16","Resources/CustomLevels/level17","Resources/CustomLevels/level18","Resources/CustomLevels/level19","Resources/CustomLevels/level20","Resources/CustomLevels/level21","Resources/CustomLevels/level22","Resources/CustomLevels/level23","Resources/CustomLevels/level24","Resources/CustomLevels/level25","Resources/CustomLevels/level26","Resources/CustomLevels/level27","Resources/CustomLevels/level28","Resources/CustomLevels/level29","Resources/CustomLevels/level30","Resources/CustomLevels/level31","Resources/CustomLevels/level32","Resources/CustomLevels/level33","Resources/CustomLevels/level34","Resources/CustomLevels/level0"};
	public static int levelNum = 0;
	public static int numOfCustomLevels;
	public static int numberOfFlips;
	public static int[][][] renderArray = new int[9][30][20];
	public static int[][][] topLightLevels = new int[9][30][20];
	public static int[][][] bottomLightLevels = new int[9][30][20];
	public static int[][][] leftLightLevels = new int[9][30][20];
	public static int[][][] rightLightLevels = new int[9][30][20];
	public static int[][][] frontLightLevels = new int[9][30][20];
	public static int[][][] backLightLevels = new int[9][30][20];
	public static int[][][] lighting = new int[9][30][20];
	public static int[][][] terminals = new int[9][30][20];
	public static int lightingX;
	public static int lightingY;
	public static int lightingZ;
	
	//------End Variables------\\
	
	//~~~~~~Methods~~~~~~\\
	
	//Loads in the world data from the text file
	public static void load(){
		for(int z = 0; z < 9; z++){
			for(int y = 0; y < 30; y++){
				for(int x = 0; x < 20; x++){
					renderArray[z][y][z] = 0;
					lighting[z][y][x] = 0;
				}
			}
		}
		if(MainClass.custom == false){
			try{
				in = new BufferedReader(new FileReader(worlds[levelNum]+".txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			for(int z = 0; z < 9; z++){
				for(int y = 0; y < 30; y++){
					try{
						line = in.readLine();
						for(int x = 0; x < 20; x++){
							renderArray[z][y][x] = Integer.parseInt(line.substring(x,x+1));
						}
					}
					catch (IOException e) { 
						e.printStackTrace(); 
					} 
				}		
			}
			try{
				in = new BufferedReader(new FileReader("Resources/levels/lighting.txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			for(int z = 0; z < 9; z++){
				for(int y = 0; y < 30; y++){
					try{
						line = in.readLine();
						for(int x = 0; x < 20; x++){
							lighting[z][y][x] = Integer.parseInt(line.substring(x,x+1));
						}
					}
					catch (IOException e) { 
						e.printStackTrace(); 
					} 
				}		
			}
			try{
				in = new BufferedReader(new FileReader(worlds[levelNum] + "terminals.txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			for(int z = 0; z < 9; z++){
				for(int y = 0; y < 30; y++){
					try{
						line = in.readLine();
						for(int x = 0; x < 20; x++){
							terminals[z][y][x] = Integer.parseInt(line.substring(x,x+1));
							if(terminals[z][y][x] == 1){
								lightingX = x;
								lightingY = y;
								lightingZ = z;
							}
						}
					}
					catch (IOException e) { 
						e.printStackTrace(); 
					} 
				}		
			}
			try{
				in = new BufferedReader(new FileReader(worlds[levelNum]+"data.txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			try{
				line = in.readLine();
					numberOfFlips = Integer.parseInt(line.substring(0,1));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
	}else{
		try{
				in = new BufferedReader(new FileReader("Resources/customLevels/counter.txt"));
				line = in.readLine();
				numOfCustomLevels = Integer.parseInt(line);
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
		try{
				in = new BufferedReader(new FileReader(customWorlds[levelNum]+".txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			for(int z = 0; z < 9; z++){
				for(int y = 0; y < 30; y++){
					try{
						line = in.readLine();
						for(int x = 0; x < 20; x++){
							renderArray[z][y][x] = Integer.parseInt(line.substring(x,x+1));
						}
					}
					catch (IOException e) { 
						e.printStackTrace(); 
					} 
				}		
			}
			try{
				in = new BufferedReader(new FileReader("Resources/customLevels/lighting.txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			for(int z = 0; z < 9; z++){
				for(int y = 0; y < 30; y++){
					try{
						line = in.readLine();
						for(int x = 0; x < 20; x++){
							lighting[z][y][x] = Integer.parseInt(line.substring(x,x+1));
						}
					}
					catch (IOException e) { 
						e.printStackTrace(); 
					} 
				}		
			}
			try{
				in = new BufferedReader(new FileReader(customWorlds[levelNum] + "terminals.txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			for(int z = 0; z < 9; z++){
				for(int y = 0; y < 30; y++){
					try{
						line = in.readLine();
						for(int x = 0; x < 20; x++){
							terminals[z][y][x] = Integer.parseInt(line.substring(x,x+1));
						}
					}
					catch (IOException e) { 
						e.printStackTrace(); 
					} 
				}		
			}
			try{
				in = new BufferedReader(new FileReader(customWorlds[levelNum]+"data.txt"));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
			try{
				line = in.readLine();
					numberOfFlips = Integer.parseInt(line.substring(0,1));
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
		}
	}	

	public static void loadSettings(){
		try{
			in = new BufferedReader(new FileReader("Resources/Settings/settings.txt"));
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
		try{
			line = in.readLine();
			MainClass.game.controls.rotationSpeed = Double.parseDouble(line);
			line = in.readLine();
			MainClass.mouseXOffset = Integer.parseInt(line);
			line = in.readLine();
			MainClass.mouseYOffset = Integer.parseInt(line);
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}
	
//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\