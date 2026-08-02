package labsession;

import java.util.Comparator;

/*
 * ============================================================
 * ItemQuantityComparator
 * ============================================================
 *
 * This class defines an ALTERNATIVE way of comparing Item
 * objects.
 *
 * Notice something important:
 *
 * Item already implements Comparable<Item>.
 *
 * Therefore Item already has ONE natural ordering.
 *
 * In our project,
 * compareTo() compares Items by PRICE.
 *
 * But what if tomorrow we want to sort by:
 *
 *      • Quantity
 *      • Name
 *      • Id
 *
 * We cannot keep changing compareTo().
 *
 * Instead, Java provides Comparator.
 *
 * Comparator allows us to create multiple independent
 * comparison strategies without modifying the Item class.
 *
 * This follows one of the SOLID principles:
 *
 *      Open for extension
 *      Closed for modification
 *
 * We extend the behavior by creating new Comparator classes
 * instead of modifying Item itself.
 */

public class ItemQuantityComparator implements Comparator<Item> {

    /*
     * ========================================================
     * Why Comparator<Item>?
     * ========================================================
     *
     * Comparator is a GENERIC interface.
     *
     * Simplified declaration:
     *
     * public interface Comparator<T>
     *
     * We replace T with Item.
     *
     * Therefore Java knows this comparator can compare:
     *
     *      Item ↔ Item
     *
     * but NOT
     *
     *      Item ↔ String
     *      Item ↔ Order
     *
     * This provides compile-time type safety.
     */

    @Override
    public int compare(Item item1, Item item2) {

        /*
         * compare() follows exactly the same contract as
         * compareTo().
         *
         * Return:
         *
         * Negative number
         *      if item1 should come BEFORE item2
         *
         * Zero
         *      if they are considered equal
         *
         * Positive number
         *      if item1 should come AFTER item2
         */

        /*
         * Here we compare ONLY the quantities.
         *
         * Example:
         *
         * Milk
         * Quantity = 20
         *
         * Bread
         * Quantity = 10
         *
         * Integer.compare(20,10)
         *
         * returns a positive number.
         *
         * Therefore Bread comes first.
         */

        return Integer.compare(
                item1.getQuantity(),
                item2.getQuantity()
        );

        /*
         * Integer.compare() is the modern Java approach.
         *
         * It is preferred over manually writing:
         *
         * if(...)
         * else if(...)
         * else
         *
         * because:
         *
         * ✔ shorter
         * ✔ easier to read
         * ✔ less error-prone
         * ✔ consistent with Java APIs
         */
    }
}

// Comparable vs Comparator
// This is one of the most frequently asked interview topics.

// Comparable	                                    Comparator
// Implemented inside the class being compared	    Implemented in a separate class
// Defines the natural/default ordering	            Defines an alternative/custom ordering
// Only one natural ordering per class	            You can create many comparators
// Method: compareTo(T other)	                    Method: compare(T o1, T o2)
// Used by Collections.sort(list)	                Used by Collections.sort(list, comparator)

// A provided Comparator always takes precedence over the natural ordering defined by Comparable.