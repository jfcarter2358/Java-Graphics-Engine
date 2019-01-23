/**
 *Class - Controller
 *Author - John Carter
 *Last Updated - 12/2/15
 */

//======Import Statements======\\

import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

//======End Inport Statements======\\

//******Begin Class******\\

public class Controller{

	//------Variables------\\

	public double x,y,z,yaw,pitch,roll,xa,ya,za,yawa,pitcha,rolla,yMove,xMove,zMove,flip,targetZ,rotationSpeed,walkSpeed;
	public int gd,oldMouseY,oldMouseX,mouseY,mouseX,lookMultiplier;
	public long startTime = 0;
	public boolean jumping;
	public double jumpDistance = .00005;
	public double mass1,mass2,velocityX1,velocityZ1,oldRadians,radians,force,gravVectorX1,gravVectorZ1,tempVelocityX,tempVelocityZ;
	public double sy,sp,sr,cy,cp,cr;
	
	//------End Variables------\\

	//~~~~~~Methods~~~~~~\\

	//Constructor
	public Controller(){
		yaw = 180;
		pitch = 45;
		roll = 0;
		jumping = true;
		new robot();
		rotationSpeed = 80;
		walkSpeed = .005;
		mass1 = Math.pow(10,1.5);
		mass2 = Math.pow(10,1);
		gravVectorX1 = force*((Math.cos(radians))+(Math.sin(radians)));
		gravVectorZ1 = force*((-Math.sin(radians))+(Math.cos(radians)));
		lookMultiplier = 1;
	}
	
	//Checks to see if the player has flipped gravity and takes the appropriate action if so
	public void checkFlip(boolean fliped){
		if(fliped){
			lookMultiplier *= -1;
			velocityZ1 = 0;
			radians = 0;
			velocityX1 = 0;
			gravVectorX1 = 0;
			gravVectorZ1 = 0;
			oldRadians = 0;
			jumping = true;
			if(MainClass.hudOn == 2){
				if(World.numberOfFlips > 0){
					if(gd == 5){
						gd = 6;
						flip = 180;
					}else{
						gd = 5;
						flip = 0;
					}
					World.numberOfFlips--;
				}
			}else{
				if(gd == 5){
					gd = 6;
					flip = 180;
				}else{
					gd = 5;
					flip = 0;
				}
			}
			InputHandler.leftMouse = false;
		}
	}
	
	//Checks to see if the player has jumped nd takes the appropriate action if so
	public void checkJump(boolean jump){
		if(gd == 5){
			if(jump){
				if(jumping == false){
				velocityZ1 = -.0045;
				radians = 0;
				velocityX1 = 0;
				gravVectorX1 = 0;
				jumping = true;
				gravVectorZ1 = 0;
				oldRadians = 0;
				MainClass.input.key[KeyEvent.VK_SPACE] = false;
				}
			}
		}
		if(gd == 6){
			if(jump){
				if(jumping == false){
					velocityZ1 = -.0045;
					radians = 0;
					velocityX1 = 0;
					gravVectorX1 = 0;
					jumping = true;
					gravVectorZ1 = 0;
					oldRadians = 0;
					MainClass.input.key[KeyEvent.VK_SPACE] = false;
				}
			}
		}
	}
	
