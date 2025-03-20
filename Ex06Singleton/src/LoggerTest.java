import java.util.ArrayList;
import java.util.List;

// Singleton Logger
class Logger {
    private static volatile Logger instance;
    private final List<String> logs;

    // Construtor privado para evitar múltiplas instâncias
    private Logger() {
        logs = new ArrayList<>();
    }

    // Implementação de Singleton usando Double-Checked Locking
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Método para registrar logs
    public void log(String message) {
        logs.add(message);
        System.out.println("Log registrado: " + message);
    }

    // Método para listar todos os logs
    public List<String> getLogs() {
        return logs;
    }
}

// Classe principal para testar o Logger
public class LoggerTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("Iniciando aplicação...");
        logger1.log("Conectando ao banco de dados...");

        Logger logger2 = Logger.getInstance();
        logger2.log("Erro: Falha na conexão!");

        // Verificando que todos os logs estão armazenados na mesma instância
        System.out.println("\n📜 Todos os Logs Registrados:");
        for (String log : logger2.getLogs()) {
            System.out.println(log);
        }
    }
}
