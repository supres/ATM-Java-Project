import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ATM{
    public static void main(String[] args) {
        // Instiate Accounts object
        Accounts accounts = new Accounts();

        // Instiate Transaction object
        Transactions tactions = new Transactions();
        accounts.createUserAccountNumber();
        accounts.createUserAccountPassword();
        accounts.saveAccountInfo();
    }
}

