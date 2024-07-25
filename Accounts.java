import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.math.*;
public class Accounts{

  // Instiance Variables
  private int accountNumber;
  private String password;

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
  public String getPassword() {                                // |
    return password;                                           // | 
  }                                                            // |
                                                               // | 
  public void setPassword(String password) {                   // |
    this.password = password;                                  // |
  }                                                            // |
  public ArrayList<Integer> getAccounts() {                    // |
    return Accounts;                                           // |
  }                                                            // |
   // ------------------------------------------------------------|                                                               


  // Creates a user account number
  public void createUserAccountNumber(){
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
    // If the user says yes have them create one
    else if (promptResponse.equalsIgnoreCase("yes")){
      System.out.println("Enter a 8 digits that you would like your account number to be");
      String responseInput = input.nextLine();
      
      // checks to see if the account number is 8 digits
      if (responseInput.matches("\\d{8}")){
        accountNumber = Integer.parseInt(responseInput);
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
 public void createUserAccountPassword(){
  while (true){
    System.out.println("Enter a password for your account");
    String localPassword = input.nextLine();
    if(isValidPassword(localPassword)){
      System.out.println("Password Created.");
      setPassword(localPassword);
      break;
    }
    else{
      System.out.println("Incorrect");
    }
  }
 }

 private boolean isValidPassword(String password){
  Pattern letter = Pattern.compile("[a-zA-Z]");
  Pattern digit = Pattern.compile("[0-9]");
  return letter.matcher(password).find() && digit.matcher(password).find();
 }
}