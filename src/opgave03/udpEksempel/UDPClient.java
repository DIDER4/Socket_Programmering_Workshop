package opgave03.udpEksempel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient {
	public static void main(String args[]) throws Exception {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();

        System.out.println("SKRIV EN COMMAND TIL SERVEREN (REGISTER FOR NAVNEREGISTER): ");
        String brugerInput = userInput.readLine();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = brugerInput.getBytes();
		byte[] receiveData = new byte[1024];

		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);

		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		clientSocket.receive(receivePacket);

        String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
        System.out.println("FROM SERVER: " + serverResponse);

        System.out.print("INDTAST DEN ØNSKEDE IPADRESSE DU VIL FORBINDE TIL (ELLER SLUT MED ENTER): ");
        String newIP = userInput.readLine();
        if (!newIP.isEmpty()) {
            IPAddress = InetAddress.getByName(newIP);
            System.out.print("INDTAST COMMAND FOR NY SERVER FORBINDELSE: ");
            brugerInput = userInput.readLine();
            sendData = brugerInput.getBytes();

            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("FRA NY SERVER: " + serverResponse);
        }
        clientSocket.close();
    }
}
