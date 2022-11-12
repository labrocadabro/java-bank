package Bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    Bank bank = new Bank("Bank Brothers");
    @Test
    void addNewCustomer() {
        bank.addCustomer("Terry");
        assertEquals("Terry", bank.getCustomerList().get(0).getName());
        assertEquals(1, bank.getCustomerList().size());
    }
    @Test
    void addTwoCustomersWithSameName() {
        bank.addCustomer("Joe");
        bank.addCustomer("Joe");
        assertEquals("Joe", bank.getCustomerList().get(0).getName());
        assertEquals("Joe", bank.getCustomerList().get(1).getName());
        assertNotEquals(bank.getCustomerList().get(0).getCustomerID(),
                bank.getCustomerList().get(1).getCustomerID());
        assertEquals(2, bank.getCustomerList().size());
    }
    @Test
    void openNewAccountWithNoBalance() {
        Customer terry = bank.addCustomer("Terry");
        Account account = bank.openAccount(terry);
        assertEquals(terry.getAccount(), account);
        assertEquals(account.getCustomer(), terry);
        assertEquals(bank.getCustomerList().get(0).getAccount(),
                bank.getOpenAccountList().get(0));
        assertEquals(bank.getOpenAccountList().get(0).getCustomer(),
                bank.getCustomerList().get(0));
    }
    void openNewAccountWithNegativeBalance() {
        Customer terry = bank.addCustomer("Terry");
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> bank.openAccount(terry, -100.00));
        String expectedMessage = "Balance cannot be negative";
        assertEquals(expectedMessage, thrown.getMessage());
        assertNull(terry.getAccount());
    }
    @Test
    void openNewAccountWithBalance() {
        Customer terry = bank.addCustomer("Terry");
        Account account = bank.openAccount(terry, 100.00);
        assertEquals(100.00, account.getBalance());
        assertEquals(100.00, terry.getAccount().getBalance());
        assertEquals(terry.getAccount(), account);
        assertEquals(account.getCustomer(), terry);
        assertEquals(bank.getCustomerList().get(0).getAccount(),
                bank.getOpenAccountList().get(0));
        assertEquals(bank.getOpenAccountList().get(0).getCustomer(),
                bank.getCustomerList().get(0));
    }
    @Test
    void depositPositiveAmount() {
        Account a = new Account();
        a.deposit(25.00);
        assertEquals(25.00, a.getBalance(), 0.001);
        a.deposit(50.00);
        assertEquals(75.00, a.getBalance(), 0.001);
    }    @Test
    void depositNegativeAmount() {
        Account a = new Account(25.00);
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> a.deposit(-25.00));
        String expectedMessage = "Cannot deposit a negative amount";
        assertEquals(expectedMessage, thrown.getMessage());
        assertEquals(25.00, a.getBalance(), 0.001);
    }
    @Test
    void withdrawFromAccountWithInsufficientBalance() {
        Account a = new Account();
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> a.withdraw(25.00));
        String expectedMessage = "Cannot withdraw more than the balance";
        assertEquals(expectedMessage, thrown.getMessage());
        assertEquals(0.00, a.getBalance(), 0.001);
    }
    @Test
    void withdrawFromAccountWithSufficientBalance() {
        Account a = new Account(30.00);
        a.withdraw(12.73);
        assertEquals(17.27, a.getBalance(), 0.001);
    }
    @Test
    void withdrawNegativeAmount() {
        Account a = new Account();
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () -> a.withdraw(-25.00));
        String expectedMessage = "Cannot withdraw a negative amount";
        assertEquals(expectedMessage, thrown.getMessage());
        assertEquals(0.00, a.getBalance(), 0.001);
    }
    @Test
    void linkAccountAndCustomer() {
        Account a = new Account();
        Customer c = new Customer("Joe");
        c.addAccount(a);
        assertEquals(c, a.getCustomer());
        assertEquals(a, c.getAccount());
    }
    @Test
    void transferSufficientMoneyInAccount() {
        Account a1 = new Account(100.00);
        Customer c1 = new Customer("Joe");
        c1.addAccount(a1);
        Account a2 = new Account();
        Customer c2 = new Customer("Kyle");
        c2.addAccount(a2);

        a1.transfer(a2, 34.72);
        assertEquals(65.28, c1.getAccount().getBalance());
        assertEquals(34.72, c2.getAccount().getBalance());
    }
    @Test
    void transferInsufficientMoneyInAccount() {
        Account a1 = new Account();
        Customer c1 = new Customer("Joe");
        c1.addAccount(a1);
        Account a2 = new Account();
        Customer c2 = new Customer("Kyle");
        c2.addAccount(a2);
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () ->  a1.transfer(a2, 34.72));

        String expectedMessage = "Cannot transfer more than the balance";
        assertEquals(expectedMessage, thrown.getMessage());
        assertEquals(0.00, c1.getAccount().getBalance());
        assertEquals(0.00, c2.getAccount().getBalance());
    }    @Test
    void transferNegativeAmount() {
        Account a1 = new Account(100.00);
        Customer c1 = new Customer("Joe");
        c1.addAccount(a1);
        Account a2 = new Account();
        Customer c2 = new Customer("Kyle");
        c2.addAccount(a2);
        Exception thrown = assertThrows(IllegalArgumentException.class,
                () ->  a1.transfer(a2, -34.72));

        String expectedMessage = "Cannot transfer a negative amount";
        assertEquals(expectedMessage, thrown.getMessage());
        assertEquals(100.00, c1.getAccount().getBalance());
        assertEquals(0.00, c2.getAccount().getBalance());
    }
}