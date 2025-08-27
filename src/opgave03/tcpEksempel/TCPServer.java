package opgave03.tcpEksempel;

import opgave02.RecievingThread;
import opgave03.tcpEksempel.register.NavneRegister;
import opgave03.tcpEksempel.threads.TCPSendThread;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        NavneRegister navneRegister = new NavneRegister();

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            new RecievingThread(connectionSocket, navneRegister).start();
        }
    }
}
