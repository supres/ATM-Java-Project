import java.io.IOException;

public class ATM {
    public static void main(String[] args) {
        // Instiate Accounts object
        Accounts accounts = new Accounts();

        // Instiate Transaction object
        Transactions tactions = new Transactions();
        // accounts.createUserAccountNumber();
        // accounts.createUserAccountPassword();
        // accounts.saveAccountInfo();
        accounts.loginAccount();
    }
}
