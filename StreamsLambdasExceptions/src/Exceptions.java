import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/*
 * ================================================================
 * CUSTOM CHECKED EXCEPTION
 * ================================================================
 *
 * By extending Exception, this becomes a CHECKED exception.
 *
 * That means Java's compiler will require methods that can throw
 * this exception to either:
 *
 *      1. Handle it using try-catch
 *
 * OR
 *
 *      2. Declare it using throws
 *
 */
class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}


/*
 * ================================================================
 * CUSTOM UNCHECKED EXCEPTION
 * ================================================================
 *
 * By extending RuntimeException, this becomes an UNCHECKED exception.
 *
 * The compiler does NOT force us to catch it or declare it.
 *
 */
class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(String message) {
        super(message);
    }
}


public class Exceptions {

    public static void main(String[] args) {


        /*
         * ============================================================
         * 1. UNCHECKED EXCEPTION
         * ============================================================
         *
         * RuntimeException and its subclasses are unchecked.
         *
         * Example:
         *
         *      ArithmeticException
         *
         * Java does NOT force us to catch it.
         *
         */

        try {

            int a = 10;
            int b = 0;

            int result = a / b;

            System.out.println(result);

        } catch (ArithmeticException e) {

            System.out.println(
                    "Cannot divide by zero."
            );
        }


        /*
         * Output:
         *
         * Cannot divide by zero.
         *
         *
         * Notice:
         *
         * ArithmeticException is a RuntimeException.
         *
         * Therefore, this try-catch is OPTIONAL from the
         * compiler's point of view.
         */


        /*
         * ============================================================
         * 2. NULLPOINTEREXCEPTION
         * ============================================================
         *
         * Another unchecked exception.
         *
         * Again, Java does NOT force us to catch it.
         */

        try {

            String name = null;

            System.out.println(name.length());

        } catch (NullPointerException e) {

            System.out.println(
                    "The name is null."
            );
        }


        /*
         * Output:
         *
         * The name is null.
         */


        /*
         * ============================================================
         * 3. CUSTOM UNCHECKED EXCEPTION
         * ============================================================
         *
         * InvalidAmountException extends RuntimeException.
         *
         * Therefore it is unchecked.
         */

        try {

            deposit(-500);

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Deposit failed: " + e.getMessage()
            );
        }


        /*
         * Output:
         *
         * Deposit failed: Deposit amount cannot be negative.
         */


        /*
         * ============================================================
         * 4. CUSTOM CHECKED EXCEPTION
         * ============================================================
         *
         * InsufficientFundsException extends Exception.
         *
         * Therefore it is CHECKED.
         *
         * withdraw() declares:
         *
         *      throws InsufficientFundsException
         *
         * So the caller must deal with it.
         */

