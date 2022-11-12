package Bank;

import java.util.UUID;

class Account {
    private final String accountNumber = UUID.randomUUID().toString();
    private double balance;
    private Customer accountOwner;
    private String accountType;

    public Account(double balance) {
        this.balance = balance;
    }
    public Account() {
        this(0.00);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getAccountOwner() {
        return accountOwner;
    }

    public void deposit(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Cannot deposit a negative amount");
        balance += amount;
    }

    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount < 0) throw new IllegalArgumentException("Cannot withdraw a negative amount");
        if (amount > balance) throw new IllegalArgumentException("Cannot withdraw more than the balance");
        balance -= amount;
    }

    void addAccountOwner(Customer customer) {
        accountOwner = customer;
    }

    @Override
    public String toString() {
        return accountNumber + ": $" + balance;
    }

    public void transfer(Account account, double amount) throws IllegalArgumentException  {
        if (amount < 0) throw new IllegalArgumentException("Cannot transfer a negative amount");
        if (amount > balance) throw new IllegalArgumentException("Cannot transfer more than the balance");
        this.withdraw(amount);
        account.deposit(amount);
    }
}

