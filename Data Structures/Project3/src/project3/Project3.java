/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * File: Project3.java
 * Author: Will Donabedian
 * Date: February 19, 2017
 * Purpose: This program implements a GUI that allows the user to input a list 
 * of integers or fractions, which is converted to a binary search tree and 
 * sorted in either ascending or descending order.  It implements recursive
 * methods to insert new nodes and to walk the tree (in order or in reverse order)
 * to produce the appropriate output.
 */

public class Project3 {

    // create class for the Input/Output Panel on GUI
    public static class IOPanel extends JPanel {
        // create variable to hold infix expression
        String originalList;
        // create text fields
        private JTextField originalListTxt = new JTextField("");
        private JTextField sortedListTxt = new JTextField("");
        // create labels
        private JLabel originalListLbl = new JLabel("Original List");
        private JLabel sortedListLbl = new JLabel("Sorted List");
        // create buttons
        private JButton performSortBtn = new JButton("Perform Sort");

        //Input/Output Panel constructor
        public IOPanel() {
            // set layout
            setLayout(new GridLayout(4, 1));           
            setBackground(Color.lightGray);
            // create the input panel for the text input field
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());
            //  modify and add new text field
            originalListTxt.setBackground(Color.WHITE);
            originalListTxt.setEditable(true);
            originalListTxt.setPreferredSize(new Dimension(250, 20));
            originalListTxt.setToolTipText("Enter your infix expression");
            inputPanel.add(originalListLbl);
            inputPanel.add(originalListTxt);
            // create the buttons panel
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());
            // create tool tip for Evaluate Button
            performSortBtn.setToolTipText("Click to perform sort");
            // set preferred size for button
            performSortBtn.setPreferredSize(new Dimension(150, 30));
            // add the  Jbuttons to the Button Panel
            buttonsPanel.add(performSortBtn);
            // create the output text panel
            JPanel outputPanel = new JPanel();
            outputPanel.setLayout(new FlowLayout());
            //modify and add new text field
            sortedListTxt.setBackground(Color.LIGHT_GRAY);
            sortedListTxt.setEditable(false);
            sortedListTxt.setPreferredSize(new Dimension(250, 20));
            outputPanel.add(sortedListLbl);
            outputPanel.add(sortedListTxt);
            JPanel radioButtonsPanel = new JPanel();
            // create main panel for radio buttons
            radioButtonsPanel.setLayout(new FlowLayout());
            // create panel for the radio buttons that determine sort order
            JPanel sortOrderPanel = new JPanel();
            sortOrderPanel.setLayout(new GridLayout(2, 1));
            sortOrderPanel.setPreferredSize(new Dimension(200, 100));
            Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder sortOrderTitle = BorderFactory.createTitledBorder(loweredEtched, "Sort Order");
            sortOrderPanel.setBorder(sortOrderTitle);
            JRadioButton ascendingBtn = new JRadioButton("Ascending");
            ascendingBtn.setSelected(true);
            JRadioButton descendingBtn = new JRadioButton("Descending");
            ButtonGroup group1 = new ButtonGroup();
            group1.add(ascendingBtn);
            group1.add(descendingBtn);
            sortOrderPanel.add(ascendingBtn);
            sortOrderPanel.add(descendingBtn);
            // create panel for the radio buttons that determine numeric type
            JPanel numericTypePanel = new JPanel();
            numericTypePanel.setLayout(new GridLayout(2, 1));
            numericTypePanel.setPreferredSize(new Dimension(200, 100));
            TitledBorder numericTypeTitle = BorderFactory.createTitledBorder(loweredEtched, "Numeric Type");
            numericTypePanel.setBorder(numericTypeTitle);
            JRadioButton integerBtn = new JRadioButton("Integer");
            integerBtn.setSelected(true);
            JRadioButton fractionBtn = new JRadioButton("Fraction");
            ButtonGroup group2 = new ButtonGroup();
            group2.add(integerBtn);
            group2.add(fractionBtn);
            numericTypePanel.add(integerBtn);
            numericTypePanel.add(fractionBtn);
            // add both of the radio buttons panels to the main radio button panel
            radioButtonsPanel.add(sortOrderPanel);
            radioButtonsPanel.add(numericTypePanel);            
            // add the input panel, output panel, buttons panel, 
            // and radio buttons panels
            add(inputPanel);
            add(outputPanel);
            add(buttonsPanel);
            add(radioButtonsPanel);
            // create listener for when Perform Sort Button is clicked                       
            performSortBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // String array for tokens from the original list
                    String[] tokens;
                    sortedListTxt.setText("");
                    originalList = originalListTxt.getText();
                    tokens = originalList.split("\\s");
                    try {
                        BinarySearchTree integerTree = new BinarySearchTree(tokens);
                        if (ascendingBtn.isSelected()) {
                            if (integerBtn.isSelected()) {
                                sortedListTxt.setText(integerTree.integersAscending());
                            } else {
                                sortedListTxt.setText(integerTree.fractionsAscending());
                            } // end if else
                        } // end if
                        if (descendingBtn.isSelected()) {                       
                            if (integerBtn.isSelected()) {
                                sortedListTxt.setText(integerTree.integersDescending());
                            } else {       
                                sortedListTxt.setText(integerTree.fractionsDescending());
                            } // end if else
                        } // end if
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Non numeric Input");
                    } catch (ArithmeticException ae) {
                        JOptionPane.showMessageDialog(null, "Denominator cannot be 0");
                    }// end try catch
                } // end method
            }); // end action listener            
        } // end constructor
    } // end class IOPanel

    // create the frame for the binary search tree sort
    public static class BSTFrame extends JFrame {
        private static final int WIDTH = 500;
        private static final int HEIGHT = 435;
        // binary search tree frame constructor
        public BSTFrame() {
            super("Binary Search Tree Sort");
            super.setSize(WIDTH, HEIGHT);
            super.setLocationRelativeTo(null);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            super.add(new IOPanel());
        } // end method
    } // end class AtmFrame

    // main method
    public static void main(String[] args) {
        BSTFrame gf = new BSTFrame();
        gf.setVisible(true);
    } // end main
} // end class
