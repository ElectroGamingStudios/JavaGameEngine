package me.ElectronicsBoy.GameEngine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

import me.ElectronicsBoy.GameEngine.Util.Util;

public class HUD {
	
	private HashMap<Integer, String>renderNames = new  HashMap<Integer, String>();
	private HashMap<Integer, String>renderNamesValue = new  HashMap<Integer, String>();
	
	private Font font = new Font("arial", 1, 15);
	
	private int indent = 16;
	private int lastItemX = 5;
	private int nextX = lastItemX + indent;
	private int lastValue = 0;
	
	public float health = 100;
	
	private boolean useHealth = true;
	
	public HUD(boolean useHealth) {
		this.useHealth = useHealth;
	}
	
	public void tick(HUDPostTick postTicker) {
		health = Util.ClampUtil.clamp(health, 0, 100);
		postTicker.postHUDTick();
	}
	
	public void render(Graphics g) {
		indent = 16;
		lastItemX = 5;
		nextX = lastItemX + indent;
		
		if(useHealth) {
			lastItemX = 15;
			
			g.setColor(Color.GRAY);
			g.fillRect(15, 15, 200, 32);
			
			g.setColor(Color.getHSBColor((1f * health) / 360, 1f, 1f));
			g.fillRect(15, 15, (int)health * 2, 32);
		
			g.setColor(Color.WHITE);
			g.drawRect(15, 15, 200, 32);
			lastItemX += indent;
			nextX += lastItemX + indent;
		}
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(renderNames.get(0) + ": " + renderNamesValue.get(0), 15, nextX);
		indent = 8;
		nextX += lastItemX + indent;
		for(int i = 1; i < renderNames.size(); i++) {
			g.drawString(renderNames.get(i) + ": " + renderNamesValue.get(i), 15, nextX);
			nextX += lastItemX + indent;
		}
	}
	
	public void addRenderValue(String var, String value) {
		renderNames.put(lastValue, var);
		renderNamesValue.put(lastValue, value);
		lastValue++;
	}
	
	public void updateValue(int valuePoint, String value) {
		int actuallPoint = valuePoint-1;
		renderNamesValue.put(actuallPoint, value);
	}
}
