public class Student {

    String name;
    int age;
    double psp;

    void pauseBatch() {
        if (age > 20) {
            System.out.println("Pause not possible");
            return;
        }
        psp = 0.0;
        System.out.println("Pause initiated.");
    }

}
