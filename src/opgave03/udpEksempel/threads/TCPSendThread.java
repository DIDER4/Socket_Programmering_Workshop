package opgave03.udpEksempel.threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPSendThread extends Thread {

    private Socket connectionSocket;
    private String message;

    public TCPSendThread(Socket clientSocket) {
        this.connectionSocket = clientSocket;
    }

    public void run(){
        try{
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToOtherUser = new DataOutputStream(connectionSocket.getOutputStream());
            System.out.println("Chat løs: ");
            while(true){
                message = inFromUser.readLine();
                outToOtherUser.writeBytes(message + '\n');
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
