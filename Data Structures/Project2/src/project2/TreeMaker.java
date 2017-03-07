/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author willd
 */

public class TreeMaker {
    private String infixString;
    private String postfixExpression;
    private Node operand1;
    private Node operand2;
    private Node finalTree;
    private FileWriter fileWriter;

    private String[] tokens = new String[100];
    private Stack<Node> operandStack = new Stack<Node>();
    
    //TreeMaker constructor
    public TreeMaker(String postfixExpression) {
        this.postfixExpression = postfixExpression;
        tokens = postfixExpression.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
    } // end TreeMaker constructor
    
    // The makeTree method creates the expression tree by using the operandStack,
    // returns the approprate infix expression for the GUI output, and writes
    // the three address instructions to the file, "file.csv"
    public String makeTree() throws IOException {
        fileWriter = new FileWriter("file.csv");
        int registerCounter = 0;
        OperatorNode opNode = null;
        for (int i = 0; i < tokens.length; i++) {
            // push operands onto operand stack
            if (tokens[i].matches("[0-9]+")) {
                operandStack.push(new OperandNode(Integer.parseInt(tokens[i])));
            // when there is an operator pop two operands from operand stack
            // and push new operator node onto operator stack
            } else if (tokens[i].contains("+")
                    || tokens[i].contains("-")
                    || tokens[i].contains("*")
                    || tokens[i].contains("/")) {
                operand2 = operandStack.pop();
                operand1 = operandStack.pop();
                if (tokens[i].contains("+")) {
                    opNode = new OperatorNode(new AddOperator(),registerCounter,
                            operand1,
                            operand2);
                    operandStack.push(opNode);
                    // adds three address instruction
                    fileWriter.append("Add R" + String.valueOf(registerCounter) + 
                            " " + operand1.returnRegister() + " " + operand2.returnRegister() +
                            "\n") ;
                    
                } else if (tokens[i].contains("-")) {
                    opNode = new OperatorNode(new SubOperator(),registerCounter,
                            operand1,
                            operand2);
                    operandStack.push(opNode);
                    // adds three address instruction
                    fileWriter.append("Sub R" + String.valueOf(registerCounter) + 
                            " " + operand1.returnRegister() + " " + operand2.returnRegister()+
                            "\n");

                } else if (tokens[i].contains("*")) {
                    opNode = new OperatorNode(new MulOperator(),registerCounter,
                            operand1,
                            operand2);
                    operandStack.push(opNode);
                    // adds three address instruction
                    fileWriter.append("Mul R" + String.valueOf(registerCounter) + 
                            " " + operand1.returnRegister() + " " + operand2.returnRegister() +
                            "\n");

                } else if (tokens[i].contains("/")) {
                    opNode = new OperatorNode(new DivOperator(),registerCounter,
                            operand1,
                            operand2);
                    operandStack.push(opNode);
                    // adds three address instruction
                    fileWriter.append("Div R" + String.valueOf(registerCounter) + 
                            " " + operand1.returnRegister() + " " + operand2.returnRegister() +
                            "\n"); 
                
                } // end if else
                // increment the register number for three address instructions
                registerCounter++;
            } else if (tokens[i].contains(" ")) {
                continue;
            } else {
                // throw RunTimeException for invalid token, which is caught
                // in the main class within the Construct Tree Button listener
                throw new RuntimeException(tokens[i]);
            } // end if else
        } // end for loop
        finalTree = operandStack.pop();
        infixString = finalTree.inOrderWalk();
        fileWriter.close();
        return infixString;
    } // end makeTree method
} // end class
