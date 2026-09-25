package designPatterns;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class AppLogger {

    /*
     * One shared Logger instance for the entire application.
     *
     * static -> belongs to the class
     * final  -> reference cannot be changed
     *
     * This is eager initialization.
     *
     * Class initialization in Java is thread-safe, so we don't
     * need synchronized or volatile for this implementation.
     */
//    private static final AppLogger INSTANCE = new AppLogger();


    /*
     * The nested class isn't initialized until it is actually
     * referenced.
     *
     * instance created only when getInstance() is called
     *
     * Therefore, INSTANCE is created lazily.
     */
    private static class Holder {
        public static final AppLogger INSTANCE = new AppLogger();
    }

    /*
     * Formatter is immutable and thread-safe.
     *
     * We create it once and reuse it.
     */
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*
     * Private constructor prevents:
     *
     *     new AppLogger();
     *
     * from outside this class.
     */
    private AppLogger() {

    }

    /*
     * Global access point to our single Logger instance.
     * along with Holder for lazy initialization
     *
     * for eager initialization avoid using Holder.INSTANCE
     * we use return INSTANCE
     */
    public static AppLogger getInstance() {
        return Holder.INSTANCE;
    }

    /*
     * Logs an informational message.
     */
    public void info(String message) {

        String timestamp = LocalDateTime.now()
                .format(FORMATTER);

        System.out.println(
                timestamp + " [INFO] " + message
        );
    }

    /*
     * Logs an error message.
     */
    public void error(String message) {

        String timestamp = LocalDateTime.now()
                .format(FORMATTER);

        System.err.println(
                timestamp + " [ERROR] " + message
        );
    }

}


//Singleton vs Double-Checked Locking
//You can now see the relationship:

//Eager Singleton
//private static final AppLogger INSTANCE =
//        new AppLogger();

//Class initialized
//       ↓
//Object created
//       ↓
//getInstance()
//       ↓
//Return existing object


//Lazy Singleton with DCL
//private static volatile AppLogger instance;

//getInstance()
//       ↓
//instance == null?
//       ↓
//synchronized
//       ↓
//create object


//Lazy Singleton with Holder
//private static class Holder {
//    static final AppLogger INSTANCE =
//            new AppLogger();
//}

//getInstance()
//       ↓
//Holder initialized
//       ↓
//INSTANCE created
//       ↓
//Return INSTANCE


//The modern Java takeaway

//If you're learning this for Java interviews + backend development, I'd remember these three levels:

//1. Understand Singleton
//        ↓
//2. Understand eager vs lazy initialization
//        ↓
//3. Understand thread-safe implementations
//        ↓
//   ├── Eager + static final
//   ├── Lazy + Holder
//   └── Lazy + DCL + volatile

//And one design principle is especially important:

//A Singleton's instance being thread-safe does not automatically make its mutable state thread-safe.

//That's why the example above deliberately uses an immutable AppLogger. It makes the Singleton much safer to share across the application.


