public class NumberPrinter implements Runnable {

    private final int i;

    public NumberPrinter(int i) {

        this.i = i;
    }
//  each object stores one number & it remembers that number

    @Override
    public void run() {
        System.out.println(
                "Thread: "
                + Thread.currentThread().getName()
                + " prints number "
                + i
        );
    }
//  this is the code executed by the thread

}


//  Runnable is a functional interface in Java.
//
//  It contains only one method:
//  void run();
//
//  Your class is saying:
//  "This class contains a task that can be executed by a thread."