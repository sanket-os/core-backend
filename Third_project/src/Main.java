import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        System.out.println("hello world");
//
//        Thread thread = Thread.currentThread(); // manual thread creation
//
//        System.out.println(Thread.currentThread().getName());
////      In Java, even main() runs inside a thread called: main
//
//        for (int i = 0; i < 100; i++) {
//            NumberPrinter numberPrinter = new NumberPrinter(i); // each iteration creates a task obj
//            //  numberPrinter.run();
//
//
//            Thread newThread = new Thread(numberPrinter); // this creates a new OS level thread
////            newThread.run();
//            //  This is just a normal method call.
//            //  NO new thread is created.
//            //  Everything runs on: main thread
//
//            newThread.start();
//            This:
//            Creates a new thread
//            JVM asks OS for a new thread
//            OS schedules it independently
//            That thread internally calls run()


            NumberDoubler numberDoubler = new NumberDoubler(100);
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            Future<Integer> integerFuture =  executorService.submit(numberDoubler);
//        Main thread hands the task to the executor.
//        Executor Places task in queue:
//        Task Queue
//        │
//        └── NumberDoubler(100)
//        One worker thread picks it up: and executes: return 100 * 2; 200


//            while(true) {
//                System.out.println(integerFuture.isDone());
//                Thread.sleep(100);
//            }

        // this defines the state of our task
        while(integerFuture.state() != Future.State.SUCCESS) {
            System.out.println(integerFuture.isDone());

//            future.state() - Possible states:
//            RUNNING
//            SUCCESS
//            FAILED
//            CANCELLED

//           For modern java future.state() is better than .isDone()
//           because you know why the task finished.
        }


//        Without Future:
//        Integer result = task.call();
//        Main thread would execute task itself.
//        No concurrency.

//        With Future:
//        Future<Integer> future = executor.submit(task);
//        Task runs elsewhere.
//        Main thread remains free. This is the real power of Future.
        System.out.println(integerFuture.get());

//        Callable = Work that returns a value
//        ExecutorService = Runs the work
//        Future = Handle to the future result

        // Blocking call -> Main thread won't proceed until
        // the thread completes execution for integer future.
        executorService.shutdown();

    }
}

//  Each thread requires:

//  Stack memory
//  OS scheduling
//  Context switching
//  CPU management

//  This is expensive
//  Creating too many threads can crash systems.
//  That's why we use thread pools

//  Instead of manual threads modern java mostly uses:
//  ExecutorService
//  ForkJoinPool
//  CompletableFuture
//  Virtual Threads (modern Java)
//  Reactive systems


//      MODERN JAVA EXAMPLE
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        System.out.println(
//                "Main Thread: "
//                        + Thread.currentThread().getName()
//        );
//
//        /*
//         * Creates a Virtual Thread Executor.
//         *
//         * Virtual Threads are lightweight threads introduced in Java 21.
//         *
//         * Unlike traditional platform threads:
//         *
//         * - very cheap to create
//         * - can scale to thousands or millions
//         * - managed mostly by the JVM
//         * - ideal for I/O-heavy applications
//         *    The JVM efficiently schedules them on a small number of real OS threads.

//         * Modern Java applications increasingly use Virtual Threads.
//         */
//        try (ExecutorService executor =
//                     Executors.newVirtualThreadPerTaskExecutor()) {
//
//            for (int i = 0; i < 100; i++) {
//
//                int number = i;
//
//                /*
//                 * submit() accepts a Runnable task.
//                 *
//                 * Instead of creating:
//                 *
//                 * new NumberPrinter(...)
//                 *
//                 * we use a lambda expression.
//                 *
//                 * Lambdas are the modern way to implement
//                 * small functional interfaces like Runnable.
//                 */
//                executor.submit(() -> {
//
//                    System.out.println(
//                            Thread.currentThread().getName()
//                                    + " prints "
//                                    + number
//                    );
//
//                });
//            }
//
//        } // executor automatically closes here
//
//        /*
//         * No need for shutdown().
//         *
//         * try-with-resources automatically calls close()
//         * on the ExecutorService.
//         *
//         * Cleaner and less error-prone.
//         */
//    }
//}


//      MODERN FIXED THREAD POOL EXAMPLE
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        try (ExecutorService executor =
//                     Executors.newFixedThreadPool(10)) {
//
//            for (int i = 0; i < 100; i++) {
//
//                int number = i;
//
//                executor.submit(() -> {
//
//                    System.out.println(
//                            Thread.currentThread().getName()
//                                    + " prints "
//                                    + number
//                    );
//
//                });
//            }
//        }
//    }
//}