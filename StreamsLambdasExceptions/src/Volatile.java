//class Worker implements Runnable {
//
//    private volatile boolean running = true;
//
//    @Override
//    public void run() {
//
//        System.out.println(Thread.currentThread().getName() + " started");
//
//        while (running) {
//            System.out.println(
//                    Thread.currentThread().getName() + " working..."
//            );
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                break;
//            }
//        }
//
//        System.out.println(
//                Thread.currentThread().getName() +
//                        " stopped"
//        );
//
//    }
//
//    public void stop() {
//        running = false;
//    }
//
//}
//
//
//
//
//
//public class Volatile {
//
//    public static void main(String[] args)
//        throws InterruptedException {
//
//        Worker worker = new Worker();
//
//        Thread thread = new Thread(worker, "Worker");
//
//        thread.start();
//
//        Thread.sleep(1000);
//
//        System.out.println("Requesting worker to stop...");
//
//        worker.stop();
//
//    }
//
//}


// collections -> ConcurrentHashMap, LinkedList, hashSet
// ReentrantLock, synchronized blocks, synchronized methods
// executorService schedule, states of thread => New, Running, Blocked, Terminated
// OutOfMemoryError exception thrown when java is out of memory
// Collections.synchronizedMap with a HashMap
// ConcurrentHashMap - putIfAbsent()
// compare two strings for equality in a case-insensitive manner => equalsIgnoreCase()
// collect() method in Java Streams



import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.List;
import java.util.TreeSet;

public class Volatile {

    public static void main(String[] args) {

//        var names = new ArrayList<String>();
//        names.add("Samuel");
//        names.add("Saishree");
//        names.add("Millie");
//
//        String secondPerson = names.get(1);
//        System.out.println(secondPerson);
//
//        names.remove("Millie");
//        System.out.println(names);

//        ____________________________

//        var uniqueTags = new HashSet<String>();
//        uniqueTags.add("Sadie");
//        uniqueTags.add("Jack");
//        uniqueTags.add("Siya");
//
//        boolean hasSadie = uniqueTags.contains("Sadie");
//        System.out.println(hasSadie);

//        ------------------------------------

//        var userScores = new HashMap<String, Integer>();
//        userScores.put("Asha", 66);
//        userScores.put("Bob", 89);
//
//        int score = userScores.getOrDefault("Charlie", 190);
//        System.out.println(score);
//        userScores.putIfAbsent("Bob", 100);
//
//        userScores.forEach((user, points) ->
//            System.out.println(user + " - " + points)
//        );

//        ---------------------------------------------------

//        var tasks = new ArrayDeque<String>();
//
//        tasks.addLast("Task 1");
//        tasks.addLast("Task 2");
//        String nextTask = tasks.pollFirst();
//
//        System.out.println(nextTask);
//        System.out.println(tasks);
//
//        tasks.push("Page A");
//        tasks.push("Page B");
//        String lastPage = tasks.pop();
//
//        System.out.println(lastPage);
//        System.out.println(tasks);

//        ---------------------------------------

        var sortedScores = new TreeSet<Integer>();
        sortedScores.addAll(List.of(5, 343, 352, 124, 353));

        int lowest = sortedScores.first();
        int highest = sortedScores.last();
        System.out.println(lowest);
        System.out.println(highest);


        var middleScores = sortedScores.subSet(5, 150);

        System.out.println(middleScores);
        System.out.println(sortedScores);

    }

}

