import java.util.Map;
import java.util.function.Supplier;

// Enum para os tipos de veículos
enum VehicleType {
    CAR, BIKE, MOTORCYCLE
}

// Interface para os veículos
interface Vehicle {
    void move();
}

// Implementações dos veículos
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

// Factory usando Map
class VehicleFactory {
    private static final Map<VehicleType, Supplier<Vehicle>> VEHICLE_MAP = Map.of(
        VehicleType.CAR, Car::new,
        VehicleType.BIKE, Bike::new,
        VehicleType.MOTORCYCLE, Motorcycle::new
    );

    public static Vehicle createVehicle(VehicleType type) {
        Supplier<Vehicle> supplier = VEHICLE_MAP.get(type);
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException("Tipo de veículo não suportado: " + type);
    }
}

// Classe Principal para testes
public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR);
        car.move();

        Vehicle bike = VehicleFactory.createVehicle(VehicleType.BIKE);
        bike.move();

        Vehicle motorcycle = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE);
        motorcycle.move();
    }
}
