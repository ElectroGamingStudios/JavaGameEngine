package io.github.electronicsboy.GameEngine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	
	private boolean isButtonClicked;
	
	private int mx, my;
	
	@Override
	public void mousePressed(MouseEvent e) {
		isButtonClicked = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		isButtonClicked = false;
	}
	
	public boolean isMouseOver(int x, int y, int width, int height) {		
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
       mx = e.getX();
       my = e.getY();
    }
	
	public boolean isMouseClicked() {
		return isButtonClicked;
	}
}
