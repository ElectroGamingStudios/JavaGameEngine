/*
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <https://unlicense.org>
*/

//NOTE: This demo is for version 1.0

package io.github.electronicsboy.GameEngineDemos.v1_0;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import io.github.electronicsboy.GameEngine.Engine;
import io.github.electronicsboy.GameEngine.GameStartupStage;
import io.github.electronicsboy.GameEngine.SpriteSheet.BufferedImageLoader;
import io.github.electronicsboy.GameEngine.SpriteSheet.SpriteSheet;
import io.github.electronicsboy.GameEngine.Util.Util;
import io.github.electronicsboy.GameEngine.entity.Entity;
import io.github.electronicsboy.GameEngine.entity.EntityType;
import io.github.electronicsboy.GameEngine.entity.Handler;
import io.github.electronicsboy.GameEngine.entity.MovementType;
import io.github.electronicsboy.GameEngine.gui.Button;
import io.github.electronicsboy.GameEngine.gui.ButtonStyle;
import io.github.electronicsboy.GameEngine.gui.GUI;
import io.github.electronicsboy.GameEngine.gui.HUD;
import io.github.electronicsboy.GameEngine.test.Engine_Test_1.Entitys.EntityEnemyTest1;
import io.github.electronicsboy.GameEngine.test.Engine_Test_1.Entitys.EntityPlayerTest1;

public class Demo1 {
	public static class Main extends Engine {
		private static final long serialVersionUID = 9055315358841257598L;

		private GUI gui;
		private Button button;
		private Handler handler;
		
		private EntityPlayerTest1 player;
		private EntityPlayerTest1 player2;
		private EntityEnemyTest1 player3;
		private EntityEnemyTest1 player4;
		
		private int val1 = 0;
		private int val2 = 100;
		
		private HUD hud;
		
		private boolean useTextures;
		
		public static BufferedImage sprite_sheet;
		
		public static void main(String[] args) {
			new Main();
		}
		
		public Main() {
			super.run(this, "Demo Game");
		}

		@Override
		public void init() {
			handler = new Handler();
			gui = new GUI();
			button = new Button(210, 350, 200, 64);
			hud = new HUD(true);
			initEntity();
			super.stage = GameStartupStage.RUN;
			
			hud.addRenderValue("HELLO!", Integer.toString(val1));
			hud.addRenderValue("HELLO!!!!!!!", Integer.toString(val2));
		}

		@Override
		public void postRender(Graphics g) {
			Font buttonFont = new Font("arial", 1, 30);
			gui.postRender(g, Color.BLUE, Color.RED, Color.GREEN, button, ButtonStyle.FullRect, 270, 390, "Quit", Color.CYAN, buttonFont);
			if(Util.ButtonUtil.checkIfButtonIsClicked(button)) {
				System.exit(0);
			}
			hud.render(g);
			handler.postRender(g, useTextures);
		}

		@Override
		public void postTick() {
			handler.postTick();
			button.tick();
			hud.tick(this);
		}
		
		public void initEntity() {
			BufferedImageLoader imageLoader = new BufferedImageLoader();
			sprite_sheet = imageLoader.loadImage("/sprite_sheet.png");
			System.out.println("Sprite Sheet Loaded!");
			
			player = new EntityPlayerTest1(32, 32, 100, 100, Color.BLACK, MovementType.WASD, new SpriteSheet(sprite_sheet).grabImage(1, 1, 32, 32), handler);
			player2 = new EntityPlayerTest1(16, 16, 200, 200, Color.CYAN, MovementType.ArrowKeys, new SpriteSheet(sprite_sheet).grabImage(1, 2, 16, 16), handler);
			player3 = new EntityEnemyTest1(16, 16, 300, 300, Color.MAGENTA, MovementType.Bounce, new SpriteSheet(sprite_sheet).grabImage(1, 3, 16, 16));
			player4 = new EntityEnemyTest1(32, 32, 380, 380, Color.ORANGE, MovementType.Bounce, new SpriteSheet(sprite_sheet).grabImage(2, 1, 96, 96));
			
			handler.addObject(player, player2, player3, player4);
			
			useTextures = true;
		}
		
		@Override
		public void postHUDTick() {
			val1++;
			val2++;
			
			hud.updateValue(1, Integer.toString(val1));
			hud.updateValue(2, Integer.toString(val2));
		}
	}
	public static class Entitys {
		public static class EntityPlayerTest1 extends Entity {
			private Handler handler;
			private int width;
			private int height;
			
			public EntityPlayerTest1(int width, int height, int x, int y, Color col, MovementType movementType, BufferedImage texture, Handler handler) {
				super(width, height, x, y, EntityType.Player, col, movementType, texture);
				super.setupMove();
				super.velX = 5;
				super.velY = 5;
				this.handler = handler;
				this.width = width;
				this.height = height;
			}

			@Override
			public void tick() {
				super.move();
				collision();
			}

			private void collision() {
				for(int i = 0; i < handler.object.size(); i++) {
					Entity tempObject = handler.object.get(i);
					
					if(tempObject.getType() == EntityType.Enemy) {
						if(getBounds().intersects(tempObject.getBounds())) {
							System.out.println("Enemy incontact!");
						}
					}
				}
			}
			
			@Override
			public void postRender(Graphics g, boolean useTexture) {
				if(useTexture) {
					g.drawImage(texture, (int)x, (int)y, null);
				}else {
					g.setColor(col);
					g.fillRect((int)x, (int)y, width, height);
				}
			}

			@Override
			public Rectangle getBounds() {
				return new Rectangle((int)x, (int)y, width, height);
			}
		}
		public static class EntityEnemyTest1 extends Entity {
			
			private int width;
			private int height;
			
			public EntityEnemyTest1(int width, int height, int x, int y, Color col, MovementType movementType, BufferedImage texture) {
				super(width, height, x, y, EntityType.Enemy, col, movementType, texture);
				super.setupMove();
				this.width = width;
				this.height = height;
				super.velX = 5;
				super.velY = 5;
			}

			@Override
			public void tick() {
				super.move();
			}
			
			@Override
			public void postRender(Graphics g, boolean useTexture) {
				if(useTexture) {
					g.drawImage(texture, (int)x, (int)y, null);
				}else {
					g.setColor(col);
					g.fillRect((int)x, (int)y, width, height);
				}
			}

			@Override
			public Rectangle getBounds() {
				return new Rectangle((int)x, (int)y, width, height);
			}
		}
	}
}
