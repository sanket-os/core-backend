// another way to use depositor

//package BankAccount;
//
//public class Depositor implements Runnable {
//
//    BankAccount bankAccount;
//
//    public Depositor(BankAccount bankAccount) {
//        this.bankAccount = bankAccount;
//    }
//
//    @Override
//    public void run() {
//        for (long i = 0; i < 100000; i++) {
//            bankAccount.deposit(i);
//        }
//    }
//
//}



// Lambda expression
// A lambda expression is a concise way to provide the implementation
// of the single abstract method of a functional interface.

// Why do lambdas exist?
// Without lambdas:
// Behavior
// ↓
// Need anonymous class
// ↓
// Lots of boilerplate

// With lambdas:
// Behavior
// ↓
// Write only the important part

// Instead of creating an entire class:
// interface Cook {
//     void cook();
// }

// Cook chef = new Cook() {
//     @Override
//     public void cook() {
//         System.out.println("Cooking...");
//    }
// };

// You simply say:
// Cook chef = () -> System.out.println("Cooking...");

// The lambda is simply the behavior.
// Think of a lambda as a function stored inside an object

// Only after assigning it to a functional interface does it make sense.

// This doesn't compile:
// (a, b) -> a + b;

// Only after assigning it to a functional interface does it make sense.
// Calculator calc =
//         (a, b) -> a + b;

// Now Java knows:
// parameters
// return type
// method name


// The four functional interfaces you should memorize
// Function
// Input → Output
// Function<String, Integer> length =
//         s -> s.length();

// Predicate
// Input → true/false
// Predicate<Integer> even =
//         n -> n % 2 == 0;

// Consumer
// Consumes data
// Returns nothing
// Consumer<String> print =
//         System.out::println;

// Supplier
// Produces data
// No input
// Supplier<Double> random =
//         Math::random;