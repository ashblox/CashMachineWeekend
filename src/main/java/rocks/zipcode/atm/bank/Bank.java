package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", false, 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", true,200
        )));

        accounts.put(3000, new PremiumAccount(new AccountData(3000, "Example 3", "example3@gmail.com", false,100)
        ));

        accounts.put(4000, new BasicAccount(new AccountData(4000, "Example 4", "example4@gmail.com", false, 450)));

        accounts.put(5000, new PremiumAccount(new AccountData(5000, "Example 5","example5@gmail.com", false,300)));

        accounts.put(6000, new PremiumAccount(new AccountData(
                6000, "Example 2", "example2@gmail.com", true, 0
        )));

        accounts.put(7000, new BasicAccount(new AccountData(
                7000, "Example 2", "example2@gmail.com", true, 1000
        )));

    }

    public ArrayList<Integer> getAllAccounts(String email) {
        ArrayList<Integer> allAccounts = new ArrayList<>();
        for (Map.Entry<Integer, Account> entry: accounts.entrySet()) {
            Account current = entry.getValue();
            if (current.getEmail() == email) {
                allAccounts.add(current.getId());
            }
        }
        return allAccounts;
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
