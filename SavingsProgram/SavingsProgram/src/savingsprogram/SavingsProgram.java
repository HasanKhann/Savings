package savingsprogram;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**********************************
 * Project: Savings Account
 * Programmer: Hasan Khan
 * Date: May 30, 2020
 * Program Name: SavingsProgram
 **********************************/

class SavingsAccount {
    
    // defining rate and balance to doubles
    private double annualRate;
    private double balance;

    // getters and setters
    public double getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(double annualRate) {
        this.annualRate = annualRate;
    }
   
    public void setAnnualRate(String annualRate) {
        this.annualRate = Double.parseDouble(annualRate);
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
  
    public void setBalance(String balance) {
        this.balance = Double.parseDouble(balance);
    }
    
    public void deposit(double amount){
        balance += amount;
    }
    
    public void deposit(String amount){
        balance += Double.parseDouble(amount);
    }
    
    public void withdraw(double amount){
        balance -= amount;
    }
    
    public void withdraw(String amount){
        balance -= Double.parseDouble(amount);
    }
    
    public void addInterest() {
        double monthlyRate = annualRate / 12; // calculates monthly rate by dividing annual rate by 12
        double interest = (monthlyRate / 100) * balance; // calaculates interest by dividing monthly rate by 100
        balance += interest;
    }
    
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#0.00"); // formats to 2 decimal 
        String state = "Your bank account has a balance of $" + formatter.format(balance) + " and an annual interest rate of %" + annualRate; // displays message of balance and interest rate
        return state;
    }
    
    // numerical arguments constructor
    SavingsAccount(double startingBalance, double rate) {
        balance = startingBalance;
        annualRate = rate;
    }
    
    // String arguments constructor
    SavingsAccount(String startingBalance, String rate){
        balance = Double.parseDouble(startingBalance);
        annualRate = Double.parseDouble(rate);
    }
    
    // no-arg constructor
    SavingsAccount() {
        annualRate = 0;
        balance = 0;
    }
}

public class SavingsProgram {

    public static void main(String[] args) {
        Scanner scanN = new Scanner(System.in); // intializing scanners
        double initialDeposit; // assigning intial deposits to a double
        
        System.out.println("Welcome to the Bank of Mike Hawk"); // prints welcome messgage
        do { // begins do-while loop
            System.out.println("How much money would you like to deposit in your account (minimum $500)"); // prints following message
            initialDeposit = scanN.nextDouble();
            
        } while (initialDeposit < 500); // intial deposit cannot be less than 500
        int rate = (int) (3 + (Math.random() * 5)); // picks random integer from 3-7
        SavingsAccount myAccount = new SavingsAccount(initialDeposit, rate); // intializes values into savings account
       
        //set values to 0
        double totalDeposits = 0;
        double totalWithdrawals = 0;
        
        File nf1 = new File("Deposits.txt"); // reads file
        File nf2 = new File("Withdrawals.txt"); // reads file
        Scanner scanS;
        double line;
        
        try { // starts try catch loop, followed by grabbing information/values from deposits file by connecting scanners to file
            scanS = new Scanner(nf1);
            while(scanS.hasNext()) {
                line = scanS.nextDouble();
                totalDeposits += line; 
            }
            scanS.close();
        } catch(IOException e) {
            System.out.println("File error: " + e.getMessage()); //catches error and displays error message
        }
        
        try { // starts try catch loop, followed by grabbing information/values from withdrawals file by connecting scanners to file
            scanS = new Scanner(nf2);
            while(scanS.hasNext()) {
                line = scanS.nextDouble();
                totalWithdrawals += line; 
            }
            scanS.close();
        } catch(IOException e) {
            System.out.println("File error: " + e.getMessage()); //catches error and displays error message
        }
        
        myAccount.setBalance(myAccount.getBalance()+totalDeposits-totalWithdrawals); // updates balance with deposits and withdrawals
        
        System.out.println("After your initial deposit, you also deposited $" + totalDeposits); // prints total deposits
        System.out.println("You also withdrew $" + totalWithdrawals); // prints total withdrawals
       
        System.out.println("We are now adding monthly interest to your account..."); // prints message about monthly interest
        myAccount.addInterest(); // determines your interest
        System.out.println(myAccount.toString()); // returns string representation of object
    }
    
}
