package CollisionDetection;

import java.awt.event.KeyEvent;

public class SpaceShip extends Sprite {
  
  private int dx;
  private int dy;
  private List<Missile> missiles;
  
  //All the missiles fired by the spacecraft are in the missiles list//
  
  public SpaceShip (int x, int y) {
    super(x, y);
    
    initCraft();
  }
  
  private void initCraft() {
    
    missiles = new ArrayList<>();
    loadImage("src/resources/missile.png");
    getImageDimensions();
  }
  
  public void move() {
    
    x += dx;
    y += dy;
    
    if (x < 1) {
      x = 1;
    }
    
    if (y < 1) {
      y = 1;
    }
  }
  
  public List<Missile> getMissiiles() {
    return missile;
  }
  
  public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) {
			fire();
		}
		
		if (key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		
		if (key == KeyEvent.VK_UP) {
			dy = -1;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			dy = 1;
		}
	}
	
	public void fire() {
		missiles.add(new Missile(x + width,y + height / 2));
	}
	
	//When we fire a missile, a new Missile object is added to the missile list//
	//It is retained in the list until it collides with an alien or goes out a window//
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
}
	
	
		
    
      
    
    
    
