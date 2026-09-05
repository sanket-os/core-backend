import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecService {

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++) {
            NumberPrinter numberPrinter = new NumberPrinter(i);
            executorService.submit(numberPrinter);
        }

        executorService.shutdown();

    }

}
