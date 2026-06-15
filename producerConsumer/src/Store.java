//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class Store {
//
//    int maxSize;
//    List<Object> items;
////    BlockingQueue<Object> items;
//
//    Lock lock;
//
//    public Store(int maxSize)
//    {
//        this.maxSize = maxSize;
//        this.items = new ArrayList<>();
////        this.items = new ArrayBlockingQueue<>(maxSize);
//        this.lock = new ReentrantLock();
//    }
//
//
//    public void addItem(Object object) {
//        lock.lock();
//        items.add(object);
//        lock.unlock();
//        System.out.println("Producer produced an item. size=" + items.size());
//    }
//
//    public void removeItem()
//    {
//        lock.lock();
//        items.remove(items.size() - 1);
////        items.poll();
//        lock.unlock();
//        System.out.println("Consumer consumed an item. size=" + items.size());
//    }
//
//}
//


// But synchronized is intentionally simple.
// Sometimes we want more control.

// Examples:
// Try to get a lock without waiting forever. Without unlock we are stuck in locked thread
// Wait only for 5 seconds.
// Fair locking.
// Explicit lock/unlock control.
// That's where ReentrantLock comes in.

// Suppose:
// public void methodA() {
//    lock.lock();
//
//    methodB();
//
//    lock.unlock();
// }

// and:

// public void methodB() {
//    lock.lock();
//
//    lock.unlock();
//}

// Same thread enters both methods.

// Without reentrancy:
// methodA holds lock
// methodB tries lock again
// deadlock

// With ReentrantLock:
// Same thread already owns lock
// Allowed to enter again

// This is called reentrancy.

// tryLock() -
// if (lock.tryLock()) {
//
//    try {
//        // do work
//    }
//    finally {
//        lock.unlock();
//    }
//
// }
// else {
//     System.out.println("Could not get lock");
// }

// Timed Lock -
// lock.tryLock(5, TimeUnit.SECONDS);

// Meaning:
// Wait at most 5 seconds.

// Then:
// success or failure

// Fair Lock -
// Normally:
// new ReentrantLock() is not fair.

//A newly arriving thread may get the lock before older waiting threads.
//If fairness matters:
//new ReentrantLock(true);
//This roughly behaves like:
//First Come
//First Served


// BlockingQueue -

// thread-safe queue that already handles all the work of semaphores - (empty, full, mutex)
// put()
// queue.put(item);
// if it is not full otherwise wait

// take()
// queue.take();
// if it is not empty otherwise wait

// Why do developers love BlockingQueue?
// Because it internally handles:
// ✅ Thread safety
// ✅ Waiting producers
// ✅ Waiting consumers
// ✅ Memory visibility
// ✅ Locking

// Used in real world applications
// It's simpler, safer, and less error-prone.



//package semaphores;

import java.util.ArrayList;
import java.util.List;

/*
 * Shared buffer between producers and consumers.
 *
 * Producers add items.
 * Consumers remove items.
 *
 * IMPORTANT:
 * Store itself is NOT thread-safe.
 *
 * We rely on the mutex semaphore to ensure that
 * only one thread can modify the buffer at a time.
 */
public class Store {

    private final int maxSize;

    private final List<Object> items;

    public Store(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }

    public void addItem(Object item) {

        items.add(item);

        System.out.println(
                Thread.currentThread().getName()
                        + " PRODUCED item. Buffer size = "
                        + items.size()
        );
    }

    public void removeItem() {

        items.remove(items.size() - 1);

        System.out.println(
                Thread.currentThread().getName()
                        + " CONSUMED item. Buffer size = "
                        + items.size()
        );
    }

    public int getMaxSize() {
        return maxSize;
    }
}

//  Quick Comparison
// Feature	            synchronized	ReentrantLock	Semaphore	            BlockingQueue
// Mutual exclusion	        ✅	            ✅	        Binary semaphore only	Internal
// Multiple permits	        ❌	            ❌	        ✅	                    Internal
// tryLock	                ❌	            ✅	        N/A	                    N/A
// Fair mode	            ❌	            ✅	        ✅	                    Some implementations
// Producer-Consumer	  Manual	        Manual	    Manual	                ✅ Built-in
// Modern usage	          Common	     Advanced        Coordination	        Very common
//                                       locking