import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int INITIAL_BANK_ACCOUNT_BALANCE = 0;
    public static final int FIRST_USER_ID = 1;
    public static final int FIRST_DEPOSIT = 100;
    public static final int FIRST_WITHDRAW = 70;
    public static final int SECOND_USER_ID = 2;
    public static final int WITHDRAWAL_FEE = 1;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;


    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", FIRST_USER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BANK_ACCOUNT_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BANK_ACCOUNT_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        assertEquals(FIRST_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        int another_deposit = 50;
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        bankAccount.deposit(SECOND_USER_ID, another_deposit);
        assertEquals(FIRST_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        bankAccount.withdraw(accountHolder.getId(), FIRST_WITHDRAW);
        assertEquals(FIRST_DEPOSIT - FIRST_WITHDRAW - WITHDRAWAL_FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        bankAccount.withdraw(SECOND_USER_ID, FIRST_WITHDRAW);
        assertEquals(FIRST_DEPOSIT, bankAccount.getBalance());
    }
}
