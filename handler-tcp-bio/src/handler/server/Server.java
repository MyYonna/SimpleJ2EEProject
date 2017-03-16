package handler.server;

public class Server {

	public static void main(String[] args){
		SimpleServerSocket server = new SimpleServerSocket();
		server.createServer();
	}
}
