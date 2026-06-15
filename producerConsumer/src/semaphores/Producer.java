package semaphores;

import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

    Semaphore producerSemaphore;

    Semaphore consumerSemaphore;

    Semaphore mutex = new Semaphore(1);

    Store store;

    public Producer(Semaphore producerSemaphore, Semaphore consumerSemaphore, Store store) {
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                producerSemaphore.acquire();
                store.addItem(new Object());
                consumerSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
