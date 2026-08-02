package labsession;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * ============================================================
 * OrderProcessor
 * ============================================================
 * Imagine an e-commerce website like Amazon.
 * Customers continuously place orders.
 * Some orders are:      EXPRESS
 * Others are:      STANDARD
 * Business requirement:
 * EXPRESS orders should always be processed first.
 *
 * How do we achieve that?
 * We could manually sort the orders every time...
 * But that would be inefficient.
 * Instead, Java provides PriorityQueue.
 * PriorityQueue automatically keeps elements ordered
 * according to their priority.
 *
 * The priority is determined by:
 *      compareTo()     or       Comparator
 *
 * In our project,
 * Order implements Comparable<Order>,
 * therefore PriorityQueue automatically knows
 * how to arrange Orders.
 */

public class OrderProcessor {

    /*
     * --------------------------------------------------------
     * Queue Interface
     * --------------------------------------------------------
     * We declare the variable as Queue.
     * Notice:
     * Queue<Order>
     * NOT
     * PriorityQueue<Order>

     * Why?
     * Because programming to an interface  is one of the best practices in Java.

     * Queue is the abstraction.
     * PriorityQueue is one implementation.

     * Tomorrow we can replace PriorityQueue with
     * another Queue implementation without changing
     * the rest of this class.
     */

    private Queue<Order> orders;

    /*
     * Constructor  -  Creates an empty PriorityQueue.
     */

    public OrderProcessor() {
        this.orders = new PriorityQueue<>();
    }

    /*
     * addOrder() -
     * Adds a new order into the queue.
     */

    public void addOrder(Order order) {
        /*
         * offer() - inserts the order.
         */
        orders.offer(order);

        /*
         * After insertion,
         * PriorityQueue immediately rearranges itself.

         * It does NOT wait until processOrder().

         * The Binary Heap property is maintained after every insertion.
         */
    }


    /*
     * processOrder() -  Removes the highest-priority order.
     */
    public Order processOrder() {
        /*
         * poll()
         * removes the root
         * of the PriorityQueue.
         */
        return orders.poll();
    }
}

//Many beginners imagine internal structure as this:

//STANDARD - EXPRESS - STANDARD - EXPRESS

//Wrong.

//A PriorityQueue is not a sorted list.
//Internally it is a Binary Heap.
//Conceptually,

//            EXPRESS
//           /       \
//      EXPRESS    STANDARD
//      /
//STANDARD
//
//Only the highest-priority element is guaranteed to be at the root.
//The remaining elements are arranged just enough to maintain the heap property—not necessarily in full sorted order.


// Why use Queue<Order> instead of PriorityQueue<Order>?
//This is a common interview question.
//You declared:
//private Queue<Order> orders;
//instead of
//private PriorityQueue<Order> orders;

//This follows the principle:
//Program to an interface, not an implementation.
//Why is this beneficial?
//Suppose tomorrow you decide that a simple FIFO queue is enough:

// private Queue<Order> orders =
//        new LinkedList<>();

// Or you want a thread-safe implementation:

// private Queue<Order> orders =
//        new PriorityBlockingQueue<>();

// The rest of your OrderProcessor class doesn't need to change because it only depends on the Queue interface
// (offer(), poll()), not on PriorityQueue-specific methods.


// Time Complexity
//Operation	PriorityQueue
//offer()	O(log n)
//poll()	O(log n)
//peek()	O(1)
//The logarithmic time for offer() and poll() comes from maintaining the binary heap.



// Modern Java Enhancement 💡
// Your implementation is already clean. If you wanted to customize the priority without embedding
// it in Order.compareTo(), you could construct the queue with a Comparator:

//Queue<Order> orders = new PriorityQueue<>(
//        Comparator.comparing(Order::getOrderType)
//                  .reversed()
//                  .thenComparing(Order::getOrderId)
//);

//In that case, Order would not need to implement Comparable at all. Both approaches are valid:
//Use Comparable when there is one clear, natural ordering.
//Use a Comparator when different contexts require different priorities.



// Why use a PriorityQueue instead of a LinkedList?
// A LinkedList queue processes elements strictly in insertion order (FIFO).
// A PriorityQueue processes elements according to priority, regardless of insertion order.

// Why doesn't PriorityQueue need a comparator here?
// Because Order implements Comparable<Order>. The queue automatically
// uses compareTo() to maintain the heap.


// Is a PriorityQueue internally sorted?
// No. It maintains a binary heap, not a fully sorted list.
// Only the highest-priority element is guaranteed to be at the front (the heap root).