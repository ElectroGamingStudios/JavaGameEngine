package me.ElectronicsBoy.PureJavaGameEngine.SpriteSheet;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = sprite.getSubimage((row * 32) - 32, (col * 32) - 32, width, height);
		return img;
	}
}
