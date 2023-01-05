package me.ElectronicsBoy.GameEngine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import me.ElectronicsBoy.GameEngine.Util.StateSystem;
import me.ElectronicsBoy.GameEngine.Util.Util;
import me.ElectronicsBoy.GameEngine.crash.CrashReport;
import me.ElectronicsBoy.GameEngine.gui.GUI;
import me.ElectronicsBoy.GameEngine.gui.GUIWindow;
import me.ElectronicsBoy.GameEngine.gui.HUD;
import me.ElectronicsBoy.GameEngine.gui.HUDPostTick;
import me.ElectronicsBoy.GameEngine.input.Keyboard;
import me.ElectronicsBoy.GameEngine.input.Mouse;
import me.ElectronicsBoy.GameEngine.networking.ClientHandler;
import me.ElectronicsBoy.GameEngine.networking.ServerHandler;

public abstract class Engine extends Canvas implements Target, Runnable, HUDPostTick, ClientHandler, ServerHandler {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7481457039403484363L;
	
	private static Keyboard keyboard;
	private static Mouse mouse;
	
	private Thread thread;
	
	private Target target;
	
	private Window window;
	
	private String title;
	
	private boolean running = false;
	
	protected GameStartupStage stage = GameStartupStage.INIT;
	
	protected int frameLimit;
	
	private List<GUIWindow> guiWindows = new ArrayList<GUIWindow>();
	
	public StateSystem sys = new StateSystem(this);
	
	public void tick() {
		target.postTick();
		for(GUIWindow window : guiWindows) if(window != null)
			if(window.getRunnableState().equals(sys.getState()))
				window.tick();
	}
	
	@Override
	public void run() {
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		long now = System.nanoTime();
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				if(stage == GameStartupStage.RUN && frames != frameLimit)
					tick();
				delta--;
			}
			if(running) {
				if(stage == GameStartupStage.RUN)
					render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				window.getFrame().setTitle(title + " | FPS: " + frames + " | TPS: " + frameLimit);
				frames = 0;
			}
		}
		System.exit(0);
	}
	
	protected void run(Engine target, String title, int frameLimit) {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				new CrashReport().createCrashReport(e, t);
				System.exit(-1);
			}
		});
		this.frameLimit = frameLimit;
		keyboard = new Keyboard(target);
		mouse = new Mouse();
		this.addKeyListener(keyboard);
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		window = new Window(640, 480, title, target);
		this.title = title;
		running = true;
		this.target = target;
		thread = new Thread(this, "Engine Thread");
		thread.start();
		target.init();
	}
	
	private BufferStrategy getOrCreateBufferStrategy() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return null;
		}
		return bs;
	}
	
	protected void render() {
		BufferStrategy bs = getOrCreateBufferStrategy();
		if(bs == null)
			return;

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Util.WIDTH, Util.HEIGHT);
		
		target.postRender(g);
		for(GUIWindow window : guiWindows) if(window != null)
			if(window.getRunnableState().equals(sys.getState()))
				window.render(g);
		
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	protected void addWindow(GUIWindow window) {
		guiWindows.add(window);
		window.init();
	}
	
	protected void initWindows(HUD hud, GUI gui) {
		for(GUIWindow window : guiWindows) if(window != null)
				window.init(hud, gui);
	}
	
	public static Keyboard getKeyboard() {
		return keyboard;
	}
	
	public static Mouse getMouse() {
		return mouse;
	}	
}
