public class BankAccount {

    String ownerName;
    double balance;

    void deposit(double depositAmount) {
        if (depositAmount > 0)
        {
            balance = balance + depositAmount;
            System.out.println("New balance: " + balance);
        }
    }

    void withdraw(double withdrawalAmount) {
        if (balance >= withdrawalAmount) {
            balance = balance - withdrawalAmount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient funds for this withdrawal.");
        }
    }
}
