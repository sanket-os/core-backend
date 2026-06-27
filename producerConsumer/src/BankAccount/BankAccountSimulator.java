package BankAccount;

public class BankAccountSimulator {

    public static void main(String[] args) throws InterruptedException {

        BankAccount bankAccount = new BankAccount();

//        Runnable depositors = new Depositor(bankAccount);

        Runnable depositors = () -> {
            for (long i = 0; i < 100000; i++) {
                bankAccount.deposit(i);
            }
        };

        Runnable withdrawers = () -> {
            for (long i = 0; i < 100000; i++) {
                bankAccount.withdraw(i);
            }
        };

        Thread t1 = new Thread(depositors);
        Thread t2 = new Thread(withdrawers);

        t1.start();
        t2.start();
//         t1 enters deposit()
//         t2 tries withdraw()
//            ↓
//         waits
//         t1 exits deposit()
//         t2 enters withdraw()

        t1.join();
        t2.join();
//        waits for another thread to finish first

        System.out.println(bankAccount.getBalance());

    }

}

// The key lesson is:
// Multiple threads can safely share the same object
// only when access to mutable state is coordinated.

// Here the mutable state is:
// balance
// and coordination is achieved using:
// synchronized

// which guarantees:
// Mutual exclusion (one thread at a time)
// Visibility (changes made by one thread become visible to others)



// H2O problem -> two HH and one O to form a H2O molecule

//import java.util.concurrent.Semaphore;
//import java.util.concurrent.CyclicBarrier;
//
//class H2O {
//
//    private final Semaphore hydrogenSemaphore =
//            new Semaphore(2);
//
//    private final Semaphore oxygenSemaphore =
//            new Semaphore(0);
//
//    private final CyclicBarrier barrier =
//            new CyclicBarrier(3);
//
//    public H2O() {
//
//    }
//
//    public void hydrogen(Runnable releaseHydrogen)
//            throws InterruptedException {
//
//        hydrogenSemaphore.acquire();
//
//        releaseHydrogen.run();
//
//        oxygenSemaphore.release();
//
//        try {
//            /*
//             * Wait until
//             * 2H + 1O arrive.
//             */
//            barrier.await();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void oxygen(Runnable releaseOxygen)
//            throws InterruptedException {
//
//        oxygenSemaphore.acquire(2);
//
//        releaseOxygen.run();
//
//        try {
//            /*
//             * Wait until
//             * all three threads
//             * reach the barrier.
//             */
//            barrier.await();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        /*
//         * Allow next molecule.
//         */
//        hydrogenSemaphore.release(2);
//    }
//}

// This is a common interview question.
//synchronized	                                                Semaphore
//Only one thread enters the critical section at a time.	    You decide how many threads can proceed concurrently.
//Used for mutual exclusion.	                                Used for coordination and limiting concurrency.
//Implicit lock acquisition and release.	                    Explicit acquire() and release().


//Important Rules to Remember
//Rule 1
//acquire() decreases the permit count.
//3 → 2 → 1 → 0

//Rule 2
//release() increases the permit count.
//0 → 1 → 2
//Wake one waiting thread (if any)

//Rule 3
//If permits are 0, the next acquire() waits.
//It does not fail or busy-wait.

//Rule 4
//Semaphores are thread-safe.
//Many threads can call acquire() and release() concurrently without corrupting the permit count.


// One Important Limitation
//A semaphore controls who is allowed to proceed. It does not ensure that a group of threads reaches a point together.
//That's why the complete H₂O solution also uses a CyclicBarrier.

//Think of it this way:
//Semaphore = "Who is allowed into the room?"

//Barrier = "Everyone waits until the whole team has arrived before moving on."

//The H₂O problem needs both:
//Semaphores to ensure the correct ratio (2 H + 1 O).
//A barrier to ensure those three threads leave together as one water molecule.

