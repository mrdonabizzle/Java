/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

public class BinarySearchTree {
    private Node root;
    private String[] tokens;
    private Fraction[] fractions;
    private StringBuilder fractionOutput = new StringBuilder();
    
    // constructor
    public BinarySearchTree(String[] tokens) {
        this.tokens = tokens;
        root = null;
    } // end constructor
    
    // method to return String result from in order traverse of integer binary tree
    public String integersAscending() {
        // root node
        root = new Node(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[0]));
        // insert nodes in binary tree
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].matches("[0-9]+")) {
                root.insert(Integer.parseInt(tokens[i]), Integer.parseInt(tokens[i]));
            } else {
                throw new NumberFormatException();
            }// end if
        } // end for loop
        return root.inOrderTraversal(new StringBuilder("")).toString();
    } // end method
    
    // method to return String result from reverse order traverse of integer binary tree
    public String integersDescending() {
        // root node
        root = new Node(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[0]));
        // insert nodes in binary tree
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].matches("[0-9]+")) {
                root.insert(Integer.parseInt(tokens[i]), Integer.parseInt(tokens[i]));
            } else {
                throw new NumberFormatException();
            }// end if
        } // end for loop
        return root.reverseOrderTraversal(new StringBuilder("")).toString();
    } // end method
    
    // method to return String result from in order traverse of fraction binary tree
    public String fractionsAscending() {
        fractions = new Fraction[tokens.length];
        // create fraction objects from tokens
        for (int i = 0; i < tokens.length; i++) {
            fractions[i] = new Fraction(tokens[i]);
        } // end for      
        // root node
        root = new Node(fractions[0].getValue(), fractions[0].toString());
        // insert nodes in binary tree
        for (int i = 1; i < fractions.length; i++) {
            root.insert(fractions[i].getValue(), fractions[i].toString());
        } // end for
        return root.inOrderTraversal(new StringBuilder("")).toString();
    } // end method
    
    // method to return String result from reverse order traverse of fraction binary tree
    public String fractionsDescending() {
        fractions = new Fraction[tokens.length];
        // create fraction objects from tokens
        for (int i = 0; i < tokens.length; i++) {
            fractions[i] = new Fraction(tokens[i]);
        } // end for
        // root node
        root = new Node(fractions[0].getValue(), fractions[0].toString());
        // insert nodes in binary tree
        for (int i = 1; i < fractions.length; i++) {
            root.insert(fractions[i].getValue(), fractions[i].toString());
        } // end for    
        return root.reverseOrderTraversal(new StringBuilder("")).toString();
    } // end method
} // end class

    

