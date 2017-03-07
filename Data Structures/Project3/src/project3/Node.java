/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;
/**
 *
 * @author willd
 */
public class Node<T> {

    private T data;
    private double key;
    private StringBuilder textOutput;
    private Node<T> leftChild;
    private Node<T> rightChild;
    
    // Node constructor
    public Node(double key, T data) {
        this.key = key;
        this.data = data;
    } // end constructor
    
    // method to insert node in binary search tree
    public void insert(double key, T data) {
        
        if (key <= this.key) {
            if (leftChild == null) {
                leftChild = new Node(key, data);
            } else {
                leftChild.insert(key, data);
            } // end if else
        } else {
            if (rightChild == null) {
                rightChild = new Node(key, data);
            } else {
                rightChild.insert(key, data);
            } // end if else
        } // end if else
    } // end method
    
    // method for in order traverse of binary search tree
    // used for ascending order
    public StringBuilder inOrderTraversal(StringBuilder answer) {
        if (leftChild != null) {
            leftChild.inOrderTraversal(answer);
        } // end if
        textOutput = answer.append(data.toString()).append(" ");
        if (rightChild != null) {
            rightChild.inOrderTraversal(answer);
        } // end if
        return textOutput;
    } // end method
    
    // method for reverse order traverse of binary search tree
    // used for descending order
    public StringBuilder reverseOrderTraversal(StringBuilder answer) {        
        if (rightChild != null) {
            rightChild.reverseOrderTraversal(answer);
        } // end if
        textOutput = answer.append(data.toString()).append(" ");
        if (leftChild != null) {
            leftChild.reverseOrderTraversal(answer);
        } // end if
        return textOutput;       
    } // end method 
} // end class

