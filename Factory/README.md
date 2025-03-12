# Factory Pattern

## Introdução
O Factory Pattern é um padrão de projeto criacional que centraliza a criação de objetos, evitando a necessidade de utilizar `new` diretamente no código cliente. Ele permite maior flexibilidade e encapsula a lógica de criação, facilitando manutenção e extensibilidade.

## Objetivo
- Evitar a repetição de `new` espalhado pelo código.
- Centralizar a lógica de criação de objetos.
- Permitir que o sistema instancie objetos dinamicamente sem modificar o código cliente.

## Versões Implementadas

### Versão 1.0 - Implementação Simples
Nesta versão, utilizamos um `switch-case` para determinar qual tipo de notificação criar.

#### Problemas:
- Cada vez que um novo tipo de notificação é adicionado, a Factory precisa ser modificada.
- Usa `String` para definir o tipo, tornando propenso a erros de digitação e dificultando a manutenção.

### Versão 1.2 - Melhorando a Implementação com ENUM
Nesta versão, substituímos a string por um `enum NotificationType`, evitando erros e tornando o código mais seguro e legível.

#### Melhorias:
- Uso de `enum` para evitar strings despadronizadas.
- Maior legibilidade e segurança ao definir tipos de notificação.

### Versão 2.0 - Substituindo Switch-Case por Map
Nesta versão, eliminamos o `switch-case` e utilizamos um `Map<NotificationType, Supplier<Notification>>`, tornando o código mais desacoplado e flexível.

#### Melhorias:
- Adicionar novos tipos de notificação requer apenas inserir um novo item no `Map`, sem modificar a Factory.
- Código mais limpo e flexível.
- Evita a necessidade de modificar a estrutura do método `createNotification` sempre que um novo tipo for adicionado.

## Exemplo Final de Implementação
```java
import java.util.Map;
import java.util.function.Supplier;

// Enum para definir os tipos de notificacao
enum NotificationType {
    EMAIL, SMS
}

// Interface para notificacoes
interface Notification {
    void send(String message);
}

// Implementacoes concretas
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email Notification: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
    }
}

// Factory usando Map
class NotificationFactory {
    private static final Map<NotificationType, Supplier<Notification>> NOTIFICATION_MAP = Map.of(
        NotificationType.EMAIL, EmailNotification::new,
        NotificationType.SMS, SMSNotification::new
    );
    
    public static Notification createNotification(NotificationType type) {
        Supplier<Notification> notification = NOTIFICATION_MAP.get(type);
        if (notification != null) {
            return notification.get();
        }
        throw new IllegalArgumentException("Tipo de notificação inválido");
    }
}

// Classe Principal (Testes)
public class Main {
    public static void main(String[] args) {
        Notification email = NotificationFactory.createNotification(NotificationType.EMAIL);
        email.send("Olá via Email!");

        Notification sms = NotificationFactory.createNotification(NotificationType.SMS);
        sms.send("Olá via SMS!");
    }
}
```

## Conclusão
A evolução do código foi de uma implementação simples e mais acoplada para uma solução flexível e escalável usando `Map`.

Com a versão final, podemos adicionar novos tipos de notificação facilmente, sem modificar o código da Factory, seguindo os princípios do SOLID e Clean Code.

