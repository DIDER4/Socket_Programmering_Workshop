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
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToOtherUser = new DataOutputStream(connectionSocket.getOutputStream());

            //Nedenstående udskriver hele navneregisteret og beder bruger inputte det ønskede brugernavn
            System.out.println(getEntireNavneRegister());
            System.out.println("Indtast det ønskede brugernavn: ");
            nickname = inFromUser.readLine();

            //Nedenstående opretter en socket ud fra det modtagede nickname
            Socket clientSocket = new Socket(getIpAdresse(nickname), 6789);
            //Starter en ny thread der tilslutter den oprettede socket
            (new TCPSendThread(clientSocket)).start();
            (new RecievingThread(clientSocket)).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized String getIpAdresse(String nickname) {
        return navn.getClients().stream()
                .filter(entry -> entry.getKey().equals(nickname))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public synchronized Collection getEntireNavneRegister(){
        return navn.getClients();
    }
}
