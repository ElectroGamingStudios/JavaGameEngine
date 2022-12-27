package io.github.electronicsboy.GameEngine.entity;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	public ArrayList<Entity> object = new ArrayList<Entity>();
	
	public void postTick() {
		for(int i = 0; i < object.size(); i++) {
			 Entity tempObject = object.get(i);
			 
			 tempObject.tick();
		}
		
	}
	
	public void postRender(Graphics g, boolean useTexture) {
		for(int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);	 
			
			tempObject.postRender(g, useTexture);
		}
	}
	
	public void addObject(Entity... object) {
		for(int i = 0; i < object.length; i++)
			this.object.add(object[i]);
	}
	
	public void removeObject(Entity... object) {
		for(int i = 0; i < object.length; i++)
			this.object.remove(object[i]);
	}
	
	public void clearAll() {
		for(int i = 0; i < object.size(); i++)
			this.object.remove(object.get(i));
	}
}
