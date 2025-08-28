// src/opgave03/udpEksempel/UDPClient.java
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
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] receiveData = new byte[1024];

        while (true) {
            System.out.print("SKRIV EN COMMAND TIL SERVEREN (TOMT FOR AT SLUTTE): ");
            String brugerInput = userInput.readLine();
            if (brugerInput == null || brugerInput.isEmpty()) break;

            byte[] sendData = brugerInput.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("FROM SERVER: " + serverResponse);
        }
        clientSocket.close();
    }
}