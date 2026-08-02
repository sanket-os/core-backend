package labsession;

import java.util.Comparator;

public class ItemNameComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return item1.getName().compareTo(item2.getName());
    }
}


// Modern Java Improvement 💡
// Since Java 8, you often don't need a separate comparator class at all.

// You can write:
// items.sort(
//         Comparator.comparingInt(Item::getQuantity)
// );

// This creates a comparator using a method reference.

// Or, using a lambda:

// items.sort(
//         (a, b) -> Integer.compare(
//                 a.getQuantity(),
//                 b.getQuantity()
//         )
// );

// These are concise and are widely used in modern Java. However, learning explicit comparator classes first,
// as you're doing, is valuable because it makes the underlying mechanics clear.

// Why use Comparator instead of changing compareTo()?
// Because a class can have only one natural ordering (Comparable), but many valid alternative orderings.
// Comparator lets you define those alternatives without modifying the class.

// What happens if both Comparable and a Comparator are available?
// The explicitly supplied Comparator is used. The compareTo() method is ignored for that operation.

// Why is Comparator generic?
// Comparator<Item> ensures the compiler only allows comparisons between Item objects,
// providing compile-time type safety and eliminating the need for casts.


// This class demonstrates the power of separating data from behavior:
// Item contains the product information.
// Comparable<Item> defines the default ordering (by price).
// ItemQuantityComparator defines an alternative ordering (by quantity).

// This design makes your code flexible, reusable, and easy to extend. As your application grows,
// you can add new sorting strategies (by ID, by price descending, by stock availability, etc.) simply by writing new comparators—without changing the Item class itself.