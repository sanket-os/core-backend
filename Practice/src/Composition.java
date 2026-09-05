class Engine {

    private final String type;

    public Engine(String type) {
        this.type = type;
    }

    public void start() {
        System.out.println(type + " engine started");
    }

}

class myCar {

    private final String brand;
    private final Engine engine;

    public myCar(String brand, Engine engine) {
        this.brand = brand;
        this.engine = engine;
    }

    public void carInfo() {
        System.out.println(brand + " car is running");

        engine.start();
    }

}






public class Composition {

    public static void main(String[] args) {

        Engine engine = new Engine("Hydrogen");

        myCar car = new myCar("Nissan", engine);

        car.carInfo();

    }

}


// OUTPUT
// Nissan car is running
// Hydrogen engine started
