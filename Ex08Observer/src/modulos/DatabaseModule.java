package modules;

import core.Observer;

public class DatabaseModule implements Observer {
    @Override
    public void update(String data) {
        System.out.println("[DatabaseModule] Salvando mensagem no banco: " + data);
    }
}
