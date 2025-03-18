// Classe de configuração do banco de dados com Singleton
class DatabaseConfig {
    private static volatile DatabaseConfig instance;
    private final String host;
    private final int port;

    // Construtor privado para evitar instanciação direta
    private DatabaseConfig() {
        this.host = "localhost";
        this.port = 5432;
        System.out.println("Nova instância de DatabaseConfig criada!");
    }

    // Método estático para obter a instância única (Thread-safe com Double-Checked Locking)
    public static DatabaseConfig getInstance() {
        if (instance == null) {
            synchronized (DatabaseConfig.class) {
                if (instance == null) {
                    instance = new DatabaseConfig();
                }
            }
        }
        return instance;
    }

    // Métodos de acesso às configurações
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}

// Classe principal para testar o Singleton
public class Main {
    public static void main(String[] args) {
        DatabaseConfig config1 = DatabaseConfig.getInstance();
        DatabaseConfig config2 = DatabaseConfig.getInstance();

        // Verificar se as instâncias são a mesma
        System.out.println("Ambas as instâncias são iguais? " + (config1 == config2));

        // Exibir as configurações
        System.out.println("Host: " + config1.getHost());
        System.out.println("Port: " + config1.getPort());
    }
}
