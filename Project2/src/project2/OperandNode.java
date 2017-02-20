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
public class OperandNode extends Node {
    private int value;
    
    // Operand Node constructor
    public OperandNode(int value) {
        this.value = value;
    } // end constructor
   
    @Override
    public double evaluate() {
        return value;
    } // end method
    
    @Override
    public String returnRegister() {
        return String.valueOf(value);
    } // end method
    
    @Override
    public String inOrderWalk() {
        return String.valueOf(value);
    } // end method
    
   
} // end class
