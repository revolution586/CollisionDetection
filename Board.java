package CollisionDetection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private SpaceShip spaceShip;
	private List<Alien> aliens;
	private boolean ingame;
	private final int ICRAFT_X = 40;
	private final int	ICRAFT_Y = 60;
	private final int	B_WIDTH = 400;
	private final int B_HEIGHT = 300;
	private final int DELAY = 15;
	
	private final int[][] pos = {
		{2380, 29}, {2500, 59}, {1380, 89},
    {780, 109}, {580, 139}, {680, 239},
    {790, 259}, {760, 50}, {790, 150},
    {980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60},
    {940, 59}, {990, 30}, {920, 200},
    {900, 259}, {660, 50}, {540, 90},
    {810, 220}, {860, 20}, {740, 180},
    {820, 128}, {490, 170}, {700, 30}
	};
	//These are initial positions of alien ships//
	
	public Board() {
		
		initBoard();
	}
	
	private void initBoard() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;
		
		setPrefferedSize(new Dimension(B_WIDTH, B_HEIGHT));
		
		spaceship = new SpaceShip(ICRAFT_X, ICRAFT_Y);
		
		initAliens();
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void initAliens() {
		
		aliens = new ArrayList<>();
		
		for (int[] p : pos) {
			aliens.add(new Alien(p[0], p[1]));
		}
	}
	//The initAliens() method creates a list of alien objects. The aliens take their initial positions from the pos array//
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (ingame) {
			
			drawObjects(g);
		
		} else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	//Inside the painComponent() method, we either draw game sprites or write the game over message. This depends on the ingame variable//
	
	private void drawObjects(Graphics g) {
		
		if (spaceship.isVisible()) {
			g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);
		}
		
		List<Missile> ms = spaceship.getMissiles();
		
		for (Missile missile : ms) {
			if (missile.isVisible()) {
				g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
			}
		}
	//The drawObjects() methods draws the game sprites on the window. First, we draw the craftt sprite//
		
		for (Alien alien : aliens) {
			if (alien.isVisible()) {
				g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
			}
		}
		//In this loop we draw all aliens; they can be drawn only if they have not been previously destroyed. This is checked by the isVisible() method//
		
		g.setColor(Color.WHITE);
		g.drawString("Aliens left: " + aliens.size(), 5, 15);
	}
	
	//In the top-left corner of the window, there is a text showing how many aliens are left//
	
	private void drawGameOver(Graphics g) {
		
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2;
								 B_HEIGHT /2);
	}
	
	//The drawGameOver() draws a game over message in the middle of the window//
	//The message is displayed in the end of the game, either when we destroy all alien ships or when we collide with one of them//
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		inGame();
		
		updateShip();
		updateMissiles();
		updateAliens();
		
		checkCollisions();
		
		repaint();
	}
	
	//Each action event represents one game cycle//
	//The game logic is factored into specific game events//
	//For instance, the updateMissiles() moves all the available missiles//
	
	private void inGame() {
		
		if (!ingame) {
			timer.stop();
		}
	}
	
	private void updateShip() {
		
		if (spaceship.isVisible()) {
			
			spaceship.move();
		}
	}
	
	private void updateMissiles() {
		
		List<Missile> ms = spaceship.getMissiles();
		
		for (int i = 0; i < ms.size(); i++) {
			
			Missile m = ms.get(i);
			
			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
		}
	}
	
	private void updateAliens() {
		
		if (aliens.isEmpty()) {
			
			ingame = false;
			return;
		}
		
		for (int i =0; i < aliens.size(); i++) {
			
			Alien a = aliens.get(i);
			
			if (a.isVisible()) {
				a.move();
			} else {
				aliens.remove(i);
			}
		}
	}
	
	//Inside the updateAliens() method, we first check if there are any alien ships left in the aliens list..//
	//The game is finished if the list is empty//
	//If it is not empty, we go through the list and remove all of the alien ships within the list//
	
	public void checkCollisions() {
		
		Rectangle r3 = spaceship.getBounds();
		
		for (Alien alien : aliens) {
			
			Rectangle r2 = alien.getBounds();
			
			if (r3.intersects(r2)) {
				
				spaceship.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
		}
		
		//The checkCollisions() method checks for possible collisions//
		//First, we check if the craft object collides with any of the alien objects//
		//We get the rectangles of the objects with the getBounds() method//
		//The intersects() method checks if the two rectangles intersect//
		
		List<Missile> ms = spaceship.getMissiles();
		
		for (Missile m : ms) {
			
			Rectangle r1 = m.getBounds();
			
			for (Alien alien : aliens) {
				
				Rectangle r2 = alien.getBounds();
				
				if (r1.intersects(r2)) {
					
					m.setVisible(false);
					alien.setVisible(false);
				}
			}
		}
	}
	
		//This code cheks the collions between aliens and missiles//
	
	private class TAdapter extends KeyAdapter {
		
		@Override
		public void keyReleased(KeyEvent e) {
			spaceship.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			spaceship.keyPressed(e);
		}
	}
}
