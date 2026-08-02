package labsession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Item item = new Item("1", "ParleG", 12.5, 11);
        Item item2 = new Item("2", "Dairy Milk", 6, 11);
        Item item3 = new Item("3", "Milk", 15, 10);

        List<Item> items = new ArrayList<>(List.of(item, item2, item3));
// List.of() returns an immutable (unmodifiable) list
// You cannot do
// list.add(...)   or   Collections.sort(list);
// because sorting modifies the list.
// Therefore, You wrap it inside
// new ArrayList<>(...)
// Now Java copies the contents into a new, modifiable ArrayList.

        Collections.sort(items);
// No Comparator here. Item implement Comparable, Java automatically calls compareTo()
// inside Item. => Double compare by price

        // System.out.println(items);

        Order order1 = new Order("123", Order.OrderType.STANDARD);
        Order order2 = new Order("345", Order.OrderType.EXPRESS);
        Order order3 = new Order("456", Order.OrderType.STANDARD);
        Order order4 = new Order("789", Order.OrderType.EXPRESS);

        OrderProcessor orderProcessor = new OrderProcessor();
// Constructor
// this.orders = new PriorityQueue<>();

// Internally
// PriorityQueue
// ↓
// (empty)
// Remember:
// Since Order implements
// Comparable<Order>
// PriorityQueue already knows how to arrange Orders.

        orderProcessor.addOrder(order1);
        orderProcessor.addOrder(order3);
// Since both are STANDARD,
// compareTo() compares by
// orderId not orderType

        System.out.println(orderProcessor.processOrder());
// Internally
// orders.poll();
// returns
// 123 STANDARD

// Many beginners expect EXPRESS here.
// But remember...
// At this point
// NO EXPRESS ORDER
// has been inserted yet.
// PriorityQueue can only prioritize the elements that currently exist.

        orderProcessor.addOrder(order2);
        orderProcessor.addOrder(order4);

// Queue now contains
// 456 STANDARD
// 345 EXPRESS
// 789 EXPRESS

// PriorityQueue automatically rearranges them.
// Conceptually
// 345 EXPRESS
// ↓
// 789 EXPRESS
// ↓
// 456 STANDARD
// Notice
// You did NOT write
// Collections.sort(...)
// PriorityQueue handles this automatically.

        System.out.println(orderProcessor.processOrder()); // Returns 345 EXPRESS
        System.out.println(orderProcessor.processOrder()); // poll() returns 789 EXPRESS

// You never call
// processOrder();
// again.
// Therefore
// 456 STANDARD
// remains inside the queue.

        Collections.sort(items, new ItemNameComparator());
// Java now ignores
// compareTo()
// because you explicitly supplied a Comparator.
// Instead, Java calls
// ItemNameComparator.compare()
// which compares
// item.getName()

// Alphabetically
// Dairy Milk
// Milk
// ParleG
// Exactly what your program prints.

        System.out.println(items);
    }
}

// Why wrap List.of(...) in new ArrayList<>(...) ?
// Because List.of() creates an unmodifiable list. Collections.sort() modifies the list in place,
// so it requires a mutable implementation like ArrayList.

// Why did Collections.sort(items) use compareTo()?
// Because no Comparator was supplied, so Java falls back to the natural ordering defined by Comparable.

// Why did the second sort ignore compareTo()?
// Because an explicit Comparator (ItemNameComparator) was provided. When a comparator is supplied,
// it always takes precedence over the natural ordering.