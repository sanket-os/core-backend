package semaphores;
import java.util.ArrayList;
import java.util.List;

public class Store {

    int maxSize;
    List<Object> items;


    public Store(int maxSize)
    {
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }


    public void addItem(Object object) {
        items.add(object);
        System.out.println("Producer produced an item. size=" + items.size());
    }

    public void removeItem()
    {
        items.remove(items.size() - 1);
        System.out.println("Consumer consumed an item. size=" + items.size());
    }

}