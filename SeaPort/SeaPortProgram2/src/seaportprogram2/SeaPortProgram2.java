/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram2;

import java.awt.GridLayout;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author willd
 */
public class SeaPortProgram2 extends JFrame {

    JTextArea jta = new JTextArea();
    World world = new World();

    public SeaPortProgram2() {
        setTitle("SeaPort Program");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,2));
        jta.setFont(new java.awt.Font("Monospaced", 0, 12));
        JScrollPane jsp = new JScrollPane(jta);
        //add(jsp);
        //add(jsp, BorderLayout.CENTER);

        
        JPanel readDisplayPanel = new JPanel();
        JButton jbr = new JButton("Read File");
        JButton jbd = new JButton("Display");
        readDisplayPanel.add(jbr);
        readDisplayPanel.add(jbd);
        
        JPanel searchPanel = new JPanel();
        JLabel jls = new JLabel("Search target");
        JTextField jtf = new JTextField(10);
        JComboBox<String> jcb = new JComboBox<>();
        jcb.addItem("Index");
        jcb.addItem("Skill");
        jcb.addItem("Name");
        JButton jbs = new JButton("Search");
        searchPanel.add(jls);
        searchPanel.add(jtf);
        searchPanel.add(jcb);
        searchPanel.add(jbs);
        
        JPanel sortPanel = new JPanel();
        JLabel sortLbl = new JLabel("Sort Que by ");
        JComboBox<String> sortQueCB = new JComboBox<>();
        sortQueCB.addItem("Weight");
        sortQueCB.addItem("Length");
        sortQueCB.addItem("Width");
        sortQueCB.addItem("Draft");
        JButton sortQueBtn = new JButton("Sort Que");
        JButton sortNameBtn = new JButton("Sort by name");
        sortPanel.add(sortLbl);
        sortPanel.add(sortQueCB);
        sortPanel.add(sortQueBtn);
        sortPanel.add(sortNameBtn);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(readDisplayPanel);
        mainPanel.add(searchPanel);
        mainPanel.add(sortPanel);
        
        /*
        jp.add(jbr);
        jp.add(jbd);
        jp.add(jls);
        jp.add(jtf);
        jp.add(jcb);
        jp.add(jbs);
        jp.add(sortLbl);
        jp.add(sortQueCB);
        jp.add(sortQueBtn);
        jp.add(sortNameBtn);
        */
        
        add(mainPanel);
        add(jsp);
        //add(jp, BorderLayout.PAGE_START);

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
                search((String) (jcb.getSelectedItem()), jtf.getText());
            } // end method
        }); // end action listener
        
        // Action listener for sort que button
        sortQueBtn.addActionListener(new ActionListener() {
            @Override
            //****modify below
            public void actionPerformed(ActionEvent e) {
                sortQue((String)sortQueCB.getSelectedItem());
            } // end method
        }); // end action listener
        
        // Action listener for sort name button
        sortNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortName();
            } // end method
        }); // end action listener


    } // end constructor

    public void readFile(String fileName) {
        HashMap<Integer, SeaPort> seaPortHM = new HashMap<>();
        HashMap<Integer, Dock> dockHM = new HashMap<>();
        Scanner scannerIn;
        Scanner lineScanner;
        File file = new File(fileName);
        SeaPort sp = null;
        try {
            scannerIn = new Scanner(file);
            while (scannerIn.hasNextLine()) {
                String line = scannerIn.nextLine();
                lineScanner = new Scanner(line);
                // create String array to parse index from the line, which
                // allows us to eliminate the index from the Thing objects
                String[] indexFinder;
                indexFinder = line.split("\\s+");
                //System.out.print(line);
                if (line.length() == 0) {
                } else if (line.contains("//")) {
                } else if (line.contains("port")) {
                    sp = new SeaPort(lineScanner);
                    // must use indexFinder[2] because the index of seaports
                    // is in index 2 of the array
                    seaPortHM.put(Integer.valueOf(indexFinder[2]), sp);
                    world.addPort(sp/*, seaPortHM*/);
                } else if (line.contains("dock")) {
                    Dock d = new Dock(lineScanner);
                    // must use indexFinder[3] because the dock lines in txt file
                    // begin with white space, so the index is in index 3 of the array
                    dockHM.put(Integer.valueOf(indexFinder[3]), d);
                    world.addDock(d, seaPortHM);
                } else if (line.contains("cship")) {
                    world.addShip(new CargoShip(lineScanner), seaPortHM, dockHM);                    
                } else if (line.contains("pship")) {
                    world.addShip(new PassengerShip(lineScanner), seaPortHM, dockHM); 
                } else if (line.contains("person")) {
                    world.addPerson(new Person(lineScanner), seaPortHM);
                } else if (line.contains("job")) {
                    // code for Projects 3 and 4
                } else {
                    continue;
                }// end if else
            } // end while
            JOptionPane.showMessageDialog(null, "File read successfully.\n"
                    + "Press Display to show the results");
            //jta.append("File read successfully\n");
            //jta.append(world.toString());
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "File not found");
        } // end try catch
    } // end method

    public void displayWorld() {
        jta.append("" + world);
    } // end method

    public void sortQue(String type) {
        if (type == "Weight") {
            for (SeaPort sp : world.ports)
                Collections.sort(sp.que, new WeightComparator());
        } else if (type == "Length") {
            for (SeaPort sp: world.ports)
                Collections.sort(sp.que, new LengthComparator());
        } else if (type == "Width") {
            for (SeaPort sp: world.ports)
                Collections.sort(sp.que, new WidthComparator());
        } else if (type == "Draft") {
            for (SeaPort sp: world.ports)
                Collections.sort(sp.que, new DraftComparator());
        }
        for (SeaPort sp : world.ports)
            jta.append(sp.sortQueToString());
        //jta.append("\n\n" + world);
    }
    
    public void sortName() {
        Collections.sort(world.ports);
        for (SeaPort sp : world.ports) {
            Collections.sort(sp.docks);
            Collections.sort(sp.persons);
            Collections.sort(sp.que);
            Collections.sort(sp.ships);
        }
        jta.append("\n\n" + world);
    }
    
    public void search(String type, String target) {
        // create markers to indicate if skill, index, or name was found
        int skillFound = 0;
        int indexFound = 0;
        int nameFound = 0;
        // search for skill
        if (type == "Skill") {
            for (int i = 0; i < world.ports.size(); i++) {
                for (int j = 0; j < world.ports.get(i).persons.size(); j++) {
                    if (target.equalsIgnoreCase(world.ports.get(i).persons.get(j).skill)) {
                        jta.append("\n\n" + world.ports.get(i).persons.get(j).toString());
                        skillFound = 1;
                    } // end if
                } // end for
            } // end for
            // show message if skill was not found
            if (skillFound == 0) {
                JOptionPane.showMessageDialog(null, "Skill not found");
            }
            // search for index
        } else if (type == "Index") {
            for (int i = 0; i < world.ports.size(); i++) {
                if (target.equalsIgnoreCase(String.valueOf(world.ports.get(i).getIndex()))) {
                    jta.append("\n\n" + world.ports.get(i).searchToString());
                    indexFound = 1;
                    break;
                } else {
                    for (Dock d : world.ports.get(i).docks) {
                        if (target.equalsIgnoreCase(String.valueOf(d.getIndex()))) {
                            jta.append("\n\n" + d.searchToString());
                            indexFound = 1;
                            break;
                        } // end if
                    } // end for
                    for (Ship s : world.ports.get(i).ships) {
                        if (target.equalsIgnoreCase(String.valueOf(s.getIndex()))) {
                            jta.append("\n\n" + s.toString());
                            indexFound = 1;
                            break;
                        } // end if 
                    } // end for
                    for (Person p : world.ports.get(i).persons) {
                        if (target.equalsIgnoreCase(String.valueOf(p.getIndex()))) {
                            jta.append("\n\n" + p.toString());
                            indexFound = 1;
                            break;
                        } // end if
                    } // end for 
                }// end if else
            } // end for
            // show message if index was not found
            if (indexFound == 0) {
                JOptionPane.showMessageDialog(null, "Index not found");
            }
            // search for name
        } else if (type == "Name") {
            for (int i = 0; i < world.ports.size(); i++) {
                if (target.equalsIgnoreCase(world.ports.get(i).getName())) {
                    jta.append("\n\n" + world.ports.get(i).searchToString());
                    nameFound = 1;
                    break;
                } else {
                    for (Dock d : world.ports.get(i).docks) {
                        if (target.equalsIgnoreCase(d.getName())) {
                            jta.append("\n\n" + d.searchToString());
                            nameFound = 1;
                            break;
                        } // end if
                    } // end for
                    for (Ship s : world.ports.get(i).ships) {
                        if (target.equalsIgnoreCase(s.getName())) {
                            jta.append("\n\n" + s.toString());
                            nameFound = 1;
                            break;
                        } // end if 
                    } // end for
                    for (Person p : world.ports.get(i).persons) {
                        if (target.equalsIgnoreCase(p.getName())) {
                            jta.append("\n\n" + p.toString());
                            nameFound = 1;
                            break;
                        } // end if
                    } // end for
                } // end if else
            } // end for
            if (nameFound == 0) {
                JOptionPane.showMessageDialog(null, "Name not found");
            }
        } // end if else
    } // end method

    public static void main(String[] args) {
        SeaPortProgram2 spp = new SeaPortProgram2();
        long currentTime = new Date().getTime();
        System.out.println(currentTime);

    } // end main
} // end class
