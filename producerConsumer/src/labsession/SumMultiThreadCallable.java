package labsession;
import java.util.List;
import java.util.concurrent.Callable;

/*
 * Finds the sum of a subarray.
 */
public class SumMultiThreadCallable implements Callable<Long> {

    private final List<Integer> arr;
    private final int start;
    private final int end;

    public SumMultiThreadCallable(List<Integer> arr, int start, int end)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

//    @Override
    public Long call() throws Exception
    {
        Long sum = 0L;

        for (int i = start; i < end; i++)
        {
            sum = sum + arr.get(i);
        }

        System.out.println("Sum calculated by " + Thread.currentThread().getName() + " " + sum);
        return sum;
    }

}


// WEB CRAWLER

// Start URL
//       │
//       ▼
// Download page
//       │
//       ▼
// Extract URLs
//       │
//       ▼
// Have I visited this page?
//       │
//  ┌────┴─────┐
//  │          │
// No         Yes
//  │          │
//  ▼          ▼
// Add to      Ignore
// visited
//  │
//  ▼
// ExecutorService.submit(
//     () -> crawl(nextUrl)
// )

// Notice how everything you've learned connects:

// Lambda → represents the task (() -> crawl(nextUrl)).
// Functional interface (Runnable) → the type that the lambda implements.
// ExecutorService → schedules tasks to worker threads.
// ConcurrentHashMap.newKeySet() → safely tracks visited URLs across threads.
// Graph traversal (BFS/DFS) → provides the crawling algorithm.


// The Bigger Picture
// Here's how the concepts you've been learning fit together:
// Lambda
//     │
//     ▼
// Represents work
//     │
//     ▼
// Runnable / Callable
//     │
//     ▼
// ExecutorService
//     │
//     ▼
// Worker Threads
//     │
//     ▼
// Concurrent Collections
//     │
//     ▼
// Safe Multithreading
//     │
//     ▼
// Real-world systems
//     │
//     ├── Web Crawlers
//     ├── Spring Boot async tasks
//     ├── Microservices
//     ├── Batch processing
//     ├── Parallel Streams
//     └── CompletableFuture


// Threads → How work runs concurrently.
// synchronized → Protect shared data.
// Atomic classes → Lock-free operations for simple shared state.
// Semaphores → Coordinate access to limited resources.
// ExecutorService → Manage thread pools instead of raw threads.
// Lambdas → Express tasks as concise pieces of behavior.
// Web crawler → Combine graph traversal, lambdas, executors, and thread-safe collections into a realistic concurrent application.