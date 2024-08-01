package main.java;

public class User {
    private int accountnumber;
    private String password;
    private String name;

    public User(int accountnumber, String password, String name) {
        this.accountnumber = accountnumber;
        this.password = password;
        this.name = name;
    }

    public int getAccountNumber() {
        return accountnumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
