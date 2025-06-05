/**
 * Demonstrates a race condition in concurrent sum calculation.
 * <p>
 * This program creates two threads to sum the numbers from 1 to 10,000 in parallel.
 * Both threads update a shared static variable {@code sum} without synchronization,
 * which can lead to incorrect results due to race conditions.
 * <p>
 * Usage:
 * <pre>
 *   java ConcurrentSumDemo
 * </pre>
 * <p>
 * Expected output (if no race condition):
 * <pre>
 *   Final sum: 50005000
 * </pre>
 * <p>
 * However, due to the lack of synchronization, the actual output may vary.
 */
public class ConcurrentSumDemo {
    /**
     * Shared variable to store the sum. Accessed concurrently by multiple threads.
     */
    private static int sum = 0;

    /**
     * Main method. Starts two threads to sum the first and second halves of the range.
     * @param args command-line arguments (not used)
     * @throws InterruptedException if thread joining is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        final int max = 10_000;
        final int mid = max / 2;

        /**
         * Runnable to sum the first half of the range [1, mid].
         */
        Runnable sumFirstHalf = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= mid; i++) {
                    sum += i;
                }
            }
        };

        /**
         * Runnable to sum the second half of the range [mid+1, max].
         */
        Runnable sumSecondHalf = new Runnable() {
            @Override
            public void run() {
                for (int i = mid + 1; i <= max; i++) {
                    sum += i;
                }
            }
        };

        Thread t1 = new Thread(sumFirstHalf);
        Thread t2 = new Thread(sumSecondHalf);

        /**
         * Starts both threads to perform the sum concurrently.
         */
        t1.start();
        t2.start();

        /**
         * Waits for both threads to finish before printing the final sum.
         */
        t1.join();
        t2.join();

        System.out.println("Final sum: " + sum);
    }
}
