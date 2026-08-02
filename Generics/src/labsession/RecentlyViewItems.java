package labsession;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecentlyViewItems {
    /*
     * ========================================================
     * Internal Storage
     * ========================================================
     * We use a LinkedList.
     * Why not an ArrayList?
     * Because we frequently:
     *
     * • Insert at the beginning
     * • Remove from the beginning
     * • Remove from the end
     *
     * LinkedList performs these operations efficiently.
     */
    private final LinkedList<Item> items;
     /*
     * Maximum number of items we want to remember
     */
    private final int MAX_SIZE;

// Why final?
// Notice
// private final LinkedList<Item> items;
// Many beginners think final means the object cannot change.
// That is not true.
// final means
// The reference cannot point to another object.

// Example
// items.add(item);
// ✅ Allowed
// because we're modifying the object.
// But
// items = new LinkedList<>();

// ❌ Not allowed.
// The reference is final.
// Exactly the same applies to
// private final int MAX_SIZE;
// Once assigned in the constructor,
// MAX_SIZE = 5;
// it can never change.


//    constructor creates an empty list
    public RecentlyViewItems(int limit) {
        this.items = new LinkedList<>();
        MAX_SIZE = limit;
    }

//    This is the heart of the class.
    public void addRecentlyViewedItem(Item item) {
// This line is brilliant.
// Suppose history is
// Laptop
// Mouse
// Keyboard

// Now user opens
// Mouse
// again.

// Without this line
// history becomes

// Mouse
// Laptop
// Mouse
// Keyboard

// Duplicate!
// Instead,
// Java first removes
// Mouse
// Now list becomes
// Laptop
// Keyboard
        items.remove(item);

// Exactly what we want.
// Most recent item always stays at the front.
        items.addFirst(item);

        if (items.size() > MAX_SIZE) {
            items.removeLast();
// The oldest item disappears.
        }
    }

// Inside of ArrayList we have to shift the items after removal or insertion
// No shifting in LinkedList.
// That's why LinkedList is a natural fit for this kind of "most recent first" history.
//

    public List<Item> getRecentlyViewedItems() {
        return new ArrayList<>(items);
    }
//    Notice
// We do not write
// return items;

// Why?
// Because that would expose our internal data.
// Someone could write

// history.getRecentlyViewedItems().clear();
// and destroy the internal list.

// Instead,
// we create a copy.
// new ArrayList<>(items)
// Now the caller gets a separate list.
// Changing it does not affect the history.
// This is called a Defensive Copy.
}


// Is this an LRU Cache?
//Not completely.
//This class behaves like the list portion of an LRU cache:
//✔ Most recently used item moves to the front.
//✔ Oldest item removed when capacity exceeded.
//However,
//A real LRU cache also stores
//Key
//↓
//Value
//and provides fast lookup using a HashMap.
//Java already provides a classic LRU implementation using
//LinkedHashMap
//with access-order enabled.
//So your class teaches the underlying idea before introducing the more advanced data structure.


// Time Complexity
//Operation	    Complexity
//addFirst()	O(1)
//removeLast()	O(1)
//remove(item)	O(n) (search required)
//getRecentlyViewedItems()	O(n) (copy the list)
//Notice that remove(item) must search for the item first, so it is linear in the number of stored items.

// Why use LinkedList instead of ArrayList?
// Because the implementation frequently inserts at the front and removes from the end.
// Those operations are efficient on a LinkedList, whereas inserting at the front of an ArrayList requires shifting elements.

// Why remove the item before adding it to the front?
// To ensure each item appears only once in the history.
// Viewing an existing item moves it to the front instead of creating a duplicate entry.

// Why return new ArrayList<>(items) instead of items?
// To create a defensive copy. This prevents callers from accidentally
// or intentionally modifying the internal state of the RecentlyViewItems object.























