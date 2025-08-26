package opgave03.register;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class navneRegister {
    private Map<String, String> serverAdresser = new HashMap<>();

    public synchronized void registerClient(String nickname, String netInfo) {
        serverAdresser.put(nickname, netInfo);
    }

    public synchronized void removeClient(String nickname) {
        serverAdresser.remove(nickname);
    }

    public synchronized Collection<Map.Entry<String, String>> getClients() {
        return serverAdresser.entrySet();
    }
}
