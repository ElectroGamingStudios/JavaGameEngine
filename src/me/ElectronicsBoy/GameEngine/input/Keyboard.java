package me.ElectronicsBoy.GameEngine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.ElectronicsBoy.GameEngine.Target;

public class Keyboard extends KeyAdapter {
	private boolean[] pressedKeys = new boolean[8];

	private Target t;
	
	public Keyboard(Target t) {
		this.t = t;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			pressedKeys[0] = true;
		}
		else if(key == KeyEvent.VK_A) {
			pressedKeys[1] = true;
		}
		else if(key == KeyEvent.VK_S) {
			pressedKeys[2] = true;
		}
		else if(key == KeyEvent.VK_D) {
			pressedKeys[3] = true;
		}
		
		else if(key == KeyEvent.VK_UP) {
			pressedKeys[4] = true;
		}
		else if(key == KeyEvent.VK_LEFT) {
			pressedKeys[5] = true;
		}
		else if(key == KeyEvent.VK_DOWN) {
			pressedKeys[6] = true;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			pressedKeys[7] = true;
		}
		
		else if(key == KeyEvent.VK_ESCAPE) {
			t.onESC();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			pressedKeys[0] = false;
		}
		else if(key == KeyEvent.VK_A) {
			pressedKeys[1] = false;
		}
		else if(key == KeyEvent.VK_S) {
			pressedKeys[2] = false;
		}
		else if(key == KeyEvent.VK_D) {
			pressedKeys[3] = false;
		}
		
		else if(key == KeyEvent.VK_UP) {
			pressedKeys[4] = false;
		}
		else if(key == KeyEvent.VK_LEFT) {
			pressedKeys[5] = false;
		}
		else if(key == KeyEvent.VK_DOWN) {
			pressedKeys[6] = false;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			pressedKeys[7] = false;
		}
	}
	
	public boolean[] getPressedKeys() {
		return pressedKeys;
	}
}
