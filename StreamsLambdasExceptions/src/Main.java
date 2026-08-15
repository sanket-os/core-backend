// Streams & Lambdas

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        /*
         * ============================================================
         * 1. LAMBDA + FUNCTIONAL INTERFACE
         * ============================================================
         *
         * A functional interface has exactly ONE abstract method.
         *
         * Function<T, R> is a functional interface.
         *
         * It represents:
         *
         *      T -> R
         *
         * In other words:
         *
         *      Input -> Output
         */

        Function<String, Integer> getLength =
                name -> name.length();

        /*
         * The lambda:
         *
         *      name -> name.length()
         *
         * provides the implementation of Function's abstract method:
         *
         *      apply()
         *
         * So:
         *
         *      getLength.apply("Alice")
         *
         * becomes:
         *
         *      "Alice".length()
         */

        int length = getLength.apply("Alice");

        System.out.println("Length of Alice: " + length);


        /*
         * ============================================================
         * 2. PREDICATE + LAMBDA
         * ============================================================
         *
         * Predicate<T> represents:
         *
         *      T -> boolean
         *
         * It answers a YES/NO question.
         */

        Predicate<Integer> isEven =
                number -> number % 2 == 0;

        /*
         * The lambda:
         *
         *      number -> number % 2 == 0
         *
         * answers:
         *
         *      "Is this number even?"
         */

        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 7 even? " + isEven.test(7));


        /*
         * ============================================================
         * 3. CONSUMER + LAMBDA
         * ============================================================
         *
         * Consumer<T> represents:
         *
         *      T -> nothing
         *
         * It receives something and performs an action.
         */

        Consumer<String> printer =
                name -> System.out.println("Hello, " + name);

        /*
         * The lambda provides the implementation of Consumer's
         * accept() method.
         */

        printer.accept("Alice");
        printer.accept("Bob");


        /*
         * ============================================================
         * 4. SUPPLIER + LAMBDA
         * ============================================================
         *
         * Supplier<T> represents:
         *
         *      nothing -> T
         *
         * It doesn't receive an input.
         * It simply supplies/produces a value.
         */

        Supplier<String> messageSupplier =
                () -> "Hello from Supplier";

        String message = messageSupplier.get();

        System.out.println(message);


        /*
         * ============================================================
         * 5. NOW LET'S USE STREAMS
         * ============================================================
         *
         * A Stream is a pipeline for processing data.
         *
         * A List stores data.
         *
         * A Stream processes that data.
         */

        List<Integer> numbers =
                List.of(1, 2, 3, 4, 5, 6, 7, 8);


        /*
         * Our requirement:
         *
         *   1. Take the numbers
         *   2. Keep only even numbers
         *   3. Double each even number
         *   4. Put the result into a List
         *
         * We can express this as:
         *
         *      source
         *        ↓
         *      filter
         *        ↓
         *      map
         *        ↓
         *      toList
         */

        List<Integer> result =
                numbers.stream()

                        /*
                         * FILTER
                         *
                         * filter() expects a Predicate<T>.
                         *
                         * Predicate:
                         *
                         *      T -> boolean
                         *
                         * Our lambda:
                         *
                         *      number -> number % 2 == 0
                         *
                         * asks:
                         *
                         *      "Should this number remain?"
                         *
                         * Let's process the numbers:
                         *
                         * 1 -> false -> discard
                         * 2 -> true  -> keep
                         * 3 -> false -> discard
                         * 4 -> true  -> keep
                         * 5 -> false -> discard
                         * 6 -> true  -> keep
                         * 7 -> false -> discard
                         * 8 -> true  -> keep
                         */

                        .filter(number -> number % 2 == 0)

                        /*
                         * MAP
                         *
                         * map() expects a Function<T, R>.
                         *
                         * Function:
                         *
                         *      T -> R
                         *
                         * Our lambda:
                         *
                         *      number -> number * 2
                         *
                         * transforms each remaining number.
                         *
                         * 2 -> 4
                         * 4 -> 8
                         * 6 -> 12
                         * 8 -> 16
                         */

                        .map(number -> number * 2)

                        /*
                         * toList() is a TERMINAL operation.
                         *
                         * It finishes the Stream pipeline and gives
                         * us the resulting List.
                         */

                        .toList();


        System.out.println("Stream result: " + result);


        /*
         * ============================================================
         * 6. ANOTHER STREAM EXAMPLE WITH STRINGS
         * ============================================================
         */

        List<String> names =
                List.of(
                        "Alice",
                        "Bob",
                        "Alexander",
                        "David",
                        "Christopher"
                );


        /*
         * Requirement:
         *
         *   1. Keep names longer than 5 characters
         *   2. Convert them to uppercase
         *   3. Store the result in a List
         */

        List<String> longNames =
                names.stream()

                        /*
                         * Predicate<String>
                         *
                         * String -> boolean
                         *
                         * Keep only names whose length is greater than 5.
                         */

                        .filter(name -> name.length() > 5)

                        /*
                         * Function<String, String>
                         *
                         * String -> String
                         *
                         * Convert every remaining name to uppercase.
                         */

                        .map(name -> name.toUpperCase())

                        /*
                         * Finish the pipeline.
                         */

                        .toList();


        System.out.println("Long names: " + longNames);


        /*
         * ============================================================
         * 7. forEach()
         * ============================================================
         *
         * forEach() expects a Consumer<T>.
         *
         * Consumer:
         *
         *      T -> nothing
         *
         * Let's print every name.
         */

        System.out.println("Printing names:");

        names.stream()
                .forEach(name ->
                        System.out.println(name));


        /*
         * ============================================================
         * 8. REDUCE
         * ============================================================
         *
         * reduce() is used when we want to combine many values
         * into ONE final value.
         *
         * Example:
         *
         *      1 + 2 + 3 + 4 + 5
         *
         *      = 15
         */

        int sum =
                numbers.stream()

                        /*
                         * Start with 0.
                         *
                         * Then repeatedly combine the current result
                         * with the next number.
                         *
                         * 0 + 1 = 1
                         * 1 + 2 = 3
                         * 3 + 3 = 6
                         * 6 + 4 = 10
                         * 10 + 5 = 15
                         * ...
                         */

                        .reduce(
                                0,
                                (total, number) -> total + number
                        );


        System.out.println("Sum: " + sum);
    }
}


