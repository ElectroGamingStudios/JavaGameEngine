package io.github.electronicsboy.GameEngine;

import java.awt.Graphics;

public interface Target {
	public abstract void postTick();
	public abstract void init();
	public abstract void postRender(Graphics g);
}
