/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmachine;

import java.text.NumberFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class Account {
    // create variables
    private double checkingBalance = 0;
    private double savingsBalance = 0;
    private int account = 1;
    private double dollarAmount = 0;
    private int withdrawalCounter = 0;
    // create instance of NumberFormat to convert output to dollars format
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    
    public Account() {
        checkingBalance = 500;
        savingsBalance = 500;
    } // end constructor method
    // method for withdrawing money.  1 is for checking, 0 is for savings
    public void withdrawMoney(int account, double dollarAmount) 
            throws InsufficientFunds {
        this.account = account;
        this.dollarAmount = dollarAmount;
        if (this.dollarAmount%20 == 0) {
            if (withdrawalCounter < 4) {
                if (this.account == 1) {
                    checkingBalance = checkingBalance - this.dollarAmount;
                    // check for sufficient funds
                    if (checkingBalance < 0) {
                        checkingBalance = checkingBalance + this.dollarAmount;
                        throw new InsufficientFunds();
                    } // end if
                } else {
                    savingsBalance = savingsBalance - this.dollarAmount;
                    // check for sufficient funds
                    if (savingsBalance < 0) {
                        savingsBalance = savingsBalance + this.dollarAmount;
                        throw new InsufficientFunds();
                    } // end if
                } // end if else
            withdrawalCounter++;
            JOptionPane.showMessageDialog(null, "Withdrawal successful\n" +
                    "Checking Balance: " + formatter.format(checkingBalance) + 
                    "\nSavings Balance: " + formatter.format(savingsBalance));
            } else {
                if (this.account == 1) {
                    checkingBalance = checkingBalance - this.dollarAmount - 1.5;
                    // check for sufficient funds
                    if (checkingBalance < 0) {
                        checkingBalance = checkingBalance + this.dollarAmount + 
                                1.5;
                        throw new InsufficientFunds();
                    } // end if
                } else {
                    savingsBalance = savingsBalance - this.dollarAmount - 1.5;
                    // check for sufficient funds
                    if (savingsBalance < 0) {
                        savingsBalance = savingsBalance + this.dollarAmount + 
                                1.5;
                        throw new InsufficientFunds();
                    } // end if
                } // end if else
                withdrawalCounter++;
                JOptionPane.showMessageDialog(null, "Withdrawal successful\n" +
                    "Checking Balance: " + formatter.format(checkingBalance) + 
                    "\nSavings Balance: " + formatter.format(savingsBalance));
            } // end if else
        } else {
            // error message when dollar amount is not an increment of 20
            JOptionPane.showMessageDialog(null, "Must be a $20 increment");
        } // end if else
    } // end method
    // Method to deposit money into an account
    public void depositMoney(int account, double dollarAmount) {
        this.account = account;
        this.dollarAmount = dollarAmount;
        if (this.account == 1) {
            checkingBalance = checkingBalance + this.dollarAmount;
        } else {
            savingsBalance = savingsBalance + this.dollarAmount;
        } // end if else
        JOptionPane.showMessageDialog(null, "Deposit successful\n" +
                    "Checking Balance: " + formatter.format(checkingBalance) + 
                    "\nSavings Balance: " + formatter.format(savingsBalance));
    } // end method
    // Method to transfer money from one account to the other
    public void transferMoney(int account, double dollarAmount) 
            throws InsufficientFunds {
        this.account = account;
        this.dollarAmount = dollarAmount;
        if (this.account == 1) {
            // check for sufficient funds
            if ((savingsBalance - this.dollarAmount) >= 0) {
                checkingBalance = checkingBalance + this.dollarAmount;
                savingsBalance = savingsBalance - this.dollarAmount;
            } else {
                throw new InsufficientFunds();
            } // end if else 
        } else {
            // check for sufficient funds
            if ((checkingBalance - this.dollarAmount) >= 0) {
                savingsBalance = savingsBalance + this.dollarAmount;
                checkingBalance = checkingBalance - this.dollarAmount;
            } else {
                throw new InsufficientFunds();
            } // end if else
        } // end if else
        JOptionPane.showMessageDialog(null, "Transfer successful\n" +
                    "Checking Balance: " + formatter.format(checkingBalance) + 
                    "\nSavings Balance: " + formatter.format(savingsBalance));
    } // end method
    // Method to display balance of accounts in JOptionPane
    public void balance(){
        JOptionPane.showMessageDialog(null, "Checking Balance: " + 
                formatter.format(checkingBalance) + "\nSavings Balance: " + 
                formatter.format(savingsBalance));
    } // end method
} // end class
