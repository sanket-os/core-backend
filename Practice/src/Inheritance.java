class Vehicle {

    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void start() {
        System.out.println(brand + " engine has started");
    }

}


class Car extends Vehicle {

    private int doors;

    public Car(String brand, int doors) {
        super(brand);
        this.doors = doors;
    }

    @Override
    public void start() {
        System.out.println(brand + " car with " + doors +" doors starts with a push button");
    }

    public void carInfo() {
        System.out.println("Car Brand: " + brand);
        System.out.println("Doors: " + doors);
    }

}


class electricCar extends Car {

    private int batteryCapacity;

    public electricCar(String brand, int doors, int batteryCapacity) {
        super(brand, doors);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void start() {
        System.out.println("Electric car has " + batteryCapacity + "mAh battery capacity.");
    }

}


class Bike extends Vehicle {

    public Bike(String brand) {
        super(brand);
    }

    @Override
    public void start() {
        System.out.println(brand + " bike starts with a kick start");
    }


}

interface GPS {
    void navigate();
}


class smartElectricCar extends electricCar implements GPS {

    public smartElectricCar(String brand, int doors, int batteryCapacity) {
        super(brand, doors, batteryCapacity);
    }

    @Override
    public void start() {
        System.out.println("Smart electric car stats with a remote signal");
    }

    @Override
    public void navigate() {
        System.out.println("Smart electric car uses navigation with GPS");
    }

}



class inheritance {

    public static void main(String[] args) {

        Car car = new Car("BMW", 4);

        car.start();
        car.carInfo();

        System.out.println();

        electricCar electricCar = new electricCar("Nissan", 4, 50);

        electricCar.start();
        electricCar.carInfo();

        System.out.println();

        Bike bike = new Bike("Duke");

        bike.start();

        System.out.println();

        smartElectricCar smartElectricCar = new smartElectricCar("Tesla", 2, 100);

        smartElectricCar.start();
        smartElectricCar.navigate();
        smartElectricCar.carInfo();


    }

}


// OUTPUT

//BMW car with 4 doors starts with a push button
//Car Brand: BMW
//Doors: 4

//Electric car has 50mAh battery capacity.
//Car Brand: Nissan
//Doors: 4

//Duke bike starts with a kick start

//Smart electric car stats with a remote signal
//Smart electric car uses navigation with GPS
//Car Brand: Tesla
//Doors: 2