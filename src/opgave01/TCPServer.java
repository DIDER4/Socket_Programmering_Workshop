package opgave01;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {


            String clientSentence;
            String capitalizedSentence;
            ServerSocket welcomSocket = new ServerSocket(6789);
            System.out.println("Serveren venter på klient");
            Socket connectionSocket = welcomSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            System.out.println("Klient forbundet til Server");

            while(true){
            clientSentence = inFromClient.readLine();
            System.out.println(clientSentence);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indtast svar besked");
            capitalizedSentence = consoleReader.readLine().toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
