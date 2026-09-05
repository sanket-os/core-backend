import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Integer> list = List.of(2, 5, 1, 6, 90, 11);
        MergeSorter mergeSorter = new MergeSorter(list, executorService);

        Future<List<Integer>> listFuture = executorService.submit(mergeSorter);

        for (Integer i: listFuture.get()) {
            System.out.printf("%d ", i);
        }

        executorService.shutdown();

    }

}
