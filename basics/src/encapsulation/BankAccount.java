package encapsulation;

/*

encapsulation is mainly achieved using:

private - be Accessible ONLY inside same class otherwise completely hidden
default (package-private) - be accessible only inside the company/package
protected - be accessible to package & child classes
public - be available to everyone

access modifiers.

*/

public class BankAccount {

    private double balance; // Best for sensitive/internal data

    String bankName; // no modifier, Accessible only inside same package

    protected String accountType; // accessible inside same package and child classes (inheritance)

    public String accountHolder; // Accessible from anywhere


    // constructor
    public BankAccount(
            String accountHolder,
            double balance,
            String bankName,
            String accountType
    ) {
        this.balance = balance; // private field initialized internally

        this.bankName = bankName;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
    }

    // PUBLIC GETTER - Controlled access to private field
    public double getBalance() {
        return balance;
    }

    // PUBLIC METHOD - Controlled modification of private field
    public void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
        }

    }

    // PRIVATE METHOD - Internal helper logic
    private void logTransaction() {

        System.out.println("Transaction logged internally");

    }

    // PUBLIC METHOD USING PRIVATE METHOD
    public void transfer(double amount) {

        if (amount <= balance) {
            balance -= amount;

            logTransaction(); // private method called internally
        }

    }

    // DISPLAY METHOD
    public void display() {

        System.out.println("Account Holder : " + accountHolder);

        System.out.println("Balance        : " + balance);

        System.out.println("Bank Name      : " + bankName);

        System.out.println("Account Type   : " + accountType);

    }


    // MAIN METHOD
    public static void main(String[] args) {

        BankAccount account = new BankAccount(
                "Sam",
                5000,
                "SBI",
                "Savings"

        );

        System.out.println(account.accountHolder); // PUBLIC FIELD - Accessible directly

        System.out.println(account.balance); //  PRIVATE FIELD -  ❌ NOT accessible directly
        // throws ERROR

        System.out.println(account.getBalance()); // CONTROLLED ACCESS USING GETTER

        account.deposit(3000); // CONTROLLED MODIFICATION

        account.transfer(1000); // PUBLIC METHOD

        account.display(); // DISPLAY DATA

    }

}

// instead of exposing private info like balance, quite dangerous
// we expose controlled methods like deposit(), transfer(), display()

// The class controls: valid data, invalid operations, security, business rules

// Encapsulation is NOT just: private variables + getters/setters
// That’s incomplete.

// Real encapsulation means: The object controls its own state safely.

// Example: if (amount > 0)
// inside deposit()
//That is true encapsulation.
//The object protects itself from invalid state.