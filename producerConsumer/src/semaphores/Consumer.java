package semaphores;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    Semaphore producerSemaphore;

    Semaphore consumerSemaphore;

    Semaphore mutex = new Semaphore(1);

    Store store;

    public Consumer(Semaphore producerSemaphore, Semaphore consumerSemaphore, Store store) {
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumerSemaphore.acquire();
                store.removeItem();
                producerSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
