// Odd Even Printer using Threads

// We use a shared lock object.
// The Even Thread tries to print. If the current number is odd, it calls lock.wait(), giving up the lock and pausing.
// The Odd Thread prints the odd number, then calls lock.notify(). This "wakes up" the Even thread.
// The threads keep swapping roles until the limit is reached.

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class OddEvenPrinter {
    private int counter;
    private int limit;
    private final Object lock;

    public OddEvenPrinter(int limit) {
        this.limit = limit;
        this.counter = 1;
        this.lock = new Object();
    }

    public void printOdd() {
        synchronized (lock) {
            while (counter < limit) {
                while (counter % 2 == 0) {
                    try {
                        lock.wait();
                    } catch (Exception e) {

                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + counter);
                counter++;
                lock.notify();
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            while (counter < limit) {
                while (counter % 2 != 0) {
                    try {
                        lock.wait();
                    } catch (Exception e) {

                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + counter);
                counter++;
                lock.notify();
            }

        }
    }
}

// 2. The Semaphore Version (Most Elegant)
// Semaphores are perfect for this because they act as "permits." We start the
// oddSemi with 1 permit and the evenSemi with 0.
class SemaphorePrinter {
    private final int limit;
    private final Semaphore oddSemi;
    private final Semaphore evenSemi;

    public SemaphorePrinter(int limit) {
        this.limit = limit;
        oddSemi = new Semaphore(1); // Starts allowed
        evenSemi = new Semaphore(0); // Starts blocked
    }

    public void printOdd() {
        for (int i = 1; i <= limit; i += 2) {
            try {
                oddSemi.acquire();
                System.out.println("Odd: " + i);
                evenSemi.release();
            } catch (Exception e) {

            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= limit; i += 2) {
            try {
                evenSemi.acquire();
                System.out.println("Even: " + i);
                oddSemi.release();
            } catch (Exception e) {

            }
        }
    }
}

// 3. ReentrantLock & Condition (The Flexible Way)
// This is the modern version of wait/notify. It allows you to have multiple
// "waiting rooms" (Conditions) for the same lock.
class LockConditionPrinter {
    private int counter;
    private final int limit;
    private final Lock lock;
    private final Condition condition;

    public LockConditionPrinter(int limit) {
        this.counter = 1;
        this.limit = limit;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public void print(boolean isEven) {
        while (counter <= limit) {
            lock.lock();
            try {
                while (counter <= limit && (counter % 2 != 0) != isEven) {
                    condition.await();
                }

                if (counter <= limit) {
                    System.out.println(Thread.currentThread().getName() + " " + counter);
                    counter++;
                    condition.signal();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }

        }
    }

}

public class OddEvenPrinterUsingThreads {
    public static void main(String[] args) throws InterruptedException {
        int limit = 10;

        System.out.println("--- Testing synchronized Version ---");
        testSynchronized(limit);

        Thread.sleep(500); // Small delay for clean console output
        System.out.println("\n--- Testing Semaphore Version ---");
        testSemaphore(limit);

        Thread.sleep(500);
        System.out.println("\n--- Testing Lock/Condition Version ---");
        testLockCondition(limit);
    }

    private static void testSynchronized(int limit) {
        OddEvenPrinter printer = new OddEvenPrinter(limit);
        Thread t1 = new Thread(printer::printOdd, "Odd-Thread");
        Thread t2 = new Thread(printer::printEven, "Even-Thread");
        t1.start();
        t2.start();
    }

    private static void testSemaphore(int limit) {
        SemaphorePrinter printer = new SemaphorePrinter(limit);
        Thread t1 = new Thread(printer::printOdd, "Odd-Semaphore");
        Thread t2 = new Thread(printer::printEven, "Even-Semaphore");
        t1.start();
        t2.start();
    }

    private static void testLockCondition(int limit) {
        LockConditionPrinter printer = new LockConditionPrinter(limit);
        // We pass 'false' for Odd and 'true' for Even
        Thread t1 = new Thread(() -> printer.print(false), "Odd-Lock");
        Thread t2 = new Thread(() -> printer.print(true), "Even-Lock");
        t1.start();
        t2.start();
    }
}
// Tool ,Type ,Best For...
// synchronized ,Keyword ,"Simple, block-level thread safety."
// ReentrantLock ,Class ,"Complex logic, timeouts, or fairness requirements."
// Semaphore ,Class ,Limiting resource access (Throttling) or signaling between
// threads.
// ReadWriteLock ,Interface,"High-performance ""Read-Heavy"" systems (many
// readers, one writer)."