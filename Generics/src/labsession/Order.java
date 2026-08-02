package labsession;

/*
 * ============================================================
 * Order Class
 * ============================================================
 *
 * This class represents a customer's order.
 *
 * Every order has:
 *
 *      • orderId
 *      • orderType
 *
 * Unlike Item, this class is NOT about products.
 * It represents orders waiting to be processed.
 *
 * This class implements Comparable<Order>,
 * meaning every Order knows how to compare itself
 * with another Order.
 *
 * Java will use compareTo() whenever Orders are
 * sorted or stored inside collections like
 * PriorityQueue or TreeSet.
 */

public class Order implements Comparable<Order> {

    private String orderId;

    /*
     * ========================================================
     * compareTo()
     * ========================================================
     *
     * Defines the NATURAL ORDERING of Orders.
     *
     * In this application we have the business rule:
     *
     *      EXPRESS orders
     *      should always be processed before
     *      STANDARD orders.
     *
     * If two orders have the same type,
     * we compare their orderId.
     */
    @Override
    public int compareTo(Order order) {
        if (!this.orderType.equals(order.orderType)) {
            if (this.orderType == OrderType.EXPRESS) {
// Notice we use ==
// instead of equals()
// Why?
// Because OrderType is an enum. Enums are singleton constants.
// There is only one EXPRESS object.
// Therefore
// ==
// is perfectly correct and is actually preferred for enums.
                // if we want this order to come first -> return -1
                return -1;
            } else {
                return 1;
            }
        }
        return this.orderId.compareTo(order.orderId);
    }

    /*
     * ========================================================
     * Enum
     * ========================================================
     *
     * An enum represents a fixed set of constants.
     *
     * In our application,
     * an order can only be:
     *
     *      STANDARD
     *      EXPRESS
     *
     * Nothing else.
     *
     * Enums provide compile-time safety.
     */

    public enum OrderType {
        STANDARD,
        EXPRESS
    }

    private OrderType orderType;

    public Order(String orderId, OrderType orderType) {
        this.orderId = orderId;
        this.orderType = orderType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderType=" + orderType +
                '}';
    }
}

// This natural ordering is automatically used by collections such as PriorityQueue, allowing
// high-priority orders to be processed first without writing additional sorting logic.
// This is a realistic example of how backend systems model and prioritize work using Java's collection framework.

// Why implement Comparable<Order>?
// To define the natural ordering of orders so Java collections like PriorityQueue, TreeSet, and
// Collections.sort() know how to arrange them.

// Why use == for enums?
// Enums are singleton instances. Comparing them with == checks identity and is the recommended
// approach. There's no need to call equals() for enum constants.

//________________________________________________________________________________________________________________________

// Modern Java Improvement 💡
//
//Your comparison logic is correct, but it can be written more concisely using the Comparator utility methods introduced in Java 8:
//
//@Override
//public int compareTo(Order other) {
//    return Comparator
//            .comparing(Order::getOrderType)
//            .reversed() // EXPRESS before STANDARD
//            .thenComparing(Order::getOrderId)
//            .compare(this, other);
//}
//
//This version expresses the business rule declaratively:
//
//Compare by orderType.
//Reverse the order so EXPRESS comes before STANDARD.
//If both types are equal, compare by orderId.
//
// It's a common style in modern Java, though your current implementation is perfectly suitable for learning
// because it makes the comparison logic explicit.