package inheritance;

/*

Inheritance means:
One class can acquire properties and behavior from another class.

It helps us:
reuse code
avoid duplication
build relationships between classes

 */

// PARENT CLASS
class vehicle {

    protected String brand; // protected → accessible in child classes

    public vehicle(String brand) {
        this.brand = brand;
    }

    public void start() {

        System.out.println(brand + " vehicle is starting");

    }

}

// SINGLE INHERITANCE - Car inherits Vehicle
class Car extends vehicle {

    private int doors;

    public Car(String brand, int doors) {

        super(brand); // call parent constructor

        this.doors = doors;

    }

    //  METHOD OVERRIDING - Child provides its own implementation
    //  Child changes behavior: This is polymorphism.
    @Override
    public void start() {

        System.out.println(brand + " car starts with push button");

    }

    public void displayCarInfo() {

        System.out.println("Brand : " + brand);

        System.out.println("Doors : " +  doors);

    }

}


//  MULTILEVEL INHERITANCE - ElectricCar inherits Car
//  Vehicle -> Car -> ElectricCar
class ElectricCar extends Car {

    private int batteryCapacity;

    public ElectricCar(
            String brand,
            int doors,
            int batteryCapacity
    ) {

        super(brand, doors);

        this.batteryCapacity = batteryCapacity;

    }

    public void charge() {

        System.out.println(
                "Charging battery : "
                    + batteryCapacity
                    + "kwh"
        );

    }

}

// HIERARCHICAL INHERITANCE - Bike also inherits Vehicle
class Bike extends Vehicle {

    public Bike(String brand) {

        super(brand);

    }

    @Override
    public void start() {

        System.out.println(brand + " bike starts with self-start");

    }

}


//  INTERFACE -  Used for multiple inheritance of behavior
interface GPS {

    void navigate();

}


//  MULTIPLE INHERITANCE (via interfaces)
//  Java does NOT support multiple inheritance with classes
//  BUT supports it using interfaces
class SmartElectricCar extends ElectricCar
        implements GPS {

    public SmartElectricCar (
            String brand,
            int doors,
            int batteryCapacity
    ) {

        super(brand, doors, batteryCapacity);

    }

    @Override
    public void navigate() {

        System.out.println("GPS navigation started");

    }

}


//  MAIN CLASS
public Class InheritanceDemo {

    public static void main(String[] args) {

        // SINGLE INHERITANCE
        Car car = new Car("Tesla", 4);

        car.start();

        car.displayCarInfo();

        System.out.println();


        // MULTILEVEL INHERITANCE
        ElectricCar electricCar =
                new ElectricCar("BYD", 4, 75);

        electricCar.start();

        electricCar.charge();

        System.out.println();


        // HIERARCHICAL INHERITANCE
        Bike bike = new Bike("BMW");

        bike.start();

        System.out.println();


        //  MULTIPLE INHERITANCE USING INTERFACE
        SmartElectricCar smartCar =
                new smartElectricCar(
                        "Tesla",
                        4,
                        100
                );

        smartCar.start();

        smartCar.charge();

        smartCar.navigate();

    }

}

/*

OUTPUT -

Tesla car starts with push button
Brand : Tesla
Doors : 4

BYD car starts with push button
Charging battery : 75 kWh

Yamaha bike starts with self-start

Tesla car starts with push button
Charging battery : 100 kWh
GPS navigation started

 */

/*

Inheritance answers:
“Is this object a specialized version of another object?”

Examples:
Car IS-A Vehicle
Bike IS-A Vehicle
ElectricCar IS-A Car


Java looks:
current class
parent class
grandparent class
upward until found

This is called:
✅ Method Resolution


Modern Java prefers:
Composition over inheritance
when inheritance becomes too deep or tightly coupled.

IS-A relationship → inheritance
HAS-A relationship → composition

*/












































