import java.util.concurrent.Callable;

//  ExecutorService → manages threads
//  Runnable Returns: Nothing
//  Callable<T> → tasks that return a value
//  Future<T> → a placeholder for a result that will arrive later

public class NumberDoubler implements Callable<Integer> {

    private final int i;

    public NumberDoubler(int i) {
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        return i * 2;
    }

}
