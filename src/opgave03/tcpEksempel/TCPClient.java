package opgave03.tcpEksempel;

import java.io.*;
import java.net.Socket;

public class TCPClient {
	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket("localhost", 6790);
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

		while (true) {
			System.out.print("SKRIV EN COMMAND TIL SERVEREN (TOMT FOR AT SLUTTE): ");
			String command = userInput.readLine();
			if (command == null || command.isEmpty()) break;
			outToServer.println(command);
			String response = inFromServer.readLine();
			System.out.println("FROM SERVER: " + response);
		}
		clientSocket.close();
	}
}