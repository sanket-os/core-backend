public class bankAccount {

    private int balance;
    String bankName;
    protected String accountType;
    public String accountHolder;

    public bankAccount(int balance, String bankName, String accountType, String accountHolder) {
        this.balance = balance;
        this.bankName = bankName;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void transfer(int amount) {
        if (amount < balance)
            balance -= amount;
    }

    public void deposit(int amount) {
        if (amount > 0)
            balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void balanceInfo() {
        System.out.println(getBalance());
    }

     String getAccountType() {
        return accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void displayAccountInfo() {
        System.out.println("Bank Balance = "+ balance);
        System.out.println("Bank Name = "+ bankName);
        System.out.println("Bank Type = "+ accountType);
        System.out.println("Bank Account Holder = "+ accountHolder);
    }

}
