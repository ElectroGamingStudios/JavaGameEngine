package me.ElectronicsBoy.GameEngine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 7933213869298553017L;
	
	private JFrame frame;
	
	public Window(int width, int height, String title, Engine target) {
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(target);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

}
