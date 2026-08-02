import java.util.List;

public class GenericsPlayground {

    /*
     * ------------------------------------------------------------
     * Generic Class
     *
     * T represents "some type"
     * decided later by the compiler.
     * ------------------------------------------------------------
     */
//    public static class Box<T> {
//
//        private T value;
//
//        public void set(T value) {
//            this.value = value;
//        }
//
//        public T get() {
//            return value;
//        }
//    }

    /*
     * ------------------------------------------------------------
     * Multiple Generic Types
     * ------------------------------------------------------------
     */
//    public static class Pair<K, V> {
//
//        private final K key;
//        private final V value;
//
//        Pair(K key, V value) {
//            this.key = key;
//            this.value = value;
//        }
//
//        public K getKey() {
//            return key;
//        }
//
//        public V getValue() {
//            return value;
//        }
//    }

    /*
     * ------------------------------------------------------------
     * Generic Method
     *
     * Compiler decides T.
     * ------------------------------------------------------------
     */
    public static <T> T identity(T value) {
        return value;
    }

    /*
     * ------------------------------------------------------------
     * Bounded Generic
     *
     * T must extend Number.
     * ------------------------------------------------------------
     */
   public  static <T extends Number> double square(T number) {

        return number.doubleValue() * number.doubleValue();

    }

    /*
     * ------------------------------------------------------------
     * Producer
     *
     * PECS:
     * Producer Extends
     * List produces Numbers.
     *
     * Safe to read.
     *
     * Cannot add.
     * ------------------------------------------------------------
     */
    public static void printNumbers(List<? extends Number> list) {

        for (Number n : list) {
            System.out.println(n);
        }

        // list.add(5);
        // Compile Error
    }

    /*
     * ------------------------------------------------------------
     * Consumer
     *
     * PECS:
     * Consumer Super
     * List consumes Integers.
     *
     * Safe to add.
     * Reading returns Object.
     * ------------------------------------------------------------
     */
    public static void addIntegers(List<? super Integer> list) {

        list.add(100);
        list.add(200);

        Object obj = list.get(0);

        System.out.println("First value = " + obj);

    }
}