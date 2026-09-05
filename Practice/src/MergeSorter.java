import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MergeSorter implements Callable<List<Integer>> {

    private final List<Integer> integerList;
    private final ExecutorService executorService;

    public MergeSorter(List<Integer> integerList, ExecutorService executorService) {
        this.integerList = integerList;
        this.executorService = executorService;
    }

    public List<Integer> sort() throws ExecutionException, InterruptedException {

        if (integerList.size() <= 1) {
            return integerList;
        }

        int mid = integerList.size() / 2;
        List<Integer> leftArray = new ArrayList<>();
        List<Integer> rightArray = new ArrayList<>();

        for (int i = 0; i < mid; i++) {
            leftArray.add(integerList.get(i));
        }

        for (int i = mid; i < integerList.size(); i++) {
            rightArray.add(integerList.get(i));
        }

        MergeSorter mergeSorterLeft = new MergeSorter(leftArray, executorService);
        MergeSorter mergeSorterRight = new MergeSorter(rightArray, executorService);

        Future<List<Integer>> leftListFuture = executorService.submit(mergeSorterLeft);
        Future<List<Integer>> rightListFuture = executorService.submit(mergeSorterRight);

        List<Integer> sortedLeft = leftListFuture.get();
        List<Integer> sortedRight = rightListFuture.get();

        int i = 0, j = 0;
        List<Integer> mergedList = new ArrayList<>();

        while (i < leftArray.size() && j < rightArray.size()) {
            if (sortedLeft.get(i) < sortedRight.get(j)) {
                mergedList.add(sortedLeft.get(i));
                i += 1;
            } else {
                mergedList.add(sortedRight.get(j));
                j += 1;
            }
        }

        while (i < leftArray.size()) {
            mergedList.add(sortedLeft.get(i));
            i += 1;
        }

        while (j < rightArray.size()) {
            mergedList.add(sortedRight.get(j));
            j += 1;
        }

        return mergedList;

    }

    @Override
    public List<Integer> call() throws Exception {
        return sort();
    }

}
