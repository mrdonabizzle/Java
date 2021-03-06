/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author willd
 */
public class Ship extends Thing{
    PortTime arrivalTime;
    PortTime dockTime;
    double draft;
    double length;
    double weight;
    double width;
    ArrayList<Job> jobs;
    
    // constructor
    public Ship(Scanner sc) {
        super(sc);
        
        if (sc.hasNextDouble()) {
            weight = sc.nextDouble();
        }
        if (sc.hasNextDouble()) {
            length = sc.nextDouble();
        }
        if (sc.hasNextDouble()) {
            width = sc.nextDouble();
        }
        if (sc.hasNextDouble()) {
            draft = sc.nextDouble();
        }
    } // end constructor
    
    // method to return String output
    @Override
    public String toString() {
        String outputString = super.toString();
        return outputString;
    } // end method
} // end class