	//Handles mouse movement and has the player look around accordingly
	public void checkMouseInput(boolean lookUp, boolean lookDown, boolean lookRight, boolean lookLeft){
		if((InputHandler.mouseY <= MainClass.HEIGHT/2)){
			if(gd == 5){
				pitcha+=(rotationSpeed * (MainClass.HEIGHT/2-InputHandler.mouseY)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
			if(gd == 6){
				pitcha-=(rotationSpeed * (MainClass.HEIGHT/2-InputHandler.mouseY)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
		if((InputHandler.mouseY >= MainClass.HEIGHT/2)){
			if(gd == 5){
				pitcha-=(rotationSpeed * (InputHandler.mouseY-MainClass.HEIGHT/2)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
			if(gd == 6){
				pitcha+=(rotationSpeed * (InputHandler.mouseY-MainClass.HEIGHT/2)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
		if((InputHandler.mouseX >= MainClass.WIDTH/2)){
			if(gd == 5){
				yawa+=(rotationSpeed * (InputHandler.mouseX-MainClass.WIDTH/2)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
			if(gd == 6){
				yawa-=(rotationSpeed * (InputHandler.mouseX-MainClass.WIDTH/2)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
		if((InputHandler.mouseX <= MainClass.WIDTH/2)){
			if(gd == 5){
				yawa-=(rotationSpeed * (MainClass.WIDTH/2-InputHandler.mouseX)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
			if(gd == 6){
				yawa+=(rotationSpeed * (MainClass.WIDTH/2-InputHandler.mouseX)) * .005 * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
		if((lookUp)){
			if(gd == 5){
				pitcha+=(rotationSpeed);
			}
			if(gd == 6){
				pitcha-=(rotationSpeed);
			}
		}
		if((lookDown)){
			if(gd == 5){
				pitcha-=(rotationSpeed);
			}
			if(gd == 6){
				pitcha+=(rotationSpeed);
			}
		}
		if((lookRight)){
			if(gd == 5){
				yawa+=(rotationSpeed);
			}
			if(gd == 6){
				yawa-=(rotationSpeed);
			}
		}
		if((lookLeft)){
			if(gd == 5){
				yawa-=(rotationSpeed);
			}
			if(gd == 6){
				yawa+=(rotationSpeed);
			}
		}
		if(yaw >= 360){
			yaw -= 360;
		}
		if(yaw <= -360){
			yaw += 360;
		}
		if(System.currentTimeMillis() - startTime >= 100) {
			new robot();
      			startTime = System.currentTimeMillis();
       	}
	}
	
	//Makes sure the player's pitch does not exceed its bounds
	public void checkPitch(){
		if(pitch > 90){
			pitch = 90;
		}
		if(pitch < -90){
			pitch = -90;
		}
	}
	
	//Checks to see if the player has reset the level and resets it if so
	public void checkReset(boolean reset){
		if(reset){
       		InputHandler.rightMouse = false;
			MainClass.load();
       	}
	}
	
	//Rolls the player if neccesary
	public void checkRoll(){
		if(roll > flip){
			roll -= 6;
		}
		if(roll < flip){
			roll += 6;
		}
	}
	
	//Checks to see if the player has walked and if so has them move in the appropriate direction
	public void checkWalk(boolean walkForward, boolean walkBack, boolean walkLeft, boolean walkRight){
		if(walkForward){
			if(gd == 5){
				yMove+=walkSpeed * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
			if(gd == 6){
				yMove-=walkSpeed * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
		if(walkBack){
			if(gd == 5){
				yMove-=walkSpeed * (System.currentTimeMillis() - MainClass.timeSinceTick);
				}
			if(gd == 6){
				yMove+=walkSpeed * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
		if(walkRight){
			if(gd == 5){
				xMove+=walkSpeed * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
			if(gd == 6){
				xMove+=walkSpeed * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
		if(walkLeft){
			if(gd == 5){
				xMove-=walkSpeed * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
			if(gd == 6){
				xMove-=walkSpeed * lookMultiplier * (System.currentTimeMillis() - MainClass.timeSinceTick);
			}
		}
	}
	
	//Performs the gravitational calculations on the player
	public void performGravitationalCalculations(){
		if(gd == 5){
			double force = .0001;
			radians = Math.atan2((0),(-z));
			gravVectorX1 = force*((Math.cos(radians))+(Math.sin(radians)));
			gravVectorZ1 = force*((-Math.sin(radians))+(Math.cos(radians)));
			tempVelocityX = ((velocityX1*Math.cos(radians-oldRadians))+(velocityZ1*Math.sin(radians-oldRadians)));
			tempVelocityZ = ((-velocityX1*Math.sin(radians-oldRadians))+(velocityZ1*Math.cos(radians-oldRadians)));
			oldRadians = radians;
		}
		if(gd == 6){
			double force = .0001;
			radians = Math.atan2((0),(9-z));
			gravVectorX1 = force*((Math.cos(radians))+(Math.sin(radians)));
			gravVectorZ1 = force*((-Math.sin(radians))+(Math.cos(radians)));
			tempVelocityX = ((velocityX1*Math.cos(radians-oldRadians))+(velocityZ1*Math.sin(radians-oldRadians)));
			tempVelocityZ = ((-velocityX1*Math.sin(radians-oldRadians))+(velocityZ1*Math.cos(radians-oldRadians)));
			oldRadians = radians;
		}
		if(gd == 5){
			velocityZ1 = tempVelocityZ;
			velocityZ1 += gravVectorZ1;
			zMove += velocityZ1 * (System.currentTimeMillis() - MainClass.timeSinceTick);
		}
		if(gd == 6){
			velocityZ1 = tempVelocityZ;
			velocityZ1 += gravVectorZ1;
			zMove -= velocityZ1 * (System.currentTimeMillis() - MainClass.timeSinceTick);
		}
	}
	
	//Rotates the x and y movement in order to make the player walk in the correct direction
	public void rotate(){
		if(gd == 5){
			xa-=(xMove*Math.cos(Math.toRadians(yaw)) + yMove * Math.sin(Math.toRadians(yaw)));
			ya+=(yMove*Math.cos(Math.toRadians(yaw)) - xMove * Math.sin(Math.toRadians(yaw)));
			za=zMove;
		}
		if(gd == 6){
			xa-=(xMove*Math.cos(Math.toRadians(yaw)) + yMove * Math.sin(Math.toRadians(yaw)));
			ya-=(yMove*Math.cos(Math.toRadians(yaw)) - xMove * Math.sin(Math.toRadians(yaw)));
			za=-zMove;
		}
	}
	
	//Handles all the movement operations for the player
	public void tick(boolean lookLeft, boolean lookRight, boolean lookUp, boolean lookDown, boolean walkForward, boolean walkBack, boolean walkRight, boolean walkLeft, boolean fliped, boolean jump, boolean move, boolean up, boolean down, boolean left, boolean right, boolean forward, boolean back, boolean reset){	
		yMove = 0;
		xMove = 0;
		zMove = 0;
		checkPitch();
		checkRoll();
		checkMouseInput(lookUp,lookDown,lookRight,lookLeft);
       	checkReset(reset);
		checkFlip(fliped);
		checkJump(jump);
		performGravitationalCalculations();
		checkWalk(walkForward,walkBack,walkLeft,walkRight);
		rotate();
		x += xa;
		y += ya;
		z += za;
		xa*=0.1;
		ya*=0.1;
		za*=0.1;
		yaw+=yawa;
		yawa*=0.1;
		pitch+=pitcha;
		pitcha*=0.1;
		roll+=rolla;
		rolla*=.1;
		sy = Math.sin(Math.toRadians(yaw));
		cy = Math.cos(Math.toRadians(yaw));
		sp = Math.sin(Math.toRadians(pitch));
		cp = Math.cos(Math.toRadians(pitch));
		sr = Math.sin(Math.toRadians(roll));
		cr = Math.cos(Math.toRadians(roll));
	}
	
	//~~~~~~End Methods~~~~~~\\

}

//******End Class******\\