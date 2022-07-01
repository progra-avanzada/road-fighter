package road_fighter_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
	public static void main(String[] args) throws InterruptedException, IOException {
		ServerSocket serverSocket;

		Socket p1ClientSocket = null;
		BufferedReader p1In;
		PrintWriter p1Out;

		Socket p2ClientSocket = null;
		BufferedReader p2In;
		PrintWriter p2Out;

		try {
			serverSocket = new ServerSocket(8888);

			while (p1ClientSocket == null || p1ClientSocket.isClosed() || p2ClientSocket == null
					|| p2ClientSocket.isClosed()) {
				Socket newPlayerSocket = serverSocket.accept();
				if (p1ClientSocket == null || p1ClientSocket.isClosed()) {
					p1ClientSocket = newPlayerSocket;
					System.out.println("Jugador 1 conectado");
				} else if (p2ClientSocket == null || p2ClientSocket.isClosed()) {
					p2ClientSocket = newPlayerSocket;
					System.out.println("Jugador 2 conectado");
				}
			}

			System.out.println("Partida iniciada");
			p1Out = new PrintWriter(p1ClientSocket.getOutputStream());
			p1In = new BufferedReader(new InputStreamReader(p1ClientSocket.getInputStream()));
			p2Out = new PrintWriter(p2ClientSocket.getOutputStream());
			p2In = new BufferedReader(new InputStreamReader(p2ClientSocket.getInputStream()));

			long seed = (long) (Math.random() * 1000);
			p1Out.println("1 " + seed);
			p1Out.flush();
			p2Out.println("2 " + seed);
			p2Out.flush();

			Thread player1Thread = new Thread(new Runnable() {
				public void run() {
					try {
						String msg = p1In.readLine();
						while (msg != null) {
							p2Out.println(msg);
							p2Out.flush();
							msg = p1In.readLine();
						}

						endGame(p1Out, p2Out, serverSocket);
					} catch (IOException e) {
						endGame(p1Out, p2Out, serverSocket);
					}
				}
			});
			player1Thread.start();

			Thread player2Thread = new Thread(new Runnable() {
				public void run() {
					try {
						String msg = p2In.readLine();
						while (msg != null) {
							p1Out.println(msg);
							p1Out.flush();
							msg = p2In.readLine();
						}

						endGame(p1Out, p2Out, serverSocket);
					} catch (IOException e) {
						endGame(p1Out, p2Out, serverSocket);
					}
				}
			});
			player2Thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void endGame(PrintWriter p1, PrintWriter p2, ServerSocket s) {
		try {
			p1.close();
			p2.close();
			s.close();
			System.out.println("Partida terminada");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}