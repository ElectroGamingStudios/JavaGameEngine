package me.ElectronicsBoy.PureJavaGameEngine.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.ElectronicsBoy.PureJavaGameEngine.Engine;
import me.ElectronicsBoy.PureJavaGameEngine.Util.Util;

public abstract class Entity {
	protected int width, height;
	protected float y;
	protected float x;
	protected float velX, velY;
	protected Color col;
	public void setCol(Color col) {
		this.col = col;
	}
	protected MovementType movementType;
	protected BufferedImage texture;
	
	private int[] keyArray;
	
	protected EntityType type;
	
	public Entity(int width, int height, float x, float y, EntityType type, Color col, MovementType movementType, BufferedImage texture) {
		this.height = height;
		this.width = width;
		this.type = type;
		this.col = col;
		this.x = x;
		this.y = y;
		this.movementType = movementType;
		this.texture = texture;
	}
	public Entity() {}
	
	public abstract void tick();
	public abstract void postRender(Graphics g, boolean useTexture);
	
	public void setupMove() {
		if(movementType == MovementType.WASD) {
			keyArray = new int[4];
			
			for(int i = 0; i < 4; i ++) {
				keyArray[i] = i;
			}
		}else if(movementType == MovementType.ArrowKeys) {
			keyArray = new int[4];
			
			for(int i = 4; i < 8; i ++) {
				keyArray[i-4] = i;
			}
		}
	}
	
	public void move() {
		if(movementType == MovementType.Bounce) {
			x += velX;
			y += velY;
			
			if(y <= 0 || y >= (Util.HEIGHT-((height*3)))) {
				velY *= -1;
			}
			
			if(x <= 0 || x >= (Util.WIDTH-(width*3))) {
				velX *= -1;
			}
		}else if(movementType == MovementType.WASD) {
			boolean[] keys = Engine.getKeyboard().getPressedKeys();
			
			boolean[] WASDKeys = new boolean[4];
			
			for (int i = 0; i < keyArray.length; i++) {
				WASDKeys[i] = keys[i];
			}
			
			for (int i = 0; i < WASDKeys.length; i++) {
				if(WASDKeys[i] == true) {
					if(i == 0) {
						y -= velY;
					}
					if(i == 1) {
						x -= velX;
					}
					if(i == 2) {
						y += velY;
					}
					if(i == 3) {
						x += velX;
					}
				}
			}
		}else if(movementType == MovementType.ArrowKeys) {
			boolean[] keys = Engine.getKeyboard().getPressedKeys();
			
			boolean[] WASDKeys = new boolean[4];
			
			for (int i = 0; i < keyArray.length; i++) {
				WASDKeys[i] = keys[(i+4)];
			}
			
			for (int i = 0; i < WASDKeys.length; i++) {
				if(WASDKeys[i] == true) {
					if(i == 0) {
						y -= velY;
					}
					if(i == 1) {
						x -= velX;
					}
					if(i == 2) {
						y += velY;
					}
					if(i == 3) {
						x += velX;
					}
				}
			}
		}
		if(movementType != MovementType.Bounce) {
			x = Util.ClampUtil.clamp(x, 0, (Util.WIDTH-(width)));
			y = Util.ClampUtil.clamp(y, 0, (Util.HEIGHT-(height*2)));
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public Color getCol() {
		return col;
	}

	public EntityType getType() {
		return type;
	}
	public void setY(float y) {
		this.y = y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}	
	public abstract Rectangle getBounds();
}
