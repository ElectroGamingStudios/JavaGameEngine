package me.ElectronicsBoy.GameEngine.networking;

import java.util.Arrays;

public class Server extends ServerConnection implements Runnable {
		
		private Thread thread;
		
		public Server(int port, ServerHandler handler) {
			super(port, handler);
		}

		public void startServer() {
			thread = null;
			thread = new Thread(this, "Server Thread");
			thread.start();
		}
		
		@Override
		public void run() {
			super.startServer();
		}
		
		public void sendMessage(String data) {
			super.sendData(data);
		}
		
		public void sendMessage(String[] data) {
			super.sendData(Arrays.toString(data));
		}
		
		public String getLastMessage() {
			return super.getLastMessage();
		}
	}