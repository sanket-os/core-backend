//public class Producer implements Runnable {
//
//    private final Store store;
//
//    public Producer(Store store) {
//        this.store = store;
//    }
//
//    public void run() {
//        while (true) {
//            synchronized (store) {
//                if (store.items.size() < store.maxSize) {
//                    store.addItem(new Object());
//                }
//            }
//
//        }
//    }
//
//}

//package semaphores;

import java.util.concurrent.Semaphore;

/*
 * Producer creates items and puts them into the Store.
 *
 * We use three semaphores:
 *
 * empty -> number of empty slots available
 * full  -> number of filled slots available
 * mutex -> protects the critical section
 */
public class Producer implements Runnable {

    private final Semaphore empty;
    private final Semaphore full;
    private final Semaphore mutex;

    private final Store store;

    public Producer(
            Semaphore empty,
            Semaphore full,
            Semaphore mutex,
            Store store
    ) {
        this.empty = empty;
        this.full = full;
        this.mutex = mutex;
        this.store = store;
    }

    @Override
    public void run() {

        try {

            while (!Thread.currentThread().isInterrupted()) {

                /*
                 * STEP 1
                 *
                 * Before producing, make sure there is an empty slot.
                 *
                 * If buffer is full:
                 *
                 * empty = 0
                 *
                 * acquire() blocks this producer thread.
                 */
                empty.acquire();

                /*
                 * STEP 2
                 *
                 * Enter critical section.
                 *
                 * Only one thread at a time can modify
                 * the shared buffer.
                 */
                mutex.acquire();

                /*
                 * STEP 3
                 *
                 * Actually add item to buffer.
                 */
                store.addItem(new Object());

                /*
                 * STEP 4
                 *
                 * Leave critical section.
                 *
                 * Other producers/consumers can now
                 * access the buffer.
                 */
                mutex.release();

                /*
                 * STEP 5
                 *
                 * We just added one item.
                 *
                 * Increase count of available items.
                 *
                 * This may wake up a waiting consumer.
                 */
                full.release();

                Thread.sleep(500);
            }

        } catch (InterruptedException e) {

            /*
             * Restore interrupted status.
             * Good practice in modern Java.
             */
            Thread.currentThread().interrupt();
        }
    }
}