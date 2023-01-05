package me.ElectronicsBoy.GameEngine;

import java.awt.Graphics;

public interface Target {
	public void postTick();
	public void init();
	public void postRender(Graphics g);
	public void onStateChange();
	public void onESC();
}
