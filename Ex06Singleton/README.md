# Singleton Logger - Documentação

## Introdução
O padrão **Singleton** é utilizado quando precisamos garantir que uma classe tenha apenas uma instância e forneça um ponto global de acesso a ela. Neste exercício, criamos um **Logger** para armazenar mensagens de log globalmente dentro da aplicação.

## Objetivo
Garantir que apenas uma instância do Logger seja usada para registrar e listar logs, independentemente de quantas vezes ele seja acessado dentro da aplicação.

## Implementação do Singleton Logger em Java

```java
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
        System.out.println("\n\ud83d\udcdd Todos os Logs Registrados:");
        for (String log : logger2.getLogs()) {
            System.out.println(log);
        }
    }
}
```

## Explicação

### Garantia de uma única instância:
- A classe **Logger** tem um **construtor privado** para evitar que novas instâncias sejam criadas.
- O método **getInstance()** é a única forma de obter a instância da classe.

### Thread Safety com Double-Checked Locking:
- O uso da palavra-chave `volatile` evita problemas de cache em ambientes concorrentes.
- O bloqueio `synchronized` é aplicado apenas na primeira inicialização, garantindo melhor desempenho.

### Armazenamento de logs em memória:
- Os logs são armazenados em uma `List<String>` dentro do Singleton, permitindo que qualquer parte da aplicação acesse os mesmos registros.

## Teste do Singleton
Se executarmos `Logger.getInstance()` em diferentes partes do código, veremos que todos os logs registrados estão na **mesma instância**, garantindo que o Singleton esteja funcionando corretamente.

## Possível Expansão
- **Salvar logs em arquivo**: Podemos adicionar um método que grava os logs em um arquivo.
- **Diferentes níveis de log**: Podemos categorizar logs como INFO, WARNING, ERROR.
- **Formatos de saída personalizados**: Permitir que os logs sejam formatados em JSON ou XML.

## Conclusão
O Singleton **Logger** garante uma única instância global para armazenar e acessar logs, melhorando a organização e prevenindo problemas de concorrência. É uma solução eficiente para aplicações que precisam de um sistema centralizado de logging.

