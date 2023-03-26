package me.ElectronicsBoy.PureJavaGameEngine.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Handler {
	public List<Entity> object = new ArrayList<Entity>();
	
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
	
	public void removeObject(Entity... objectRM) {
		for(Entity obj : objectRM)
			this.object.removeIf((Entity a) -> a.equals(obj));
	}
	
	public void clearAll() {
		object.removeIf((Entity a) -> true);
	}

	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++)
			this.object.removeIf((Entity a) -> a.type == EntityType.Enemy);
	}
}
