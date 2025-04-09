package modules;

import core.Observer;

public class ChatModule implements Observer {
    @Override
    public void update(String data) {
        System.out.println("[ChatModule] Nova mensagem: " + data);
    }
}
