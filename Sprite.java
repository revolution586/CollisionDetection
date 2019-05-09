package CollisionDetection;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

//The code that can be shared by all sprites (missile, spaceship, aliens) is shared in the Sprite class//

public class Sprite {
  
  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected boolean visible;
  protected Image image;
  
  public Sprite(int x, int y) {
    
    this.x = x;
    this.y = y;
    visible = true;
  }
	
  protected void getImageDimensions() {
		
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	protected void loadImage(String imageName) {
		
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}	

	public int getY() {
		return y;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(Boolean visible) [
		this.visible = visible;
		}
		
		public Rectangle getBounds() [
			return new Rectangle(x, y, width, height);
		}
	}
//The getBounds() method retuurns the bounding rectangle of the sprite image//
//We need the bounds in collision detection//
		
