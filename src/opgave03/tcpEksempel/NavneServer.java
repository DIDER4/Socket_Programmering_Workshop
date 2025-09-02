package opgave03.tcpEksempel;

import opgave03.tcpEksempel.register.NavneRegister;
import opgave03.tcpEksempel.threads.NavneServerThread;

import java.net.ServerSocket;
import java.net.Socket;

public class NavneServer {
    public static void main(String[] args) throws Exception {
        // Opretter et navneregister og tilføjer Simon
        NavneRegister navneRegister = new NavneRegister();
        navneRegister.registerClient("Simon", "localhost");
        navneRegister.registerClient("Loke", "10.10.69.69");
        navneRegister.registerClient("Henrik","");
        navneRegister.registerClient("Govher","");

        // Starter serveren og acceptere flere brugere
        ServerSocket serverSocket = new ServerSocket(6790);
        while (true) {
            // Venter på en klient
            Socket clientSocket = serverSocket.accept();
            System.out.println("Ny klient forbundet: " + clientSocket.getInetAddress());
            System.out.println("Udskriver navneregister til: " + clientSocket.getInetAddress());
            // Starter en tråd til at håndtere denne klient
            new NavneServerThread(clientSocket, navneRegister).start();
        }
    }
}
