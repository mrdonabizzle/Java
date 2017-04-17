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
public class PassengerShip extends Ship{
    int numberOfOccupiedRooms;
    int numberOfPassengers;
    int numberOfRooms;
    
    // constructor
    public PassengerShip(Scanner sc) {
        super(sc);
        if (sc.hasNextInt()) {
            numberOfPassengers = sc.nextInt();
        } // end if
        if (sc.hasNextInt()) {
            numberOfRooms = sc.nextInt();
        } // end if
        if (sc.hasNextInt()) {
            numberOfOccupiedRooms = sc.nextInt();
        } // end if
    } // end constructor
    
    @Override
    public String toString() {
        String outputString = "Passenger ship: " + super.toString();
        /*
        if (jobs.isEmpty())
            return outputString;
        for (Job j: jobs) {
            outputString += "\n    -" + j;
        }
        */
        return outputString;
    }
}
