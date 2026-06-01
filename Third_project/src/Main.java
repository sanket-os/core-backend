//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("hello world");

        Thread thread = Thread.currentThread(); // manual thread creation

        System.out.println(Thread.currentThread().getName());
//      In Java, even main() runs inside a thread called: main

        for (int i = 0; i < 100; i++) {
            NumberPrinter numberPrinter = new NumberPrinter(i); // each iteration creates a task obj
            //  numberPrinter.run();


            Thread newThread = new Thread(numberPrinter); // this creates a new OS level thread
//            newThread.run();
            //  This is just a normal method call.
            //  NO new thread is created.
            //  Everything runs on: main thread

            newThread.start();
//            This:
//            Creates a new thread
//            JVM asks OS for a new thread
//            OS schedules it independently
//            That thread internally calls run()
        }

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