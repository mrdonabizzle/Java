/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencecalculator;
/* 
 * File:  SequenceCalculator.java
 * Author:  Will Donabedian
 * Date:  December 3, 2016
 * Purpose:  This program implements an GUI that allows the user to calculate
 * a number in the sequence where n = 2*(n-1) + (n-2) by either iterative or 
 * recursive methods and shows the respective efficiency.  When the GUI is 
 * closed, a file is created that gives the value and efficiency for both 
 * methods of all terms, 0-10, in the sequence.
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SequenceCalculator {
    
    public static class MainPanel extends JPanel {
        // create input/output components
        private JTextField numberTxt = new JTextField("");
        private JTextField resultTxt = new JTextField("");
        private JTextField efficiencyTxt = new JTextField("");
        // create labels
        private JLabel enterNLbl = new JLabel("Enter n: ");
        private JLabel resultLbl = new JLabel("Result: ");
        private JLabel efficiencyLbl = new JLabel("Efficiency: ");
        // create buttons
        private JButton computeBtn = new JButton("Compute");
        // create radio buttons
        private JRadioButton iterativeBtn = new JRadioButton("Iterative");
        private JRadioButton recursiveBtn = new JRadioButton("Recursive");
        // create variable for numeric input
        private int n = 0;
        
        public MainPanel() {
            setLayout(new GridLayout(1, 2));
            // create the input panel for the text field
            JPanel IOPanel = new JPanel();
            IOPanel.setLayout(new BoxLayout(IOPanel, BoxLayout.PAGE_AXIS));
            // create the panel for the labels
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
            // edit text fields  
            numberTxt.setBackground(Color.WHITE);
            numberTxt.setEditable(true);
            resultTxt.setEditable(false);
            resultTxt.setBackground(Color.WHITE);
            efficiencyTxt.setEditable(false);
            efficiencyTxt.setBackground(Color.WHITE);
            // add buttons
            IOPanel.add(iterativeBtn);
            IOPanel.add(Box.createVerticalStrut(5));
            IOPanel.add(recursiveBtn);
            IOPanel.add(Box.createVerticalStrut(5));
            IOPanel.add(numberTxt);
            IOPanel.add(Box.createVerticalStrut(5));
            IOPanel.add(computeBtn);
            IOPanel.add(Box.createVerticalStrut(5));
            // add texd fields
            IOPanel.add(resultTxt);
            IOPanel.add(Box.createVerticalStrut(5));
            IOPanel.add(efficiencyTxt);
            labelPanel.add(Box.createVerticalStrut(58));
            // add labels
            labelPanel.add(enterNLbl);
            labelPanel.add(Box.createVerticalStrut(40));
            labelPanel.add(resultLbl);
            labelPanel.add(Box.createVerticalStrut(10));
            labelPanel.add(efficiencyLbl);
            // add panels
            add(labelPanel);
            add(IOPanel);
            // create tool tips            
            iterativeBtn.setToolTipText("Click to select iteration");
            recursiveBtn.setToolTipText("Click to select recursion");
            computeBtn.setToolTipText("Click to compute");
            numberTxt.setToolTipText("Enter the number of the sequence");
            resultTxt.setToolTipText("Displays the result");
            efficiencyTxt.setToolTipText("Displays the efficiency");            
            // create the radio buttons group
            ButtonGroup group = new ButtonGroup();
            group.add(iterativeBtn);
            group.add(recursiveBtn);
            iterativeBtn.setSelected(true);                      
            // create listener for when Deposit Button is clicked
            computeBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int output = 0;
                    int efficiency = 0;
                    try {
                        n = (Integer.parseInt(numberTxt.getText()));
                        if (n < 0) {
                            throw new NumberFormatException();
                        } else {
                            if (iterativeBtn.isSelected() == true) {
                                output = Sequence.computeIterative(n);
                                efficiency = Sequence.getEfficiency();
                                resultTxt.setText(String.valueOf(output));
                                efficiencyTxt.setText(String.valueOf(efficiency));
                            } else {
                                output = Sequence.computeRecursive(n);
                                efficiency = Sequence.getEfficiency();
                                resultTxt.setText(String.valueOf(output));
                                efficiencyTxt.setText(String.valueOf(efficiency));
                            } // end if else
                        } // end if else
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Please enter" +
                                " a valid number of at least 1");
                    } // end try catch
                } // end method                
            }); // end listener
        } // end constructor
    } // end class
    
    public static class CalculatorFrame extends JFrame {
        private static final int WIDTH = 275;
        private static final int HEIGHT = 200;
        public CalculatorFrame() throws IOException{
            super("Project 3");
            super.setSize(WIDTH, HEIGHT);
            super.setLocationRelativeTo(null);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            super.add(new MainPanel());
            super.setResizable(false);
            FileWriter fileWriter = new FileWriter("file.csv");
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    try {
                        // add Header to file
                        fileWriter.append("n");
                        fileWriter.append(",");
                        fileWriter.append("iterative");
                        fileWriter.append(",");
                        fileWriter.append("recursive");
                        fileWriter.append("\n");
                        // add n values, and efficiencies to file
                        for (int i = 0; i <= 10; i++) {
                            fileWriter.append(String.valueOf(i));
                            fileWriter.append(",");
                            Sequence.computeIterative(i);
                            fileWriter.append(String.valueOf(Sequence.getEfficiency()));
                            fileWriter.append(",");
                            Sequence.computeRecursive(i);
                            fileWriter.append(String.valueOf(Sequence.getEfficiency() + 
                                "\n"));
                        } // end for
                        fileWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SequenceCalculator.class.getName()).log(Level.SEVERE, null, ex);
                    } // end try catch
                } // end method
            }); // end listener          
        } // end method
    } // end class AtmFrame
    
    public static void main(String[] args) throws IOException {
        CalculatorFrame cf = new CalculatorFrame();
        cf.setVisible(true);    
    } // end main
} // end class
