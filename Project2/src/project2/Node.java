package project2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willd
 */

// This is the Node abstract class that is extended in the OperatorNode 
// and OperandNode classes
public abstract class Node {

    Node left;
    Node right;
    
    
    public double evaluate() {
        return 0;
    }
    public String returnRegister() {
        return null;
    }
    
    public String inOrderWalk() {
        return null;
    }
    
}

