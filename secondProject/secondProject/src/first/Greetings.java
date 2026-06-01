package first;

public class Greetings {

    //    method overloading or compile time polymorphism
    void hello() {
        System.out.println("Hello world!");
    }

    void hello(String s) {
        System.out.println("hello " + s);
    }

    void hello(Dog d) {
        System.out.println("Hello dawg!");
    }



}
