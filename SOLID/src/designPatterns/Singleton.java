package designPatterns;

public class Singleton {

    public static void main(String[] args) {

//        UserService userService = new UserService();
//        PaymentService paymentService = new PaymentService();
//        userService.createUser("Sam");
//        paymentService.processPayment(1500);
//        AppLogger logger1 = AppLogger.getInstance();
//        AppLogger logger2 = AppLogger.getInstance();


//      Cleaner Version
        AppLogger logger1 = AppLogger.getInstance();
        AppLogger logger2 = AppLogger.getInstance();

        UserService userService = new UserService(logger1);
        PaymentService paymentService = new PaymentService(logger2);

        userService.createUser("Sam");
        paymentService.processPayment(1500);

        /*
         * Let's prove that both services are using
         * the exact same Logger object.
         */
        System.out.println(
                "Same logger instance? " +
                        (logger1 == logger2)
        );

    }

}

// OUTPUT -
//2026-09-24 19:50:22 [INFO] Creating user: Sam
//2026-09-24 19:50:22 [INFO] User created successfully: Sam
//2026-09-24 19:50:22 [INFO] Processing payment: Rs.1500.0
//2026-09-24 19:50:22 [INFO] Payment completed successfully
//Same logger instance? true



// Thread-safe Singleton using Double-Checked Locking

//public final class Singleton {
//
//    // volatile is essential for correct double-checked locking
//    private static volatile Singleton instance;
//
//    // Private constructor prevents external object creation
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//
//        // First check — no locking in the common case
//        if (instance == null) {
//
//            // Only one thread can enter this block at a time
//            synchronized (Singleton.class) {
//
//                // Second check — another thread may have
//                // created the instance while we were waiting
//                if (instance == null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//
//        return instance;
//    }
//}

// Modern Java perspective
// There's an important caveat:
// you usually don't need DCL in modern Java unless you specifically need this pattern.

//For eager initialization, this is simpler:
//public final class Singleton {
//
//    private static final Singleton INSTANCE = new Singleton();
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        return INSTANCE;
//    }
//}

// And for lazy initialization, the Initialization-on-demand holder idiom is often cleaner
// public final class Singleton {
//
//    private Singleton() {
//    }
//
//    private static class Holder {
//        private static final Singleton INSTANCE = new Singleton();
//    }
//
//    public static Singleton getInstance() {
//        return Holder.INSTANCE;
//    }
//}


//The holder approach gives you:

//lazy initialization
//thread safety
//no explicit synchronization
//no volatile
//less code
//easier reasoning

//So for an interview, you should definitely know DCL, but in production code I'd consider whether the holder idiom or dependency injection is a cleaner fit.



//A Singleton ensures that a class has exactly one instance within a given application/runtime and provides a way to access that instance.

//Why does private constructor matter?
//Why static final?

//static
//There is one INSTANCE associated with the class rather than with individual objects.
//final
//The reference cannot be reassigned:
//private
//Only the class itself can access the instance directly.

//private
//   ↓
//encapsulation
//
//static
//   ↓
//one class-level reference
//
//final
//   ↓
//reference cannot change

//Is Singleton thread-safe?
// Yes. The JVM handles class initialization safely, so multiple threads won't create multiple instances of INSTANCE.
//This is one reason this implementation is preferable to manually writing synchronization logic.

// Pros of Singleton
//✅ 1. Guarantees a single instance
//Useful when multiple instances would be incorrect.

//Examples can include certain:
//configuration registries
//application-level caches
//resource managers

//✅ 2. Centralized access
//You have:
//AppConfig.getInstance()
//rather than creating the object everywhere.

//✅ 3. Can avoid unnecessary object creation
//If an object is expensive to create and genuinely needs only one instance, you don't repeatedly construct it.

//✅ 4. Shared state
//All consumers see the same instance:
//Service A ──┐
//Service B ──┼──► Shared Object
//Service C ──┘
//Sometimes this is exactly what you need.


//Cons of Singleton
//This is where most of the criticism comes from.

//❌ 1. Global state
//Singletons are essentially a controlled form of global state.
//For example:
//Logger.getInstance().setLevel(DEBUG);
//Some completely unrelated part of the application can now observe the changed state.
//This can make behavior difficult to reason about.

//❌ 2. Hidden dependencies
//This:
//class OrderService {
//
//    public void process() {
//        Logger.getInstance().log("...");
//    }
//}
//doesn't tell you from the constructor that OrderService needs Logger.
//Whereas:
//public OrderService(Logger logger)
//does.

//❌ 3. Testing becomes harder
//Imagine:
//class OrderService {
//
//    public void process() {
//        Logger.getInstance().log("...");
//    }
//}

//In a unit test, you'd ideally like to give it:
//FakeLogger
//or:
//MockLogger

//But because OrderService directly calls:
//Logger.getInstance()
//it's much harder to substitute a different implementation.
//Dependency injection makes this easier:
//OrderService service =
//        new OrderService(fakeLogger);

//❌ 4. Can violate Single Responsibility Principle
//Your Singleton may be responsible for:
//Creating itself
//       +
//Managing global access
//       +
//Performing business logic
//       +
//Managing state
//Now the class has multiple reasons to change.

//❌ 5. Can create tight coupling
//If 20 classes directly use:
//SomeSingleton.getInstance()
//those 20 classes are now coupled to that specific implementation.

//❌ 6. Concurrency can become complicated
//The Singleton instance itself may be thread-safe, but its mutable state may not be.
//For example:
//public final class Counter {
//    private static final Counter INSTANCE = new Counter();
//
//    private int count;
//
//    private Counter() {
//    }
//
//    public static Counter getInstance() {
//        return INSTANCE;
//    }
//
//    public void increment() {
//        count++;
//    }
//}

//The Singleton is safely created, but:
//count++;
//is not automatically thread-safe.
//That's an important distinction:
//Thread-safe Singleton creation ≠ thread-safe Singleton state.


// When should you use Singleton?
//A good question isn't:
//"Can I make this a Singleton?"
//Almost anything can be made one.
//Instead ask:
//"Does my application's design require exactly one instance?"
//If yes, Singleton might be appropriate.

//For example:
//One application-wide configuration
//One shared registry
//One specific resource manager
//One application-wide cache

//But be careful with things like:
//User
//Order
//Product
//Payment
//Customer

//These generally should not be Singletons.

//You obviously need:
//User #1
//User #2
//User #3
//...

//not:
//ONE User


// One subtle but important point
//Singleton doesn't necessarily mean:
//"There can only ever be one object in the entire JVM."

//The practical meaning is:
//One instance managed by that Singleton mechanism within its relevant runtime/class-loading context.

//That's why statements like "Singleton guarantees exactly one object everywhere" are overly simplistic.

//For normal application development, though, you can think:

//Application
//     │
//     ▼
//Singleton
//     │
//     ▼
//One shared instance
