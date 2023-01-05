package me.ElectronicsBoy.GameEngine.networking;

import java.util.Arrays;

public class Client extends ClientConnection implements Runnable {
		
		private Thread thread;
		
		public Client(String ip, int port, ClientHandler handler) {
			super(port, ip, handler);
		}
		
		public void connectToServer() {
			thread = null;
			thread = new Thread(this, "Client Thread");
			thread.start();
		}

		public String getLastMessage() {
			return super.getLastMessage();
		}
		
		public void sendMessage(String data) {
			super.sendData(data);
		}
		
		public void sendMessage(String[] data) {
			super.sendData(Arrays.toString(data));
		}
		
		@Override
		public void run() {
			super.connect();
		}
	}