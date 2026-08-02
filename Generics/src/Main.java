import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



public class Main {

    public static void main(String[] args) {

        /*
         * ------------------------------------------------------------
         * Generic Class
         * ------------------------------------------------------------
         */

        Box<String> stringBox = new Box<>();

        stringBox.set("Hello Generics");

        System.out.println(stringBox.get());


        Box<Integer> intBox = new Box<>();

        intBox.set(50);

        System.out.println(intBox.get());



        /*
         * ------------------------------------------------------------
         * Generic Method
         * ------------------------------------------------------------
         */

        String language = GenericsPlayground.identity("Java");

        Integer year = GenericsPlayground.identity(2026);

        System.out.println(language);

        System.out.println(year);



        /*
         * ------------------------------------------------------------
         * Multiple Generic Types
         * ------------------------------------------------------------
         */

        Pair<String, Integer> age =

                new Pair<>("Sam", 35);

        System.out.println(

                age.getKey()

                        + " -> "

                        + age.getValue()

        );



        /*
         * ------------------------------------------------------------
         * Type Inference
         * ------------------------------------------------------------
         */

        List<String> names = new ArrayList<>();

        names.add("Alice");
        names.add("Bob");

        for (String name : names) {

            System.out.println(name);

        }



        /*
         * ------------------------------------------------------------
         * Generic Method with Bound
         * ------------------------------------------------------------
         */

        System.out.println(GenericsPlayground.square(12));

        System.out.println(GenericsPlayground.square(5.5));



        /*
         * ------------------------------------------------------------
         * Wildcards
         * ------------------------------------------------------------
         */

        List<Integer> numbers =

                List.of(10, 20, 30);

        GenericsPlayground.printNumbers(numbers);

        List<Number> destination =

                new ArrayList<>();

        GenericsPlayground.addIntegers(destination);

        System.out.println(destination);



        /*
         * ------------------------------------------------------------
         * Lambda + Type Inference
         * ------------------------------------------------------------
         */

        Function<String, Integer> length =
//                String::length;
                s -> s.length();

        System.out.println(

                length.apply("Generics")

        );



        /*
         * ------------------------------------------------------------
         * Streams + Generic Inference
         * ------------------------------------------------------------
         */

        List<Integer> lengths =

                names.stream()

                        .map(String::length)

                        .toList();

        System.out.println(lengths);



        /*
         * ------------------------------------------------------------
         * Runtime Type Erasure
         * ------------------------------------------------------------
         */

        List<String> a = new ArrayList<>();

        List<Integer> b = new ArrayList<>();

        System.out.println(

                a.getClass()

        );

        System.out.println(

                b.getClass()

        );

        System.out.println(

                a.getClass() == b.getClass()

        );

    }

    }




// Output -

//Hello Generics
//50
//Java
//2026
//Sam -> 35
//Alice
//Bob
//144.0
//30.25
//10
//20
//30
//First value = 100
//[100, 200]
//8
//[5, 3]
//class java.util.ArrayList
//class java.util.ArrayList
//true



// What are Java Generics?

// Generics are a compile-time type-safety feature that allows classes, interfaces,
// and methods to operate on different types while preserving type safety. They enable
// code reuse, eliminate most explicit casts, and let the compiler detect type mismatches
// before the program runs. Internally, Java implements generics using type erasure, so
// generic type information is primarily a compile-time construct rather than a runtime one.


// Type Inference

//Compiler looks at
//Assignment
//Method arguments
//Return type
//Lambda context

//and figures out the generic type automatically.

// Diamond Operator
//Instead of
//new ArrayList<String>()
//Modern Java
//new ArrayList<>()
//Compiler infers the type.

// Type Safety
//Compiler prevents
//List<String> names = new ArrayList<>();
//names.add(10);
//Compile error.
//Instead of
//Runtime error.


// Type Erasure
//During compilation
//List<String>
//becomes approximately

//List
//The JVM does not know String.
//Only the compiler does.


// T = Type
// E = Element
// K = Key
// V = Value
// R = Result


// You now understand:
//✅ Why generics exist
//✅ Generic classes
//✅ Generic methods
//✅ Type parameters (T, K, V, E, R)
//✅ Type safety
//✅ Type inference
//✅ Diamond operator (<>)
//✅ Bounded generics (extends)
//✅ Wildcards (?, ? extends, ? super)
//✅ PECS (Producer Extends, Consumer Super)
//✅ Type erasure
//✅ Why arrays and generics behave differently
//✅ How lambdas, streams, Optional, Function, and CompletableFuture all rely on generics
//✅ How the compiler "thinks" when inferring types



// One Last Mental Model
//Whenever you see generics, imagine two worlds.

//Compile Time 🌍
//
//The compiler knows everything.
//List<String>
//↓
//Only Strings
//↓
//Safe
//↓
//Checks types
//↓
//Infers types
//↓
//Rejects mistakes

//Runtime 🌍
//The JVM only sees
//List
//
//The compiler has already done its job.