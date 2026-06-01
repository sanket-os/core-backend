package first;

public class Dog extends Animal{

    int s = 5;

    public void bark() {
        System.out.println("Dog barks.");
    }

    @Override
    public void walk() {
        System.out.println("Dog walks.");
    }

}
