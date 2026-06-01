package polymorphism;

/*
Polymorphism means:
One common type
Many different behaviors

or

Same method call
Different implementation
based on the actual object.


The Big OOP Principle Behind It
Polymorphism helps achieve:
“Program to interfaces, not implementations.”
 */

//Modern Java interfaces are powerful.
// They can contain:

//abstract methods
//default methods
//static methods
//private methods
//sealed interfaces
interface Payment {

    void pay(double amount);

    // Compile-time polymorphism (overloading)
    default void pay() {
        pay(0);
    }

}

// use interface when classes share capability / behavior
// use abstract classes when classes share common state + behavior

// This is cleaner and more flexible than deep inheritance.
class CardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + "using Card");
    }

}

class UpiPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }

}


public class Main {

    public static void main(String[] args) {

        //  Upcasting
        //  Converting child object → parent reference.
        //  This is automatic and safe.
        //  Polymorphism mainly works through upcasting.
        Payment p1 = new CardPayment();
        Payment p2 = new UpiPayment();


        // Runtime polymorphism (Dynamic Method Dispatch)
        // Achieved using: interfaces, inheritance, method overriding
        // based on Actual object, more flexible, extensible
        p1.pay(500);
        p2.pay(1000); // Different implementations execute

        System.out.println("___________________________");


        // Polymorphism in collections - very powerful
        // all classes are treated uniformly
        Payment[] payments = {
                new CardPayment(),
                new UpiPayment(),
        };

        for (Payment payment : payments) {
            payment.pay(999);
        }

        System.out.println("_____________________________");


        // Modern pattern matching - higher-level safer feature
        // Pattern Matching = Type Check + Safe Downcast + Variable Creation
        Payment payment = new UpiPayment();


        // Downcasting = “Treat this parent reference as a child type.”
        // instanceof is Cleaner, safer, less repetitive way of downcasting
        if (payment instanceof UpiPayment upi) {
            System.out.println("This is a UPI payment");
        }

        System.out.println("_____________________________");

        // Compile-time polymorphism
        // Method Overloading, gives priority based on datatype of parameters
        // less flexible, convenient
        payment.pay(); // Java decides at compile time based on parameters.

    }

}


/*
Final Output
Paid ₹500.0 using Card
Paid ₹1000.0 using UPI
------------
Paid ₹999.0 using Card
Paid ₹999.0 using UPI
------------
This is UPI payment
------------
Paid ₹0.0 using UPI
 */


/*

With polymorphism messy code becomes clean and scalable
This Is the Foundation of Modern Software


Method Overriding Rules
A child class can redefine parent behavior.

Rules:
Same method name
Same parameters
Return type compatible
Cannot reduce visibility

@Override is strongly recommended. It lets compiler verify correctness.


Dynamic Method Dispatch
This is the internal mechanism behind runtime polymorphism.
Java JVM maintains method tables and resolves:
to the correct implementation at runtime.


Static Methods Are NOT Polymorphic
They are loaded once into memory when the class loads.
Static methods belong to the class, not the object.


Example:
class A {
    static void hello() {}
}

class B extends A {
    static void hello() {}
}

This is: Method Hiding NOT overriding.
Static methods are resolved at compile time.
Static methods should ideally be called using class names:

Final Methods Cannot Be Overridden
final void process() {} - This implementation must not change
runtime polymorphism cannot happen for that method.


Sealed Classes and Interfaces (Modern Java)
sealed interface Payment permits UpiPayment, cardPayment

This restricts who can implement the interface.

Benefits:
Better control
Safer hierarchies
Better compiler checks
Better switch exhaustiveness

Very common in modern Java design.


Records + Polymorphism
Records are immutable data carriers.
e.g. record EmailNotification(String email)

Benefits:
Less boilerplate
Immutable by default
Cleaner code
Great for DTOs/events/messages

Modern Java prefers records when appropriate.


You cannot access methods inside of an actual child object in inheritance from parents reference
You can only access methods inside of parent class
This is why you need downcasting to access methods of child class from parent reference

If you have many children then downcasting becomes difficult to manage
hence we use pattern matching using instanceOf to safely downcast without ClassCastException crash


Compile-Time - java checks everything at start for errors

Your .java file is checked by the compiler.
Example:
Main.java

Compiler:
javac Main.java

The compiler checks:
syntax errors
type checking
method existence
variable declarations
compile-time rules
If everything is correct: Main.class (bytecode) is created.

In compile time - Static Binding
Java decides method early.

The Java Virtual Machine (JVM) is a core engine that drives Java code.
It acts as an abstract computing machine that converts compiled Java bytecode
into machine-specific instructions for execution. The JVM provides the runtime
environment that allows Java applications to run on any operating system without
modification.

Runtime -

Now JVM runs the bytecode:
java Main

Now actual execution happens:
objects created
methods called
memory allocated
loops executed
exceptions may occur
garbage collection
thread scheduling

Runtime → Dynamic Binding
Java waits for actual object.

Binding means:
“Connecting a method call to the actual method implementation.”

Static binding - Early binding at compile time
Uses static/final/private/overloading
Depends on Reference type
No polymorphism
less flexible


Runtime binding - Late binding at runtime
Uses overriding
Depends on Actual object
Polymorphism happens
more flexible

 */
