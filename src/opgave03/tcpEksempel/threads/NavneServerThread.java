package opgave03.tcpEksempel.threads;

import opgave03.tcpEksempel.register.NavneRegister;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;

public class NavneServerThread extends Thread {

    Socket connectionSocket;
    String nickname;
    public NavneRegister navn;

    public NavneServerThread(Socket clientSocket, NavneRegister navn) {
        this.connectionSocket = clientSocket;
        this.navn = navn;
    }

    public void run() {
        try {
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            for (Map.Entry<String, String> entry : navn.getClients()) {
                outToClient.writeBytes(entry.getKey() + " IP: " + entry.getValue() + "\n");
            }
            outToClient.writeBytes("-------------------------\n");
            connectionSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
