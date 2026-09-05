import java.util.concurrent.Semaphore;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        Store store = new Store(5);

        Semaphore empty = new Semaphore(5);
        Semaphore full = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);

        Thread producer1 = new Thread(
                new Producer(full, empty, mutex, store),
                "Producer-1"
        );

        Thread producer2 = new Thread(
                new Producer(full, empty, mutex, store),
                "Producer-2"
        );

        Thread consumer1 = new Thread(
                new Consumer(full, empty, mutex, store),
                "Consumer-1"
        );

        Thread consumer2 = new Thread(
                new Consumer(full, empty, mutex, store),
                "Consumer-2"
        );

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();

        Thread.sleep(1000);

        producer1.interrupt();
        producer2.interrupt();

        consumer1.interrupt();
        consumer2.interrupt();

        System.out.println("\nApplication Stopped");

    }

}
