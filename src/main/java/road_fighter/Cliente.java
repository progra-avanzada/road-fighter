package road_fighter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class Cliente {
	private RoadFighterGame game;
	private Socket clientSocket;
	private BufferedReader in;
    private PrintWriter out; 

	public Cliente(String url, RoadFighterGame game) {
		this.game = game;
		
		String[] urlParts = url.split(":");
		url = urlParts[0];
		
		try {
			clientSocket = new Socket(url, 8888);
			out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            game.onConnect();
			
			Thread loop = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    	String[] startGameMessage = in.readLine().split(" ");
                    	game.onStartGame(Integer.parseInt(startGameMessage[0]), Long.parseLong(startGameMessage[1]));
        			
                        String msg = in.readLine();
                        while(msg != null){
                        	String[] messageParts = msg.split(" ");                        	
                        	game.onReceiveInput(Boolean.parseBoolean(messageParts[0]), Boolean.parseBoolean(messageParts[1]),
                					Boolean.parseBoolean(messageParts[2]));
                        	msg = in.readLine();
                        }
                        out.close();
                        clientSocket.close();
                        game.onDisconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
			loop.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendInput(boolean accelerate, boolean left, boolean right) {
		out.println(accelerate + " " + left + " " + right);
		out.flush();
	}
}