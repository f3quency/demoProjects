package fightApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * 
 * @author Febechukwu Megwalu, Simeon Cross
 *
 */

public class PlayerFighter_1 {
	private int xLocation, dxChange, yLocation, xLeftLimit,xRightLimit, health = 5;
	private Image playerImage, moveImage, attackImage, dieImage;
	private int player_2XLocation;
	private int elapsedTime = 10000, titleTime;
	
	private PlayerFighter_2 player_2;
	
	/**
	 * Creates healthBars
	 */
	private ImageIcon[] healthBars = new ImageIcon[] {
			new ImageIcon("src/fightApp/HealthBars/NoHealth.png"),
			new ImageIcon("src/fightApp/HealthBars/1Health.png"),
			new ImageIcon("src/fightApp/HealthBars/2Health.png"),
			new ImageIcon("src/fightApp/HealthBars/3Health.png"),
			new ImageIcon("src/fightApp/HealthBars/4Health.png"),
			new ImageIcon("src/fightApp/HealthBars/FullHealth.png")
		};
	
	/**
	 * Creates Player movement images
	 */
	private ImageIcon[] imageIcons = new ImageIcon[] {
		new ImageIcon("src/fightApp/Images/Player_Stand.gif"),
		new ImageIcon("src/fightApp/Images/Player_Strike.gif"),
		new ImageIcon("src/fightApp/Images/Player_Die.gif")
	};
	private int tenStrikes = 0;
	
	/**
	 * creates PlayerFigher
	 */
	public PlayerFighter_1() {
		xLeftLimit = 15;
		xLocation = xLeftLimit;

		moveImage = imageIcons[0].getImage();
		attackImage = imageIcons[1].getImage();
		dieImage = imageIcons[2].getImage();
		playerImage = moveImage;
	}
	
	public int getXPoint() {
		return xLocation;
	}
	
	/**
	 * Enables player images to move
	 */
	public Image move() {
		xLocation += dxChange;
		return moveImage;
	}
	

	public void die(){
	}

	public void attack() {
		if(player_2XLocation - xLocation < 80){
			player_2.setHealth();
		}
	}
	
	public Image getHealthBar(){
		return healthBars[getHealth()].getImage();
	}
	
	public void setHealth(){
		if (tenStrikes  == 10) {
			health--;
			tenStrikes = 0;
		}
		else {
			tenStrikes++;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public Image getPlayerImage() {
		return playerImage;
	}
	
	/**
	 * Enables player to move depending on what key is pressed
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch(key) {
		case KeyEvent.VK_D:
			dxChange = 5;
			break;
		case KeyEvent.VK_A:
			dxChange = -5;
			break;
		case KeyEvent.VK_W:
			playerImage = attackImage;
			attack();
			break;
		}
		if(xLocation < xLeftLimit)
			xLocation = xLeftLimit;
		if(xLocation > player_2XLocation - 60)
			xLocation = player_2XLocation - 60;
		if(xLocation > xRightLimit)
			xLocation = xRightLimit;
	}
	
	/**
	 * Enables player to move depending on what key is released
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch(key) {
		case KeyEvent.VK_D:
			dxChange = 0;
			break;
		case KeyEvent.VK_A:
			dxChange = 0;
			break;
		case KeyEvent.VK_W:
			playerImage = moveImage;
			break;
		}
		if(health == 0)
			playerImage = dieImage;
	}
	
	/**
	 * Draws player image
	 * @param g2d
	 * @param getWidth
	 * @param getHeight
	 * @param player_2XLocation
	 */
	public void drawImage(Graphics2D g2d, int getWidth, int getHeight, PlayerFighter_2 player_2) {
		yLocation = getHeight - 145;
		xRightLimit = getWidth - 145;
		this.player_2XLocation = player_2.getXPoint();
		this.player_2 = player_2;
		
		//to make sure characters don't float off screen
		if((xLocation >= xLeftLimit) && (xLocation <= xRightLimit) && (xLocation <= player_2XLocation - 60))
			g2d.drawImage(getPlayerImage(), xLocation, yLocation, null);
		else if(xLocation < xLeftLimit)
			g2d.drawImage(getPlayerImage(), xLeftLimit, yLocation, null);
		else if(xLocation > player_2XLocation - 60)
			g2d.drawImage(getPlayerImage(), player_2XLocation - 60, yLocation, null);
		else if(xLocation > getWidth - 145)
			g2d.drawImage(getPlayerImage(), getWidth - 145, yLocation, null);
		
		//player label
		String player = "Player 1";
		g2d.setColor(new Color(127, 255, 212));
		g2d.setFont(new Font(player, Font.BOLD, 20));
		g2d.drawString(player, 30, 27);
		
		//health bar
		g2d.drawImage(getHealthBar(), 10, 0 , null);

	    timer();
		String time = titleTime + "";
	    g2d.setColor(Color.WHITE);
		g2d.setFont(new Font(time, Font.BOLD, 50));
		g2d.drawString(time, (getWidth / 2) - 30, 50);
	}

	private void timer() {
		if(elapsedTime >= 0){
			
			titleTime = elapsedTime/100;
			elapsedTime -= 1;
			
		}
	}

	public int getElapsedTime() {
		return elapsedTime;
	}
}
