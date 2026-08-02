package labsession;

/*
 * ============================================================
 * Item Class
 * ============================================================
 *
 * This class represents a generic item in our inventory.
 *
 * Instead of creating separate classes for each product with
 * duplicate fields, we create one reusable base class.
 *
 * Later, classes like Book, Clothing and Electronics will
 * inherit from this class.
 *
 * This class also implements Comparable<Item>, which means
 * every Item knows how to compare itself with another Item.
 *
 * Java uses this comparison while sorting.
 */

public class Item implements Comparable<Item> {
    private String id;
    private String name;
    private double price;
    private int quantity;
//    Why don't we make fields public?
//
//    Answer:
//    It follows the Encapsulation principle.
//    It protects the object's internal state.
//    It allows validation before modifying data.
//    It lets us change the internal implementation later without affecting users of the class.

    public Item(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /*
     * --------------------------------------------------------
     * Getter Methods
     * --------------------------------------------------------
     *
     * Since our fields are private, code outside this class
     * cannot access them directly.
     *
     * Instead we expose controlled access using getters.
     *
     * This is one of the fundamental ideas of
     * Object-Oriented Programming called Encapsulation.
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    /*
     * A real production system would usually validate
     * that the price cannot become negative.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    /*
     * Updates quantity.
     *
     * Again, production code would normally ensure
     * quantity never becomes negative.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // To make sure the orders can be compared based on their pricing
    // Item -> @123
    // Ascending.

    /*
     * ============================================================
     * Comparable<Item>
     * ============================================================
     *
     * Our class implements Comparable<Item>.
     *
     * Comparable is a generic interface provided by Java.
     *
     * Its declaration (simplified) looks like:
     *
     *      public interface Comparable<T> {
     *          int compareTo(T other);
     *      }
     *
     * Notice the generic type parameter <T>.
     *
     * By writing Comparable<Item>, we are telling Java:
     *
     *      "An Item can be compared only with another Item."
     *
     * This provides compile-time type safety.
     *
     * Without generics (before Java 5), compareTo() accepted
     * Object, requiring explicit casting and allowing mistakes.
     *
     * Modern Java avoids those problems by using generics.
     */
    @Override
    public int compareTo(Item item) {
        /*
         * compareTo() defines the NATURAL ORDERING
         * of Item objects.
         *
         * Java uses this method automatically whenever
         * you write:
         *
         * Collections.sort(items);
         *
         * or
         *
         * Arrays.sort(items);
         *
         * or whenever an Item is stored inside a TreeSet,
         * TreeMap, PriorityQueue (unless another Comparator
         * is supplied).
         */

         /*
         * The compareTo() contract is:
         *
         * Return:
         *
         * Negative Number
         *      if this object should come BEFORE the other.
         *
         * Zero
         *      if both objects are considered equal.
         *
         * Positive Number
         *      if this object should come AFTER the other.
         *
         * Java does NOT care about the exact value.
         *
         * -1
         * -100
         * -5000
         *
         * are all treated exactly the same.
            similar for positive numbers
          */

        return Double.compare(this.price, item.price);
//        if (this.price < item.price) return -1;
//        else if (this.price > item.price) return 1;
//        return 0;

//      Objects don't have an obvious ordering.
//      You must choose one.
//      For Item, you decided
//      Natural Order
//      ↓
//      Price
//      That becomes the default ordering everywhere.
    }

    /*
     * ============================================================
     * toString()
     * ============================================================
     *
     * Every Java object inherits a toString() method
     * from the Object class.
     *
     * If we don't override it,
     * Java prints something like:
     *
     * labsession.Item@5e91993f
     *
     * which is not useful.
     *
     * By overriding toString(),
     * we decide exactly how an Item should be displayed.
     */

    @Override
    public String toString() {

        /*
         * This method creates a readable String
         * representing the current object.
         */
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

// Where Generics Fit In
//At first glance, this class doesn't look "generic," but it is the type that your generic classes are built around.
//
//Later, you'll see:
//public class Inventory<T extends Item>
//
//This means:
//T can be Item
//T can be Book
//T can be Clothing
//T can be Electronics
//
//because all of them inherit from Item.
//Without this Item class, the generic inventory couldn't guarantee that every stored object has methods like:
//
//getId();
//getName();
//getPrice();
//getQuantity();
//
//The extends Item bound gives the generic class that guarantee,
//without needing any casts.

// Why implement Comparable<Item>?
// To define the natural ordering of Item objects so that APIs like Collections.sort() can sort them without
// requiring a separate Comparator.