package CollisionDetection;

import java.awt.Image;
import java.awt.Rectangle;
import java.event.KeyEvent;
import java.event.Event;

public class SpaceShip extends Sprite {
  
  private int x;
  private int y;
  private int width;
  private int height;
  private boolean visible;
  
  private void SpaceShip () {
    KeyReleased = e.getKeyCode;
    
    x += dx;
    y += dy;
    isVisible = true;
    
  if (key == VK_SPACE) {
    fire();
  }
  
  if (key == VK_LEFT) {
    dx = -1;
  }
    
  if (key == VK_RIGHT) {
    dx = 1;
  }
    
  if (key == VK_UP) {
    dy = -1;
  }
    
  if (key == VK_DOWN) {
    dy = 1;
  }
    

List<Missiles> missiles = fire();
    
  if (key == VK_LEFT) {
    dx = 0;
  }
    
  if (key == VK_RIGHT) {
    dx = 0;
  }
    
  if (key == VK_UP) {
    dy = 0;
  }
    
  if (key == VK_DOWN) {
    dy = 0;
  }

 //There are many errors, this is to be fixed//
    
    

    
    
