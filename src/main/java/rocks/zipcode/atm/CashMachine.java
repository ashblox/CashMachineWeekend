package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;

    public CashMachine(Bank bank) {
        this.bank = bank;
    }

    private Consumer<AccountData> update = data -> {
        accountData = data;
    };

    public void login(int id) {
        tryCall(
                () -> bank.getAccountById(id),
                update
        );
    }

    public boolean accountActive() {
        boolean accountActive;
        if (accountData != null) {
            accountActive = true;
        } else {
            accountActive = false;
        }
        return accountActive;
    }

    public void deposit(double amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update
            );
        }
    }

    public void withdraw(double amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }
    }

    public void exit() {
        if (accountData != null) {
            accountData = null;
        }
    }

//    @Override
//    public String toString() {
//        return accountData != null ? accountData.toString() : "Try account 1000 or 2000 and click submit.";
//    }

    public String idToString() {
        return accountData != null ? accountData.idToString() : "No Account";
    }

    public Integer getId() {
        return accountData != null ? accountData.getId() : null;
    }

    public String nameToString() {
        return accountData != null ? accountData.nameToString() : "";
    }

    public String emailToString() {
        return accountData != null ? accountData.emailToString() : "";
    }

    public String balanceToString() {
        return accountData != null ? accountData.balanceToString() : "";
    }

    public boolean overdrawn () {
        return accountData != null ? accountData.overdrawn() : false;
    }

    public boolean hasMultAccounts() {
        return accountData != null ? accountData.hasMultipleAccounts() : false;
    }

    public ArrayList<Integer> findAccounts() {
        return bank.getAllAccounts(accountData.emailToString());
    }

    private <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                T data = result.getData();
                postAction.accept(data);
            } else {
                String errorMessage = result.getErrorMessage();
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
