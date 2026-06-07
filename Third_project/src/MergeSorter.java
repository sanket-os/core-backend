import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MergeSorter implements Callable<List<Integer> > {

    private final List<Integer> integerList;

    private final ExecutorService executorService;
//    Executor Hierarchy
//    Executor
//    ↑
//    ExecutorService
//    ↑
//    ThreadPoolExecutor

//    Most modern code uses:
//    ExecutorService
//    because it adds:
//    task submission
//    shutdown
//    Futures
//    lifecycle management


//    public MergeSorter(List<Integer> integerList) {
//        this.integerList = integerList;
//    }


    public MergeSorter(List<Integer> integerList, ExecutorService executorService) {
        this.integerList = integerList;
        this.executorService = executorService;
    }

//    Thread.sleep(10000); => thread.interrupt(); => InterruptedException
//    InterruptedException = Someone requested that this thread stop waiting.
//
//    In concurrency:   future.get()
//    can also throw it.
//            Why?

//    Because:
//    While waiting for result, thread got interrupted
//    or the result has not been computed yet


//    Future<Integer> future = executor.submit(task);
//    future.get();
//    throws: ExecutionException
//    because task failed. NOT: Future failed. It is specific to future.get()

//    Which thread originally threw the exception?
//    Main Thread?
//    Worker Thread?
//    Answer: Worker Thread

    public List<Integer> sort() throws ExecutionException, InterruptedException {

       System.out.printf("Sorting %s on Thread %s\n", integerList, Thread.currentThread().getName());

        if (integerList.size() <= 1) {
            return integerList;
        }

        int mid = integerList.size() / 2;
        List<Integer> leftArray = new ArrayList<>();
        List<Integer> rightArray = new ArrayList<>();

//        Without Generics

//        List list = new ArrayList();
//        list.add("Java");
//        list.add(100);
//        Java allows this.

//        Problem:
//        String s = (String) list.get(1);
//        Boom: ClassCastException

//        Generics tell Java:
//        "What type goes inside this container?"
//        Examples: List<String>, List<Integer>, List<Book>
//        Future<Integer>, Future<String>
//        No casting needed.

        for (int i = 0; i < mid; i++)
        {
            leftArray.add(integerList.get(i));
        }

        for (int i = mid; i < integerList.size(); i++)
        {
            rightArray.add(integerList.get(i));
        }

        MergeSorter mergeSorterLeft = new MergeSorter(leftArray, executorService);
        MergeSorter mergeSorterRight = new MergeSorter(rightArray, executorService);

        // Changes here
        // Instead of direcly calling sort on the same thread
        // Submit the task to a different thread pool
        Future<List<Integer>> leftListFuture = executorService.submit(mergeSorterLeft);
        Future<List<Integer>> rightListFuture = executorService.submit(mergeSorterRight);
        // submit() vs execute()
        // submit() returns a Future, execute() executes on time, no concurrency
        // You can later get the result.


//        List<Integer> sortedLeft = mergeSorterLeft.sort();
//        List<Integer> sortedRight = mergeSorterRight.sort();

        // Blocking call to get the sorted arrays.

        List<Integer> sortedLeft = leftListFuture.get();
        List<Integer> sortedRight = rightListFuture.get();

        // merge sorted arrays.
        int i = 0, j = 0;
        List<Integer> mergedList = new ArrayList<>();

        while (i < leftArray.size() && j < rightArray.size()) {
            if (sortedLeft.get(i) <= sortedRight.get(j)) {
                mergedList.add(sortedLeft.get(i));
                i+=1;
            } else {
                mergedList.add(sortedRight.get(j));
                j+=1;
            }
        }

        while (i < leftArray.size()) {
            mergedList.add(sortedLeft.get(i));
            i+=1;
        }

        while (j < rightArray.size()) {
            mergedList.add(sortedRight.get(j));
            j+=1;
        }

        return mergedList;

    }

    @Override
    public List<Integer> call() throws Exception {
        return sort();
    }

//  "If an exception occurs,
//  I'm not handling it here.
//  Pass it to my caller/parent."

//Throwable
//│
//├── Error
//│
//└── Exception
//    │
//    ├── IOException
//    │
//    ├── InterruptedException
//    │
//    ├── SQLException
//    │
//    ├── ExecutionException
//    │
//    └── RuntimeException
//         │
//         ├── NullPointerException
//         └── NumberFormatException

//    You can catch the parent class:
//  try {
//    future.get();
//  }
//  catch (Exception e) {
//      System.out.println(e.getClass().getName());
//  }

//  Now you'll catch:
//  IOException
//  InterruptedException
//  SQLException
//  ExecutionException
//  and many others.

//  Because: All of them ARE Exceptions.

}
