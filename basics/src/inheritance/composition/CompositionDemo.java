package inheritance.composition;

/*

Composition means:
One class contains another class as a part of itself.

Instead of saying:
Car IS-A Engine ❌

we say:
Car HAS-A Engine ✅

That is composition.

 */

class Engine {

    private final String type;

    public Engine(String type) {

        this.type = type;

    }

    public void start() {

        System.out.println(type + " engine started");

    }

}

//  Car HAS-A Engine
//  This is composition
class Car {

    //  composition
    private final Engine engine;
//    This means:
//    Car contains Engine
//    Car uses Engine
//    Car delegates work to Engine

    private final String brand;

    public Car(String brand, Engine engine) {

        this.brand = brand;

        this.engine = engine;

    }

    public void startCar() {

        System.out.println(brand + " car is starting");

        engine.start(); // using Engine object internally

    }

}

public class CompositionDemo {

    public static void main(String[] args) {

        Engine electricEngine =
                new Engine("Electric");

        Car tesla = new Car("Tesla", electricEngine);

        tesla.startCar();

    }

}

//  Output
//  Tesla car is starting
//  Electric engine started

//  Problem with Deep Inheritance
//  rigid, tightly coupled, hard to maintain

//  Instead of giant inheritance chains:
//  Each part is independent in composition
//  it has flexible design, loose coupling

//  Composition is more maintainable, more modular,
//  easier to test, avoids fragile inheritance chains