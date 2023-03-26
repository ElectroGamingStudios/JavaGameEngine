package me.ElectronicsBoy.PureJavaGameEngine.gui;

import java.awt.Font;

public enum ButtonStyle {
	FullRect,
	Rect;
	
	protected Font f;
	
	public static ButtonStyle createStyle(Font f, ButtonStyle style) {
		ButtonStyle button = style;
		button.f = f;
		return button;
	}
	
	public Font getFont() {
		return f;
	}
}
