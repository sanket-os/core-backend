package adderSubtractor;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

//    int i = 0;
//    AtomicInteger i = new AtomicInteger();

//    Locking has overhead and can reduce scalability under heavy contention
//    because of normal integer and thread may block

//    Thread-safe counter ✅
//    Lock-free updates ✅
//    High performance ✅

//    Internally, AtomicInteger uses special CPU instructions called atomic compare-and-swap (CAS) operations.
//    This makes it thread safe and avoids race condition

//    For compound operations, you may need:
// compareAndSet()
// ReentrantLock
// synchronized
// depending on the situation.

//    Pros:
// No explicit locks
// Very fast
// Excellent for counters

//  Cons:
// Only works for atomic operations
// Not suitable for large critical sections



//    int i = 0;

//    void increment() {
//        synchronized (this) {
//            i  += 1;
//        }
//    }
//
//    synchronized void decrement() {
//        i -= 1;
//    }

    // better design
    private int i = 0;

    public synchronized void increment() {
        i++;
    }

    public synchronized void decrement() {
        i--;
    }

    public synchronized int getValue() {
        return i;
    }

}
