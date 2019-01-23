/**
 *Class - Texture
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//======End Import Statements======\\

//******Begin Class******\\

public class Texture{
	
	//------Variables------\\
	
	public BufferedImage image;
	public Color[][] colors;
	public int height;
	public int width;

	//------End Variables------\\
	
	//~~~~~~Methods~~~~~~\\
	
	//Constructor
	public Texture(){
		
	}
	
	//Load the texture
	public void loadTexture(String fileName){
		try {
   			image = ImageIO.read(new File("Resources/images/"+fileName+".png"));
			height = image.getHeight();
			width = image.getWidth();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		colors = new Color[width][height];
		for (int i = 0; i < width; i++) {
   			for (int j = 0; j < height; j++) {
       			colors[i][j] = new Color(image.getRGB(i,j));
    		}
		}
	}
}