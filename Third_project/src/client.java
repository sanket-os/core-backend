import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        List<Integer> list = List.of(4, 7, 9, 1, 5, 2, 8);
//        MergeSorter mergeSorter = new MergeSorter(list);


        ExecutorService executorService = Executors.newFixedThreadPool(10);


//        List<Integer> sortedList = mergeSorter.sort();


        List<Integer> list = List.of(4, 7, 9, 1, 5, 2, 8);

//        List<Integer> list = List.of(4, 7, 9, 1, 5, 2, 8, 10, 11, 14, 12, 13);
        // insufficient number of threads for large input size
        // parents thread gets block on child thread execution

        MergeSorter mergeSorter = new MergeSorter(list, executorService);


        Future<List<Integer>> listFuture = executorService.submit(mergeSorter);


//        for (Integer i: sortedList) {
//            System.out.printf("%d ", i);
//        }


        for (Integer i: listFuture.get()) {
            System.out.printf("%d ", i);
        }

        executorService.shutdown();

    }

}
