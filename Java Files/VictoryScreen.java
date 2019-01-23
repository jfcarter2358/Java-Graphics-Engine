/**
 *Class - VictoryScreen
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//======End Import Statements======\\

//******Begin Class******\\

public class VictoryScreen extends JFrame{
	
	//------Variables------\\

	public static int actionNum = 0;
	public JPanel window = new JPanel();
	public JButton Quit;
	public Rectangle quit,title,youWin;
	public boolean start = false;
	public JLabel Title,YouWin,S;
	
	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\

	//Constructor
	public VictoryScreen(){
		setTitle("You Win!");
		setSize(MainClass.WIDTH,MainClass.HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().add(window);
		setUndecorated(true);
		window.setLayout(null);
		window.setBackground(Color.black);
		setVisible(true);
		setResizable(false);
		drawButton();
	}
	
	//Sets text and positions for buttons and labels and then draws them
	public void drawButton(){
		setParts();
		listeners();	
	}
	
	//Listens for actions
	public void listeners(){
		Quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				start = true;
				MainClass.start = false;
				MainClass.frame.dispose();
				MainClass.alive = false;
			}	
		});	
	}
	
	//Sets the data for the labels and buttons and such
	public void setParts(){
		Title = new JLabel("CONGRATULATIONS", SwingConstants.CENTER);
		title = new Rectangle(MainClass.WIDTH/2-350,5,700,60);
		YouWin = new JLabel("You Won!", SwingConstants.CENTER);
		youWin = new Rectangle(MainClass.WIDTH/2-150,75,300,40);
		Quit = new JButton("Main Menu");
		quit = new Rectangle(MainClass.WIDTH/2-150,150,300,40);
		Quit.setBackground(Color.black);
		Quit.setForeground(Color.green);
		Quit.setBounds(quit);
		Title.setBackground(Color.black);
		Title.setForeground(Color.green);
		Title.setBounds(title);
		YouWin.setBackground(Color.black);
		YouWin.setForeground(Color.green);
		YouWin.setBounds(youWin);
		Title.setFont(new Font("Lucida Console", Font.PLAIN, 65));
		YouWin.setFont(new Font("Lucida Console", Font.PLAIN, 55));
		window.add(Title);
		window.add(YouWin);
		window.add(Quit);
	}
	
	//~~~~~~End Methods~~~~~~\\
	
}

//******End Class******\\