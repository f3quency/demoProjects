package fightApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.ImageIcon;

/**
 * 
 * @author John Hutchins
 *
 */

public class PlayerFighter_2 {
	private int xLocation, dxChange, yLocation, xLeftLimit,xRightLimit, health = 5;
	private Image playerImage, moveImage, attackImage, dieImage;
	private int player_1XLocation;
	private PlayerFighter_1 player_1;
	
	private ImageIcon[] healthBars = new ImageIcon[] {
			new ImageIcon("src/fightApp/HealthBars/CPUNoHealth.png"),
			new ImageIcon("src/fightApp/HealthBars/CPU1Health.png"),
			new ImageIcon("src/fightApp/HealthBars/CPU2Health.png"),
			new ImageIcon("src/fightApp/HealthBars/CPU3Health.png"),
			new ImageIcon("src/fightApp/HealthBars/CPU4Health.png"),
			new ImageIcon("src/fightApp/HealthBars/CPUFullHealth.png")
		};
	
	private ImageIcon[] imageIcons = new ImageIcon[] {
			new ImageIcon("src/fightApp/Images/Cpu_Stand.gif"),
			new ImageIcon("src/fightApp/Images/Cpu_Strike.gif"),
			new ImageIcon("src/fightApp/Images/Cpu_Die.gif")
		};
	private int tenStrikes = 0;

	/**
	 * creates computer fighter
	 */
	public PlayerFighter_2(){
		xRightLimit = 800;
		xLocation = xRightLimit;
		
		moveImage = imageIcons[0].getImage();
		attackImage = imageIcons[1].getImage();
		dieImage = imageIcons[2].getImage();
		playerImage = moveImage;
	}

	/*
	 * @Author John -- 4/12
	 * best way to call a random method inside of a class without
	 * using reflection
	 */
	
	public int getXRightLimit() {
		return xRightLimit;
	}
	
	public int getXLeftLimit() {
		return xLeftLimit;
	}
	
	public int getXPoint(){
		return xLocation;
	}
	
	public int getYPoint() {
		return yLocation;
	}
	
	public Image move() {
		xLocation += dxChange;
		return moveImage;
	}
	
	public void attack() {
		/*
		 * player 2's location will always be greater than player ones
		 * so we subtract smaller int from larger int.
		 * also, bigger player img, so larger hit box to make hitting more realistic
		 * 4-18
		 */
		if(xLocation - player_1XLocation < 80){
			player_1.setHealth();
		}
	}
	
	public void die(){
		playerImage = dieImage;
	}
	
	public Image getHealthBar(){
		return healthBars[getHealth()].getImage();
	}
	
	public void setHealth(){
		if (tenStrikes == 10) {
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
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch(key) {
		case KeyEvent.VK_RIGHT:
			dxChange = 5;
			break;
		case KeyEvent.VK_LEFT:
			dxChange = -5;
			break;
		case KeyEvent.VK_UP:
			playerImage = attackImage;
			attack();
			break;
		}
		if(xLocation < xLeftLimit)
			xLocation = xLeftLimit;
		if(xLocation < player_1XLocation + 60)
			xLocation = player_1XLocation + 60;
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
		case KeyEvent.VK_RIGHT:
			dxChange = 0;
			break;
		case KeyEvent.VK_LEFT:
			dxChange = 0;
			break;
		case KeyEvent.VK_UP:
			playerImage = moveImage;
			break;
		}
		if(health == 0)
			playerImage = dieImage;
	}
	
	public void drawImage(Graphics2D g2d, int getWidth, int getHeight, PlayerFighter_1 player_1) {
		/**
		 * Ensures Computer don't exceed right or left limit
		 */
		yLocation = getHeight - 145;
		xRightLimit = getWidth - 145;
		this.player_1XLocation = player_1.getXPoint();
		this.player_1 = player_1;
		
		if((xLocation >= xLeftLimit) && (xLocation <= xRightLimit) && (xLocation >= player_1XLocation - 60))
			g2d.drawImage(getPlayerImage(), xLocation, yLocation, null);
		else if(xLocation < xLeftLimit)
			g2d.drawImage(getPlayerImage(), xLeftLimit, yLocation, null);
		else if(xLocation < player_1XLocation - 60)
			g2d.drawImage(getPlayerImage(), player_1XLocation - 60, yLocation, null);
		else if(xLocation > getWidth - 145)
			g2d.drawImage(getPlayerImage(), getWidth - 145, yLocation, null);
		
		String player = "Player 2";
		g2d.setColor(new Color(127, 255, 212));
		g2d.setFont(new Font(player, Font.BOLD, 20));
		g2d.drawString(player, getWidth - 115, 27);
		
		g2d.drawImage(getHealthBar(), getWidth - 310, 0 , null);
	}
}
