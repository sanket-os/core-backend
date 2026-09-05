import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    private final Semaphore full;
    private final Semaphore empty;
    private final Semaphore mutex;

    private final Store store;

    public Consumer(Semaphore full, Semaphore empty, Semaphore mutex, Store store) {
        this.full = full;
        this.empty = empty;
        this.mutex = mutex;
        this.store = store;
    }

    @Override
    public void run() {

        try {
            while(!Thread.currentThread().isInterrupted()) {
                full.acquire();

                mutex.acquire();
                store.removeItem();
                mutex.release();

                empty.release();
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
