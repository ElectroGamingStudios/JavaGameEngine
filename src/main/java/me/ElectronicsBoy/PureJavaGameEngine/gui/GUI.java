package me.ElectronicsBoy.PureJavaGameEngine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.ElectronicsBoy.PureJavaGameEngine.Util.Util;

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
}
