/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*  
 * File: Project2.java
 * Author: Will Donabedian
 * Date: January 24, 2017
 * Purpose: This program implements a GUI that allows the user to input a postfix 
 * expression, which is converted to an infix expression and output to a text field
 * on the GUI, and creates a text file that stores the three address instructions
 * for simplifying the expression.
 */

public class Project2 {
    // create class for the Input/Output Panel on GUI
    public static class IOPanel extends JPanel {
        // create variable to hold infix expression
        String postfixExpression;
        // create text fields
        private JTextField postfixExpressionTxt = new JTextField("");
        private JTextField infixExpressionTxt = new JTextField("");
        // create labels
        private JLabel postfixExpressionLbl = new JLabel("Enter Postfix Expression");
        private JLabel infixExpressionLbl = new JLabel("Infix Expression");
        // create buttons
        private JButton constructTreeBtn = new JButton("Construct Tree");
        //Input/Output Panel constructor
        public IOPanel() {
            // set layout
            setLayout(new GridLayout(3, 1));
            setBackground(Color.lightGray);
            // create the input panel for the text input field
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());
            //  modify and add new text field
            postfixExpressionTxt.setBackground(Color.WHITE);
            postfixExpressionTxt.setEditable(true);
            postfixExpressionTxt.setPreferredSize(new Dimension(200, 20));
            postfixExpressionTxt.setToolTipText("Enter your infix expression");
            inputPanel.add(postfixExpressionLbl);
            inputPanel.add(postfixExpressionTxt);
            // create the buttons panel
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            // create tool tip for Evaluate Button
            constructTreeBtn.setToolTipText("Click to construct tree");
            // set preferred size for button
            constructTreeBtn.setPreferredSize(new Dimension(150, 30));
            // add the  Jbuttons to the Button Panel
            buttonsPanel.add(constructTreeBtn);
            // create the output text panel
            JPanel outputPanel = new JPanel();
            outputPanel.setLayout(new FlowLayout());
            //modify and add new text field
            infixExpressionTxt.setBackground(Color.LIGHT_GRAY);
            infixExpressionTxt.setEditable(false);
            infixExpressionTxt.setPreferredSize(new Dimension(150, 20));
            outputPanel.add(infixExpressionLbl);
            outputPanel.add(infixExpressionTxt);
            // add the input, buttons, and output panels
            add(inputPanel);
            add(buttonsPanel);
            add(outputPanel);
            // create listener for when Construct Tree Button is clicked            
            constructTreeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    infixExpressionTxt.setText("");
                    postfixExpression = postfixExpressionTxt.getText();
                    TreeMaker answer = new TreeMaker(postfixExpression);
                           
                    try {
                        infixExpressionTxt.setText(answer.makeTree());
                       
                    } catch (IOException ex) {
                        Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage() + 
                                " is an invalid token");
                    } // end try-catch
                } // end method
            }); // end action listener
        } // end constructor
    } // end class IOPanel
    
    // create the frame for the three address generator
    public static class GeneratorFrame extends JFrame {
        private static final int WIDTH = 400;
        private static final int HEIGHT = 150;
        public GeneratorFrame(){
            super("Three Address Generator");
            super.setSize(WIDTH, HEIGHT);
            super.setLocationRelativeTo(null);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            super.add(new IOPanel());
        } // end method
    } // end class AtmFrame
    
    public static void main(String[] args) {
        GeneratorFrame gf = new GeneratorFrame();
        gf.setVisible(true);
    } // end main
} // end class
