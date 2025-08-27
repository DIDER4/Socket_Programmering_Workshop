package opgave03.tcpEksempel;

import opgave03.tcpEksempel.threads.RecievingThread;
import opgave03.tcpEksempel.threads.TCPSendThread;

import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {

		Socket clientSocket = new Socket("localhost", 6790);

		(new TCPSendThread(clientSocket)).start();
		(new RecievingThread(clientSocket)).start();
	}
}