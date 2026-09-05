import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumCalculation {

    public static void main(String[] args) {

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cores: " + cores);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> arr = new ArrayList<>();
        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            arr.add(i % 3);
        }

        int chunkSize = 100000 / cores;

        for (int chunk = 0; chunk < cores; chunk++) {
            SumMultiThreadCallable callable = new SumMultiThreadCallable(
                    arr,
                    chunk*chunkSize,
                    (chunk+1)*chunkSize);

            Future<Long> sum = executorService.submit(callable);
            futures.add(sum);
        }

        Long ans = futures.stream()
                        .map(future -> {
                            try {
                                return future.get();
                            } catch (InterruptedException | ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        })
                                .reduce(0L, Long :: sum);

        System.out.println(ans);

        executorService.shutdown();

    }

}
