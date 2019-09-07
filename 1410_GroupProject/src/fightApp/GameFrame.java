package fightApp;

import javax.swing.JFrame;

public class GameFrame {
	public static void main(String[]args) {
		JFrame frame = new JFrame();
		GameWorld gameWorld = new GameWorld();

		frame.add(gameWorld);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(280, 140, 500, 500);
		frame.setSize(752, 484);
		frame.setVisible(true);
		
		framesPerSecond(gameWorld);
		
		frame.getContentPane().remove(gameWorld);
		frame.add(new ReadAndWrite());
		frame.getContentPane().invalidate();
		frame.getContentPane().validate();
	}

	private static void framesPerSecond(GameWorld gameWorld) {
		long nextSecond = System.currentTimeMillis() + 1000;
		int frameInLastSecond = 0;
		int framesInCurrentSecond = 0;
		
		do{
			//just doing nothing so our loop is just doing something or it wont run through
			//can out output FPS if we want
			long currentTime = System.currentTimeMillis();
		    if (currentTime > nextSecond) {
		        nextSecond += 1000;
		        frameInLastSecond = framesInCurrentSecond;
		        framesInCurrentSecond = 0;
		    }
		    framesInCurrentSecond++;
		    System.out.println("FPS:  " + frameInLastSecond/1000);
		}while(gameWorld.getGameOver() != true);
	}
}