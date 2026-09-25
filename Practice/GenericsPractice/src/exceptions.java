import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
};

class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
};


public class exceptions {
    public static void main(String[] args) {

        try {
            int a = 10;
            int b = 0;

            int result = a / b;

            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println(
                    "Cannot divide by zero"
            );
        }


        try {
            String name = null;

            System.out.println(name.length());

        } catch (NullPointerException e) {
            System.out.println(
                    "The name is null."
            );
        }


        try {
            deposit(-500);
        } catch (InvalidAmountException e) {
            System.out.println(
                    ("Deposit failed: " + e.getMessage())
            );
        }


        try {
            withdraw(1500);
        } catch (InsufficientFundsException e) {
            System.out.println(
                    ("Withdrawal failed: " + e.getMessage())
            );
        }


        try {
            String value = "abc";
            int number = Integer.parseInt(value);
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println(
                    "The string is not a valid number."
            );
        } catch (RuntimeException e) {
            System.out.println(
                    "Some other runtime problem occurred."
            );
        }


        try {
            System.out.println("Inside try.");
        } catch (Exception e) {
            System.out.println("Exception occurred.");
        } finally {
            System.out.println("Finally executed.");
        }


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


        try {
            deposit(-100);
        } catch (InvalidAmountException e) {
            System.out.println(
                    "Caught thrown exception: "
                        + e.getMessage()
            );
        }


        try {
            processWithdrawal();
        } catch (InsufficientFundsException e) {
            System.out.println(
                    "Handled propagated exception: "
                    + e.getMessage()
            );
        }


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

    }

    static void deposit(double amount) {
        if (amount < 0) {
            throw new InvalidAmountException(
                    "Deposit amount cannot be negative."
            );
        }

        System.out.println(
                "Deposited: " + amount
        );
    }


    static void withdraw(double amount)
        throws InsufficientFundsException {

        double balance = 1000;

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds."
            );
        } else if (amount < 0) {
            throw new InvalidAmountException(
                    "Amount cannot be negative"
            );
        }

        System.out.println(
                "Withdrawal successful: " + amount
        );
    }

    static void processWithdrawal()
            throws InsufficientFundsException {
        withdraw(5000);
    }


}