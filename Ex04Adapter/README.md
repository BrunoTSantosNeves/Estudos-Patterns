# Adapter Pattern - Padronizando Serviços Diferentes

## Problema
Você está desenvolvendo uma aplicação que consome informações de diferentes serviços terceirizados. No entanto, cada serviço retorna dados em formatos diferentes. O objetivo é padronizar esses formatos para que o sistema possa tratar todos os serviços de maneira uniforme.

## Solução
Utilizamos o **Adapter Pattern** para criar adaptadores que convertem os dados de cada serviço para um formato padronizado. Criamos uma interface comum chamada `NameProvider`, e cada serviço terá um adapter específico que implementa essa interface.

---

## Implementação

### Serviços Originais
Essas são as classes que representam os serviços terceirizados.

```java
class ServiceA {
    public String getUser() {
        return "Alice";
    }
}

class ServiceB {
    public String fetchName() {
        return "Bob";
    }
}
```

### Interface Comum (`NameProvider`)
Criamos uma interface para garantir que todos os serviços tenham um método padronizado `getName()`.

```java
interface NameProvider {
    String getName();
}
```

### Adapters
Cada serviço recebe um adapter correspondente, que implementa a interface `NameProvider` e converte os dados para o formato esperado.

```java
class ServiceAAdapter implements NameProvider {
    private final ServiceA serviceA;

    public ServiceAAdapter(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @Override
    public String getName() {
        return serviceA.getUser();
    }
}

class ServiceBAdapter implements NameProvider {
    private final ServiceB serviceB;

    public ServiceBAdapter(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    @Override
    public String getName() {
        return serviceB.fetchName();
    }
}
```

### Testando o Adapter

```java
public class AdapterExample {
    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();

        NameProvider adapterA = new ServiceAAdapter(serviceA);
        NameProvider adapterB = new ServiceBAdapter(serviceB);

        System.out.println("Nome do ServiceA: " + adapterA.getName());
        System.out.println("Nome do ServiceB: " + adapterB.getName());
    }
}
```

---

## Adicionando um Novo Serviço (ServiceC)
Se um novo serviço for adicionado (por exemplo, `ServiceC`), basta criar um novo adapter sem modificar o código existente, garantindo que o sistema seja **extensível**.

```java
class ServiceC {
    public String obtainFullName() {
        return "Charlie";
    }
}

class ServiceCAdapter implements NameProvider {
    private final ServiceC serviceC;

    public ServiceCAdapter(ServiceC serviceC) {
        this.serviceC = serviceC;
    }

    @Override
    public String getName() {
        return serviceC.obtainFullName();
    }
}
```

Agora podemos simplesmente criar uma instância do `ServiceCAdapter` e utilizá-la sem precisar modificar o restante do código!

```java
ServiceC serviceC = new ServiceC();
NameProvider adapterC = new ServiceCAdapter(serviceC);
System.out.println("Nome do ServiceC: " + adapterC.getName());
```

---

## Benefícios do Adapter Pattern
✅ **Encapsulamento**: Os serviços terceirizados são utilizados sem modificações.
✅ **Extensibilidade**: Novos serviços podem ser adicionados sem alterar o código existente.
✅ **Código desacoplado**: O sistema não depende diretamente das implementações específicas dos serviços.
✅ **Princípios SOLID**:
   - **SRP (Single Responsibility Principle)**: Cada adapter tem uma única responsabilidade.
   - **OCP (Open/Closed Principle)**: Podemos adicionar novos adapters sem modificar os existentes.

---

Com essa abordagem, garantimos que nossa aplicação possa consumir serviços externos de forma padronizada e escalável! 🚀

