import java.util.ArrayList;
import java.util.List;

public class Store {

    private final int maxSize;
    private final List<Object> items;

    public Store(int maxSize) {
        this.items = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void addItem(Object item) {
        items.add(item);

        System.out.println(
                Thread.currentThread().getName()
                    + " PRODUCED item. Buffer size = "
                    + items.size()
        );
    }

    public void removeItem() {
        items.removeLast();

        System.out.println(
                Thread.currentThread().getName()
                    + " item CONSUMED. Buffer size = "
                    + items.size()
        );
    }

    public int getMaxSize() {
        return maxSize;
    }

}