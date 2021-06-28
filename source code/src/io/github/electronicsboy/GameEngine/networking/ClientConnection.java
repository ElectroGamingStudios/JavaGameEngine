package io.github.electronicsboy.GameEngine.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class ClientConnection {
		protected int port;
		protected String ip;
		
		protected Socket socket;
		
		protected InputStreamReader in;
		protected BufferedReader bf;
		
		protected String lastMessage;
		
		protected ClientHandler handler;
		
		public ClientConnection(int port, String ip, ClientHandler handler) {
			this.ip = ip;
			this.port = port;
			this.handler = handler;
		}
		
		protected void connect() {
			try {
				socket = new Socket(this.ip, this.port);
				while(true) {
					in = new InputStreamReader(socket.getInputStream());
					bf = new BufferedReader(in);
					Scanner s = new Scanner(bf);
					
					while(s.hasNext()) {
						lastMessage = bf.readLine();
						handler.messageReceivedClient(bf.readLine());
					}
					
					s.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		protected void sendData(String data) {
			if(socket != null) {
				PrintWriter printWriter = null;
				try {
					printWriter = new PrintWriter(socket.getOutputStream());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(socket.isConnected()) {
					try {
						printWriter.println(data);
						printWriter.flush();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		
		protected String getLastMessage() {
			return lastMessage;
		}
	}