import java.util.List;

public class GenericsPlayground {

   public static <T> T identity(T value) {
       return value;
   }

    public static <T extends Number> double square(T number) {
       return number.doubleValue() * number.doubleValue();
    }

    public static void printNumber(List<? extends Number> list) {
       for (Number n : list) {
           System.out.println(n);
       }
    }

    public static void addNumber(List<? super Number> list) {
       list.add(10);
       list.add(20);

       Object obj = list.get(1);
       System.out.println("Second value = " + obj);
    }

}
