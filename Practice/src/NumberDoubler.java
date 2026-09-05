import java.util.concurrent.Callable;

class NumberDoubler implements Callable {

    private final int i;

    public NumberDoubler(int i) {
        this.i = i;
    }

    public Integer call() {
        return i * 2;
    }

}