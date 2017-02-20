/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

/*  
 * File: Project1.java
 * Author: Will Donabedian
 * Date: January 18, 2017
 * Purpose: This program implements an infix expression calculator.  The user
 * can enter an infix expression, and the program utilizes Stacks to properly
 * simplify the expression using the order of operations.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import project1.InfixExpression.DivideByZero;

public class Project1 {
    // create class for the Input/Output Panel on GUI
    public static class IOPanel extends JPanel {
        // create variable to hold infix expression
        String infixExpression;
        // create text fields
        private JTextField infixExpressionTxt = new JTextField("");
        private JTextField resultTxt = new JTextField("");
        // create labels
        private JLabel infixExpressionLbl = new JLabel("Enter Infix Expression");
        private JLabel resultLbl = new JLabel("Result");
        // create buttons
        private JButton evaluateBtn = new JButton("Evaluate");
        //Input/Output Panel constructor
        public IOPanel() {
            // set layout
            setLayout(new GridLayout(3, 1));
            setBackground(Color.lightGray);
            // create the input panel for the text input field
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());
            //  modify and add new text field
            infixExpressionTxt.setBackground(Color.WHITE);
            infixExpressionTxt.setEditable(true);
            infixExpressionTxt.setPreferredSize(new Dimension(200, 20));
            infixExpressionTxt.setToolTipText("Enter your infix expression");
            inputPanel.add(infixExpressionLbl);
            inputPanel.add(infixExpressionTxt);
            // create the buttons panel
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            // create tool tip for Evaluate Button
            evaluateBtn.setToolTipText("Click to evaluate the expression");
            // set preferred size for button
            evaluateBtn.setPreferredSize(new Dimension(100, 30));
            // add the  Jbuttons to the Button Panel
            buttonsPanel.add(evaluateBtn);
            // create the output text panel
            JPanel outputPanel = new JPanel();
            outputPanel.setLayout(new FlowLayout());
            //modify and add new text field
            resultTxt.setBackground(Color.LIGHT_GRAY);
            resultTxt.setEditable(false);
            resultTxt.setPreferredSize(new Dimension(150, 20));
            outputPanel.add(resultLbl);
            outputPanel.add(resultTxt);
            // add the input, buttons, and output panels
            add(inputPanel);
            add(buttonsPanel);
            add(outputPanel);
            // create listener for when Evalute Button is clicked            
            evaluateBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resultTxt.setText("");
                    infixExpression = infixExpressionTxt.getText();
                    try {
                        // create new InfixExpression Object
                        InfixExpression infix = new InfixExpression(infixExpression);
                        // call getResult() method to simplify the infix expression
                        resultTxt.setText(infix.getResult());
                    // handle the custom DivideByZero exception
                    } catch (DivideByZero ex) {
                        JOptionPane.showMessageDialog(null, "You cannot divide by zero");                    
                    } // end try-catch
                } // end method
            }); // end action listener
        } // end constructor
    } // end class IOPanel
    
    // create the frame for the infix calculator
    public static class CalculatorFrame extends JFrame {
        private static final int WIDTH = 400;
        private static final int HEIGHT = 150;
        public CalculatorFrame(){
            super("Infix Expression Calculator");
            super.setSize(WIDTH, HEIGHT);
            super.setLocationRelativeTo(null);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            super.add(new IOPanel());
        } // end method
    } // end class AtmFrame
    
    public static void main(String[] args) {
        CalculatorFrame cf = new CalculatorFrame();
        cf.setVisible(true);
    } // end main
} // end class
