package atmapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author V
 */
public class ChangePin extends Transaction {
    private Keypad keypad; // reference to keypad                                         // reference to keypad
    private Screen screen;

    public ChangePin(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        screen = atmScreen;
    }
 
    @Override
    public void execute(){
        validatePIN(EnterNewPIN());
    }
    
    public void validatePIN(int newPIN){
        Screen screen = super.getScreen();
        if(newPIN == super.getBankDatabase().getLastPIN(super.getAccountNumber())){
            screen.displayMessageLine("Can't change to your current PIN");
        }else if(newPIN < 0){
            screen.displayMessageLine("Can't change to your PIN with minus");
        }else{
            super.getBankDatabase().setPin(super.getAccountNumber(),newPIN);
            screen.displayMessageLine("\nYou changed your PIN");
        }
    }
    
    public int EnterNewPIN(){
        screen.displayMessage("Input your new PIN : ");
        return keypad.getInput();
    }
}
