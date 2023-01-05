package me.ElectronicsBoy.GameEngine.Util;

import me.ElectronicsBoy.GameEngine.Engine;
import me.ElectronicsBoy.GameEngine.gui.Button;

public class Util {
	
	public static final int WIDTH = 640, HEIGHT = 480;
	
	public static class ClampUtil {
		public static float clamp(float var, float min, float max) {
			if(var >= max) {
				return var = max;
			}else if(var <= min) {
				return var = min;
			}else {
				return var;
			}
		}
	}
	
	public static class ButtonUtil {
		public static boolean checkIfButtonIsClicked(Button button) {
			if(Engine.getMouse().isMouseClicked()) {
				if(Engine.getMouse().isMouseOver(button.getX(), button.getY(), button.getWidth(), button.getHeight())) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
	}
}
