package me.ElectronicsBoy.GameEngine.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public abstract class ServerConnection {
		
		private ServerSocket serverSocket;
		private Socket socket;
		
		private PrintWriter printWriter;
		
		private String oldConnectedIP = null;
		
		protected InputStreamReader in;
		protected BufferedReader bf;
		
		private int port;
		
		protected String lastMessage;
		
		protected ServerHandler handler;
		
		public ServerConnection(int port, ServerHandler handler) {
			this.port = port;
			this.handler = handler;
		}
		
		protected void startServer() {
			try {
				serverSocket = null;
				serverSocket = new ServerSocket(this.port);
				socket = null;
				
				socket = serverSocket.accept();
				
				while(true) {					
					if(socket.isConnected()) {
						if(!socket.getInetAddress().toString().equals(oldConnectedIP)) {
							oldConnectedIP = socket.getInetAddress().toString();
							
							printWriter = new PrintWriter(socket.getOutputStream());
							
							printWriter.println(socket.getInetAddress().toString() + " has connected!");
							printWriter.flush();
						}else {
							printWriter = new PrintWriter(socket.getOutputStream());
							printWriter.flush();
							continue;
						}
					}else {
						serverSocket.close();
						serverSocket = null;
						serverSocket = new ServerSocket(this.port);
						socket = null;
						socket = serverSocket.accept();
						continue;
					}
					
					in = new InputStreamReader(socket.getInputStream());
					bf = new BufferedReader(in);
					Scanner s = new Scanner(bf);
					
					while(s.hasNext()) {
						lastMessage = bf.readLine();
						handler.messageReceivedServer(bf.readLine());
					}
					s.close();
				}
			} catch (IOException e) {e.printStackTrace();e.printStackTrace(printWriter);}
		}
		
		protected void sendData(String data) {
			if(socket != null) {
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