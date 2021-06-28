package io.github.electronicsboy.GameEngine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

import io.github.electronicsboy.GameEngine.Util.Util;

public class HUD {
	
	private HashMap<Integer, String>renderNames = new  HashMap<Integer, String>();
	private HashMap<Integer, String>renderNamesValue = new  HashMap<Integer, String>();
	
	private Font font = new Font("arial", 1, 15);
	
	private int indent = 80 - 64;
	private int lastItemX = 5;
	private int nextX = lastItemX + indent;
	private int lastValue = 0;
	
	public static float HEALTH = 100;
	
	private boolean useHealth = true;
	
	public HUD(boolean useHealth) {
		this.useHealth = useHealth;
	}
	
	public void tick(HUDPostTick postTicker) {
		HEALTH = Util.ClampUtil.clamp(HEALTH, 0, 100);
		postTicker.postHUDTick();
	}
	
	public void render(Graphics g) {
		indent = 80 - 64;
		lastItemX = 5;
		nextX = lastItemX + indent;
		
		if(useHealth) {
			lastItemX = 15;
			
			g.setColor(Color.GRAY);
			g.fillRect(15, 15, 200, 32);
			
			g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
			g.fillRect(15, 15, (int)HEALTH * 2, 32);
		
			g.setColor(Color.WHITE);
			g.drawRect(15, 15, 200, 32);
			
			lastItemX += indent;
			nextX += lastItemX + indent;
		}
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		for(int i = 0; i < renderNames.size(); i++) {
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
