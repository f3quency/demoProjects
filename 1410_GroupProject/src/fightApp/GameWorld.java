package fightApp;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Febechukwu Megwalu, Simeon Cross, and John Hutchins
 *
 */

public class GameWorld extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -7752385302301571409L;
	private GameFrame gFrame = new GameFrame();
	private PlayerFighter_1 playerFighter_1;
	private PlayerFighter_2 playerFighter_2;
	private Timer timer;
	private Timer timerCount;
	private boolean gameover = false;
	private int countDown = 200;
	BackgroundMusic backgroundMusic = new BackgroundMusic("src/fightApp/Music/StreetFighter.wav");
	
	/**
	 * creates GameWorld
	 */
	public GameWorld() {
		playerFighter_1 = new PlayerFighter_1();
		playerFighter_2 = new PlayerFighter_2();
		
		addKeyListener(new AL());		
		setFocusable(true);
		
		timer = new Timer(15, this);
		timer.start();
		
		backgroundMusic.play();
		
	}
	
	/**
	 * creates actionPerformed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		playerFighter_1.move();
		playerFighter_2.move();		
		repaint();
	}
	
	/**
	 * paints graphics
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(new ImageIcon("src/fightApp/Images/background.gif").getImage(), // Draws background image
				0, 0, this.getWidth(), this.getHeight(), null);
		
		playerFighter_1.drawImage(g2d, this.getWidth(), this.getHeight(), playerFighter_2); // Draws player image
		playerFighter_2.drawImage(g2d, this.getWidth(), this.getHeight(), playerFighter_1);
		
		if(playerFighter_1.getHealth() == 0){
			//change frame so that it says P2 won
			winTimer();
			String player = "Player 2 Wins!";
			g2d.setFont(new Font(player, Font.BOLD, 78));
			g2d.drawString(player, getWidth()/8, 200);
		}else if(playerFighter_2.getHealth() == 0){
			//change frame so that it says P2 won
			winTimer();
			String player = "Player 1 Wins!";
			g2d.setFont(new Font(player, Font.BOLD, 78));
			g2d.drawString(player, getWidth()/8, 200);
		}else if(playerFighter_1.getElapsedTime() <= 0){
			//change frame so that is says no one won or tie game
			winTimer();
			String tie = "Tie Game!";
			g2d.setFont(new Font(tie, Font.BOLD, 78));
			g2d.drawString(tie, getWidth()/4, 200);	
		}
	
	}
	private void winTimer() {
		if(countDown >= 0){
			countDown -= 2;
			
		}else{
			setGameOver();
		}
	}
	
	/**
	 * creates anonymous class
	 * @author gaban
	 *
	 */
	private class AL extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			playerFighter_1.keyPressed(e);
			playerFighter_2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			playerFighter_1.keyReleased(e);
			playerFighter_2.keyReleased(e);
		}
	}

	public void setGameOver() {
		gameover = true;
	}
	public boolean getGameOver() {
		return gameover;
	}
}