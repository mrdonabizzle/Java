/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import studentdatabase.StudentDatabase.MainPanel.StudentFrame;

/*
 * File:  StudentDatabase.java
 * Author:  Will Donabedian
 * Date:  December 18, 2016
 * Purpose:  This program implements an GUI that allows the user to insert,
 * delete, find, and update student records in a hashmap database.
 */
public class StudentDatabase {

    public static class MainPanel extends JPanel {
        // create text fields
        private JTextField idTxt = new JTextField("");
        private JTextField nameTxt = new JTextField("");
        private JTextField majorTxt = new JTextField("");
        // create labels
        private JLabel idLbl = new JLabel("Id: ");
        private JLabel nameLbl = new JLabel("Name: ");
        private JLabel majorLbl = new JLabel("Major: ");
        private JLabel chooseSelectionLbl = new JLabel("Choose Selection");
        // create combo boxes
        private String[] actionStrings = {"Insert", "Delete", "Find", "Update"};
        private JComboBox actionList = new JComboBox(actionStrings);
        private String[] gradeStrings = {"A", "B", "C", "D", "F"};
        private JComboBox gradeList = new JComboBox(gradeStrings);
        private String[] creditStrings = {"3", "6"};
        private JComboBox creditList = new JComboBox(creditStrings);
        // create buttons
        private JButton processRequestBtn = new JButton("Process Request");
        // initialize hash map for database values
        HashMap hm = new HashMap();
        // constructor method
        public MainPanel() {
            setLayout(new GridLayout(1, 2));
            // create the input panel for the text field
            JPanel IOPanel = new JPanel();
            IOPanel.setLayout(new BoxLayout(IOPanel, BoxLayout.PAGE_AXIS));
            // create the panel for the labels
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BoxLayout(labelPanel, 
                    BoxLayout.PAGE_AXIS));
            // create the panel for grade input when Update is selected
            JPanel gradePanel = new JPanel();
            gradePanel.setLayout(new BoxLayout(gradePanel, 
                    BoxLayout.PAGE_AXIS));
            // create the panel for credit input when Update is selected
            JPanel creditPanel = new JPanel();
            creditPanel.setLayout(new BoxLayout(creditPanel, 
                    BoxLayout.PAGE_AXIS));
            // edit text fields  
            idTxt.setBackground(Color.WHITE);
            idTxt.setEditable(true);
            nameTxt.setBackground(Color.WHITE);
            nameTxt.setEditable(true);
            majorTxt.setBackground(Color.WHITE);
            // add items to labelPanel
            labelPanel.add(Box.createVerticalStrut(2));
            labelPanel.add(idLbl);
            labelPanel.add(Box.createVerticalStrut(10));
            labelPanel.add(nameLbl);
            labelPanel.add(Box.createVerticalStrut(10));
            labelPanel.add(majorLbl);
            labelPanel.add(Box.createVerticalStrut(10));
            labelPanel.add(chooseSelectionLbl);
            labelPanel.add(Box.createVerticalStrut(15));
            labelPanel.add(processRequestBtn);
            labelPanel.add(Box.createVerticalStrut(25));
            // add items to IOPanel
            IOPanel.add(idTxt);
            IOPanel.add(Box.createVerticalStrut(5));
            IOPanel.add(nameTxt);
            IOPanel.add(Box.createVerticalStrut(5));
            IOPanel.add(majorTxt);
            IOPanel.add(Box.createVerticalStrut(5));
            IOPanel.add(actionList);
            IOPanel.add(Box.createVerticalStrut(68));
            actionList.setSelectedIndex(0);
            // add panels
            add(labelPanel);
            add(IOPanel);
            // create tool tips      
            idTxt.setToolTipText("Enter student id");
            nameTxt.setToolTipText("Enter student name");
            majorTxt.setToolTipText("Enter student major");
            actionList.setToolTipText("Select action");
            processRequestBtn.setToolTipText("Click to process");
            // create action listener for Process Request Button
            processRequestBtn.addActionListener(new ActionListener() {                
                public void actionPerformed(ActionEvent e) {
                    // create variable to hold Hashmap key value (Student ID)
                    int studentID = 0;
                    try {
                        studentID = Integer.parseInt(idTxt.getText()); 
                        // Selection statement to allow student ID values 
                        // of at least 1
                        if (studentID < 1)
                            throw new NumberFormatException();
                        // Insert student record
                        if (actionList.getSelectedIndex() == 0) {
                            if (hm.containsKey(studentID) == true) {
                                JOptionPane.showMessageDialog(null, 
                                        "Record already exists");
                            } else {
                                Student student = new Student(nameTxt.getText(), 
                                        majorTxt.getText());
                                hm.put(studentID, student);
                                JOptionPane.showMessageDialog(null, "Record " +
                                        "successfully added");
                            } // end if else
                        // Delete student record
                        }else if (actionList.getSelectedIndex() == 1) {
                            if (hm.containsKey(studentID)) {
                                hm.remove(studentID);
                                JOptionPane.showMessageDialog(null, "Record " +
                                        "successfully deleted");
                            } else {
                                JOptionPane.showMessageDialog(null, 
                                        "Record does not exist");
                            } // end if else
                        // Find student record
                        } else if (actionList.getSelectedIndex() == 2) {
                            if (hm.containsKey(studentID)) {
                                JOptionPane.showMessageDialog(null, 
                                        hm.get(studentID).toString());
                            } else {
                                JOptionPane.showMessageDialog(null, 
                                        "Record does not exist");
                            } // end if else
                        // Update student record
                        } else if (actionList.getSelectedIndex() == 3) {
                            // create combobox items for JOptionPanes
                            String[] gradeList = {"A", "B", "C", "D", "F"};
                            String[] creditList = {"3", "6"};
                            float grade;
                            int credits;
                            if (hm.containsKey(studentID)) {
                                // input grade for course
                                String gradeValue = 
                                        (String)JOptionPane.showInputDialog(
                                        null,
                                        "Choose grade:",
                                        "Grade",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        gradeList,
                                        gradeList[0]);
                                // input credits for course
                                String creditValue = 
                                        (String)JOptionPane.showInputDialog(
                                        null,
                                        "Choose credits: ",
                                        "Credits",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        creditList,
                                        creditList[0]);  
                                //convert credits from String
                                credits = Integer.parseInt(creditValue);
                                // selection statement to convert letter grade
                                // to numeric value
                                if (gradeValue.equals("A")){
                                    grade = 4;
                                } else if (gradeValue.equals("B")) {
                                    grade = 3;
                                } else if (gradeValue.equals("C")) {
                                    grade = 2;
                                } else if (gradeValue.equals("D")) {
                                    grade = 1;
                                } else {
                                    grade = 0;
                                } // end if else
                                Student student = (Student) hm.get(studentID);
                                student.courseCompleted(grade, credits);
                                JOptionPane.showMessageDialog(null, "Record " +
                                        "successfully updated");                       
                            } else {
                                JOptionPane.showMessageDialog(null, 
                                        "Record does not exist");
                            } // end if else
                        } else {
                            System.exit(0);
                        } // end if else
                        // Clear text fields
                        idTxt.setText("");
                        nameTxt.setText("");
                        majorTxt.setText("");
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, 
                                "Please enter a valid numeric student ID");
                    } // end try catch
                } // end method                
            }); // end listener      
        } // end MainPanel constructor 
        
        public static class StudentFrame extends JFrame {
            private static final int WIDTH = 275;
            private static final int HEIGHT = 200;
            public StudentFrame() /*throws IOException*/{
                super("Project 4");
                super.setSize(WIDTH, HEIGHT);
                super.setLocationRelativeTo(null);
                super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                super.add(new MainPanel());
                super.setResizable(false);
            } // end method
        } // end class StudentFrame      
    } // end class MainPanel
    
    public static void main(String[] args) {
        StudentFrame stu = new StudentFrame();
        stu.setVisible(true);
    } // end main method
} // end class StudentDatabase
    

