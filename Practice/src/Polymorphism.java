//interface Payment {
//    void pay(double amount);
//
//    default void pay() {
//        pay(0);
//    }
//}
//
//class cardPayment implements Payment {
//    public void pay(double amount) {
//        System.out.println(amount + " paid using card payment.");
//    }
//}
//
//class upiPayment implements Payment {
//    public void pay(double amount) {
//        System.out.println(amount + " paid using UPI payment.");
//    }
//}
//
//
//public class Polymorphism {
//
//    public static void main(String[] args) {
//
//        Payment p1 = new cardPayment();
//        Payment p2 = new upiPayment();
//
//        p1.pay(1000);
//        p2.pay(5000);
//
//        System.out.println();
//
//        Payment[] payments = {
//                new cardPayment(),
//                new upiPayment()
//        };
//
//        for (Payment payment: payments) {
//            payment.pay(800);
//        }
//
//        System.out.println();
//
//        Payment payment = new cardPayment();
//
//        if (payment instanceof cardPayment card) {
//            System.out.println("This is a card payment.");
//        }
//
//        payment.pay();
//
//    }
//
//}




 class USER {

    String name;
    String email;

    public USER(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void getInfo() {
        System.out.println("Name: " + name + " Email: " + email + " method - I");
    }

//   method overloading - compile time polymorphism
    public void getInfo(String name, String email) {
        System.out.println("Name: " + name + " Email: " + email + " method - II");
    }

}


class Student extends USER {

    private String batchName;
    private int psp;

    public Student(String batchName, int psp, String name, String email) {
        super(name, email);
        this.batchName = batchName;
        this.psp = psp;
    }

//    method overriding - runtime polymorphism
    @Override
    public void getInfo() {
        System.out.println("Name: " + name + " Email: " + email + " BatchName: " + batchName + " PSP: " + psp);
    }

}




public class Polymorphism {

    public static void main(String[] args) {

        USER user = new USER("max", "max@gmail.com");
        user.getInfo();

        user.getInfo("Caleb", "caleb@yahoo.com");

        Student student = new Student("batch 1", 90, "Sam", "sam@gmail.com");
        student.getInfo();

        USER User = new Student("batch 2", 80, "John", "jj@gmail.com");
        User.getInfo();

    }

}





















































