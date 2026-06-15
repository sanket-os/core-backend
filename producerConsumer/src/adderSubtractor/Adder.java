package adderSubtractor;

public class Adder implements Runnable {

    Counter counter;

    public Adder(Counter counter) {
        this.counter = counter;
    }

//    @Override
//    public void run() {
//        for (int i = 0; i < 10000; i++)
//        {
////            counter.i += 1;
//            counter.i.incrementAndGet();
//
////            counter.i += 1;
//        }
//    }

@Override
public void run() {
    for (int i = 0; i < 10000; i++) {
//        synchronized (counter) {
//            counter.i += 1;
//        }
        counter.increment();
    }
}

}

// Without synchronization:
//
// Adder --------\
//                \
//                 -> counter.i
//                /
// Subtractor ---/
//
// Both can modify simultaneously.
// Leads to race condition where both threads modify the data.
// Danger.


// With synchronization:
//
//                LOCK(counter)
//
// Adder --------\
//                \
//                 -> counter.i
//                /
// Subtractor ---/
//
//                UNLOCK(counter)
//
// Only one thread can enter at a time.
// Avoids race condition and ambiguous state change
// Safe.
