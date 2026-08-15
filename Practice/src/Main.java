////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//void main() {
//    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//    // to see how IntelliJ IDEA suggests fixing it.
////    IO.println(String.format("Hello and welcome!"));
////
////    for (int i = 1; i <= 5; i++) {
////        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
////        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
////        IO.println("i = " + i);
////}
//
////        System.out.println("Hello World");
//
//        for (int i = 0; i < 5; i++)
//        {
//            System.out.println("i " + i);
//        }
//
//    }
//
//public class Main {
//    public static void main(String[] args)
//    {
////        User user = new User();
////
////        user.setUserName("Sam");
////        user.setUserAge(24);
////
////        String name = user.getUserName();
////        int age = user.getUserAge();
////
////        System.out.println(name +" - "+ age);
////        Output -
////        Sam - 24
//
////        User user = new User();
////        user.display();
////        Output -
////        Guest - 0
//
////        User user = new User("Sandip", 25);
////        user.display();
////        Output -
////        Sandip - 25
//
//
////        User user = User.createTeenUser("Laisha");
////        user.display();
////        Output -
////        Laisha - 18
//
////        User user = new User("Lin", 37);
////        User copiedUser = new User(user);
////
////        user.display();
////        copiedUser.display();
////        Output -
////        Lin - 37
////        Lin - 37
//
//        User u1 = new User();
//        User u2 = new User("Gerrard");
//        User u3 = new User("Ronaldo", 39);
//        User u4 = new User(40);
//
//        u1.display();
//        u2.display();
//        u3.display();
//        u4.display();
//
////        Output -
////        Default constructor called
////        One parameter constructor called
////        Guest - 0
////        Gerrard - 12
////        Ronaldo - 39
////        Kartik - 40
//
//
//
//    }
//}

public class Main {
    public static void main(String[] args) {

        bankAccount account = new bankAccount(1000, "SBI", "Savings", "Jack");

        account.balanceInfo();

        account.displayAccountInfo();

        account.deposit(1000);

        account.displayAccountInfo();

        account.transfer(500);

        account.displayAccountInfo();

        account.getAccountType();

//        System.out.println(balance);

    }
}



