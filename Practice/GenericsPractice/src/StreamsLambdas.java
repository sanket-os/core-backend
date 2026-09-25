import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.List;

public class StreamsLambdas {

    public static void main(String[] args) {

        Function<String, Integer> getLength = name -> name.length();

        int length = getLength.apply("Sam");
        System.out.println("Length of Sam: " + length);

        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 3 even? " + isEven.test(3));

        Consumer<String> printer =
                name -> System.out.println("Hello " + name);

        printer.accept("El");

        Supplier<String> messageSupplier =
                () -> "Hi from supplier";

        String message = messageSupplier.get();
        System.out.println(message);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> result =
                numbers.stream()
                        .filter(number -> number % 2 == 0)
                        .map(number -> number * 2)
                        .toList();

        System.out.println("Stream result: " + result);


        List<String> names = List.of("Alice", "Bob", "Samuel", "Joseph", "Kerry");

        List<String> longNames =
                names.stream()
                        .filter(name -> name.length() > 5)
                        .map(name -> name.toUpperCase())
                        .toList();

        System.out.println("Long names: " + longNames);


        System.out.println("Printing names:");
        names.stream()
                .forEach(name -> System.out.println(name));


        int sum = numbers.stream()
                .reduce(0,
                        (total, number) -> total + number);
        System.out.println("Sum: " + sum);
    }

}
