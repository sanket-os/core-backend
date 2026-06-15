package adderSubtractor;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        Adder adder = new Adder(counter);
        Subtractor subtractor = new Subtractor(counter);

        Thread t1 = new Thread(adder);
        Thread t2 = new Thread(subtractor);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

//        Method	    Meaning
//        start()	    Create a new thread and begin execution
//        join()	    Wait for that thread to finish
//        sleep()	    Pause the current thread for some time
//        interrupt()	Request another thread to stop what it's doing

//        System.out.println(counter.i);
//        System.out.println(counter.i.get());

//        System.out.println(counter.i);

        System.out.println(counter.getValue());
//        This follows encapsulation.
    }

}
