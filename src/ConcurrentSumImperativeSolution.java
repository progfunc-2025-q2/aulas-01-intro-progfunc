/**
 * Demonstrates a thread-safe solution to the concurrent sum problem using synchronization.
 * This version uses imperative concurrency mechanisms with explicit locks to ensure
 * that the shared variable {@code sum} is updated correctly by multiple threads.
 * <p>
 * This program creates two threads to sum the numbers from 1 to 10,000 in parallel.
 * Both threads update a shared static variable {@code sum}, but access is synchronized
 * using a lock object to prevent race conditions and ensure correct results.
 * <p>
 * Usage:
 * <pre>
 *   java ConcurrentSumImperativeSolution
 * </pre>
 * <p>
 * Expected output:
 * <pre>
 *   Final sum: 50005000
 * </pre>
 */
public class ConcurrentSumImperativeSolution {
    /**
     * Shared variable to store the sum. Accessed concurrently by multiple threads.
     * Access is synchronized using {@code lock}.
     */
    private static int sum = 0;
    /**
     * Lock object for synchronizing access to {@code sum}.
     * Every Java object has an intrinsic lock, we will use this object
     * to synchronize access to the shared variable {@code sum}.
     */
    private static final Object lock = new Object();

    /**
     * Main method. Starts two threads to sum the first and second halves of the range.
     * @param args command-line arguments (not used)
     * @throws InterruptedException if thread joining is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        final int max = 10_000;
        final int mid = max / 2;
        sum = 0;

        /**
         * Runnable to sum the first half of the range [1, mid], with synchronization.
         */
        Runnable sumFirstHalf = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= mid; i++) {
                    /**
                     * a synchronized block ensures that only one thread can execute
                     * this block at a time
                     */
                    synchronized (lock) {
                        sum += i;
                    }
                }
            }
        };

        /**
         * Runnable to sum the second half of the range [mid+1, max], with synchronization.
         */
        Runnable sumSecondHalf = new Runnable() {
            @Override
            public void run() {
                for (int i = mid + 1; i <= max; i++) {
                    /**
                     * a synchronized block ensures that only one thread can execute
                     * this block at a time
                     */
                    synchronized (lock) {
                        sum += i;
                    }
                }
            }
        };

        Thread t1 = new Thread(sumFirstHalf);
        Thread t2 = new Thread(sumSecondHalf);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final sum: " + sum);
    }
}
