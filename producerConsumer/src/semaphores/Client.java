package semaphores;

import java.util.concurrent.Semaphore;

public class Client {

    public static void main(String[] args) {

        Store store = new Store(5);
        Semaphore producerSemaphore = new Semaphore(5);
        Semaphore consumerSemaphore = new Semaphore(0);

        for (int i = 0; i < 8; i++)
        {
            Producer producer = new Producer(producerSemaphore, consumerSemaphore, store);
            new Thread(producer).start();
        }

        for (int i = 0; i < 20; i++)
        {
            Consumer consumer = new Consumer(producerSemaphore, consumerSemaphore, store);
            new Thread(consumer).start();
        }

    }

}
