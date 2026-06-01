import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ExecutorService is a high-level thread manager.

//  Instead of manually creating threads:
//  new Thread(...)

//  you submit tasks to a pool.

//  The pool decides:

//  when to create threads
//  reuse threads
//  schedule tasks
//  optimize performance

public class ExecutorServiceDemo {

    // use 10 threads to print 100 numbers

    public static void main(String[] args) {

        //  ExecutorService executorService = Executors.newFixedThreadPool(10);
        //  fixed size threads run simultaneously
        //  Remaining tasks wait in a queue.
        //  this is much more efficient, backend servers work like this

        ExecutorService executorService = Executors.newCachedThreadPool();
        // creates threads as needed or dynamic threads
        // reuses idle threads
        // can create MANY threads
        // Better for short async tasks

        for (int i = 0; i < 100; i++) {
            NumberPrinter numberPrinter = new NumberPrinter(i);
            executorService.submit(numberPrinter); // Submits task to pool, execute this runnable when possible
        }

       executorService.shutdown();
        // tells executor no more new tasks
        // existing tasks continue
        // pool shuts down gracefully afterward
        // Without this:
        // your application may never terminate.

    }

}


// Because thread scheduling is controlled by:
// JVM
// OS scheduler
// CPU timing

// Multithreading is NON-DETERMINISTIC.
// Threads execute randomly

// Architecture Difference
// Manual Threading
// Task → New Thread → Execute

// Thread Pool
// Task → Queue → Reusable Worker Threads
// Much better architecture.