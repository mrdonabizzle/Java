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
public class CargoShip extends Ship{
    double cargoValue;
    double cargoVolume;
    double cargoWeight;

    public CargoShip(Scanner sc) {
        super(sc);
    } // end method
    
    @Override
    public String toString() {
        String outputString = "Cargo ship: " + super.toString();
        /*
        if (jobs.size() == 0)
            return outputString; // end if
        for (Job j: jobs) {
            outputString += "\n    -" + j;
        } // end for
        */
        return outputString;
    } // end method
    
} // end class
