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
public class Dock extends Thing{
    Ship ship=null;

    // constructor
    public Dock(Scanner sc) {
        super(sc);       
    } // end constructor
    
    // method to add ship to dock---it appears that each dock can only have one ship
    public void addShip(Ship s) {
        /*
        if (s.parent==this.index) {
            ship = s;
        }*/
        ship = s;
    } // end method
    
    // method to return output String
    public String toString() {
        String outputString = "Dock: " + super.toString() + "\n";
        if (ship!=null) {
            outputString+= "  Ship: " + ship.toString() + "\n";
        } // end if
        return outputString;
    } // end method
    
    // method to print search results to text area
    public String searchToString() {
        String outputString = "Dock: " + super.toString();
        return outputString;
    } // end method
}
