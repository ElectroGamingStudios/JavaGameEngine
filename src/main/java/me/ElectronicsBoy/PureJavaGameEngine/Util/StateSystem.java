package me.ElectronicsBoy.PureJavaGameEngine.Util;

import me.ElectronicsBoy.PureJavaGameEngine.Engine;

public class StateSystem {
	private String state = "INIT";
	private String lastState = null;
	
	private Engine engine;
	
	public StateSystem(Engine engine) {
		this.engine = engine;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String newState) {
		lastState = state;
		state = newState;
		engine.onStateChange();
	}

	public String getLastState() {
		return lastState;
	}
}
