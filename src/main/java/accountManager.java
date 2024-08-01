package main.java;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class accountManager {

  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  // Instiate Scanner object
  Scanner input = new Scanner(System.in);

  public boolean loginAccount() {
    String AccountNumToCheck = "";
    String AccountPwToCheck = "";

    // Try catch block if the user enters in anything else other than numbers
    System.out.println("Enter your Account number: ");
    try {
      AccountNumToCheck = input.nextLine(); // KEEPS TRACK OF ACCOUNT NUM USER INPUTED
    } catch (InputMismatchException e) {
      e.printStackTrace();
      System.out.println("Enter only numbers please");
      input.next();
      loginAccount(); // recursion as error handling
    }

    // Try catch block if the user enters in anything else other than numbers
    System.out.println("Enter your Accounts Password");
    try {
      AccountPwToCheck = input.nextLine();// KEEPS TRACK OF ACCOUNT PASSWORD USER INPUTED
    } catch (InputMismatchException e) {
      e.printStackTrace();
      System.out.println("Enter only numbers please");
      input.next();
      loginAccount(); // recursion as error handling
    }
    String localFileAccountNumber = "";
    String localFilePassword = "";

    // Reads users.json file and keeps track of last entered Account Information
    try(FileReader reader = new FileReader("users.json");) {
      User[] usersArray = gson.fromJson(reader, User[].class);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Use the account number and password variables as needed
    System.out.println("Account Number: " + localFileAccountNumber);
    System.out.println("Password: " + localFilePassword);

    if ((localFileAccountNumber.equalsIgnoreCase(AccountNumToCheck)) 
       && (localFilePassword.equalsIgnoreCase(AccountPwToCheck))){
      System.out.println("Login was successful");
      return true;
    }
    else {
      System.out.println("Account Number or Password was incorrect. Double check your credentials");
    }
  return false;
    
  }
}