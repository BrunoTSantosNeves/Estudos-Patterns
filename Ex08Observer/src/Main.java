import application.CacheService;
import application.EventManager;
import modules.ChatModule;
import modules.DatabaseModule;

public class Main {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        ChatModule chat = new ChatModule();
        DatabaseModule db = new DatabaseModule();

        manager.subscribe(chat);
        manager.subscribe(db);

        // Simula envio de mensagem
        String message = "Olá, mundo!";
        manager.notifyObservers(message);

        // Simula uso do cache
        CacheService cache = CacheService.getInstance();
        cache.set("user1", "João");
        cache.set("user2", "Maria");

        System.out.println("Usuário em cache: " + cache.get("user1"));

        System.out.println("Logs do Cache:");
        cache.getLogs().forEach(System.out::println);
    }
}
