package me.ElectronicsBoy.GameEngine.gui;

import java.awt.Color;
import java.awt.Graphics;

import me.ElectronicsBoy.GameEngine.Engine;

public class Button {
	
	public static long LAST_CLICK = System.currentTimeMillis();
	
	private boolean isButtonClicked;
	
	private int x, y, width, height, tx, ty;
	private Color normalColor, onhoverColor, tc;
	private ButtonStyle buttonStyle;
	private String text;
	private ButtonRunnable run;
	
	public Button(int x, int y, int width, int height, Color normalColor, Color onhoverColor, ButtonStyle buttonStyle, ButtonRunnable run) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.tx = 0;
		this.ty = 0;
		this.text = "";
		this.normalColor = normalColor;
		this.onhoverColor = onhoverColor;
		this.buttonStyle = buttonStyle;
		this.tc = null;
		this.run = run;
	}
	public Button(int x, int y, int width, int height, Color normalColor, Color onhoverColor, ButtonStyle buttonStyle, int tx, int ty, String text, Color tc, ButtonRunnable run) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.tx = tx;
		this.ty = ty;
		this.normalColor = normalColor;
		this.onhoverColor = onhoverColor;
		this.buttonStyle = buttonStyle;
		this.text = text;
		this.tc = tc;
		this.run = run;
	}
	
	public void tick() {
		if(Engine.getMouse().isMouseClicked() && (System.currentTimeMillis() - LAST_CLICK) > 200) {
			if(Engine.getMouse().isMouseOver(x, y, width, height)) {
				LAST_CLICK = System.currentTimeMillis();
				isButtonClicked = true;
				run.onClick();
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
	
	public void postRender(Graphics g) {
		g.setFont(buttonStyle.getFont());
		g.setColor(Engine.getMouse().isMouseOver(x, y, width, height) ? onhoverColor : normalColor);
		if(buttonStyle == ButtonStyle.FullRect)
			g.fillRect(x, y, width, height);
		else
			g.drawRect(x, y, width, height);
		if(tc != null && tx != 0 && ty != 0 && !text.equals("")) {
			g.setColor(tc);
			g.drawString(text, tx, ty);
		}
	}
	
	public String getText() {
		return text;
	}
}
