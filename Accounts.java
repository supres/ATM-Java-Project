import java.util.Scanner;
import java.util.ArrayList;
import java.math.*;
public class Accounts{

  // Instiance Variables
  private int accountNumber;
  private int password;

  ArrayList<Integer> Accounts = new ArrayList<Integer>();

  // Instiate Scanner object
  Scanner input = new Scanner(System.in);

  //  -----------------Getters and Setters-------------------------
  public int getAccountNumber() {                              // |
    return accountNumber;                                      // |
  }                                                            // |
                                                               // |
  public void setAccountNumber(int accountNumber) {            // |
    this.accountNumber = accountNumber;                        // |
  }                                                            // |
                                                               // |
  public int getPassword() {                                   // |
    return password;                                           // | 
  }                                                            // |
                                                               // | 
  public void setPassword(int password) {                      // |
    this.password = password;                                  // |
  }                                                            // |
  public ArrayList<Integer> getAccounts() {                    // |
    return Accounts;                                           // |
  }                                                            // |
   // ------------------------------------------------------------|                                                               


  // Creates a user account
  public void createAccount(){
    // Prompt the user if they would like to create their own account num
    System.out.println("Would you like to create a 8 digit account number"
    + "(type 'yes' or 'no')");
    String promptResponse = input.nextLine();

    // If the user says no, generate one
    if (promptResponse.equalsIgnoreCase("no")){
      double randNum = Math.random();
      accountNumber = (int) (10000000 + randNum * 90000000);
      System.out.println("Your randomly generated account number is: " + accountNumber);
    }
    // if the user says yes have them create one
    else if (promptResponse.equalsIgnoreCase("yes")){
      System.out.println("Enter a 8 digits that you would like your account number to be");
      String responseInput = input.nextLine();
      
      // checks to see if the account number is 8 digits
      if (responseInput.matches("\\d{8}")){
        accountNumber = Integer.parseInt(responseInput);
        System.out.println("Entered Account number:" + accountNumber);
    }
      // error handling if number entered isnt 8 digits
       else {
      System.out.println("Invalid input. Please enter exactly 8 digits.");
      createAccount(); // recursion
  } 
   }

   // error handling if the use types anything besides 'yes' or 'no'
  else {
    System.out.println("\"Invalid response. Please type 'yes' or 'no'.");
    createAccount(); // recursion 
   }
 }
}