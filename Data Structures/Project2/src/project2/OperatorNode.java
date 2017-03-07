/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author willd
 */
public class OperatorNode extends Node {
    
    private Node left, right;
    private Operator operator;
    private int register;
    
    public OperatorNode(Operator operator, int register, Node left,
                        Node right) {
        this.operator = operator;
        this.register = register;
        this.left = left;
        this.right = right;    
    } // end constructor
    
    public String returnRegister() {
        return "R" + String.valueOf(register);
    } // end method
    /*
    @Override
    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();
       
        return operator.evaluate(leftValue, rightValue);
    }
*/

    // this method is used to create the infix expression for the GUI output
    @Override
    public String inOrderWalk() {
        String leftValue = left.inOrderWalk();
        String rightValue = right.inOrderWalk();
        return "(" + leftValue + " " + operator + " "
                + rightValue + ")";
    } // end method
    
}
