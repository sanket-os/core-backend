package labsession;

import java.util.*;

/*
 * ============================================================
 * Inventory Class
 * ============================================================
 *
 * This class represents a generic inventory.
 *
 * Notice that nowhere in this class do we say:
 *
 *      Book
 *      Electronics
 *      Clothing
 *
 * Instead, we use a TYPE PARAMETER (T).
 *
 * This makes the Inventory reusable.
 *
 * Instead of writing:
 *
 *      BookInventory
 *      ElectronicsInventory
 *      ClothingInventory
 *
 * we write ONE generic Inventory class that works
 * for every kind of Item.
 *
 * This is one of the biggest advantages of Generics:
 *
 *          Write Once
 *          Reuse Everywhere
 */

public class Inventory<T extends labsession.Item> {

    /*
     * ========================================================
     * What does T mean?
     * ========================================================
     *
     * T is a TYPE PARAMETER.
     * Think of it as a placeholder.
     * Java does NOT know what T is yet.
     * Later, when creating an Inventory object,
     * we replace T with an actual type.
     *
     * Example:
     * Inventory<Book>
     * becomes
     * Inventory<Book>
     * Inventory<Electronics>
     * becomes
     * Inventory<Electronics>
     */

    /*
     * ========================================================
     * Why "extends Item"?
     * ========================================================
     *
     * This is called a BOUNDED TYPE PARAMETER.
     *
     * It tells Java:
     * "T can only be Item
     *  or any subclass of Item."
     *
     * Therefore these are VALID:
     * Inventory<Item>
     * Inventory<Book>
     * Inventory<Clothing>
     * Inventory<Electronics>
     *
     * But this is INVALID:
     * Inventory<String>
     * because String does not extend Item.
     * The compiler immediately rejects it.
     * This gives us type safety.
     */


    /*
     * ========================================================
     * Internal Storage
     * ========================================================
     * We store all items inside a HashMap.
     * Key
     * ----
     * String
     *
     * Value
     * -----
     * T

     * Example:
     * "101" → Book
     * "102" → Book
     * OR
     * "E11" → Electronics
     * depending on the type of Inventory.
     */
    private Map<String, T> map;

// Why use Map<String, T>?
// It provides efficient lookup by ID (average O(1) for put, get, and remove) while preserving the
// specific item type through generics.

// Why return T instead of Item from get()?
// Returning T preserves the exact type. An Inventory<Book> returns a Book, not just an Item, so
// callers don't need casts and get full compile-time type safety.

    /*
     * ========================================================
     * Constructor
     * ========================================================
     * Creates an empty HashMap.
     */
    public Inventory() {
        /*
         * HashMap gives O(1) average time complexity
         * for insert, search and remove.
         */
        this.map = new HashMap<>();
    }

//    "ADB2134"

    /*
     * ========================================================
     * add()
     * ========================================================
     * Adds an item into the inventory.
     * Notice the parameter:
     * T item
     * NOT
     * Book
     * NOT
     * Electronics
     *
     * T automatically becomes the correct type.
     */
    public void add(T item) {
        /*
         * Since T extends Item,
         * Java knows every T has getId().
         *
         * Without "extends Item"
         * this line would NOT compile.
         */
        if (map.containsKey(item.getId())) {
            /*
             * Prevent duplicate ids.
             */
            throw new IllegalArgumentException("Already exists.");
        }
        /*
         * Store the object.
         */
        map.put(item.getId(), item);
    }

    /*
     * Removes an item using its id.
     */
    public void remove(String id) {
        map.remove(id);
    }

    /*
     * Removes an object directly.
     */
    public void remove(Item item) {
        /*
         * We simply use its id.
         */
        map.remove(item.getId());
    }

    /*
     * Returns an item by id.
     */
    public T get(String id) {
        return map.get(id);
    }

    /*
     * Returns every item.
     */
    public List<T> getAll() {
        return new ArrayList<>(map.values());
    }

    /*
     * Returns all items sorted using
     * the supplied Comparator.
     */
//    public List<T> getAll(Comparator<Item> comparator) {
        public List<T> getAll(Comparator<? super T> comparator) {
        /*
         * Copy map values into a list.
         */
        List<T> items = new ArrayList<>(map.values());
        /*
         * Sort using the Comparator
         * provided by the caller.
         */
        Collections.sort(items, comparator);
        /*
         * Return the sorted copy.
         */
        return items;
    }
}

// Summary
// This class demonstrates the core value of Java Generics:
// Type safety: The compiler prevents storing unrelated types.
// Code reuse: One Inventory implementation works for Item and every subclass.
// Bounded generics: T extends Item guarantees access to Item's methods.
// Generic collections: Map<String, T> and List<T> preserve the concrete type throughout the API.
// Flexibility: By combining generics with comparators, the same inventory can return items in different orders without changing its internal storage.

// If Item taught you how to model a product, Inventory<T extends Item> teaches you how to build reusable,
// type-safe APIs—which is exactly what generics were designed for.

// Why use a Generic Inventory?

//Without Generics, you might end up writing separate classes like:
//BookInventory
//ElectronicsInventory
//ClothingInventory

//Each would contain almost identical logic for adding, removing, searching, and listing items.

//Generics let you write that logic once:
//Inventory<T extends Item>
//and reuse it for any subclass of Item.