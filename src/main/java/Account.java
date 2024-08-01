package main.java;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Account extends accountManager{
    
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  // Instiance Variables
  private int accountNumber;
  private String password;
  private double balance;
  public String name;

// Getters and Setters
  public int getAccountNumber() {                           
    return accountNumber;                                  
  }                                                         
                                                                                      
  public void setAccountNumber(int accountNumber) {         
    this.accountNumber = accountNumber;                     
  }                                                         
                                                                                                                     
  public String getPassword() {                             
    return password;                                        
  }                                                         
                                                                                                            
  public double getBalance() {                             
    return balance;                                         
  }          
  
  public String getName() {
    return name;
  }
                                                                                                                     
  public void setBalance(double balance) {                  
    this.balance = balance;                                 
  }                                                         
                                                                                                                   
  public void setPassword(String password) {                
    this.password = password;                               
  }                       
  
  public void setName(String name) {
    this.name = name;
  }
    
  // Creates a user account number
   public void createUserAccountNumber() {
    
    // Prompt the user if they would like to create their own account num
    System.out.println("Would you like to create a 8 digit account number"
        + "(type 'yes' or 'no')");
    String promptResponse = input.nextLine();

    // If the user says no, generate one
    if (promptResponse.equalsIgnoreCase("no")) {
      double randNum = Math.random();
      setAccountNumber((int) (10000000 + randNum * 90000000));
      System.out.println("Your randomly generated account number is: " + accountNumber);
    }

    // If the user says yes have them create one
    else if (promptResponse.equalsIgnoreCase("yes")) {
      System.out.println("Enter a 8 digits that you would like your account number to be");
      String responseInput = input.nextLine();

      // checks to see if the account number is 8 digits
      if (responseInput.matches("\\d{8}")) {
        setAccountNumber(Integer.parseInt(responseInput));
        System.out.println("Entered Account number:" + accountNumber);
      }
      // Error handling if number entered isnt 8 digits
      else {
        System.out.println("Invalid input. Please enter exactly 8 digits.");
        createUserAccountNumber(); // recursion
      }
    }

    // Error handling if the use types anything besides 'yes' or 'no'
    else {
      System.out.println("\"Invalid response. Please type 'yes' or 'no'.");
      createUserAccountNumber(); // recursion
    }
  }

  // Creates a user Account Password
  public void createUserAccountPassword() {
    while (true) {
      System.out.println("Enter a password for your account");
      String localPassword = input.nextLine();
      if (isValidPassword(localPassword)) {
        System.out.println("Password Created.");
        setPassword(localPassword);
        break;
      } else {
        System.out.println("Incorrect, please ensure your password contains a number and letters");
        createUserAccountPassword(); // Recursion
      }
    }
  }

  // Prompts the user for their name 
  public void createUserAccountName() {
    System.out.println("What is your full name:");
    setName(input.nextLine());
  }

  // Creates a users final account 
  public void createUserFinalAccount() {
    createUserAccountName();
    createUserAccountNumber();
    createUserAccountPassword();
  }

  // Method that checks to see if a user enters in a valid password
  private boolean isValidPassword(String password) {
    Pattern letter = Pattern.compile("[a-zA-Z]");
    Pattern digit = Pattern.compile("[0-9]");
    return letter.matcher(password).find() && digit.matcher(password).find();
  }

  // Saves user account Information to text file
  public void saveAccountInfo() {

    // read from the json file into an array
    // on first read this will be empty
    // add this account to the array of users
    // then save file back out

    User[] newData = null;
    User newUser = new User(getAccountNumber(), getPassword(), getName());

    try (FileReader reader = new FileReader("users.json")) {
            User[] usersArray = gson.fromJson(reader, User[].class);
            if (usersArray == null)
              usersArray = new User[0];
            // This block always adds a new user
            newData = new User[usersArray.length + 1];
            System.arraycopy(usersArray, 0, newData, 0, usersArray.length);
            newData[newData.length - 1] = newUser;

        } catch (IOException e) {
            e.printStackTrace();
            newData = new User[1];
            newData[0] = newUser;
        }

      // Writes out serilizaed user object to json file  
      try (FileWriter writer = new FileWriter("users.json")) {
          gson.toJson(newData, writer);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  // Account toString Method
    public String toString() {
        return "Account Number: " + getAccountNumber()
            + "\nCurrent Account Balance: " + getBalance();
      }
}
