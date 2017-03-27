/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Date;
import javax.swing.JOptionPane;

/* 
 * File:  SeaPortProgram.java
 * Author:  Will Donabedian
 * Date:  March 26, 2017
 * Purpose:  This program implements a GUI that allows the user to open and read
 * text files.  Once the file has been read, the user can display the internal
 * data structure in the text area.  The user can then search the results by 
 * index, name, or skill.
 */
public class SeaPortProgram extends JFrame{
    
    JTextArea jta = new JTextArea();
    World world = new World();
    // constructor
    public SeaPortProgram() {
        setTitle("SeaPort Program");
        setSize(620, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        //modify text area for displaying results
        jta.setFont(new java.awt.Font("Monospaced", 0, 12));
        JScrollPane jsp = new JScrollPane(jta);
        add (jsp, BorderLayout.CENTER);
        // add buttons
        JButton jbr = new JButton("Read File");
        JButton jbd = new JButton("Display");
        JButton jbs = new JButton("Search");
        // add label
        JLabel jls = new JLabel("Search target");
        // add textfield
        JTextField jtf = new JTextField(10);
        // add combobox for search fields
        JComboBox<String> jcb = new JComboBox<>();
        jcb.addItem("Index");
        jcb.addItem("Skill");
        jcb.addItem("Name");
        // add components to new panel
        JPanel jp = new JPanel();
        jp.add(jbr);
        jp.add(jbd);
        jp.add(jls);
        jp.add(jtf);
        jp.add(jcb);
        jp.add(jbs);
        // add panel to frame
        add(jp, BorderLayout.PAGE_START);
        validate();
        
        // Action listener for read file button
        jbr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(".");
                jfc.setDialogTitle("Sea Port Files");
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (jfc.showOpenDialog(jbr) == JFileChooser.APPROVE_OPTION) {
                } // end if
                readFile(jfc.getSelectedFile().getName());
            } // end method
        }); // end action listener
        // Action listener for display button
        jbd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayWorld();
            } // end method
        }); // end action listener
        // Action listener for search button
        jbs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search((String)(jcb.getSelectedItem()), jtf.getText());
            } // end method
        }); // end action listener
    } // end constructor
    
    // method for reading text file and creating appropriate objects
    public void readFile(String fileName) {
        Scanner scannerIn;
        Scanner lineScanner;
        File file = new File(fileName);
        SeaPort sp = null;
        try {
            scannerIn = new Scanner(file);
            while (scannerIn.hasNextLine()) {
                String line = scannerIn.nextLine();
                lineScanner = new Scanner(line);
                //System.out.print(line);
                // selection statement based on name of object
                if (line.length() == 0) {

                } else if (line.contains("//")) {
                    
                } else if (line.contains("port")) {
                    world.addPort(sp = new SeaPort(lineScanner));
                } else if (line.contains("dock")) {
                    world.addDock(new Dock(lineScanner));
                } else if (line.contains("cship")) {
                    world.addShip(new CargoShip(lineScanner));
                } else if (line.contains("pship")) {
                    world.addShip(new PassengerShip(lineScanner));
                } else if (line.contains("person")) {
                    world.addPerson(new Person(lineScanner));
                } else if (line.contains("job")) {
                    // code for Projects 3 and 4
                } else {
                    continue;
                }// end if else
            } // end while
            JOptionPane.showMessageDialog(null, "File read successfully.\n" +
                    "Press Display to show the results");
            //jta.append("File read successfully\n");
            //jta.append(world.toString());
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "File not found");
        } // end try catch
    } // end method
    
    // method to display internal data structure in text area
    public void displayWorld() {
        jta.append("" + world);
    } // end method
    
    public void search(String type, String target) {
        // create markers to indicate if skill, index, or name was found
        int skillFound = 0;
        int indexFound = 0;
        int nameFound = 0;
        // search for skill
        if (type == "Skill") {
            for (int i = 0; i<world.ports.size(); i++) {
               for (int j = 0; j<world.ports.get(i).persons.size(); j++) {
                   if (target.equalsIgnoreCase(world.ports.get(i).persons.get(j).skill)) {
                       jta.append("\n\n" + world.ports.get(i).persons.get(j).toString());
                       skillFound = 1;
                   } // end if
               } // end for
            } // end for
            // show message if skill was not found
            if (skillFound == 0) JOptionPane.showMessageDialog(null, "Skill not found");
        // search for index
        } else if (type == "Index") {
            for (int i = 0; i<world.ports.size(); i++) {
                if (target.equalsIgnoreCase(String.valueOf(world.ports.get(i).index))) {
                    jta.append("\n\n" + world.ports.get(i).searchToString());
                    indexFound = 1;
                    break;
                } else {
                    for (Dock d: world.ports.get(i).docks) {
                        if (target.equalsIgnoreCase(String.valueOf(d.index))) {
                            jta.append("\n\n" + d.searchToString());
                            indexFound = 1;
                            break;
                        } // end if
                    } // end for
                    for (Ship s: world.ports.get(i).ships) {
                        if (target.equalsIgnoreCase(String.valueOf(s.index))) {
                            jta.append("\n\n" + s.toString());
                            indexFound = 1;
                            break;
                        } // end if 
                    } // end for
                    for (Person p: world.ports.get(i).persons) {
                        if (target.equalsIgnoreCase(String.valueOf(p.index))) {
                            jta.append("\n\n" + p.toString());
                            indexFound = 1;
                            break;
                        } // end if
                    } // end for 
                }// end if else
            } // end for
            // show message if index was not found
            if (indexFound ==0) JOptionPane.showMessageDialog(null, "Index not found");
        // search for name
        } else if (type == "Name") {
            for (int i = 0; i < world.ports.size(); i++) {
                if (target.equalsIgnoreCase(world.ports.get(i).name)) {
                    jta.append("\n\n" + world.ports.get(i).searchToString());
                    nameFound = 1;
                    break;
                } else {
                    for (Dock d : world.ports.get(i).docks) {
                        if (target.equalsIgnoreCase(d.name)) {
                            jta.append("\n\n" + d.searchToString());
                            nameFound = 1;
                            break;
                        } // end if
                    } // end for
                    for (Ship s : world.ports.get(i).ships) {
                        if (target.equalsIgnoreCase(s.name)) {
                            jta.append("\n\n" + s.toString());
                            nameFound = 1;
                            break;
                        } // end if 
                    } // end for
                    for (Person p : world.ports.get(i).persons) {
                        if (target.equalsIgnoreCase(p.name)) {
                            jta.append("\n\n" + p.toString());
                            nameFound = 1;
                            break;
                        } // end if
                    } // end for
                } // end if else
            } // end for
            if (nameFound==0) JOptionPane.showMessageDialog(null, "Name not found");
        } // end if else
    } // end method
    // main method
    public static void main(String[] args) {
        SeaPortProgram spp = new SeaPortProgram(); 
        long currentTime = new Date().getTime();
        System.out.println(currentTime);
    } // end main
} // end class
