package me.ElectronicsBoy.PureJavaGameEngine;

import java.awt.Graphics;

/**
 * Project: Java Game Engine
 * By: ElectronicsBoy
 * File: Target.java
*/
public interface Target {
	public void postTick();
	public void init();
	public void postRender(Graphics g);
	public void onStateChange();
	public void onESC();
}
