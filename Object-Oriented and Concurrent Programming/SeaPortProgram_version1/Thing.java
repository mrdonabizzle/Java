/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.Scanner;

/**
 *
 * @author willd
 */
public class Thing implements Comparable<Thing>{

    String name;
    String type;
    int index;
    int parent;
    
    // default constructor
    public Thing() {
        
    }

    // one argument constructor
    public Thing(Scanner sc) {
        if (sc.hasNext()) {
            type = sc.next();
        } // end if
        if (sc.hasNext()) {
            name = sc.next();
        } // end if
        if (sc.hasNextInt()) {
            index = sc.nextInt();
        } // end if
        if (sc.hasNextInt()) {
            parent = sc.nextInt();
        } // end if
    } // end constructor
    
    @Override
    public String toString() {
        String outputString = name + " " + index;
        return outputString;
    } // end method
    
    // I don't think this is required to be used yet
    @Override
    public int compareTo(Thing o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } // end method
    
} // end class
