package designPatterns;

public class UserService {

//    But there's an important design issue
//    You might notice that our services have:

//public UserService() {
//    this.logger = AppLogger.getInstance();
//}

//This creates a hidden dependency.

//Looking at:
//new UserService();
//you can't immediately tell that UserService depends on AppLogger.


//    A cleaner design is dependency injection:
    private final AppLogger logger;

    /*
     * Get the application's shared Logger.
     * We aren't creating a new Logger.
     *
     */
    public UserService(AppLogger logger) {
        this.logger = logger;
    }

//    Now the dependency is explicit.

//            AppLogger
//                │
//                │ injected
//                ▼
//          UserService

//This is one of the reasons Singleton and Dependency Injection aren't the same thing.


    public void createUser(String username) {
        logger.info("Creating user: " + username);

        // Imagine database operation here...

        logger.info("User created successfully: " + username);
    }

}
