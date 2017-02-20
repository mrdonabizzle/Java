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

import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JOptionPane;

public class InfixExpression {
    //declare private variables
    private String infixExpression;
    private int operand1;
    private int operand2;
    private String operator;
    private String[] tokens = new String[100];
    private Stack<String> operandStack = new Stack<String>();
    private Stack<String> operatorStack = new Stack<String>();
      
    // default constructor
    public InfixExpression() {
        operand1 = 0;
        operand2 = 0;
    } // end default constructor
    // create constructor with String infixExpression parameter
    public InfixExpression(String infixExpression) {
        operand1 = 0;
        operand2 = 0;
        this.infixExpression = infixExpression;
    } // end constructord
    // create method for simplifying infix expression and returning result
    public String getResult() throws DivideByZero {
        //tokenize String from infixExpressionTxt in Project1 class
        tokens = infixExpression.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
        /*JOptionPane.showMessageDialog(null, Arrays.toString(tokens));*/        
        try {
            // while there are tokens loop
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].matches("[0-9]+")) {
                    operandStack.push(tokens[i]);
                } else if (tokens[i].contains("(")) {
                    operatorStack.push(tokens[i]);
                } else if (tokens[i].contains(")")) {
                    // while the top of the stack is a left parenthesis
                    while (!operatorStack.peek().equals("(")) {
                        operand2 = Integer.parseInt(operandStack.pop());
                        operand1 = Integer.parseInt(operandStack.pop());
                        operator = (String) operatorStack.pop();
                        operandStack.push(performOperation(operand1, operand2, operator));
                    } // end while loop
                } else if (tokens[i].contains("*")
                        || tokens[i].contains("/")
                        || tokens[i].contains("+")
                        || tokens[i].contains("-")) {
                    while (!operatorStack.empty()
                            && ((operatorStack.peek().equals("*")
                            || operatorStack.peek().equals("/"))
                            || (((operatorStack.peek().equals("+")
                            || operatorStack.peek().equals("-"))
                            && (tokens[i].contains("+")
                            || tokens[i].contains("-")))))) {
                        operand2 = Integer.parseInt(operandStack.pop());
                        operand1 = Integer.parseInt(operandStack.pop());
                        operator = (String) operatorStack.pop();
                        operandStack.push(performOperation(operand1, operand2, operator));
                    } // end while
                    operatorStack.push(tokens[i]);
                }// end if else
            } // end for loop
            while (!operatorStack.empty()) {
                if (operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                } else {
                    operand2 = Integer.parseInt(operandStack.pop());
                    operand1 = Integer.parseInt(operandStack.pop());
                    operator = (String) operatorStack.pop();
                    operandStack.push(performOperation(operand1, operand2, operator));
                } // end if else
            } // end while loop       
        } catch (EmptyStackException ex) {
            JOptionPane.showMessageDialog(null, "Invalid infix expression");
        } // end try-catch        
        return operandStack.pop();
    } // end getResult method
    
    // create method to simplify expression using appropriate operations
    public String performOperation(int operand1, int operand2, String operator) throws DivideByZero {
        String operandStackPush = null;
        if (operator.equalsIgnoreCase("*")) {
            operandStackPush = (Integer.toString(operand1 * operand2));
        } else if (operator.equalsIgnoreCase("/")) {
            if (operand2 == 0) {
                // throw custom exception DivideByZero
                throw new DivideByZero("You cannot divide by zero");
            } // end if
             operandStackPush = (Integer.toString(operand1 / operand2));
        } else if (operator.equalsIgnoreCase("+")) {
            operandStackPush = (Integer.toString(operand1 + operand2));
        } else if (operator.equalsIgnoreCase("-")) {
            operandStackPush = (Integer.toString(operand1 - operand2));
        } // end if else
        return operandStackPush;
    } // end performOperation method
    
    // create custom exception class to handle dividing by zero
    public static class DivideByZero extends Exception {
        public DivideByZero(String message) {
            super(message);
        } // end constructor
    } // end custom exception class
} // end InfixExpression class
