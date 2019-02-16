package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;

    private final int balance;

    AccountData(int id, String name, String email, int balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    public String idToString() {
        return "" + id;
    }

    public String nameToString() {
        return name;
    }

    public String emailToString() {
        return email;
    }

    public String balanceToString() {
        return "" + balance;
    }

    public boolean overdrawn() {
        boolean overdrawn;
        if (balance < 0) {
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public String toString() {
//        if (balance > 0) {
//            return "Account id: " + id + '\n' +
//                    "Name: " + name + '\n' +
//                    "Email: " + email + '\n' +
//                    "Balance: " + balance;
//        } else {
//            return "Account id: " + id + '\n' +
//                    "Name: " + name + '\n' +
//                    "Email: " + email + '\n' +
//                    "Balance: " + balance + '\n' + '\n' +
//                    "ALERT! Your account is currently overdrawn.";
//        }
    }