        try {

            withdraw(1500);

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Withdrawal failed: " + e.getMessage()
            );
        }


        /*
         * Output:
         *
         * Withdrawal failed: Insufficient funds.
         */


        /*
         * ============================================================
         * 5. MULTIPLE CATCH BLOCKS
         * ============================================================
         *
         * Different exceptions can be handled differently.
         */

        try {

            String value = "abc";

            int number = Integer.parseInt(value);

            System.out.println(number);

        } catch (NumberFormatException e) {

            System.out.println(
                    "The string is not a valid number."
            );

        } catch (RuntimeException e) {

            /*
             * This catches other RuntimeExceptions.
             *
             * Important:
             *
             * More specific exceptions should come BEFORE
             * their more general parent exception.
             */

            System.out.println(
                    "Some other runtime problem occurred."
            );
        }


        /*
         * Output:
         *
         * The string is not a valid number.
         */


        /*
         * ============================================================
         * 6. FINALLY
         * ============================================================
         *
         * finally is used for cleanup code that should happen
         * after the try/catch operation.
         *
         * It generally runs whether an exception occurs or not.
         */

        try {

            System.out.println("Inside try.");

        } catch (Exception e) {

            System.out.println("Exception occurred.");

        } finally {

            System.out.println("Finally executed.");
        }


        /*
         * Output:
         *
         * Inside try.
         * Finally executed.
         */


        /*
         * ============================================================
         * 7. CHECKED EXCEPTION FROM JAVA'S FILE API
         * ============================================================
         *
         * Files.readString() can throw IOException.
         *
         * IOException is a CHECKED exception.
         *
         * Therefore we must handle it or declare it.
         *
         * Here we choose to handle it.
         */

        try {

            String content =
                    Files.readString(
                            Path.of("data.txt")
                    );

            System.out.println(content);

        } catch (IOException e) {

            System.out.println(
                    "Could not read the file."
            );
        }


        /*
         * Notice the difference:
         *
         * Files.readString()
         *       ↓
         * IOException
         *       ↓
         * Checked Exception
         *       ↓
         * Compiler requires handling or propagation.
         */


        /*
         * ============================================================
         * 8. THROW
         * ============================================================
         *
         * throw is an ACTION.
         *
         * It actually creates/throws an exception.
         *
         * Example:
         *
         *      throw new InvalidAmountException(...);
         */

        try {

            deposit(-100);

        } catch (InvalidAmountException e) {

            System.out.println(
                    "Caught thrown exception: "
                            + e.getMessage()
            );
        }


        /*
         * Output:
         *
         * Caught thrown exception:
         * Deposit amount cannot be negative.
         */


        /*
         * ============================================================
         * 9. THROWS
         * ============================================================
         *
         * Look at withdraw():
         *
         *
         *      static void withdraw(double amount)
         *              throws InsufficientFundsException
         *
         *
         * throws is a DECLARATION.
         *
         * It tells the caller:
         *
         *      "This method may throw this checked exception."
         *
         * The caller must then either:
         *
         *      try-catch
         *
         * OR
         *
         *      propagate it further using throws.
         */


        try {

            processWithdrawal();

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Handled propagated exception: "
                            + e.getMessage()
            );
        }


        /*
         * Output:
         *
         * Handled propagated exception:
         * Insufficient funds.
         */


        /*
         * ============================================================
         * 10. TRY-WITH-RESOURCES
         * ============================================================
         *
         * This is the modern way to work with resources such as:
         *
         *      Files
         *      InputStreams
         *      OutputStreams
         *      Readers
         *      etc.
         *
         * The resource is automatically closed.
         *
         * We don't need to manually close it in finally.
         *
         */

        try (var reader =
                     Files.newBufferedReader(
                             Path.of("data.txt")
                     )) {

            String line = reader.readLine();

            System.out.println(
                    "First line: " + line
            );

        } catch (IOException e) {

            System.out.println(
                    "Could not read the file."
            );
        }


        /*
         * This is preferable to manually managing the resource
         * with finally.
         *
         * Java automatically closes 'reader'.
         */
    }


    /*
     * ================================================================
     * METHOD WITH UNCHECKED EXCEPTION
     * ================================================================
     *
     * No "throws" is required here because
     * InvalidAmountException extends RuntimeException.
     */

    static void deposit(double amount) {

        if (amount < 0) {

            /*
             * throw = actually throw the exception.
             */

            throw new InvalidAmountException(
                    "Deposit amount cannot be negative."
            );
        }

        System.out.println(
                "Deposited: " + amount
        );
    }


    /*
     * ================================================================
     * METHOD WITH CHECKED EXCEPTION
     * ================================================================
     *
     * InsufficientFundsException extends Exception.
     *
     * Therefore this method must either:
     *
     *      1. catch it
     *
     * OR
     *
     *      2. declare it using throws
     *
     * We choose option 2.
     */

    static void withdraw(double amount)
            throws InsufficientFundsException {

        double balance = 1000;

        if (amount > balance) {

            /*
             * Actually throw the exception.
             */

            throw new InsufficientFundsException(
                    "Insufficient funds."
            );
        }

        System.out.println(
                "Withdrawal successful: " + amount
        );
    }


    /*
     * ================================================================
     * EXCEPTION PROPAGATION
     * ================================================================
     *
     * This method calls withdraw().
     *
     * withdraw() may throw InsufficientFundsException.
     *
     * Instead of handling it here, we propagate it upward using
     * throws.
     */

    static void processWithdrawal()
            throws InsufficientFundsException {

        withdraw(2000);
    }
}

// Why are they called unchecked?
//Because the compiler doesn't force you to handle them.

