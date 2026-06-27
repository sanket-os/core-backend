package BankAccount;

public class BankAccount {

    private Long balance = 0L;
//    In modern Java, for a simple counter-like value:
//    private long balance;
//    is usually preferred.

    public Long getBalance() {
        return balance;
    } // Long is an object wrapper.
//    long is a primitive and avoids unnecessary boxing/unboxing.

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    // Class level lock (class.lock())
    public synchronized static void temp() {
        System.out.println("hello");
    }
//    What lock is used by a static synchronized method?
//  Static methods belong to the class, not an object.
//  The lock is:
//  BankAccount.class
//  Equivalent:
//  synchronized(BankAccount.class) {
//      ...
//  }

//    Object Lock
//  -----------
//  Account1 lock
//  Account2 lock
//  Account3 lock

//  Class Lock
//  ----------
//  BankAccount.class lock
//  There is only one class lock.

    // Object level lock. (object.lock())
    public synchronized void deposit(Long amount) {
        // synchronized would ensure that the caller has the
        // access to the lock of bank account;
        if (amount > 0) {
            balance += amount;
        }
//        The lock used is:
//        this
//        which means: the current BankAccount object itself
//        Equivalent:
//        synchronized(this) {
//          ...
//        }
    }

    public synchronized void withdraw(Long amount) {
        if (balance >= amount && amount > 0) {
            balance -= amount;
        }
//      race condition occurs without synchronization
    }

}

//  Why isn't the final balance always zero ?
//  Many beginners assume:
//  deposit 0..99999
//  withdraw 0..99999
//  should cancel out.

//  But both threads run concurrently.
//  Example:
//  Balance = 0
//  Withdraw 500 executes first
//  Check:
//  balance >= 500
//  False.
//  Withdrawal fails.

//  Later:
//  Deposit 500 executes
//  Now money is added.
//  That failed withdrawal is never retried.
//  So some withdrawals are lost.
//  Result:
//  Final balance > 0

//  in some cases you might get 0


//  Why is balance += amount not atomic?
//  Because it is actually several steps:
//  balance += amount;
//  becomes conceptually:

//  read balance
//  calculate new value
//  write balance

//  Example:
//  read 100
//  add 50
//  write 150

//  Another thread can interfere between these steps.
//  That's why synchronization is needed.
