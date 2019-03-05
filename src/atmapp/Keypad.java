package atmapp;
import java.util.*;
import java.util.Scanner;

public class Keypad {
    private Scanner input; // reads data from the command line

    public Keypad() {
        input = new Scanner(System.in);    
    } 

    public int getInput() {
//        return input.nextInt(); // user enters an integer
        try{
            return input.nextInt();
        } // user enters an integer
        catch(InputMismatchException ex){
            System.out.println("Input Mismatch Exception..");
            input.nextLine();     // clean buffer
            return 0;
        }
    } 
} 