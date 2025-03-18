# Factory Pattern - Padrão de Fábrica

## 🔎 O que é o Factory Pattern?
O **Factory Pattern** é um padrão de projeto criacional que fornece uma interface para criar objetos em uma superclasse, permitindo que as subclasses alterem o tipo de objetos criados. Ele centraliza a criação de objetos, evitando a necessidade de utilizar `new` diretamente espalhado pelo código.

## ✅ Benefícios do Factory Pattern
- **Desacoplamento**: O código que solicita a criação do objeto não precisa saber os detalhes da implementação.
- **Fácil expansão**: Adicionar novos tipos de objetos é mais simples.
- **Centralização**: Toda a lógica de criação de objetos fica em um único local.

---

## 👨‍💻 Implementação em Java

### 1. Definição das interfaces
Criamos a interface `Vehicle`, que define o método `move()` para todos os tipos de veículos.

```java
// Interface para os veículos
interface Vehicle {
    void move();
}
```

### 2. Implementação dos Veículos
Criamos classes concretas `Car`, `Bike` e `Motorcycle` que implementam a interface `Vehicle`.

```java
// Implementações concretas
class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("Dirigindo um carro!");
    }
}

class Bike implements Vehicle {
    @Override
    public void move() {
        System.out.println("Andando de bicicleta!");
    }
}

class Motorcycle implements Vehicle {
    @Override
    public void move() {
        System.out.println("Pilotando uma motocicleta!");
    }
}
```

### 3. Enum para os Tipos de Veículos
Usamos um `enum` para evitar o uso de strings soltas e tornar o código mais seguro e fácil de manter.

```java
// Enum para tipos de veículos
enum VehicleType {
    CAR, BIKE, MOTORCYCLE
}
```

### 4. Implementação da Factory
Criamos a classe `VehicleFactory`, que contém um método estático `createVehicle()` para instanciar o veículo correto com base no tipo fornecido.

```java
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// Factory centralizada
class VehicleFactory {
    private static final Map<VehicleType, Supplier<Vehicle>> vehicleMap = new HashMap<>();

    static {
        vehicleMap.put(VehicleType.CAR, Car::new);
        vehicleMap.put(VehicleType.BIKE, Bike::new);
        vehicleMap.put(VehicleType.MOTORCYCLE, Motorcycle::new);
    }

    public static Vehicle createVehicle(VehicleType type) {
        Supplier<Vehicle> vehicle = vehicleMap.get(type);
        if (vehicle != null) {
            return vehicle.get();
        }
        throw new IllegalArgumentException("Tipo de veículo não suportado");
    }
}
```

### 5. Uso da Factory
Agora podemos criar veículos de forma centralizada e sem acoplamento direto!

```java
public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR);
        car.move(); // Saída: Dirigindo um carro!

        Vehicle bike = VehicleFactory.createVehicle(VehicleType.BIKE);
        bike.move(); // Saída: Andando de bicicleta!
        
        Vehicle motorcycle = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE);
        motorcycle.move(); // Saída: Pilotando uma motocicleta!
    }
}
```

---

## 💡 Conclusão
Essa versão do Factory Pattern nos permite criar novos tipos de veículos apenas adicionando novas classes e registrando-as no `Map` dentro da Factory. Dessa forma, evitamos a necessidade de modificar o método `createVehicle()` diretamente, tornando o sistema **mais flexível e escalável**! 🚀

