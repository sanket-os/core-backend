import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

    private final Semaphore full;
    private final Semaphore empty;
    private final Semaphore mutex;

    private final Store store;

    public Producer(Semaphore full, Semaphore empty, Semaphore mutex, Store store) {
        this.full = full;
        this.empty = empty;
        this.mutex = mutex;
        this.store = store;
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                empty.acquire();
                mutex.acquire();
                store.addItem(new Object());
                mutex.release();
                full.release();

                Thread.sleep(500);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
