package io.github.electronicsboy.GameEngine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import io.github.electronicsboy.GameEngine.Engine;

public class Button {
	
	private boolean isButtonClicked;
	
	private int x, y, width, height;
	
	public Button(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(Engine.getMouse().isMouseClicked()) {
			if(Engine.getMouse().isMouseOver(x, y, width, height)) {
				isButtonClicked = true;
			}else {
				isButtonClicked = false;
			}
		}
	}
	
	public boolean isButtonClicked() {
		return isButtonClicked;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void postRender(Graphics g, Color col, ButtonStyle buttonStyle) {
		g.setColor(col);
		if(buttonStyle == ButtonStyle.FullRect)
			g.fillRect(x, y, width, height);
		else
			g.drawRect(x, y, width, height);
	}
	
	public void postRender(Graphics g, Color col, ButtonStyle buttonStyle, int tx, int ty, String title, Color tc, Font font) {
		g.setFont(font);
		g.setColor(col);
		if(buttonStyle == ButtonStyle.FullRect)
			g.fillRect(x, y, width, height);
		else
			g.drawRect(x, y, width, height);
		g.setColor(tc);
		g.drawString(title, tx, ty);
	}
}
