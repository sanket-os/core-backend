//public class Client {
//
//    public static void main(String[] args) {
//
//        Store store = new Store(10);
//
//        for (int i = 0; i < 8; i++)
//        {
//            Producer producer = new Producer(store);
//            new Thread(producer).start();
//        }
//
//        for (int i = 0; i < 20; i++)
//        {
//            Consumer consumer = new Consumer(store);
//            new Thread(consumer).start();
//        }
//
//    }
//
//}


//
//package semaphores;

import java.util.concurrent.Semaphore;

/*
 * Driver program.
 */
public class Client {

    public static void main(String[] args)
            throws InterruptedException {

        /*
         * Buffer capacity = 5
         */
        Store store = new Store(5);

        /*
         * empty = available slots
         *
         * Initially buffer is empty.
         *
         * [ ][ ][ ][ ][ ]
         *
         * Therefore:
         *
         * empty = 5
         */
        Semaphore empty = new Semaphore(5);

        /*
         * full = occupied slots
         *
         * Initially buffer contains nothing.
         *
         * Therefore:
         *
         * full = 0
         */
        Semaphore full = new Semaphore(0);

        /*
         * Binary semaphore.
         *
         * Works like a lock.
         *
         * Only one thread can enter critical
         * section at a time.
         */
        Semaphore mutex = new Semaphore(1);

        Thread producer1 =
                new Thread(
                        new Producer(empty, full, mutex, store),
                        "Producer-1"
                );

        Thread producer2 =
                new Thread(
                        new Producer(empty, full, mutex, store),
                        "Producer-2"
                );

        Thread consumer1 =
                new Thread(
                        new Consumer(empty, full, mutex, store),
                        "Consumer-1"
                );

        Thread consumer2 =
                new Thread(
                        new Consumer(empty, full, mutex, store),
                        "Consumer-2"
                );

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();

        /*
         * Let system run for 10 seconds.
         */
        Thread.sleep(10_000);

        /*
         * Gracefully stop threads.
         */
        producer1.interrupt();
        producer2.interrupt();

        consumer1.interrupt();
        consumer2.interrupt();

//      This is the modern way to stop worker threads gracefully.
//      The thread:
//      Receives the interruption request.
//      Finishes its current work.
//      Exits the loop.
//      Terminates cleanly.

        System.out.println("\nApplication Stopped");
    }
}


// Mental Model
// Think of:
// interrupt()
// as:
// "Please stop when it is safe to stop."

//Not: "Die immediately."

// Common Thread Lifecycle with Interrupt
// start()
//   ↓
// running
//   ↓
// sleep()/wait()/join()/acquire()
//   ↓
// interrupt()
//   ↓
// InterruptedException
//   ↓
// cleanup
//   ↓
// thread exits