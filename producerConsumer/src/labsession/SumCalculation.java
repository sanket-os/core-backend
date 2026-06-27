package labsession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumCalculation {

    /*
     * Main class that coordinates the entire calculation.
     *
     * Think of this class as a project manager.
     *
     * It does NOT calculate the sum itself.
     *
     * Instead it:
     *
     * 1. Creates the data
     * 2. Splits work into chunks
     * 3. Creates tasks
     * 4. Gives tasks to worker threads
     * 5. Waits for results
     * 6. Combines results
     */

    public static void main(String[] args) {

        /*
         * Ask the JVM:
         *
         * "How many processors are available?"
         *
         * Example outputs:
         *
         * 4
         * 8
         * 12
         * 16
         *
         * This value helps us decide how many chunks
         * we should divide our work into.
         */
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cores "+ cores);

        /*
         * Create a thread pool containing 10 worker threads.
         *
         * Thread pools are preferred over creating
         * new Thread() objects manually.
         *
         * Why?
         *
         * Creating threads is expensive.
         *
         * A thread pool creates workers once
         * and reuses them.
         *
         * Think:
         *
         * Manager
         *   ├── Worker 1
         *   ├── Worker 2
         *   ├── Worker 3
         *   ...
         *   └── Worker 10
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        /*
         * ArrayList that will contain our numbers.
         *
         * Example:
         *
         * [0,1,2,0,1,2,0,1,2...]
         */
        List<Integer> arr = new ArrayList<>();

        /*
         * Stores Future objects.
         *
         * Each Future represents:
         *
         * "A result that will be available later."
         *
         * We don't have the sums yet.
         *
         * We only have promises that the sums
         * will arrive eventually.
         */
        List<Future<Long>> futures = new ArrayList<>();
//        futures is a List, and List has a stream() method.

        /*
         * Populate the list.
         *
         * i % 3 generates:
         *
         * 0
         * 1
         * 2
         * 0
         * 1
         * 2
         * ...
         */
        for (int i = 0; i < 100000; i++)
        {
            arr.add(i % 3);
        }

        /*
         * Determine how much work each task should do.
         *
         * Example:
         *
         * Array size = 100000
         * Cores = 8
         *
         * chunkSize = 12500
         *
         * Therefore:
         *
         * Task 1 -> 12500 elements
         * Task 2 -> 12500 elements
         * ...
         */
        int chunkSize = 100000 / cores;

        /*
         * Create one task per CPU core.
         *
         * Example:
         *
         * chunk = 0
         * start = 0
         * end = 12500
         *
         * chunk = 1
         * start = 12500
         * end = 25000
         *
         * chunk = 2
         * start = 25000
         * end = 37500
         */
        for (int chunk = 0; chunk < cores; chunk++)
        {
            /*
             * Create a task responsible for
             * summing a specific section
             * of the array.
             */
            SumMultiThreadCallable callable = new SumMultiThreadCallable(
                    arr,
                    chunk*chunkSize,
                    (chunk+1)*chunkSize);

            /*
             * Submit the task to the thread pool.
             *
             * IMPORTANT:
             *
             * submit() does NOT execute the task
             * immediately on this thread.
             *
             * Instead:
             *
             * 1. Task enters queue
             * 2. Worker thread picks it up
             * 3. Worker executes call()
             * 4. Result is stored in Future
             */

            Future<Long> sum = executorService.submit(callable);
            /*
             * Save the Future so we can retrieve
             * the result later.
             */
            futures.add(sum);
        }

        // i = 0 -> 0 -> 100000
        // i = 1 -> 100000 -> 200000

        /*
         * At this point:
         *
         * Main thread has submitted all tasks.
         *
         * Worker threads are calculating sums
         * in parallel.
         */

        /*
         * Process all Future objects.
         *
         * For each Future:
         *
         * future.get()
         *
         * retrieves the result returned by call().
         */
        Long ans = futures.stream()
                /*
                 * Convert:
                 *
                 * Future<Long>
                 *
                 * into
                 *
                 * Long
                 */
                .map(future -> {
                    try {

                        /*
                         * Wait for task completion.
                         *
                         * If task is already done:
                         *
                         * return result immediately.
                         *
                         * If task is still running:
                         *
                         * current thread waits.
                         */
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        /*
                         * InterruptedException:
                         *
                         * Current thread was interrupted
                         * while waiting.
                         *
                         * ExecutionException:
                         *
                         * The worker thread threw
                         * an exception inside call().
                         */
                        throw new RuntimeException(e);
                    }
        })
                /*
                 * Combine all partial sums.
                 *
                 * Equivalent to:
                 *
                 * long total = 0;
                 *
                 * for(Long value : values)
                 *     total += value;
                 */

                .reduce(0L, Long::sum);


        /*
         * Print final result.
         */
        System.out.println(ans);

        /*
         * Tell the thread pool:
         *
         * "No more tasks are coming."
         *
         * Existing tasks are allowed to finish.
         *
         * New submissions are rejected.
         */
        executorService.shutdown();

    }

}
//A Stream is a way to process a collection of data.

//List
// |
// v
//Stream
// |
// v
//Operations
// |
// v
//Result

//Full Stream Pipeline
//
//Suppose:
//
//Future1 -> 100
//Future2 -> 200
//Future3 -> 300
//Step 1
//futures.stream()
//
//Produces:
//
//Future1
//Future2
//Future3
//Step 2
//.map(future -> future.get())
//
//Produces:
//
//100
//200
//300
//Step 3
//.reduce(0L, Long::sum)
//
//Produces:
//
//600

//Future<Long>
//        |
//        v
//map(future -> future.get())
//        |
//        v
//Long
//        |
//        v
//reduce(sum)
//        |
//        v
//Single Long result
