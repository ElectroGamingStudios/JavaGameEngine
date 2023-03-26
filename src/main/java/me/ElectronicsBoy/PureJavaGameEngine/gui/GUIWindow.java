package me.ElectronicsBoy.PureJavaGameEngine.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import me.ElectronicsBoy.PureJavaGameEngine.Engine;

public abstract class GUIWindow {
	protected HUD hud;
	protected GUI gui;
	protected List<Button> buttons = new ArrayList<Button>();
	protected String runnableState;
	protected Engine engine;
	
	public GUIWindow(String runnableState, Engine engine) {
		this.runnableState = runnableState;
		this.engine = engine;
	}
	
	public void init(HUD hud, GUI gui) {
		this.gui = gui;
		this.hud = hud;
		this.init();
	}
	public void tick() {
		this.tickUI();
		for(Button b : buttons) b.tick();
	}
	public void render(Graphics g) {
		this.renderUI(g);
		for(Button b : buttons) b.postRender(g);
	}
	
	public abstract void init();
	public abstract void tickUI();
	public abstract void renderUI(Graphics g);
	
	protected void addButton(Button b) {
		buttons.add(b);
	}
	public String getRunnableState() {
		return runnableState;
	}
}
