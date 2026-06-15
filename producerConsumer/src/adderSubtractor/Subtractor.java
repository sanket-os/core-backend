package adderSubtractor;

public class Subtractor implements Runnable{

    Counter counter;

    public Subtractor(Counter counter) {
        this.counter = counter;
    }

//    @Override
//    public void run() {
//        for (int i = 0; i < 10000; i++)
//        {
////            counter.i -= 1;
//            counter.i.decrementAndGet();
//
////            counter.i -= 1;
//
//
//        }
//    }

@Override
public void run() {
    for (int i = 0; i < 10000; i++) {
//        synchronized (counter) {
//            counter.i -= 1;
//        }
        counter.decrement();
    }
}

}
