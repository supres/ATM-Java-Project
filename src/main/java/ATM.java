package main.java;

public class ATM {
    public static void main(String[] args) {

        // Instiate Accounts object
        Account account = new Account();

        // Instiate Transaction object
        Transactions tactions = new Transactions();

        account.createUserFinalAccount(); // Creates a user an account (name-number-password) // 
        account.saveAccountInfo();
        account.loginAccount();
    }
}
