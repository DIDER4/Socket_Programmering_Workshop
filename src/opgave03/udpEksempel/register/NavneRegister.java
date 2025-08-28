package opgave03.udpEksempel.register;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NavneRegister {

    private final Map<String, String> serverAdresser = new HashMap<>();

    public NavneRegister() {
        serverAdresser.put("Simon", "localhost");
        serverAdresser.put("Loke", "192.168.1.2");
        serverAdresser.put("Henrik", "192.168.1.3");
        serverAdresser.put("Govher", "192.168.1.4");
    }

    public Map<String, String> getClients() {
        return serverAdresser;
    }

    public synchronized void registerClient(String nickname, String netInfo) {
        serverAdresser.put(nickname, netInfo);
    }
}

