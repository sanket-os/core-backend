//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import instructors.Instructor;
import users.User;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.println("Hello and welcome!");
//        int i = 4;
//        i = i*4;
//        System.out.println(i+10);
//
//        Student Chamath = new Student();
//        Chamath.psp = 90.9;
//        Chamath.name = "chamath";
//        Chamath.age = 30;
//
//        System.out.println(Chamath);
//
//        Student jagruti = new Student();
//        jagruti.age = 19;
//        jagruti.name = "Jagruti";
//        jagruti.psp = 80;
//
//        System.out.println(jagruti);
//
//        Chamath.pauseBatch();
//        jagruti.pauseBatch();
//
//        System.out.println(jagruti);

//        User user = new User();
//        user.name = "Sam";

        User user = new User("Sam", "123","admin");

        Instructor ins = new Instructor();
        ins.printNameOfUser(user);

        User copyUser = new User(user);

        System.out.println(copyUser);

    }
}