//For example:
//int[] numbers = {1, 2, 3};
//System.out.println(numbers[10]);
//This can throw:
//ArrayIndexOutOfBoundsException

//But Java doesn't force you to write:
//try {
//    ...
//} catch (...) {
//    ...
//}
//The code can compile normally.
//That's why it's called unchecked.



// Checked Exceptions
// Java's compiler says:
//"You need to deal with this possibility."
//You have two choices.
//Option 1 — Handle it with try-catch
//You're saying:
//"I know this operation can fail, and I've decided how to handle that failure."
// Option 2 — Propagate it with throws
// You're saying:
//"I'm not going to handle this exception here. Whoever calls me has to deal with it."
// The responsibility moves upward.
// If nobody handles the checked exception, the compiler complains.

// Checked exceptions are exceptions that the Java compiler requires you to handle or declare.
//The compiler isn't checking whether the exception actually occurs.
//For example:
//Files.readString(path);
//The compiler doesn't know:
//"The file will definitely fail."
//It knows:
//"This method can throw IOException, and Java's rules require the caller to deal with that possibility."
//That's a much better mental model.

// Why does Java have checked exceptions?
//The idea is that some failures are reasonably expected consequences of interacting with the outside world.
//For example:
//Your program
//     │
//     ├── File system
//     ├── Database
//     ├── Network
//     └── External service
//Those things can fail independently of your program.
//For example:
//Files.readString(...)
//The compiler forces you to acknowledge:
//"Reading this file may fail."
//This is why APIs such as older Java I/O APIs have many checked exceptions.



// throw vs throws
// This is probably the single most important distinction to memorize.

// throw
// Actually throws an exception.
// throw new InvalidAmountException(
//       "Deposit amount cannot be negative."
//);

// Think:
//throw = DO IT
//throws
//Declares that a method might propagate an exception.

//static void withdraw(double amount)
//        throws InsufficientFundsException {
// Think:
// throws = WARNING / DECLARATION
// So:

// throw
//   ↓
// "Throw this exception."

// throws
//   ↓
// "This method may throw this exception."




// Exception propagation
// This is another concept worth understanding deeply.

// Imagine:
// main()
//   │
//   ▼
// processWithdrawal()
//   │
//   ▼
// withdraw()
//   │
//   ▼
// throw InsufficientFundsException

// withdraw() doesn't handle it.
// So it propagates upward:

// withdraw()
//     ↓
// processWithdrawal()
//     ↓
// main()

// Eventually main() handles it:
// try {
//     processWithdrawal();
// } catch (InsufficientFundsException e) {
//     ...
// }
//So throws essentially allows the exception to travel up the call stack.

// Error is:
// Usually a serious JVM/system problem, not something application code is expected to recover from.



// Users can define their own exceptions by extending the Exception class.
// you can have both checked and unchecked exception as a choice
// The choice communicates how you expect callers to deal with the failure.


//                          Throwable
//                             │
//                  ┌──────────┴──────────┐
//                  │                     │
//                Error               Exception
//                  │                     │
//           serious JVM             ┌────┴────┐
//             problems              │         │
//                              RuntimeException  Other
//                                    │         Exceptions
//                                    │             │
//                                    ▼             ▼
//                                UNCHECKED      CHECKED
//                                    │             │
//                              compiler does    compiler requires
//                              not force you    try-catch OR throws
//                                    │             │
//                                    └──────┬──────┘
//                                           │
//                                    Exception handling
//                                           │
//                    ┌──────────────────────┼───────────────────┐
//                    │                      │                   │
//                 try-catch              throw              throws
//                    │                      │                   │
//              handle problem        throw problem        declare/propagate
//                    │
//                    ▼
//                 finally
//                    │
//                    ▼
//              cleanup code
//
//             try-with-resources
//                    │
//                    ▼
//        automatic resource cleanup


// The big picture is:
// An exception is an object representing an exceptional condition. Java's exception mechanism lets that
// condition travel up the call stack until some code decides to handle it. Checked exceptions make the
// compiler force you to acknowledge certain failures; unchecked exceptions don't. throw creates/throws
// the failure, while throws declares that a method may pass it upward.