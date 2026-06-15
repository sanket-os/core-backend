//public class Consumer implements Runnable {
//
//    private final Store store;
//
//    public Consumer(Store store) {
//        this.store = store;
//    }
//
//    public void run()
//    {
//        while(true) {
//            synchronized (store) { // store.lock()
//                if (store.items.size() > 0)
//                {
//                    store.removeItem();
//                }
//            }
//            // store.unlock()
//        }
//    }
//
//}





//
//package semaphores;

import java.util.concurrent.Semaphore;

/*
 * Consumer removes items from the Store.
 */
public class Consumer implements Runnable {

    private final Semaphore empty;
    private final Semaphore full;
    private final Semaphore mutex;

    private final Store store;

    public Consumer(
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
                 * Wait until at least one item exists.
                 *
                 * If:
                 *
                 * full = 0
                 *
                 * Consumer blocks here.
                 */
                full.acquire();

                /*
                 * STEP 2
                 *
                 * Enter critical section.
                 */
                mutex.acquire();

                /*
                 * STEP 3
                 *
                 * Remove item from buffer.
                 */
                store.removeItem();

                /*
                 * STEP 4
                 *
                 * Leave critical section.
                 */
                mutex.release();

                /*
                 * STEP 5
                 *
                 * One slot became free.
                 *
                 * Increase empty slot count.
                 *
                 * This may wake up a waiting producer.
                 */
                empty.release();

                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}

// Semaphore: The Generalization of a Lock

// Lock is basically a 1-permit semaphore.

// A semaphore doesn't just say:
// 1 thread allowed

// It says:
// N threads allowed

// acquire() - Take a permit.
// release() - Return a permit. One waiting thread wakes up.

// Binary Semaphore
// Semaphore mutex = new Semaphore(1);
// Permits: 1

// This behaves almost like a lock.
// Only one thread can proceed.

// mutex - Protects the buffer.
// Only one thread modifies buffer at a time

// The Most Important Thing to Remember
// A semaphore is not protecting data.
// A semaphore is just a counter of permits.