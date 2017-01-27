/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmachine;
/* 
 * File:  AtmMachine.java
 * Author:  Will Donabedian
 * Date:  November 17, 2016
 * Purpose:  This program implements an ATM machine.  The user is able to use 
 * it to withdraw, deposit, and transfer funds to either checking or savings
 * accounts, as well as check the balance of either account.  
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AtmMachine {
    
    public static class IOPanel extends JPanel {
        // create input/output components
        private JTextField dollarAmountTxt = new JTextField("");
        // create buttons
        private JButton withdrawBtn = new JButton("Withdraw");
        private JButton depositBtn = new JButton("Deposit");
        private JButton transferBtn = new JButton("Transfer to");
        private JButton balanceBtn = new JButton("Balance");
        // create radio buttons
        private JRadioButton checkingBtn = new JRadioButton("Checking");
        private JRadioButton savingsBtn = new JRadioButton("Savings");
        // create new instance of Account
        Account accnt = new Account();
        // create variables for account type (1 for checking, 0 for savings),
        // and dollar input from user
        private int accountType = 1;
        private double dollarAmount = 0;

        public IOPanel() {
            setLayout(new BorderLayout());
            setBackground(Color.lightGray);
            // create the input panel for the text field
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());
            // create and add new text field
            dollarAmountTxt.setBackground(Color.WHITE);
            dollarAmountTxt.setEditable(true);
            dollarAmountTxt.setPreferredSize(new Dimension(150, 20));
            inputPanel.add(dollarAmountTxt);
            add(inputPanel, BorderLayout.SOUTH);
            // create the buttons panel
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            // create tool tip for Play Button
            withdrawBtn.setToolTipText("Click to withdraw money");
            depositBtn.setToolTipText("Click to deposit money");
            transferBtn.setToolTipText("Click to transfer money");
            balanceBtn.setToolTipText("Click to check your balance");
            checkingBtn.setToolTipText("Click for checking");
            savingsBtn.setToolTipText("Click for savings");
            add(buttonsPanel, BorderLayout.CENTER);
            // set preferred size for buttons
            withdrawBtn.setPreferredSize(new Dimension(100, 30));
            depositBtn.setPreferredSize(new Dimension(100, 30));
            transferBtn.setPreferredSize(new Dimension(100, 30));
            balanceBtn.setPreferredSize(new Dimension(100, 30));
            // add the  Jbuttons to the Button Panel
            buttonsPanel.add(withdrawBtn);
            buttonsPanel.add(depositBtn);
            buttonsPanel.add(transferBtn);
            buttonsPanel.add(balanceBtn);
            buttonsPanel.add(checkingBtn);
            buttonsPanel.add(savingsBtn);
            // create the radio buttons group
            ButtonGroup group = new ButtonGroup();
            group.add(checkingBtn);
            group.add(savingsBtn);
            checkingBtn.setSelected(true);
            
            // create listener for when Withdraw Button is clicked
            withdrawBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        dollarAmount = 
                                (Double.parseDouble(dollarAmountTxt.getText()));
                        if (dollarAmount < 0) {
                            throw new NumberFormatException();
                        }
                        accnt.withdrawMoney(accountType, dollarAmount);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Please enter" +
                                " a valid dollar amount");
                    } catch (InsufficientFunds isf) {
                        // I'm not sure if I need to put anything in here
                        // since I think this exception would be thrown from the 
                        // Withdraw method in the Account class.
                        // If something should go here, I should put the same
                        // JOptionPane.showMessageDialog that I have in the 
                        // InsufficientFunds class
                    }
                }                
            });  
            // create listener for when Deposit Button is clicked
            depositBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        dollarAmount = 
                                (Double.parseDouble(dollarAmountTxt.getText()));
                        if (dollarAmount < 0) {
                            throw new NumberFormatException();
                        }
                        accnt.depositMoney(accountType, dollarAmount);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Please enter" +
                                " a valid dollar amount");
                    }
                }                
            });   
            // create listener for when Transfer Button is clicked
            transferBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                    dollarAmount = 
                            (Double.parseDouble(dollarAmountTxt.getText()));
                    if (dollarAmount < 0) {
                        throw new NumberFormatException();
                    }
                    accnt.transferMoney(accountType, dollarAmount);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Please enter" +
                                " a valid dollar amount");
                    } catch (InsufficientFunds isf) {
                        // I'm not sure if I need to put anything in here
                        // since I think this exception would be thrown from the 
                        // Transfer method in the Account class
                    }
                }                
            });      
            // create listener for when Balance Button is clicked
            balanceBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accnt.balance();
                }                
            });
            // create listener for when Checking Button is clicked
            checkingBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accountType = 1;
                }                
            });   
            // create listener for when Savings Button is clicked
            savingsBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    accountType = 0;
                }                
            });     
        } // end constructor
    } // end class IOPanel

    public static class AtmFrame extends JFrame {
        private static final int WIDTH = 275;
        private static final int HEIGHT = 200;
        public AtmFrame(){
            super("ATM Machine");
            super.setSize(WIDTH, HEIGHT);
            super.setLocationRelativeTo(null);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            super.add(new IOPanel());
        } // end method
    } // end class AtmFrame
    
    public static void main(String[] args) {
        AtmFrame atm = new AtmFrame();
        atm.setVisible(true);
    } // end main
} // end class
    

    

