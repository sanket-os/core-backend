public class NumberPrinter implements Runnable {

    private final int i;

    public NumberPrinter(int i) {
        this.i = i;
    }

    public void run() {
        System.out.println("Thread "
                            + Thread.currentThread().getName() +
                            " prints number " +
                                i);
    }

}
