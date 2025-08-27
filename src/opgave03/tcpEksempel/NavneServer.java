package opgave03.tcpEksempel;

import opgave03.tcpEksempel.register.NavneRegister;
import opgave03.tcpEksempel.threads.NavneServerThread;

import java.net.Socket;

public class NavneServer {
    public static void main(String[] args) throws Exception {

        //Opretter et navneregister og tilføjer Simon
        NavneRegister navneRegister = new NavneRegister();
        navneRegister.registerClient("Simon", "localhost");
        navneRegister.registerClient("Loke", "");
        navneRegister.registerClient("Henrik","");
        navneRegister.registerClient("Govher","");

        //Nedenstående tilslutter navneserveren og starter tråden.
        Socket navneServerSocket = new Socket("localhost", 6790);
        (new NavneServerThread(navneServerSocket, navneRegister)).start();
    }
}
