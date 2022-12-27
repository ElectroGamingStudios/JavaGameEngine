package io.github.electronicsboy.GameEngine.Util;

public class StateSystem {
	private String state = "INIT";
	
	public String getState() {
		return state;
	}
	public void setState(String newState) {
		state = newState;
	}
}
