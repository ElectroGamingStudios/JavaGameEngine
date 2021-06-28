package io.github.electronicsboy.GameEngine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import io.github.electronicsboy.GameEngine.Engine;
import io.github.electronicsboy.GameEngine.Util.Util;

public class GUI {
	public GUI() {}
	
	@Deprecated
	public void drawTitle(Graphics g, String string, Font font) {
		g.setFont(font);
		if(((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) < 100){
			g.drawString(string, ((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) + (font.getSize()*4), 75);
			System.out.println(((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) + (font.getSize()*4));
		}else if(((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) < 200){
			g.drawString(string, ((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) + (font.getSize()*2), 75);
			System.out.println(((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) + (font.getSize()*2));
		}else if(((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) < 300){
			g.drawString(string, ((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) + font.getSize(), 75);
			System.out.println(((Util.WIDTH/2) - ((string.length()*font.getSize())/2)) + font.getSize());
		}
	}
	
	public void postRender(Graphics g, Color backgroundColor) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Util.WIDTH, Util.HEIGHT);
	}
	
	public void postRenderButton(Graphics g, Color onHoverButtonColor, Color defaultButtonColor, Button button, ButtonStyle buttonStyle, int tx, int ty, String title, Color tc, Font font) {
		if(button != null) {
			if(Engine.getMouse().isMouseOver(button.getX(), button.getY(), button.getWidth(), button.getHeight())){
				button.postRender(g, onHoverButtonColor, buttonStyle, tx, ty, title, tc, font);
			}else {
				button.postRender(g, defaultButtonColor, buttonStyle, tx, ty, title, tc, font);
			}
		}
	}
	public void postRenderButton(Graphics g, Color onHoverButtonColor, Color defaultButtonColor, Button button, ButtonStyle buttonStyle) {
		if(button != null) {
			if(Engine.getMouse().isMouseOver(button.getX(), button.getY(), button.getWidth(), button.getHeight())){
				button.postRender(g, onHoverButtonColor, buttonStyle);
			}else {
				button.postRender(g, defaultButtonColor, buttonStyle);
			}
		}
	}
}
