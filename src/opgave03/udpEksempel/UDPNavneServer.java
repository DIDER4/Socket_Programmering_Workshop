package opgave03.udpEksempel;

import opgave03.udpEksempel.register.NavneRegister;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPNavneServer {

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
        NavneRegister navneRegister = new NavneRegister();
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();;
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();


            String brugersSvar;
            if (sentence.equals("REGISTER")) {
                brugersSvar = navneRegister.getClients().toString();
            } else {
                brugersSvar = "UKENDT SVAR, FØLGENDE COMMANDS TILGÆNGELIGE: \n'REGISTER'\n----------------";
            }
			sendData = brugersSvar.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}
