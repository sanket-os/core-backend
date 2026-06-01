package constructors;

//Java itself only really recognizes:

//constructors with parameters
//constructors without parameters

//The others are more like design patterns/concepts.


public class User {

//    A constructor is a special method used to create and initialize an object.
//    It runs automatically when you use new.

    private String name;
    private int age;

    // Default constructor
    public User() {
        this.name = "Guest";
        this.age = 0;
    }

    /*
        - No parameters
        - Gives default values
     */

//    public User() {

//        // constructor chaining
//        this("Guest", 0);

//        System.out.println("Default constructor called");
//    }
//      This calls another constructor in same class.
//      Constructor Chaining
//      Used to avoid duplicate code.


    // Parameterized constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Private constructor
    private User(String name) {
        this.name = name;
        this.age = 18;
    }

    // Copy constructor
    public User(User other) {
        this.name = other.name;
        this.age = other.age;
    }

    /*
    ---------------------------------------------------------
    FACTORY METHOD
    ---------------------------------------------------------
    - Public method that internally uses private constructor
    */
    public static User createTeenUser(String name) {
        return new User(name);
    }

    public void display() {
        System.out.println(name + " - " + age);
    }

}


//Default Constructor
//public User() {
//    this.name = "Guest";
//    this.age = 0;
//}


//Usage
//User user1 = new User();
//user1.display();

//Output
//Guest - 0


//Parameterized Constructor
//public User(String name, int age) {
//    this.name = name;
//    this.age = age;
//}

//Usage
//User user2 = new User("Sam", 25);
//user2.display();

//Output
//Sam - 25


//Private Constructor
//private User(String name) {
//    this.name = name;
//    this.age = 18;
//}

//❌ Can this work outside the class?
//User u = new User("Alex"); // ERROR ❌

//No.
//Because the constructor is private.


//Then why use it?

//To control object creation.

//The class itself can still use it:

//public static User createTeenUser(String name) {
//    return new User(name);
//}

//Usage
//User teen = User.createTeenUser("Alex");
//teen.display();

//Output
//Alex - 18



// copy constructor

//Usage
//User original = new User("Sam", 25);

//User copied = new User(original);

//copied.display();

//Output
//Sam - 25

//What happened here?
//        new User(original)

//calls:
//public User(User other)

//which copies values from another object.


//Constructor overloading means having Multiple constructors


//  A constructor is fundamentally just:

//  “Rules for how an object should come into existence.”

//  Every constructor type is just a different way of controlling object creation.



//  Constructor chaining means:

//  One constructor calls another constructor to reuse initialization logic and avoid duplicate code.


//public class User {
//
//    private String name;
//    private int age;
//
//    /*
//     -------------------------------------------------
//     MAIN constructor
//     -------------------------------------------------
//     All real initialization happens here
//     */
//    public User(String name, int age) {
//
//        this.name = name;
//        this.age = age;
//
//        System.out.println("Main constructor");
//    }
//
//    /*
//     -------------------------------------------------
//     DEFAULT constructor
//     -------------------------------------------------
//     Calls another constructor
//     */
//    public User() {
//
//        // constructor chaining
//        this("Guest", 0);
//
//        System.out.println("Default constructor");
//    }
//
//    /*
//     -------------------------------------------------
//     ONE PARAMETER constructor
//     -------------------------------------------------
//     Calls another constructor
//     */
//    public User(String name) {
//
//        // constructor chaining
//        this(name, 18);
//
//        System.out.println("One parameter constructor");
//    }
//
//    public void display() {
//        System.out.println(name + " - " + age);
//    }
//}


//  Usage
//User u1 = new User();

//User u2 = new User("Sam");

//User u3 = new User("Alex", 25);


//  Instead of this logic existing in 3 places:
//
//        this.name = ...
//        this.age = ...
//
//  it exists in ONLY ONE place.


//  Constructor Chaining Rule

//  Using:
//        this(...)
//  must be the FIRST line in constructor.



//  shallow copy and deep copy constructors are explained next classes
