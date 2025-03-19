// Serviço A retorna um usuário no formato { "user": "Alice" }
class ServiceA {
    public String getUser() {
        return "Alice";
    }
}

// Serviço B retorna um nome no formato { "name": "Bob" }
class ServiceB {
    public String fetchName() {
        return "Bob";
    }
}

// Interface comum para padronizar saída
interface NameProvider {
    String getName();
}

// Adapter para o Serviço A
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

// Adapter para o Serviço B
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

// Classe principal para testes
public class Adapter {
    public static void main(String[] args) {
        
        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();

        NameProvider adapterA = new ServiceAAdapter(serviceA);
        NameProvider adapterB = new ServiceBAdapter(serviceB);

        System.out.println("Nome do Serviço A: " + adapterA.getName());
        System.out.println("Nome do Serviço B: " + adapterB.getName());
    }
}
