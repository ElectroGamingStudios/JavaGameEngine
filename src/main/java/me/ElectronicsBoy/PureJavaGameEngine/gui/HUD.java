package me.ElectronicsBoy.PureJavaGameEngine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import me.ElectronicsBoy.PureJavaGameEngine.Util.Util;

public class HUD {
	private List<String> renderName = new ArrayList<String>();
	private List<String> renderValue = new ArrayList<String>();
	
	private Font font = new Font("arial", 1, 15);
	
	private int indent = 16;
	private int lastItemX = 5;
	private int nextX = lastItemX + indent;
	
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
		if(!renderName.isEmpty()) {
			g.drawString(renderName.get(0) + ": " + renderValue.get(0), 15, nextX);
			indent = 8;
			nextX += lastItemX + indent;
			for(int i = 1; i < renderName.size(); i++) {
				g.drawString(renderName.get(i) + ": " + renderValue.get(i), 15, nextX);
				nextX += lastItemX + indent;
			}
		}
	}
	
	public void addRenderValue(String var, String value) {
		renderName.add(var);
		renderValue.add(value);
	}
	
	public void updateValue(int valuePoint, String value) {
		int actuallPoint = valuePoint-1;
		renderValue.set(actuallPoint, value);
	}

	public void removeAll() {
		renderName.removeIf((e) -> true);
		renderValue.removeIf((e) -> true);
	}
}
