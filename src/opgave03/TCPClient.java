package opgave03;

import opgave03.Threads.RecievingThread;
import opgave03.Threads.TCPSendThread;

import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {

		Socket clientSocket = new Socket("localhost", 6789);

		(new TCPSendThread(clientSocket)).start();
		(new RecievingThread(clientSocket)).start();
	}
}