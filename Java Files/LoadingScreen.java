/**
 *Class - LoadingScreen
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.Dimension;
import java.awt.Toolkit;

//======End Import Statements======\\

//******Begin Class******\\

public class LoadingScreen extends JFrame{

	//------Variables------\\

	public static int actionNum = 0;
	public JPanel window = new JPanel();
	public String line;
	public BufferedReader in;
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension dim = toolkit.getScreenSize();
	public static int WIDTH = dim.width;
	public static int HEIGHT = dim.height;

	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\
	
	//Constructor
	public LoadingScreen(){
		setTitle("Freefall Launcher");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		try {
    		setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Resources/Images/loadingScreen.png")))));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	pack();
		getContentPane().add(window);
		window.setLayout(null);
		window.setBackground(Color.black);
		setVisible(true);
		setResizable(false);
	}
	
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\
