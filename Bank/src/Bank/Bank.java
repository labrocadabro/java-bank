package Bank;

import java.util.ArrayList;

public class Bank {
    private String name;
    private final ArrayList<Account> openAccountList = new ArrayList<>();
    private final ArrayList<Account> closedAccountList = new ArrayList<>();
    private final ArrayList<Customer> customerList = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Account> getOpenAccountList() {
        return openAccountList;
    }

    public ArrayList<Account> getClosedAccountList() {
        return closedAccountList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer addCustomer(String name) {
        Customer newCustomer = new Customer(name);
        addCustomerToCustomerList(newCustomer);
        return newCustomer;

    }
    private void addCustomerToCustomerList(Customer customer) {
        customerList.add(customer);
    }

    public Account openAccount(Customer customer, double amount) {
        Account newAccount = new Account(amount);
        openAccountList.add(newAccount);
        customer.addAccount(newAccount);
        return newAccount;
    }
    public Account openAccount(Customer customer) {
        Account newAccount = openAccount(customer, 0.00);
        return newAccount;
    }
}
