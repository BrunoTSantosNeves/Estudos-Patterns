// Factory Pattern
// Version: 1.0

/*class EmailNotification {
    public void send(String message) {
        System.out.println("Email Notification: " + message);
    }
}

class SMSNotification {
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
    }
}

// Factory simples para criar notificações  
// (Diretamente acoplada as classes concretas)

class NotificationFactory {
    public static Object createNotification(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EmailNotification();
        } else if (type.equalsIgnoreCase("sms")) {
            return new SMSNotification();
        } else {
           throw new IllegalArgumentException("Invalid notification type");
        }
    }
}

//Teste
public class Main {
    public static void main(String[] args) {
        EmailNotification email = (EmailNotification) NotificationFactory.createNotification("email");
        email.send("Hello, this is an email notification");

        SMSNotification sms = (SMSNotification) NotificationFactory.createNotification("sms");
        sms.send("Olá, SMS!");
    }
}
 
*/


// Versão: 1.1
// Factory Pattern

/*
// Interface para notificações (Desacopla as implementações concretas)
 interface Notification {
    void send(String message);
}

// Implementações concretas

class EamilNotification implements Notification {
    @Override
    public void send (String message ) {
        System.out.println("Email Notification: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void send (String message ) {
        System.out.println("SMS Notification: " + message);
    }
}

// Factory melhorada: retornando notification(interface)

class NotificationFactory {
    public static Notification createNotification(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EamilNotification();
        } else if (type.equalsIgnoreCase("sms")) {
            return new SMSNotification();
        } else {
           throw new IllegalArgumentException("Invalid notification type");
        }
    }
}

// Classe Principal (Testes)
public class Main {
    public static void main(String[] args) {
        Notification email = NotificationFactory.createNotification("email");
        email.send("Olá via Email!");

        Notification sms = NotificationFactory.createNotification("sms");
        sms.send("Olá via SMS!");
    }
}
*/

// Versão: 1.2
// Factory Pattern

/*enum NotificationType {
    EMAIL, SMS
}

// Interface para notificações

interface Notification {
    void send(String message);
}

// Implementações concretas
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

// Factory melhorada: retornando notification(interface) Usando ENUM

class NotificationFactory {
    public static Notification createNotification(NotificationType type) {
        switch (type) {
            case EMAIL:
                return new EmailNotification();
            case SMS:
                return new SMSNotification();
            default:
                throw new IllegalArgumentException("Invalid notification type");
        }
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
*/

// Versão: 1.3
// Factory Pattern

import java.util.Map;
import java.util.function.Supplier;

// Enum para tipos de notificações
enum NotificationType {
    EMAIL, SMS
}

// Interface para notificações
interface Notification {
    void send(String message);
}

// Implementações concretas
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

// Factory utilizando Map para armazenar os tipos de notificações
class NotificationFactory {
    private static final Map<NotificationType, Supplier<Notification>> notificationMap = Map.of(
            NotificationType.EMAIL, EmailNotification::new,
            NotificationType.SMS, SMSNotification::new
    );

    public static Notification createNotification(NotificationType type) {
        Supplier<Notification> supplier = notificationMap.get(type);
        if (supplier == null) {
            throw new IllegalArgumentException("Invalid notification type");
        }
        return supplier.get();
    }
}

// Classe Principal para Testes
public class Main {
    public static void main(String[] args) {
        Notification email = NotificationFactory.createNotification(NotificationType.EMAIL);
        email.send("Olá via Email!");

        Notification sms = NotificationFactory.createNotification(NotificationType.SMS);
        sms.send("Olá via SMS!");
    }
}