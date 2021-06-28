package io.github.electronicsboy.GameEngine.networking;

public class NetworkingManager {
	
	private Client client;
	
	private Server server;
	
	private String ip;
	private int port;
	
	private ServerHandler serverHandler;
	private ClientHandler clientHandler;
	
	public enum TYPE {
		CLIENT,
		SERVER;
	}
	
	public NetworkingManager(String ip, int port, ServerHandler serverHandler, ClientHandler clientHandler) {
		this.ip = ip;
		this.port = port;
		
		this.clientHandler = clientHandler;
		this.serverHandler = serverHandler;
	}
	
	public NetworkingManager(int port, ServerHandler serverHandler, ClientHandler clientHandler) {
		this.ip = "localhost";
		this.port = port;
		
		this.clientHandler = clientHandler;
		this.serverHandler = serverHandler;
	}
	
	public void start(TYPE type) {
		if(type == TYPE.CLIENT)
			initClient();
		else if(type == TYPE.SERVER)
			initServer();
		else
			throw new IllegalArgumentException("Type should be SERVER to start the server ot CLIENT to start the client!");
	}
	
	private void initClient() {
		client = null;
		client = new Client(ip, port, clientHandler);
		client.connectToServer();
	}
	
	private void initServer() {
		server = null;
		server = new Server(port, serverHandler);
		server.startServer();
	}
	
	public void sendServerMessage(String message) {
		this.server.sendMessage(message);
	}
	public void sendServerMessage(String[] message) {
		this.server.sendMessage(message);
	}
	
	public Client getClient() {
		return client;
	}
	public Server getServer() {
		return server;
	}
}
