import java.util.concurrent.Callable;
import java.util.List;

public class SumMultiThreadCallable implements Callable<Long> {

    private final int start;
    private final int end;
    private final List<Integer> arr;

    public SumMultiThreadCallable(List<Integer> arr, int start, int end) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    public Long call() throws Exception {
        Long sum = 0L;

        for(int i = start; i < end; i++) {
            sum = sum + arr.get(i);
        }
        System.out.println("Sum calculated by " + Thread.currentThread().getName() + " " + sum);

        return sum;
    }


}