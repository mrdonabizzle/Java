/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram2;

import java.util.Scanner;

/**
 *
 * @author willd
 */
public class Thing implements Comparable<Thing>{

    private String name;
    private String type;
    private int index;
    private int parent;
    
    public Thing() {
        
    }

    // constructor
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
    
    public int getIndex() {
        return index;
    }
    
    public int getParent() {
        return parent;
    }
    
    public String getName() {
        return name;
    }
    
    /*
    public int returnIndex(Scanner sc) {
        if (sc.hasNext()) {
            sc.next();
        } // end if
        if (sc.hasNext()) {
            sc.next();
        } // end if
        if (sc.hasNextInt()) {
            index = sc.nextInt();
        }
        return index;
    }
    */
    @Override
    public String toString() {
        String outputString = name + " " + index;
        return outputString;
    } // end method
    
    @Override
    public int compareTo(Thing o) {
        return (this.name).compareToIgnoreCase(o.name);
    } // end method
    
} // end class
