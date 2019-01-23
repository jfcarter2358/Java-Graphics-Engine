/**
 *Class - Launcher
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Toolkit;

//======End Import Statements======\\

//******Begin Class******\\

public class Launcher extends JFrame{

	//------Variables------\\

	public static int actionNum = 0;
	public JPanel window = new JPanel();
	public JButton Play,Quit,Options,SensativityUp,SensativityDown,Back,Resume,MainMenu,Load,SwitchGraphics,HowToPlay,Back2,HUD,CustomLevels,BetterTextures;
	public Rectangle play,mouseX,mouseY,quit,title,options,sensativityUp,sensativityDown,back,resume,mainMenu,sensativity,s,load,credits,john,adam,cody,johnl,switchGraphics,carson,adam2,codyPlaytester,mrsSchumacher,w,a,ss,d,leftMouse,rightMouse,space,escape,howToPlay,controls,back2,blue,red,white,lightBlue,yellow,blockTypes,hud,customLevels,betterTextures;
	public boolean start;
	public JLabel Title,MouseX,MouseY,Sensativity,S,Credits,John,Adam,Cody,JohnL,Carson,Adam2,CodyPlaytester,MrsSchumacher,W,A,Ss,D,LeftMouse,RightMouse,Space,Escape,Controls,BlockTypes,Blue,Red,White,LightBlue,Yellow;
	public String line;
	public JButton MouseXUp,MouseXDown,MouseYUp,MouseYDown;
	public Rectangle mouseXUp,mouseXDown,mouseYUp,mouseYDown;
	public BufferedReader in;
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension dim = toolkit.getScreenSize();
	public static int WIDTH = dim.width;
	public static int HEIGHT = dim.height;
	public JLabel mx,my;
	

	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\
	
	//Constructor
	public Launcher(){
		setTitle("Freefall Launcher");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		try {
    		setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Resources/Images/background.png")))));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	pack();
		getContentPane().add(window);
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
		Play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				World.levelNum = 0;
				MainClass.start = true;
			}	
		});
		Options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Title.setText("OPTIONS");
				S.setText(Integer.toString((int)(100*(MainClass.game.controls.rotationSpeed/.08 - 1))));
				Title.setFont(new Font("Lucida Console", Font.PLAIN, 20));
				Sensativity.setFont(new Font("Lucida Console", Font.PLAIN, 20));
				S.setFont(new Font("Lucida Console", Font.PLAIN, 20));
				getContentPane().removeAll();
				getContentPane().revalidate();
				getContentPane().repaint();
				getContentPane().add(Title);
				getContentPane().add(SensativityUp);
				getContentPane().add(SensativityDown);
				getContentPane().add(SwitchGraphics);
				getContentPane().add(BetterTextures);
				getContentPane().add(Back);
				getContentPane().add(Sensativity);
				getContentPane().add(S);
				getContentPane().add(HUD);
				getContentPane().add(MouseXUp);
				getContentPane().add(MouseXDown);
				getContentPane().add(MouseYUp);
				getContentPane().add(MouseYDown);
				getContentPane().add(MouseY);
				getContentPane().add(MouseX);
			}	
		});
		CustomLevels.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainClass.custom = !MainClass.custom;
				if(!MainClass.custom){
					CustomLevels.setText("Campaign");
				}else{
					CustomLevels.setText("Custom Levels");
				}
				getContentPane().removeAll();
				getContentPane().revalidate();
				getContentPane().repaint();
				getContentPane().add(Title);
				getContentPane().add(Play);
				getContentPane().add(Options);
				getContentPane().add(Load);
				getContentPane().add(Quit);
				getContentPane().add(Credits);
				getContentPane().add(John);
				getContentPane().add(Adam);
				getContentPane().add(Cody);
				getContentPane().add(JohnL);
				getContentPane().add(Adam2);
				getContentPane().add(Carson);
				getContentPane().add(CodyPlaytester);
				getContentPane().add(MrsSchumacher);
				getContentPane().add(HowToPlay);
				//getContentPane().add(CustomLevels);
			}	
		});
		
		BetterTextures.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Controls.setFont(new Font("Lucida Console", Font.PLAIN, 30));
				BlockTypes.setFont(new Font("Lucida Console", Font.PLAIN, 30));
				MainClass.betterTextures = !MainClass.betterTextures;
				BetterTextures.setText("Better Textures - " + String.valueOf(MainClass.betterTextures));
			}	
		});
		
		HowToPlay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Controls.setFont(new Font("Lucida Console", Font.PLAIN, 30));
				BlockTypes.setFont(new Font("Lucida Console", Font.PLAIN, 30));
				getContentPane().removeAll();
				getContentPane().revalidate();
				getContentPane().repaint();
				getContentPane().add(Controls);
				getContentPane().add(W);
				getContentPane().add(A);
				getContentPane().add(Ss);
				getContentPane().add(D);
				getContentPane().add(White);
				getContentPane().add(Red);
				getContentPane().add(Blue);
				getContentPane().add(LightBlue);
				getContentPane().add(Yellow);
				getContentPane().add(BlockTypes);
				getContentPane().add(LeftMouse);
				getContentPane().add(RightMouse);
				getContentPane().add(Space);
				getContentPane().add(Escape);
				getContentPane().add(Back2);
			}	
		});
		Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Title.setText("FREEFALL");
				Title.setFont(new Font("Lucida Console", Font.PLAIN, 55));
				getContentPane().removeAll();
				getContentPane().revalidate();
				getContentPane().repaint();
				getContentPane().add(Title);
				getContentPane().add(Play);
				getContentPane().add(Options);
				getContentPane().add(Load);
				getContentPane().add(Quit);
				getContentPane().add(Credits);
				getContentPane().add(John);
				getContentPane().add(Adam);
				getContentPane().add(Cody);
				getContentPane().add(JohnL);
				getContentPane().add(Adam2);
				getContentPane().add(Carson);
				getContentPane().add(CodyPlaytester);
				getContentPane().add(MrsSchumacher);
				getContentPane().add(HowToPlay);
				MainClass.saveSettings();
			}	
		});
		HUD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(MainClass.hudOn == 2){
					HUD.setText("Difficutly - Normal");
					MainClass.hudOn = 1;
				}else{
					if(MainClass.hudOn == 1){
						HUD.setText("Difficulty - Hard");
						MainClass.hudOn = 0;
					}else{
						HUD.setText("Difficulty - Easy");
						MainClass.hudOn = 2;
					}
				}
			}	
				
		});
		Back2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Title.setText("FREEFALL");
				Title.setFont(new Font("Lucida Console", Font.PLAIN, 55));
				getContentPane().removeAll();
				getContentPane().revalidate();
				getContentPane().repaint();
				getContentPane().add(Title);
				getContentPane().add(Play);
				getContentPane().add(Options);
				getContentPane().add(Load);
				getContentPane().add(Quit);
				getContentPane().add(Credits);
				getContentPane().add(John);
				getContentPane().add(Adam);
				getContentPane().add(Cody);
				getContentPane().add(JohnL);
				getContentPane().add(Adam2);
				getContentPane().add(Carson);
				getContentPane().add(CodyPlaytester);
				getContentPane().add(MrsSchumacher);
				getContentPane().add(HowToPlay);
			}	
		});
		Quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}	
		});
		SwitchGraphics.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(MainClass.retro == true){
					SwitchGraphics.setText("Helmet On");
					MainClass.retro = false;
				}else{
					SwitchGraphics.setText("Helmet Off");
					MainClass.retro = true;
				}
			}	
		});
		SensativityUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(MainClass.game.controls.rotationSpeed < .16){
					MainClass.game.controls.rotationSpeed += .008;
					System.out.println(MainClass.game.controls.rotationSpeed);
					S.setText(Integer.toString((int)(100*(MainClass.game.controls.rotationSpeed/.08 - 1))));
				}
			}	
		});
		SensativityDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(MainClass.game.controls.rotationSpeed > 0){
					MainClass.game.controls.rotationSpeed -= .008;
					S.setText(Integer.toString((int)(100*(MainClass.game.controls.rotationSpeed/.08 - 1))));
				}
			}	
		});
		MouseXUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainClass.mouseXOffset++;
				MouseX.setText("Mouse X Offset - " + Integer.toString(MainClass.mouseXOffset));
			}	
		});
		MouseXDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainClass.mouseXOffset--;
				MouseX.setText("Mouse X Offset - " + Integer.toString(MainClass.mouseXOffset));
			}	
		});
		MouseYUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainClass.mouseYOffset++;
				MouseY.setText("Mouse Y Offset - " + Integer.toString(MainClass.mouseYOffset));
			}	
		});
		MouseYDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainClass.mouseYOffset--;
				MouseY.setText("Mouse Y Offset - " + Integer.toString(MainClass.mouseYOffset));
			}	
		});
		Load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MainClass.start = true;
				try{
					File file = new File("Resources/SaveGame/saveGame.txt");
					if(file.exists()){
						in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
					}else{
						file.createNewFile();
					}
					
				}
				catch (IOException ie) { 
					ie.printStackTrace(); 
				} 
				try{
					line = in.readLine();
					World.levelNum = Integer.parseInt(line);
				}
				catch (IOException iie) { 
					iie.printStackTrace(); 
				} 	
			}
		});
	}
	
	//Sets all of the labels and buttons and such
	public void setParts(){
		Title = new JLabel("FREEFALL", SwingConstants.CENTER);
		title = new Rectangle(MainClass.WIDTH/2-150,5,300,40);
		Sensativity = new JLabel("SENSATIVITY", SwingConstants.CENTER);
		sensativity = new Rectangle(MainClass.WIDTH/2-150,25,300,40);
		MouseX = new JLabel("Mouse X Offset - " + Integer.toString(MainClass.mouseXOffset), SwingConstants.CENTER);
		mouseX = new Rectangle(MainClass.WIDTH/2-150,250,300,40);
		MouseY = new JLabel("Mouse Y Offset - " + Integer.toString(MainClass.mouseYOffset), SwingConstants.CENTER);
		mouseY = new Rectangle(MainClass.WIDTH/2-150,350,300,40);
		S = new JLabel("", SwingConstants.CENTER);
		s = new Rectangle(MainClass.WIDTH/2-150,45,300,40);
		Controls = new JLabel("CONTROLS", SwingConstants.CENTER);
		controls = new Rectangle(MainClass.WIDTH/2-200,5,400,40);
		W = new JLabel("W - walk forwards", SwingConstants.CENTER);
		w = new Rectangle(MainClass.WIDTH/2-200,45,400,40);
		A = new JLabel("A - walk left", SwingConstants.CENTER);
		a = new Rectangle(MainClass.WIDTH/2-200,65,400,40);
		Ss = new JLabel("S - walk backwards", SwingConstants.CENTER);
		ss = new Rectangle(MainClass.WIDTH/2-200,85,400,40);
		D = new JLabel("D - walk right", SwingConstants.CENTER);
		d = new Rectangle(MainClass.WIDTH/2-200,105,400,40);
		Space = new JLabel("SPACE - jump", SwingConstants.CENTER);
		space = new Rectangle(MainClass.WIDTH/2-200,185,400,40);
		Escape = new JLabel("ESCAPE - pause menu", SwingConstants.CENTER);
		escape = new Rectangle(MainClass.WIDTH/2-200,165,400,40);
		LeftMouse = new JLabel("LEFT MOUSE CLICK - flip gravity", SwingConstants.CENTER);
		leftMouse = new Rectangle(MainClass.WIDTH/2-200,125,400,40);
		RightMouse = new JLabel("RIGHT MOUSE CLICK - reset level", SwingConstants.CENTER);
		rightMouse = new Rectangle(MainClass.WIDTH/2-200,145,400,40);
		Blue = new JLabel("BLUE BLOCKS - fall in the direction of gravity", SwingConstants.CENTER);
		blue = new Rectangle(MainClass.WIDTH/2-200,300,400,40);
		Red = new JLabel("RED BLOCKS - send you back to the beginning of the level", SwingConstants.CENTER);
		red = new Rectangle(MainClass.WIDTH/2-200,280,400,40);
		White = new JLabel("WHITE BLOCKS - normal walls", SwingConstants.CENTER);
		white = new Rectangle(MainClass.WIDTH/2-200,260,400,40);
		Back2 = new JButton("Back");
		back2= new Rectangle(MainClass.WIDTH/2-200,380,400,40);
		Yellow = new JLabel("YELLOW BLOCKS - fall one half second after player contact", SwingConstants.CENTER);
		yellow = new Rectangle(MainClass.WIDTH/2-200,340,400,40);
		LightBlue = new JLabel("LIGHT BLUE BLOCKS - fall in the opposite direction of gravity", SwingConstants.CENTER);
		lightBlue = new Rectangle(MainClass.WIDTH/2-250,320,500,40);
		BlockTypes = new JLabel("BLOCK TYPES", SwingConstants.CENTER);
		blockTypes = new Rectangle(MainClass.WIDTH/2-200,220,400,40);
		if(MainClass.retro == false){
			SwitchGraphics = new JButton("Helmet On");
		}else{
			SwitchGraphics = new JButton("Helmet Off");
		}
		if(MainClass.hudOn == 2){
			HUD = new JButton("DIfficulty - Easy");
		}else{
			if(MainClass.hudOn == 1){
				HUD = new JButton("DIfficulty - Normal");
			}else{
				HUD = new JButton("Difficulty - Hard");
			}
		}
		switchGraphics = new Rectangle(MainClass.WIDTH/2-150,150,300,40);
		Play = new JButton("New Game");
		play = new Rectangle(MainClass.WIDTH/2-150,50,300,40);
		HowToPlay = new JButton("How To Play");
		howToPlay = new Rectangle(MainClass.WIDTH/2-150,200,300,40);
		Options = new JButton("Options");
		options = new Rectangle(MainClass.WIDTH/2-150,100,300,40);
		Load = new JButton("Load Game");
		load = new Rectangle(MainClass.WIDTH/2-150,150,300,40);
		Quit = new JButton("Quit");
		quit = new Rectangle(MainClass.WIDTH/2-150,250,300,40);
		Resume = new JButton("Quit");
		resume = new Rectangle(MainClass.WIDTH/2-150,50,300,40);
		MainMenu = new JButton("Quit");
		mainMenu = new Rectangle(MainClass.WIDTH/2-150,150,300,40);
		BetterTextures = new JButton("Better Textures - true");
		betterTextures = new Rectangle(MainClass.WIDTH/2-150,450,300,40);
		Back = new JButton("Back");
		back = new Rectangle(MainClass.WIDTH/2-150,500,300,40);
		CustomLevels = new JButton("Campaign");
		customLevels = new Rectangle(MainClass.WIDTH/2-150,250,300,40);
		SensativityUp = new JButton("+");
		sensativityUp = new Rectangle(MainClass.WIDTH/2+10,100,140,40);
		SensativityDown = new JButton("-");
		sensativityDown = new Rectangle(MainClass.WIDTH/2-150,100,140,40);
		MouseXUp = new JButton("+");
		mouseXUp = new Rectangle(MainClass.WIDTH/2+10,300,140,40);
		MouseXDown = new JButton("-");
		mouseXDown = new Rectangle(MainClass.WIDTH/2-150,300,140,40);
		MouseYUp = new JButton("+");
		mouseYUp = new Rectangle(MainClass.WIDTH/2+10,400,140,40);
		MouseYDown = new JButton("-");
		mouseYDown = new Rectangle(MainClass.WIDTH/2-150,400,140,40);
		Credits = new JLabel("CREDITS",SwingConstants.CENTER);
		credits = new Rectangle(MainClass.WIDTH/2-150,300+50,300,40);
		CodyPlaytester = new JLabel("- Cody Jensen",SwingConstants.CENTER);
		codyPlaytester = new Rectangle(MainClass.WIDTH/2-150,440+50,300,40);
		John = new JLabel("PROGRAMMER - John Carter",SwingConstants.CENTER);
		john = new Rectangle(MainClass.WIDTH/2-150,320+50,300,40);
		Adam = new JLabel("CREATIVE DESIGN - Adam Schumacher",SwingConstants.CENTER);
		adam = new Rectangle(MainClass.WIDTH/2-150,340+50,300,40);
		Carson = new JLabel("PLAY TESTERS - Carson Discher            ",SwingConstants.CENTER);
		carson = new Rectangle(MainClass.WIDTH/2-150,400+50,300,40);
		Adam2 = new JLabel("- Adam Schumacher",SwingConstants.CENTER);
		adam2 = new Rectangle(MainClass.WIDTH/2-150,420+50,300,40);
		Cody = new JLabel("- Cody Jensen",SwingConstants.CENTER);
		cody = new Rectangle(MainClass.WIDTH/2-150,360+50,300,40);
		JohnL = new JLabel("- John Lyons",SwingConstants.CENTER);
		johnl = new Rectangle(MainClass.WIDTH/2-150,380+50,300,40);
		hud = new Rectangle(MainClass.WIDTH/2-150,200,300,40);
		MrsSchumacher = new JLabel("SPECIAL THANKS - Mrs. Schumacher for teaching all of us in grade school",SwingConstants.CENTER);
		mrsSchumacher = new Rectangle(MainClass.WIDTH/2-225,460+50,450,40);
		MrsSchumacher.setBackground(Color.black);
		MrsSchumacher.setForeground(Color.green);
		MrsSchumacher.setBounds(mrsSchumacher);
		HUD.setBackground(Color.black);
		HUD.setForeground(Color.green);
		HUD.setBounds(hud);
		CodyPlaytester.setBackground(Color.black);
		CodyPlaytester.setForeground(Color.green);
		CodyPlaytester.setBounds(codyPlaytester);
		Credits.setBackground(Color.black);
		Credits.setForeground(Color.green);
		Credits.setBounds(credits);
		John.setBackground(Color.black);
		John.setForeground(Color.green);
		John.setBounds(john);
		W.setBackground(Color.black);
		W.setForeground(Color.green);
		W.setBounds(w);
		Ss.setBackground(Color.black);
		Ss.setForeground(Color.green);
		Ss.setBounds(ss);
		A.setBackground(Color.black);
		A.setForeground(Color.green);
		A.setBounds(a);
		D.setBackground(Color.black);
		D.setForeground(Color.green);
		D.setBounds(d);
		Blue.setBackground(Color.black);
		Blue.setForeground(Color.green);
		Blue.setBounds(blue);
		LightBlue.setBackground(Color.black);
		LightBlue.setForeground(Color.green);
		LightBlue.setBounds(lightBlue);
		Red.setBackground(Color.black);
		Red.setForeground(Color.green);
		Red.setBounds(red);
		Yellow.setBackground(Color.black);
		Yellow.setForeground(Color.green);
		Yellow.setBounds(yellow);
		White.setBackground(Color.black);
		White.setForeground(Color.green);
		White.setBounds(white);
		Back2.setBackground(Color.black);
		Back2.setForeground(Color.green);
		Back2.setBounds(back2);
		BlockTypes.setBackground(Color.black);
		BlockTypes.setForeground(Color.green);
		BlockTypes.setBounds(blockTypes);
		LeftMouse.setBackground(Color.black);
		LeftMouse.setForeground(Color.green);
		LeftMouse.setBounds(leftMouse);
		RightMouse.setBackground(Color.black);
		RightMouse.setForeground(Color.green);
		RightMouse.setBounds(rightMouse);
		Space.setBackground(Color.black);
		Space.setForeground(Color.green);
		Space.setBounds(space);
		Escape.setBackground(Color.black);
		Escape.setForeground(Color.green);
		Escape.setBounds(escape);
		Controls.setBackground(Color.black);
		Controls.setForeground(Color.green);
		Controls.setBounds(controls);
		HowToPlay.setBackground(Color.black);
		HowToPlay.setForeground(Color.green);
		HowToPlay.setBounds(howToPlay);
		Adam.setBackground(Color.black);
		Adam.setForeground(Color.green);
		Adam.setBounds(adam);
		Adam2.setBackground(Color.black);
		Adam2.setForeground(Color.green);
		Adam2.setBounds(adam2);
		Carson.setBackground(Color.black);
		Carson.setForeground(Color.green);
		Carson.setBounds(carson);
		Cody.setBackground(Color.black);
		Cody.setForeground(Color.green);
		Cody.setBounds(cody);
		JohnL.setBackground(Color.black);
		JohnL.setForeground(Color.green);
		JohnL.setBounds(johnl);
		Play.setBackground(Color.black);
		Play.setForeground(Color.green);
		Play.setBounds(play);
		Sensativity.setBackground(Color.black);
		Sensativity.setForeground(Color.green);
		Sensativity.setBounds(sensativity);
		S.setBackground(Color.black);
		S.setForeground(Color.green);
		S.setBounds(s);
		SwitchGraphics.setBackground(Color.black);
		SwitchGraphics.setForeground(Color.green);
		SwitchGraphics.setBounds(switchGraphics);
		Options.setBackground(Color.black);
		Options.setForeground(Color.green);
		Options.setBounds(options);
		Resume.setBackground(Color.black);
		Resume.setForeground(Color.green);
		Resume.setBounds(resume);
		MainMenu.setBackground(Color.black);
		MainMenu.setForeground(Color.green);
		MainMenu.setBounds(mainMenu);
		Load.setBackground(Color.black);
		Load.setForeground(Color.green);
		Load.setBounds(load);
		Quit.setBackground(Color.black);
		Quit.setForeground(Color.green);
		Quit.setBounds(quit);
		Back.setBackground(Color.black);
		Back.setForeground(Color.green);
		Back.setBounds(back);
		BetterTextures.setBackground(Color.black);
		BetterTextures.setForeground(Color.green);
		BetterTextures.setBounds(betterTextures);
		CustomLevels.setBackground(Color.black);
		CustomLevels.setForeground(Color.green);
		CustomLevels.setBounds(customLevels);
		SensativityUp.setBackground(Color.black);
		SensativityUp.setForeground(Color.green);
		SensativityUp.setBounds(sensativityUp);
		SensativityDown.setBackground(Color.black);
		SensativityDown.setForeground(Color.green);
		SensativityDown.setBounds(sensativityDown);
		MouseXUp.setBackground(Color.black);
		MouseXUp.setForeground(Color.green);
		MouseXUp.setBounds(mouseXUp);
		MouseXDown.setBackground(Color.black);
		MouseXDown.setForeground(Color.green);
		MouseXDown.setBounds(mouseXDown);
		MouseYUp.setBackground(Color.black);
		MouseYUp.setForeground(Color.green);
		MouseYUp.setBounds(mouseYUp);
		MouseYDown.setBackground(Color.black);
		MouseYDown.setForeground(Color.green);
		MouseYDown.setBounds(mouseYDown);
		MouseX.setBackground(Color.black);
		MouseX.setForeground(Color.green);
		MouseX.setBounds(mouseX);
		MouseY.setBackground(Color.black);
		MouseY.setForeground(Color.green);
		MouseY.setBounds(mouseY);
		Title.setBackground(Color.black);
		Title.setForeground(Color.green);
		Title.setBounds(title);
		Title.setFont(new Font("Lucida Console", Font.PLAIN, 55));
		getContentPane().add(Title);
		getContentPane().add(Play);
		getContentPane().add(Options);
		getContentPane().add(Load);
		getContentPane().add(Quit);
		getContentPane().add(Credits);
		getContentPane().add(John);
		getContentPane().add(Adam);
		getContentPane().add(Adam2);
		getContentPane().add(Carson);
		getContentPane().add(Cody);
		getContentPane().add(JohnL);
		getContentPane().add(CodyPlaytester);
		getContentPane().add(MrsSchumacher);
		getContentPane().add(HowToPlay);
		Title.setVisible(true);
		Play.setVisible(true);
		Options.setVisible(true);
		Load.setVisible(true);
		Quit.setVisible(true);
		HowToPlay.setVisible(true);	
	}
	
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\
