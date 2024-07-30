import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Accounts {

  // Instiance Variables
  private int accountNumber;
  private String password;
  private double balance;

  ArrayList<Integer> Accounts = new ArrayList<Integer>();

  // Accounts toString Method
  public String toString() {
    return "Account Number: " + getAccountNumber()
        + "\nCurrent Account Balance: " + getBalance();
  }

  // Instiate Scanner object
  Scanner input = new Scanner(System.in);

  // -----------------Getters and Setters-------------------------
  public int getAccountNumber() { // |
    return accountNumber; // |
  } // |
    // |

  public void setAccountNumber(int accountNumber) { // |
    this.accountNumber = accountNumber; // |
  } // |
    // |

  public String getPassword() { // |
    return password; // |
  } // |
    // |

  public double getBalance() { // |
    return balance; // |
  } // |
    // |

  public void setBalance(double balance) { // |
    this.balance = balance; // |
  } // |
    // |

  public void setPassword(String password) { // |
    this.password = password; // |
  } // |

  public ArrayList<Integer> getAccounts() { // |
    return Accounts; // |
  } // |
  // ------------------------------------------------------------|

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

  // Method that checks to see if a user enters in a valid password
  private boolean isValidPassword(String password) {
    Pattern letter = Pattern.compile("[a-zA-Z]");
    Pattern digit = Pattern.compile("[0-9]");
    return letter.matcher(password).find() && digit.matcher(password).find();
  }

  // Saves user account Information to text file
  public void saveAccountInfo() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
      writer.write("Account number: " + getAccountNumber());
      writer.write("\nPassword: " + getPassword());
      writer.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

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

    // Reads file and keeps track of last entered Account Information
    try {
      BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.startsWith("Account number: ")) {
          localFileAccountNumber = line.substring(16); // Extracts the account number
        } else if (line.startsWith("Password: ")) {
          localFilePassword = line.substring(10); // Extracts the password
        }
      }
      reader.close();
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