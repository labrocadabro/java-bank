package Bank;

import java.util.UUID;

class Customer {
    private String name;
    private final String customerID = UUID.randomUUID().toString();
    private Account account;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Account getAccount() {
        return account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAccount(Account a) {
        account = a;
        a.addAccountOwner(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
