import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

/**
 * Demonstrates a declarative and thread-safe approach to concurrent sum calculation using Java streams and futures.
 * We avoid the use of shared mutable state and explicit synchronization mechanisms.
 * The advantage of declarative concurrency is that we do not need to manage thread synchronization explicitly,
 * as the abstractions handle it for us.
 * <p>
 * This program creates two threads, each summing a portion of the range [1, 10,000] using {@link IntStream}.
 * The results are combined after both threads complete, ensuring thread safety without explicit synchronization.
 * <p>
 * Usage:
 * <pre>
 *   java ConcurrentSumDeclarativeDemo
 * </pre>
 * <p>
 * Expected output:
 * <pre>
 *   Final sum: 50005000
 * </pre>
 */

public class ConcurrentSumDeclarativeDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int max = 10_000;
        final int mid = max / 2;

        /**
         * Callable to sum the first half of the range [1, mid].
         * This uses IntStream to calculate the sum in a declarative style.
         * Streams do not perform mutating operations on shared state,
         */
        Callable<Integer> sumFirstHalf = () -> IntStream.rangeClosed(1, mid).sum();
        Callable<Integer> sumSecondHalf = () -> IntStream.rangeClosed(mid + 1, max).sum();

        /**
         * FutureTasks are used to run the Callable tasks in separate threads.
         * The advantage of a FutureTask is that it is a promise to return a result in the future,
         * allowing us to retrieve the result once the computation is complete.
         * Pure threads, on the other hand, do not return a result and require additional synchronization
         * mechanisms to share results between threads.
         */
        FutureTask<Integer> future1 = new FutureTask<>(sumFirstHalf);
        FutureTask<Integer> future2 = new FutureTask<>(sumSecondHalf);

        /**
         * Create and start threads for each FutureTask.
         */
        Thread t1 = new Thread(future1);
        Thread t2 = new Thread(future2);

        t1.start();
        t2.start();

        /**
         * Wait for both threads to complete and retrieve their results.
         * The variable result will be assigned only after both threads have completed their execution.
         * This ensures that we do not have to worry about thread synchronization.
         */
        int result = future1.get() + future2.get();

        System.out.println("Final sum: " + result);
    }
}
