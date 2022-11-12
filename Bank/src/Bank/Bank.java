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

    public void changeName() {

    }

    public void addCustomer(String name) {
        Customer newCustomer = new Customer(name);
        customerList.add(newCustomer);
    }
}