// OUTPUT -
// Length of Alice: 5

// Is 10 even? true
// Is 7 even? false

// Hello, Alice
// Hello, Bob

// Hello from Supplier

// Stream result: [4, 8, 12, 16]
// Long names: [ALEXANDER, CHRISTOPHER]

// Printing names:
// Alice
// Bob
// Alexander
// David
// Christopher

// Sum: 36




//                     INTERFACE
//                        │
//                        ▼
//              Functional Interface
//                        │
//                        ▼
//              Exactly one abstract
//                     method
//           (SAM = Single abstract method)
//                        │
//                        ▼
//                     Lambda
//                        │
//                        │ provides implementation
//                        ▼
//              ┌─────────────────────┐
//              │                     │
//         Predicate             Function
//          T → boolean            T → R
//              │                     │
//              ▼                     ▼
//           filter()              map()
//              │                     │
//              └──────────┬──────────┘
//                         ▼
//                       Stream
//                         │
//                  Data processing
//                         │
//             ┌───────────┼───────────┐
//             ▼           ▼           ▼
//          filter        map       reduce
//             │           │           │
//             └───────────┼───────────┘
//                         ▼
//                   Terminal operation
//                         │
//                         ▼
//                      Result



// Most stream pipelines can be understood as:
// SOURCE
//    ↓
// INTERMEDIATE OPERATIONS
//    ↓
// TERMINAL OPERATION



// Source
// numbers.stream()
// Where does the data come from?

// Intermediate operations
// Examples:
// filter()
// map()
// sorted()
// distinct()
// limit()
// They transform the stream into another stream.

// Terminal operation
// Examples:
// toList()
// forEach()
// count()
// reduce()
// findFirst()
// anyMatch()
// The terminal operation finishes the pipeline.



//  Why Streams are called "lazy"
//This is one of the most important concepts.
//Look at:
//numbers.stream()
//       .filter(number -> number % 2 == 0)
//       .map(number -> number * 10);
//You might think Java immediately processes the numbers.
//It doesn't.
//Why?
//Because there is no terminal operation.
//You haven't said:
//"Now give me the result."

//Add:
//.toList();
//Now the pipeline executes.
//So:
//numbers.stream()
//       .filter(...)
//       .map(...)
//       .toList();
//
//has a terminal operation.
//This is called lazy evaluation.



// Why is laziness useful?
//Consider:
//List<Integer> numbers =
//        List.of(1, 2, 3, 4, 5);

//Suppose we write:
//numbers.stream()
//       .filter(number -> number % 2 == 0)
//       .map(number -> number * 10)
//       .findFirst();
//Java doesn't necessarily process everything.

//Conceptually:
//1 → filter → discard
//2 → filter → keep → map → 20 → found!

//Once findFirst() has enough information, processing can stop.
//That's one of the powerful ideas behind streams:

//The pipeline describes a computation, and Java can execute it efficiently rather than blindly doing every operation on every element.



// forEach()
//Used when your goal is an action.

// toList()
//Used when your goal is to produce a result collection.

//A lambda is a way of expressing behavior..
//A Stream is a way of processing a sequence of data.

// But don't think "lambda = anonymous class"
//This is an important technical distinction.

//It is tempting to say:
//"A lambda is just a shorter anonymous class."
//That's not technically accurate.
//A lambda expression is a distinct Java language feature with different semantics.

//For learning purposes, this is a much better statement:
//A lambda provides an implementation for the single abstract method of a functional interface, using concise syntax.
//That's precise and still intuitive.

// A functional interface defines one piece of behavior; a lambda supplies that behavior.
// Streams use these behaviors to process data through a pipeline.



// Why is "one abstract method" important?
//  Imagine:
//interface Calculator {
//    int calculate(int a, int b);
//    int subtract(int a, int b);
//}

//Now you write:
//Calculator c =
//        (a, b) -> a + b;

//Java would have to ask:
//"Is this implementation for calculate() or subtract()?"
//That's why a lambda requires a functional interface.




// Optional is a box that may contain a value.
// For example:
// Optional<String> name =
//        Optional.of("Sam");
// There is no null inside the box.

//The box itself represents:
//VALUE PRESENT
//or:
//VALUE ABSENT
//Because now the caller has to think about the possibility of absence.

// Optional
//   │
//   └── if a value exists
//             │
//             ▼
//         Consumer<T>
//             │
//             ▼
//        do something
//This is another place where functional interfaces + lambdas show up.

//You could write:
//name.ifPresent(value ->
//        System.out.println(value));