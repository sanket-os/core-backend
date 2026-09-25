import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Taylor");
        System.out.println(stringBox.get());

        Box<Integer> integerBox = new Box<>();
        integerBox.set(100);
        System.out.println(integerBox.get());


        String language = GenericsPlayground.identity("Yo");
        Integer year = GenericsPlayground.identity(70);
        System.out.println(language);
        System.out.println(year);


        Pair<String, Integer> age = new Pair<>("Jane", 24);
        System.out.println(age.getKey() + " -> " + age.getValue());

        List<String> names = new ArrayList<>();
        names.add("Nancy");
        names.add("Jonathan");

        for (String name : names) {
            System.out.println(name);
        }


        System.out.println(GenericsPlayground.square(11));
        System.out.println(GenericsPlayground.square(9.98));


        List<Integer> numbers = List.of(11, 22, 33);
        GenericsPlayground.printNumber(numbers);

        List<Number> destination = new ArrayList<>();
        GenericsPlayground.addNumber(destination);
        System.out.println(destination);


        Function<String, Integer> length = s -> s.length();
        System.out.println(length.apply("Harrington"));


        List<Integer> lengths = names.stream()
                .map(String :: length)
                .toList();
        System.out.println(lengths);


        List<String> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        System.out.println(a.getClass());
        System.out.println(b.getClass());

        System.out.println(a.getClass() == b.getClass());

    }

}