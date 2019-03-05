package atmapp;
import java.util.*;
// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public Withdrawal(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      
      // -- NAMBAH INI
      amount = 0;
      keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
      
   }

   // perform transaction
   @Override
   public void execute() {
       double availableBalance;
       Screen screen = super.getScreen();
       amount = displayMenuOfAmounts();
       if(amount != 0){
            if(cashDispenser.isSufficientCashAvailable(amount)){
                BankDatabase atmBankDatabase = super.getBankDatabase();
                availableBalance =
                        atmBankDatabase.getAvailableBalance(
                                super.getAccountNumber());
                if(amount <= availableBalance){
                    cashDispenser.dispenseCash(amount);
                    atmBankDatabase.getAccount(super.getAccountNumber()).
                            credit(amount);
                    screen.displayMessageLine("Your cash has been dispensed. "
                            + "Please take your cash now.");
                }
                else {
                    screen.displayMessageLine("Your balance is not enough "
                        + "to make a withdrawal");
                    screen.displayMessage("Balance : ");
                    screen.displayDollarAmount(availableBalance);
                    screen.displayMessageLine("");
                    screen.displayMessage("Amount Withdrawal : ");
                    screen.displayDollarAmount(amount);
                }
            }
            else {
                screen.displayMessageLine("Bills is insufficient in "
                        + "cash dispensers");
            }
       }
       else {
           screen.displayMessageLine("Canceling transaction...");
       }
   } 

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nWithdrawal Menu:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            // if the user chose a withdrawal amount
            // (i.e., chose option 1, 2, 3, 4 or 5), return the
            // corresponding amount from amounts array
            
            // -- NAMBAH INI
            case 1: 
               userChoice = amounts[input]; // save user's choice
               break; 
            case 2: 
               userChoice = amounts[input]; // save user's choice
               break; 
            case 3: 
               userChoice = amounts[input]; // save user's choice
               break; 
            case 4:
               userChoice = amounts[input]; // save user's choice
               break;  
            case 5:
               userChoice = amounts[input]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = amounts[input - CANCELED]; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
} 