package first;

public class Main {

    public static void main(String[] args) {

//        Animal animal = new Animal();
//        animal.walk();
//
//        Dog dog = new Dog();
//        dog.walk();
//        dog.bark();
//
//        System.out.println(dog.s);
//        //      runtime polymorphism
//        Animal animalDog = new Dog();
//        animalDog.walk();
//        //      animalDog.bark(); => error
//
//        System.out.println(animalDog.s);

//        __________________________________________________________

//        Shallow copy example

//        Address addr = new Address("Mumbai");
//
//        Student s1 = new Student("Sam", addr);
//
//        Student s2 = new Student(s1); // shallow copy
//
//        s2.address.city = "Delhi";
//        System.out.println(s1.address.city);

//      _______________________________________________________________

//      Deep copy example

        Address addr = new Address("Mumbai");
        Student s1 = new Student("Sam", addr);

        Student s2 = new Student(s1); // deep copy

        s2.address.city = "Delhi";

        System.out.println(s1.address.city);

    }

}
