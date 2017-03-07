/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author willd
 */
public class Project4 {
    
    // create class for the Input/Output Panel on GUI
    public static class IOPanel extends JPanel {
        DirectedGraph digraph;
        // create variable to hold infix expression
        String originalList;
        // create text fields
        private JTextField inputFileNameTxt = new JTextField("");
        private JTextField classToRecompileTxt = new JTextField("");
        // create labels
        private final JLabel inputFileNameLbl = new JLabel("Input file name: ");
        private final JLabel classToRecompileLbl = new JLabel("Class to recompile");
        // create buttons
        private final JButton buildDiGraphBtn = new JButton("Build Directed Graph");
        private final JButton topoOrderBtn = new JButton("Topological Order");

        //Input/Output Panel constructor
        public IOPanel() {
            // set layout
            //setLayout(new GridLayout(2, 1));
            setLayout(new BorderLayout());
            setBackground(Color.lightGray);
            // create the input panel for the text input field
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout()); 
            //  modify and add content to the first part of the top panel
            inputFileNameTxt.setBackground(Color.WHITE);
            inputFileNameTxt.setEditable(true);
            inputFileNameTxt.setPreferredSize(new Dimension(150, 20));
            inputFileNameTxt.setToolTipText("Enter the file name");
            inputPanel.add(inputFileNameLbl);
            inputPanel.add(inputFileNameTxt);
            inputPanel.add(buildDiGraphBtn);
            // modify and add content to the second part of the top panel
            classToRecompileTxt.setBackground(Color.WHITE);
            classToRecompileTxt.setEditable(true);
            classToRecompileTxt.setPreferredSize(new Dimension(150, 20));
            classToRecompileTxt.setToolTipText("Enter the class name");
            inputPanel.add(classToRecompileLbl);
            inputPanel.add(classToRecompileTxt);
            inputPanel.add(topoOrderBtn);
            // create tool tip for Buttons
            buildDiGraphBtn.setToolTipText("Click to build directed graph");
            topoOrderBtn.setToolTipText("Click to perform topological sort");            
            // create panel for the radio buttons that determine sort order
            JPanel outputPanel = new JPanel();
            outputPanel.setLayout(new FlowLayout());
            //sortOrderPanel.setPreferredSize(new Dimension(200, 100));
            Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder outputPanelTitle = BorderFactory.createTitledBorder(loweredEtched, "Recompilation Order");
            outputPanel.setBorder(outputPanelTitle);
            outputPanel.setBackground(Color.WHITE);
            JTextArea outputTxtArea = new JTextArea(11,40);
            outputTxtArea.setBackground(Color.WHITE);
            outputTxtArea.setEditable(false);
            outputPanel.add(outputTxtArea);            
            // add the input panel, output panel, buttons panel, 
            // and radio buttons panels
            add(inputPanel, BorderLayout.CENTER);
            add(outputPanel, BorderLayout.SOUTH);     
            // create listener for when Perform Sort Button is clicked                                   
            buildDiGraphBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // String array for tokens from the original list
                    String fileName = inputFileNameTxt.getText();
                    digraph = new DirectedGraph(fileName);
                } // end method
            }); // end action listener 
            topoOrderBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outputTxtArea.removeAll();
                    try {
                        outputTxtArea.setText(digraph.convertIndexToString(digraph.topologicalSort(classToRecompileTxt.getText())));
                        digraph.resetIndexVisited();
                    } catch (InvalidClassNameException icne) {
                        JOptionPane.showMessageDialog(null, "Invalid Class Name");
                    } catch (CycleException ce) {
                        JOptionPane.showMessageDialog(null, "Cycle Error");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Exception");
                    }                    
                } // end method
            }); // end action listener 
        } // end constructor
    } // end class IOPanel

    // create the frame for the binary search tree sort
    public static class CDGFrame extends JFrame {
        private static final int WIDTH = 500;
        private static final int HEIGHT = 300;
        // binary search tree frame constructor
        public CDGFrame() {
            super("Class Dependency Graph");
            super.setSize(WIDTH, HEIGHT);
            super.setLocationRelativeTo(null);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            super.add(new IOPanel());
        } // end method
    } // end class AtmFrame
    
    // main method
    public static void main(String[] args) {
        CDGFrame gf = new CDGFrame();
        gf.setVisible(true);
    } // end main
} // end class
