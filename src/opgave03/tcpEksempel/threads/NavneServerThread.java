// src/opgave03/tcpEksempel/threads/NavneServerThread.java
package opgave03.tcpEksempel.threads;

import opgave03.tcpEksempel.register.NavneRegister;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class NavneServerThread extends Thread {

    Socket connectionSocket;
    public NavneRegister navn;

    public NavneServerThread(Socket clientSocket, NavneRegister navn) {
        this.connectionSocket = clientSocket;
        this.navn = navn;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String sentence;
            while ((sentence = inFromClient.readLine()) != null && !sentence.isEmpty()) {
                String response;
                if (sentence.equals("REGISTER")) {
                    response = navn.getClients().toString();
                } else if (sentence.startsWith("REGISTERCLIENT")) {
                    String args = sentence.substring("REGISTERCLIENT".length()).trim();
                    String[] kommando = args.split(",");
                    if (kommando.length == 2) {
                        String name = kommando[0].trim();
                        String ip = kommando[1].trim();
                        navn.registerClient(name, ip);
                        response = "Client registered: " + name + " (" + ip + ")";
                    } else {
                        response = "Invalid REGISTERCLIENT format. Use: REGISTERCLIENT Name,IP";
                    }
                } else {
                    response = "UKENDT SVAR, FØLGENDE COMMANDS TILGÆNGELIGE: \n'REGISTER'\n'REGISTERCLIENT Name,IP'\n----------------";
                }
                outToClient.writeBytes(response + "\n");
            }
            outToClient.writeBytes("-------------------------\n");
            connectionSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}